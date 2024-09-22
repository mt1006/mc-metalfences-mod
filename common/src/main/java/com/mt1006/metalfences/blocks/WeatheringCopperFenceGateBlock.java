package com.mt1006.metalfences.blocks;

import com.mt1006.metalfences.MetalFencesBlockAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
	public static final WoodType MATERIAL = new WoodType("metalfences:copper_fence_gate_wood", BlockSetType.COPPER,
			SoundType.COPPER, SoundType.HANGING_SIGN, SoundEvents.COPPER_DOOR_OPEN, SoundEvents.COPPER_DOOR_CLOSE);
	private final WeatherState weatherState;

	public WeatheringCopperFenceGateBlock(WeatherState weatherState, Properties properties)
	{
		super(MATERIAL, properties);
		this.weatherState = weatherState;
	}

	@Override protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
	{
		changeOverTime(state, level, pos, random);
	}

	@Override protected boolean isRandomlyTicking(BlockState state)
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

	@Override protected @NotNull ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos,
																 Player player, InteractionHand hand, BlockHitResult hitResult)
	{
		ItemInteractionResult result = MetalFencesBlockAccessor.useItemOnWeathering(itemStack, blockState, level, blockPos, player, hand);
		return result != null ? result : super.useItemOn(itemStack, blockState, level, blockPos, player, hand, hitResult);
	}
}
