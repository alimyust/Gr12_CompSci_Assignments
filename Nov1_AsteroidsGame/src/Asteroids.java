//ICS4U
// Simple Game Project: Asteroids
// Ali Mustafa
// Nov 15 2023
/*
* A Simple remake with slight modification to some features
* Includes:
*   Player with rotation control and a boost that propels them forward with declaration
*   Shooting that causes objects to be destroyed
*   UFOs that shoot at the player ( 2 sizes )
*   Meteoroids that break into 2 smaller versions ( 3 total sizes )
*   Particles that come out of broken things as a visual effect
*   Sounds implemented for basic actions
*   A score page that keeps track of the highest scores ( top 5 )
*   Logic to determine if a new high score is achieved, and text acknowledging it
*   Dynamic background
*   Progression system that gradually ramps up in difficulty
*
*/

import javax.swing.*;
import java.awt.*;

public class Asteroids extends JFrame{
     AsteroidsPanel asteroidsPanel = new AsteroidsPanel();

    public Asteroids() {
        add(asteroidsPanel);
        setSize(800,900);
        setTitle("Asteroids");
        ImageIcon icon=new ImageIcon("Nov1_AsteroidsGame/asteroidsIcon.png");
        Image image=icon.getImage();
        setIconImage(image);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new Asteroids();
    }
}