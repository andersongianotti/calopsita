package com.calopsita.client;

import com.calopsita.CalopsitaMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ParrotRenderer;
import net.minecraft.client.renderer.entity.state.ParrotRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.parrot.Parrot;

public class CalopsitaRenderer extends ParrotRenderer {

	private static final Identifier NORMAL = CalopsitaMod.id("textures/entity/calopsita_normal");
	private static final Identifier WHITEFACE = CalopsitaMod.id("textures/entity/calopsita_whiteface");
	private static final Identifier LUTINO = CalopsitaMod.id("textures/entity/calopsita_lutino");

	public CalopsitaRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public Identifier getTextureLocation(ParrotRenderState state) {
		Parrot.Variant variant = state.variant != null ? state.variant : Parrot.Variant.GRAY;
		return switch (variant) {
			case YELLOW_BLUE, BLUE -> WHITEFACE;
			case GREEN -> LUTINO;
			default -> NORMAL;
		};
	}
}
