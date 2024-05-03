package net.joy.minenitormod;

import com.mojang.logging.LogUtils;
import net.joy.minenitormod.block.ModBlocks;
import net.joy.minenitormod.block.entity.ModBlockEntities;
import net.joy.minenitormod.entity.ModEntities;
import net.joy.minenitormod.entity.client.RhinoRenderer;
import net.joy.minenitormod.item.ModItems;
import net.joy.minenitormod.loot.ModLootModifier;
import net.joy.minenitormod.recipe.ModRecipes;
import net.joy.minenitormod.screen.GemPolishingStationScreen;
import net.joy.minenitormod.screen.ModMenuType;
import net.joy.minenitormod.sound.ModSounds;
import net.joy.minenitormod.villager.ModVillagers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MinenitorMod.MOD_ID)
public class MinenitorMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "minenitor";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public MinenitorMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuType.register(modEventBus);

        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);
        ModRecipes.register(modEventBus);

        ModLootModifier.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.CAT_MINT.getId(), ModBlocks.POTTED_CAT_MINT);
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.RHINO.get(), RhinoRenderer::new);

            MenuScreens.register(ModMenuType.GEM_POLISHING_MENU.get(), GemPolishingStationScreen::new);
        }
    }
}
