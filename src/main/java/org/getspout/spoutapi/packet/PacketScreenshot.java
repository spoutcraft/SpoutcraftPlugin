/*
 * This file is part of SpoutPluginAPI (http://www.spout.org/).
 *
 * SpoutPluginAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPluginAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.packet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.screen.ScreenshotReceivedEvent;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketScreenshot implements SpoutPacket {
	byte[] ssAsPng = null;
	boolean isRequest = false;

	public PacketScreenshot() {
		isRequest = true;
	}

	public PacketScreenshot(BufferedImage ss) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(ss, "png", baos);
		baos.flush();
		ssAsPng = baos.toByteArray();
		baos.close();
	}

	public int getNumBytes() {
		if (ssAsPng == null) {
			return 1;
		}
		return ssAsPng.length + 5;
	}

	public void readData(SpoutInputStream input) throws IOException {
		isRequest = input.readBoolean();
		if (!isRequest) {
			int ssLen = input.readInt();
			ssAsPng = new byte[ssLen];
			input.read(ssAsPng);
		}
	}

	public void writeData(SpoutOutputStream output) throws IOException {
		if (ssAsPng == null) {
			output.writeBoolean(true);
		} else {
			output.writeBoolean(false);
			output.writeInt(ssAsPng.length);
			output.write(ssAsPng);
		}
	}

	public void run(int playerId) {
		if (isRequest) {
			return; // we can't do anything!
		}
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(ssAsPng);
			BufferedImage ss = ImageIO.read(bais);
			SpoutPlayer sp = SpoutManager.getPlayerFromId(playerId);
			ScreenshotReceivedEvent sre = new ScreenshotReceivedEvent(sp, ss);
			Bukkit.getServer().getPluginManager().callEvent(sre);
			sp.sendNotification("Sending screenshot...", "Screenshot received", Material.PAINTING);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void failure(int playerId) {
	}

	public PacketType getPacketType() {
		return PacketType.PacketScreenshot;
	}

	public int getVersion() {
		return 1;
	}
}
