package com.mt1006.metalfences;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.mt1006.metalfences.blocks.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

public class MetalFencesMod extends MetalFencesBlockAccessor implements ModInitializer
{
	public static final String MOD_ID = "metalfences";

	public static final Block IRON_FENCE = new FenceBlock(
			BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(4.0f, 5.0f).sound(SoundType.METAL));
	public static final Block IRON_FENCE_GATE = new IronFenceGateBlock(
			BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(4.0f, 5.0f).sound(SoundType.METAL));

	public static final Block COPPER_FENCE = new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.UNAFFECTED,
			BlockBehaviour.Properties.of(Material.METAL, Blocks.COPPER_BLOCK.defaultMaterialColor()).strength(3.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.COPPER));
	public static final Block EXPOSED_COPPER_FENCE = new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.copy(COPPER_FENCE));
	public static final Block WEATHERED_COPPER_FENCE = new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.copy(COPPER_FENCE));
	public static final Block OXIDIZED_COPPER_FENCE = new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.copy(COPPER_FENCE));
	public static final Block WAXED_COPPER_FENCE = new WaxedCopperFenceBlock(BlockBehaviour.Properties.copy(COPPER_FENCE));
	public static final Block WAXED_EXPOSED_COPPER_FENCE = new WaxedCopperFenceBlock(BlockBehaviour.Properties.copy(COPPER_FENCE));
	public static final Block WAXED_WEATHERED_COPPER_FENCE = new WaxedCopperFenceBlock(BlockBehaviour.Properties.copy(COPPER_FENCE));
	public static final Block WAXED_OXIDIZED_COPPER_FENCE = new WaxedCopperFenceBlock(BlockBehaviour.Properties.copy(COPPER_FENCE));

	public static final Block COPPER_FENCE_GATE = new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.copy(COPPER_FENCE));
	public static final Block EXPOSED_COPPER_FENCE_GATE = new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.copy(COPPER_FENCE));
	public static final Block WEATHERED_COPPER_FENCE_GATE = new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.copy(COPPER_FENCE));
	public static final Block OXIDIZED_COPPER_FENCE_GATE = new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.copy(COPPER_FENCE));
	public static final Block WAXED_COPPER_FENCE_GATE = new WaxedCopperFenceGateBlock(BlockBehaviour.Properties.copy(COPPER_FENCE));
	public static final Block WAXED_EXPOSED_COPPER_FENCE_GATE = new WaxedCopperFenceGateBlock(BlockBehaviour.Properties.copy(COPPER_FENCE));
	public static final Block WAXED_WEATHERED_COPPER_FENCE_GATE = new WaxedCopperFenceGateBlock(BlockBehaviour.Properties.copy(COPPER_FENCE));
	public static final Block WAXED_OXIDIZED_COPPER_FENCE_GATE = new WaxedCopperFenceGateBlock(BlockBehaviour.Properties.copy(COPPER_FENCE));


	public static final Item IRON_FENCE_ITEM = new BlockItem(IRON_FENCE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
	public static final Item IRON_FENCE_GATE_ITEM = new BlockItem(IRON_FENCE_GATE, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));

	public static final Item COPPER_FENCE_ITEM = new BlockItem(COPPER_FENCE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
	public static final Item EXPOSED_COPPER_FENCE_ITEM = new BlockItem(EXPOSED_COPPER_FENCE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
	public static final Item WEATHERED_COPPER_FENCE_ITEM = new BlockItem(WEATHERED_COPPER_FENCE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
	public static final Item OXIDIZED_COPPER_FENCE_ITEM = new BlockItem(OXIDIZED_COPPER_FENCE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
	public static final Item WAXED_COPPER_FENCE_ITEM = new BlockItem(WAXED_COPPER_FENCE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
	public static final Item WAXED_EXPOSED_COPPER_FENCE_ITEM = new BlockItem(WAXED_EXPOSED_COPPER_FENCE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
	public static final Item WAXED_WEATHERED_COPPER_FENCE_ITEM = new BlockItem(WAXED_WEATHERED_COPPER_FENCE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
	public static final Item WAXED_OXIDIZED_COPPER_FENCE_ITEM = new BlockItem(WAXED_OXIDIZED_COPPER_FENCE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

	public static final Item COPPER_FENCE_GATE_ITEM = new BlockItem(COPPER_FENCE_GATE, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
	public static final Item EXPOSED_COPPER_FENCE_GATE_ITEM = new BlockItem(EXPOSED_COPPER_FENCE_GATE, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
	public static final Item WEATHERED_COPPER_FENCE_GATE_ITEM = new BlockItem(WEATHERED_COPPER_FENCE_GATE, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
	public static final Item OXIDIZED_COPPER_FENCE_GATE_ITEM = new BlockItem(OXIDIZED_COPPER_FENCE_GATE, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
	public static final Item WAXED_COPPER_FENCE_GATE_ITEM = new BlockItem(WAXED_COPPER_FENCE_GATE, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
	public static final Item WAXED_EXPOSED_COPPER_FENCE_GATE_ITEM = new BlockItem(WAXED_EXPOSED_COPPER_FENCE_GATE, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
	public static final Item WAXED_WEATHERED_COPPER_FENCE_GATE_ITEM = new BlockItem(WAXED_WEATHERED_COPPER_FENCE_GATE, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
	public static final Item WAXED_OXIDIZED_COPPER_FENCE_GATE_ITEM = new BlockItem(WAXED_OXIDIZED_COPPER_FENCE_GATE, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));


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

		register("iron_fence", IRON_FENCE, IRON_FENCE_ITEM);
		register("iron_fence_gate", IRON_FENCE_GATE, IRON_FENCE_GATE_ITEM);

		register("copper_fence", COPPER_FENCE, COPPER_FENCE_ITEM);
		register("exposed_copper_fence", EXPOSED_COPPER_FENCE, EXPOSED_COPPER_FENCE_ITEM);
		register("weathered_copper_fence", WEATHERED_COPPER_FENCE, WEATHERED_COPPER_FENCE_ITEM);
		register("oxidized_copper_fence", OXIDIZED_COPPER_FENCE, OXIDIZED_COPPER_FENCE_ITEM);

		register("waxed_copper_fence", WAXED_COPPER_FENCE, WAXED_COPPER_FENCE_ITEM);
		register("waxed_exposed_copper_fence", WAXED_EXPOSED_COPPER_FENCE, WAXED_EXPOSED_COPPER_FENCE_ITEM);
		register("waxed_weathered_copper_fence", WAXED_WEATHERED_COPPER_FENCE, WAXED_WEATHERED_COPPER_FENCE_ITEM);
		register("waxed_oxidized_copper_fence", WAXED_OXIDIZED_COPPER_FENCE, WAXED_OXIDIZED_COPPER_FENCE_ITEM);

		register("copper_fence_gate", COPPER_FENCE_GATE, COPPER_FENCE_GATE_ITEM);
		register("exposed_copper_fence_gate", EXPOSED_COPPER_FENCE_GATE, EXPOSED_COPPER_FENCE_GATE_ITEM);
		register("weathered_copper_fence_gate", WEATHERED_COPPER_FENCE_GATE, WEATHERED_COPPER_FENCE_GATE_ITEM);
		register("oxidized_copper_fence_gate", OXIDIZED_COPPER_FENCE_GATE, OXIDIZED_COPPER_FENCE_GATE_ITEM);

		register("waxed_copper_fence_gate", WAXED_COPPER_FENCE_GATE, WAXED_COPPER_FENCE_GATE_ITEM);
		register("waxed_exposed_copper_fence_gate", WAXED_EXPOSED_COPPER_FENCE_GATE, WAXED_EXPOSED_COPPER_FENCE_GATE_ITEM);
		register("waxed_weathered_copper_fence_gate", WAXED_WEATHERED_COPPER_FENCE_GATE, WAXED_WEATHERED_COPPER_FENCE_GATE_ITEM);
		register("waxed_oxidized_copper_fence_gate", WAXED_OXIDIZED_COPPER_FENCE_GATE, WAXED_OXIDIZED_COPPER_FENCE_GATE_ITEM);
	}

	private static void register(String id, Block block, Item item)
	{
		ResourceLocation resLoc = new ResourceLocation(MOD_ID, id);
		Registry.register(Registry.BLOCK, resLoc, block);
		Registry.register(Registry.ITEM, resLoc, item);
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
