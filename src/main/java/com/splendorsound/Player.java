package com.splendorsound;

public class Player {

    //PROPERTIES
    private String name;
    private int energy;
    private int position;
    private String player;
    private Player opponent;

    // MJL - Same comment as I put in other places with colors
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";

    //GETTERS AND SETTERS
    public String getName() {
        // MJL - The player object should not have to know if it is player1 or player2.
        // Instead you can just store the player color as a property and remove the 
        // conditional
        if (player == "player1") {
            // MJL - ANSI_RESET is the same regardless of player right?  Why specify the
            // oponent's ANS_RESET?  Just ANSI_RESET would work.
            return ANSI_YELLOW + name + opponent.ANSI_RESET;
        } else {
            return ANSI_GREEN + name + opponent.ANSI_RESET;
        }
    }

    // MJL - With Lombok you can ust @Getter and @Setter annotations
    public int getEnergy() {return energy;}
    public int getPosition() {return position;}
    public Player getOpponent() {return opponent;}
    public void setOpponent(Player opponent) {this.opponent = opponent;}

    //CONSTRUCTORS
    // MJL - Add the color, energy, and position as a constructor argument to simplify some logic
    public Player(String player, String name) {
        this.name = name;
        this.player = player;

        // MJL - Again, you really want to avoid this sort of check within the object.
        // If you were to expand to four players, this would require you to update your
        // Player object to support the new world rule.  That shouldn't be necessary.
        if (player.equals("player1")) {
            energy = 2;
            position = 2;
        }
        else {
            energy = 2;
            position = 7;
        }
    }

    //METHODS
    // MJL - I like how you combine "position" and "energy" into "stance".  It is good use
    // of domain terminology.  When I wrote my implementation I just used "x" and "y".
    // that worked, but does not capture a core concept of the game.
    public String getStance() {
        return energy + ":" + position;
    }

    public void changeEnergy(int energyChange){
        this.energy = getEnergy() + energyChange;
    }

    public void changePosition(int positionChange){
        this.position = getPosition() + positionChange;
    }

    // MJL - If you were to add an orientation property to the Player class, instead of
    // needing to know which player you are checking, you could do it based on player
    // orientation (e.g. FACING_LEFT, FACING_RIGHT).
    //
    // I would recommend you avoid hard coding the bounds here.  What if the "size" of the
    // map changes?  Instead, you could have a Platform object with min / max values. Then
    // pass in the relevant Platform here and check player stance against the platform's
    // bounds.  This would also mean that you don't need to use the conditional logic to
    // determine which bounds to check:  they would be defined in the Platform object.
    public boolean isOutOfBounds() {
        if (player.equals("player1") && (energy > 4 || energy < 0 || position > 4 || position < 0)) {
            return true;
        }
        else if (player.equals("player2") && (energy > 4 || energy < 0 || position > 9 || position < 5)) {
            return true;
        }
        else {
            return false;
        }
    }
    // MJL - Why the extra empty line here?

}
