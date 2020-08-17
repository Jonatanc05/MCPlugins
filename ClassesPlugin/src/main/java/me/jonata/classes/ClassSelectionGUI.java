package me.jonata.classes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClassSelectionGUI implements Listener {

	private Inventory inv;
	private String invName = ChatColor.BLACK + "" + ChatColor.BOLD + "Selecione sua classe";
	private int slotRows = 1;

	public ClassSelectionGUI() {
		inv = Bukkit.createInventory(null, slotRows * 9, invName);
		for(IClass c : Main.classes) {
			ItemStack item = Util.createItemStack(c.getMaterial(), 1, c.getName(), c.getDescription());
			inv.addItem(item);
		}
	}

	public void show(Player p) {
		p.openInventory(inv);
	}

	@EventHandler
	public void onPlayerClicked(InventoryClickEvent e) {
		ItemStack item = e.getCurrentItem();
		try {
			for (IClass c : Main.classes) {
				if (item.getItemMeta().getDisplayName().equals(c.getName())) {
					e.setCancelled(true);
					e.getView().close();
					c.add((Player) e.getWhoClicked());
				}
			}
		} catch (Exception exception) { return; }
	}

}
