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

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.packet.SpoutPacket;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PacketSendLink extends SpoutPacket {
    protected URL link;

    public PacketSendLink() {
        link = null;
    }

    public PacketSendLink(URL link) {
        this.link = link;
    }

    public PacketSendLink(String link) throws MalformedURLException {
        this.link = new URL(link);
    }

    @Override
    public void readData(SpoutInputStream input) throws IOException {
        throw new IOException("The server cannot receive a link from the client!");
    }

    @Override
    public void writeData(SpoutOutputStream output) throws IOException {
        if (link == null) {
            throw new IOException("Attempt made to serialize " + this + " with a null URL link reference!");
        }
        output.writeString(link.toString());
    }

    @Override
    public void run(int playerId) {
    }

    @Override
    public void failure(int playerId) {
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.PacketSendLink;
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public String toString() {
        return "PacketSendLink{ version= " + getVersion() + ", link= " + (link == null ? "null" : link.toString()) + " }";
    }
}
