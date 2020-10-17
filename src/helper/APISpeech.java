package helper;

import com.darkprograms.speech.synthesiser.SynthesiserV2;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.IOException;


public class APISpeech {
    SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

    public APISpeech(String s) {
        this.speak(s);
    }

    public void speak(String text) {
        System.out.println(text);
        Thread thread = new Thread(() -> {
            try {
                AdvancedPlayer player = new AdvancedPlayer(this.synthesizer.getMP3Data(text));
                player.play();
                System.out.println("Successfully got back synthesizer data");
            } catch (JavaLayerException | IOException var3) {
                var3.printStackTrace();
            }

        });
        thread.setDaemon(false);
        thread.start();
    }
}
