package blackjack;

public enum Suit {
    HEARTS, SPADES, CLUBS, DIAMONDS;
	
	
	public String toString() {
		switch (this) {
		case HEARTS:
			return "♥";
		case SPADES:
			return "♠";
		case CLUBS:
			return "♣";
		case DIAMONDS:
			return "♦";
		default:
			return "";
		}
	}
}
