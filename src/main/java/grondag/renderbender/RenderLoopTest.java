/*
 * Copyright © Contributing Authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional copyright and licensing notices may apply for content that was
 * included from other projects. For more information, see ATTRIBUTION.md.
 */

package grondag.renderbender;

import io.vram.frex.api.renderloop.BlockOutlineListener;
import io.vram.frex.api.renderloop.BlockOutlinePreListener;
import io.vram.frex.api.renderloop.DebugRenderListener;
import io.vram.frex.api.renderloop.EntityRenderPostListener;
import io.vram.frex.api.renderloop.EntityRenderPreListener;
import io.vram.frex.api.renderloop.FrustumSetupListener;
import io.vram.frex.api.renderloop.TranslucentPostListener;
import io.vram.frex.api.renderloop.WorldRenderLastListener;
import io.vram.frex.api.renderloop.WorldRenderPostListener;
import io.vram.frex.api.renderloop.WorldRenderStartListener;

import grondag.renderbender.init.BasicBlocks;

public class RenderLoopTest {
	private static boolean active = true;
	private static boolean firstBlockOutline = true;

	static void init() {
		RenderBender.LOG.info("Registering FREX event test callbacks.");
		RenderBender.LOG.info("Should see the following messages 1X in this order when world is loaded.");
		RenderBender.LOG.info("    WorldRenderStartListener");
		RenderBender.LOG.info("    FrustumSetupListener");
		RenderBender.LOG.info("    EntityRenderPreListener");
		RenderBender.LOG.info("    EntityRenderPostListener");
		RenderBender.LOG.info("    DebugRenderListener");
		RenderBender.LOG.info("    TranslucentPostListener");
		RenderBender.LOG.info("    WorldRenderLastListener");
		RenderBender.LOG.info("    WorldRenderPostListener");

		WorldRenderStartListener.register(c -> {
			if (active) {
				RenderBender.LOG.info("WorldRenderStartListener");
			}
		});

		FrustumSetupListener.register(c -> {
			if (active) {
				RenderBender.LOG.info("FrustumSetupListener");
			}
		});

		EntityRenderPreListener.register(c -> {
			if (active) {
				RenderBender.LOG.info("EntityRenderPreListener");
			}
		});

		EntityRenderPostListener.register(c -> {
			if (active) {
				RenderBender.LOG.info("EntityRenderPostListener");
			}
		});

		BlockOutlinePreListener.register((c, h) -> {
			if (firstBlockOutline) {
				RenderBender.LOG.info("BlockOutlinePreListener");
				firstBlockOutline = false;
			}

			return true;
		});

		BlockOutlineListener.register((c, b) -> {
			return b.blockState().getBlock() != BasicBlocks.GLOW_BLOCK_DYNAMIC;
		});

		DebugRenderListener.register(c -> {
			if (active) {
				RenderBender.LOG.info("DebugRenderListener");
			}
		});

		TranslucentPostListener.register(c -> {
			if (active) {
				RenderBender.LOG.info("TranslucentPostListener");
			}
		});

		WorldRenderLastListener.register(c -> {
			if (active) {
				RenderBender.LOG.info("WorldRenderLastListener");
			}
		});

		WorldRenderPostListener.register(c -> {
			if (active) {
				RenderBender.LOG.info("WorldRenderPostListener");
				active = false;
			}
		});
	}
}
