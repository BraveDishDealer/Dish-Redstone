package com.dish.redstone.block.custom;

import com.dish.redstone.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Detector extends Block {
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    public Detector(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(ACTIVE, false));
        settings.strength(15,15);
        settings.velocityMultiplier(0.5f);
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if(state.get(ACTIVE)) return 15;
        else return 0;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.setBlockState(pos, state.with(ACTIVE, !state.get(ACTIVE)));
        return ActionResult.SUCCESS;
    }

    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        world.setBlockState(pos, state.with(ACTIVE, !state.get(ACTIVE)));
        super.onBlockBreakStart(state, world, pos, player);
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        world.setBlockState(hit.getBlockPos(), ModBlocks.BROKEN_DETECTOR.getDefaultState());
        if(projectile.getOwner() instanceof PlayerEntity){
            spawnBreakParticles(world, null, hit.getBlockPos(), state.with(ACTIVE, false));
            world.playSound(null, hit.getBlockPos(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1f, 1f);
        }
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(!state.get(ACTIVE)) world.setBlockState(pos, state.with(ACTIVE, true));
        super.onSteppedOn(world, pos, state, entity);
    }
}
