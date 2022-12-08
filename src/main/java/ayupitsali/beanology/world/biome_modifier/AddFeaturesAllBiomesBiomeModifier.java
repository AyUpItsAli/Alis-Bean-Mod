package ayupitsali.beanology.world.biome_modifier;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record AddFeaturesAllBiomesBiomeModifier(HolderSet<PlacedFeature> features, Decoration step) implements BiomeModifier {

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD) {
            BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
            this.features.forEach(feature -> generationSettings.addFeature(this.step, feature));
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return ModBiomeModifiers.ADD_FEATURES_ALL_BIOMES_CODEC.get();
    }
}
