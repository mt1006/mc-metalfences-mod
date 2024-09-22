package com.mt1006.metalfences.blocks;

import com.mt1006.metalfences.MetalFencesBlockAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class WaxedCopperFenceGateBlock extends FenceGateBlock
{
	public WaxedCopperFenceGateBlock(Properties properties)
	{
		super(properties, WeatheringCopperFenceGateBlock.MATERIAL);
	}

	@Override public @NotNull InteractionResult use(BlockState blockState, Level level, BlockPos blockPos,
													Player player, InteractionHand hand, BlockHitResult hitResult)
	{
		InteractionResult result = MetalFencesBlockAccessor.useItemOnWaxed(player.getItemInHand(hand), blockState, level, blockPos, player, hand);
		return result != null ? result : super.use(blockState, level, blockPos, player, hand, hitResult);
	}
}
