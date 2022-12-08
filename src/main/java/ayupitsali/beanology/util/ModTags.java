package ayupitsali.beanology.util;

import ayupitsali.beanology.Beanology;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModTags {
    public static class Biomes {
        public static final TagKey<Biome> HAS_BLACK_EYED_BEANS = create("has_crop/black_eyed_bean");
        public static final TagKey<Biome> HAS_BROAD_BEANS = create("has_crop/broad_bean");
        public static final TagKey<Biome> HAS_HARICOT_BEANS = create("has_crop/haricot_bean");
        public static final TagKey<Biome> HAS_KIDNEY_BEANS = create("has_crop/kidney_bean");

        private static TagKey<Biome> create(String name) {
            return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Beanology.MOD_ID, name));
        }
    }
}
