/*
 * This file is part of SpoutPlugin (http://www.spout.org/).
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
package org.getspout.spout.util;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockMonitor extends Thread {
	@Override
	public void run() {
		boolean dead = false;
		while (!dead && !interrupted()) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				dead = true;
			}
			ThreadMXBean tmx = ManagementFactory.getThreadMXBean();
			long[] ids = tmx.findDeadlockedThreads();
			if (ids != null) {
				ThreadInfo[] infos = tmx.getThreadInfo(ids, true, true);
				System.out.println("The following threads are deadlocked:");
				for (ThreadInfo ti : infos) {
					System.out.println(ti);
				}
			}
		}
	}
}
