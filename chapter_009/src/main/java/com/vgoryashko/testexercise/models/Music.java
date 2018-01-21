package com.vgoryashko.testexercise.models;

/**
 * Class that defines model Music.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 1/15/18
 */
public class Music {

    private long id;
    private String musicGenre;

    public Music() {
    }

    public Music(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    public String getMusicGenre() {
        return this.musicGenre;
    }

    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

        return musicGenre != null ? musicGenre.equals(music.musicGenre) : music.musicGenre == null;
    }

    @Override
    public int hashCode() {
        return musicGenre != null ? musicGenre.hashCode() : 0;
    }
}
