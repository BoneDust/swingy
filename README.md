# Swingy

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

1. Build the project by running the command below in the root of your project folder.
This needs to generate a runnable swingy.jar file that can launch the game.

&nbsp;&nbsp;&nbsp;![build] 

2. Setup MySQL server on localhost port 3308 with user = *root* and password = *password*. 

3. Launh the game. Remember that the game can be launched in two modes, console and gui.

&nbsp;&nbsp;&nbsp;![console_exec]
   
&nbsp;&nbsp;&nbsp;![gui_exec]

## Play

* #### Start Menu
&nbsp;&nbsp;![main]

* #### Create New Player
&nbsp;&nbsp;![create]

* #### Select Saved Player
&nbsp;&nbsp;![saved]

* #### Play
&nbsp;&nbsp;![play]





 

[build]: https://drive.google.com/uc?id=1VBKSTGajziRk8FeM93f1KAymwalJciGi  "maven build"
[console_exec]: https://drive.google.com/uc?id=1Kr59H27O1zrbblIWZ_ObcJPmNNBETVPr  "console execution"
[gui_exec]: https://drive.google.com/uc?id=1q-0Nw2cbLPSEClCey-mJYFrouCsuNIjz  "gui execution"
[main]: https://drive.google.com/uc?id=1RBRZtyysRQegMQZIfCgKpV1xgZ2WJ2BQ  "start menu"
[create]: https://drive.google.com/uc?id=1ZHmCmcqMeKXb2M1f8VN7vjZZg-a9QEyv  "create player"
[saved]: https://drive.google.com/uc?id=1ct6n-TND_kS7y8BLJHHZYoGwbs3Ze4cw  "select saved player"
[play]: https://drive.google.com/uc?id=1lCLCToi9NwbNBv421gAjxGetkkPUCS8F  "play"


