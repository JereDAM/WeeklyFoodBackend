package com.weeklymeal.weeklymeal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.weeklymeal.weeklymeal.service.RecipeService;

@Controller
public class RecipeController {
	
	@Autowired
	RecipeService recipeService;

}
