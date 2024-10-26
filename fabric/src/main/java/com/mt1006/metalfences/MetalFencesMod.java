package com.mt1006.metalfences;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.mojang.datafixers.util.Pair;
import com.mt1006.metalfences.blocks.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MetalFencesMod extends MetalFencesBlockAccessor implements ModInitializer
{
	public static final String MOD_ID = "metalfences";

	private static final ArrayList<Triple<ResourceLocation, Block, Item>> toRegister = new ArrayList<>();
	private static final List<Pair<Item, Item>> creativeTabPairs = new ArrayList<>();

	public static final Block IRON_FENCE = register("iron_fence", FenceBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(4.0f, 5.0f).sound(SoundType.METAL), Items.IRON_DOOR);
	public static final Block IRON_FENCE_GATE = register("iron_fence_gate", IronFenceGateBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(4.0f, 5.0f).sound(SoundType.METAL), Items.IRON_DOOR);

	public static final Block COPPER_FENCE = register("copper_fence", (props) -> new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.UNAFFECTED, props),
			BlockBehaviour.Properties.of().mapColor(Blocks.COPPER_BLOCK.defaultMapColor()).strength(3.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.COPPER), Items.COPPER_DOOR);
	public static final Block EXPOSED_COPPER_FENCE = register("exposed_copper_fence", (props) -> new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.EXPOSED, props), COPPER_FENCE, Items.EXPOSED_COPPER_DOOR);
	public static final Block WEATHERED_COPPER_FENCE = register("weathered_copper_fence", (props) -> new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.WEATHERED, props), COPPER_FENCE, Items.WEATHERED_COPPER_DOOR);
	public static final Block OXIDIZED_COPPER_FENCE = register("oxidized_copper_fence", (props) -> new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.OXIDIZED, props), COPPER_FENCE, Items.OXIDIZED_COPPER_DOOR);
	public static final Block WAXED_COPPER_FENCE = register("waxed_copper_fence", WaxedCopperFenceBlock::new, COPPER_FENCE, Items.WAXED_COPPER_DOOR);
	public static final Block WAXED_EXPOSED_COPPER_FENCE = register("waxed_exposed_copper_fence", WaxedCopperFenceBlock::new, COPPER_FENCE, Items.WAXED_EXPOSED_COPPER_DOOR);
	public static final Block WAXED_WEATHERED_COPPER_FENCE = register("waxed_weathered_copper_fence", WaxedCopperFenceBlock::new, COPPER_FENCE, Items.WAXED_WEATHERED_COPPER_DOOR);
	public static final Block WAXED_OXIDIZED_COPPER_FENCE = register("waxed_oxidized_copper_fence", WaxedCopperFenceBlock::new, COPPER_FENCE, Items.WAXED_OXIDIZED_COPPER_DOOR);

	public static final Block COPPER_FENCE_GATE = register("copper_fence_gate", (props) -> new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.UNAFFECTED, props), COPPER_FENCE, Items.COPPER_DOOR);
	public static final Block EXPOSED_COPPER_FENCE_GATE = register("exposed_copper_fence_gate", (props) -> new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.EXPOSED, props), COPPER_FENCE, Items.EXPOSED_COPPER_DOOR);
	public static final Block WEATHERED_COPPER_FENCE_GATE = register("weathered_copper_fence_gate", (props) -> new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.WEATHERED, props), COPPER_FENCE, Items.WEATHERED_COPPER_DOOR);
	public static final Block OXIDIZED_COPPER_FENCE_GATE = register("oxidized_copper_fence_gate", (props) -> new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.OXIDIZED, props), COPPER_FENCE, Items.OXIDIZED_COPPER_DOOR);
	public static final Block WAXED_COPPER_FENCE_GATE = register("waxed_copper_fence_gate", WaxedCopperFenceGateBlock::new, COPPER_FENCE, Items.WAXED_COPPER_DOOR);
	public static final Block WAXED_EXPOSED_COPPER_FENCE_GATE = register("waxed_exposed_copper_fence_gate", WaxedCopperFenceGateBlock::new, COPPER_FENCE, Items.WAXED_EXPOSED_COPPER_DOOR);
	public static final Block WAXED_WEATHERED_COPPER_FENCE_GATE = register("waxed_weathered_copper_fence_gate", WaxedCopperFenceGateBlock::new, COPPER_FENCE, Items.WAXED_WEATHERED_COPPER_DOOR);
	public static final Block WAXED_OXIDIZED_COPPER_FENCE_GATE = register("waxed_oxidized_copper_fence_gate", WaxedCopperFenceGateBlock::new, COPPER_FENCE, Items.WAXED_OXIDIZED_COPPER_DOOR);


	public static final BiMap<Block, Block> WEATHERING = ImmutableBiMap.of(
			COPPER_FENCE, EXPOSED_COPPER_FENCE,
			EXPOSED_COPPER_FENCE, WEATHERED_COPPER_FENCE,
			WEATHERED_COPPER_FENCE, OXIDIZED_COPPER_FENCE,
			COPPER_FENCE_GATE, EXPOSED_COPPER_FENCE_GATE,
			EXPOSED_COPPER_FENCE_GATE, WEATHERED_COPPER_FENCE_GATE,
			WEATHERED_COPPER_FENCE_GATE, OXIDIZED_COPPER_FENCE_GATE);
	public static final BiMap<Block, Block> INVERSE_WEATHERING = WEATHERING.inverse();

	public static final BiMap<Block, Block> WAXABLES = ImmutableBiMap.of(
			COPPER_FENCE, WAXED_COPPER_FENCE,
			EXPOSED_COPPER_FENCE, WAXED_EXPOSED_COPPER_FENCE,
			WEATHERED_COPPER_FENCE, WAXED_WEATHERED_COPPER_FENCE,
			OXIDIZED_COPPER_FENCE, WAXED_OXIDIZED_COPPER_FENCE,
			COPPER_FENCE_GATE, WAXED_COPPER_FENCE_GATE,
			EXPOSED_COPPER_FENCE_GATE, WAXED_EXPOSED_COPPER_FENCE_GATE,
			WEATHERED_COPPER_FENCE_GATE, WAXED_WEATHERED_COPPER_FENCE_GATE,
			OXIDIZED_COPPER_FENCE_GATE, WAXED_OXIDIZED_COPPER_FENCE_GATE);
	public static final BiMap<Block, Block> UNWAXABLES = WAXABLES.inverse();


	@Override public void onInitialize()
	{
		MetalFencesBlockAccessor.ACCESSOR = this;

		for (Triple<ResourceLocation, Block, Item> triple : toRegister)
		{
			Registry.register(BuiltInRegistries.BLOCK, triple.getLeft(), triple.getMiddle());
			Registry.register(BuiltInRegistries.ITEM, triple.getLeft(), triple.getRight());
		}
		toRegister.clear();

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(this::addToCreativeTab);
	}

	private static Block register(String id, Function<BlockBehaviour.Properties, Block> blockSupplier, BlockBehaviour.Properties properties, Item nextInTab)
	{
		ResourceLocation resLoc = ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
		Block block = blockSupplier.apply(properties.setId(ResourceKey.create(Registries.BLOCK, resLoc)));
		Item item = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, resLoc)));

		toRegister.add(Triple.of(resLoc, block, item));
		creativeTabPairs.add(Pair.of(item, nextInTab));
		return block;
	}

	private static Block register(String id, Function<BlockBehaviour.Properties, Block> blockSupplier, Block copyFrom, Item nextInTab)
	{
		return register(id, blockSupplier, BlockBehaviour.Properties.ofFullCopy(copyFrom), nextInTab);
	}

	private void addToCreativeTab(FabricItemGroupEntries entries)
	{
		creativeTabPairs.forEach((pair) -> entries.addBefore(pair.getSecond(), pair.getFirst()));
	}

	@Override public @Nullable Block getNextWeathered(Block block)
	{
		return WEATHERING.get(block);
	}

	@Override public @Nullable Block getNextUnweathered(Block block)
	{
		return INVERSE_WEATHERING.get(block);
	}

	@Override public @Nullable Block getWaxed(Block block)
	{
		return WAXABLES.get(block);
	}

	@Override public @Nullable Block getUnwaxed(Block block)
	{
		return UNWAXABLES.get(block);
	}
}
