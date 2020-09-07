package me.jonata.classes.farmer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Material;

public class FarmerDoubleDropMaterialsSetFactory {

	public static Set<Material> build() {
		Material[] materialsArray = {
			Material.WHEAT,
			Material.WHEAT_SEEDS,
			Material.BEETROOT,
			Material.BEETROOT_SEEDS,
			Material.POTATO,
			Material.CARROT,
			Material.PUMPKIN,
			Material.MELON,
			Material.SUGAR_CANE
		};

		return new HashSet(Arrays.asList(materialsArray));
	}

}
