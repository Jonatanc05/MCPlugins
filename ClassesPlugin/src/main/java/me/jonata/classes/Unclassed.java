package me.jonata.classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Unclassed implements Listener {

	@EventHandler
	public void onPlayerConnect(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(Main.playersClasses.containsKey(p.getName())) return;
		p.sendMessage(ChatColor.GREEN + "Bem Vindo! " + ChatColor.WHITE + "Selecione uma classe para começar a jogar");
		p.getInventory().addItem(Util.createItemStack(Material.BOOK, 1, "Selecionar classe", null));
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getItem() == null) return;
		if (e.getItem().getType() != Material.BOOK || Main.playersClasses.containsKey(p.getName())) return;
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Main.gui.show(p);
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerBreak(BlockBreakEvent e) {
		if(!Main.playersClasses.containsKey(e.getPlayer().getName()))
			e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerPlace(BlockPlaceEvent e) {
		if(!Main.playersClasses.containsKey(e.getPlayer().getName()))
			e.setCancelled(true);
	}

}
