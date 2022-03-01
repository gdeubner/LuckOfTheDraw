package models;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private int placeInDeck;
    private ArrayList<Card> cards;

    public Deck(){
        placeInDeck = 0;
        cards = new ArrayList<>();
        addCards();
    }

    private void addCards() {
        for(int i = 1; i <= 14; i++){
            cards.add(new Card(Suit.Club, i));
        }
        for(int i = 1; i <= 14; i++){
            cards.add(new Card(Suit.Diamond, i));
        }
        for(int i = 1; i <= 14; i++){
            cards.add(new Card(Suit.Spade, i));
        }
        for(int i = 1; i <= 14; i++){
            cards.add(new Card(Suit.Heart, i));
        }
    }

    public void shuffle() {
        for(int i = 0; i < cards.size(); i++){
            int ran = new Random().nextInt(cards.size());
            Card temp = cards.get(ran);
            cards.set(ran, cards.get(i));
            cards.set(i, temp);
        }
    }

    public Card drawCard(){
        Card c = cards.get(placeInDeck);
        placeInDeck++;
        //the deck is reshuffled when the last card is drawn
        if (placeInDeck >= cards.size()){
            placeInDeck = 0;
            shuffle();
        }
        return c;
    }

    @Override
    public String toString(){
        String str = "";
        for (Card c : cards){
            str = str + c.toString() + "\n";
        }

        return str;
    }
}
