package mars.ext.game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SoundPlayer {
    private HashMap<Integer, AudioClip> soundsMap;
    private ArrayList<Integer> loopSounds;

    public SoundPlayer() {
        loopSounds = new ArrayList<Integer>();
        loadSounds();
    }

    private void loadSounds() {
        soundsMap = new HashMap<Integer, AudioClip>();
        String file;
        int index = 0;
        if (GameConfigFile.properties != null) {
            GameConfigFile.usedGameSounds = new String[Integer.parseInt(GameConfigFile.properties.getProperty("soundCount"))];
            file = GameConfigFile.properties.getProperty("sound" + index);
            while (file != null) {
                GameConfigFile.usedGameSounds[index] = file;
                index++;
                file = GameConfigFile.properties.getProperty("sound" + index);
            }
        }

        for (int i = 0; i < GameConfigFile.usedGameSounds.length; i++) {

            URL url = getClass().getResource(GameConfigFile.usedGameSounds[i]);
            if (url == null) {
                System.out.println("Url is null for " + GameConfigFile.usedGameSounds[i]);
            } else {
                AudioClip clip = Applet.newAudioClip(getClass().getResource(GameConfigFile.usedGameSounds[i]));
                if (clip == null) {
                    System.out.println("Problem loading " + GameConfigFile.usedGameSounds[i]);
                } else {
                    soundsMap.put(i, clip);
                }
            }
        }
    } // end of loadSounds()

    public void play(int soundId, boolean toLoop) {
        AudioClip audioClip = (AudioClip) soundsMap.get(soundId);
        if (audioClip == null) {
            System.out.println("No clip for ID " + soundId);
            return;
        }
        if (toLoop) {
            audioClip.loop();
            loopSounds.add(soundId);
        } else {
            audioClip.play();
        }
    }

    public void stopSound(int soundId) {
        AudioClip audioClip = soundsMap.get(soundId);
        if (audioClip == null) {
            System.out.println("No clip for ID " + soundId);
            return;
        }
        audioClip.stop();
        if (loopSounds.contains(soundId)) {
            loopSounds.remove(new Integer(soundId));
        }
    }


    public void enable(boolean e) {
        loopAll(e, false);
    }

    public void stopAll() {
        loopAll(false, true);
    }

    public void loopAll(boolean play, boolean clearList) {
        AudioClip clip;
        for (int i = 0; i < loopSounds.size(); ++i) {
            int id = loopSounds.get(i);
            clip = soundsMap.get(id);
            if (clip != null) {
                if (play)
                    clip.loop();
                else clip.stop();
            }
        }
        if (clearList)
            loopSounds.clear();
    }

    public static void main(String args[]) {
        SoundPlayer player = new SoundPlayer();
        player.play(0, true);
    }
}
