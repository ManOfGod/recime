package com.recime.demo;

import com.recime.demo.model.Recipe;
import com.recime.demo.service.RecimeService;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private RecimeService service;

    @Test
    void getTrendingRecipeSuccess() throws URISyntaxException, Exception {
        
        var list = new ArrayList<Recipe>();
        list.add(new Recipe("Test1", Recipe.Difficulty.Easy, 1, true));
        list.add(new Recipe("Test2", Recipe.Difficulty.Medium, 2, true));
        list.add(new Recipe("Test3", Recipe.Difficulty.Hard, 3, true));
        list.add(new Recipe("Test4", Recipe.Difficulty.Easy, 4, true));
        list.add(new Recipe("Test5", Recipe.Difficulty.Easy, 5, true));
        list.add(new Recipe("Test6", Recipe.Difficulty.Easy, 6, true));
        list.add(new Recipe("Test7", Recipe.Difficulty.Easy, 7, true));      
        
        Mockito.when(service.getTrendingRecipes())
                .thenReturn(list);
        
        mockMvc.perform(get("/api/recime/trending")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(7)))
                .andExpect(jsonPath("$[4].name", is("Test5")));

    }
    
    @Test
    void getTrendingRecipeFilterByEasyDifficulty() throws URISyntaxException, Exception {
        
        var list = new ArrayList<Recipe>();
        list.add(new Recipe("Test1", Recipe.Difficulty.Easy, 1, true));
        list.add(new Recipe("Test2", Recipe.Difficulty.Easy, 2, true));
        list.add(new Recipe("Test3", Recipe.Difficulty.Easy, 3, true));
        list.add(new Recipe("Test4", Recipe.Difficulty.Easy, 4, true));
        list.add(new Recipe("Test5", Recipe.Difficulty.Easy, 5, true));
        
        var response = (ResponseEntity)ResponseEntity.ok(list);
               
        Mockito.when(service.getTrendingRecipesByDifficulty("easy"))
                .thenReturn(response);
        
        mockMvc.perform(get("/api/recime/trending/filtered")
                .param("difficulty", "easy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[4].difficulty", is("Easy")));
        
    }
    
    @Test
    void getTrendingRecipeFilterByMediumDifficulty() throws URISyntaxException, Exception {
        
        var list = new ArrayList<Recipe>();
        list.add(new Recipe("Test1", Recipe.Difficulty.Medium, 1, true));
        list.add(new Recipe("Test2", Recipe.Difficulty.Medium, 2, true));
        list.add(new Recipe("Test3", Recipe.Difficulty.Medium, 3, true));     
        
        var response = (ResponseEntity)ResponseEntity.ok(list);
               
        Mockito.when(service.getTrendingRecipesByDifficulty("medium"))
                .thenReturn(response);
        
        mockMvc.perform(get("/api/recime/trending/filtered")
                .param("difficulty", "medium")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].difficulty", is("Medium")))
                .andExpect(jsonPath("$[2].position", is(3)));
        
    }
    
    @Test
    void getTrendingRecipeFilterByHardDifficulty() throws URISyntaxException, Exception {
        
        var list = new ArrayList<Recipe>();
        list.add(new Recipe("Test1", Recipe.Difficulty.Hard, 1, true));
        list.add(new Recipe("Test2", Recipe.Difficulty.Hard, 2, true));
        list.add(new Recipe("Test3", Recipe.Difficulty.Hard, 3, true)); 
        list.add(new Recipe("Test4", Recipe.Difficulty.Hard, 4, true));
        list.add(new Recipe("Test5", Recipe.Difficulty.Hard, 7, true));
        list.add(new Recipe("Test6", Recipe.Difficulty.Hard, 8, true));
        list.add(new Recipe("Test7", Recipe.Difficulty.Hard, 13, true));
        list.add(new Recipe("Test8", Recipe.Difficulty.Hard, 23, true));
        list.add(new Recipe("Test9", Recipe.Difficulty.Hard, 43, true));
        
        var response = (ResponseEntity)ResponseEntity.ok(list);      
        
        Mockito.when(service.getTrendingRecipesByDifficulty("hard"))
                .thenReturn(response);
        
        mockMvc.perform(get("/api/recime/trending/filtered")
                .param("difficulty", "hard")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(9)))
                .andExpect(jsonPath("$[7].position", is(23)))
                .andExpect(jsonPath("$[7].difficulty", is("Hard")));
        
    }
    
    @Test
    void getTrendingRecipeFilterByNoneReturnsBadRequestError() throws URISyntaxException, Exception {
        
        var response = (ResponseEntity)ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("A difficulty is required for filtering trending recipes");
             
        Mockito.when(service.getTrendingRecipesByDifficulty(""))
                .thenReturn(response);
        
        mockMvc.perform(get("/api/recime/trending/filtered")
                .param("difficulty", "")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("A difficulty is required for filtering trending recipes")));
        
    }

}
