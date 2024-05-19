package com.weeklymeal.weeklymeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weeklymeal.weeklymeal.entity.Recipe;
import com.weeklymeal.weeklymeal.service.RecipeService;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {
	
	@Autowired
	RecipeService recipeService;
	
	@GetMapping("/recipes")
	public List<Recipe> allRecipes(){
		return recipeService.findAllRecipes();
	}

}
