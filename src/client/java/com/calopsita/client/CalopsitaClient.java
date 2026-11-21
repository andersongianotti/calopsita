package com.calopsita.client;

import com.calopsita.CalopsitaMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class CalopsitaClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(CalopsitaMod.CALOPSITA, CalopsitaRenderer::new);
	}
}
