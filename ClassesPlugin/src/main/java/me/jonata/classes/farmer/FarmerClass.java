package me.jonata.classes.farmer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import me.jonata.classes.IClass;
import me.jonata.classes.Main;
import me.jonata.classes.PlayableClass;
import me.jonata.classes.Util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class FarmerClass implements IClass {

	private static final Set<Material> DOUBLE_DROP_MATERIALS
			= FarmerDoubleDropMaterialsSetFactory.build();

	@EventHandler
	public void onBlockDropItem(BlockDropItemEvent e) {
		List<Item> items = e.getItems();
		for (Item item : items) {
			ItemStack itemStack = item.getItemStack();
			if (DOUBLE_DROP_MATERIALS.contains(itemStack.getType())) {
				itemStack.setAmount(2 * itemStack.getAmount());
			}
		}
	}

	@EventHandler
	public void onPlayerTakeDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)
				|| Main.playersClasses.get(((Player) e.getEntity()).getName())
				!= PlayableClass.Farmer) {
			return;
		}
		e.setDamage(e.getDamage() * 1.5);
	}

	@Override
	public String getName() {
		return "Fazendeiro";
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> lore = new ArrayList<>();
		lore.add(ChatColor.GREEN + "-> Colhe o dobro de plantações");
		lore.add(ChatColor.GREEN + "-> Nasce com enxada, balde de água, semente de trigo e cenoura");
		lore.add(ChatColor.RED + "-> Toma 150% de dano");
		return lore;
	}

	@Override
	public Material getMaterial() {
		return Material.WHEAT;
	}

	@Override
	public PlayableClass getPlayableClass() {
		return PlayableClass.Farmer;
	}

	@Override
	public void add(Player p) {
		Main.playersClasses.put(p.getName(), getPlayableClass());
		Util.setupPlayer(p);
		ItemStack diamondHoe = Util.createItemStack(
				Material.IRON_HOE,
				1,
				"Querida Enxada",
				null
		);
		diamondHoe.addEnchantment(Enchantment.MENDING, 1);
		ItemStack waterBucket = Util.createItemStack(
				Material.WATER_BUCKET,
				1,
				"Baldão",
				null
		);
		ItemStack wheatSeeds = Util.createItemStack(
				Material.WHEAT_SEEDS,
				1,
				"Semente de trigo",
				null
		);
		ItemStack carrot = Util.createItemStack(
				Material.CARROT,
				1,
				"Cenoura",
				null
		);
		p.getInventory().addItem(diamondHoe, waterBucket, wheatSeeds, carrot);
	}

}
