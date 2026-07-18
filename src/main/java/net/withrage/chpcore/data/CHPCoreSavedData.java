package net.withrage.chpcore.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

public class CHPCoreSavedData extends SavedData {

    private static final String DATA_NAME = "chp_core";

    private static final String FTF_PRESET_VERSION_KEY =
            "FromTheFogPresetVersion";

    private int fromTheFogPresetVersion;

    public CHPCoreSavedData() {
        this.fromTheFogPresetVersion = 0;
    }

    public static CHPCoreSavedData load(CompoundTag tag) {
        CHPCoreSavedData data = new CHPCoreSavedData();

        data.fromTheFogPresetVersion =
                tag.getInt(FTF_PRESET_VERSION_KEY);

        return data;
    }

    public static CHPCoreSavedData get(MinecraftServer server) {
        return server.overworld()
                .getDataStorage()
                .computeIfAbsent(
                        CHPCoreSavedData::load,
                        CHPCoreSavedData::new,
                        DATA_NAME
                );
    }

    public int getFromTheFogPresetVersion() {
        return fromTheFogPresetVersion;
    }

    public void setFromTheFogPresetVersion(int version) {
        this.fromTheFogPresetVersion = version;
        setDirty();
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putInt(
                FTF_PRESET_VERSION_KEY,
                fromTheFogPresetVersion
        );

        return tag;
    }
}