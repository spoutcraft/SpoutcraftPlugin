/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
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
package org.getspout.spoutapi.block.design;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BlockVector;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.packet.PacketUtil;

public class GenericBlockDesign implements BlockDesign {
	protected boolean reset = false;
	protected float lowXBound;
	protected float lowYBound;
	protected float lowZBound;
	protected float highXBound;
	protected float highYBound;
	protected float highZBound;
	protected String textureURL;
	protected String texturePlugin;
	protected Texture texture;
	protected float[][] xPos;
	protected float[][] yPos;
	protected float[][] zPos;
	protected float[][] textXPos;
	protected float[][] textYPos;
	protected int[] lightSourceXOffset;
	protected int[] lightSourceYOffset;
	protected int[] lightSourceZOffset;
	protected float maxBrightness = 1.0F;
	protected float minBrightness = 0F;
	protected float brightness = 0.5F;
	protected int renderPass = 0;

	public GenericBlockDesign() {
	}

	public GenericBlockDesign(float lowXBound, float lowYBound, float lowZBound, float highXBound, float highYBound, float highZBound, String textureURL, Plugin texturePlugin, float[][] xPos, float[][] yPos, float[][] zPos, float[][] textXPos, float[][] textYPos, int renderPass) {
		this.lowXBound = lowXBound;
		this.lowYBound = lowYBound;
		this.lowZBound = lowZBound;
		this.highXBound = highXBound;
		this.highYBound = highYBound;
		this.highZBound = highZBound;
		this.textureURL = textureURL;
		this.texturePlugin = texturePlugin.getDescription().getName();
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		this.textXPos = textXPos;
		this.textYPos = textYPos;
		this.renderPass = renderPass;
	}

	public BlockDesign setMaxBrightness(float maxBrightness) {
		this.maxBrightness = maxBrightness;
		return this;
	}

	public BlockDesign setMinBrightness(float minBrightness) {
		this.minBrightness = minBrightness;
		return this;
	}

	public BlockDesign setBrightness(float brightness) {
		this.brightness = brightness * maxBrightness + (1 - brightness) * minBrightness;
		return this;
	}

	public BlockDesign setRenderPass(int renderPass) {
		this.renderPass = renderPass;
		return this;
	}

	public int getRenderPass() {
		return renderPass;
	}

	public int getVersion() {
		return 3;
	}

	public void read(SpoutInputStream input) throws IOException {
		textureURL = input.readString();
		if (textureURL.equals(resetString)) {
			reset = true;
			return;
		}
		reset = false;
		texturePlugin = input.readString();
		xPos = PacketUtil.readDoubleArray(input);
		yPos = PacketUtil.readDoubleArray(input);
		zPos = PacketUtil.readDoubleArray(input);
		textXPos = PacketUtil.readDoubleArray(input);
		textYPos = PacketUtil.readDoubleArray(input);
		lowXBound = input.readFloat();
		lowYBound = input.readFloat();
		lowZBound = input.readFloat();
		highXBound = input.readFloat();
		highYBound = input.readFloat();
		highZBound = input.readFloat();
		maxBrightness = input.readFloat();
		minBrightness = input.readFloat();
		renderPass = input.readInt();
		lightSourceXOffset = PacketUtil.readIntArray(input);
		lightSourceYOffset = PacketUtil.readIntArray(input);
		lightSourceZOffset = PacketUtil.readIntArray(input);
	}

	private final static String resetString = "[reset]";

	public void writeReset(SpoutOutputStream output) throws IOException {
		output.writeString(resetString);
	}

	public void write(SpoutOutputStream output) throws IOException {
		if (reset) {
			output.writeString(resetString);
			return;
		}
		output.writeString(textureURL);
		output.writeString(texturePlugin);
		PacketUtil.writeDoubleArray(output, xPos);
		PacketUtil.writeDoubleArray(output, yPos);
		PacketUtil.writeDoubleArray(output, zPos);
		PacketUtil.writeDoubleArray(output, textXPos);
		PacketUtil.writeDoubleArray(output, textYPos);
		output.writeFloat(lowXBound);
		output.writeFloat(lowYBound);
		output.writeFloat(lowZBound);
		output.writeFloat(highXBound);
		output.writeFloat(highYBound);
		output.writeFloat(highZBound);
		output.writeFloat(maxBrightness);
		output.writeFloat(minBrightness);
		output.writeInt(renderPass);
		PacketUtil.writeIntArray(output, lightSourceXOffset);
		PacketUtil.writeIntArray(output, lightSourceYOffset);
		PacketUtil.writeIntArray(output, lightSourceZOffset);
	}
	
