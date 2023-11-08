import javax.swing.*;

public class Asteroids extends JFrame{
     static AsteroidsPanel asteroidsPanel = new AsteroidsPanel();

    public Asteroids() {
        super("Asteroids");
        add(asteroidsPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {new Asteroids();
    }
}
