package me.jonata.classes;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public final class Main extends JavaPlugin {

	public static HashMap<String, PlayableClass> playersClasses;
	public static ArrayList<IClass> classes;
	public static Unclassed unclassed;
	public static ClassSelectionGUI gui;

	@Override
	public void onEnable() {
		playersClasses = new HashMap<>();
		classes = new ArrayList<>();
		classes.add(new SpeedrunnerClass());
		PluginManager pm = getServer().getPluginManager();
		for (IClass classObject : classes) {
			pm.registerEvents(classObject, this);
		}

		unclassed = new Unclassed();
		pm.registerEvents(unclassed, this);

		gui = new ClassSelectionGUI();
		pm.registerEvents(gui, this);
	}

}
