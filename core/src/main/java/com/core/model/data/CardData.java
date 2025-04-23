package com.core.model.data;

public class CardData {
    private final String word;
    private final String imgFile;
    private final String audiofile;

    public CardData(String word, String imgFile, String audiofile) {
        this.audiofile = audiofile;
        this.word = word;
        this.imgFile = imgFile;
    }


    public String getWord() {
        return this.word;
    }

    public String getImgFile() {
        return this.imgFile;
    }

    public String getAudio() {
        return audiofile;
    }
}
