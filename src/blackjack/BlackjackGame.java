package blackjack;

import java.util.Scanner;

public class BlackjackGame {
	private Deck deck;
	private Player player;
	private Player dealer;
	public final int DEALER_HIT_VALUE = 17; // dealer must hit if card value less than this

	public BlackjackGame() {
		deck = new Deck();
		dealer = new Player("Dealer");
	}

	public void shuffleDeck() {
		deck.shuffleDeck();
	}

	public void initPlayer(Scanner sc) {
		System.out.println("Please enter your name: ");
		player = new Player(sc.next());
	}

	public void dealStartingCards() {
		for (int i = 0; i < 2; i++) {
			player.getHand().addCard(deck.dealCard());
			dealer.getHand().addCard(deck.dealCard());
		}
	}

	public String faceDown() {
    	String card = 	"\n ▁▁▁▁▁\n" +
			  			"|░░░░░░░░░|\n" +
		  				"|░░░░░░░░░|\n"    +
	  					"|░░░░░░░░░|\n" +
	  					"|░░░░░░░░░|\n" +
	  					"|░░░░░░░░░|\n"+
	  					" ▔▔▔▔▔";
    	return card;
	}
	
	public void displayHand(Player player, boolean hide) {
		if (player == dealer && hide == true) {
			Hand hand = player.getHand();
			System.out.println(player.getName() + " has " + hand.getCardsInRange(0, hand.size()-1) + faceDown());
		} else {
			System.out.println(	player.getName() + " has " + player.getHand() + 
							"\nEqual to: " + player.getHand().getValueOfHand());
		}
	}

	public String hitOrStay(Scanner sc) {
		String input = "";
		String result = "";
		System.out.println("Hit or Stay?");
		input = sc.next();
		switch (input.toLowerCase()) {
		case "h":
		case "hit":
			result = "hit";
			break;
		case "stay":
		case "s":
			result = "stay";
			break;
		default:
			System.out.println("Unknown choice. Type hit(h) or stay(s).");
			result = "";
		}
		return result;
	}

	public boolean busted(Player player) {
		if (player.getHand().getValueOfHand() > 21) {
			return true;
		}
		return false;
	}

	public boolean blackJack(Player player) {
		if (player.getHand().getValueOfHand() == 21) {
			System.out.println(player.getName() + " wins with " + player.getHand());
			return true;
		}
		return false;
	}

	public String playersTurn(Scanner sc) {
		boolean ok = true;
		String result = "";
		while (ok) {
			if ( player.getHand().size() > 4 && !busted(player) ) {
				result = "win";
				break;
			}
			result = hitOrStay(sc);
			if (result.equals("stay")) {
				result = "stay"; 	// break out of loop if they wish to stay 
				break;
			} else if (result.equals("hit")) {
				player.getHand().addCard(deck.dealCard()); // player chose to hit
				displayHand(player, false);
			}
			if (busted(player)) { // check if player busted, return "bust" if so
				result = "bust";
				ok = false;
			} else if (player.getHand().getValueOfHand() == 21) { // check for score of 21
				result = "stay"; 		// auto-return "stay" if so
				ok = false;
			} else {
				ok = true;
			}
		}
		return result;
	}
	
	public String dealersTurn() {
		displayHand(dealer, false);
		String result = "";
		int dealerHand = 0; // declare this for readability
		while (!result.equals("stay")) { // loop until dealer must stay
			dealerHand = dealer.getHand().getValueOfHand();
			// if dealers hand is < the hit value, must hit.
			if( dealerHand < DEALER_HIT_VALUE ){
				System.out.println(dealer.getName() + " must hit!");
				dealer.getHand().addCard(deck.dealCard());
				if (busted(dealer)) { // exit loop if busted
					displayHand(dealer, false);
					result = "bust";
					break;
				}
			} else { // else dealer stays, loop condition becomes false. Return "stay".
				displayHand(dealer, false);
				System.out.println(dealer.getName() + " stays.");
				result = "stay";
			}
		}
		return result;
	}
	
	public boolean runPlayerLogic(Scanner keyboard) {
		String playersResult = playersTurn(keyboard);
		if (playersResult.equals("bust")) {
			System.out.println("Bust! You lose!");
			return false;
		} else if (playersResult.equals("win")) {
			System.out.println("5 cards! You win!");
			return false;
		}
		return true;
	}
	
	public boolean runDealerLogic() {
		String dealersResult = dealersTurn();
		if (dealersResult.equals("bust")) {
			System.out.println("Dealer busts! You win!");
			return false;
		}
		return true;
	}
	
	public void calcWin() {
		if (dealer.getHand().getValueOfHand() > player.getHand().getValueOfHand()) {
			System.out.println("Dealer wins.");
		} else if (dealer.getHand().getValueOfHand() < player.getHand().getValueOfHand()) {
			System.out.println("Player wins!");
		} else {
			System.out.println("Draw. Nobody wins.");
		}
	}
	
	public void start(Scanner keyboard) {
		shuffleDeck();
		dealStartingCards();
		displayHand(player, false);
		displayHand(dealer, true);
		// Immediately check for blackjack. If so, player wins automatically.
		if (blackJack(player)) {
			System.out.println("Blackjack! You win!");
			return;
		}
		if (!runPlayerLogic(keyboard)) {
			return;
		}
		if (!runDealerLogic()) {
			return;
		}
		calcWin();
	}
	
	
	// Getters and Setters
	public Player getPlayer() {
		return player;
	}

	public Player getDealer() {
		return dealer;
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		BlackjackGame game = new BlackjackGame();
		game.initPlayer(keyboard);
		
		game.start(keyboard);
		
		keyboard.close();

	}
}
