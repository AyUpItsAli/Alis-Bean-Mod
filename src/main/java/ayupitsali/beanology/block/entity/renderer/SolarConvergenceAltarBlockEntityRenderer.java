package ayupitsali.beanology.block.entity.renderer;

import ayupitsali.beanology.Beanology;
import ayupitsali.beanology.block.entity.SolarConvergenceAltarBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;

public class SolarConvergenceAltarBlockEntityRenderer implements BlockEntityRenderer<SolarConvergenceAltarBlockEntity> {
    private static final ResourceLocation SUN_BEAM = new ResourceLocation(Beanology.MOD_ID, "textures/entity/solar_convergence_sun_beam.png");
    private static final ResourceLocation MOON_BEAM = new ResourceLocation(Beanology.MOD_ID, "textures/entity/solar_convergence_moon_beam.png");

    public SolarConvergenceAltarBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(SolarConvergenceAltarBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        Level level = pBlockEntity.getLevel();
        int lightLevel = getLightLevel(level, pBlockEntity.getBlockPos());
        renderItem(pBlockEntity.getStackToRender(), lightLevel, pPoseStack, pBufferSource);
        int beamProgress = pBlockEntity.getBeamProgress();
        if (beamProgress > 0) {
            ResourceLocation beamLocation = level.getDayTime() >= 13000 ? MOON_BEAM : SUN_BEAM;
            renderSolarBeam(beamLocation, beamProgress, level.getGameTime(), pPoseStack, pBufferSource, pPartialTick);
        }
    }

    private int getLightLevel(Level pLevel, BlockPos pPos) {
        int blockLight = pLevel.getBrightness(LightLayer.BLOCK, pPos);
        int skyLight = pLevel.getBrightness(LightLayer.SKY, pPos);
        return LightTexture.pack(blockLight, skyLight);
    }

    private void renderItem(ItemStack stack, int pLightLevel, PoseStack pPoseStack, MultiBufferSource pBufferSource) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.265f, 0.5f); // 0.26
        pPoseStack.scale(0.4f, 0.4f, 0.4f); // 0.375
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(-90));
        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.GUI, pLightLevel,
                OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);
        pPoseStack.popPose();
    }

    private void renderSolarBeam(ResourceLocation beamLocation, int beamProgress, long gameTime, PoseStack pPoseStack,
                                 MultiBufferSource pBufferSource, float pPartialTick) {
        float progressFactor = (float) beamProgress / (float) SolarConvergenceAltarBlockEntity.TOTAL_BEAM_PROGRESS;
        float radius = 0.08F * progressFactor;
        float glowRadius = 0.25F * progressFactor;
        if (radius > 0) {
            BeaconRenderer.renderBeaconBeam(pPoseStack, pBufferSource, beamLocation, pPartialTick, 1.0F, gameTime,
                    0, BeaconRenderer.MAX_RENDER_Y, DyeColor.WHITE.getTextureDiffuseColors(), radius, glowRadius);
        }
    }

    @Override
    public boolean shouldRenderOffScreen(SolarConvergenceAltarBlockEntity pBlockEntity) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 256;
    }

    @Override
    public boolean shouldRender(SolarConvergenceAltarBlockEntity pBlockEntity, Vec3 pCameraPos) {
        return Vec3.atCenterOf(pBlockEntity.getBlockPos()).multiply(1.0D, 0.0D, 1.0D)
                .closerThan(pCameraPos.multiply(1.0D, 0.0D, 1.0D), this.getViewDistance());
    }
}
