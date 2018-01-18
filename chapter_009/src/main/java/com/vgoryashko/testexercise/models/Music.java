package com.vgoryashko.testexercise.models;

/**
 * Class that defines model Music.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 1/15/18
 */
public class Music {

    private String musicGanre;

    public Music(String musicGanre) {
        this.musicGanre = musicGanre;
    }

    public String getMusicGanre() {
        return this.musicGanre;
    }

    public void setMusicGanre(String musicGanre) {
        this.musicGanre = musicGanre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Music)) {
            return false;
        }

        Music music = (Music) o;

        return musicGanre != null ? musicGanre.equals(music.musicGanre) : music.musicGanre == null;
    }

    @Override
    public int hashCode() {
        return musicGanre != null ? musicGanre.hashCode() : 0;
    }
}
