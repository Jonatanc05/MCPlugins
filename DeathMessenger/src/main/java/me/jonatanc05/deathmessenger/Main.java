package me.jonatanc05.deathmessenger;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new DeathListener(), this);
	}

}
