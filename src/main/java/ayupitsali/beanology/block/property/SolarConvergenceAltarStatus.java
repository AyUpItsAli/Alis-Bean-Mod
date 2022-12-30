package ayupitsali.beanology.block.property;

import net.minecraft.util.StringRepresentable;

public enum SolarConvergenceAltarStatus implements StringRepresentable {
    INACTIVE,
    STARTING,
    ACTIVE,
    PROCESSING,
    STOPPING;

    public String toString() {
        return this.getSerializedName();
    }

    public String getSerializedName() {
        return switch (this) {
            case INACTIVE -> "inactive";
            case STARTING -> "starting";
            case ACTIVE -> "active";
            case PROCESSING -> "processing";
            case STOPPING -> "stopping";
        };
    }
}