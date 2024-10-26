package metalfences;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.mojang.datafixers.util.Pair;
import com.mt1006.metalfences.MetalFencesBlockAccessor;
import com.mt1006.metalfences.blocks.*;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Mod(MetalFencesMod.MOD_ID)
public class MetalFencesMod extends MetalFencesBlockAccessor
{
	public static final String MOD_ID = "metalfences";


	private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
	private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);
	private static final List<Pair<DeferredHolder<Item, Item>, Item>> creativeTabPairs = new ArrayList<>();


	public static final DeferredHolder<Block, Block> IRON_FENCE = register("iron_fence", FenceBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(4.0f, 5.0f).sound(SoundType.METAL), Items.IRON_DOOR);
	public static final DeferredHolder<Block, Block> IRON_FENCE_GATE = register("iron_fence_gate", IronFenceGateBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(4.0f, 5.0f).sound(SoundType.METAL), Items.IRON_DOOR);

	public static final DeferredHolder<Block, Block> COPPER_FENCE = register("copper_fence", (props) -> new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.UNAFFECTED, props), getCopperProps(), Items.COPPER_DOOR);
	public static final DeferredHolder<Block, Block> EXPOSED_COPPER_FENCE = register("exposed_copper_fence", (props) -> new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.EXPOSED, props), getCopperProps(), Items.EXPOSED_COPPER_DOOR);
	public static final DeferredHolder<Block, Block> WEATHERED_COPPER_FENCE = register("weathered_copper_fence", (props) -> new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.WEATHERED, props), getCopperProps(), Items.WEATHERED_COPPER_DOOR);
	public static final DeferredHolder<Block, Block> OXIDIZED_COPPER_FENCE = register("oxidized_copper_fence", (props) -> new WeatheringCopperFenceBlock(WeatheringCopper.WeatherState.OXIDIZED, props), getCopperProps(), Items.OXIDIZED_COPPER_DOOR);
	public static final DeferredHolder<Block, Block> WAXED_COPPER_FENCE = register("waxed_copper_fence", WaxedCopperFenceBlock::new, getCopperProps(), Items.WAXED_COPPER_DOOR);
	public static final DeferredHolder<Block, Block> WAXED_EXPOSED_COPPER_FENCE = register("waxed_exposed_copper_fence", WaxedCopperFenceBlock::new, getCopperProps(), Items.WAXED_EXPOSED_COPPER_DOOR);
	public static final DeferredHolder<Block, Block> WAXED_WEATHERED_COPPER_FENCE = register("waxed_weathered_copper_fence", WaxedCopperFenceBlock::new, getCopperProps(), Items.WAXED_WEATHERED_COPPER_DOOR);
	public static final DeferredHolder<Block, Block> WAXED_OXIDIZED_COPPER_FENCE = register("waxed_oxidized_copper_fence", WaxedCopperFenceBlock::new, getCopperProps(), Items.WAXED_OXIDIZED_COPPER_DOOR);

	public static final DeferredHolder<Block, Block> COPPER_FENCE_GATE = register("copper_fence_gate", (props) -> new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.UNAFFECTED, props), getCopperProps(), Items.COPPER_DOOR);
	public static final DeferredHolder<Block, Block> EXPOSED_COPPER_FENCE_GATE = register("exposed_copper_fence_gate", (props) -> new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.EXPOSED, props), getCopperProps(), Items.EXPOSED_COPPER_DOOR);
	public static final DeferredHolder<Block, Block> WEATHERED_COPPER_FENCE_GATE = register("weathered_copper_fence_gate", (props) -> new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.WEATHERED, props), getCopperProps(), Items.WEATHERED_COPPER_DOOR);
	public static final DeferredHolder<Block, Block> OXIDIZED_COPPER_FENCE_GATE = register("oxidized_copper_fence_gate", (props) -> new WeatheringCopperFenceGateBlock(WeatheringCopper.WeatherState.OXIDIZED, props), getCopperProps(), Items.OXIDIZED_COPPER_DOOR);
	public static final DeferredHolder<Block, Block> WAXED_COPPER_FENCE_GATE = register("waxed_copper_fence_gate", WaxedCopperFenceGateBlock::new, getCopperProps(), Items.WAXED_COPPER_DOOR);
	public static final DeferredHolder<Block, Block> WAXED_EXPOSED_COPPER_FENCE_GATE = register("waxed_exposed_copper_fence_gate", WaxedCopperFenceGateBlock::new, getCopperProps(), Items.WAXED_EXPOSED_COPPER_DOOR);
	public static final DeferredHolder<Block, Block> WAXED_WEATHERED_COPPER_FENCE_GATE = register("waxed_weathered_copper_fence_gate", WaxedCopperFenceGateBlock::new, getCopperProps(), Items.WAXED_WEATHERED_COPPER_DOOR);
	public static final DeferredHolder<Block, Block> WAXED_OXIDIZED_COPPER_FENCE_GATE = register("waxed_oxidized_copper_fence_gate", WaxedCopperFenceGateBlock::new, getCopperProps(), Items.WAXED_OXIDIZED_COPPER_DOOR);


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


	public MetalFencesMod(IEventBus modEventBus)
	{
		MetalFencesBlockAccessor.ACCESSOR = this;

		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);
		modEventBus.addListener(this::addToCreativeTab);
	}

	private static DeferredHolder<Block, Block> register(String id, Function<BlockBehaviour.Properties, Block> blockSupplier, BlockBehaviour.Properties properties, Item nextInTab)
	{
		DeferredHolder<Block, Block> registryObject = BLOCKS.registerBlock(id, blockSupplier, properties);
		DeferredHolder<Item, Item> item = ITEMS.registerItem(id, (props) -> new BlockItem(registryObject.get(), props.useBlockDescriptionPrefix()));
		creativeTabPairs.add(Pair.of(item, nextInTab));
		return registryObject;
	}

	private void addToCreativeTab(BuildCreativeModeTabContentsEvent event)
	{
		if (event.getTabKey().equals(CreativeModeTabs.BUILDING_BLOCKS))
		{
			for (Pair<DeferredHolder<Item, Item>, Item> pair : creativeTabPairs)
			{
				event.insertBefore(new ItemStack(pair.getSecond()), new ItemStack(pair.getFirst().get()),
						CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			}
		}
	}

	private @Nullable Block getFromMap(Map<ResourceLocation, ResourceLocation> map, Block block)
	{
		if (!BuiltInRegistries.BLOCK.containsValue(block)) { return null; }
		ResourceLocation newBlockId = map.get(BuiltInRegistries.BLOCK.getKey(block));

		if (newBlockId == null || !BuiltInRegistries.BLOCK.containsKey(newBlockId)) { return null; }
		Holder.Reference<Block> ref = BuiltInRegistries.BLOCK.get(newBlockId).orElse(null);
		return ref != null ? ref.value() : null;
	}

	private static BlockBehaviour.Properties getCopperProps()
	{
		return BlockBehaviour.Properties.of().mapColor(Blocks.COPPER_BLOCK.defaultMapColor()).strength(3.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.COPPER);
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
