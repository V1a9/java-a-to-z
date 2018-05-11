package com.vgoryashko.multithreading.bomberman;

/**
 * Class that defines a type of a hero (Bomberman or Monster).
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 10/4/17
 */
public class HeroType {

    /**
     * Field that defines a type of hero.
     * True - Bomberman, False - Monster.
     */
    private final boolean hero;

    /**
     * Constructor for the class.
     * @param hero type of a hero
     */
    public HeroType(boolean hero) {
        this.hero = hero;
    }

}
