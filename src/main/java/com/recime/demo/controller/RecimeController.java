package com.recime.demo.controller;

import com.recime.demo.model.Recipe;
import com.recime.demo.service.RecimeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author junel
 */
@RestController
@RequestMapping("/api/recime")
public class RecimeController {
    
    @Autowired
    RecimeService service;
    
    @GetMapping("/trending")
    public List<Recipe> getTrendingRecipe() {
        return service.getTrendingRecipes();
    }
    
    @GetMapping("/trending/filtered")
    public ResponseEntity<?> getTrendingRecipe(@RequestParam("difficulty") String difficulty) {
        return service.getTrendingRecipesByDifficulty(difficulty);
    }
    
}
