import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

class Play{
    private static Scanner scanner;

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

            switch (response.toLowerCase()) {
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

    private static void startGame() {
        //asks for the number of players and for their names
        Player[] playerList = getPlayers();

        int round = 1;
        Deck deck = new Deck();
        //loops through each round, having players draw cards and changing their score
        while (true){
            if(round != 1) {
                System.out.println("Press enter to start the next round.");
                scanner.nextLine();
            }
            System.out.println("\n***Round " + round + "***\n");
            Card[] roundCards = new Card[playerList.length];

            //have each player draw a card
            for(int i = 0; i < playerList.length; i++){
                Player p = playerList[i];
                System.out.println("Player" + p.getId() + ", please press enter to draw your card.");
                String response = scanner.nextLine();
                 if(response.length() > 1){
                     System.out.println("you didn't follow my instructions, but I'll let it slide.");
                } 
                Card card = deck.drawCard();
                roundCards[i] = card;
                System.out.println("Player" + p.getId() + " drew...\nthe " + card.toString() + "\n");
            }

            //update player scores
            Card bestCard = null;
            int bestCardHolder = -1;
            for(int i = 0; i < roundCards.length; i++){
                Card card = roundCards[i];
                if(card.getValue() == 0){
                    //penalty card - decriment player score
                    if(playerList[i].getScore() > 0){
                        playerList[i].decrimentScore();
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
            if(bestCardHolder >-1){
                playerList[bestCardHolder].incrementScore();
                System.out.println(playerList[bestCardHolder].getId() + "has won this round.");
            }
            printScoreboard(round, playerList);

            round++;
            //if someone has won, declare the victor, exit the loop, and end the program
            Player winner = checkGameOver(playerList);
            if(winner != null){
                System.out.println("Player" + winner.getId() + " has won the game!");
                System.out.println("Congrats " + winner.getName() + "!");
                break;
            }
        }
        System.out.println("Thanks for playng!");
    }

    private static Player checkGameOver(Player[] arr) {
        Player highestPlayer = arr[0];
        for(int i = 1; i < arr.length; i++){
            Player p = arr[i];
            if(p.getScore() > highestPlayer.getScore() + 1){
                highestPlayer = p;
            }
        }
        if( highestPlayer.getScore() >= 21){
            return highestPlayer;
        }
        return null;
    }

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
        for(int i = 1; i <= playerList.length; i++){
            System.out.println("Player" + i +", please enter your name.");
            String response = scanner.nextLine();
            playerList[i-1] = new Player(i, response, 0);
        }
        return playerList;
    }

    private static void printScoreboard(int round, Player[] arr){
        System.out.println("\n*************************");
        System.out.println("Scoreboard for round " + round + ":");
        for (Player p : arr){
            System.out.println("Player" + p.getId() + ": " + p.getScore());
        }
        System.out.println("*************************\n");

    }




    private static void printInstructions() {
        File myObj = new File(".instructions");
        try{
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                System.out.println(myReader.nextLine());
            }
            myReader.close();
        } catch (Exception e){
            System.out.println(".instructions file not found or not readable");
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
