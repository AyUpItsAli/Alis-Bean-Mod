package ayupitsali.beanology.world.biome_modifier;

import ayupitsali.beanology.Beanology;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Beanology.MOD_ID);

    public static final RegistryObject<Codec<AddFeaturesAllBiomesBiomeModifier>> ADD_FEATURES_ALL_BIOMES_CODEC = BIOME_MODIFIER_SERIALIZERS.register("add_features_all_biomes",
            () -> RecordCodecBuilder.create(builder -> builder.group(
                    PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(AddFeaturesAllBiomesBiomeModifier::features),
                    Decoration.CODEC.fieldOf("step").forGetter(AddFeaturesAllBiomesBiomeModifier::step)
            ).apply(builder, AddFeaturesAllBiomesBiomeModifier::new)));

    public static final RegistryObject<Codec<ReplaceFeaturesBiomeModifier>> REPLACE_FEATURES_CODEC = BIOME_MODIFIER_SERIALIZERS.register("replace_features",
            () -> RecordCodecBuilder.create(builder -> builder.group(
                    PlacedFeature.LIST_CODEC.fieldOf("replace").forGetter(ReplaceFeaturesBiomeModifier::replace),
                    PlacedFeature.LIST_CODEC.fieldOf("with").forGetter(ReplaceFeaturesBiomeModifier::with),
                    Decoration.CODEC.fieldOf("step").forGetter(ReplaceFeaturesBiomeModifier::step)
            ).apply(builder, ReplaceFeaturesBiomeModifier::new)));

    public static void register(IEventBus eventBus) {
        BIOME_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
