/*
 * Copyright 2018 Mikhalev Ruslan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package one.chest.minecraft.ssskins.server;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Created by ruslanmikhalev on 27/01/18.
 */
public class ServerPlayerEventsObserver {

    private ServerManager serverManager = new ServerManager();

    @SubscribeEvent
    void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent playerLoggedInEvent) {
        if (!(playerLoggedInEvent.player instanceof EntityPlayerMP)) {
            throw new IllegalStateException(playerLoggedInEvent.player.getClass().toString());
        }
        serverManager.sendServerSettings((EntityPlayerMP) playerLoggedInEvent.player);
    }

}
