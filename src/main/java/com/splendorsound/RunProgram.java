package com.splendorsound;

// MJL - Remove unused import statements
import com.sun.tools.jconsole.JConsolePlugin;

import java.io.IOException;
import java.util.Scanner;

public class RunProgram {
    // MJL - Rather than implmenting your logic in the static main, use the main
    // method to create an instance of RunProgram().  You can then use member variables
    // to store the game state and pass that game state between methods.
    public static void main(String[] args) throws IOException, InterruptedException {

        //TODO make stance markers on map blend in with background (set background color?)

        // MJL - I kind of like that the player isn't just initials
        //TODO switch "@@@" to player initials - also require three character input

        // MJL - Adding more moves will be much easier as you refactor your code to
        // generalize your implementation.
        //TODO make moves options more readable, add more moves?
        //TODO figure out how to clear console between turns

        // MJL - Start with this
        //TODO make unit tests


        // MJL - It is generaly a good idea to move constants like this to a sharable location.
        // For example, a class name Colors with static constants.

        //SET VARIABLES FOR TEXT COLOR
        final String resetColor = "\u001B[0m"; //RESET
        final String errorColor = "\u001B[31m"; //RED
        final String player2Color = "\u001B[32m"; //GREEN
        final String player1Color = "\u001B[33m"; //YELLOW
        final String titleColor = "\u001B[35m"; //PURPLE
        final String keywordColor = "\u001B[36m"; //CYAN

        //GAME HEADER & INTRO
        printOutTitle(resetColor, titleColor, keywordColor);

        // MJL - I honestly had no idea how to do CLI input in Java.  Thanks for this.  This is
        // logic that I would put into some sort of input class so that the details of reading
        // input don't get in the way of game logic.

        //Open scanner, prompt to start new game or quit
        Scanner input = new Scanner(System.in);
        boolean validInput = false;

        // MJL - The while loop works perfectly fine here; however, if you now you are
        // absolutely going to do sometehing at least once before checking the condition, a
        // do loop is a better semantic fit (note, some people hate do loops).

        while (!validInput) {
            // MJL - This is where I learned that Java lacks string interpolation.
            System.out.print("Start " + keywordColor + "NEW GAME? " + resetColor + "(Y/N): ");
            // MJL - Limiting the scope of a variable is good practice; however, you use some
            // form of input throughout the game:  why not just have a single "input" value that
            // you re-use?  It reduces the need for very specific input values (which moves your
            // input logic towards a more generalized approach) and makes more effecient use of
            // memory (not super important here, but something to always consider).
            String startInput = input.nextLine();
            if (startInput.equalsIgnoreCase("y")) {
                validInput = true;
            } else if (startInput.equalsIgnoreCase("n")) {
                validInput = true;
                System.out.println(errorColor + "OK WEIRD CHOICE BYEEEEEEEEEE" + resetColor);
                System.exit(69);
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

        // MJL - If you look at my implementation you will see I also used an opponent property
        // on my Player object; however, this is generally not a good idea.  I chose to take the
        // approach because I did not want to introduce circulare linked lists (which is the better
        // choice here).  Think of this like being a DM:  the player should not really know who
        // their enemy is.  It is the game world's responsibility to track this information.  What
        // if you wanted to expand to a four player game?  Suddenly your player object logic gets
        // pretty complicated.

        //Set opponents
        player1.setOpponent(player2);
        player2.setOpponent(player1);

        // MJL - Declaring variables in the middle of a function... there are a few schools of 
        // thought here.  One is that you should declare the variable as close to the point of use
        // as possible.  I think this is true from a scope perspective (nested blocks of code),
        // but not in general.  Another is that you should declare your variables at the top of
        // the function:  I agree with this philosophy.  Introducing your variables is like intro-
        // ducing all of the characters in your story:  surprise characters are nice, but there
        // had better be a damn good reason for the late introduction.  Otherwise, let me know
        // who I should care about from the start... 
        //
        // Related, if you keep your functions short (general rule, a function should not require
        // you to scroll to read the whole thing), this situation doesn't really come up very
        // often.

        //GAME BEGINS:
        boolean gameWon = false;
        int turnCount = 0;
        Player currentPlayer = player1;

        // MJL - Declaring a winner before the game is over?  What is this... the NBA?  Initizling variables
        // is always a good idea; however, you should do it using safer values.  What if no one
        // wins (player takes themself off the map while doing a pull / push)?  I would start with
        // winner = null and I would skip the winnerName variable:  you can get that from the winner
        // instance later.
        Player winner = player1;

        // MJL - Treat variables as a precious commodity.  Always ask yourself if you really need it.
        // At this point it seems that you could always just use winner.getName().  Why have the extra
        // variable?  Variables generally represent mutable state in Java.  For the most part, the
        // more mutable state you have, the more likely you are to make a mistake somewhere.
        //
        // Related, avoiding null is also strongly recommended (even though I just suggested using null).
        // There is something called the Null Object Pattern that will help you avoid using null and
        // ensure you don't end up with NullPointerExceptions (NPE).
        String winnerName = winner.getName();

        //Print starting map
        Map map = new Map();
        System.out.println();
        // MJL - Good to see the DRY princple put to use for displaying the map
        map.printMap(player1, player2);
        System.out.println();

        //TURN STRUCTURE:

        while (!gameWon) {

            //Increment turn count
            // MJL - Somehow you indentation went awry here (one space left of where it should be).
            // This continues lines 138 - 155.
           turnCount++;

           //Take input for breath, execute position change
           validInput = false;
           while (!validInput) {
                // MJL - Here you are doing two distinct things:
                // 1. Getting user input
                // 2. Updating player state
                //
                // Try to avoid coupling this logic.  This would make your code easier to maintain
                // going forward and allow you to come up with a way to re-use input validation.
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

                // MJL - This certainly works, and works well; however, it tightly couples the two
                // player objects and means that changing victory conditions might mean you have to
                // modify your Player object:  changing the rules of the game world shouldn't maean
                // changing the objects that live in it.
                winner = currentPlayer.getOpponent();

                // MJL - "break" is indeed valid Java... and so is "goto" (apparently).  Unless
                // you are writting in assembly, you should try to avoid explicit jumps like this.
                // It makes code harded to follow and introduces the potential for significant bugs
                // Instead, you should be using conditional logic to dictate program flow below this
                // point.  Some people think "break" and "goto" are just fine:  they are wrong.
                break;
            }

            //Clear console
//            System.out.print("\033[H\033[2J");
//            System.out.flush();

            // MJL - Your identation is off again...
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

            // MJL - You repeat this check several times.  This means it is a good candidate
            // for a class level method... also, your indentation is off again.

            //Check to see if move took current player out of bounds.
            if(currentPlayer.isOutOfBounds()) {
                gameWon = true;
                winner = currentPlayer.getOpponent();
                break;
            }

            //Clear console
//            System.out.print("\033[H\033[2J");
//            System.out.flush();


            // MJL - I suggest turning on white space visibility if your IDE supports it.  It makes
            // very clear when your indentation is off.
           //Print updated map
           System.out.println();
           System.out.println();
           map.printMap(player1, player2);
           System.out.println();
           // MJL - Two blank lines... line 244 must be pretty important...


           //Take input for move, execute stance changes
            validInput = false;
            while (!validInput) {
                System.out.println(currentPlayer.getName() + "! CHOOSE YOUR " + keywordColor + "ACTION: " + resetColor + "\n");
                System.out.println(keywordColor + "PUSH" + resetColor + " = You forward 1, yang 1. Opponent back 2, yin 1");
                System.out.println(keywordColor + "PULL" + resetColor + " = You back 1, yin 1. Opponent forward 2, yang 1\n");
                System.out.println("YOUR CHOICE (" + keywordColor + "PUSH" + resetColor + "/" + keywordColor + "PULL): ");
                String actionInput = input.nextLine();

                // MJL - Your logic here certainly works, but it would be a bit cleaner to have
                // activePlayer and inactivePlayer variables.  Going through one player to modify
                // the state of another is another example of tight coupling.
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
                // MJL - Perhaps, gameOver would be a better variable name here.  In this
                // case, no one won, so...?
                //
                // gameOver = true;
                // winner = null;
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
            // MJL - Magic strings = bad.  I believe [checks Google] this is a command character?
            // If so, declare a constant much like you did for colors.
            System.out.print("\033[H\033[2J");
            System.out.flush();

            //Print updated map
            System.out.println();
            System.out.println();
            map.printMap(player1, player2);
            System.out.println();

            // MJL - This works, but the player objects shouldn't really be responsible for
            // tracking who is next.  It limits the extensibility of the game world.
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

        // MJL - If you do not default "winner" to player1, you could just check to see
        // if winner == null here.
        if(currentPlayer.isOutOfBounds() && currentPlayer.getOpponent().isOutOfBounds()){
            System.out.println("It's a DRAW!");
        }
        else {
            // MJL - I would avoid the extra mutable state here:  instead just put the
            // call to winner.getName() directly on line 323.
            winnerName = winner.getName();
            System.out.println(keywordColor + winnerName + " WINS!" + resetColor);
        }
        System.out.println(turnCount + " turns played.");

        //CLOSE SCANNER
        input.close();
    }

    // MJL - This doesn't really need to be static.  If you instead use the static main to create
    // an instance of the RunProgram class, you could then make this a method that is called via
    // your instance of the object.
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