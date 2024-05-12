package com.weeklymeal.weeklymeal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.weeklymeal.weeklymeal.entity.Recipe;
import com.weeklymeal.weeklymeal.repository.RecipesRepository;

@Service
public class RecipeService {

	@Autowired
	RecipesRepository recipesRepository;
	
	//Get a recipes via id
	public Recipe findById(Long id) {
		return recipesRepository.findById(id).get();
	}
	
	//Get all the recipes
	public List<Recipe> findAllRecipes(){
		return recipesRepository.findAll();
	}
	
	//Creates a recipe
	public ResponseEntity<Recipe> createRecipe(Recipe recipe){
		recipesRepository.save(recipe);
		return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
	}
	
	//Delete a recipe via Id
	public String deleteRecipe(Long id) {
		recipesRepository.deleteById(id);
		return "Receta eliminada";
	}
}
