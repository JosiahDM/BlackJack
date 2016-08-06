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
	
	public boolean hasAce() {
		for (Card card : this) {
			if (card.getRank() == Rank.ACE) {
				return true;
			}
		} 
		return false;
	}
	
	public int getValueOfHand() {
		int value = 0;
		for (Card card : this) {
			value += card.getValue();
		}
		if (value > 21 && this.hasAce()) {
			value -= 10;
		}
		return value;
	}
	
	// maybe remove this. Replace with good toString method.
	public void display() {
		for (Card card : this) {
			System.out.println(card);
		}
	}
}
