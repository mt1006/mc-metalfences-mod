package com.mt1006.metalfences.blocks;

import com.mt1006.metalfences.MetalFencesBlockAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class WeatheringCopperFenceGateBlock extends FenceGateBlock implements WeatheringCopper
{
	public static final WoodType MATERIAL = new WoodType("metalfences:copper_fence_gate_wood", BlockSetType.IRON,
			SoundType.COPPER, SoundType.HANGING_SIGN, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE);
	private final WeatherState weatherState;

	public WeatheringCopperFenceGateBlock(WeatherState weatherState, Properties properties)
	{
		super(properties, MATERIAL);
		this.weatherState = weatherState;
	}

	@Override public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
	{
		onRandomTick(state, level, pos, random);
	}

	@Override public boolean isRandomlyTicking(BlockState state)
	{
		return MetalFencesBlockAccessor.nextWeathered(state.getBlock()) != null;
	}

	@Override public @NotNull Optional<BlockState> getNext(BlockState blockState)
	{
		Block nextBlock = MetalFencesBlockAccessor.nextWeathered(blockState.getBlock());
		return nextBlock != null ? Optional.of(nextBlock.withPropertiesOf(blockState)) : Optional.empty();
	}

	@Override public @NotNull WeatherState getAge()
	{
		return weatherState;
	}

	@Override public @NotNull InteractionResult use(BlockState blockState, Level level, BlockPos blockPos,
													Player player, InteractionHand hand, BlockHitResult hitResult)
	{
		InteractionResult result = MetalFencesBlockAccessor.useItemOnWeathering(player.getItemInHand(hand), blockState, level, blockPos, player, hand);
		return result != null ? result : super.use(blockState, level, blockPos, player, hand, hitResult);
	}
}
