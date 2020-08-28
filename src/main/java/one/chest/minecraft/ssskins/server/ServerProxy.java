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

import net.minecraft.server.dedicated.PropertyManager;
import net.minecraftforge.common.MinecraftForge;
import one.chest.minecraft.ssskins.Settings;
import one.chest.minecraft.ssskins.common.CommonProxy;

import java.io.File;

/**
 * Created by ruslanmikhalev on 27/01/18.
 */
public class ServerProxy extends CommonProxy {

    @Override
    public void registerSettings() {
        PropertyManager propertyManager = new PropertyManager(new File("config/ssskins.properties"));
        String skinsLocation = propertyManager.getStringProperty("ssskins-url", "http://skins.minecraft.net/MinecraftSkins");
        Settings.setInstance(new Settings(skinsLocation));
    }

    @Override
    public void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new ServerPlayerEventsObserver());
    }

}
