package com.weeklymeal.weeklymeal.dto;

import com.weeklymeal.weeklymeal.entity.Recipe;

public class RecipeDtoMapper {
	
	public static RecipeDto toRecipeDto(Recipe recipe) {
		return new RecipeDto(recipe.getId(),recipe.getName(),recipe.getIngredients() ,recipe.getSteps(), recipe.getLabel(), recipe.getDescription());
	}
	
	public static Recipe toRecipe(RecipeDto recipeDto) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeDto.getId());
        recipe.setName(recipeDto.getName());
        recipe.setIngredients(recipeDto.getIngredients());
        recipe.setSteps(recipeDto.getSteps());
        recipe.setDescription(recipeDto.getDescription());
        return recipe;
    }

}
