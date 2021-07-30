package subaraki.pga.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import subaraki.pga.mod.ScreenMod;

public class CapabilityProvider implements ICapabilitySerializable<CompoundTag> {

    /**
     * Unique key to identify the attached provider from others
     */
    public static final ResourceLocation KEY = new ResourceLocation(ScreenMod.MODID, "screenviewmod");

    /**
     * The instance that we are providing
     */
    final ScreenData data = new ScreenData();

    /**
     * Gets called before world is initiated. player.worldObj will return null here
     * !
     */
    public CapabilityProvider(Player player) {

        data.setPlayer(player);
    }

    @Override
    public CompoundTag serializeNBT() {

        return new CompoundTag(); // (CompoundNBT) ScreenCapability.CAPABILITY.writeNBT(data, null);
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

        // ScreenCapability.CAPABILITY.readNBT(data, null, nbt);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {

        if (cap == ScreenCapability.CAPABILITY)
            return (LazyOptional<T>) LazyOptional.of(this::getImpl);

        return LazyOptional.empty();
    }

    private ScreenData getImpl() {

        if (data != null) {
            return data;
        }
        return new ScreenData();
    }

}
