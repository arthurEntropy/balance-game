package com.splendorsound;

public class Map {

    //PROPERTIES
    private String currentMap;

    final String RESET = "\u001B[0m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";
    final String WHITE = "\u001B[97m";

    //GETTERS AND SETTERS

    //CONSTRUCTORS

    //METHODS
    public void printMap(Player player1, Player player2) {
        String currentMap =
                YELLOW +  "                         " + WHITE + "YANG                                                         YANG                          \n" +
                YELLOW +                    "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX   " + GREEN +                   "       XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  \n" +
                YELLOW +                    "  X         X         X         X         X         X   " + GREEN +                   "       X         X         X         X         X         X  \n" +
                YELLOW +                    "  X   4:0   X   4:1   X   4:2   X   4:3   X   4:4   X   " + GREEN +                   "       X   4:9   X   4:8   X   4:7   X   4:6   X   4:5   X  \n" +
                YELLOW +                    "  X         X         X         X         X         X   " + GREEN +                   "       X         X         X         X         X         X  \n" +
                YELLOW +                    "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX   " + GREEN +                   "       XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  \n" +
                YELLOW +                    "  X         X         X         X         X         X   " + GREEN +                   "       X         X         X         X         X         X  \n" +
                YELLOW +                    "  X   3:0   X   3:1   X   3:2   X   3:3   X   3:4   X   " + GREEN +                   "       X   3:9   X   3:8   X   3:7   X   3:6   X   3:5   X  \n" +
                YELLOW +                    "  X         X         X         X         X         X" + WHITE + " F      F" +      GREEN + " X         X         X         X         X         X  \n" +
                WHITE + "B" +      YELLOW +  " XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" + WHITE + " O      O" +      GREEN + " XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX " + WHITE + "B\n" +
                WHITE + "A" +      YELLOW +  " X         X         X         X         X         X" + WHITE + " R      R" +      GREEN + " X         X         X         X         X         X " + WHITE + "A\n" +
                WHITE + "C" +      YELLOW +  " X   2:0   X   2:1   X   2:2   X   2:3   X   2:4   X" + WHITE + " W      W" +      GREEN + " X   2:9   X   2:8   X   2:7   X   2:6   X   2:5   X " + WHITE + "C\n" +
                WHITE + "K" +      YELLOW +  " X         X         X         X         X         X" + WHITE + " A      A" +      GREEN + " X         X         X         X         X         X " + WHITE + "K\n" +
                YELLOW +                    "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" + WHITE + " R      R" +      GREEN + " XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  \n" +
                YELLOW +                    "  X         X         X         X         X         X" + WHITE + " D      D" +      GREEN + " X         X         X         X         X         X  \n" +
                YELLOW +                    "  X   1:0   X   1:1   X   1:2   X   1:3   X   1:4   X   " + GREEN +                   "       X   1:9   X   1:8   X   1:7   X   1:6   X   1:5   X  \n" +
                YELLOW +                    "  X         X         X         X         X         X   " + GREEN +                   "       X         X         X         X         X         X  \n" +
                YELLOW +                    "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX   " + GREEN +                   "       XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  \n" +
                YELLOW +                    "  X         X         X         X         X         X   " + GREEN +                   "       X         X         X         X         X         X  \n" +
                YELLOW +                    "  X   0:0   X   0:1   X   0:2   X   0:3   X   0:4   X   " + GREEN +                   "       X   0:9   X   0:8   X   0:7   X   0:6   X   0:5   X  \n" +
                YELLOW +                    "  X         X         X         X         X         X   " + GREEN +                   "       X         X         X         X         X         X  \n" +
                YELLOW +                    "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX   " + GREEN +                   "       XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  \n" +
                YELLOW +  "                          " + WHITE + "YIN                                                          YIN                          " + RESET;
        currentMap = currentMap.replace(player1.getStance(), WHITE + "@@@" + YELLOW);
        currentMap = currentMap.replace(player2.getStance(), WHITE + "@@@" + GREEN);
        System.out.println(currentMap);
        }
    public void printEmptyMap() {
        String currentMap =
                YELLOW +  "                         " + WHITE + "YANG                                                         YANG                          \n" +
                        YELLOW +                    "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX   " + GREEN +                   "       XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  \n" +
                        YELLOW +                    "  X         X         X         X         X         X   " + GREEN +                   "       X         X         X         X         X         X  \n" +
                        YELLOW +                    "  X   4:0   X   4:1   X   4:2   X   4:3   X   4:4   X   " + GREEN +                   "       X   4:9   X   4:8   X   4:7   X   4:6   X   4:5   X  \n" +
                        YELLOW +                    "  X         X         X         X         X         X   " + GREEN +                   "       X         X         X         X         X         X  \n" +
                        YELLOW +                    "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX   " + GREEN +                   "       XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  \n" +
                        YELLOW +                    "  X         X         X         X         X         X   " + GREEN +                   "       X         X         X         X         X         X  \n" +
                        YELLOW +                    "  X   3:0   X   3:1   X   3:2   X   3:3   X   3:4   X   " + GREEN +                   "       X   3:9   X   3:8   X   3:7   X   3:6   X   3:5   X  \n" +
                        YELLOW +                    "  X         X         X         X         X         X" + WHITE + " F      F" +      GREEN + " X         X         X         X         X         X  \n" +
                        WHITE + "B" +      YELLOW +  " XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" + WHITE + " O      O" +      GREEN + " XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX " + WHITE + "B\n" +
                        WHITE + "A" +      YELLOW +  " X         X         X         X         X         X" + WHITE + " R      R" +      GREEN + " X         X         X         X         X         X " + WHITE + "A\n" +
                        WHITE + "C" +      YELLOW +  " X   2:0   X   2:1   X   2:2   X   2:3   X   2:4   X" + WHITE + " W      W" +      GREEN + " X   2:9   X   2:8   X   2:7   X   2:6   X   2:5   X " + WHITE + "C\n" +
                        WHITE + "K" +      YELLOW +  " X         X         X         X         X         X" + WHITE + " A      A" +      GREEN + " X         X         X         X         X         X " + WHITE + "K\n" +
                        YELLOW +                    "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" + WHITE + " R      R" +      GREEN + " XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  \n" +
                        YELLOW +                    "  X         X         X         X         X         X" + WHITE + " D      D" +      GREEN + " X         X         X         X         X         X  \n" +
                        YELLOW +                    "  X   1:0   X   1:1   X   1:2   X   1:3   X   1:4   X   " + GREEN +                   "       X   1:9   X   1:8   X   1:7   X   1:6   X   1:5   X  \n" +
                        YELLOW +                    "  X         X         X         X         X         X   " + GREEN +                   "       X         X         X         X         X         X  \n" +
                        YELLOW +                    "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX   " + GREEN +                   "       XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  \n" +
                        YELLOW +                    "  X         X         X         X         X         X   " + GREEN +                   "       X         X         X         X         X         X  \n" +
                        YELLOW +                    "  X   0:0   X   0:1   X   0:2   X   0:3   X   0:4   X   " + GREEN +                   "       X   0:9   X   0:8   X   0:7   X   0:6   X   0:5   X  \n" +
                        YELLOW +                    "  X         X         X         X         X         X   " + GREEN +                   "       X         X         X         X         X         X  \n" +
                        YELLOW +                    "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX   " + GREEN +                   "       XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  \n" +
                        YELLOW +  "                          " + WHITE + "YIN                                                          YIN                          " + RESET;
        System.out.println(currentMap);
    }
}

