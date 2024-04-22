package net.joy.minenitormod.utils;

import net.joy.minenitormod.MinenitorMod;
import net.minecraft.world.flag.FeatureElement;
import net.minecraftforge.registries.RegistryObject;

public class TextHelper {
    // Method to generate tooltip key
    public static <T extends FeatureElement> String tooltipKey(RegistryObject<T> item) {
        return "tooltip." + MinenitorMod.MOD_ID + "." + item.getId().getPath();
    }
}
