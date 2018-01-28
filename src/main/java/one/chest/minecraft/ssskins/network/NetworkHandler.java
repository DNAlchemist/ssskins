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
package one.chest.minecraft.ssskins.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import one.chest.minecraft.ssskins.SSSkins;
import one.chest.minecraft.ssskins.Settings;

import java.io.*;

/**
 * Created by ruslanmikhalev on 27/01/18.
 */
public class NetworkHandler implements IMessageHandler<NetworkHandler.ServerSettingsMessage, IMessage> {

    private static NetworkHandler instance;
    private SimpleNetworkWrapper networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(SSSkins.MOD_ID);

    synchronized public static NetworkHandler getInstance() {
        return instance != null ? instance : (instance = new NetworkHandler());
    }

    public SimpleNetworkWrapper getNetwork() {
        return networkWrapper;
    }

    @Override
    public IMessage onMessage(ServerSettingsMessage message, MessageContext ctx) {
        Settings.setInstance(message.getContent());
        return null;
    }

    public static class ServerSettingsMessage implements IMessage {

        private Settings content;

        @Override
        public void fromBytes(ByteBuf buf) {
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);

            try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                 ObjectInputStream input = new ObjectInputStream(bis)) {

                content = (Settings) input.readObject();

            } catch (IOException | ClassNotFoundException e) {
                throw new IllegalStateException("Read object error.", e);
            }
        }

        @Override
        public void toBytes(ByteBuf buf) {
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                 ObjectOutputStream output = new ObjectOutputStream(bos)) {

                output.writeObject(Settings.getInstance());
                buf.writeBytes(bos.toByteArray());

            } catch (IOException e) {
                throw new IllegalStateException("Write object error.", e);
            }
        }

        public Settings getContent() {
            if (content == null) {
                throw new IllegalStateException("Content is missing!");
            }
            return content;
        }
    }
}