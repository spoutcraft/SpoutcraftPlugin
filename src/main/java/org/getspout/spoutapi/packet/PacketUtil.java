/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
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

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

public abstract class PacketUtil {
	public static int[] readIntArray(SpoutInputStream input) throws IOException {
		int length = input.readInt();
		if (length > 256) {
			throw new IllegalArgumentException("Int array exceeded max length (" + length + ")");
		}
		int[] newArray = new int[length];
		for (int i = 0; i < length; i++) {
			newArray[i] = input.readInt();
		}
		return newArray;
	}

	public static float[] readQuadFloat(SpoutInputStream input) throws IOException {
		float[] newArray = new float[4];
		for (int i = 0; i < 4; i++) {
			newArray[i] = input.readFloat();
		}
		return newArray;
	}

	public static int getDoubleArrayLength(float[][] doubleArray) {
		return doubleArray.length * 16;
	}

	public static float[][] readDoubleArray(SpoutInputStream input) throws IOException {
		int length = input.readShort();
		if (length > 256) {
			throw new IllegalArgumentException("Double array exceeded max length (" + length + ")");
		}
		float[][] newDoubleArray = new float[length][];
		for (int i = 0; i < length; i++) {
			newDoubleArray[i] = readQuadFloat(input);
		}
		return newDoubleArray;
	}

	public static void writeIntArray(SpoutOutputStream output, int[] ints) throws IOException {
		if (ints.length > 256) {
			throw new IllegalArgumentException("Array containing " + ints.length + " ints passed to writeQuadFloat");
		}
		output.writeInt(ints.length);
		for (int i = 0; i < ints.length; i++) {
			output.writeInt(ints[i]);
		}
	}
	
	public static void writeIntArray(DataOutputStream output, int[] ints) throws IOException {
		if (ints.length > 256) {
			throw new IllegalArgumentException("Array containing " + ints.length + " ints passed to writeQuadFloat");
		}
		output.writeInt(ints.length);
		for (int i = 0; i < ints.length; i++) {
			output.writeInt(ints[i]);
		}
	}

	public static void writeQuadFloat(SpoutOutputStream output, float[] floats) throws IOException {
		if (floats.length != 4) {
			throw new IllegalArgumentException("Array containing " + floats.length + " floats passed to writeQuadFloat");
		}
		for (int i = 0; i < 4; i++) {
			output.writeFloat(floats[i]);
		}
	}
	
	public static void writeQuadFloat(DataOutputStream output, float[] floats) throws IOException {
		if (floats.length != 4) {
			throw new IllegalArgumentException("Array containing " + floats.length + " floats passed to writeQuadFloat");
		}
		for (int i = 0; i < 4; i++) {
			output.writeFloat(floats[i]);
		}
	}
	
	public static void writeDoubleArray(SpoutOutputStream output, float[][] floats) throws IOException {
		if (floats.length > 256) {
			throw new IllegalArgumentException("Double array exceeded max length (" + floats.length + ")");
		}

		output.writeShort((short) floats.length);
		for (int i = 0; i < floats.length; i++) {
			writeQuadFloat(output, floats[i]);
		}
	}
	
	public static void writeDoubleArray(DataOutputStream output, float[][] floats) throws IOException {
		if (floats.length > 256) {
			throw new IllegalArgumentException("Double array exceeded max length (" + floats.length + ")");
		}

		output.writeShort((short) floats.length);
		for (int i = 0; i < floats.length; i++) {
			writeQuadFloat(output, floats[i]);
		}
	}
	
	public static void writeString(DataOutputStream output, String string) throws IOException {
		byte[] data = string.getBytes("UTF-8");
		output.writeInt(data.length);
		output.write(data);
	}
	
	public static String readString(DataInputStream input) throws IOException {
		int length= input.readInt();
		byte[] data=new byte[length];
		input.readFully(data);
		String string = new String(data,"UTF-8");
		
		return string;
	}
}
