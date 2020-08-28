# ![GitHub Logo](images/logo.svg) Server Side Skins 

Mod allows to set custom skins location url on the server side.

Mod uses "http://skins.minecraft.net/MinecraftSkins" path by default.

*ATTENTION! MOJANG no longer uses this path to store skins. You must specify your own*

You can use GitHub as an HTTP server.
For example:

Create repo with name minecraft_skins
1. Put skin player.png into root folder in your repo
2. Set URL in the ./config/ssskins.properties to https://raw.githubusercontent.com/<your_github_username>/<repository>/<branch>
3. A user with the nickname player will get skin player.png.
Each png file that falls into this repository will be associated with the player's name.

## Requirements

* [Minecraft Server](https://minecraft.net/ru-ru/download/server)
* Compatible version of [Minecraft Forge](https://files.minecraftforge.net/) installed on your Minecraft Server

## Installation

1. Go to [project page](https://minecraft.curseforge.com/projects/ssskins) on curseforge 
2. Download latest version from "Files" tab
3. Put file into /mods directory in server folder  


## Configuration

Specify ssskins-url in the ./config/ssskins.properties for changing the skin url, e.g:

    # ssskins.properties
    ssskins-url=http://chest.one/skins

For a user with a nickname DNAlchemist the skin should be available by URL: 

http://chest.one/skins/DNAlchemist.png

