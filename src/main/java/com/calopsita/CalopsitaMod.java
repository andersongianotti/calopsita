package com.calopsita;

import com.calopsita.entity.CalopsitaEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.parrot.Parrot;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.TypedEntityData;
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
					.build(ResourceKey.create(Registries.ENTITY_TYPE, id("calopsita")))
	);

	public static final Item CALOPSITA_SPAWN_EGG = spawnEgg("calopsita_spawn_egg", Parrot.Variant.YELLOW_BLUE);

	private static Item spawnEgg(String name, Parrot.Variant variant) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id(name));
		return Registry.register(
				BuiltInRegistries.ITEM,
				id(name),
				new SpawnEggItem(new Item.Properties()
						.setId(key)
						.spawnEgg(CALOPSITA)
						.component(DataComponents.ENTITY_DATA, eggData(variant)))
		);
	}

	private static TypedEntityData<EntityType<?>> eggData(Parrot.Variant variant) {
		CompoundTag components = new CompoundTag();
		components.putString("minecraft:parrot_variant", variant.getSerializedName());
		CompoundTag tag = new CompoundTag();
		tag.put("components", components);
		return TypedEntityData.of((EntityType<?>) CALOPSITA, tag);
	}

	@Override
	public void onInitialize() {
		FabricDefaultAttributeRegistry.register(CALOPSITA, CalopsitaEntity.createAttributes());

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS)
				.register(output -> {
					output.accept(CALOPSITA_SPAWN_EGG);
				});

		LOGGER.info("Calopsita loaded!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
