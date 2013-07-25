package com.caved_in.TotalWarItems.Handlers;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackHandler
{
	
	private String[] Blade_Prefixes_Common = new String[] { "Dull", "Mediocre", "Worn", "Damaged", "Cracked", "Bent", "Second Hand", "Home-Made", "Buster", "Training", "Large", "Petite", "Knotted", "Chipped" };
	private String[] Blade_Prefixes_Rare = new String[] { "Ferocious", "Deadly", "Raging", "Tempered", "Unique", "Ancient", "Mighty", "The Underdogs", "Flesh-Eating", "Bone Crushing" };
	private String[] Blade_Prefixes_Epic = new String[] { "Blood-Thirsty", "Legendary", "Couragous", "Brave Hearts", "Blood Stained", "Cthulus", "Hercules", "Macbeths", "Sir. Pents", "The Tzars", "Lord Williams", "Sir. Watsons" };
	private String[] Blade_Suffix_Common = new String[] { "the Peasant", "Adventuring", "the Adventurer", "False Hopes", "Compensation", "the Warrior", "Warriors" };
	private String[] Blade_Suffix_Rare = new String[] { "failed Apprentices", "Apprenticeship", "the Adventurer", "Adventuring", "War", "Hobknocking", "Destruction", "Strength", "the Bear", "Kinship", "Knights", "the Warlord", "Warlords" };
	private String[] Blade_Suffix_Epic = new String[] { "Destruction", "Mutilation", "Hobknocking", "War-Lords", "Death Bringing", "Kratos", "Ares", "Athena", "Zeus", "Hades", "the Templars", "Strongly Opinionated Literature", "Over Compensation" };
	private String Blade_Diamond_Boots_Weapon_Name = " Blade";
	private String Blade_Diamond_Boots_Weapon_Seperator = " of ";
	
	private String[] Katana_Prefixes_Common = new String[] { "Dull", "Mediocre", "Worn", "Damaged", "Tired", "Cracked", "Bent", "Fragile", "Second Hand", "Sharp", "Home-Made" };
	private String[] Katana_Prefixes_Rare = new String[] { "Ferocious", "Deadly", "Raging", "Tempered", "Unique", "Ancient" };
	private String[] Katana_Prefixes_Epic = new String[] { "Blood-Thirsty", "Legendary", "Couragous" };
	private String[] Katana_Suffix_Common = new String[] { "Training", "the Peasant", "Half-Grade Iron", "Adventuring", "the Adventurer" };
	private String[] Katana_Suffix_Rare = new String[] { "the Adventurer", "Adventuring", "War", "Hobknocking", "Destruction", "Strength", "the Bear", "Kinship", "Knights", "the Samurai", "Samurai-Jack" };
	private String[] Katana_Suffix_Epic = new String[] { "Destruction", "Mutilation", "Hobknocking", "War-Lords", "Death Bringing", "Zues", "Charlie Sheen", "Jodah-Kiin", "Kratos", "Ares", "Athena", "Epicosity" };
	private String Katana_Diamond_Boots_Weapon_Name = " Katana";
	private String Katana_Diamond_Boots_Weapon_Seperator = " of ";
	
	private String[] Bow_Prefixes_Common = new String[] { "Worn", "Damaged", "Tired", "Cracked", "Bent", "Fragile", "Second Hand", "Sharp", "Home-Made", "Oak", "Short", "Long" };
	private String[] Bow_Prefixes_Rare = new String[] { "Ferocious", "Deadly", "Deadly Short", "Deadly Long", "Raging", "Tempered", "Unique", "Ancient Long", "Ancient Short" };
	private String[] Bow_Prefixes_Epic = new String[] { "Legendary Long", "Legendary Short", "Couragous Short", "Couragous Long", "Blood Thirsty Yew Short", "Blood Thirsty Yew Long", "Blood Thirsty Maple Short", "Sacred Willow Short", "Sacred Willow Long", "Sacred Yew Short", "Sacred Yew Long", "Sacred Maple Short", "Sacred Maple Long" };
	private String[] Bow_Suffix_Common = new String[] { "Training", "Buck-Shots", "Sniping", "the Archer", "Archers" };
	private String[] Bow_Suffix_Rare = new String[] { "Champions", "the Eagle", "the Squirrel", "Precision", "Ares", "Sharp Shooting" };
	private String[] Bow_Suffix_Epic = new String[] { "the Pagans", "Robin Hood", "the Rogue", "Rogues", "Ares", "Athena", "Kratos", "Ezio", "Altair", "Assassins", "the Dark Brotherhood" };
	private String Bow_Diamond_Boots_Weapon_Name = " Bow";
	private String Bow_Diamond_Boots_Weapon_Seperator = " of ";
	
	private String[] Diamond_Boots_Prefixes_Common = new String[] { "Mediocre", "Worn", "Damaged", "Tired", "Second Hand", "Home-Made" };
	private String[] Diamond_Boots_Prefixes_Rare = new String[] { "Sturdy", "Premium", "Well-Built", "Fine" };
	private String[] Diamond_Boots_Prefixes_Epic = new String[] { "Perfect", "Excellent", "Steel Enforced" };
	private String[] Suffix_Common = new String[] { "Fur", "Adventuring", "the Adventurer", "Minor Defence", "Beginners", "the Noob", "Newbies" };
	private String[] Suffix_Rare = new String[] { "the Adventurer", "Adventuring", "the Bear", "Kinship", "Knights", "the Samurai", "Samurai-Jack" };
	private String[] Suffix_Epic = new String[] { "Solid Defence", "Zeus", "Unmoving", "Eternity" };
	private String Diamond_Boots_Weapon_Name = " Diamond Boots";
	private String Diamond_Boots_Weapon_Seperator = " of ";
	
	private ChatColor CommonColor = ChatColor.GREEN;
	private ChatColor RareColor = ChatColor.AQUA;
	private ChatColor EpicColor = ChatColor.GOLD;
	
	public ItemStackHandler()
	{
		
	}
	
	public String Generate_Blade_Name(Tier ItemTier)
	{
		switch (ItemTier)
		{
			case Common:
				if (new Random().nextInt(100) <= 30)
				{
					return (CommonColor + Blade_Prefixes_Common[new Random().nextInt(Blade_Prefixes_Common.length)] + this.Blade_Diamond_Boots_Weapon_Name + this.Blade_Diamond_Boots_Weapon_Seperator + Blade_Suffix_Common[new Random().nextInt(Blade_Suffix_Common.length)]);
				}
				else
				{
					return (CommonColor + Blade_Prefixes_Common[new Random().nextInt(Blade_Prefixes_Common.length)] + this.Blade_Diamond_Boots_Weapon_Name);
				}
			case Rare:
				return (RareColor + Blade_Prefixes_Rare[new Random().nextInt(Blade_Prefixes_Rare.length)] + this.Blade_Diamond_Boots_Weapon_Name + this.Blade_Diamond_Boots_Weapon_Seperator + Blade_Suffix_Rare[new Random().nextInt(Blade_Suffix_Rare.length)]);
			case Epic:
				return (EpicColor + Blade_Prefixes_Epic[new Random().nextInt(Blade_Prefixes_Epic.length)] + this.Blade_Diamond_Boots_Weapon_Name + this.Blade_Diamond_Boots_Weapon_Seperator + Blade_Suffix_Epic[new Random().nextInt(Blade_Suffix_Epic.length)]);
			default:
				return Blade_Diamond_Boots_Weapon_Name;
		}
	}
	
	public String Generate_Katana_Name(Tier ItemTier)
	{
		switch (ItemTier)
		{
			case Common:
				if (new Random().nextInt(100) <= 30)
				{
					return (CommonColor + Katana_Prefixes_Common[new Random().nextInt(Katana_Prefixes_Common.length)] + this.Katana_Diamond_Boots_Weapon_Name + this.Katana_Diamond_Boots_Weapon_Seperator + Katana_Suffix_Common[new Random().nextInt(Katana_Suffix_Common.length)]);
				}
				else
				{
					return (CommonColor + Katana_Prefixes_Common[new Random().nextInt(Katana_Prefixes_Common.length)] + this.Katana_Diamond_Boots_Weapon_Name);
				}
			case Rare:
				return (RareColor + Katana_Prefixes_Rare[new Random().nextInt(Katana_Prefixes_Rare.length)] + this.Katana_Diamond_Boots_Weapon_Name + this.Katana_Diamond_Boots_Weapon_Seperator + Katana_Suffix_Rare[new Random().nextInt(Katana_Suffix_Rare.length)]);
			case Epic:
				return (EpicColor + Katana_Prefixes_Epic[new Random().nextInt(Katana_Prefixes_Epic.length)] + this.Katana_Diamond_Boots_Weapon_Name + this.Katana_Diamond_Boots_Weapon_Seperator + Katana_Suffix_Epic[new Random().nextInt(Katana_Suffix_Epic.length)]);
			default:
				return Katana_Diamond_Boots_Weapon_Name;
		}
	}
	
	public String Generate_Bow_Name(Tier ItemTier)
	{
		switch (ItemTier)
		{
			case Common:
				if (new Random().nextInt(100) <= 30)
				{
					return (CommonColor + Bow_Prefixes_Common[new Random().nextInt(Bow_Prefixes_Common.length)] + this.Bow_Diamond_Boots_Weapon_Name + this.Bow_Diamond_Boots_Weapon_Seperator + Bow_Suffix_Common[new Random().nextInt(Bow_Suffix_Common.length)]);
				}
				else
				{
					return (CommonColor + Bow_Prefixes_Common[new Random().nextInt(Bow_Prefixes_Common.length)] + this.Bow_Diamond_Boots_Weapon_Name);
				}
			case Rare:
				return (RareColor + Bow_Prefixes_Rare[new Random().nextInt(Bow_Prefixes_Rare.length)] + this.Bow_Diamond_Boots_Weapon_Name + this.Bow_Diamond_Boots_Weapon_Seperator + Bow_Suffix_Rare[new Random().nextInt(Bow_Suffix_Rare.length)]);
			case Epic:
				return (EpicColor + Bow_Prefixes_Epic[new Random().nextInt(Bow_Prefixes_Epic.length)] + this.Bow_Diamond_Boots_Weapon_Name + this.Bow_Diamond_Boots_Weapon_Seperator + Bow_Suffix_Epic[new Random().nextInt(Bow_Suffix_Epic.length)]);
			default:
				return Bow_Diamond_Boots_Weapon_Name;
		}
	}
	
	public String Generate_Diamond_Boots_Name(Tier ItemTier)
	{
		if (ItemTier == Tier.Common)
		{
			if (new Random().nextInt(100) <= 30)
			{
				return (Diamond_Boots_Prefixes_Common[new Random().nextInt(Diamond_Boots_Prefixes_Common.length)] + this.Diamond_Boots_Weapon_Name + this.Diamond_Boots_Weapon_Seperator + Suffix_Common[new Random().nextInt(Suffix_Common.length)]);
			}
			else
			{
				return (Diamond_Boots_Prefixes_Common[new Random().nextInt(Diamond_Boots_Prefixes_Common.length)] + this.Diamond_Boots_Weapon_Name);
			}
		}
		else if (ItemTier == Tier.Rare)
		{
			return (Diamond_Boots_Prefixes_Rare[new Random().nextInt(Diamond_Boots_Prefixes_Rare.length)] + this.Diamond_Boots_Weapon_Name + this.Diamond_Boots_Weapon_Seperator + Suffix_Rare[new Random().nextInt(Suffix_Rare.length)]);
		}
		else if (ItemTier == Tier.Epic)
		{
			return (Diamond_Boots_Prefixes_Epic[new Random().nextInt(Diamond_Boots_Prefixes_Epic.length)] + this.Diamond_Boots_Weapon_Name + this.Diamond_Boots_Weapon_Seperator + Suffix_Epic[new Random().nextInt(Suffix_Epic.length)]);
		}
		return this.Diamond_Boots_Weapon_Name;
	}
	
	public ArrayList<String> getItemLore(ItemStack Item)
	{
		ArrayList<String> ReturnLore = new ArrayList<String>();
		if (Item.hasItemMeta() == true)
		{
			for (String S : Item.getItemMeta().getLore())
			{
				ReturnLore.add(S);
			}
			return ReturnLore;
		}
		else
		{
			return null;
		}
	}
	
	public String getItemLore(ItemStack Item, int Line)
	{
		if (Item.hasItemMeta() == true)
		{
			return Item.getItemMeta().getLore().get(Line);
		}
		else
		{
			return null;
		}
	}
	
	public boolean itemLoreContains(ItemStack Item, String Text)
	{
		if (Item.hasItemMeta() == true)
		{
			ArrayList<String> Lore = getItemLore(Item);
			for (String s : Lore)
			{
				if (s.toLowerCase().contains(Text.toLowerCase()))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void setItemName(ItemStack Item, String Text)
	{
		if (Item.hasItemMeta())
		{
			ItemMeta iMeta = Item.getItemMeta();
			iMeta.setDisplayName(Text);
			Item.setItemMeta(iMeta);
		}
	}
	
	public String getItemName(ItemStack Item)
	{
		return Item.getItemMeta().getDisplayName();
	}
	
	/**
	 * Removes items from an Item Stack, and then returns the stack
	 * 
	 * @param Item
	 *            The Itemstack to modify
	 * @param Amount
	 *            The amount to remove from the item stack
	 * @return Returns the itemstack with an amount removed from it, but if the
	 *         amount is greater thanwhat's in the stack, it returns null
	 */
	public ItemStack RemoveFromStack(ItemStack Item, int Amount)
	{
		if (Item.getAmount() > Amount)
		{
			ItemMeta StackMeta = Item.getItemMeta();
			ItemStack Return = new ItemStack(Item.getType(), (Item.getAmount() - Amount));
			Return.setItemMeta(StackMeta);
			return Return;
		}
		else
		{
			return null;
		}
	}
	
	public static enum Tier
	{
		Common, Rare, Epic
	}
	
}
