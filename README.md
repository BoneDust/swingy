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

A player can choose between three different hero types. There's Swordsman, Gunman and KungFu Master. Each with their own different stats.

When the player starts the game he has 2 options:
 * Create a hero
 * Select a previously created hero.
 
In either case, the player can see the hero stats:
 * Hero name
 * Hero class
 * Level
 * Experience
 * Attack
 * Defense
 * Hit Points

Hero stats are affected by the hero level and artifacts. There are 3 types of artefacs:
 * Weapon - increases the attack
 * Armor - increases defense
 * Helm - increases hit points

After choosing a hero the actual game begins. The hero needs to navigate a square map with the size calculated by the formula  *((level - 1) * 5) + 10 - (level % 2)*. 
For example a hero of level 7 will be placed on a 39 X 39 map.
The initial position of the hero is in the center of the map. He wins the game if he reaches on of the borders of the map. Each turn he can move one position in one of the four directions:
 * North
 * East
 * South
 * West

When a map is generated, villains of varying power will be spread randomly over the
map. When a hero moves to a position occupied by a villain, the hero has 2 options:
 * Fight, which engages him in a battle with the villain
 * Run, which gives him a 50% chance of returning to the previous position. If the
odds aren’t on his side, he must fight the villain.

The battle between the hero and villain will be simulated and the outcome will be presented to the user.
If a hero looses a battle, he dies and loses the mission.
If a hero wins a battle, he gains:
 * Experience points, based on the villain power. Of course, he will level up if he
reaches the next level experience.
 * An artifact, which he can keep or leave. Of course, winning a battle doesn’t guarantee that an artefact will be droped and the quality of the artefact also varies
depending on the villain’s strength.

Leveling up is based on the following formula *(level * 1000) + ((level − 1)^2) * 450*. So the
necessary experience to level up will follow this pattern:
 * Level 1 - 1000 XP
 * Level 2 - 2450 XP
 * Level 3 - 4800 XP
 * Level 4 - 8050 XP
 * Level 5 - 12200 XP
 
## Build and Launch

Build the project running the command bellow in the root of your project folder.
This needs to generate a runnable swingyt.jar file that can launch the game
 

[build]: https://drive.google.com/uc?id=1VcSVBVmGNjo-J0ECZJm4V-b0Qop9qyDn  "maven build"
[console_exec]: https://drive.google.com/uc?id=1VcSVBVmGNjo-J0ECZJm4V-b0Qop9qyDn  "console execution"
[gui_exec]: https://drive.google.com/uc?id=18KQ93p_jhd-IP45Bi_RzmFMjwQiqpKH5  "gui execution"
