package com.caved_in.TotalWarItems.Handlers;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.caved_in.TotalWarItems.Handlers.DataType.ArmorType.Armor;

public class ArmorHandler
{
	
	private String[] HPBonus = new String[] { "Increases Max HP by ", "!" };
	private List<String> Effects = Arrays.asList(new String[] { "Mana regen" });
	private ItemStackHandler StackHandler = new ItemStackHandler();
	
	public ArmorHandler()
	{
		
	}
	
	public ItemStack[] getPlayerArmor(Player Player)
	{
		return Player.getInventory().getArmorContents();
	}
	
	public boolean hasEquiped(Armor Piece, Player Player)
	{
		switch (Piece)
		{
			case BOOTS:
				return (Player.getInventory().getBoots() != null) && (Player.getInventory().getBoots().getType() != Material.AIR);
			case HELM:
				return (Player.getInventory().getHelmet() != null) && (Player.getInventory().getHelmet().getType() != Material.AIR);
			case PLATEBODY:
				return (Player.getInventory().getChestplate() != null) && (Player.getInventory().getChestplate().getType() != Material.AIR);
			case PLATELEGS:
				return (Player.getInventory().getLeggings() != null) && (Player.getInventory().getLeggings().getType() != Material.AIR);
			default:
				break;
		}
		return false;
	}
	
	/*
	public int getHealthBonus(Player Player)
	{
		try
		{
			for(ItemStack Item : getPlayerArmor(Player))
			{
				if (Item != null)
				{
					if (Item.getType() != Material.AIR)
					{
						if (Item.hasItemMeta() == true)
						{
							ItemMeta ItemMeta = Item.getItemMeta();
							if (ItemMeta.hasLore() == true)
							{
								for (String S : ItemMeta.getLore())
								{
									String LoreLine = S.toLowerCase();
									if ((LoreLine.contains("deals")) && (LoreLine.contains("to")) && (LoreLine.contains("damage!")))
									{
										//check for health
									}
									else if (LoreLine.contains("% chance to "))
									{
										
									}
								}
							}
						}
					}
				}
			}
		}
		catch (NullPointerException Ex)
		{
			//Dirty Catch
		}
		*/
	
	public int getHealthBonus(ItemStack Item, Player Player)
	{
		try
		{
			if (Item.hasItemMeta() == true)
			{
				ItemMeta ItemMeta = Item.getItemMeta();
				if (ItemMeta.hasLore() == true)
				{
					for (String S : ItemMeta.getLore())
					{
						if (S.contains(this.HPBonus[0]))
						{
							return Integer.parseInt(StringUtils.substringBetween(S, this.HPBonus[0], this.HPBonus[1]));
						}
					}
				}
			}
			return 0;
		}
		catch (Exception Ex)
		{
			return 0;
		}
	}
	
	public boolean hasArmorEffect(ItemStack Piece)
	{
		List<String> Lore = StackHandler.getItemLore(Piece);
		if (Lore != null)
		{
			for (String S : Lore)
			{
				if (S.toLowerCase().contains("attribute:"))
				{
					return true;
				}
			}
			return false;
		}
		return false;
	}
	/*
	public double getManaIncrease(Player Player)
	{
		double Increase = 0.0;
		if (this.hasEquiped(Armor.HELM, Player))
		{
			
		}
	}
	*/
}
