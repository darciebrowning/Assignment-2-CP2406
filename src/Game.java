
import java.util.Scanner;
/**
 * Created by Darcie on 27/08/2016.
 */
public class Game {

    public static void main(String args[]){

        System.out.println("You have started a new game, how many people would you like to play with?");

        Scanner user_input = new Scanner(System.in);
        int userMenuAction = 0;
        userMenuAction = user_input.nextInt();

        if (userMenuAction > 2 && userMenuAction < 6) {
            System.out.println("You have chosen to play with " + userMenuAction + " people!");
        }
        else {
            System.out.println("You can only play with 3 - 5 players. Please begin again.");
        }


    }
}
