package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    
    private final List<Cards> cards;

    public Deck() {

        cards = new ArrayList<>();

        String[] suits = {"Spades","Clubs","Hearts","Diamonds"};
        String[] ranks = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Cards(suit, rank));  
            }
        }
    }

    //supporting methods:

    public void shuffle_deck(){
        Collections.shuffle(cards);
    }

    public Cards draw_card_from_deck(){
        return cards.remove(0);
    }

    public int size_of_deck(){
        return cards.size();
    }

    
    
}
