package org.getspout.spoutapi.packet;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PacketSendLink implements SpoutPacket {
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
        return 0;
    }

    @Override
    public String toString() {
        return "PacketSendLink{ version= " + getVersion() + ", link= " + (link == null ? "null" : link.toString()) + " }";
    }
}
