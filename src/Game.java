
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.*;

import com.sun.codemodel.internal.CodeWriter;



/**
 * Created by Darcie on 27/08/2016.
 */
public class Game {

    public static void main(String args[]) {

        showWelocome();
        showMenu();
        int opt = getUserMenuChoice();

        if (opt == 1){
            System.out.println("You have started a new game.");
            startNewGame();
        }



    }

    private static void startNewGame(){
        int numberOfPlayers = getNumberPlayers();
        SuperTrumpGame game = new SuperTrumpGame(numberOfPlayers);
        System.out.print("You have chosen to play a game with " + numberOfPlayers + " players. \n");
        game.selectDealer(numberOfPlayers);
        System.out.print("The dealer is player number " + game.dealerNumber + ". \n");
        //game.dealCards();
    }

    private static int getNumberPlayers(){

        System.out.println("How many players would you like to play with?");
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        int n = reader.nextInt();

        while (n <= 2 || n >= 6) {
            System.out.println("How many players would you like to play with?");
            Scanner reader2 = new Scanner(System.in);
            System.out.println("Enter your choice: ");
            n = reader.nextInt();
        }
        return n;

    }

    private static int getUserMenuChoice(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        int n = reader.nextInt();
        return n;
    }

    private static void showMenu(){
        System.out.println("1. Start game.");
        System.out.println("2. Exit.");
    }

    private static void showWelocome() {
        System.out.print("Welcome to Mineral SuperTrumps! What would you like to do? \n");
    }
}





