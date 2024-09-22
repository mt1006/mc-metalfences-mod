package com.mt1006.metalfences.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class IronFenceGateBlock extends FenceGateBlock
{
	private static final WoodType MATERIAL = new WoodType("metalfences:iron_fence_gate_wood", BlockSetType.IRON,
			SoundType.METAL, SoundType.HANGING_SIGN, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE);

	public IronFenceGateBlock(Properties properties)
	{
		super(MATERIAL, properties);
	}

	@Override protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
	{
		return InteractionResult.PASS;
	}
}
