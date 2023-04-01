package Test;

import org.junit.Test;
import org.junit.assertEquals;
import org.junit.*;
import Entities.Cards;
import Entities.Deck;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@DisplayName("CardGame Test")
public class DeckTest {
    
    @Test
    @DisplayName("To check the size of deck created")
    public void test_initial_deck() {
        Deck deck = new Deck();
        assertEquals(52, deck.size_of_deck());
    }


    //to test draw_card_from_deck function:
    @Test
    @DisplayName("To check if the first card from deck is drawn:")
    public void draw_topcard_from_deck(){
        List<Cards> cards_deck = new ArrayList<>();

        //arrange a random deck:
        cards_deck.add(new Cards("Spade", "4", "number"));
        cards_deck.add(new Cards("Heart", "Jack", "action"));
        cards_deck.add(new Cards("Club", "9", "number"));
        cards_deck.add(new Cards("Spade", "Ace", "action"));

        //act:
        Deck deck = new Deck();
        Cards top_card = deck.get_deck().get(0);

        Cards expected_card = deck.draw_card_from_deck();

        //assert:
        assertEquals(top_card,expected_card);

}





   
}

