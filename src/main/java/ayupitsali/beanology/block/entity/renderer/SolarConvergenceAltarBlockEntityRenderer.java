package ayupitsali.beanology.block.entity.renderer;

import ayupitsali.beanology.block.entity.SolarConvergenceAltarBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class SolarConvergenceAltarBlockEntityRenderer implements BlockEntityRenderer<SolarConvergenceAltarBlockEntity> {
    public SolarConvergenceAltarBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(SolarConvergenceAltarBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemStack stack = pBlockEntity.getStackToRender();
        int lightLevel = getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos());
        renderItem(stack, lightLevel, pPoseStack, pBufferSource);
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
}
