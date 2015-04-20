package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Torstein on 20.04.2015.
 */
public class BoardTexture extends Texture {
    private String imageName;
    public BoardTexture(String internalPath) {
        super(internalPath);
        imageName = internalPath;
    }

    public String getName() {
        return imageName.substring(0, imageName.length()-9);
    }
}
