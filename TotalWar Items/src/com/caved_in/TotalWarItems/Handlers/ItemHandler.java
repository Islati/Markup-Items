package com.caved_in.TotalWarItems.Handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import me.cybermaxke.materialapi.inventory.CustomItemStack;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import com.caved_in.TotalWarItems.Handlers.ItemStackHandler.Tier;

public class ItemHandler
{
	private String DataLocation;
	private TextHandler ItemData;
	private String[] HpBonus = new String[] { "Increases Max HP by ", "!" };
	private String[] ItemTag = { "<Item>", "</Item>" };
	
	private String[] MultipleMaterialsTag = { "<MultipleMaterials>", "</MultipleMaterials>" };
	private String[] TypesTag = { "<Types>", "</Types>" };
	private String[] TypeTag = { "<Type>", "</Type>" };
	
	private String[] IDTag = { "<ID>", "</ID>" };
	
	private String[] NameTag = { "<Name>", "</Name>" };
	private String[] HasNameColorTag = { "<HasNameColor>", "</HasNameColor>" };
	private String[] NameColorTag = { "<NameColor>", "</NameColor>" };
	private String[] SeperatorTag = { "<Seperator>", "</Seperator>" };
	private String[] GeneratedNameTag = { "<GeneratedName>", "</GeneratedName>" };
	
	private String[] CommonTag = { "<Common>", "</Common>" };
	private String[] RareTag = { "<Rare>", "</Rare>" };
	private String[] EpicTag = { "<Epic>", "</Epic>" };
	
	private String[] PrefixesTag = { "<Prefixes>", "</Prefixes>" };
	private String[] PrefixTag = { "<Prefix>", "</Prefix>" };
	private String[] SuffixesTag = { "<Suffixes>", "</Suffixes>" };
	private String[] SuffixTag = { "<Suffix>", "</Suffix>" };
	
	private String[] LoreTag = { "<Lore>", "</Lore>" };
	private String[] LoreLineTag = { "<Line>", "</Line>" };
	private String[] HasLoreTag = { "<HasLore>", "</HasLore>" };
	
	private String[] EnchantmentsTag = { "<Enchantments>", "</Enchantments>" };
	private String[] EnchantTag = { "<Enchantment>", "</Enchantment>" };
	// private String[] EnchantTypeTag = { "<EnchantmentType>",
	// "</EnchantmentType>" };
	private String[] EnchantmentLevelTag = { "<Level>", "</Level>" };
	
	private String[] DamageTag = { "<Damage>", "</Damage>" };
	private String[] MinDamageTag = { "<Minimum>", "</Minimum>" };
	private String[] MaxDamageTag = { "<Maximum>", "</Maximum>" };
	
	private String[] ArmorTag = { "<IsArmor>", "</IsArmor>" };
	private String[] HorseTag = new String[] { "<Horse>", "</Horse>" };
	
	private String[] HealthMinTag = new String[] { "<HealthMin>", "</HealthMin>" };
	private String[] HealthMaxTag = new String[] { "<HealthMax>", "</HealthMax>" };
	
	private String[] EffectsTag = { "<Effects>", "</Effects>" };
	private String[] EffectTag = { "<Effect>", "</Effect>" };
	private String[] EffectNameTag = { "<EffectName>", "</EffectName>" };
	private String[] EffectGenChanceTag = { "<EffectChance>", "</EffectChance>" };
	private String[] HasEffectsTag = { "<HasEffects>", "</HasEffects>" };
	
	public ItemHandler(String Location)
	{
		this.DataLocation = Location;
		this.ItemData = new TextHandler(Location);
	}
	
	/**
	 * @param ItemID
	 *            Get the data block for the specified ItemID
	 */
	public String getData(String ItemID)
	{
		for (String S : this.ItemData.getAllBetween(this.ItemTag[0], this.ItemTag[1]))
		{
			if (this.ItemData.getBetween(S, this.IDTag[0], this.IDTag[1]).equalsIgnoreCase(ItemID))
			{
				return S;
			}
		}
		return null;
	}
	
	/**
	 * Get all the available item IDS
	 */
	public List<String> getAllItemIDs()
	{
		List<String> IDs = new ArrayList<String>();
		for (String S : this.ItemData.getAllBetween(this.ItemTag[0], this.ItemTag[1]))
		{
			IDs.add(this.ItemData.getBetween(S, this.IDTag[0], this.IDTag[1]));
		}
		return IDs;
	}
	
