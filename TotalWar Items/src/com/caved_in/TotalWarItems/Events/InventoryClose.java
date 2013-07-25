package com.caved_in.TotalWarItems.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import com.caved_in.TotalWarItems.TotalWarItems;
import com.caved_in.TotalWarItems.Handlers.DataType.ArmorType.Armor;

public class InventoryClose implements Listener
{
	public InventoryClose(TotalWarItems Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler
	public void InventoryIsClosed(InventoryCloseEvent Event)
	{
		Player Player = (Player) Event.getPlayer();
		double Health = 20;
		
		if (TotalWarItems.ArmorHandler.hasEquiped(Armor.HELM, Player) == true)
		{
			Health += TotalWarItems.ArmorHandler.getHealthBonus(Player.getInventory().getHelmet(), Player);
		}
		
		if (TotalWarItems.ArmorHandler.hasEquiped(Armor.PLATEBODY, Player) == true)
		{
			Health += TotalWarItems.ArmorHandler.getHealthBonus(Player.getInventory().getChestplate(), Player);
		}
		
		if (TotalWarItems.ArmorHandler.hasEquiped(Armor.PLATELEGS, Player) == true)
		{
			Health += TotalWarItems.ArmorHandler.getHealthBonus(Player.getInventory().getLeggings(), Player);
		}
		
		if (TotalWarItems.ArmorHandler.hasEquiped(Armor.BOOTS, Player) == true)
		{
			Health += TotalWarItems.ArmorHandler.getHealthBonus(Player.getInventory().getBoots(), Player);
		}
		
		Player.setMaxHealth((int) Health);
		// Player.setLevel((int) ((Damageable) Player).getHealth());
	}
	
}
