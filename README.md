# Swingy
---
wnWN.,/?

## Description

A minimalistic Java RPG game that can be played either on a GUI or a CLI or even allow switching between them during gameplay. The program needs to follow the Model-View-Controller architecture, automated Maven build and validate user input through annotation validation(Hibernate). Game data is persisted to a MySQL database upon game exit or at any moment the user decides to save a game state.

## Game Requirements

* Java8 installed (and java and javac commands available on your terminal).
* Maven [download](https://maven.apache.org/download.cgi)
* MySQL Community Server 5+ [download](https://dev.mysql.com/downloads/mysql/)

## Gameplay

A player can choose between different hero types. 

When the player starts the game he has 2 options:
 * Create a hero
 * Select a previously created hero.
In either case, the player can see the hero stats:
• Hero name
• Hero class
• Level
• Experience
• Attack
• Defense
• Hit Points
Hero stats are affected by the hero level and artifacts. There are 3 types of artefacs:
• Weapon - increases the attack
• Armor - increases defense
• Helm - increases hit points
After choosing a hero the actual game begins. The hero needs to navigate a square
map with the size calculated by the formula (level-1)*5+10-(level%2). For example a
hero of level 7 will be placed on a 39X39 map.
The initial position of the hero is in the center of the map. He wins the game if he
reaches on of the borders of the map. Each turn he can move one position in one of the
4 four directions:



[build]: https://drive.google.com/uc?id=1VcSVBVmGNjo-J0ECZJm4V-b0Qop9qyDn  "maven build"
[console_exec]: https://drive.google.com/uc?id=1VcSVBVmGNjo-J0ECZJm4V-b0Qop9qyDn  "console execution"
[gui_exec]: https://drive.google.com/uc?id=18KQ93p_jhd-IP45Bi_RzmFMjwQiqpKH5  "gui execution"
