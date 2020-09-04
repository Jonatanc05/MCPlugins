package me.jonata.classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ArcherClass implements IClass {

	public static final double bowDmgMultiplier = 1.5;
	public static final double meleeDmgMultiplier = 0.5;
	public static final int arrowsCrafted = 5;

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if (!(e.getEntity() instanceof Player)) return;

		// Reduzir dano melee
		if (e.getDamager() instanceof Player)
			e.setDamage(e.getDamage() * meleeDmgMultiplier);

		// Aumentar dano do arco
		if (e.getDamager() instanceof Arrow){
			Arrow a = (Arrow) e.getEntity();
			if (a.getShooter() instanceof Player){
				Player shooter = (Player) a.getShooter();
				if (Main.playersClasses.get(shooter.getName()) == PlayableClass.Archer) {
					e.setDamage(e.getDamage() * bowDmgMultiplier);
				}
			}
		}
	}

	@Override
	public String getName() {
		return "Arqueiro";
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> lore = new ArrayList<>();
		lore.add(ChatColor.GREEN + "-> Seu dano com arco ou besta é multiplicado por " + String.format("%.2f", bowDmgMultiplier));
		lore.add(ChatColor.GREEN + "-> Fabrica " + arrowsCrafted + " flechas de cada vez");
		lore.add(ChatColor.GREEN + "-> Nasce com peitoral de couro");
		lore.add(ChatColor.RED   + "-> Tem seu dano melee multiplicado por " + String.format("%.2f", meleeDmgMultiplier));
		return lore;
	}

	@Override
	public Material getMaterial() {
		return Material.BOW;
	}

	@Override
	public PlayableClass getPlayableClass() {
		return PlayableClass.Archer;
	}

	@Override
	public void add(Player p) {
		Main.playersClasses.put(p.getName(), getPlayableClass());
		Util.setupPlayer(p);
		ItemStack cp = Util.createItemStack(Material.LEATHER_CHESTPLATE, 1,"Couraça do arqueiro", null);
		p.getEquipment().setChestplate(cp);
	}
}
