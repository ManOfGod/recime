/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.recime.demo.repository;

import com.recime.demo.model.Recipe;
import com.recime.demo.model.Recipe.Difficulty;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;

/**
 *
 * @author junel
 */
public class DataSource {
    
    @SneakyThrows
    public static List<Recipe> getData() throws URISyntaxException {
        var list = new ArrayList<Recipe>();
        
        list.add(new Recipe("Adobo", Difficulty.Easy, 1, true));
        list.add(new Recipe("Bicol Express", Difficulty.Medium, 2, true));
        list.add(new Recipe("Letchon", Difficulty.Hard, 3, true));
        list.add(new Recipe("Sinigang", Difficulty.Easy, 4, true));
        list.add(new Recipe("Kinilaw", Difficulty.Medium, 5, false));
        list.add(new Recipe("Garlic Buttered Shrimp", Difficulty.Easy, 6, true));
        list.add(new Recipe("Egg Fried Rice", Difficulty.Easy, 7, true));
        list.add(new Recipe("Pancit Canton", Difficulty.Easy, 8, false));
        list.add(new Recipe("Menudo", Difficulty.Medium, 9, false));
        list.add(new Recipe("Fried Chicken", Difficulty.Easy, 10, true));
        list.add(new Recipe("Fish and Chips", Difficulty.Easy, 11, false));
        
        return list;
    }
    
}
