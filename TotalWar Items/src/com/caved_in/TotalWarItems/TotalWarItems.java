package com.caved_in.TotalWarItems;

import java.io.File;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.caved_in.TotalWarItems.Events.DamageEntity;
import com.caved_in.TotalWarItems.Events.InventoryClose;
import com.caved_in.TotalWarItems.Handlers.ArmorHandler;
import com.caved_in.TotalWarItems.Handlers.ItemHandler;
import com.caved_in.TotalWarItems.Handlers.ItemStackHandler;

public class TotalWarItems extends JavaPlugin
{
	
	public static ItemStackHandler StackHandler = new ItemStackHandler();
	public static ItemHandler ItemHandler;
	public static ArmorHandler ArmorHandler = new ArmorHandler();
	
	@Override
	public void onEnable()
	{
		ItemHandler = new ItemHandler(this.getDataFolder() + File.separator + "Items.txt");
		new DamageEntity(this);
		new InventoryClose(this);
		// new PlayerInteract(this);
	}
	
	@Override
	public void onDisable()
	{
		HandlerList.unregisterAll(this);
	}
}
