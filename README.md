# Blackjack
##Week 3 Project at Skill Distillery

This week continued use of OOP with further exploration of the Java API including String manipulation, generics, and collections.


The project is a simple text-based blackjack game with random card selection, options to hit or stay, and automated dealer logic.


####Directions:

1. Compile and run from the BlackJackGame file.

2. Enter your name.

3. Cards will be dealt automatically.

4. Enter your choice of 'hit' or 'stay'.

5. Dealer will automatically run and winner will be calculated.


####Class Structure Overview:

- Card.java models a card. Each card has a Rank, Suit, and Value. Rank and Suit are Enums.
- Deck.java models a deck. Deck is-a list therefore it extends ArrayList. The Deck class populates itself with all cards when initialized.
- Hand.java models a hand, which is-a list of cards and also extends ArrayList. The Hand class has methods to determine the value of an instance of a hand.
- Player.java models a player. Players have names and hands. The automated dealer is also an object of Player.
- BlackJackGame.java contains the main method and game logic. It instantiates all of the required objects and manipulates them in order to run the game and determine when the game is over.
