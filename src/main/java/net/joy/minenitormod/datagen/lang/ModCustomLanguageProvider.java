package net.joy.minenitormod.datagen.lang;

import net.joy.minenitormod.MinenitorMod;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class ModCustomLanguageProvider extends LanguageProvider {
    public ModCustomLanguageProvider(PackOutput output, String locale) {
        super(output, MinenitorMod.MOD_ID, locale);
    }

    public void add(CreativeModeTab key, String name) {
        add(key.getDisplayName().getString(), name);
    }

    public void add(RegistryObject<VillagerProfession> key, String name) {
        add("entity.minecraft.villager." + key.getId().getNamespace() + "." + key.getId().getPath(), name);
    }

    @Override
    protected void addTranslations() {
    }
}
