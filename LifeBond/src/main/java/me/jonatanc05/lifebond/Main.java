package me.jonatanc05.lifebond;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

	public static ArrayList<Bond> bonds;

	@Override
	public void onEnable() {
		bonds = new ArrayList<>();
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) return false;

		// Entrar em vínculo existente
		if (label.equalsIgnoreCase("entrar")) {
			if(args.length != 1) {
				sender.sendMessage(ChatColor.RED + "/entrar <nome do vínculo>");
				return false;
			}
			for(Bond bond : bonds) {
				if(bond.getName().equalsIgnoreCase(args[0])) {
					bond.addPlayer((Player) sender);
					sender.sendMessage(ChatColor.GREEN + "Você foi adicionado ao vínculo '" + bond.getName() + "'");
					return true;
				}
			}
			Bond bond = new Bond(args[0], (Player) sender);
			bonds.add(bond);
			getServer().getPluginManager().registerEvents(bond, this);
			sender.sendMessage(ChatColor.GREEN + "Você criou o vínculo '" + args[0] + "'.");
		}

		// Listar vínculos
		if (label.equalsIgnoreCase("vinculos")) {
			String msg = "Vínculos (" + bonds.size() + "): ";
			for(Bond bond : bonds) {
				if(bond.getName().equalsIgnoreCase(args[0])) {
					bond.addPlayer((Player) sender);
					msg += bond.getName() + ", ";
					return true;
				}
			}
		}

		// Informa o vínculo do player
		if (label.equalsIgnoreCase("meuvinculo")) {
			for(Bond bond : bonds) {
				if(bond.contains((Player) sender)) {
					sender.sendMessage("Você pertence ao vínculo " + bond.getName() + ".");
					return true;
				}
			}
			sender.sendMessage("Você não está inserido em nenhum vínculo.");
			return true;
		}

		// Retira o player do vínculo atual
		if (label.equalsIgnoreCase("sair")) {
			for(Bond bond : bonds) {
				if(bond.contains((Player) sender)) {
					bond.removePlayer((Player) sender);
					sender.sendMessage(ChatColor.GREEN + "Você saiu do vínculo " + bond.getName() + ".");
					return true;
				}
			}
			sender.sendMessage(ChatColor.RED + "Você não está inserido em nenhum vínculo.");
			return true;
		}
		return false;
	}
}
