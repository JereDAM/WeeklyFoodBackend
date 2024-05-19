package com.weeklymeal.weeklymeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weeklymeal.weeklymeal.entity.Recipe;
import com.weeklymeal.weeklymeal.service.RecipeService;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {
	
	@Autowired
	RecipeService recipeService;
	
	//Get ALL recipes
	@GetMapping("/recipes")
	public List<Recipe> allRecipes(){
		return recipeService.findAllRecipes();
	}
	
	//Get an specific recipe via id
	@GetMapping("/recipe/{recipeId}")
	public Recipe findRecipeById(@PathVariable Long recipeId) {
		return recipeService.findById(recipeId);
	}
	
	//Get ALL menus FROM a user
	@GetMapping("recipe/{userId}")
	public ResponseEntity<?> allUserRecipes(@PathVariable Long userId){
		return recipeService.findUserRecipes(userId);
	}
	
	//Create a new recipe
	@PostMapping("/recipe")
	public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId){
		return recipeService.createRecipe(recipe, userId);
	}
	
	//Deletes a recipe via id
	@DeleteMapping("/recipe/{recipeId}")
	public String deleteRecipe(@PathVariable Long recipeId) {
		return recipeService.deleteRecipe(recipeId);
	}
	
	//Update a recipe via id
	@PutMapping("/recipe/{recipeId}")
	public ResponseEntity<Recipe> updateRecipe(@PathVariable Long recipeId, @RequestBody Recipe recipe){
		return recipeService.updateRecipe(recipeId, recipe);
	}
	
	

}
