package net.toxevo.alcd.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.toxevo.alcd.block.entity.ModBlockEntities;
import net.toxevo.alcd.block.entity.custom.AlchemyTableBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

@SuppressWarnings("deprecation")
public class AlchemyTableBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public AlchemyTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 10);
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, Random random) {
        super.tick(blockstate, world, pos, random);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        alchemyTableParticles(world, x, y, z);
        alchemyTableParticles2(world, x, y, z);
        world.scheduleTick(pos, this, 10);
    }

    public static void alchemyTableParticles(LevelAccessor world, double x, double y, double z) {
        double xRadius;
        double loop;
        double zRadius;
        double particleAmount;
        loop = 0;
        particleAmount = 16;
        xRadius = 0.75;
        zRadius = 0.75;
        while (loop < particleAmount) {
            if (world instanceof ServerLevel _level)
                _level.sendParticles(ParticleTypes.ENCHANT,
                        (x + 0.5 + Math.cos(((Math.PI * 2) / particleAmount) * loop) * xRadius),
                        y + 0.75,
                        (z + 0.5 + Math.sin(((Math.PI * 2) / particleAmount) * loop) * zRadius),
                        0,
                        0,
                        0.05,
                        0,
                        1);
            loop = loop + 1;
        }
    }

    public static void alchemyTableParticles2(LevelAccessor world, double x, double y, double z) {
        double xRadius;
        double loop;
        double zRadius;
        double particleAmount;
        loop = 0;
        particleAmount = 8;
        xRadius = 0.25;
        zRadius = 0.25;
        while (loop < particleAmount) {
            if (world instanceof ServerLevel _level)
                _level.sendParticles(ParticleTypes.ENCHANT,
                        (x + 0.5 + Math.cos(((Math.PI * 2) / particleAmount) * loop) * xRadius),
                        y + 1.6,
                        (z + 0.5 + Math.sin(((Math.PI * 2) / particleAmount) * loop) * zRadius),
                        0,
                        0,
                        0,
                        0,
                        1);
            loop = loop + 1;
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof AlchemyTableBlockEntity) {
                ((AlchemyTableBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof AlchemyTableBlockEntity) {
                NetworkHooks.openGui(((ServerPlayer)pPlayer), (AlchemyTableBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AlchemyTableBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.ALCHEMY_TABLE_BLOCK_ENTITY.get(),
                AlchemyTableBlockEntity::tick);
    }
}