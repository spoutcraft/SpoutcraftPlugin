/*
 * This file is part of Spout API (http://wiki.getspout.org/).
 * 
 * Spout API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.SliderDragEvent;
import org.getspout.spoutapi.event.screen.TextFieldChangeEvent;
import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.Slider;
import org.getspout.spoutapi.gui.TextField;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketControlAction implements SpoutPacket{
	protected UUID screen;
	protected UUID widget;
	protected float state;
	protected String data = "";
	public PacketControlAction() {
		
	}
	
	public PacketControlAction(Screen screen, Widget widget, float state) {
		this.screen = screen.getId();
		this.widget = widget.getId();
		this.state = state;
	}
	
	public PacketControlAction(Screen screen, Widget widget, String data) {
		this.screen = screen.getId();
		this.widget = widget.getId();
		this.state = 0F;
		this.data = data;
	}

	@Override
	public int getNumBytes() {
		return 36 + PacketUtil.getNumBytes(data);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		long msb = input.readLong();
		long lsb = input.readLong();
		this.screen = new UUID(msb, lsb);
		msb = input.readLong();
		lsb = input.readLong();
		this.widget = new UUID(msb, lsb);
		this.state = input.readFloat();
		this.data = PacketUtil.readString(input);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeLong(screen.getMostSignificantBits());
		output.writeLong(screen.getLeastSignificantBits());
		output.writeLong(widget.getMostSignificantBits());
		output.writeLong(widget.getLeastSignificantBits());
		output.writeFloat(state);
		PacketUtil.writeString(output, data);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		if (player != null) {
			Screen screen = null;
			if (player.getMainScreen().getId().equals(this.screen)) {
				screen = player.getMainScreen();
			}
			if (player.getMainScreen().getActivePopup() != null && player.getMainScreen().getActivePopup().getId().equals(this.screen)) {
				screen = player.getMainScreen().getActivePopup();
			}
			if (screen != null) {
				Widget control = screen.getWidget(widget);
				if (control != null) {
					if (control instanceof Button) {
						ButtonClickEvent event = new ButtonClickEvent(player, screen, (Button)control);
						((Button)control).onButtonClick(event);
						Bukkit.getServer().getPluginManager().callEvent(event);
					}
					else if (control instanceof Slider) {
						SliderDragEvent event = new SliderDragEvent(player, screen, (Slider)control, state);
						((Slider)control).onSliderDrag(event);
						Bukkit.getServer().getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							((Slider)control).setSliderPosition(event.getOldPosition());
							control.setDirty(true);
						}
						else if (event.getNewPosition() != state) {
							((Slider)control).setSliderPosition(event.getNewPosition());
							control.setDirty(true);
						}
						else {
							((Slider)control).setSliderPosition(event.getNewPosition());
						}
					}
					else if (control instanceof TextField) {
						TextFieldChangeEvent event = new TextFieldChangeEvent(player, screen, (TextField)control, data);
						((TextField)control).onTextFieldChange(event);
						Bukkit.getServer().getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							((TextField)control).setText(event.getOldText());
							control.setDirty(true);
						}
						else if (!event.getNewText().equals(data)) {
							((TextField)control).setText(event.getNewText());
							control.setDirty(true);
						}
						else {
							((TextField)control).setText(event.getNewText());
							((TextField)control).setCursorPosition((int)state);
						}
					}
				}
			}
		}
	}

	@Override
	public void failure(int id) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketControlAction;
	}
	
	@Override
	public int getVersion() {
		return 0;
	}

}
