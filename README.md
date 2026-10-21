The Cooke Jar Readme

IMPORTANT NOTE: TO ACCESS THE FILES, YOU NEED TO LOOK AT SRC/MAIN. THAT CONTAINS THE RESOURCES AND JAVA. JAVA CONTAINS THE CLASSES AND RESOURCES CONTAINS THE IMAGES.

Team Name: The Cookie Jar Team

An important note: I didn't write this code alone. I had the help from my partner Ofer and then Jake who helped from time to time. 
Thankfully, they let me stamp my name on it since I did most of the work on this project, but I still wouldn't want any of the work they did
to go unheard. Even now there was a lot of work to be done because most of the files malfunctioned when I transferred them from Eclipse to VS code
and also from school repository to my home repository.

Team member Names: Siddarth Iyer, Ofer Koren, Jake Jiang

Revision Date: The most recent date the document was updated or changed (10/11/2026). 

Program Purpose: After running this program, a window will appear allowing the user to play a role-playing game (RPG). The purpose is to entertain the user. 

Target Audience: Anybody interested in exploration, action, and Sci-Fi. Most likely a younger audience that appreciates the fun, light hearted-ness and adventure of simple RPGs.

Feature List: List and describe the purpose and function of each key program feature.
Moveability: Players will be able to use WASD to move in 4 directions.
Actions: Players will be able to drink potions using the number P button. The player will be able to use their weapon using the space key. The player will be able to use items using the number 1 and 2 keys. Perhaps dash as well
Equipability: The player will be able to equip different types of armor and weapons.
Sightseeing: The player will be able to witness different types of interesting environments and fight 4 types of enemies and talk to 4 different npcs.
Shopping: Players will be able to buy potions to heal themselves in battle as well as buy a sword to protect themself.
Death punishment: The player will lose resources once their health bar reaches 0. 
Unique Fight Sequencing: The enemies will each have their own fighting mechanism

Instructions: Run the program by running the Main file. Then interact with the “start game” NPC. Explore the game and progress through rooms by using the controlling keys W, A, S, D. Use the potion slot with P. Interact with NPCs with E. Use key slots with 1 and 2. Attack with space. B to buy potions

Completion of the game results in options to either restart or go back to the initial menu. 
Death results in losing EVERYTHING. One life only. 

Class List:
Armor - Stores symbolic constants for armor values and models.
BovisFight - Contains Bovis’ speed, damage, detection range, health, and model.
BovisTalk -  Contains Bovis’ coordinates and model.
Dialogue - Contains dialogue animations, timings, and buttons. 
EliteGuard - Contains Elite Guards’ speed, damage, detection range, health, and model.
Enemy - Parent class for enemy speed, damage, detection range, health, and model.
EnemyTester - Tests enemies with a debug menu.
GamePanel - Responsible for creating the background, player, and NPC objects. Also creates custom game icon
Grandpa - Contains Grandpa’s coordinates and model.
Hotbar - Contains model and logic for the hotbar.
Infarctus - Contains Infarctus’ speed, damage, detection range, health, and model.
Item - contains all the items that are necessary for the game
Knight -  Contains the Knights’ speed, damage, detection range, health, and model.
Main - Responsible for the player object, created the windows and panels, and inputs.
Map - Creates different backgrounds and stores their collision values.
NPC - Parent class for NPC coordinates and model.
Player - Handles movement, items, and the player model.
Shop - Contains the shop’s coordinates, model, and stock.
Weapon - Contains symbolic constants for weapons and their models.
Cloak - Contains symbolic constants for cloaks and their models.
Boss Music - Loads some music from memory and allows it to play without any problems
Bovine - makes methods for Game Panel to call like play and pause so it can control when the music plays and doesn’t play
Inventory creates an array for the inventory
HotbarSlot creates an array for the hotbar itself
Keybarslot also creates an array

The point of the last 3 classes is to create distinct arrays because they each handle different item types. Is this the way to do it? Maybe not, but does it work? Sure does.


