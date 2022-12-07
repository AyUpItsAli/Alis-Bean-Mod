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

public record ReplaceFeaturesBiomeModifier(HolderSet<PlacedFeature> replace, HolderSet<PlacedFeature> with, Decoration step) implements BiomeModifier {

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
        if (phase == Phase.REMOVE) {
            generationSettings.getFeatures(step).removeIf(this.replace::contains);
        } else if (phase == Phase.ADD) {
            this.with.forEach(feature -> generationSettings.addFeature(this.step, feature));
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return ModBiomeModifiers.REPLACE_FEATURES_CODEC.get();
    }
}
