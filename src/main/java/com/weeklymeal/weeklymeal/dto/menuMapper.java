package com.weeklymeal.weeklymeal.dto;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import com.weeklymeal.weeklymeal.entity.Menu;
import com.weeklymeal.weeklymeal.entity.Recipe;

public class menuMapper {
	
	//Converts menu to dto
    public static MenuDto toMenuDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setCreated(menu.getCreated());
        
        //Map each Recipe associated with the menu to a RecipeDto using RecipeDtoMapper
        //and add them into the set
        menuDto.setRecipes(menu.getRecipes().stream().map(RecipeDtoMapper::toRecipeDto).collect(Collectors.toSet()));
        return menuDto;
    }	
    
    public static Menu toMenu(MenuDto menuDto) {
        Menu menu = new Menu();
        menu.setId(menuDto.getId());
        menu.setCreated(menuDto.getCreated());
        
        /* Map each RecipeDto associated with the menuDto to a Recipe entity using RecipeDtoMapper,
        and collect them into a Set in the Menu entity*/
        Set<Recipe> recipes = menuDto.getRecipes() != null ? 
                                menuDto.getRecipes().stream().map(RecipeDtoMapper::toRecipe).collect(Collectors.toSet()) :
                                Collections.emptySet();
        menu.setRecipes(recipes);

        return menu;
    }
    
    

}
