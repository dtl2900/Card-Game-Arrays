// Programmer Daniel Torres Larreta
// Purpose: This will create a basic card shuffling and dealing program. The purpose is to serve as a exercise and practice for Lab 3 which will be wriiting a card game using arrays. This lab will also help with creating multiple classes.

 import java.security.SecureRandom;

     class Card {
     private final String face; // face of card ("Ace", "Deuce", ...)
     private final String suit; // suit of card ("Hearts", "Diamonds", ...)

     // two-argument constructor initializes card’s face and suit
     public Card(String cardFace, String cardSuit) {
        this.face = cardFace; // initialize face of card
        this.suit = cardSuit; // initialize suit of card
     }

     // return String representation of Card
     public String toString() {
     return face + " of " + suit;
     }
     
     public String getFace() {
         return face;
     }    
     public String getSuit() {
         return suit;
     }
         
  }
  
 
 
 
 class DeckOfCards {
     // random number generator
     private static final SecureRandom randomNumbers = new SecureRandom();
     private static final int NUMBER_OF_CARDS = 52; // constant # of Cards

    private Card[] deck = new Card[NUMBER_OF_CARDS]; // Card references 
    private int currentCard = 0; // index of next Card to be dealt (0-51)

     // constructor fills deck of Cards
     public DeckOfCards() {
        String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
           "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};   
       String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};      

         // populate deck with Card objects          
        for (int count = 0; count < deck.length; count++) {         
          deck[count] =          
              new Card(faces[count % 13], suits[count / 13]);
        }                         
     }

    // shuffle deck of Cards with one-pass algorithm
     public void shuffle() {
        // next call to method dealCard should start at deck[0] again
        currentCard = 0;

       // for each Card, pick another random Card (0-51) and swap them
         for (int first = 0; first < deck.length; first++) {
            // select a random number between 0 and 51
           int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

           // swap current Card with randomly selected Card
            Card temp = deck[first];
           deck[first] = deck[second];
            deck[second] = temp;
         }
      }

      // deal one Card
      public Card dealCard() {
        // determine whether Cards remain to be dealt
        if (currentCard < deck.length) {
           return deck[currentCard++]; // return current Card in array
         }
        else {
           return null; // return null to indicate that all Cards were dealt
        }
      }
      
      //method that will determine whether the user has a pair or not
      public boolean hasPair(Card[] hand) {
         for (int i = 0; i < hand.length; i++) {
            for (int j = i + 1; j < hand.length; j++) {
               if (hand[i].getFace().equals(hand[j].getFace())) {
                  return true; 
               }
           }
        }
        return false;
       }
}

class DeckOfCardsTest {
   // execute application
   public static void main(String[] args) {
        DeckOfCards myDeckOfCards = new DeckOfCards();
         myDeckOfCards.shuffle(); // place Cards in random order

        // print 5 Cards in the order in which they are dealt
         Card[] hand = new Card[5];
         for (int i = 0; i < 5; i++) {
            // deal and display a Card
            hand[i] = myDeckOfCards.dealCard();
            System.out.printf("%-19s", hand[i]);

            if ((i + 1) % 5 == 0) { // output a newline after every fifth card
               System.out.println();
            }
        }
        
        // will tell the user if their hand contains a pair or not.
        if (myDeckOfCards.hasPair(hand)) {
            System.out.println("This hand contains a pair");
        } else {
            System.out.println("This hand does not contain a pair");
        }
    }     
}
