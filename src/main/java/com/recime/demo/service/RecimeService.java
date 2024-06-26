package com.recime.demo.service;

import com.recime.demo.model.Recipe;
import com.recime.demo.repository.DataSource;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author junel
 */
@Service
@Slf4j
public class RecimeService {
    
    @SneakyThrows
    public List<Recipe> getTrendingRecipes() {
        try {
            return DataSource
                    .getData()
                    .stream()
                    .filter(data -> data.isTrending() == true)
                    .sorted(Comparator.comparingInt(Recipe::getPosition))
                    .toList();
        } catch (URISyntaxException ex) {
            log.error("Error getting trending recipes", ex);
        }
        
        return new ArrayList<>();
    }
    
    @SneakyThrows
    public ResponseEntity<?> getTrendingRecipesByDifficulty(String difficulty) {
        
        if (difficulty == null || difficulty.isBlank())
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("A difficulty is required for filtering trending recipes");
        
        var recipes = getTrendingRecipes()
                .stream()
                .filter(r -> r.getDifficulty().value.equalsIgnoreCase(difficulty))
                .toList();
        
        return ResponseEntity.ok(recipes);
    }
    
}
