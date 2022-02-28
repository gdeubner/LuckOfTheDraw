public class Card implements Comparable<Card>{

    private Suit suit;
    private int value;
    //number cards: 1-10
    //Face Cards: 11 = Jack, 12 = Queen, 13 = King, 14 = Ave
    //0 = penalty card
    
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
        int valueCompare = this.value - otherCard.getValue() ;
        if (valueCompare != 0) {
            return valueCompare;
        } 
        return this.suit.compareTo(otherCard.getSuit());
    }


    @Override
    public String toString(){
        String str;
        if(value == 0){
            str = "penalty card";
        } else if (value > 10){
            //deals with face value cards
            str = FaceValues.values()[(value-11)] + " of " + suit.toString() + "s";
        } else {
            //deals with number cards
            str = String.valueOf(value) + " of " + suit.toString() + "s";
        }
        return str;
    }
}
