# ![GitHub Logo](images/logo.svg) Server Side Skins 

Mod allows to set custom skins location url on the server side.

Mod uses "http://skins.minecraft.net/MinecraftSkins" path by default.

## Requirements

* [Minecraft Server](https://minecraft.net/ru-ru/download/server)
* Compatible version of [Minecraft Forge](https://files.minecraftforge.net/) installed on your Minecraft Server

## Installation

1. Go to [project page](https://minecraft.curseforge.com/projects/ssskins) on curseforge 
2. Download latest version from "Files" tab
3. Put file into /mods directory in server folder  


## Configuration

Specify ssskins.url in the server.property for changing the skin url, e.g:

    # server.properties
    ssskins.url=http://chest.one/skins

For a user with a nickname DNAlchemist the skin should be available by URL: 

http://chest.one/skins/DNAlchemist.png

