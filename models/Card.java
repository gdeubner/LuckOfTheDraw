package models;
public class Card implements Comparable<Card>{

    private Suit suit;
    private int value;
    //number cards: 2-10
    //Face Cards: 11 = Jack, 12 = Queen, 13 = King, 14 = Ace
    //1 = penalty card
    
    public Card(Suit suit, int value){
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit(){
        return suit;
    }

    public int getValue(){
        return value;
    }

    @Override
    public int compareTo(Card otherCard) {
        int valueCompare = this.value - otherCard.getValue();
        if (valueCompare != 0) {
            return valueCompare;
        } 
        return this.suit.compareTo(otherCard.getSuit());
    }


    @Override
    public String toString(){
        if(value == 1){
            return "penalty card";
        } else {
            String str;
            if (value > 10){
                //deals with face value cards
                str = "" + FaceValues.values()[(value-11)];
            } else {
                //deals with number cards
                str = String.valueOf(value);
            }
            return str +  " of " + suit.toString() + "s";
        }
    }
}
