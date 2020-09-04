package me.jonata.classes;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class SpeedrunnerClass implements IClass {

	public static final double dmgMultiplier = 1.3;
	public static final int radius = 5000;

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		// Removendo casos que não interessam
		if (e.getItem() == null) return;
		if (e.getItem().getType() != Material.COMPASS || Main.playersClasses.get(p.getName()) != PlayableClass.SpeedRunner) return;
		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

		World w = p.getWorld();
		StructureType st = null;
		switch (w.getEnvironment()) {
			case NETHER: st = StructureType.NETHER_FORTRESS; break;
			case NORMAL: st = StructureType.STRONGHOLD; break;
			case THE_END: st = StructureType.END_CITY; break;
		}
		Location l = w.locateNearestStructure(p.getLocation(), st, radius, false);
		try {
			if (w.getEnvironment() != World.Environment.NORMAL) {
				l.getBlock().setType(Material.LODESTONE);
				CompassMeta meta = (CompassMeta) e.getItem().getItemMeta();
				meta.setLodestone(l);
				meta.setLodestoneTracked(true);
				e.getItem().setItemMeta(meta);
				e.setCancelled(true);
			} else {
				p.setCompassTarget(l);
			}
			p.sendMessage(ChatColor.GOLD + "A bússola está apontando para a " + ChatColor.BOLD + st.getName().toUpperCase());
		} catch(Exception exception) {
			p.sendMessage(ChatColor.RED + "Você está incrivelmente longe da " + st.getName() + " mais próxima kkkkkkk achei q isso nunca fosse acontecer pq é tipo " + radius + " blocos");
		}

	}

	@EventHandler
	public void onPlayerTakeDamage(EntityDamageEvent e) {
		if(!(e.getEntity() instanceof Player) || Main.playersClasses.get(((Player) e.getEntity()).getName()) != PlayableClass.SpeedRunner) return;
		if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
			e.setDamage(e.getDamage() * dmgMultiplier);
	}

	@EventHandler
	public void onPlayerConnect(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(Main.playersClasses.get(p.getName()) != PlayableClass.SpeedRunner) return;
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
	}

	@Override
	public String getName() {
		return "SpeedRunner";
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> lore = new ArrayList<>();
		lore.add(ChatColor.GREEN + "-> Habilidade de fazer bússolas apontarem para a");
		lore.add(ChatColor.GREEN + "fortaleza mais próxima (no nether) ou para a");
		lore.add(ChatColor.GREEN + "stronghold mais próxima (no overworld)");
		lore.add(ChatColor.GREEN + "-> Possui efeito de velocidade I constante");
		lore.add(ChatColor.RED   + "-> Tem todo o dano que recebe de mobs ou players ");
		lore.add(ChatColor.RED + "multiplicado por " + String.format("%.2f", dmgMultiplier));
		return lore;
	}

	@Override
	public Material getMaterial() {
		return Material.COMPASS;
	}

	@Override
	public PlayableClass getPlayableClass() {
		return PlayableClass.SpeedRunner;
	}

	@Override
	public void add(Player p) {
		Main.playersClasses.put(p.getName(), getPlayableClass());
		Util.setupPlayer(p);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
	}
}
