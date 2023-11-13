import javax.swing.*;

public class Asteroids extends JFrame{
     AsteroidsPanel asteroidsPanel = new AsteroidsPanel();

    public Asteroids() {
        super("Asteroids");
        add(asteroidsPanel);
        setSize(800,900);
//        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        System.setProperty("jna.library.path", "Nov1_AsteroidsGame/src/lib");
        new Asteroids();
    }
}