package blackjack;

public class Card {
    private Rank rank;
    private Suit suit;
    private int value;

    public Card(Rank r, Suit s, int value) {
        rank = r;
        suit = s;
        this.value = value;
    }

    public int getValue() {
    	return this.value;
    }
    
    public Rank getRank() {
    	return rank;
    }
    
    
    @Override
    public String toString() {
        return (rank + " of " + suit).toLowerCase();
    }
    
    
}