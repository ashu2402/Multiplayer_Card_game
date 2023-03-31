package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGame {

    private Deck deck;
    
    private List<Cards> discard_pile_stack;
    private List<Player> players_list;
    
    //a varaible to maintain the order whether clockwise or anticlockwisse
    private boolean reverseOrder;

    //this maintains the current player whose chance is there to play
    private int current_player_index;

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
            // If the top card is a King or Ace,

            performAction(topRank);
        }

        reverseOrder = false;
        current_player_index = 0;
        
        while (true) {
            Player currentPlayer = players_list.get(current_player_index);

            System.out.println("Top card: " + discard_pile_stack.get(discard_pile_stack.size() - 1));
            System.out.println(currentPlayer.getPlayer_name() + "'s turn:");

            if (currentPlayer.hasCardToPlay(discard_pile_stack.get(discard_pile_stack.size() - 1))) {

                int cardIndex = chooseCard(currentPlayer);
                Cards playedCard = currentPlayer.card_to_play(cardIndex);
                discard_pile_stack.add(playedCard);
                System.out.println(currentPlayer.getPlayer_name() + " played " + playedCard);
                String playedRank = playedCard.getRank();
                if (playedRank.equals("King") || playedRank.equals("Ace")) {
                    performAction(playedRank);
                }
                if(currentPlayer.has_player_won()) {
                    System.out.println(currentPlayer.getPlayer_name() + " has won!");
                    break;
                }
            } 
            else {
                System.out.println(currentPlayer.getPlayer_name() + " cannot play a card.");
                if (deck.size_of_deck() == 0) {
                    System.out.println("The draw pile is empty. The game is a draw.");
                    break;
                } 
                else {
                    Cards drawnCard = deck.draw_card_from_deck();
                    System.out.println(currentPlayer.getPlayer_name() + " drew " + drawnCard);
                    currentPlayer.add_card(drawnCard);
                }
            }

            if(reverseOrder) {
                current_player_index--;
                if (current_player_index < 0) {
                    current_player_index = players_list.size() - 1;
                }
            } 
            else {
                current_player_index++;
                if (current_player_index == players_list.size()) {
                    current_player_index = 0;
                }
            }
        }
    }
    

    
    private int chooseCard(Player player) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Your hand:");

        List<Cards> hand = player.get_hand_cards();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i));
        }

        while (true) {
            System.out.print("Choose a card to play: ");
            int cardIndex = scanner.nextInt();
            if (cardIndex < 0 || cardIndex >= hand.size()) {
                System.out.println("Invalid input.");
            } 
            else {
                Cards chosenCard = hand.get(cardIndex);
                if (chosenCard.getSuit().equals(discard_pile_stack.get(discard_pile_stack.size() - 1).getSuit()) || chosenCard.getRank().equals(discard_pile_stack.get(discard_pile_stack.size() - 1).getRank())) {
                    return cardIndex;
                } else {
                    System.out.println("You cannot play that card.");
                }
            }
        }
    }
    
}
