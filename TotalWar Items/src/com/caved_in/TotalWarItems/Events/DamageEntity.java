package com.caved_in.TotalWarItems.Events;

import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.caved_in.TotalWar;
import com.caved_in.TotalWarItems.TotalWarItems;
import com.caved_in.TotalWarItems.Handlers.ItemEffectHandler;

public class DamageEntity implements Listener
{
	
	public DamageEntity(TotalWarItems Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler
	public void EntityDamagedByEntity(EntityDamageByEntityEvent Event)
	{
		
		if (Event.getEntity().hasMetadata("NPC"))
		{
			Event.setCancelled(true);
			return;
		}
		
		if ((Event.getDamager() instanceof Player))
		{
			Player Damager = (Player) Event.getDamager();
			if (Damager.getGameMode() == GameMode.CREATIVE)
			{
				Event.setCancelled(true);
				return;
			}
		}
		if ((Event.getDamager() instanceof Player) || (Event.getDamager() instanceof Arrow))
		{
			Player Player;
			boolean ArrowShot = false;
			if (Event.getEntity() instanceof LivingEntity)
			{
				if (Event.getDamager() instanceof Arrow)
				{
					Arrow Arrow = (Arrow) Event.getDamager();
					if (Arrow.getShooter() instanceof Player)
					{
						Player = (Player) Arrow.getShooter();
						ArrowShot = true;
					}
					else
					{
						return;
					}
				}
				else
				{
					Player = (Player) Event.getDamager();
				}
				// Player = (Player) Event.getDamager();
				if (Event.getEntity() instanceof Player)
				{
					if (TotalWar.PlayerHandler.isSameFaction(Player, (Player) Event.getEntity()))
					{
						Event.setCancelled(true);
						return;
					}
				}
				
				double eDamage = 0;
				if ((Player.getItemInHand() != null) && (Player.getItemInHand().hasItemMeta()))
				{
					if (Player.getItemInHand().getType() == Material.BOW && ArrowShot == false)
					{
						Event.setCancelled(true);
						return;
					}
					ItemStack ItemInHand = Player.getItemInHand();
					ItemMeta ItemMeta = ItemInHand.getItemMeta();
					if (ItemMeta.hasLore())
					{
						for (String S : ItemMeta.getLore())
						{
							String LoreLine = S.toLowerCase();
							if ((LoreLine.contains("deals")) && (LoreLine.contains("to")) && (LoreLine.contains("damage!")))
							{
								int DamageMin = Integer.parseInt(StringUtils.substringBetween(S.toLowerCase(), "deals ", " to "));
								int DamageMax = Integer.parseInt(StringUtils.substringBetween(S.toLowerCase(), " to ", " damage!"));
								int dRange = DamageMax - DamageMin;
								eDamage = (Math.round(DamageMin + new Random().nextInt(dRange + 1)));
							}
							else if (LoreLine.contains("% chance to "))
							{
								String EffectName = StringUtils.substringBetween(S, "% Chance to ", "!");
								int ChanceOnHit = Integer.parseInt(StringUtils.substringBetween(S, "Has a ", "% Chance to "));
								if (new Random().nextInt(100) <= ChanceOnHit)
								{
									int Result = ItemEffectHandler.HandleEffect(Player, (LivingEntity) Event.getEntity(), EffectName, (int) Math.round(eDamage));
									if (Result > 1)
									{
										eDamage = ((int) Math.round(eDamage * Result));
										// Player.playSound(Player.getLocation(),
										// Sound.HURT_FLESH, 1.0F, 1.0F);
									}
								}
							}
						}
						Event.setDamage(eDamage);
					}
				}
			}
		}
	}
	
	public void Log(String Text)
	{
		Bukkit.getLogger().info("[TotalWarItems] " + Text);
	}
}