package com.mt1006.metalfences;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

public abstract class MetalFencesBlockAccessor
{
	protected static MetalFencesBlockAccessor ACCESSOR = null;

	public abstract @Nullable Block getNextWeathered(Block block);
	protected abstract @Nullable Block getNextUnweathered(Block block);
	protected abstract @Nullable Block getWaxed(Block block);
	protected abstract @Nullable Block getUnwaxed(Block block);

	public static @Nullable Block nextWeathered(Block block)
	{
		if (ACCESSOR == null) { throw new RuntimeException(); }
		return ACCESSOR.getNextWeathered(block);
	}

	public static @Nullable InteractionResult useItemOnWeathering(ItemStack itemStack, BlockState blockState, Level level,
																  BlockPos blockPos, Player player, InteractionHand hand)
	{
		if (ACCESSOR == null) { throw new RuntimeException(); }
		Block block = blockState.getBlock();

		if (itemStack.is(Items.HONEYCOMB))
		{
			Block newBlock = ACCESSOR.getWaxed(block);
			if (newBlock == null) { return null; }
			BlockState newBlockState = newBlock.withPropertiesOf(blockState);

			if (player instanceof ServerPlayer)
			{
				CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, itemStack);
			}

			if (!player.isCreative()) { itemStack.shrink(1); }
			level.setBlock(blockPos, newBlockState, Block.UPDATE_ALL_IMMEDIATE);
			level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, newBlockState));
			level.levelEvent(player, LevelEvent.PARTICLES_AND_SOUND_WAX_ON, blockPos, 0);

			player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		else if (itemStack.getItem() instanceof AxeItem)
		{
			Block newBlock = ACCESSOR.getNextUnweathered(block);
			if (newBlock == null) { return null; }
			BlockState newBlockState = newBlock.withPropertiesOf(blockState);

			level.playSound(player, blockPos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0f, 1.0f);
			level.levelEvent(player, LevelEvent.PARTICLES_SCRAPE, blockPos, 0);

			if (player instanceof ServerPlayer)
			{
				CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, itemStack);
			}

			level.setBlock(blockPos, newBlockState, Block.UPDATE_ALL_IMMEDIATE);
			level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, newBlockState));

			itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
			player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
			return InteractionResult.sidedSuccess(level.isClientSide);
		}

		return null;
	}

	public static @Nullable InteractionResult useItemOnWaxed(ItemStack itemStack, BlockState blockState, Level level,
															 BlockPos blockPos, Player player, InteractionHand hand)
	{
		if (ACCESSOR == null) { throw new RuntimeException(); }
		if (itemStack.getItem() instanceof AxeItem)
		{
			Block newBlock = ACCESSOR.getUnwaxed(blockState.getBlock());
			if (newBlock == null) { return null; }
			BlockState newBlockState = newBlock.withPropertiesOf(blockState);

			level.playSound(player, blockPos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0f, 1.0f);
			level.levelEvent(player, LevelEvent.PARTICLES_WAX_OFF, blockPos, 0);

			if (player instanceof ServerPlayer)
			{
				CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, itemStack);
			}

			level.setBlock(blockPos, newBlockState, Block.UPDATE_ALL_IMMEDIATE);
			level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, newBlockState));

			itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
			player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		return null;
	}
}
