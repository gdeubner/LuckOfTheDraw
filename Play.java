import java.io.File;
import java.util.*;

import models.Card;
import models.Deck;
import models.Player;

class Play{
    private static Scanner scanner;

    //displays the title and allows user to choose between play/instructions/quit
    private static void pregame() throws InterruptedException{
        System.out.println("""
        Welcome to...
        Luck Of The Draw: the card game of chance and glory
        """
        );
        
        boolean continueLooping = true;
        
        while( continueLooping ){
            continueLooping = false;
            System.out.println("""
            What would you like to do?
            Enter: Play / Instructions / quit
            """);

            String response = scanner.nextLine();

            switch (response.toLowerCase().trim()) {
                case "play":
                    startGame();
                    break;
                case "instructions":
                    printInstructions();
                    continueLooping = true;
                    break;
                case "quit":
                    System.out.println("Bye bye!");
                    return;
                default:
                    System.out.println("Invalid input.");
                    continueLooping = true;
                    break;
            }
            System.out.println();
        }
    }

    //game logic
    private static void startGame() {
        //asks for the number of players and for their names
        Player[] playerList = getPlayers();
        int round = 1;
        Deck deck = new Deck();
        deck.shuffle();
        //loops through each round, having players draw cards and changing their scores
        System.out.println("(Note: you may exit the game at any time by holding Ctrl/Cmd - C)");
        while (true){
            if(round != 1) {
                System.out.println("Press enter to start the next round.");
                scanner.nextLine();
            }
            System.out.println("\n***Round " + round + "***\n");

            //each player takes turns drawing a card
            Card[] playerCards = drawCards(deck, playerList);

            System.out.println("Press enter to see the results of the round.");
            scanner.nextLine();

            updatePlayerScores(playerCards, playerList);
            
            printScoreboard(round, playerList);
            round++;
            //if someone has won, declare the victor, exit the loop, and end the program
            if(checkGameOver(playerList)){
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }

    //prompts the user for the number of players and asks for each player's name
    private static Player[] getPlayers() {
        Player[] playerList = null;
    
        while(playerList == null){
            System.out.println("\nHow many players are there? Enter 2, 3, or 4.");
            String response = scanner.nextLine().trim();
            if(!(response.equals("2") || response.equals("3") || response.equals("4"))){
                System.out.println("Invalid input.");
            } else {
                playerList = new Player[Integer.parseInt(response)];
                break;
            }
        }
        //asks each player for a unique, non-empty name under 50 characters
        for(int i = 1; i <= playerList.length; i++){
            boolean continueLooping = true;
            while(continueLooping){
                System.out.println("Player" + i +", please enter your name.");
                String response = scanner.nextLine().trim();
                if(response.length() < 50 && response.length() > 0){
                    //make sure name is unique
                    boolean isUnique = true;
                    for(Player player : playerList){
                        if(player != null && player.getName().equals(response)){
                            isUnique = false;
                            System.out.println("That name is already taken.");
                            break;
                        }
                    }
                    if(isUnique){
                        continueLooping = false;
                        playerList[i-1] = new Player(i, response);
                    }                    
                } else {
                    System.out.println("That name was invalid. Try something else.");
                }
            }
        }
        return playerList;
    }

    //Each player is asked to draw a card from the deck.
    private static Card[] drawCards(Deck deck, Player[] playerList){
        Card[] playerCards = new Card[playerList.length];
        for(int i = 0; i < playerList.length; i++){
            Player currentPlayer = playerList[i];
            System.out.println(currentPlayer.getName() + ", please press enter to draw your card.");
            String response = scanner.nextLine();
             if(response.length() > 1){
                 System.out.println("you didn't follow my instructions, but I'll let it slide.");
            } 
            Card card = deck.drawCard();
            playerCards[i] = card;
            System.out.println(currentPlayer.getName() + " drew...\nthe " + card.toString() + "!\n");
        }
        return playerCards;
    }

    //Decrement player scores if a penalty card is drawn. Adds points to the winner's score.
    //Announces the winner.
    private static void updatePlayerScores(Card[] playerCards, Player[] playerList) {
        Card bestCard = null;
        int bestCardHolder = -1;
        for(int i = 0; i < playerCards.length; i++){
            Card card = playerCards[i];
            if(card.getValue() == 1){
                //penalty card - decrement player score, but not below zero
                if(playerList[i].getScore() > 0){
                    playerList[i].decrementScore();
                }
            } else {
                 if(bestCardHolder == -1){
                    //assigns bestCard to the first non-penalty card
                    bestCard = card;
                    bestCardHolder = i;
                } else {
                    if(card.compareTo(bestCard) > 0){
                        bestCard = card;
                        bestCardHolder = i;
                    }
                }
            }
        }
        //if at least one person has drawn a non-penalty card, a round winner is declared
        if(bestCardHolder >-1){
            playerList[bestCardHolder].incrementScore();
            System.out.println(playerList[bestCardHolder].getName() + " has won this round.");
        } else {
            System.out.println("Everyone drew penalty cards. Nobody winds this round. :(");
        }
    }

    //checks to see if any player has met the conditions for winning the game
    //conditions: player score >= 21, player player has highest score by 2 points
    private static boolean checkGameOver(Player[] playerList) {
        Player highestPlayer = null;
        Player secondHighestPlayer = null;
        int topScore = -1;
        int secondHighestScore = -1;
        //finds highest 2 player scores
        for(int i = 0; i < playerList.length; i++){
           if(playerList[i].getScore() >= topScore){
               secondHighestPlayer = highestPlayer;
               highestPlayer = playerList[i];
               secondHighestScore = topScore;
               topScore = highestPlayer.getScore();
           } else if (playerList[i].getScore() >= secondHighestScore){
               secondHighestPlayer = playerList[i];
           }
        }
        //checks win conditions
        if(topScore >= 21 && secondHighestPlayer.getScore() + 1 < topScore){
            System.out.println("Player" + highestPlayer.getId() + " has won the game!");
            System.out.println("Congrats " + highestPlayer.getName() + "!");
            return true;
        }
        return false;
    }

    //prints the score board to console
    private static void printScoreboard(int round, Player[] players){
        System.out.println("\n*************************");
        System.out.println("Scoreboard for round " + round + ":");
        for (Player player : players){
            System.out.println("Player" + player.getId() + ": " + player.getName() + " " + player.getScore());
        }
        System.out.println("*************************\n");

    }

    //reads the instructions from the .instructions file and prints them to console
    private static void printInstructions() {
        File myObj = new File("./assets/instructions.txt");
        try{
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                System.out.println(myReader.nextLine());
            }
            myReader.close();
        } catch (Exception e){
            System.out.println("instructions file not found or not readable.");
        }
        
    }
    public static void main (String[] args) {
        scanner = new Scanner(System.in);
        try {
            pregame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
