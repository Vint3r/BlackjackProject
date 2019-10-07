## Blackjack
---
Fourth weekend project for Skill Distillery, a coding boot camp.

### Overview
---
This code tries to simulate a Blackjack table for any friendly game of Blackjack. It starts by creating the dealer and along with him, most of whatever else the code will need moving forward. It does this by just calling and creating other classes in his code to create them (such as the deck), or they are created in the main method by the same means. After the hand lists and deck have been created the game simply asks if you would like to play, which it will come back to after every hand. If you say yes it will deal the cards for both the dealer and the player and the game starts. After that a simple game of Blackjack is played.

### Technologies/Topics used
---
This code is made up of 6 classes and 2 enum classes;
* The enum classes are as follows;
  * One for the rank/value of the cards in the deck.
  * One for the suit of the cards.
* One abstract class for both the player and dealer, which are themselves they're own concrete classes.
* One class to represent a singular card
* One class to represent a deck of 52 playing cards.
* Finally, a application class to provide a interface for the actual player to use for using the program.

The program starts by saying hello then hoping into a launch method where it finishes setting up what it needs to and asking if the user would like to play a round. If the user types yes it moves on to actually playing the game, if they say no the program ends there. After the round starts it has a new method it calls to print the menu system and deals the cards to the player and dealer. It checks for a Blackjack for both parties and if not found it moves on through the rest of the app. After the initial dealing of card, the app will check if the player has the option to split.  If the place can it changes how the menu is displayed to reflect that. After that it proceeds much like a real Blackjack game, having the player do his turn until they either choose to stay or busts before the dealer plays. When the player is done the dealer plays until it too either busts or passes its must stand variable of 17 and he stands.

### Lessons Learned
---
So i started off using a abstract method for the dealer and player class, I knew they were going to have a lot of similar methods so putting them in a abstract class would really help me in more way then one. I find I still have some issues with using them and get a bit turned around so I really wanted to get myself to use at least one to continue to work with them.

I also had a bit of difficulty with some of the logic for the dealer and win conditions. The dealers turn has been a whole mess of bugs and errors, starting with falling into an endless loop or even not taking a turn at all. I did resolve these issues but everything is in nested if statements to check for stuff like busting and for if he has a ace or not. So picking them apart to see why it wasn't working got a bit confusing from time to time, some just were not in the right spots or I put the wrong variables in to compare, and so on.

The logic for the win conditions are also the same, originally it was a few more if statements with some nesting inside but it would often print the wrong winner or the wrong winning line so in the end I had to tear it all down and start over again from scratch with it. Which ended up being the right call because the code there now is much easier to read, more compact, and actually works! Splits also were a bit difficult for me, most of my logic was set up to handle single lists for method calls and the like, so I ended up having to create another two or three methods to specifically handle the maps I made for the splits.
