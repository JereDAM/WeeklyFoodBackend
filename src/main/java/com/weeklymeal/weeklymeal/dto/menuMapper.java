package com.weeklymeal.weeklymeal.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.weeklymeal.weeklymeal.entity.Menu;
import com.weeklymeal.weeklymeal.entity.Recipe;

public class menuMapper {
	
    public static MenuDto toMenuDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setDate(menu.getDate());
        menuDto.setCreated(menu.getCreated());
        menuDto.setRecipes(menu.getRecipes().stream().map(RecipeDtoMapper::toRecipeDto).collect(Collectors.toList()));
        return menuDto;
    }
    
    public static Menu toMenu(MenuDto menuDto) {
        Menu menu = new Menu();
        menu.setId(menuDto.getId());
        menu.setDate(menuDto.getDate());
        menu.setCreated(menuDto.getCreated());
        
        List<Recipe> recipes = menuDto.getRecipes() != null ? 
                                menuDto.getRecipes().stream().map(RecipeDtoMapper::toRecipe).collect(Collectors.toList()) :
                                Collections.emptyList();
        menu.setRecipes(recipes);

        return menu;
    }
    
    

}
