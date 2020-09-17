package me.jonata.mobacraft;

import jdk.internal.jline.internal.Nullable;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemBuilder {

	public static ItemStack getItemStack(Material mat, int amount, String name, @Nullable ArrayList<String> lore) {

		ItemStack item = new ItemStack(mat, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		if(lore != null) meta.setLore(lore);

		item.setItemMeta(meta);
		return item;
	}

}
