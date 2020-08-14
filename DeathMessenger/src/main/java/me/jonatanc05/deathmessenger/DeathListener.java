package me.jonatanc05.deathmessenger;

import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

	public void OnPlayerDie(PlayerDeathEvent e) {

		Location pos = e.getEntity().getLocation();
		String msg = String.format("%s [%d %d %d]", e.getDeathMessage(), pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());

		e.setDeathMessage(msg);

	}

}
