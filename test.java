public class test {
    public static void main(String[] args){
        Card c1 = new Card(Suit.Spade, 1);
        Card c2 = new Card(Suit.Spade, 2);
        System.out.println(c1.compareTo(c2));
    }
}
