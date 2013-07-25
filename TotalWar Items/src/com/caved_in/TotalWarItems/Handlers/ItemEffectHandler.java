package com.caved_in.TotalWarItems.Handlers;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemEffectHandler
{
	public static List<String> ValidEffectNames = Arrays.asList(new String[] { "absorb Hp", "slow an enemy", "cause an explosion", "do 2x damage from behind", "poison an enemy" });
	
	public static int HandleEffect(Player Damager, LivingEntity Damaged, String EffectName, int EventDamage)
	{
		Damageable Player = (Damageable) Damager;
		switch (EffectName.toLowerCase())
		{
			case "absorb hp":
				if (Player.getHealth() < Player.getMaxHealth())
				{
					int Restored = new Random().nextInt(EventDamage) + 1;
					if ((Player.getHealth() + Restored) < Player.getMaxHealth())
					{
						Damager.setHealth(Player.getHealth() + Restored);
						return 1;
					}
					return 1;
				}
				return 1;
			case "slow an enemy":
				Damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 50 + new Random().nextInt(110), 1));
				return 1;
			case "cause an explosion":
				Damaged.getWorld().createExplosion(Damaged.getLocation().getX(), Damaged.getLocation().getY(), Damaged.getLocation().getZ(), 0.05F, false, false);
				return 1;
			case "do 2x damage from behind":
				if (isBehind(Damager, Damaged))
				{
					return 2;
				}
				return 1;
			case "do 3x damage at the cost of 10% health":
				int Health = (int) Player.getHealth();
				int HealthSac = (int) (Health * 0.1);
				Damager.setHealth(Player.getHealth() - HealthSac);
				return 3;
			case "set an enemy on fire":
				int FlameLength = 25 + new Random().nextInt(28);
				Damaged.setFireTicks(FlameLength);
				return 1;
			case "poison an enemy":
				Damaged.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 50 + new Random().nextInt(110), 1));
				return 1;
			default:
				return 1;
		}
	}
	
	public static boolean isBehind(LivingEntity Attacker, LivingEntity Attacked)
	{
		double dreg = Math.abs(Math.toDegrees(Attacker.getEyeLocation().getDirection().angle(Attacked.getLocation().getDirection())));
		if (dreg < 45.0D)
		{
			return true;
		}
		return false;
	}
}