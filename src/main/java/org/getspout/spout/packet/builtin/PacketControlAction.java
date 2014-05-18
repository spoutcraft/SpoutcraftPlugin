/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org//>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spout.packet.builtin;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.SliderDragEvent;
import org.getspout.spoutapi.event.screen.TextFieldChangeEvent;
import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.gui.CheckBox;
import org.getspout.spoutapi.gui.ListWidget;
import org.getspout.spoutapi.gui.Orientation;
import org.getspout.spoutapi.gui.RadioButton;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.Scrollable;
import org.getspout.spoutapi.gui.Slider;
import org.getspout.spoutapi.gui.TextField;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.packet.SpoutPacket;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketControlAction extends SpoutPacket {
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
	public void readData(SpoutInputStream input) throws IOException {
		long msb = input.readLong();
		long lsb = input.readLong();
		this.screen = new UUID(msb, lsb);
		msb = input.readLong();
		lsb = input.readLong();
		this.widget = new UUID(msb, lsb);
		this.state = input.readFloat();
		this.data = input.readString();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeLong(screen.getMostSignificantBits());
		output.writeLong(screen.getLeastSignificantBits());
		output.writeLong(widget.getMostSignificantBits());
		output.writeLong(widget.getLeastSignificantBits());
		output.writeFloat(state);
		output.writeString(data);
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
			if (player.getCurrentScreen() != null && player.getCurrentScreen().getId().equals(this.screen)) {
				screen = player.getCurrentScreen();
			}
			if (screen != null) {
				Widget control = screen.getWidget(widget);
				if (control != null) {
					if (control instanceof Button) {
						if (control instanceof CheckBox) {
							((CheckBox) control).setChecked(!((CheckBox) control).isChecked());
						}
						if (control instanceof RadioButton) {
							((RadioButton) control).setSelected(true);
						}
						ButtonClickEvent event = new ButtonClickEvent(player, screen, (Button) control);
						((Button) control).onButtonClick(event);
						Bukkit.getServer().getPluginManager().callEvent(event);
					} else if (control instanceof Slider) {
						SliderDragEvent event = new SliderDragEvent(player, screen, (Slider) control, state);
						((Slider) control).onSliderDrag(event);
						Bukkit.getServer().getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							((Slider) control).setSliderPosition(event.getOldPosition());
							control.setDirty(true);
						} else if (event.getNewPosition() != state) {
							((Slider) control).setSliderPosition(event.getNewPosition());
							control.setDirty(true);
						} else {
							((Slider) control).setSliderPosition(event.getNewPosition());
						}
					} else if (control instanceof TextField) {
						TextFieldChangeEvent event = new TextFieldChangeEvent(player, screen, (TextField) control, data);
						((TextField) control).onTextFieldChange(event);
						Bukkit.getServer().getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							((TextField) control).setText(event.getOldText());
							control.setDirty(true);
						} else if (!event.getNewText().equals(data)) {
							((TextField) control).setText(event.getNewText());
							control.setDirty(true);
						} else {
							((TextField) control).setText(event.getNewText());
							((TextField) control).setCursorPosition((int) state);
							control.setDirty(false);
						}
					} else if (control instanceof Scrollable) {
						if (data.equals("HORIZONTAL") || data.equals("VERTICAL")) {
							Orientation axis = Orientation.valueOf(data);
							Scrollable scroll = (Scrollable) control;
							scroll.setScrollPosition(axis, (int) state);
						} else if (control instanceof ListWidget) {
							ListWidget list = (ListWidget) control;
							boolean dblclick = false;
							if (data.equals("click") || data.equals("doubleclick") || data.equals("selected")) {
								int item = (int) state;
								if (data.equals("doubleclick")) {
									dblclick = true;
								}
								list.setSelection(item);
								list.onSelected(item, dblclick);
							}
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
		return 1;
	}
}
