package ayupitsali.beanology.world.feature;

import ayupitsali.beanology.Beanology;
import ayupitsali.beanology.block.ModBlocks;
import ayupitsali.beanology.block.crop.BlackEyedBeanCropBlock;
import com.epherical.croptopia.blocks.CroptopiaCropBlock;
import com.epherical.croptopia.register.Content;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Beanology.MOD_ID);
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Beanology.MOD_ID);

    // Same configuration as "croptopia.registry.GeneratorRegistry.RANDOM_CROP" but with Beanology crops added to it.
    // beanology:random_crop will replace croptopia:random_crop during biome modification.
    public static final RegistryObject<ConfiguredFeature<?, ?>> RANDOM_CROP = CONFIGURED_FEATURES.register("random_crop",
            () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(3,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                            .add(Content.ARTICHOKE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.ASPARAGUS.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.BARLEY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.BASIL.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                            .add(Content.BELLPEPPER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.BLACKBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.BLUEBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.BROCCOLI.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.CABBAGE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.CANTALOUPE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.CAULIFLOWER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.CELERY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.CORN.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.CRANBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.CUCUMBER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.CURRANT.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.EGGPLANT.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                            .add(Content.ELDERBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.GARLIC.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                            .add(Content.GINGER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.GRAPE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.GREENONION.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                            .add(Content.HONEYDEW.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                            .add(Content.HOPS.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.KALE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.KIWI.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.LEEK.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.LETTUCE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.MUSTARD.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.OAT.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.OLIVE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.ONION.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                            .add(Content.PEANUT.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                            .add(Content.CHILE_PEPPER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.PINEAPPLE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                            .add(Content.RADISH.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.RASPBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.RHUBARB.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                            .add(Content.RICE.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                            .add(Content.RUTABAGA.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.SAGUARO.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.SPINACH.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.SQUASH.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.STRAWBERRY.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.SWEETPOTATO.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.TOMATILLO.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.TOMATO.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.TURMERIC.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.TURNIP.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                            .add(Content.YAM.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.ZUCCHINI.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.VANILLA.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 20)
                            .add(Content.PEPPER.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.TEA_LEAVES.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            // Beans crops (including Beanology beans)
                            .add(Content.BLACKBEAN.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(ModBlocks.BLACK_EYED_BEAN_CROP.get().defaultBlockState().setValue(BlackEyedBeanCropBlock.AGE, 7), 10)
                            .add(ModBlocks.BROAD_BEAN_CROP.get().defaultBlockState().setValue(BlackEyedBeanCropBlock.AGE, 7), 10)
                            .add(Content.COFFEE_BEANS.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(Content.GREENBEAN.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .add(ModBlocks.HARICOT_BEAN_CROP.get().defaultBlockState().setValue(BlackEyedBeanCropBlock.AGE, 7), 10)
                            .add(ModBlocks.KIDNEY_BEAN_CROP.get().defaultBlockState().setValue(BlackEyedBeanCropBlock.AGE, 7), 10)
                            .add(Content.SOYBEAN.asBlock().defaultBlockState().setValue(CroptopiaCropBlock.AGE, 7), 10)
                            .build()))))));

    // TODO:
    // -- Add "croptopia:has_crop" biome tags for Beanology bean crops
    // -- Override "croptopia:has_crop" biome tags for Croptopia bean crops?

    public static final RegistryObject<PlacedFeature> RANDOM_CROP_PLACED = PLACED_FEATURES.register("random_crop",
            () -> new PlacedFeature(RANDOM_CROP.getHolder().orElseThrow(), List.of(
                    CountPlacement.of(3),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome())));

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
        PLACED_FEATURES.register(eventBus);
    }
}
