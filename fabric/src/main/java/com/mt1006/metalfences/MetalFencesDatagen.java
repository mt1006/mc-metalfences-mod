package com.mt1006.metalfences;

import com.google.common.collect.Iterables;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class MetalFencesDatagen implements DataGeneratorEntrypoint
{
	private static final List<Block> WEATHERING_COPPER_FENCES = List.of(
			MetalFencesMod.COPPER_FENCE, MetalFencesMod.EXPOSED_COPPER_FENCE,
			MetalFencesMod.WEATHERED_COPPER_FENCE, MetalFencesMod.OXIDIZED_COPPER_FENCE);

	private static final List<Block> WAXED_COPPER_FENCES = List.of(
			MetalFencesMod.WAXED_COPPER_FENCE, MetalFencesMod.WAXED_EXPOSED_COPPER_FENCE,
			MetalFencesMod.WAXED_WEATHERED_COPPER_FENCE, MetalFencesMod.WAXED_OXIDIZED_COPPER_FENCE);

	private static final List<Block> WEATHERING_COPPER_FENCE_GATES = List.of(
			MetalFencesMod.COPPER_FENCE_GATE, MetalFencesMod.EXPOSED_COPPER_FENCE_GATE,
			MetalFencesMod.WEATHERED_COPPER_FENCE_GATE, MetalFencesMod.OXIDIZED_COPPER_FENCE_GATE);

	private static final List<Block> WAXED_COPPER_FENCE_GATES = List.of(
			MetalFencesMod.WAXED_COPPER_FENCE_GATE, MetalFencesMod.WAXED_EXPOSED_COPPER_FENCE_GATE,
			MetalFencesMod.WAXED_WEATHERED_COPPER_FENCE_GATE, MetalFencesMod.WAXED_OXIDIZED_COPPER_FENCE_GATE);

	private static final Iterable<Block> COPPER_FENCES = Iterables.concat(WEATHERING_COPPER_FENCES, WAXED_COPPER_FENCES);
	private static final Iterable<Block> COPPER_FENCE_GATES = Iterables.concat(WEATHERING_COPPER_FENCE_GATES, WAXED_COPPER_FENCE_GATES);


	private static final TagKey<Block> METAL_FENCE_BLOCKS = TagKey.create(Registries.BLOCK,
			new ResourceLocation(MetalFencesMod.MOD_ID, "metal_fences"));
	private static final TagKey<Block> METAL_FENCE_GATE_BLOCKS = TagKey.create(Registries.BLOCK,
			new ResourceLocation(MetalFencesMod.MOD_ID, "metal_fence_gates"));
	private static final TagKey<Block> COPPER_FENCE_BLOCKS = TagKey.create(Registries.BLOCK,
			new ResourceLocation(MetalFencesMod.MOD_ID, "copper_fences"));
	private static final TagKey<Block> COPPER_FENCE_GATE_BLOCKS = TagKey.create(Registries.BLOCK,
			new ResourceLocation(MetalFencesMod.MOD_ID, "copper_fence_gates"));

	private static final TagKey<Item> METAL_FENCE_ITEMS = TagKey.create(Registries.ITEM, METAL_FENCE_BLOCKS.location());
	private static final TagKey<Item> METAL_FENCE_GATE_ITEMS = TagKey.create(Registries.ITEM, METAL_FENCE_GATE_BLOCKS.location());
	private static final TagKey<Item> COPPER_FENCE_ITEMS = TagKey.create(Registries.ITEM, COPPER_FENCE_BLOCKS.location());
	private static final TagKey<Item> COPPER_FENCE_GATE_ITEMS = TagKey.create(Registries.ITEM, COPPER_FENCE_GATE_BLOCKS.location());

	private static final TagKey<Block> IRON_DOOR_KEY_OPENABLE = TagKey.create(Registries.BLOCK,
			new ResourceLocation("irondoorkey:openable"));


	@Override public void onInitializeDataGenerator(FabricDataGenerator dataGenerator)
	{
		FabricDataGenerator.Pack pack = dataGenerator.createPack();
		pack.addProvider(RecipeGenerator::new);
		pack.addProvider(LootTableGenerator::new);
		pack.addProvider(BlockTagGenerator::new);
		pack.addProvider(ItemTagGenerator::new);
		pack.addProvider(ModelGenerator::new);
	}

	private static class RecipeGenerator extends FabricRecipeProvider
	{
		public RecipeGenerator(FabricDataOutput output)
		{
			super(output);
		}

		@Override public void buildRecipes(Consumer<FinishedRecipe> exporter)
		{
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, MetalFencesMod.IRON_FENCE)
					.define('#', Items.IRON_INGOT).define('-', Items.IRON_NUGGET)
					.pattern("#-#").pattern("#-#")
					.unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
					.save(exporter);

			ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, MetalFencesMod.IRON_FENCE_GATE)
					.define('#', Items.IRON_INGOT).define('-', Items.IRON_NUGGET)
					.pattern("-#-").pattern("-#-")
					.unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
					.save(exporter);

			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, MetalFencesMod.COPPER_FENCE)
					.define('#', Items.COPPER_INGOT).define('/', Items.STICK)
					.pattern("#/#").pattern("#/#")
					.unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
					.save(exporter);

			ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, MetalFencesMod.COPPER_FENCE_GATE)
					.define('#', Items.COPPER_INGOT).define('/', Items.STICK)
					.pattern("/#/").pattern("/#/")
					.unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
					.save(exporter);

			for (int i = 0; i < 4; i++)
			{
				ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, WAXED_COPPER_FENCES.get(i))
						.requires(WEATHERING_COPPER_FENCES.get(i))
						.requires(Items.HONEYCOMB)
						.unlockedBy("has_copper_fence", has(WEATHERING_COPPER_FENCES.get(i)))
						.save(exporter);

				ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, WAXED_COPPER_FENCE_GATES.get(i))
						.requires(WEATHERING_COPPER_FENCE_GATES.get(i))
						.requires(Items.HONEYCOMB)
						.unlockedBy("has_copper_fence", has(WEATHERING_COPPER_FENCE_GATES.get(i)))
						.save(exporter);
			}
		}
	}

	private static class LootTableGenerator extends FabricBlockLootTableProvider
	{
		protected LootTableGenerator(FabricDataOutput dataOutput)
		{
			super(dataOutput);
		}

		@Override public void generate()
		{
			dropSelf(MetalFencesMod.IRON_FENCE);
			dropSelf(MetalFencesMod.IRON_FENCE_GATE);
			COPPER_FENCES.forEach(this::dropSelf);
			COPPER_FENCE_GATES.forEach(this::dropSelf);
		}
	}

	private static class BlockTagGenerator extends FabricTagProvider.BlockTagProvider
	{
		public BlockTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
		{
			super(output, registriesFuture);
		}

		@Override protected void addTags(HolderLookup.Provider provider)
		{
			FabricTagBuilder copperFenceBlocks = getOrCreateTagBuilder(COPPER_FENCE_BLOCKS);
			COPPER_FENCES.forEach(copperFenceBlocks::add);

			FabricTagBuilder copperFenceGateBlocks = getOrCreateTagBuilder(COPPER_FENCE_GATE_BLOCKS);
			COPPER_FENCE_GATES.forEach(copperFenceGateBlocks::add);

			FabricTagBuilder metalFenceBlocks = getOrCreateTagBuilder(METAL_FENCE_BLOCKS);
			metalFenceBlocks.add(MetalFencesMod.IRON_FENCE);
			metalFenceBlocks.addOptionalTag(COPPER_FENCE_BLOCKS);

			FabricTagBuilder metalFenceGateBlocks = getOrCreateTagBuilder(METAL_FENCE_GATE_BLOCKS);
			metalFenceGateBlocks.add(MetalFencesMod.IRON_FENCE_GATE);
			metalFenceGateBlocks.addOptionalTag(COPPER_FENCE_GATE_BLOCKS);

			getOrCreateTagBuilder(BlockTags.FENCES).addOptionalTag(METAL_FENCE_BLOCKS);
			getOrCreateTagBuilder(BlockTags.FENCE_GATES).addOptionalTag(METAL_FENCE_GATE_BLOCKS);
			getOrCreateTagBuilder(IRON_DOOR_KEY_OPENABLE).add(MetalFencesMod.IRON_FENCE_GATE);

			FabricTagBuilder mineableWithPickaxe = getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE);
			mineableWithPickaxe.addOptionalTag(METAL_FENCE_BLOCKS);
			mineableWithPickaxe.addOptionalTag(METAL_FENCE_GATE_BLOCKS);

			FabricTagBuilder needsStoneTool = getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL);
			needsStoneTool.addOptionalTag(COPPER_FENCE_BLOCKS);
			needsStoneTool.addOptionalTag(COPPER_FENCE_GATE_BLOCKS);
		}
	}

	private static class ItemTagGenerator extends FabricTagProvider.ItemTagProvider
	{
		public ItemTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
		{
			super(output, completableFuture);
		}

		@Override protected void addTags(HolderLookup.Provider provider)
		{
			FabricTagBuilder copperFenceItems = getOrCreateTagBuilder(COPPER_FENCE_ITEMS);
			COPPER_FENCES.forEach((block) -> copperFenceItems.add(block.asItem()));

			FabricTagBuilder copperFenceGateItems = getOrCreateTagBuilder(COPPER_FENCE_GATE_ITEMS);
			COPPER_FENCE_GATES.forEach((block) -> copperFenceGateItems.add(block.asItem()));

			FabricTagBuilder metalFenceItems = getOrCreateTagBuilder(METAL_FENCE_ITEMS);
			metalFenceItems.add(MetalFencesMod.IRON_FENCE_ITEM);
			metalFenceItems.addOptionalTag(COPPER_FENCE_ITEMS);

			FabricTagBuilder metalFenceGateItems = getOrCreateTagBuilder(METAL_FENCE_GATE_ITEMS);
			metalFenceGateItems.add(MetalFencesMod.IRON_FENCE_GATE_ITEM);
			metalFenceGateItems.addOptionalTag(COPPER_FENCE_GATE_ITEMS);

			getOrCreateTagBuilder(ItemTags.FENCES).addOptionalTag(METAL_FENCE_ITEMS);
			getOrCreateTagBuilder(ItemTags.FENCE_GATES).addOptionalTag(METAL_FENCE_GATE_ITEMS);
		}
	}

	private static class ModelGenerator extends FabricModelProvider
	{
		private ModelGenerator(FabricDataOutput generator)
		{
			super(generator);
		}

		@Override public void generateBlockStateModels(BlockModelGenerators modelGenerator)
		{
			ModBlockModelFamily ironFamily = new ModBlockModelFamily(modelGenerator, Blocks.IRON_BLOCK);
			List<ModBlockModelFamily> copperFamilies = List.of(
					new ModBlockModelFamily(modelGenerator, Blocks.COPPER_BLOCK),
					new ModBlockModelFamily(modelGenerator, Blocks.EXPOSED_COPPER),
					new ModBlockModelFamily(modelGenerator, Blocks.WEATHERED_COPPER),
					new ModBlockModelFamily(modelGenerator, Blocks.OXIDIZED_COPPER));

			ironFamily.addIronFence(MetalFencesMod.IRON_FENCE);
			ironFamily.addIronFenceGate(MetalFencesMod.IRON_FENCE_GATE);

			for (int i = 0; i < 4; i++)
			{
				ModBlockModelFamily family = copperFamilies.get(i);

				family.addCopperFences(WEATHERING_COPPER_FENCES.get(i), WAXED_COPPER_FENCES.get(i));
				family.addCopperFenceGates(WEATHERING_COPPER_FENCE_GATES.get(i), WAXED_COPPER_FENCE_GATES.get(i));
			}
		}

		@Override public void generateItemModels(ItemModelGenerators itemModelGenerator) {}
	}

	private static class ModBlockModelFamily
	{
		private static final String BLOCK_PREFIX = "block/";
		private final BlockModelGenerators modelGenerator;
		private final TextureMapping textureMapping;
		private final BlockModelGenerators.BlockFamilyProvider familyProvider;

		public ModBlockModelFamily(BlockModelGenerators modelGenerator, Block block)
		{
			this.modelGenerator = modelGenerator;

			textureMapping = new TextureMapping();
			textureMapping.put(TextureSlot.TEXTURE, BuiltInRegistries.BLOCK.getKey(block).withPrefix(BLOCK_PREFIX));
			familyProvider = modelGenerator.new BlockFamilyProvider(textureMapping);
		}

		public void addIronFence(Block block)
		{
			familyProvider.fence(block);
		}

		public void addIronFenceGate(Block block)
		{
			familyProvider.fenceGate(block);
			modelGenerator.delegateItemModel(block, BuiltInRegistries.BLOCK.getKey(block).withPrefix(BLOCK_PREFIX));
		}

		public void addCopperFences(Block block, Block waxedBlock)
		{
			ResourceLocation fencePost = ModelTemplates.FENCE_POST.create(block, textureMapping, modelGenerator.modelOutput);
			ResourceLocation fenceSide = ModelTemplates.FENCE_SIDE.create(block, textureMapping, modelGenerator.modelOutput);
			modelGenerator.blockStateOutput.accept(BlockModelGenerators.createFence(block, fencePost, fenceSide));
			modelGenerator.blockStateOutput.accept(BlockModelGenerators.createFence(waxedBlock, fencePost, fenceSide));

			ResourceLocation fenceInventory = ModelTemplates.FENCE_INVENTORY.create(block, textureMapping, modelGenerator.modelOutput);
			modelGenerator.delegateItemModel(block, fenceInventory);
			modelGenerator.delegateItemModel(waxedBlock, fenceInventory);
		}

		public void addCopperFenceGates(Block block, Block waxedBlock)
		{
			ResourceLocation gateOpen = ModelTemplates.FENCE_GATE_OPEN.create(block, textureMapping, modelGenerator.modelOutput);
			ResourceLocation gateClosed = ModelTemplates.FENCE_GATE_CLOSED.create(block, textureMapping, modelGenerator.modelOutput);
			ResourceLocation gateWallOpen = ModelTemplates.FENCE_GATE_WALL_OPEN.create(block, textureMapping, modelGenerator.modelOutput);
			ResourceLocation gateWallClosed = ModelTemplates.FENCE_GATE_WALL_CLOSED.create(block, textureMapping, modelGenerator.modelOutput);

			modelGenerator.blockStateOutput.accept(
					BlockModelGenerators.createFenceGate(block, gateOpen, gateClosed, gateWallOpen, gateWallClosed, true));
			modelGenerator.blockStateOutput.accept(
					BlockModelGenerators.createFenceGate(waxedBlock, gateOpen, gateClosed, gateWallOpen, gateWallClosed, true));

			modelGenerator.delegateItemModel(block, gateClosed);
			modelGenerator.delegateItemModel(waxedBlock, gateClosed);
		}
	}
}
