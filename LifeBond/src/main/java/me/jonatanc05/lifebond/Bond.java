package me.jonatanc05.lifebond;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;

public class Bond implements Listener {
	protected String name;
	protected ArrayList<Player> players;

	public Bond(String name, Player p) {
		this.name = name;
		players = new ArrayList<Player>();
		players.add(p);
	}

	public ArrayList<Player> getPlayers() { return players; }

	public boolean contains(Player p) { return players.contains(p); }

	public void addPlayer(Player p) { players.add(p); }

	public void removePlayer(Player p) {
		players.remove(p);
		if (players.isEmpty()) {
			Main.bonds.remove(this);
			HandlerList.unregisterAll(this);
		}
	}

	public String getName() { return name; }

	@EventHandler
	public void onPlayerTakeDamage(EntityDamageEvent e) {
		Entity damaged = e.getEntity();
		if (!(damaged instanceof Player) || !players.contains(damaged) || e.getCause() == EntityDamageEvent.DamageCause.CUSTOM) {
			return;
		}
		double dmg = e.getDamage();
		for (Player player : players) {
			if(player == damaged) continue;
			player.damage(dmg);
			player.sendMessage(ChatColor.DARK_RED + ((Player) damaged).getDisplayName() + " sofreu dano!");
		}
	}

}
