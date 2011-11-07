package org.getspout.spoutapi.packet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.screen.ScreenshotReceivedEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


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

    public void readData(DataInputStream input) throws IOException {
        isRequest = input.readBoolean();
        if (!isRequest) {
            int ssLen = input.readInt();
            ssAsPng = new byte[ssLen];
            input.readFully(ssAsPng);
        }
    }

    public void writeData(DataOutputStream output) throws IOException {
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
            sp.sendNotification("Screenshot received", "Screenshot received by server", Material.PAINTING);
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
