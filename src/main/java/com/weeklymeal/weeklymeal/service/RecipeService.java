package com.weeklymeal.weeklymeal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.weeklymeal.weeklymeal.dto.RecipeDto;
import com.weeklymeal.weeklymeal.dto.RecipeDtoMapper;
import com.weeklymeal.weeklymeal.entity.Recipe;
import com.weeklymeal.weeklymeal.entity.User;
import com.weeklymeal.weeklymeal.repository.RecipesRepository;
import com.weeklymeal.weeklymeal.repository.UserRepository;

@Service
public class RecipeService {

	@Autowired
	RecipesRepository recipesRepository;
	
	@Autowired
	UserRepository userRepository;
	
	//Get a recipes via id
	public Recipe findById(Long id) {
		return recipesRepository.findById(id).get();
	}
	
	//Get all the recipes
	public List<Recipe> findAllRecipes(){
		return recipesRepository.findAll();
	}
	
	//Creates a recipe
	public ResponseEntity<RecipeDto> createRecipe(Recipe recipe, Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		
		recipe.setUser(user);
		RecipeDto recipeDto = RecipeDtoMapper.toRecipeDto(recipe);
		Recipe savedRecipe = recipesRepository.save(recipe);
		user.getRecipes().add(savedRecipe);
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(recipeDto);
	}
	
	//Delete a recipe via Id
	public String deleteRecipe(Long id) {
		recipesRepository.deleteById(id);
		return "Receta eliminada";
	}
	
	//Updates a recipe
	public ResponseEntity<Recipe> updateRecipe(Long id, Recipe newRecipe) {
		Optional<Recipe> optionalRecipe = recipesRepository.findById(id);
		
		if (optionalRecipe.isPresent()) {
			Recipe existingRecipe = optionalRecipe.get();
			existingRecipe.setName(newRecipe.getName());
			existingRecipe.setIngredients(newRecipe.getIngredients());
			existingRecipe.setSteps(newRecipe.getSteps());
			existingRecipe.setLabel(newRecipe.getLabel());
			existingRecipe.setDescription(newRecipe.getDescription());
			Recipe updatedRecipe = recipesRepository.save(existingRecipe);
			return ResponseEntity.ok(updatedRecipe);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	//Get all recipes from a user
	public ResponseEntity<?> findUserRecipes(Long userId){
		try {
			List<Recipe> recipeList = recipesRepository.findByUserId(userId);
			if(recipeList.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no recipes");
			} else {
	
				List<RecipeDto> userRecipes = new ArrayList<>();
				for(Recipe recipe: recipeList) {
					RecipeDto recipeDto = RecipeDtoMapper.toRecipeDto(recipe);
					userRecipes.add(recipeDto);
				}
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(userRecipes);
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("There was an error finding user recipes");
		}
	}
}
