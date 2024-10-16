package com.mt1006.metalfences;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.mojang.datafixers.util.Pair;
import com.mt1006.metalfences.blocks.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mod(MetalFencesMod.MOD_ID)
public class MetalFencesMod extends MetalFencesBlockAccessor
{
	public static final String MOD_ID = "metalfences";


	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
	private static final List<Pair<RegistryObject<Item>, Item>> creativeTabPairs = new ArrayList<>();


	public static final RegistryObject<Block> IRON_FENCE = register("iron_fence", () -> new FenceBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(4.0f, 5.0f).sound(SoundType.METAL)), Items.IRON_DOOR);
	public static final RegistryObject<Block> IRON_FENCE_GATE = register("iron_fence_gate", () -> new IronFenceGateBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(4.0f, 5.0f).sound(SoundType.METAL)), Items.IRON_DOOR);

	public static final RegistryObject<Block> COPPER_FENCE = register("copper_fence", () -> new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.UNAFFECTED,
			BlockBehaviour.Properties.of().mapColor(Blocks.COPPER_BLOCK.defaultMapColor()).strength(3.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.COPPER)), Items.COPPER_DOOR);
	public static final RegistryObject<Block> EXPOSED_COPPER_FENCE = register("exposed_copper_fence", () -> new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.EXPOSED_COPPER_DOOR);
	public static final RegistryObject<Block> WEATHERED_COPPER_FENCE = register("weathered_copper_fence", () -> new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.WEATHERED_COPPER_DOOR);
	public static final RegistryObject<Block> OXIDIZED_COPPER_FENCE = register("oxidized_copper_fence", () -> new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.OXIDIZED_COPPER_DOOR);
	public static final RegistryObject<Block> WAXED_COPPER_FENCE = register("waxed_copper_fence", () -> new WaxedCopperFenceBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.WAXED_COPPER_DOOR);
	public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_FENCE = register("waxed_exposed_copper_fence", () -> new WaxedCopperFenceBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.WAXED_EXPOSED_COPPER_DOOR);
	public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_FENCE = register("waxed_weathered_copper_fence", () -> new WaxedCopperFenceBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.WAXED_WEATHERED_COPPER_DOOR);
	public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_FENCE = register("waxed_oxidized_copper_fence", () -> new WaxedCopperFenceBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.WAXED_OXIDIZED_COPPER_DOOR);

	public static final RegistryObject<Block> COPPER_FENCE_GATE = register("copper_fence_gate", () -> new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.COPPER_DOOR);
	public static final RegistryObject<Block> EXPOSED_COPPER_FENCE_GATE = register("exposed_copper_fence_gate", () -> new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.EXPOSED_COPPER_DOOR);
	public static final RegistryObject<Block> WEATHERED_COPPER_FENCE_GATE = register("weathered_copper_fence_gate", () -> new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.WEATHERED_COPPER_DOOR);
	public static final RegistryObject<Block> OXIDIZED_COPPER_FENCE_GATE = register("oxidized_copper_fence_gate", () -> new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.OXIDIZED_COPPER_DOOR);
	public static final RegistryObject<Block> WAXED_COPPER_FENCE_GATE = register("waxed_copper_fence_gate", () -> new WaxedCopperFenceGateBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.WAXED_COPPER_DOOR);
	public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_FENCE_GATE = register("waxed_exposed_copper_fence_gate", () -> new WaxedCopperFenceGateBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.WAXED_EXPOSED_COPPER_DOOR);
	public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_FENCE_GATE = register("waxed_weathered_copper_fence_gate", () -> new WaxedCopperFenceGateBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.WAXED_WEATHERED_COPPER_DOOR);
	public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_FENCE_GATE = register("waxed_oxidized_copper_fence_gate", () -> new WaxedCopperFenceGateBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_FENCE.get())), Items.WAXED_OXIDIZED_COPPER_DOOR);


	public static final BiMap<ResourceLocation, ResourceLocation> WEATHERING = ImmutableBiMap.of(
			COPPER_FENCE.getId(), EXPOSED_COPPER_FENCE.getId(),
			EXPOSED_COPPER_FENCE.getId(), WEATHERED_COPPER_FENCE.getId(),
			WEATHERED_COPPER_FENCE.getId(), OXIDIZED_COPPER_FENCE.getId(),
			COPPER_FENCE_GATE.getId(), EXPOSED_COPPER_FENCE_GATE.getId(),
			EXPOSED_COPPER_FENCE_GATE.getId(), WEATHERED_COPPER_FENCE_GATE.getId(),
			WEATHERED_COPPER_FENCE_GATE.getId(), OXIDIZED_COPPER_FENCE_GATE.getId());
	public static final BiMap<ResourceLocation, ResourceLocation> INVERSE_WEATHERING = WEATHERING.inverse();

	public static final BiMap<ResourceLocation, ResourceLocation> WAXABLES = ImmutableBiMap.of(
			COPPER_FENCE.getId(), WAXED_COPPER_FENCE.getId(),
			EXPOSED_COPPER_FENCE.getId(), WAXED_EXPOSED_COPPER_FENCE.getId(),
			WEATHERED_COPPER_FENCE.getId(), WAXED_WEATHERED_COPPER_FENCE.getId(),
			OXIDIZED_COPPER_FENCE.getId(), WAXED_OXIDIZED_COPPER_FENCE.getId(),
			COPPER_FENCE_GATE.getId(), WAXED_COPPER_FENCE_GATE.getId(),
			EXPOSED_COPPER_FENCE_GATE.getId(), WAXED_EXPOSED_COPPER_FENCE_GATE.getId(),
			WEATHERED_COPPER_FENCE_GATE.getId(), WAXED_WEATHERED_COPPER_FENCE_GATE.getId(),
			OXIDIZED_COPPER_FENCE_GATE.getId(), WAXED_OXIDIZED_COPPER_FENCE_GATE.getId());
	public static final BiMap<ResourceLocation, ResourceLocation> UNWAXABLES = WAXABLES.inverse();


	public MetalFencesMod(FMLJavaModLoadingContext context)
	{
		MetalFencesBlockAccessor.ACCESSOR = this;

		IEventBus modEventBus = context.getModEventBus();
		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);
		modEventBus.addListener(this::addToCreativeTab);
	}

	private static RegistryObject<Block> register(String id, Supplier<Block> blockSupplier, Item nextInTab)
	{
		RegistryObject<Block> registryObject = BLOCKS.register(id, blockSupplier);
		RegistryObject<Item> item = ITEMS.register(id, () -> new BlockItem(registryObject.get(), new Item.Properties()));
		creativeTabPairs.add(Pair.of(item, nextInTab));
		return registryObject;
	}

	private void addToCreativeTab(BuildCreativeModeTabContentsEvent event)
	{
		if (event.getTabKey().equals(CreativeModeTabs.BUILDING_BLOCKS))
		{
			MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();

			for (Pair<RegistryObject<Item>, Item> pair : creativeTabPairs)
			{
				entries.putBefore(new ItemStack(pair.getSecond()), new ItemStack(pair.getFirst().get()),
						CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			}
		}
	}

	private @Nullable Block getFromMap(Map<ResourceLocation, ResourceLocation> map, Block block)
	{
		if (!ForgeRegistries.BLOCKS.containsValue(block)) { return null; }
		ResourceLocation newBlockId = map.get(ForgeRegistries.BLOCKS.getKey(block));

		if (newBlockId == null || !ForgeRegistries.BLOCKS.containsKey(newBlockId)) { return null; }
		return ForgeRegistries.BLOCKS.getValue(newBlockId);
	}

	@Override public @Nullable Block getNextWeathered(Block block)
	{
		return getFromMap(WEATHERING, block);
	}

	@Override public @Nullable Block getNextUnweathered(Block block)
	{
		return getFromMap(INVERSE_WEATHERING, block);
	}

	@Override public @Nullable Block getWaxed(Block block)
	{
		return getFromMap(WAXABLES, block);
	}

	@Override public @Nullable Block getUnwaxed(Block block)
	{
		return getFromMap(UNWAXABLES, block);
	}
}
