package com.weeklymeal.weeklymeal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weeklymeal.weeklymeal.repository.RecipesRepository;

@Service
public class RecipeService {

	@Autowired
	RecipesRepository recipesRepository;
}
