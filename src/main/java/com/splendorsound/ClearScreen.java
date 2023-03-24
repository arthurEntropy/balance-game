package com.splendorsound;
import java.io.IOException;


public class ClearScreen {
    // MJL - I legit had no idea you could put two "static void main" in a single package.
    // I guess this is where the META-INF folder / Maven plugin comes into play?  I assume
    // this was just for testing the screen clearing code.
    public static void main(String[] args)
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}