package me.jonata.mobacraft.classes;

import me.jonata.mobacraft.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public abstract class IClass implements Listener {

	// Nome da classe
	public abstract String getName();

	/*
	 * Vantagens e desvantagens da classe
	 * Cada String no array corresponde a uma linha
	 * Não deixar linhas com muito mais que 50 caracteres
	 * Iniciar a primeira linha sobre uma vantagem ou desvantagem com '-> '
	 * Iniciar descrevendo as vantagens (em verde) depois desvantagens (em vermelho)
	 * A linha abaixo é um exemplo de como colorir uma desvantagem em vermelho
	 * ChatColor.RED + "-> Toma dano na água"
	 */
	public abstract ArrayList<String> getDescription();

	// Um item do jogo pra servir de ícone (e. g. Material.IRON_SWORD, Material.COBBLESTONE)
	public abstract Material getMaterial();

	// O tipo da classe como declarado no Enum "ClassID" que você deve criar
	public abstract ClassID getClassID();

	/* Se precisar executar algo assim que o jogador escolher a classe, sobrescreva
	 * este método chamando super.add(p) no início
	 */
	public void add(Player p) {
		p.getInventory().clear();
		p.setGameMode(GameMode.SURVIVAL);
		p.setHealth(p.getHealthScale());
		p.setFoodLevel(20);
		Main.playersClasses.put(p.getName(), getClassID());
	}

}
