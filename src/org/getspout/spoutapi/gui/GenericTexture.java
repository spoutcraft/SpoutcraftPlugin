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
package org.getspout.spoutapi.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.getspout.spoutapi.packet.PacketUtil;

public class GenericTexture extends GenericWidget implements Texture {

	protected String url = null;
	protected boolean drawAlpha = false;
	protected WidgetAnchor align = WidgetAnchor.SCALE;
	protected int top;
	protected int left;

	public GenericTexture() {
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 2;
	}

	public GenericTexture(String url) {
		this.url = url;
	}

	@Override
	public WidgetType getType() {
		return WidgetType.Texture;
	}

	@Override
	public int getNumBytes() {
		return super.getNumBytes() + PacketUtil.getNumBytes(getUrl()) + 6;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		this.setUrl(PacketUtil.readString(input));
		this.setDrawAlphaChannel(input.readBoolean());
		setAlign(WidgetAnchor.getAnchorFromId(input.readByte()));
		setTop(input.readShort());
		setLeft(input.readShort());
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		PacketUtil.writeString(output, getUrl());
		output.writeBoolean(isDrawingAlphaChannel());
		output.writeByte(align.getId());
		output.writeShort(top);
		output.writeShort(left);
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public Texture setUrl(String Url) {
		if ((getUrl() != null && !getUrl().equals(Url)) || (getUrl() == null && Url != null)) {
			this.url = Url;
			autoDirty();
		}
		return this;
	}

	@Override
	public Texture copy() {
		return ((Texture) super.copy()).setUrl(getUrl()).setDrawAlphaChannel(isDrawingAlphaChannel());
	}

	@Override
	public boolean isDrawingAlphaChannel() {
		return drawAlpha;
	}

	@Override
	public Texture setDrawAlphaChannel(boolean draw) {
		if (isDrawingAlphaChannel() != draw) {
			drawAlpha = draw;
			autoDirty();
		}
		return this;
	}

	@Override
	public Texture setAlign(WidgetAnchor align) {
		if (align != null && getAlign() != align) {
			this.align = align;
			autoDirty();
		}
		return this;
	}

	@Override
	public WidgetAnchor getAlign() {
		return align;
	}

	@Override
	public Texture setTop(int top) {
		if (getTop() != top) {
			this.top = top;
			autoDirty();
		}
		return this;
	}

	@Override
	public int getTop() {
		return top;
	}

	@Override
	public Texture setLeft(int left) {
		if (getLeft() != left) {
			this.left = left;
			autoDirty();
		}
		return this;
	}

	@Override
	public int getLeft() {
		return left;
	}
}
