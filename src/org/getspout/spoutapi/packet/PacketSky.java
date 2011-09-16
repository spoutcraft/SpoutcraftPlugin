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

import org.getspout.spoutapi.gui.Color;

public class PacketSky implements SpoutPacket{
	private int cloudY = 0, stars = 0, sunPercent = 0, moonPercent = 0;
	private Color skyColor = Color.ignore(), fogColor = Color.ignore(), cloudColor = Color.ignore();
	String sun = "";
	String moon = "";
	public PacketSky() {
		
	}
	
	public PacketSky(int cloudY, int stars, int sunPercent, int moonPercent) {
		this.cloudY = cloudY;
		this.stars = stars;
		this.sunPercent = sunPercent;
		this.moonPercent = moonPercent;
	}
	
	public PacketSky(String sunUrl, String moonUrl) {
		this.cloudY = 0;
		this.stars = 0;
		this.sunPercent = 0;
		this.moonPercent = 0;
		this.sun = sunUrl;
		this.moon = moonUrl;
	}
	
	public PacketSky(Color sky, Color fog, Color cloud){
		if(sky!=null)
			skyColor = sky.clone();
		if(fog!=null)
			fogColor = fog.clone();
		if(cloud!=null)
			cloudColor = cloud.clone();
	}
	
	public PacketSky(int cloudY, int stars, int sunPercent, int moonPercent, Color sky, Color fog, Color cloud, String sunUrl, String moonUrl) {
		this.cloudY = cloudY;
		this.stars = stars;
		this.sunPercent = sunPercent;
		this.moonPercent = moonPercent;
		this.sun = sunUrl;
		this.moon = moonUrl;
		if(sky!=null)
			skyColor = sky.clone();
		if(fog!=null)
			fogColor = fog.clone();
		if(cloud!=null)
			cloudColor = cloud.clone();
	}

	@Override
	public int getNumBytes() {
		return 31 + PacketUtil.getNumBytes(sun) + PacketUtil.getNumBytes(moon);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		cloudY = input.readInt();
		stars = input.readInt();
		sunPercent = input.readInt();
		moonPercent = input.readInt();
		sun = PacketUtil.readString(input, 256);
		moon = PacketUtil.readString(input, 256);
		skyColor = PacketUtil.readColor(input);
		fogColor = PacketUtil.readColor(input);
		cloudColor = PacketUtil.readColor(input);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(cloudY);
		output.writeInt(stars);
		output.writeInt(sunPercent);
		output.writeInt(moonPercent);
		PacketUtil.writeString(output, sun);
		PacketUtil.writeString(output, moon);
		PacketUtil.writeColor(output, skyColor);
		PacketUtil.writeColor(output, fogColor);
		PacketUtil.writeColor(output, cloudColor);
	}

	@Override
	public void run(int PlayerId) {
		
	}

	@Override
	public void failure(int id) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketSky;
	}
	
	@Override
	public int getVersion() {
		return 1;
	}

}
