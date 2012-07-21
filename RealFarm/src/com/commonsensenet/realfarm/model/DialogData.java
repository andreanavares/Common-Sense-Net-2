package com.commonsensenet.realfarm.model;

public class DialogData {
	   
    private String name;
    private int imgRes = -1;
    private int img2Res = -1;
    private int audioRes = -1;
    private String value;
    private String number = "";
    private int backgroundRes = -1;
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getImageRes() {
        return imgRes;
    }
    public void setImage(int img) {
        this.imgRes = img;
    }
    public int getImage2Res() {
        return img2Res;
    }
    public void setImage2(int img) {
        this.img2Res = img;
    }
    public int getAudioRes() {
        return audioRes;
    }
    public void setAudio(int audio) {
        this.audioRes = audio;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public int getBackgroundRes() {
        return backgroundRes;
    }
    public void setBackground(int background) {
        this.backgroundRes = background;
    }
}
