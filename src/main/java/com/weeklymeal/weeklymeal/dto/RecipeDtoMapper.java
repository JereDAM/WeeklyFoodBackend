package com.weeklymeal.weeklymeal.dto;

import com.weeklymeal.weeklymeal.entity.Recipe;

public class RecipeDtoMapper {
	
	public static RecipeDto toRecipeDto(Recipe recipe) {
		return new RecipeDto(recipe.getId(),recipe.getName(),recipe.getIngredients() ,recipe.getSteps(), recipe.getDescription());
	}

}
