package Test;

import Entities.Cards;
import Entities.Player;
import Services.CardGame;
import java.beans.Transient;
import org.junit.Test;
import org.junit.assertEquals;

@DisplayName("Players test")
public class PlayerTest {

    @Test
    @DisplayName("Test for creating a player")
    public void test_player_creation(){
        Player player = new Player("Player-1");
        assertEquals("Player-1", player.getPlayer_name());
        assertEquals(0, player.get_hand_cards().size());
    }

  
    
    @Test
    @DisplayName("validating number of cards given to each player before starting the match:")
    public void test_initial_player_hands() {
        CardGame game = new CardGame(4);
        assertEquals(5, game.getPlayers_list().get(0).hand_cards_size());
        assertEquals(5, game.getPlayers_list().get(1).hand_cards_size());
        assertEquals(5, game.getPlayers_list().get(2).hand_cards_size());
        assertEquals(5, game.getPlayers_list().get(3).hand_cards_size());
        
    }


    @Test
    @DisplayName("Test for adding a card to player's hand")
    public void add_card_to_player(){
        Cards card = new Cards("Diamond", "Queen", "action");
        Player player = new Player("Player-1");
        player.add_card(card);
    
        //assert:
        assertEquals(1, player.get_hand_cards().size());
        //as the hand_list consist of only one card:
        assertEquals(card, player.get_hand_cards().get(0));
    }
   

    //to test the doplayerhascardtoplay function:
    @Test
    @DisplayName("Test for validating if the player has a valid card to play in his turn")
    public void validate_players_handcards(){
        //arrange:
        Player player = new Player("Player-1");
        player.add_card(new Cards("Diamond", "Queen", "action"));
        player.add_card(new Cards("Spade", "4", "number"));
        player.add_card(new Cards("Heart", "Jack", "action"));
        player.add_card(new Cards("Club", "9", "number"));
        player.add_card(new Cards("Spade", "Ace", "action"));
        
        Cards top_card = new Cards("Spade", "6", "number");

        //act:
        boolean actual_response = player.doPlayerhaveCardToPlay(top_card);
        boolean expected_response = true;

        //assert:
        assertEquals(actual_response,expected_response);

    }


    //player would not have any valid card to play:
    @Test
    @DisplayName("Test for validating if the player has no valid card to play in his turn")
    public void validate_players_handcards_fails(){
        //arrange:
        Player player = new Player("Player-1");
        player.add_card(new Cards("Diamond", "Queen", "action"));
        player.add_card(new Cards("Spade", "4", "number"));
        player.add_card(new Cards("Heart", "Jack", "action"));
        player.add_card(new Cards("Spade", "Ace", "action"));
        
        Cards top_card = new Cards("club", "6", "number");

        //act:
        boolean actual_response = player.doPlayerhaveCardToPlay(top_card);
        boolean expected_response = false;

        //assert:
        assertEquals(actual_response,expected_response);

    }


    //to check if a player has won:
    @Test
    @DisplayName("Test case to validate if a player has won:")
    public void validate_if_player_has_won(){
        //arrange:
        Player player = new Player("Player-1");

        //act:
        boolean actual_response = player.has_player_won();
        boolean expected_response = true;

        //assert:
        assertEquals(actual_response,expected_response);

    }

    //to check if a player has not won:
    @Test
    @DisplayName("Test case to validate if a player has not won:")
    public void validate_if_player_has_not_won(){
        //arrange:
        Player player = new Player("Player-1");
        player.add_card(new Cards("Diamond", "Queen", "action"));
        player.add_card(new Cards("Spade", "4", "number"));

        //act:
        boolean actual_response = player.has_player_won();
        boolean expected_response = false;

        //assert:
        assertEquals(actual_response,expected_response);

    }

}
