package com.calopsita;

import com.calopsita.entity.CalopsitaEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.SpawnEggItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalopsitaMod implements ModInitializer {
	public static final String MOD_ID = "calopsita";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EntityType<CalopsitaEntity> CALOPSITA = Registry.register(
			BuiltInRegistries.ENTITY_TYPE,
			id("calopsita"),
			EntityType.Builder.of(CalopsitaEntity::new, MobCategory.CREATURE)
					.sized(0.5F, 0.9F)
					.build("calopsita")
	);

	public static final Item CALOPSITA_SPAWN_EGG = Registry.register(
			BuiltInRegistries.ITEM,
			id("calopsita_spawn_egg"),
			new SpawnEggItem(CALOPSITA, 0xA8D8EA, 0xFDF6E3, new Item.Properties())
	);

	@Override
	public void onInitialize() {
		FabricDefaultAttributeRegistry.register(CALOPSITA, CalopsitaEntity.createAttributes());

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS)
				.register(entries -> entries.accept(CALOPSITA_SPAWN_EGG));

		LOGGER.info("Calopsita loaded!");
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
