package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class PacketUtil {
	public static final int maxString = 32767;
	
	public static void writeString(DataOutputStream output, String s) {
		try {
			output.writeShort(s.length());
			output.writeChars(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readString(DataInputStream input) {
		return readString(input, maxString);
	}
	
	public static int getNumBytes(String str) {
		return 2 + str.length() * 2;
	}
	
	public static String readString(DataInputStream input, int maxSize) {
		try {
			short size = input.readShort();

	        if (size > maxSize) {
	            throw new IOException("Received string length longer than maximum allowed (" + size + " > " + maxSize + ")");
	        } else if (size < 0) {
	            throw new IOException("Received string length is less than zero! Weird string!");
	        } else {
	            StringBuilder stringbuilder = new StringBuilder();

	            for (int j = 0; j < size; ++j) {
	                stringbuilder.append(input.readChar());
	            }

	            return stringbuilder.toString();
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
