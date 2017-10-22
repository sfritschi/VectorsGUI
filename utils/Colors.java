package utils;

import java.awt.Color;

public enum Colors {
    BLACK ("<html><font color='black'>black</font>", Color.BLACK),
    BROWN ("<html><font color='#BF8040'>brown</font>", Color.decode("#BF8040")),
    BLUE ("<html><font color='blue'>blue</font>", Color.BLUE),
    RED ("<html><font color='red'>red</font>", Color.RED),
    YELLOW ("<html><font color='yellow'>yellow</font>", Color.YELLOW),
    GREEN ("<html><font color='#66FF33'>green</font>", Color.GREEN),
    CYAN ("<html><font color='#00CCFF'>cyan</font>", Color.CYAN),
    DARK_GRAY ("<html><font color='#333333'>dark-gray</font>", Color.DARK_GRAY),
    GRAY ("<html><font color='gray'>gray</font>", Color.GRAY),
    LIGHT_GRAY ("<html><font color='#CCCCCC'>light-gray</font>", Color.LIGHT_GRAY),
    ORANGE ("<html><font color='orange'>orange</font>", Color.ORANGE),
    PINK ("<html><font color='#FFB3EC'>pink</font>", Color.PINK),
    MAGENTA ("<html><font color='#FF33CC'>magenta</font>", Color.MAGENTA),
    PURPLE ("<html><font color='#9900CC'>purple</font>", Color.decode("#9900CC"));
    
    private String text;
    private Color col;
    
    Colors(String text, Color col) {
        this.text = text;
        this.col = col;
    }
    
    public String getText() {
        return text;
    }
    
    public Color getCol() {
        return col;
    }
}
