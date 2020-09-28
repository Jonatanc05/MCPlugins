package me.jonata.mobacraft.classes;

import me.jonata.mobacraft.ItemBuilder;
import me.jonata.mobacraft.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Unclassed implements Listener {

	@EventHandler
	public void onPlayerConnect(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(Main.getPlayerClass(p) != ClassID.Unclassed) return;

		p.sendMessage(ChatColor.GREEN + "Bem Vindo! " + ChatColor.WHITE
				+ "Selecione uma classe para começar a jogar");

		p.getInventory().addItem(
			ItemBuilder.getItemStack(
				Material.BOOK,
				1,
				"Selecionar classe",
				null
			)
		);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (
				e.getItem() != null &&
				e.getItem().getType() == Material.BOOK &&
				Main.getPlayerClass(p) == ClassID.Unclassed &&
				(e.getAction() == Action.RIGHT_CLICK_AIR ||
				e.getAction() == Action.RIGHT_CLICK_BLOCK)
			) {
			Main.gui.show(p);
		}
	}

	@EventHandler
	public void onPlayerBreak(BlockBreakEvent e) {
		if(Main.getPlayerClass(e.getPlayer()) == ClassID.Unclassed) {
			e.setCancelled(true);
			alert(e.getPlayer());
		}
	}

	@EventHandler
	public void onPlayerPlace(BlockPlaceEvent e) {
		if(Main.getPlayerClass(e.getPlayer()) == ClassID.Unclassed) {
			e.setCancelled(true);
			alert(e.getPlayer());
		}
	}

	private void alert(Player p) {
		p.sendMessage(ChatColor.RED + "Selecione uma classe para começar a jogar");
	}

}
