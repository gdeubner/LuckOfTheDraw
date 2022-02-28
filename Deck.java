import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private int placeInDeck;
    private ArrayList<Card> deck;

    public Deck(){
        placeInDeck = 0;
        deck = new ArrayList<>();
        createDeck();
        shuffleDeck();
    }

    private void createDeck() {
        for(int i = 0; i <= 14; i++){
            deck.add(new Card(Suit.Club, i));
        }
        for(int i = 0; i <= 14; i++){
            deck.add(new Card(Suit.Diamond, i));
        }
        for(int i = 0; i <= 14; i++){
            deck.add(new Card(Suit.Spade, i));
        }
        for(int i = 0; i <= 14; i++){
            deck.add(new Card(Suit.Heart, i));
        }
    }

    private void shuffleDeck() {
        for(int i = 0; i < deck.size(); i++){
            int ran = new Random().nextInt(56);
            Card temp = deck.get(ran);
            deck.set(ran, deck.get(i));
            deck.set(i, temp);
        }
    }

    public Card drawCard(){
        Card c = deck.get(placeInDeck);
        placeInDeck++;
        if (placeInDeck > 55){
            placeInDeck = 0;
        }
        return c;
    }

    @Override
    public String toString(){
        String str = "";
        for (Card c : deck){
            str = str + c.toString() + "\n";
        }

        return str;
    }
}
