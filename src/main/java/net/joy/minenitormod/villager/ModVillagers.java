package net.joy.minenitormod.villager;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import net.joy.minenitormod.MinenitorMod;
import net.joy.minenitormod.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, MinenitorMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MinenitorMod.MOD_ID);

    public static final RegistryObject<PoiType> SOUND_POI = POI_TYPES.register("sound_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.SOUND_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> SOUND_MASTER = VILLAGER_PROFESSIONS.register("sound_master",
            () -> new VillagerProfession("sound_master", poiTypeHolder -> poiTypeHolder.get() == SOUND_POI.get(),
                    poiTypeHolder -> poiTypeHolder.get() == SOUND_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER));


    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
