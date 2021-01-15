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
package one.chest.minecraft.ssskins;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by ruslanmikhalev on 09/04/17.
 */
@Mod(SSSkins.MOD_ID)
public class SSSkins {

    public static final String MOD_ID = "ssskins";
    public static final String NAME = "Server Side Skins";
    public static final String VERSION = "0.0.1";

    @SidedProxy(
            clientSide = "one.chest.minecraft.ssskins.client.ClientProxy",
            serverSide = "one.chest.minecraft.ssskins.server.ServerProxy"
    )
    private static Proxy proxy;

    @Mod.EventHandler
    void init(FMLInitializationEvent event) {
        proxy.init();
    }


}