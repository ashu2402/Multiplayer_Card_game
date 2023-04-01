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
                if (rank.equals("Ace") || rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
                    cards.add(new Cards(suit, rank, "action"));
                } 
                else {
                    cards.add(new Cards(suit, rank, "number"));
                }
            }
        }
    }

    //supporting methods:
    public List<Cards> get_deck(){
        return cards;
    }

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
