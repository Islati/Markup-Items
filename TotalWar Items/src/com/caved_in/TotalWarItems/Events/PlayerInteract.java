package com.caved_in.TotalWarItems.Events;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.caved_in.TotalWar;
import com.caved_in.TotalWarItems.TotalWarItems;

public class PlayerInteract implements Listener
{
	public PlayerInteract(TotalWarItems Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler
	public void InteractEvent(PlayerInteractEvent Event)
	{
		if (Event.isCancelled())
		{
			return;
		}
		
		// if (Event.getPlayer().getGameMode() == GameMode.cre)
		Player Player = Event.getPlayer();
		if (Event.getAction() == Action.RIGHT_CLICK_AIR || Event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if (Player.getItemInHand() != null && Player.getItemInHand().hasItemMeta() == true && Player.getItemInHand().getItemMeta().hasLore() == true)
			{
				if (TotalWar.ItemNamer.itemLoreContains(Player.getItemInHand(), "Watch out, it's going to explode!"))
				{
					if (Event.getPlayer().getInventory().contains(Material.SULPHUR))
					{
						int SlotOfSulphur = Player.getInventory().first(Material.SULPHUR);
						ItemStack SulphurStack = Player.getInventory().getItem(SlotOfSulphur);
						Player.getInventory().setItem(SlotOfSulphur, TotalWar.ItemNamer.RemoveFromStack(SulphurStack, 1));
						shootFireball(1.0, Player);
					}
				}
			}
		}
	}
	
	public void shootFireball(Double speed, Player Player)
	{
		// Shooter:
		Location shootLocation = Player.getEyeLocation();
		
		// Direction vector:
		Vector directionVector = shootLocation.getDirection().normalize();
		
		// Shoot shift vector:
		double startShift = 2;
		Vector shootShiftVector = new Vector(directionVector.getX() * startShift, directionVector.getY() * startShift, directionVector.getZ() * startShift);
		
		// Shift shoot location:
		shootLocation = shootLocation.add(shootShiftVector.getX(), shootShiftVector.getY(), shootShiftVector.getZ());
		
		// Create the fireball:
		Fireball fireballl = shootLocation.getWorld().spawn(shootLocation, Fireball.class);
		fireballl.setVelocity(directionVector.multiply(speed));
		
		// Remove fire:
		fireballl.setIsIncendiary(true);
		fireballl.setFireTicks(20 + new Random().nextInt(60));
		
	}
	
}
