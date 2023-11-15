import javax.swing.*;

public class Asteroids extends JFrame{
     AsteroidsPanel asteroidsPanel = new AsteroidsPanel();

    public Asteroids() {
        add(asteroidsPanel);
        setSize(800,900);
//        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new Asteroids();
    }
}