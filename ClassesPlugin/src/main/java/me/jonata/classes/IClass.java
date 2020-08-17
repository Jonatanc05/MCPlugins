package me.jonata.classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public interface IClass extends Listener {

	String getName(); // Nome da classe
	ArrayList<String> getDescription(); // O que o usuário deve saber para escolher
	Material getMaterial(); // Um item do jogo pra servir de ícone (e. g. Material.IRON_SWORD, Material.COBBLESTONE)
	PlayableClass getPlayableClass(); // O tipo da classe como declarado no Enum "PlayableClass" que você deve criar
	void add(Player p); // Recomenda-se chamar Util.setupPlayer() no início, inicializa o player com sua classe

}
