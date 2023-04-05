package com.splendorsound;

import java.io.IOException;
import java.util.Scanner;

public class RunProgram {
    public static void main(String[] args) throws IOException, InterruptedException {

        //TODO make stance markers on map blend in with background (set background color?)
        //TODO make moves options more readable, add more moves?
        //TODO figure out how to clear console between turns
        //TODO make unit tests

        //SET VARIABLES FOR TEXT COLOR
        final String resetColor = Color.RESET;
        final String errorColor = Color.RED;
        final String player2Color = Color.GREEN;
        final String player1Color = Color.YELLOW;
        final String titleColor = Color.PURPLE;
        final String keywordColor = Color.CYAN;

        //GAME HEADER & INTRO
        printOutTitle(resetColor, titleColor, keywordColor);

        //Open scanner, prompt to start new game or quit
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Start " + keywordColor + "NEW GAME? " + resetColor + "(Y/N): ");
            String startInput = input.nextLine();
            if (startInput.equalsIgnoreCase("y")) {
                validInput = true;
            } else if (startInput.equalsIgnoreCase("n")) {
                validInput = true;
                System.out.println(errorColor + "GOODBYE!" + resetColor);
                System.exit(0);
            } else {
                System.out.println(errorColor + "It's a simple question. Try again." + resetColor);
            }
        }
        System.out.println();

        //Enter player 1 name and create instance of Player for player 1:
        System.out.println("NEW GAME STARTED!");
        System.out.println();
        System.out.print(player1Color + "PLAYER 1 NAME: " + resetColor);
        String userInput = input.nextLine();
        Player player1 = new Player("player1", userInput);

        //Enter player 2 name and create instance of Player for player 2:
        System.out.print(player2Color + "PLAYER 2 NAME: " + resetColor);
        userInput = input.nextLine();
        Player player2 = new Player("player2", userInput);

        //Set opponents
        player1.setOpponent(player2);
        player2.setOpponent(player1);

        //GAME BEGINS:
        boolean gameWon = false;
        int turnCount = 0;
        Player currentPlayer = player1;
        Player winner = player1;
        String winnerName = winner.getName();

        //Print starting map
        Map map = new Map();
        System.out.println();
        map.printMap(player1, player2);
        System.out.println();

        //TURN STRUCTURE:

        while (!gameWon) {
            //Increment turn count
            turnCount++;

            //Take input for breath, execute position change
            validInput = false;
            while (!validInput) {
                System.out.print(currentPlayer.getName() + "! CHOOSE YOUR " + keywordColor + "BREATH" + resetColor + " MOVE (YIN/YANG): ");
                String breathInput = input.nextLine();
                if (breathInput.equalsIgnoreCase("yin")) {
                    currentPlayer.changeEnergy(-1);
                    validInput = true;
                } else if (breathInput.equalsIgnoreCase("yang")) {
                    currentPlayer.changeEnergy(1);
                    validInput = true;
                } else {
                    System.out.println(errorColor + "Not a valid breath move. Try again." + resetColor);
                }
            }
            //Check to see if move took player out of bounds.
            if(currentPlayer.isOutOfBounds()) {
                gameWon = true;
                winner = currentPlayer.getOpponent();
                break;
            }

            //Clear console
//            System.out.print("\033[H\033[2J");
//            System.out.flush();

            //Print updated map
            System.out.println();
            System.out.println();
            map.printMap(player1, player2);
            System.out.println();

            //Take input for position, execute position change
            validInput = false;
            while (!validInput) {
                System.out.print(currentPlayer.getName() + "! CHOOSE YOUR " + keywordColor + "POSITION" + resetColor + " MOVE (FORWARD/BACK): ");
                String positionInput = input.nextLine();
                if (positionInput.equalsIgnoreCase("forward")) {
                    currentPlayer.changePosition(1);
                    validInput = true;
                } else if (positionInput.equalsIgnoreCase("back")) {
                    currentPlayer.changePosition(-1);
                    validInput = true;
                } else {
                    System.out.println(errorColor + "Not a valid position move. Try again.\n" + resetColor);
                }
            }

            //Check to see if move took current player out of bounds.
            if(currentPlayer.isOutOfBounds()) {
                gameWon = true;
                winner = currentPlayer.getOpponent();
                break;
            }

            //Clear console
//            System.out.print("\033[H\033[2J");
//            System.out.flush();


            //Print updated map
            System.out.println();
            System.out.println();
            map.printMap(player1, player2);
            System.out.println();


            //Take input for move, execute stance changes
            validInput = false;
            while (!validInput) {
                System.out.println(currentPlayer.getName() + "! CHOOSE YOUR " + keywordColor + "ACTION: " + resetColor + "\n");
                System.out.println(keywordColor + "PUSH" + resetColor + " = You forward 1, yang 1. Opponent back 2, yin 1");
                System.out.println(keywordColor + "PULL" + resetColor + " = You back 1, yin 1. Opponent forward 2, yang 1\n");
                System.out.println("YOUR CHOICE (" + keywordColor + "PUSH" + resetColor + "/" + keywordColor + "PULL): ");
                String actionInput = input.nextLine();
                if (actionInput.equalsIgnoreCase("push")) {
                    currentPlayer.changePosition(1);
                    currentPlayer.changeEnergy(1);
                    currentPlayer.getOpponent().changePosition(-2);
                    currentPlayer.getOpponent().changeEnergy(-1);
                    validInput = true;
                } else if (actionInput.equalsIgnoreCase("pull")) {
                    currentPlayer.changePosition(-1);
                    currentPlayer.changeEnergy(-1);
                    currentPlayer.getOpponent().changePosition(2);
                    currentPlayer.getOpponent().changeEnergy(1);
                    validInput = true;
                } else {
                    System.out.println(errorColor + "Not a valid action. Try again." + resetColor);
                }
            }

            //Check to see if move took either player out of bounds.
            if(currentPlayer.isOutOfBounds() && currentPlayer.getOpponent().isOutOfBounds()){
                gameWon = true;
                break;
            }
            if(currentPlayer.isOutOfBounds()) {
                gameWon = true;
                winner = currentPlayer.getOpponent();
                break;
            }
            if(currentPlayer.getOpponent().isOutOfBounds()) {
                gameWon = true;
                winner = currentPlayer;
                break;
            }

            //Clear console
            System.out.print("\033[H\033[2J");
            System.out.flush();

            //Print updated map
            System.out.println();
            System.out.println();
            map.printMap(player1, player2);
            System.out.println();

            //Switch players
            currentPlayer = currentPlayer.getOpponent();
        }

