package me.jonata.classes;

import jdk.internal.jline.internal.Nullable;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Util {

	public static ItemStack createItemStack(Material mat, int amount, String name, @Nullable ArrayList<String> lore) {

		ItemStack item = new ItemStack(mat, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		if(lore != null) meta.setLore(lore);

		item.setItemMeta(meta);
		return item;
	}

	public static void setupPlayer(Player p) {
		p.getInventory().clear();
		p.setGameMode(GameMode.SURVIVAL);
		p.setHealth(p.getHealthScale());
		p.setFoodLevel(20);
	}

}