	public void write(DataOutputStream output) throws IOException {
		if (reset) {
			PacketUtil.writeString(output, resetString);
			return;
		}
		PacketUtil.writeString(output, textureURL);
		PacketUtil.writeString(output, texturePlugin);
		PacketUtil.writeDoubleArray(output, xPos);
		PacketUtil.writeDoubleArray(output, yPos);
		PacketUtil.writeDoubleArray(output, zPos);
		PacketUtil.writeDoubleArray(output, textXPos);
		PacketUtil.writeDoubleArray(output, textYPos);
		output.writeFloat(lowXBound);
		output.writeFloat(lowYBound);
		output.writeFloat(lowZBound);
		output.writeFloat(highXBound);
		output.writeFloat(highYBound);
		output.writeFloat(highZBound);
		output.writeFloat(maxBrightness);
		output.writeFloat(minBrightness);
		output.writeInt(renderPass);
		PacketUtil.writeIntArray(output, lightSourceXOffset);
		PacketUtil.writeIntArray(output, lightSourceYOffset);
		PacketUtil.writeIntArray(output, lightSourceZOffset);
	}
	
	public BlockDesign setTexture(Plugin plugin, String textureURL) {
		this.texturePlugin = plugin.getDescription().getName();
		this.textureURL = textureURL;
		return this;
	}

	public BlockDesign setBoundingBox(float lowX, float lowY, float lowZ, float highX, float highY, float highZ) {
		this.lowXBound = lowX;
		this.lowYBound = lowY;
		this.lowZBound = lowZ;
		this.highXBound = highX;
		this.highYBound = highY;
		this.highZBound = highZ;
		return this;
	}

	public BlockDesign setQuadNumber(int quads) {
		xPos = new float[quads][];
		yPos = new float[quads][];
		zPos = new float[quads][];
		textXPos = new float[quads][];
		textYPos = new float[quads][];
		lightSourceXOffset = new int[quads];
		lightSourceYOffset = new int[quads];
		lightSourceZOffset = new int[quads];

		for (int i = 0; i < quads; i++) {
			xPos[i] = new float[4];
			yPos[i] = new float[4];
			zPos[i] = new float[4];
			textXPos[i] = new float[4];
			textYPos[i] = new float[4];
			lightSourceXOffset[i] = 0;
			lightSourceYOffset[i] = 0;
			lightSourceZOffset[i] = 0;
		}
		return this;
	}

	public BlockDesign setQuad(int quadNumber, float x1, float y1, float z1, int tx1, int ty1, float x2, float y2, float z2, int tx2, int ty2, float x3, float y3, float z3, int tx3, int ty3, float x4, float y4, float z4, int tx4, int ty4, int textureSizeX, int textureSizeY) {

		setVertex(quadNumber, 0, x1, y1, z1, tx1, ty1, textureSizeX, textureSizeY);
		setVertex(quadNumber, 1, x2, y2, z2, tx2, ty2, textureSizeX, textureSizeY);
		setVertex(quadNumber, 2, x3, y3, z3, tx3, ty3, textureSizeX, textureSizeY);
		setVertex(quadNumber, 3, x4, y4, z4, tx4, ty4, textureSizeX, textureSizeY);
		return this;
	}

	public BlockDesign setVertex(int quadNumber, int vertexNumber, float x, float y, float z, int tx, int ty, int textureSizeX, int textureSizeY) {
		float u = (float) tx / (float) textureSizeX;
		float v = (float) ty / (float) textureSizeY;
		return setVertex(quadNumber, vertexNumber, x, y, z, u, v);

	}
	public BlockDesign setVertex(int quadNumber, int vertexNumber, float x, float y, float z, float u, float v) {
		xPos[quadNumber][vertexNumber] = x;
		yPos[quadNumber][vertexNumber] = y;
		zPos[quadNumber][vertexNumber] = z;
		textXPos[quadNumber][vertexNumber] = u;
		textYPos[quadNumber][vertexNumber] = v;
		return this;
	}

	public String getTexureURL() {
		return textureURL;
	}

	public String getTexturePlugin() {
		return texturePlugin;
	}

	public boolean getReset() {
		return reset;
	}

	public BlockDesign setLightSource(int quad, int x, int y, int z) {
		lightSourceXOffset[quad] = x;
		lightSourceYOffset[quad] = y;
		lightSourceZOffset[quad] = z;
		return this;
	}

	public BlockVector getLightSource(int quad, int x, int y, int z) {
		BlockVector blockVector = new BlockVector(x + lightSourceXOffset[quad], y + lightSourceYOffset[quad], z + lightSourceZOffset[quad]);
		return blockVector;
	}

	@Override
	public BlockDesign setTexture(Plugin plugin, Texture texture) {
		this.texture = texture;
		return setTexture(plugin, texture.getTexture());
	}

	@Override
	public Texture getTexture() {
		return texture;
	}

	@Override
	public BlockDesign setQuad(Quad quad) {
		return setVertex(quad.getVertex(0)).setVertex(quad.getVertex(1)).setVertex(quad.getVertex(2)).setVertex(quad.getVertex(3));
	}

