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

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.minecraft.entity.player.EntityPlayer.getOfflineUUID;

/**
 * Created by ruslanmikhalev on 27/01/18.
 */
public class TextureLocator {

    @SuppressWarnings("unchecked")
    public void loadTexture(EntityPlayer entityPlayer) {
        NetHandlerPlayClient connection = Minecraft.getMinecraft().getConnection();
        if (connection == null) {
            return;
        }
        NetworkPlayerInfo networkPlayerInfo = connection.getPlayerInfo(entityPlayer.getUniqueID());
        if (networkPlayerInfo == null) {
            return;
        }
        try {
            Field[] fields = networkPlayerInfo.getClass().getDeclaredFields();
            List<Field> list = fetchFieldsWithType(fields, Map.class);

            if (list.size() != 1) {
                throw new IllegalStateException(String.valueOf(list.size()));
            }
            Field field = list.get(0);

            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            Map playerTextures = (Map) field.get(networkPlayerInfo);
            playerTextures.put(MinecraftProfileTexture.Type.SKIN, getTextureFor(entityPlayer.getName()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Field> fetchFieldsWithType(Field[] fields, Class<Map> clazz) {
        List<Field> list = new ArrayList<>();
        for (Field field : fields) {
            if (field.getType() == clazz) {
                list.add(field);
            }
        }
        return list;
    }

    public ResourceLocation getTextureFor(String playerName) {
        ResourceLocation resourceLocation = AbstractClientPlayer.getLocationSkin(playerName);
        loadImageSkin(resourceLocation, Settings.getInstance().getSkinsLocation(), playerName);
        return resourceLocation;
    }

    public static void loadImageSkin(ResourceLocation resourceLocationIn, String url, String username) {
        net.minecraft.client.renderer.texture.TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
        ITextureObject textureObject = textureManager.getTexture(resourceLocationIn);

        if (textureObject == null) {
            textureObject = new ThreadDownloadImageData(
                    (File) null,
                    String.format("%s/%s.png", new Object[]{url, StringUtils.stripControlCodes(username)}),
                    DefaultPlayerSkin.getDefaultSkin(getOfflineUUID(username)),
                    new ImageBufferDownload()
            );
            textureManager.loadTexture(resourceLocationIn, textureObject);
        }
    }

}
