package me.jonata.mobacraft.classes;

import me.jonata.mobacraft.ItemBuilder;
import me.jonata.mobacraft.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ArcherClass extends IClass {

	public static final double bowDmgMultiplier = 1.70;
	public static final double meleeDmgMultiplier = 0.5;
	public static final int skillCost = 8;

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if (!(e.getEntity() instanceof Player)) return;

		// Reduzir dano melee
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (Main.getPlayerClass(p) == ClassID.Archer)
				e.setDamage(e.getDamage() * meleeDmgMultiplier);
		}

		// Aumentar dano do arco
		if (e.getDamager() instanceof Arrow){
			Arrow a = (Arrow) e.getEntity();
			if (a.getShooter() instanceof Player){
				Player shooter = (Player) a.getShooter();
				if (Main.getPlayerClass(shooter) == ClassID.Archer) {
					e.setDamage(e.getDamage() * bowDmgMultiplier);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		// Removendo casos que não interessam
		if (e.getItem() == null) return;
		ItemStack itens = e.getItem();
		if (itens.getType() != Material.ARROW ||
				Main.getPlayerClass(p) != ClassID.Archer)
			return;
		if (e.getAction() != Action.RIGHT_CLICK_AIR &&
				e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;

		// Habilidade em si
		if (p.getLevel() >= skillCost) {
			p.getInventory().addItem(itens.clone());
			p.setLevel(p.getLevel() - skillCost);
			p.sendMessage(ChatColor.GOLD + "Suas flechas foram duplicadas!");
		} else {
			p.sendMessage(ChatColor.RED + "Você não tem os " + skillCost + " níveis de experiência necessários para duplicar flechas.");
		}
	}

	@Override
	public String getName() {
		return "Arqueiro";
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> lore = new ArrayList<>();
		lore.add(ChatColor.GREEN + "-> Seu dano com arco ou besta é multiplicado");
		lore.add(ChatColor.GREEN + "por " + String.format("%.2f", bowDmgMultiplier));
		lore.add(ChatColor.GREEN + "-> Gastando " + skillCost + " levels de experiência ");
		lore.add(ChatColor.GREEN + "pode duplicar as flechas na sua mão");
		lore.add(ChatColor.GREEN + "-> Nasce com peitoral de couro");
		lore.add(ChatColor.RED   + "-> Tem seu dano melee multiplicado por " + String.format("%.2f", meleeDmgMultiplier));
		return lore;
	}

	@Override
	public Material getMaterial() {
		return Material.BOW;
	}

	@Override
	public ClassID getClassID() {
		return ClassID.Archer;
	}

	@Override
	public void add(Player p) {
		super.add(p);
		ItemStack cp = ItemBuilder.getItemStack(
				Material.LEATHER_CHESTPLATE,
				1,
				"Couraça do arqueiro",
				null
			);
		p.getEquipment().setChestplate(cp);
	}
}