	@Override
	public BlockDesign setVertex(Vertex vertex) {
		return setVertex(vertex.getQuadNum(), vertex.getIndex(), vertex.getX(), vertex.getY(), vertex.getZ(), vertex.getTextureX(), vertex.getTextureY(), vertex.getTextureWidth(), vertex.getTextureHeight());
	}
	
	@Override
	public BlockDesign rotate(int degrees) {
		return rotate(degrees, Axis.Y);
	}
	
	@Override
	public BlockDesign rotate(int degrees, Axis axis) {
		// Store angle to save some cpu calculations.
		double angle = Math.toRadians(degrees);
		
		// Rotation matrices to use for rotation calculation.
		float[][] rotmatrixX = {
			{ 1f, 0f,                       0f                      },
			{ 0f, (float) Math.cos(angle), (float) -Math.sin(angle) },
			{ 0f, (float) Math.sin(angle), (float) Math.cos(angle)  }
		};
		
		float[][] rotmatrixY = {
			{ (float) Math.cos(angle),  0f, (float) Math.sin(angle) },
			{ 0f,                       1f, 0f                      },
			{ (float) -Math.sin(angle), 0f, (float) Math.cos(angle) }
		};
		
		float[][] rotmatrixZ = {
			{ (float) Math.cos(angle), (float) -Math.sin(angle), 0f },
			{ (float) Math.sin(angle), (float) Math.cos(angle),  0f },
			{ 0f,                      0f,                       1f }
		};
		
		// Store matrices for easy code acces.
		HashMap<Axis, float[][]> rotmatrix = new HashMap<Axis, float[][]>();
		rotmatrix.put(Axis.X, rotmatrixX);
		rotmatrix.put(Axis.Y, rotmatrixY);
		rotmatrix.put(Axis.Z, rotmatrixZ);

		// Create new vertices arrays.
		float[][] xx = new float[xPos.length][xPos[0].length];
		float[][] yy = new float[yPos.length][yPos[0].length];
		float[][] zz = new float[zPos.length][zPos[0].length];
		
		// Iterate over all vertices.
		for (int i = 0; i < xx.length; i++) {
			for (int j = 0; j < 4; j++) {
				// Shift 0.5 to center around origin.
				float x1 = xPos[i][j] - 0.5f;
				float y1 = yPos[i][j] - 0.5f;
				float z1 = zPos[i][j] - 0.5f;
				
				// Calculate rotated vertices coords.
				float x2 = (x1*rotmatrix.get(axis)[0][0]) + (y1*rotmatrix.get(axis)[0][1]) + (z1*rotmatrix.get(axis)[0][2]);
				float y2 = (x1*rotmatrix.get(axis)[1][0]) + (y1*rotmatrix.get(axis)[1][1]) + (z1*rotmatrix.get(axis)[1][2]);
				float z2 = (x1*rotmatrix.get(axis)[2][0]) + (y1*rotmatrix.get(axis)[2][1]) + (z1*rotmatrix.get(axis)[2][2]);
				
				// Shift 0.5 to move block back to correct position.
				xx[i][j] = x2 + 0.5f;
				yy[i][j] = y2 + 0.5f;
				zz[i][j] = z2 + 0.5f;
			}
		}

		// Create new BlockDesign and apply auto light calculations.
		GenericBlockDesign des = new GenericBlockDesign(lowXBound, lowYBound, lowZBound, highXBound, highYBound, highZBound, textureURL, Bukkit.getPluginManager().getPlugin(texturePlugin), xx, yy, zz, textXPos, textYPos, renderPass);
		des.calculateLightSources();
		return des;
	}
	
	public GenericBlockDesign calculateLightSources() {
		lightSourceXOffset = new int[xPos.length];
		lightSourceYOffset = new int[xPos.length];
		lightSourceZOffset = new int[xPos.length];
		for (int quad = 0; quad < xPos.length; quad++) {
			BlockVector normal = new BlockVector();

			normal.setX(((yPos[quad][0] - yPos[quad][1]) * (zPos[quad][1] - zPos[quad][2])) - ((zPos[quad][0] - zPos[quad][1]) * (yPos[quad][1] - yPos[quad][2])));
			normal.setY(((zPos[quad][0] - zPos[quad][1]) * (xPos[quad][1] - xPos[quad][2])) - ((xPos[quad][0] - xPos[quad][1]) * (zPos[quad][1] - zPos[quad][2])));
			normal.setZ(((xPos[quad][0] - xPos[quad][1]) * (yPos[quad][1] - yPos[quad][2])) - ((yPos[quad][0] - yPos[quad][1]) * (xPos[quad][1] - xPos[quad][2])));

			Double length = Math.sqrt((normal.getX() * normal.getX()) + (normal.getY() * normal.getY()) + (normal.getZ() * normal.getZ()));

			this.setLightSource(quad, (int) Math.round(normal.getX() / length), (int) Math.round(normal.getY() / length), (int) Math.round(normal.getZ() / length));
		}
		
		return this;
	}
}
