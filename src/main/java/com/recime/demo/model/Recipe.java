package com.recime.demo.model;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author junel
 */
@Getter 
@Setter
public class Recipe implements Serializable {

    private String name;
    private Difficulty difficulty;
    private int position;
    private boolean isTrending;
    private String imageUrl;
    
    public Recipe(String name, Difficulty difficulty, int position, boolean isTrending) 
            throws URISyntaxException {
        this.name = name;
        this.difficulty = difficulty;
        this.position = position;
        this.isTrending = isTrending;
        this.imageUrl = new URI("https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg")
                .toString();
    }
    
    public enum Difficulty {
        Easy("easy"),
        Medium("medium"),
        Hard("hard");
        
        public final String value;
        
        Difficulty(String value) {
            this.value = value;
        }
    }
}


