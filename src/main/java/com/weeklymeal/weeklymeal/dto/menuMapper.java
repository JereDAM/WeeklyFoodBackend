package com.weeklymeal.weeklymeal.dto;

import java.util.stream.Collectors;

import com.weeklymeal.weeklymeal.entity.Menu;

public class menuMapper {
	
    public static MenuDto toMenuDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setDate(menu.getDate());
        menuDto.setCreated(menu.getCreated());
        menuDto.setRecipes(menu.getRecipes().stream().map(RecipeDtoMapper::toRecipeDto).collect(Collectors.toList()));
        return menuDto;
    }
    
    

}
