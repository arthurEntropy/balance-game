package com.splendorsound;

public class Player {

    //PROPERTIES
    private String name;
    private int energy;
    private int position;
    private String player;
    private Player opponent;

    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";

    //GETTERS AND SETTERS
    public String getName() {
        if (player == "player1") {
            return ANSI_YELLOW + name + opponent.ANSI_RESET;
        } else {
            return ANSI_GREEN + name + opponent.ANSI_RESET;
        }
    }
    public int getEnergy() {return energy;}
    public int getPosition() {return position;}
    public Player getOpponent() {return opponent;}
    public void setOpponent(Player opponent) {this.opponent = opponent;}

    //CONSTRUCTORS
    public Player(String player, String name) {
        this.name = name;
        this.player = player;
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
    public String getStance() {
        return energy + ":" + position;
    }

    public void changeEnergy(int energyChange){
        this.energy = getEnergy() + energyChange;
    }

    public void changePosition(int positionChange){
        this.position = getPosition() + positionChange;
    }

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

}
