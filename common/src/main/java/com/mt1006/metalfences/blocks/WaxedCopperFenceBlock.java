package com.mt1006.metalfences.blocks;

import com.mt1006.metalfences.MetalFencesBlockAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class WaxedCopperFenceBlock extends FenceBlock
{
	public WaxedCopperFenceBlock(Properties properties)
	{
		super(properties);
	}

	@Override protected @NotNull ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos,
																 Player player, InteractionHand hand, BlockHitResult hitResult)
	{
		ItemInteractionResult result = MetalFencesBlockAccessor.useItemOnWaxed(itemStack, blockState, level, blockPos, player, hand);
		return result != null ? result : super.useItemOn(itemStack, blockState, level, blockPos, player, hand, hitResult);
	}
}
