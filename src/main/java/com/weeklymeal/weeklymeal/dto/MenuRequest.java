package com.weeklymeal.weeklymeal.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequest {
    private List<RecipeDto> recipeDtoList;
}
