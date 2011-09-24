package org.getspout.spoutapi.keyboard;

import org.bukkit.plugin.Plugin;

public class KeyBinding {
	private String id;
	private Keyboard defaultKey;
	private String description;
	private Plugin plugin;
	private BindingExecutionDelegate delegate;
	
	public KeyBinding(String id, Keyboard defaultkey, String description, Plugin plugin, BindingExecutionDelegate delegate)
	{
		this.id = id;
		this.defaultKey = defaultkey;
		this.description = description;
		this.plugin = plugin;
		this.delegate = delegate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Keyboard getDefaultKey() {
		return defaultKey;
	}

	public void setDefaultKey(Keyboard defaultKey) {
		this.defaultKey = defaultKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Plugin getPlugin() {
		return plugin;
	}

	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}

	public BindingExecutionDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(BindingExecutionDelegate delegate) {
		this.delegate = delegate;
	}
}
