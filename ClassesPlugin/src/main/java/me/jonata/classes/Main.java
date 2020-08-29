package me.jonata.classes;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public final class Main extends JavaPlugin {

	public static final String filePath = "." + File.separatorChar + "plugins" + File.separatorChar + "players-classes.ser";

	public static HashMap<String, PlayableClass> playersClasses;
	public static ArrayList<IClass> classes;
	public static Unclassed unclassed;
	public static ClassSelectionGUI gui;

	@Override
	public void onEnable() {
		// Obtendo ou criando arquivo com classes dos players
		try {
			if ((new File(filePath)).createNewFile()) {
				playersClasses = new HashMap<>();
			} else {
				FileInputStream fin = new FileInputStream(filePath);
				ObjectInputStream ois = new ObjectInputStream(fin);
				playersClasses = (HashMap<String, PlayableClass>) ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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

	@Override
	public void onDisable(){
		try {
			FileOutputStream fout = new FileOutputStream(filePath);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(playersClasses);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
