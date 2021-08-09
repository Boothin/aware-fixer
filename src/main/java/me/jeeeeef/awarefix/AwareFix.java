package me.jeeeeef.awarefix;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public final class AwareFix extends JavaPlugin implements Listener{
    @Override
    public void onEnable() {
    	return;
    }
    
    @Override
    public void onDisable() {
        return;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if (sender instanceof Player) {
    		Player player = (Player) sender;
        	if(command.getName().equalsIgnoreCase("fixaware") && sender.hasPermission("fixaware.use")) {
	    		Chunk currentChunk = player.getLocation().getChunk();
	    		Entity[] currentEntities = currentChunk.getEntities();
	    		Bukkit.getScheduler().runTaskLaterAsynchronously(this, new Runnable() {
	    			@Override
	    			public void run() {
		    			player.sendMessage("Fixing aware for current chunk");
			    		for(Entity e : currentEntities) {
			    			if (e instanceof Mob) {
			    				Mob mob = (Mob) e;
			    				if(!mob.isAware()) {
			    					mob.setAware(true);
			    				}
			    			}
			    		}
	    			}
	    		}
	    		, 20);
    		}
    		else {
    			player.sendMessage(ChatColor.RED + "Insufficient Permissions");
    		}
    	}
        return false;
    }
}