Team Responsibility: 
Armor - Sid
BossMusic - Sid
Bovine - Sid
BovisFight - Jake
BovisTalk - Ofer
Dialogue - Ofer
Numbers - Ofer & Sid
EliteGuard - Jake & Ofer & Sid
Enemy - Jake & Ofer & Sid
EnemyTester - Jake & Ofer & Sid
GamePanel - Ofer
Grandpa - Ofer
Hotbar - Sid
HotbarSlot - Sid
Cloak - Sid
KeybarSlot - Sid
Inventory - Sid
Infarctus - Jake
Item - Sid
Knight -  Jake
Main - Ofer & Sid
Map - Sid
NPC - Ofer
Player - Ofer & Sid
Shop - Ofer
Weapon - Sid

Known Bugs/Workarounds: 
Cannot add the main menu background in Main. 
Workaround: Add menu backgrounds in the new Main class.
Debug WASD Movement displayed incorrect letters in the console.
Workaround: Made isPressingA output instead of isPressingW.
The player shifts when starting to move left/right.
Workaround: Fixed player animations to account for sprite size difference.
The game crashes when the player moves perpendicular in doorways.
Workaround: Make collision independent of the last button pressed.
The player cannot escape room 5.
Workaround: Reconfigure the switch case to allow the escapee to make a run for it.
The file only correctly runs if the GamePainter class is specifically run.
Make the GamePainter class the Main class
Player can only attack while facing downwards.
Workaround: Add functionality and models for attacking in all directions.
The UI can move around from time to time, showing the white background.
Workaround: Set background and panel to black.
Grandpa leaps out of bed when shown as interactable.
Workaround: Redid grandpa’s original model and coordinates.
Player cannot escape the abyss
Workaround: Added placeholder background models. 
Enemies and player have their coordinates in the corner of their models.
Workaround: Add an offset in the Main class.
Player must get very close to enemies on the left and right side to hit them.
Workaround: Add default attack range when facing left and right.
After adding in the upper UI, all game objects are too high on the screen.
Workaround: Increase y-coordinates of all objects by 171.
Players can begin talking with the last NPC they talked with even if they aren’t in its range.
Workaround: Make the currentNPC variable to “” after each NPC finishes talking.
The player dies before it’s able to touch the enemy. 
Workaround: Change hitboxes
Numbers in the shop didn’t appear due to it being a part of dialogue.
Workaround: Redraw them over the dialogue
The UI got all messed up
 turns out the x and y coordinates were all messed up for some reason in the transfer so fixed that.
Some images were not showing up, for example the potion. 
Workaround, spelled it correctly

Presentation Link:
https://canva.link/tn2u9fs192sb5bh

Key Learnings: 
Programming and art takes a lot longer than you think it will.
Don’t overreach because you will have to bend over backwards to fit it into your schedule and without help, may be a lot more than you can chew
Thought 3 days would be enough for the items. Long story short, it wasn’t. You’ll still need to keep making items and things along the way. Also, player and enemy animations are HARD due to art and rescaling. So do it early or don’t do it at all. NEVER SAVE THESE THINGS FOR LAST MINUTE!!!!
If a variable or method is static, it belongs to a class. Otherwise, you can use an object with accessor and/or mutator methods.
Code can crash surprisingly easily. Sometimes Eclipse will give a fake reason for tweaking out. If it doesn’t make sense, then it probably is a different mistake and probably also one that is very stupid. Check the code THOROUGHLY!!!!
Do not pay attention to details nor polish early on.
When starting out, place items in alphabetical order since many automated programs will do so as well. This can be seen in listing classes.
Long story short: This is not a project that one can procrastinate. Even while diligently working, we’re still running short on time. 


Credit List: 
DrawAndScale - Used for painting and panel classes to make visuals appear.
AnimationDemo - Used for key detection for the player.
SoundsAndSprites - Used for Music

Honorable Mentions:
Sid - Created the music and some of the art like the backgrounds, the potion, the boss, and the enemies
Ofer - Created some of the art like the UI and the Player itself.


