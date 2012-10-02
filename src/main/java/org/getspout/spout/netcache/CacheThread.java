/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
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
package org.getspout.spout.netcache;

import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.Packet;
import net.minecraft.server.Packet51MapChunk;

import org.getspout.spout.SpoutNetServerHandler;

public class CacheThread extends Thread {
	private static final CacheThread instance;

	private static LinkedBlockingQueue<QueueEntry> queue = new  LinkedBlockingQueue<QueueEntry>();

	static {
		instance = new CacheThread();
	}

	private CacheThread() {
		super("SpoutPlugin Cache Thread");
		setDaemon(true);
		start();
	}

	public static CacheThread getInstance() {
		return instance;
	}

	public void run() {
		while (!isInterrupted()) {
			try {
				QueueEntry entry = queue.take();

				if (entry.getPacket() instanceof Packet51MapChunk) {
					Packet51MapChunk p = (Packet51MapChunk) entry.getPacket();
					p.inflatedBuffer = entry.getChunkNetCache().handle(p.inflatedBuffer);
				}
				entry.getNSH().queueOutputPacket(entry.getPacket());
			} catch (InterruptedException ie) {
				ie.printStackTrace();
				break;
			}
		}
	}

	public static void sendPacket(SpoutNetServerHandler nsh, Packet packet, ChunkNetCache chunkNetCache) {
		queue.add(new QueueEntry(nsh, packet, chunkNetCache));
	}

	private static class QueueEntry {
		private final SpoutNetServerHandler nsh;
		private final Packet packet;
		private final ChunkNetCache chunkNetCache;

		public QueueEntry(SpoutNetServerHandler nsh, Packet packet, ChunkNetCache chunkNetCache) {
			this.nsh = nsh;
			this.packet = packet;
			this.chunkNetCache = chunkNetCache;
		}

		public SpoutNetServerHandler getNSH() {
			return nsh;
		}

		public Packet getPacket() {
			return packet;
		}

		public ChunkNetCache getChunkNetCache() {
			return chunkNetCache;
		}
	}
}
