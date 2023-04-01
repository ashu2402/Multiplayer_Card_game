package Test;

import Entities.Cards;
import Entities.Deck;
import Entities.Player;
import Services.CardGame;
import java.beans.Transient;

public class CardGameTest {
    
    @Test
    @DisplayName("Test for Jack card:")
    public void test_Jack_Card_Action() {
        CardGame cardGame = new CardGame(2);
        // Create deck and shuffle
        Deck deck = new Deck();
        deck.shuffle_deck();

        // Deal cards to players
        Cards card;
        for (int i = 0; i < 5; i++) {
            card = deck.draw_card_from_deck();
            cardGame.getPlayers_list().get(0).add_card(card);
            card = deck.draw_card_from_deck();
            cardGame.getPlayers_list().get(1).add_card(card);
        }

        Player currentPlayer = cardGame.getPlayers_list().get(0);
        Player nextPlayer = cardGame.getPlayers_list().get(1);
        
        //add 4 cards to the next one:
        for (int i = 0; i < 4; i++) {
            cardGame.getPlayers_list().get(1).add_card(deck.draw_card_from_deck());
        }
        //current player index always has the index of first player in the player list which is player-1 in this case:
        // Test jack card action
        cardGame.performAction("Jack");

        //Intially player-2 had 5 cards
        //Then player-1 used Jack rank card 
        //now player-2 has got 4 more cards and total has became 9
        assertEquals(9, cardGame.getPlayers_list().get(1).get_hand_cards().size());
    }



    @Test
    @DisplayName("Test for Queen card:")
    public void test_Queen_Card_Action() {
        CardGame cardGame = new CardGame(2);
        // Create deck and shuffle
        Deck deck = new Deck();
        deck.shuffle_deck();

        // Deal cards to players
        Cards card;
        for (int i = 0; i < 5; i++) {
            card = deck.draw_card_from_deck();
            cardGame.getPlayers_list().get(0).add_card(card);
            card = deck.draw_card_from_deck();
            cardGame.getPlayers_list().get(1).add_card(card);
        }

        Player currentPlayer = cardGame.getPlayers_list().get(0);
        Player nextPlayer = cardGame.getPlayers_list().get(1);
        
        //add 4 cards to the next one:
        for (int i = 0; i < 2; i++) {
            cardGame.getPlayers_list().get(1).add_card(deck.draw_card_from_deck());
        }

        //current player index always has the index of first player in the player list which is player-1 in this case:
        // Test queen card action
        cardGame.performAction("Queen");

        //Intially player-2 had 5 cards
        //Then player-1 used Jack rank card 
        //now player-2 has got 2 more cards and total has became 7
        assertEquals(7, cardGame.getPlayers_list().get(1).get_hand_cards().size());
    }



    @Test
    @DisplayName("Test for King card:")
    public void test_King_Card_Action() {
        CardGame cardGame = new CardGame(3);
        // Create deck and shuffle
        Deck deck = new Deck();
        deck.shuffle_deck();

        // Deal cards to players
        Cards card;
        for (int i = 0; i < 5; i++) {
            card = deck.draw_card_from_deck();
            cardGame.getPlayers_list().get(0).add_card(card);
            card = deck.draw_card_from_deck();
            cardGame.getPlayers_list().get(1).add_card(card);
            card = deck.draw_card_from_deck();
            cardGame.getPlayers_list().get(2).add_card(card);
        }

        Player currentPlayer = cardGame.getPlayers_list().get(0);
        Player nextPlayer = cardGame.getPlayers_list().get(2);

        //current player index always has the index of first player in the player list which is player-1 in this case:
        // Test king card action
        cardGame.performAction("King");

        //Intially player-2 would be have next but as it is reversed . The next players turn would be of player-3
        //current_player_index is being upadated:
        assertEquals(nextPlayer.getPlayer_name(), cardGame.getPlayers_list().get(cardGame.getCurrent_player_index()).getPlayer_name());
    }




    @Test
    @DisplayName("Test for Ace card:")
    public void test_Ace_Card_Action() {
        CardGame cardGame = new CardGame(3);
        // Create deck and shuffle
        Deck deck = new Deck();
        deck.shuffle_deck();

        // Deal cards to players
        Cards card;
        for (int i = 0; i < 5; i++) {
            card = deck.draw_card_from_deck();
            cardGame.getPlayers_list().get(0).add_card(card);
            card = deck.draw_card_from_deck();
            cardGame.getPlayers_list().get(1).add_card(card);
            card = deck.draw_card_from_deck();
            cardGame.getPlayers_list().get(2).add_card(card);
        }

        Player currentPlayer = cardGame.getPlayers_list().get(0);
        Player nextPlayer = cardGame.getPlayers_list().get(2);

        //current player index always has the index of first player in the player list which is player-1 in this case:
        // Test king card action
        cardGame.performAction("Ace");

        //Intially player-2 would be have next but as it is skipped due to ace action card .
        // The next players turn would be of player-3
        //current_player_index is being upadated:
        assertEquals(nextPlayer.getPlayer_name(), cardGame.getPlayers_list().get(cardGame.getCurrent_player_index()).getPlayer_name());
    }
    
}
