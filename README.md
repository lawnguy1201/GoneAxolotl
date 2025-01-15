# GoneAxolotl 
![GitHub Downloads (all assets, all releases)](https://img.shields.io/github/downloads/lawnguy1201/GoneAxolotl/total?style=for-the-badge&logo=Github&color=blue)
![GitHub last commit](https://img.shields.io/github/last-commit/lawnguy1201/GoneAxolotl?style=for-the-badge&logo=Github&color=pink)
![GitHub Repo stars](https://img.shields.io/github/stars/lawnguy1201/GoneAxolotl?style=for-the-badge&logo=Github&color=red)

GoneAxolotl is a Minecraft data miner specializing in getting banner and sign data from Minecraft Region files from World Downloads (wdl's); the program can also get biome, block, and chunk timestamp data.
GoneAxolotl was made over the course of 8 months specifically for Maxdog006's 25k End wdl; with the goal of getting the nbt sign and banner data from a big wdl (over 10,000 region files).

Big thanks to lamp and stalpo for some help fixing some bugs and pointing me in the right direction.

# Fetures 
- Handle large amounts of region files
- Get:
  - Block data
  - Biome data
  - Sign data
  - Banner data
  - Chunk Timestamps
-  Calculate region file names based on cartesian coordinates
-  output data into CSV files
-  Console-based

# What To Do With The Data 
- You have two options:
  - create your own visualizer
    - Best languages ```Matlab``` and ```Python```
  - Use the ```Sightseer``` the visualizer made specially for this project
  - https://github.com/lawnguy1201/Sightseer

# How Does the NBT System Work 
Named Binary Tag (NBT) is a tree data structure used to store, game data from the Minecraft world. NBT data only has 13 different tag types with a number associated with them ranging from 0-12. 
Since The data is in a tree structure the data will be hierarchical; there is a root tag at the top and all other tags atr below and branches out like root to sections... 
 ![](https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.minecraftforum.net%2Fforums%2Fmapping-and-modding-java-edition%2Fminecraft-tools%2F1262665-nbtexplorer-nbt-editor-for-windows-and-mac&psig=AOvVaw3pMtYarww9gBRaYwETjhE6&ust=1737050168803000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCMjAuaqm-IoDFQAAAAAdAAAAABAj)

# dependencies
- Java 8+ Next update will be Java 22+
- Any ide (I used NetBeans 22)
- Build the lib .jar from Querz/NBT https://github.com/Querz/NBT (Note I will provide an already built .jar)
  - if you do build the lib yourself you need to Change Chunk.java L73 from ```CompoundTag level;``` to ```CompoundTag level = data;``` and remove Chunk.java L74-L76 (https://github.com/Querz/NBT/issues/95)

# How To Use The Program 
- First, make sure you have all the dependencies
- Load the project into your ide
- Put the lib .jar into the correct lib folder for your ide
- Run the program and follow the directions in the console
  - For those who dont know what a path is read this https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/path
 
# How to Contribute 
- Create a fork of the project
- Make the changes to the code
- Create a pull request with detailed comments on what you changed

# Future Updates 
If I have the time I would like to add these things to better the program
- A re-write of the whole program to create a better design and fix some of the bad code that is written
- Some type of GUI or create the program into an application so the project is not console-based
- Optimization
- Some type of better system to keep track of what files were done to make it easier for larger downloads
- Implement Better data structures for better memory allocation and management 
