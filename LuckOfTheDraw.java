import java.util.*;
import java.util.concurrent.TimeUnit;

class LuckOfTheDraw{
    private static Scanner scanner;

    private static void pregame() throws InterruptedException{
        System.out.println("""
        Welcome to...
        Luck Of The Draw: the card game of chance and glory
        """
        );
        
        TimeUnit.SECONDS.sleep(3);
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
            TimeUnit.SECONDS.sleep(2);

        }
        

    }

    


    private static void startGame() {
    }




    private static void printInstructions() {
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
