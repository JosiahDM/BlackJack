package blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Deck() {
		createDeck();
	}
	
	public void createDeck() {
		
		for (Suit s : Suit.values()) {
	    	int val = 2;
	        for (Rank r : Rank.values()) {
	            if (r == Rank.JACK || r == Rank.QUEEN || r == Rank.KING) {
	            	val = 10;
	            }
	        	this.add(new Card(r, s, val++));
	        }
	    }
	}
	
	public void shuffleDeck() {
		Collections.shuffle(this);
	}
	
	public Card dealCard() {
		return this.remove(0);
	}
}
