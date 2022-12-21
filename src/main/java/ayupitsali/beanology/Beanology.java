package ayupitsali.beanology;

import ayupitsali.beanology.block.ModBlocks;
import ayupitsali.beanology.block.entity.ModBlockEntities;
import ayupitsali.beanology.block.entity.renderer.SolarConvergenceAltarBlockEntityRenderer;
import ayupitsali.beanology.item.ModItems;
import ayupitsali.beanology.recipe.ModRecipes;
import ayupitsali.beanology.world.biome_modifier.ModBiomeModifiers;
import ayupitsali.beanology.world.feature.ModFeatures;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Beanology.MOD_ID)
public class Beanology {
    public static final String MOD_ID = "beanology";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Beanology() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModFeatures.register(modEventBus);
        ModBiomeModifiers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("IT'S BEAN TIME!");
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.SOLAR_CONVERGENCE_ALTAR.get(), SolarConvergenceAltarBlockEntityRenderer::new);
        }
    }
}