        //END OF GAME

        //Clear console
//        Runtime.getRuntime().exec("clear");

        //Print end-state map
        System.out.println();
        System.out.println();
        map.printMap(player1, player2);
        System.out.println();

        //Print end-of-game message (winner name or draw)
        System.out.println();
        System.out.println(errorColor + "GAME OVER" + resetColor);

        if(currentPlayer.isOutOfBounds() && currentPlayer.getOpponent().isOutOfBounds()){
            System.out.println("It's a DRAW!");
        }
        else {
            winnerName = winner.getName();
            System.out.println(keywordColor + winnerName + " WINS!" + resetColor);
        }
        System.out.println(turnCount + " turns played.");

        //CLOSE SCANNER
        input.close();
    }

    private static void printOutTitle(String resetColor, String titleColor, String keywordColor) {
        System.out.println(titleColor +
                "                    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n" +
                "                    X~*~                                                                   ~*~X\n" +
                "                    X     BBBBBB      A     L          A     N     N   CCCCCC  EEEEEEE  !!    X\n" +
                "                    X     B     B    A A    L         A A    NN    N  C        E        !!    X\n" +
                "                    X     B     B   A   A   L        A   A   N N   N  C        E        !!    X\n" +
                "                    X     BBBBBB   AAAAAAA  L       AAAAAAA  N  N  N  C        EEEEE    !!    X\n" +
                "                    X     B     B  A     A  L       A     A  N   N N  C        E        !!    X\n" +
                "                    X     B     B  A     A  L       A     A  N    NN  C        E              X\n" +
                "                    X     BBBBBB   A     A  LLLLLLL A     A  N     N   CCCCCC  EEEEEEE  !!    X\n" +
                "                    X~*~                                                                   ~*~X\n" +
                "                    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                resetColor);
        System.out.println();
        System.out.println("                         Welcome to " + keywordColor + "BALANCE" + resetColor + " - a sparring game of physical and mental strategy.");
        System.out.println();
        System.out.println("               The goal is to knock your opponent off their grid while keeping yourself on your grid:\n");
        Map emptyMap = new Map();
        emptyMap.printEmptyMap();
        System.out.println();
        System.out.println("               Players take turns, beginning with player 1. Each turn consists of three actions:\n\n" +
                "          1) Your " + keywordColor + "BREATH" + resetColor + " action allows you to move one space toward YIN (down) or YANG (up).\n" +
                "          2) Your " + keywordColor + "POSITION" + resetColor + " action allows you to move one space FORWARD (toward opponent) or BACK (away from opponent).\n" +
                "          3) Finally, you choose an " + keywordColor + "ACTION" + resetColor + " that will affect your opponent and - to a lesser extent - yourself.");
        System.out.println();
    }
}