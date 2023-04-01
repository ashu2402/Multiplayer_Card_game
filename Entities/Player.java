package Entities;

import java.util.ArrayList;
import java.util.List;

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

    public List<Cards> get_hand_cards(){
        return hand_cards;
    }

    public int hand_cards_size(){
        return hand_cards.size();
    }




    public boolean doPlayerhaveCardToPlay(Cards topCard) {

        // int count_handcards_valid = 0;
        
        // for (Cards card : hand_cards) {
        //         if(card.getSuit().equals(topCard.getSuit()) || card.getRank().equals(topCard.getRank())) {

        //             if((topCard.getRank().equals("Ace") && card.getRank().equals("Ace")) || (topCard.getRank().equals("King") && card.getRank().equals("King")) ||
        //             (topCard.getRank().equals("Queen") && card.getRank().equals("Queen")) || (topCard.getRank().equals("Jack") && card.getRank().equals("Jack"))){

        //             }
        //             else{
        //                 count_handcards_valid++;
        //             } 
        //         }
        // }
        // if(count_handcards_valid>=1){
        //     return true;
        // }   
        // else{
        //     return false;
        // }       

        for (Cards card : hand_cards) {
            if (card.getSuit().equals(topCard.getSuit()) || card.getRank().equals(topCard.getRank())) {
                return true;
            }
        }
        return false;
    }


    
    public boolean has_player_won() {
        return hand_cards.isEmpty();
    }


    
}