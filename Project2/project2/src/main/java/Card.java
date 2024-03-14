public class Card {
    String suit;
    int value;
    Card(String theSuit, int theValue){
        suit = theSuit;
        value = theValue;
    }

    // getter and setters?
//    public int getValue() {
//        return value;
//    }
//
//    public String getSuit() {
//        return suit;
//    }

    public String CardPathAsString() {
        String strVal;
        if (value == 1) {
            strVal = "ace";
        } else if (value == 11) {
            strVal = "jack";
        } else if (value == 12) {
            strVal = "queen";
        } else if (value == 13) {
            strVal = "king";
        } else {
            strVal = String.valueOf(value);
        }
        return strVal + "_of_" + suit;
    }
}
