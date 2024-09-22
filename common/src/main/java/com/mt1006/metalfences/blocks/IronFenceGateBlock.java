package com.mt1006.metalfences.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class IronFenceGateBlock extends FenceGateBlock
{
	public IronFenceGateBlock(Properties properties)
	{
		super(properties);
	}

	@Override public @NotNull InteractionResult use(BlockState blockState, Level level, BlockPos blockPos,
													Player player, InteractionHand hand, BlockHitResult hitResult)
	{
		return InteractionResult.PASS;
	}
}
