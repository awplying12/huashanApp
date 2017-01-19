package com.karazam.huashanapp.main.dialog.PromptDialog;

/**
 * Created by Administrator on 2017/1/19.
 */

public class TextProperty {
    private String text;
    private String textColour;

    public TextProperty(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextColour() {
        return textColour;
    }

    public void setTextColour(String textColour) {
        this.textColour = textColour;
    }

    @Override
    public String toString() {
        return "TextProperty{" +
                "text='" + text + '\'' +
                ", textColour='" + textColour + '\'' +
                '}';
    }
}
