/*
 * Steam 'n' Rails
 * Copyright (c) 2022-2024 The Railways Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.railwayteam.railways.util.packet;

import com.railwayteam.railways.content.conductor.ConductorEntity;
import com.railwayteam.railways.multiloader.C2SPacket;
import com.railwayteam.railways.registry.CRPackets;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class DismountCameraPacket implements C2SPacket {
    public DismountCameraPacket() {}
    public DismountCameraPacket(FriendlyByteBuf buf) {}
    @Override
    public void write(FriendlyByteBuf buffer) {}

    @Override
    public void handle(ServerPlayer sender) {
        if (sender.getCamera() instanceof ConductorEntity conductor) {
            conductor.stopViewing(sender);
        } else {
            CRPackets.PACKETS.sendTo(sender, new SetCameraViewPacket(sender));
        }
    }
}
