package Services;

import Entities.Cards;
import Entities.Deck;
import Entities.Player;
import MainApplication.Main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGame {

    private Deck deck;
    
    private List<Cards> discard_pile_stack;
    private List<Player> players_list;

    public List<Player> getPlayers_list() {
        return players_list;
    }
    
    //a varaible to maintain the order whether clockwise or anticlockwisse
    private boolean reverseOrder;

    //this maintains the current player whose chance is there to play
    private int current_player_index;

    private String played_rank;

    private static final String[] actions = {"Skip","Reverse","+2","+4"};
    private static final String[] ranks = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};


    //constructor
    public CardGame(int num_of_players){

        deck = new Deck();

        players_list = new ArrayList<Player>();
        for (int i=0; i<num_of_players; i++) {
            players_list.add(new Player("Player "+i));
        }

        discard_pile_stack = new ArrayList<Cards>();
    }




    public void start_game(){

        //started with game and we are shuffling the deck:
        deck.shuffle_deck();

        //distributing cards for each player
        for (Player player : players_list) {
            //now for distributing 5 cards for each player
            for (int i = 0; i < 5; i++) {
                player.add_card(deck.draw_card_from_deck());
            }
        }

        //after distributing among players we also need the first card or opening one on discard pile for our game to begin
        discard_pile_stack.add(deck.draw_card_from_deck());

        //rank of the opening card:
        String topRank = discard_pile_stack.get(0).getRank();


        if (topRank.equals("King") || topRank.equals("Ace")) {
            // If the top card is a King or Ace, we apply its action at the start of the game.
            performAction(topRank);
        }

        reverseOrder = false;
        current_player_index = 0;
        
        while (true) {
            Player current_player = players_list.get(current_player_index);

            System.out.println("Top card: " + discard_pile_stack.get(discard_pile_stack.size() - 1));
            System.out.println(current_player.getPlayer_name() + "'s turn:");

            //now we will be checking if whether a player can play his turn or need to draw a new card:

            //the player has a valid cards to play in his hand_cards:
            if (current_player.doPlayerhaveCardToPlay(discard_pile_stack.get(discard_pile_stack.size() - 1))) {

                //this fucntion chooseCard will return index of a valid card from hand_cards:
                int card_index = chooseCardfromHandCards(current_player);

                Cards played_card = current_player.card_to_play(card_index);
                discard_pile_stack.add(played_card);

                System.out.println(current_player.getPlayer_name() + " played " + played_card);

                String played_rank = played_card.getRank();

                //if the card is an action card following steps needs to be taken : performAction function will be called
                if (played_rank.equals("King") || played_rank.equals("Ace") || played_rank.equals("Queen") || played_rank.equals("Jack")) {
                    performAction(played_rank);
                }
                if(current_player.has_player_won()) {
                    System.out.println(current_player.getPlayer_name() + " has won!");
                    break;
                }
            } 
            else {      //when we need to draw a card:
                System.out.println(current_player.getPlayer_name() + " cannot play a card.");

                //checking if deck has cards to draw or is empty:
                if (deck.size_of_deck() == 0) {
                    System.out.println("The draw pile is empty. The game is a draw.");
                    break;
                } 
                else {
                    Cards drawn_card = deck.draw_card_from_deck();
                    System.out.println(current_player.getPlayer_name() + " drew " + drawn_card);
                    current_player.add_card(drawn_card);
                }
            }

            //updating the next player inline as per the order:
            if(reverseOrder) {

                if(Main.num_of_players==2 && played_rank.equals("King")){
                    System.out.println("done");
                    break;
                }

                current_player_index--;
                if (current_player_index < 0) {
                    current_player_index = players_list.size() - 1;
                }
            } 
            else {

                if(Main.num_of_players==2 && played_rank.equals("King")){
                    System.out.println("done");
                    break;
                }

                current_player_index++;
                if (current_player_index == players_list.size()) {
                    current_player_index = 0;
                }
            }
            System.out.println();
        }
    }
    


    //this function will return the index of the card which is valid for the turn considering all of the required conditions:
    private int chooseCardfromHandCards(Player player) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Your hand:");

        List<Cards> hand = player.get_hand_cards();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i));
        }

        while (true) {
            System.out.print("Choose a card to play: ");
            int cardIndex = sc.nextInt();
            if (cardIndex < 0 || cardIndex >= hand.size()) {
                System.out.println("Invalid input.");
            } 
            else {
                // Cards chosenCard = hand.get(cardIndex);

                // String rank_topCard = discard_pile_stack.get(discard_pile_stack.size() - 1).getRank();


                // if(chosenCard.getSuit().equals(discard_pile_stack.get(discard_pile_stack.size() - 1).getSuit()) || chosenCard.getRank().equals(discard_pile_stack.get(discard_pile_stack.size() - 1).getRank())) {

                //     if((rank_topCard.equals("Ace") && chosenCard.getRank().equals("Ace")) || (rank_topCard.equals("King") && chosenCard.getRank().equals("King")) ||
                //     (rank_topCard.equals("Queen") && chosenCard.getRank().equals("Queen")) || (rank_topCard.equals("Jack") && chosenCard.getRank().equals("Jack")))
                //     {
                //         System.out.println("You cannot play that card as action card cannot be played over the same");
                //     }
                //     else{
                //         return cardIndex;
                //     }  
                // } 
                // else {
                //     System.out.println("You cannot play that card.");
                // }

                Cards chosenCard = hand.get(cardIndex);
                    if (chosenCard.getSuit().equals(discard_pile_stack.get(discard_pile_stack.size() - 1).getSuit()) || chosenCard.getRank().equals(discard_pile_stack.get(discard_pile_stack.size() - 1).getRank())) {
                        return cardIndex;
                    } 
                    else {
                        System.out.println("You cannot play that card.");
                    }
            }
        }
    }
    


    //following actions for the action_cards will be performed:
    private void performAction(String rank) {
        switch (rank) {
            case "King":
                System.out.println("The order of play has been reversed.");
                reverseOrder = !reverseOrder;
                break;

                
            case "Ace":
                System.out.println("The next player has been skipped.");
                if(reverseOrder==false){
                    current_player_index++;
                    if (current_player_index == players_list.size()) {
                        current_player_index = 0;
                    }
                }
                else{
                    current_player_index--;
                    if (current_player_index < 0) {
                        current_player_index = players_list.size() - 1;
                    }
                }
                break;


            case "Queen":
                System.out.println("The next player will draw 2 cards.");
                if(reverseOrder==false){
                    current_player_index++;
                    if (current_player_index == players_list.size()) {
                        current_player_index = 0;
                    }
                }
                else{
                    current_player_index--;
                    if (current_player_index < 0) {
                        current_player_index = players_list.size() - 1;
                    }
                }
                Player nextPlayer = players_list.get(current_player_index);
                nextPlayer.add_card(deck.draw_card_from_deck());
                nextPlayer.add_card(deck.draw_card_from_deck());
                break;


            case "Jack":
                System.out.println("The next player will draw 4 cards.");
                if(reverseOrder==false){
                    current_player_index++;
                    if (current_player_index == players_list.size()) {
                        current_player_index = 0;
                    }
                }
                else{
                    current_player_index--;
                    if (current_player_index < 0) {
                        current_player_index = players_list.size() - 1;
                    }
                }
                Player nextPlayer1 = players_list.get(current_player_index);
                nextPlayer1.add_card(deck.draw_card_from_deck());
                nextPlayer1.add_card(deck.draw_card_from_deck());
                nextPlayer1.add_card(deck.draw_card_from_deck());
                nextPlayer1.add_card(deck.draw_card_from_deck());
                break;
    }
}

}
