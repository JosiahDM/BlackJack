package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand extends ArrayList<Card> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addCard(Card card) {
		this.add(card);
	}
	
	public List<Card> getCardsInHand() {
		return this;
	}
	// Check for ace 
	public int hasAce() { 
		int val = 0;
		for (Card card : this) {
			if (card.getRank() == Rank.ACE) {
				val++;
			}
		} 
		return val;
	}
	
	public int getValueOfHand() {
		int value = 0;
		for (Card card : this) {
			value += card.getValue();
		}
		if (value > 21 && this.hasAce() > 0) {
			value -= (this.hasAce() * 10);
		}
		return value;
	}
	
	public Hand getCardsInRange(int startIndex, int endIndex) {
		Hand partial = new Hand();
		for(int i = startIndex; i < endIndex; i++) {
			partial.add(this.get(i));
		}
		return partial;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card : this) {
			sb.append(card);
		}
		return sb.toString();
	}
}
