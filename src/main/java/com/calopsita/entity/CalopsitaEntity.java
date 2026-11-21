package com.calopsita.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.parrot.Parrot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class CalopsitaEntity extends Parrot {

	public CalopsitaEntity(EntityType<CalopsitaEntity> entityType, Level level) {
		super(entityType, level);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Parrot.createAttributes();
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.is(Items.WHEAT_SEEDS)
				|| stack.is(Items.MELON_SEEDS)
				|| stack.is(Items.PUMPKIN_SEEDS)
				|| stack.is(Items.BEETROOT_SEEDS);
	}
}
