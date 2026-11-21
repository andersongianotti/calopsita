package com.calopsita.entity;

import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.parrot.Parrot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class CalopsitaEntity extends Parrot {

	private static final Parrot.Variant[] COLORWAYS = {
			Parrot.Variant.GRAY, Parrot.Variant.YELLOW_BLUE, Parrot.Variant.GREEN
	};

	public CalopsitaEntity(EntityType<CalopsitaEntity> entityType, Level level) {
		super(entityType, level);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Parrot.createAttributes();
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
										EntitySpawnReason reason, SpawnGroupData data) {
		Parrot.Variant requested = getVariant();
		SpawnGroupData result = super.finalizeSpawn(level, difficulty, reason, data);
		Parrot.Variant chosen = isColorway(requested)
				? requested
				: Util.getRandom(COLORWAYS, level.getRandom());
		setComponent(DataComponents.PARROT_VARIANT, chosen);
		return result;
	}

	private static boolean isColorway(Parrot.Variant variant) {
		for (Parrot.Variant v : COLORWAYS) {
			if (v == variant) return true;
		}
		return false;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.is(Items.WHEAT_SEEDS)
				|| stack.is(Items.MELON_SEEDS)
				|| stack.is(Items.PUMPKIN_SEEDS)
				|| stack.is(Items.BEETROOT_SEEDS);
	}
}
