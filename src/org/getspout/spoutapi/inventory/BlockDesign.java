package org.getspout.spoutapi.inventory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.plugin.Plugin;
import org.bukkit.util.BlockVector;

public interface BlockDesign {

	public void setMaxBrightness(float maxBrightness);
	
	public void setMinBrightness(float minBrightness);
	
	public void setBrightness(float brightness);
	
	public void setRenderPass(int renderPass);
	
	public int getRenderPass();
	
	public int getNumBytes();
	
	public int getVersion();
	
	public void read(DataInputStream input) throws IOException;
	
	public void writeReset(DataOutputStream output);
	
	public int getResetNumBytes();
	
	public void write(DataOutputStream output) throws IOException;
	
	public void setTexture(Plugin plugin, String textureURL);
	
	public void setBoundingBox(float lowX, float lowY, float lowZ, float highX, float highY, float highZ);
	
	public void setQuadNumber(int quads);
	
	public void setQuad(int quadNumber,
			float x1, float y1, float z1, int tx1, int ty1,
			float x2, float y2, float z2, int tx2, int ty2,
			float x3, float y3, float z3, int tx3, int ty3,
			float x4, float y4, float z4, int tx4, int ty4,
			int textureSizeX, int textureSizeY);
	
	public void setVertex(int quadNumber, int vertexNumber, float x, float y, float z, int tx, int ty, int textureSizeX, int textureSizeY);
	
	public String getTexureURL();
	
	public String getTexturePlugin();
	
	public boolean getReset();
	
	public void setLightSource(int quad, int x, int y, int z);
	
	public BlockVector getLightSource(int quad, int x, int y, int z);
}