	/**
	 * @param ItemID
	 *            Get all the available prefixes for the specified itemID
	 */
	public List<String> getPrefixes(String ItemID)
	{
		return this.ItemData.getAllBetween(this.ItemData.getBetween(getData(ItemID), this.PrefixesTag[0], this.PrefixesTag[1]), this.PrefixTag[0], this.PrefixTag[1]);
	}
	
	public List<String> getSuffixes(String ItemID)
	{
		return this.ItemData.getAllBetween(this.ItemData.getBetween(getData(ItemID), this.SuffixesTag[0], this.SuffixesTag[1]), this.SuffixTag[0], this.SuffixTag[1]);
	}
	
	public Material getMaterial(String ItemID)
	{
		return Material.getMaterial(this.ItemData.getBetween(getData(ItemID), this.TypeTag[0], this.TypeTag[1]));
	}
	
	public List<Material> getMaterials(String ID)
	{
		if (hasMultipleMaterials(ID))
		{
			List<Material> Materials = new ArrayList<Material>();
			for (String S : this.ItemData.getAllBetween(this.ItemData.getBetween(getData(ID), this.TypesTag[0], this.TypesTag[1]), this.TypeTag[0], this.TypeTag[1]))
			{
				try
				{
					Materials.add(Material.getMaterial(S));
				}
				catch (Exception Ex)
				{
					Ex.printStackTrace();
				}
			}
			
			return Materials;
		}
		return null;
	}
	
	public String getName(String ID)
	{
		return this.ItemData.getBetween(getData(ID), this.NameTag[0], this.NameTag[1]);
	}
	
	public String getGeneratedName(String ID)
	{
		if (this.ItemData.getBetween(getData(ID), this.GeneratedNameTag[0], this.GeneratedNameTag[1]).equalsIgnoreCase("true"))
		{
			List<String> Prefixes = getPrefixes(ID);
			List<String> Suffixes = getSuffixes(ID);
			String Name = getName(ID);
			String Seperator = getSeperator(ID);
			return Prefixes.get(new Random().nextInt(Prefixes.size())) + " " + Name + " " + Seperator + " " + Suffixes.get(new Random().nextInt(Suffixes.size()));
		}
		return null;
	}
	
	public String getGeneratedName(String ID, ItemStackHandler.Tier Tier)
	{
		if (this.ItemData.getBetween(getData(ID), this.GeneratedNameTag[0], this.GeneratedNameTag[1]).equalsIgnoreCase("true"))
		{
			String Prefix = this.ItemData.getBetween(this.ItemData.getBetween(getData(ID), this.PrefixesTag[0], this.PrefixesTag[1]), getTierTag(Tier)[0], getTierTag(Tier)[1]);
			String Suffix = this.ItemData.getBetween(this.ItemData.getBetween(getData(ID), this.SuffixesTag[0], this.SuffixesTag[1]), getTierTag(Tier)[0], getTierTag(Tier)[1]);
			List<String> Prefixes = this.ItemData.getAllBetween(Prefix, this.PrefixTag[0], this.PrefixTag[1]);
			List<String> Suffixes = this.ItemData.getAllBetween(Suffix, this.SuffixTag[0], this.SuffixTag[1]);
			String Name = getName(ID);
			String Seperator = getSeperator(ID);
			return Prefixes.get(new Random().nextInt(Prefixes.size())) + " " + Name + " " + Seperator + " " + Suffixes.get(new Random().nextInt(Suffixes.size()));
		}
		return null;
	}
	
	public boolean hasRandomNames(String ID)
	{
		if (this.ItemData.getBetween(getData(ID), this.GeneratedNameTag[0], this.GeneratedNameTag[1]).equalsIgnoreCase("true"))
		{
			return true;
		}
		return false;
	}
	
