package com.caved_in.TotalWarItems.Weapons;

import me.cybermaxke.materialapi.material.CustomMaterial;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class HandCannon extends CustomMaterial
{
	
	public HandCannon(String id, Material material)
	{
		super("HandCannon", Material.DISPENSER);
		this.setName(ChatColor.LIGHT_PURPLE + "Hand Cannon");
		this.setCanPlace(false);
		this.setLore(new String[] { ChatColor.RED + "Watch out, it's going to explode!", ChatColor.RED + "Uses Gunpowder for Ammo" });
	}
	
	@Override
	public void onBlockBreak(BlockBreakEvent Event)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onBlockDamage(BlockDamageEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onBlockInteract(PlayerInteractEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onBlockPlaced(BlockPlaceEvent Event)
	{
		// Event.setCancelled(true);
	}
	
	@Override
	public void onDrop(PlayerDropItemEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onHit(EntityDamageByEntityEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onHold(PlayerItemHeldEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onInteract(PlayerInteractEvent Event)
	{
		
	}
	
	@Override
	public void onInteractEntity(PlayerInteractEntityEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
}
