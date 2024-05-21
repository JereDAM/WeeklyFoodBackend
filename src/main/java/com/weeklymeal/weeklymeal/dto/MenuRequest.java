package com.weeklymeal.weeklymeal.dto;

import java.util.List;

public class MenuRequest {

    private MenuDto menuDto;
    
    private List<RecipeDto> recipeDtoList;

    public MenuDto getMenuDto() {
        return menuDto;
    }

    public void setMenuDto(MenuDto menuDto) {
        this.menuDto = menuDto;
    }

    public List<RecipeDto> getRecipeDtoList() {
        return recipeDtoList;
    }

    public void setRecipeDtoList(List<RecipeDto> recipeDtoList) {
        this.recipeDtoList = recipeDtoList;
    }
}
