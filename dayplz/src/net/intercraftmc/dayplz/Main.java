package net.intercraftmc.dayplz;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;



public class Main extends JavaPlugin implements Listener {
	int novotes = 0;
	int yesvotes = 0;
	boolean votesaccepted = false;
	String votemaker;
	

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		
		System.out.println("Loaded dayplz");
		
		
	}
	@Override
	public void onDisable() {
		System.out.println("Disabled dayplz");
	}
	
	
	
	
	
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	
	
	
	
		if (cmd.getName().equals("day")) {
			novotes = 0;
			yesvotes = 0;
		if (sender instanceof Player) {
			Player player = (Player) sender;
			votemaker = player.getName();
			World world = player.getWorld();
			if(world.getTime() <= 12541) {
				player.sendMessage(ChatColor.RED + "You can only make a vote for day at night!");
				return true;
			}
			
			if(votesaccepted == true) {
				player.sendMessage(ChatColor.RED + "You can't make a vote while a vote is currently active!");
			}
			votesaccepted = true;
			for (Player p : Bukkit.getOnlinePlayers()) {
			    p.sendMessage(ChatColor.BLUE +"A user has started a vote for the time to be set to day. If you would like to vote, say /vote yes or /vote no.");
			}
			
						
				        BukkitScheduler scheduler = getServer().getScheduler();
				       
				        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
				            @Override
				            public void run() {
				            	if(yesvotes >= novotes) {
									for (Player p : Bukkit.getOnlinePlayers()) {
									   
									    world.setTime(1000);
									    p.sendMessage(ChatColor.GREEN +"Changed the time to day.");
									    votesaccepted = false;
									
									}
									}else {
										for (Player p : Bukkit.getOnlinePlayers()) {
										    p.sendMessage(ChatColor.RED +"The time will stay the same as majority of players have voted for the time to stay the same.");
										    votesaccepted = false;
										}
									
							}
				            }
				        }, 600L);
			

					
					
		
			

		
			
			
			
		}
		}
		if (cmd.getName().equals("vote")) {
			if (votesaccepted == false) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					player.sendMessage(ChatColor.RED + "There is no vote going on currently. You can create a vote for day by using /day.");
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("no")) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					if (player.getName() == votemaker) {
						player.sendMessage(ChatColor.RED + "You are unable to cast a vote if you have created the vote!");
						return true;
					}
					novotes = novotes + 1;
					player.sendMessage(ChatColor.GREEN + "Your vote has been recorded successfully.");
				}
			}
			if (args[0].equalsIgnoreCase("yes")) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					yesvotes = yesvotes + 1;
					player.sendMessage(ChatColor.GREEN + "Your vote has been recorded successfully.");
				}
			}
		}
		
		
		if (cmd.getName().equals("votestats")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				
				player.sendMessage(ChatColor.GOLD + "There are currently "+yesvotes+" players who want time to be changed to day and "+novotes+" who do not want to.");
			}
		}
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		return false;
		
	}




}





