package me.jonata.mobacraft.classes;

import me.jonata.mobacraft.ItemBuilder;
import me.jonata.mobacraft.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.util.ArrayList;
import java.util.Random;

public class AdventurerClass extends IClass {

	private static Random r = new Random();
	private static double loseDropChance = 0.20;
	private static int bonusHunger = 2;

	@EventHandler
	public void onBreakBlock(BlockBreakEvent e) {
		if (Main.getPlayerClass(e.getPlayer()) == ClassID.Adventurer) {
			if (r.nextDouble() < loseDropChance)
				e.setDropItems(false);
		}
	}

	@EventHandler
	public void onPlayerSpawn(PlayerSpawnLocationEvent e) {
		Player p = e.getPlayer();
		if (Main.getPlayerClass(p) == ClassID.Adventurer)
			p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, 0));
	}

	@EventHandler
	public void onItemConsume(PlayerItemConsumeEvent e) {
		Player p = e.getPlayer();
		if (Main.getPlayerClass(p) == ClassID.Adventurer)
			p.setFoodLevel(p.getFoodLevel() + bonusHunger);
	}

	@Override
	public String getName() {
		return "Aventureiro";
	}

	@Override
	public Material getMaterial() {
		return Material.SHULKER_BOX;
	}

	@Override
	public ClassID getClassID() {
		return ClassID.Adventurer;
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> desc = new ArrayList<String>();
		desc.add(ChatColor.GREEN + "-> Nasce com uma shulker box");
		desc.add(ChatColor.GREEN + "-> Tem efeito de Sorte constante");
		desc.add(ChatColor.GREEN + "-> Itens consumíveis recuperam " + String.format(".1f", ((double)bonusHunger)/2f) + " pernis de fome");
		desc.add(ChatColor.RED   + "-> Ao quebrar um bloco, há " + String.format("%.2f", loseDropChance*100) + "% de chance de os");
		desc.add(ChatColor.RED   + "itens correspondentes não droparem");
		return desc;
	}

	@Override
	public void add(Player p) {
		super.add(p);
		ItemStack backpack = ItemBuilder.getItemStack(
				Material.SHULKER_BOX,
				1,
				"Mochila do Aventureiro",
				null
			);
		p.getInventory().addItem(backpack);
		p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, 0);
	}

}
