import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
//music class taken from an online example that creates music objects and allows playing, etc.
public class Music {
    Clip clip;
    AudioInputStream sound;
    public void setFile(String soundFileName) {
        try {//gets file and tries setting the current object to the sound file
            File file = new File(soundFileName);
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void play() {clip.start();}//plays audio
    public void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }
}