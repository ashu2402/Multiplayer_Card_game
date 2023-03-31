package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    
    private String player_name;
    private List<Cards> hand_cards;

    public Player(String player_name) {
        this.player_name = player_name;
        hand_cards = new ArrayList<>();
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void add_card(Cards card){
        hand_cards.add(card);
    }

    public Cards card_to_play(int index){
        return hand_cards.remove(index);
    }

    public int hand_cards_size(){
        return hand_cards.size();
    }


    

    public boolean hasCardToPlay(Cards topCard) {
        for (Cards card : hand_cards) {
            if(card.getSuit().equals(topCard.getSuit()) || card.getRank().equals(topCard.getRank())) {
                return true;
            }
        }
        return false;
    }
    

    
}