	public boolean hasMultipleMaterials(String ID)
	{
		String Data = getData(ID);
		if (Data.contains(this.MultipleMaterialsTag[0]) && Data.contains(this.MultipleMaterialsTag[1]))
		{
			if (this.ItemData.getBetween(Data, this.MultipleMaterialsTag[0], this.MultipleMaterialsTag[1]).equalsIgnoreCase("true"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public boolean isHorseArmor(String ID)
	{
		if (this.isArmor(ID))
		{
			try
			{
				String ItemData = getData(ID);
				if (ItemData.contains(this.HorseTag[0]) && ItemData.contains(this.HorseTag[1]))
				{
					if (this.ItemData.getBetween(getData(ID), this.HorseTag[0], this.HorseTag[1]).equalsIgnoreCase("true"))
					{
						return true;
					}
					return false;
				}
				return false;
			}
			catch (Exception Ex)
			{
				return false;
			}
		}
		return false;
	}
	
	public HashMap<Enchantment, Integer> getEnchantments(String ID)
	{
		HashMap<Enchantment, Integer> Enchants = new HashMap<Enchantment, Integer>();
		for (String S : this.ItemData.getAllBetween(this.ItemData.getBetween(getData(ID), this.EnchantmentsTag[0], this.EnchantmentsTag[1]), this.EnchantTag[0], this.EnchantTag[1]))
		{
			Enchantment e = Enchantment.getByName(this.ItemData.getBetween(S, this.EnchantTag[0], this.EnchantTag[1]));
			if (!Enchants.containsKey(e))
			{
				Enchants.put(e, Integer.valueOf(Integer.parseInt(this.ItemData.getBetween(S, this.EnchantmentLevelTag[0], this.EnchantmentLevelTag[1]))));
			}
		}
		return Enchants;
	}
	
	public boolean hasNameColor(String ID)
	{
		if (this.ItemData.getBetween(getData(ID), this.HasNameColorTag[0], this.HasNameColorTag[1]).equalsIgnoreCase("true"))
		{
			return true;
		}
		return false;
	}
	
	public String getSeperator(String ID)
	{
		return this.ItemData.getBetween(getData(ID), this.SeperatorTag[0], this.SeperatorTag[1]);
	}
	
	public String[] getTierTag(ItemStackHandler.Tier Tier)
	{
		switch (Tier)
		{
			case Common:
				return this.CommonTag;
			case Rare:
				return this.EpicTag;
			case Epic:
				return this.RareTag;
		}
		
		return null;
	}
	
	public ChatColor getTierColor(ItemStackHandler.Tier Tier)
	{
		switch (Tier)
		{
			case Common:
				return ChatColor.GREEN;
			case Rare:
				return ChatColor.AQUA;
			case Epic:
				return ChatColor.GOLD;
		}
		return ChatColor.WHITE;
	}
	
	public ChatColor getItemColor(String ID)
	{
		if (hasNameColor(ID))
		{
			String Data = this.ItemData.getBetween(getData(ID), this.NameColorTag[0], this.NameColorTag[1]);
			return ChatColor.valueOf(Data);
		}
		return null;
	}
	
	public List<String> getItemLore(String ID)
	{
		return this.ItemData.getAllBetween(this.ItemData.getBetween(getData(ID), this.LoreTag[0], this.LoreTag[1]), this.LoreLineTag[0], this.LoreLineTag[1]);
	}
	
	public boolean hasLore(String ID)
	{
		if (this.ItemData.getBetween(getData(ID), this.HasLoreTag[0], this.HasLoreTag[1]).equalsIgnoreCase("true"))
		{
			return true;
		}
		return false;
	}
	
	public int[] getItemDamage(String ID)
	{
		String DamageBlock = this.ItemData.getBetween(getData(ID), this.DamageTag[0], this.DamageTag[1]);
		return new int[] { Integer.parseInt(this.ItemData.getBetween(DamageBlock, this.MinDamageTag[0], this.MinDamageTag[1])), Integer.parseInt(this.ItemData.getBetween(DamageBlock, this.MaxDamageTag[0], this.MaxDamageTag[1])) };
	}
	
	public int[] getItemDamage(String ID, ItemStackHandler.Tier Tier)
	{
		String[] iTearTag = getTierTag(Tier);
		String DamageBlock = this.ItemData.getBetween(getData(ID), this.DamageTag[0], this.DamageTag[1]);
		String TieredDamageBlock = this.ItemData.getBetween(DamageBlock, iTearTag[0], iTearTag[1]);
		return new int[] { Integer.parseInt(this.ItemData.getBetween(TieredDamageBlock, this.MinDamageTag[0], this.MinDamageTag[1])), Integer.parseInt(this.ItemData.getBetween(TieredDamageBlock, this.MaxDamageTag[0], this.MaxDamageTag[1])) };
	}
	
	public int getMinDamage(String ID)
	{
		return getItemDamage(ID)[0];
	}
	
	public int getMinDamage(String ID, ItemStackHandler.Tier Tier)
	{
		return getItemDamage(ID, Tier)[0];
	}
	
	public int getMaxDamage(String ID)
	{
		return getItemDamage(ID)[1];
	}
	
	public int getMaxDamage(String ID, ItemStackHandler.Tier Tier)
	{
		return getItemDamage(ID)[1];
	}
	
	public boolean isArmor(String ID)
	{
		if (this.ItemData.getBetween(getData(ID), this.ArmorTag[0], this.ArmorTag[1]).equalsIgnoreCase("true"))
		{
			return true;
		}
		return false;
	}
	
	public int getHealthMin(String ID)
	{
		return Integer.parseInt(this.ItemData.getBetween(this.getData(ID), this.HealthMinTag[0], this.HealthMinTag[1]));
	}
	
	public int getHealthMax(String ID)
	{
		return Integer.parseInt(this.ItemData.getBetween(this.getData(ID), this.HealthMaxTag[0], this.HealthMaxTag[1]));
	}
	
	public CustomItemStack getArmor(String ID)
	{
		CustomItemStack Stack = new CustomItemStack(this.getMaterial(ID));
		
		if (hasRandomNames(ID))
		{
			Stack.setName(ChatColor.AQUA + getGeneratedName(ID));
		}
		else
		{
			Stack.setName(ChatColor.translateAlternateColorCodes('&', getName(ID)));
		}
		
		if (hasLore(ID))
		{
			if (getItemLore(ID).size() > 0)
			{
				for (String S : getItemLore(ID))
				{
					Stack.addLore(new String[] { ChatColor.translateAlternateColorCodes('&', S) });
				}
			}
		}
		
		Stack.addLore(new String[] { ChatColor.RED + "Increases Max HP by " + this.Calculate_Health(new int[] { this.getHealthMin(ID), this.getHealthMax(ID) }) + "!" });
		
		if (hasEffects(ID))
		{
			for (String S : getEffectLoreLines(ID))
			{
				Stack.addLore(new String[] { ChatColor.YELLOW + S });
			}
		}
		return Stack;
	}
	
	public int Calculate_Health(Tier Tier)
	{
		int Minimum = 0;
		int Maximum = 0;
		if (Tier == com.caved_in.TotalWarItems.Handlers.ItemStackHandler.Tier.Common)
		{
			Minimum = 15;
			Maximum = 45;
		}
		else if (Tier == com.caved_in.TotalWarItems.Handlers.ItemStackHandler.Tier.Rare)
		{
			Minimum = 39;
			Maximum = 83;
		}
		else if (Tier == com.caved_in.TotalWarItems.Handlers.ItemStackHandler.Tier.Epic)
		{
			Minimum = 65;
			Maximum = 127;
		}
		
		int MinD = 1 + ((new Random().nextInt(Minimum + 1) + new Random().nextInt(Minimum + 1)) / 2);
		int MaxD = 1 + ((new Random().nextInt(Maximum + 1) + new Random().nextInt(Maximum + 1)) / 2);
		return (MinD + MaxD) / 2;
	}
	
	public int Calculate_Health(int Min, int Max)
	{
		int Minimum = Min;
		int Maximum = Max;
		int MinD = 1 + ((new Random().nextInt(Minimum + 1) + new Random().nextInt(Minimum + 1)) / 2);
		int MaxD = 1 + ((new Random().nextInt(Maximum + 1) + new Random().nextInt(Maximum + 1)) / 2);
		return (MinD + MaxD) / 2;
	}
	
	public int Calculate_Health(int[] Values)
	{
		int Minimum = Values[0];
		int Maximum = Values[1];
		int MinD = 1 + ((new Random().nextInt(Minimum + 1) + new Random().nextInt(Minimum + 1)) / 2);
		int MaxD = 1 + ((new Random().nextInt(Maximum + 1) + new Random().nextInt(Maximum + 1)) / 2);
		return (MinD + MaxD) / 2;
	}
	
	public CustomItemStack getCustomItemStack(String ID)
	{
		CustomItemStack Stack;
		if (hasMultipleMaterials(ID))
		{
			Stack = new CustomItemStack(getMaterials(ID).get(new Random().nextInt(getMaterials(ID).size())));
		}
		else
		{
			Stack = new CustomItemStack(getMaterial(ID));
		}
		
		if (hasRandomNames(ID))
		{
			Stack.setName(ChatColor.YELLOW + getGeneratedName(ID));
		}
		else
		{
			Stack.setName(ChatColor.translateAlternateColorCodes('&', getName(ID)));
		}
		
		if (hasLore(ID))
		{
			if (getItemLore(ID).size() > 0)
			{
				for (String S : getItemLore(ID))
				{
					Stack.addLore(new String[] { ChatColor.translateAlternateColorCodes('&', S) });
				}
			}
		}
		if (!isArmor(ID))
		{
			int[] Damages = Calculate_Damage(getItemDamage(ID));
			Stack.addLore(new String[] { ChatColor.RED + "Deals " + Damages[0] + " to " + Damages[1] + " damage!" });
		}
		else
		{
			if (!isHorseArmor(ID))
			{
				Stack.addLore(new String[] { ChatColor.RED + this.HpBonus[0] + this.Calculate_Health(new int[] { this.getHealthMin(ID), this.getHealthMax(ID) }) + this.HpBonus[1] });
			}
		}
		if (hasEffects(ID))
		{
			for (String S : getEffectLoreLines(ID))
			{
				Stack.addLore(new String[] { ChatColor.YELLOW + S });
			}
		}
		return Stack;
	}
	
	public double getTierDamageBonus(ItemStackHandler.Tier Tier)
	{
		switch (Tier)
		{
			case Common:
				return new Random().nextDouble() * new Random().nextInt(2);
			case Rare:
				return new Random().nextDouble() * new Random().nextInt(9);
			case Epic:
				return new Random().nextDouble() * new Random().nextInt(5);
		}
		
		return 0.0D;
	}
	
	public CustomItemStack getCustomItemStack(String ID, ItemStackHandler.Tier Tier)
	{
		CustomItemStack Stack;
		if (hasMultipleMaterials(ID))
		{
			Stack = new CustomItemStack(getMaterials(ID).get(new Random().nextInt(getMaterials(ID).size())));
		}
		else
		{
			Stack = new CustomItemStack(getMaterial(ID));
		}
		if (hasRandomNames(ID))
		{
			Stack.setName(getTierColor(Tier) + getGeneratedName(ID, Tier));
		}
		else
		{
			Stack.setName(getTierColor(Tier) + getName(ID));
		}
		
		if (hasLore(ID))
		{
			if (getItemLore(ID).size() > 0)
			{
				for (String S : getItemLore(ID))
				{
					Stack.addLore(new String[] { ChatColor.translateAlternateColorCodes('&', S) });
				}
			}
		}
		if (!isArmor(ID))
		{
			int[] Damages = Calculate_Damage(getItemDamage(ID, Tier));
			Stack.addLore(new String[] { ChatColor.RED + "Deals " + Damages[0] + " to " + Damages[1] + " damage!" });
		}
		else
		{
			if (!isHorseArmor(ID))
			{
				Stack.addLore(new String[] { ChatColor.RED + this.HpBonus[0] + this.Calculate_Health(new int[] { this.getHealthMin(ID), this.getHealthMax(ID) }) + this.HpBonus[1] });
			}
		}
		if (hasEffects(ID))
		{
			for (String S : getEffectLoreLines(ID))
			{
				Stack.addLore(new String[] { ChatColor.YELLOW + S });
			}
		}
		return Stack;
	}
	
	public CustomItemStack getCustomItemStack(String ID, ItemStackHandler.Tier Tier, Material Material)
	{
		CustomItemStack Stack = new CustomItemStack(Material);
		if (hasRandomNames(ID))
		{
			Stack.setName(getTierColor(Tier) + getGeneratedName(ID, Tier));
		}
		else
		{
			Stack.setName(getTierColor(Tier) + getName(ID));
		}
		
		if (hasLore(ID))
		{
			if (getItemLore(ID).size() > 0)
			{
				for (String S : getItemLore(ID))
				{
					Stack.addLore(new String[] { ChatColor.translateAlternateColorCodes('&', S) });
				}
			}
		}
		if (!isArmor(ID))
		{
			int[] Damages = Calculate_Damage(getItemDamage(ID, Tier));
			Stack.addLore(new String[] { ChatColor.RED + "Deals " + Damages[0] + " to " + Damages[1] + " damage!" });
		}
		else
		{
			if (!isHorseArmor(ID))
			{
				Stack.addLore(new String[] { ChatColor.RED + this.HpBonus[0] + this.Calculate_Health(new int[] { this.getHealthMin(ID), this.getHealthMax(ID) }) + this.HpBonus[1] });
			}
		}
		if (hasEffects(ID))
		{
			for (String S : getEffectLoreLines(ID))
			{
				Stack.addLore(new String[] { ChatColor.YELLOW + S });
			}
		}
		return Stack;
	}
	
	public List<String> getEffectLoreLines(String ID)
	{
		List<String> LinesOfLore = new ArrayList<String>();
		for (Entry<String, Integer> Pair : getEffects(ID).entrySet())
		{
			if (new Random().nextInt(100) <= Pair.getValue().intValue())
			{
				String Text = "Has a " + (new Random().nextInt(6) + new Random().nextInt(6) + new Random().nextInt(11) + 1) + "% Chance to " + Pair.getKey() + "!";
				LinesOfLore.add(Text);
			}
		}
		return LinesOfLore;
	}
	
	public String getDamageLore(Tier Tier)
	{
		int Damage = this.Calculate_Health(Tier);
		int[] Damages = new int[] { Damage / 2, Damage };
		int[] Damages2 = this.Calculate_Damage(Damages);
		return ChatColor.RED + "Deals " + Damages2[0] + " to " + Damages2[1] + " damage!";
	}
	
	public HashMap<String, Integer> getEffects(String ID)
	{
		HashMap<String, Integer> EffectsWithChance = new HashMap<String, Integer>();
		if (hasEffects(ID))
		{
			List<String> EffectBlocks = this.ItemData.getAllBetween(this.ItemData.getBetween(getData(ID), this.EffectsTag[0], this.EffectsTag[1]), this.EffectTag[0], this.EffectTag[1]);
			for (String S : EffectBlocks)
			{
				EffectsWithChance.put(this.ItemData.getBetween(S, this.EffectNameTag[0], this.EffectNameTag[1]), Integer.valueOf(Integer.parseInt(this.ItemData.getBetween(S, this.EffectGenChanceTag[0], this.EffectGenChanceTag[1]))));
			}
			return EffectsWithChance;
		}
		return null;
	}
	
	/**
	 * Does the item have effects?
	 */
	public boolean hasEffects(String ID)
	{
		if (this.ItemData.getBetween(getData(ID), this.HasEffectsTag[0], this.HasEffectsTag[1]).equalsIgnoreCase("true"))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * @Returns Array of Damages which are randomized from the input of max and
	 *          min
	 */
	public int[] Calculate_Damage(int Minimum, int Maximum)
	{
		int MinD = 1 + ((new Random().nextInt(Minimum + 1) + new Random().nextInt(Minimum + 1)) / 2);
		int MaxD = 1 + ((new Random().nextInt(Maximum + 1) + new Random().nextInt(Maximum + 1)) / 2);
		if (MaxD < MinD)
		{
			return new int[] { MaxD, MinD };
		}
		
		return new int[] { MinD, MaxD };
	}
	
	public int[] Calculate_Damage(int[] Damages)
	{
		return Calculate_Damage(Damages[0], Damages[1]);
	}
	
	public Tier getTier(String Input)
	{
		if (Input.equalsIgnoreCase("common"))
		{
			return Tier.Common;
		}
		else if (Input.equalsIgnoreCase("rare"))
		{
			return Tier.Rare;
		}
		else if (Input.equalsIgnoreCase("epic"))
		{
			return Tier.Epic;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Reload the Items file
	 */
	@Deprecated
	public void Reload()
	{
		this.ItemData = new TextHandler(this.DataLocation);
	}
}