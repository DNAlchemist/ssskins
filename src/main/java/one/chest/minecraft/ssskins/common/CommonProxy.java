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
package one.chest.minecraft.ssskins.common;

import net.minecraftforge.fml.relauncher.Side;
import one.chest.minecraft.ssskins.Proxy;
import one.chest.minecraft.ssskins.network.NetworkHandler;

/**
 * Created by ruslanmikhalev on 27/01/18.
 */
abstract public class CommonProxy implements Proxy {

    @Override
    final public void init() {
        registerSettings();
        registerEvents();
        registerPackets();
    }

    @Override
    final public void registerPackets() {
        NetworkHandler networkHandler = NetworkHandler.getInstance();
        networkHandler.getNetwork().registerMessage(networkHandler, NetworkHandler.ServerSettingsMessage.class, 99, Side.CLIENT);
    }

}
