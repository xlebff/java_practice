package org.example;

import ws.schild.jave.*;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import javax.sound.sampled.*;
import java.io.*;

public class JaveMP3Player implements AutoCloseable {
    private Clip clip;
    private File tempWavFile;
    private boolean playing = false;
    private long duration = 0;
    private String trackName;

    public JaveMP3Player(File mp3File) throws Exception {
        this.trackName = mp3File.getName();
        tempWavFile = convertMp3ToWav(mp3File);

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(tempWavFile);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        duration = clip.getMicrosecondLength();

        audioStream.close();
    }

    private File convertMp3ToWav(File mp3File) throws Exception {
        File wavFile = File.createTempFile("jave_audio", ".wav");
        wavFile.deleteOnExit();

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("pcm_s16le"); // 16-bit little-endian PCM
        audio.setChannels(2); // stereo
        audio.setSamplingRate(44100); // 44.1 kHz

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat("wav");
        attrs.setAudioAttributes(audio);

        Encoder encoder = new Encoder();
        encoder.encode(new MultimediaObject(mp3File), wavFile, attrs);

        return wavFile;
    }

    public void play() {
        if (clip != null && !playing) {
            clip.start();
            playing = true;
        }
    }

    public void pause() {
        if (clip != null && playing) {
            clip.stop();
            playing = false;
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.setMicrosecondPosition(0);
            playing = false;
        }
    }

    public void seek(long microseconds) {
        if (clip != null) {
            microseconds = Math.min(microseconds, duration);
            clip.setMicrosecondPosition(microseconds);
        }
    }

    public long getPosition() {
        return clip != null ? clip.getMicrosecondPosition() : 0;
    }

    public long getDuration() {
        return duration;
    }

    public void setVolume(float volume) {
        if (clip != null) {
            try {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float min = gainControl.getMinimum();
                float max = gainControl.getMaximum();

                if (volume <= 0.001f) {
                    gainControl.setValue(min);
                } else {
                    float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
                    dB = Math.max(dB, min);
                    dB = Math.min(dB, max);
                    gainControl.setValue(dB);
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Регулировка громкости не поддерживается: " + e.getMessage());
            }
        }
    }

    public boolean isPlaying() {
        return playing;
    }

    public String getTrackName() {
        return trackName;
    }

    @Override
    public void close() {
        if (clip != null) {
            clip.close();
            clip = null;
        }
        if (tempWavFile != null && tempWavFile.exists()) {
            tempWavFile.delete();
        }
    }
}