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
package org.getspout.spoutapi.gui;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractListModel {
	private HashSet<ListView> views = new HashSet<ListView>();

	public abstract ListWidgetItem getItem(int row);

	public abstract int getSize();

	public abstract void onSelected(int item, boolean doubleClick);

	public void addView(ListView view) {
		views.add(view);
	}

	public void removeView(ListView view) {
		views.remove(view);
	}

	public void sizeChanged() {
		for (ListView view : views) {
			view.sizeChanged();
		}
	}

	public Set<ListView> getViews() {
		return Collections.unmodifiableSet(views);
	}
}
