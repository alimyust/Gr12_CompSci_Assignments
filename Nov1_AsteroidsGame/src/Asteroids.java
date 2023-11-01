import javax.swing.*;

public class Asteroids extends JFrame{
    AsteroidsPanel panel = new AsteroidsPanel();

    public Asteroids() {
        super("Asteroids");
        add(panel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        Asteroids frame = new Asteroids();
    }
}
