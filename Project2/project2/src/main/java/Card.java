public class Card {
    String suit;
    int value;
    Card(String theSuit, int theValue){
        suit = theSuit;
        value = theValue;
    }

    // getter and setters?
    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String CardPathAsString(){
        return value + "_of_" + suit;
    }
}
