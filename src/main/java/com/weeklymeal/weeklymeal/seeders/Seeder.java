package com.weeklymeal.weeklymeal.seeders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.weeklymeal.weeklymeal.entity.Menu;
import com.weeklymeal.weeklymeal.entity.Recipe;
import com.weeklymeal.weeklymeal.entity.User;
import com.weeklymeal.weeklymeal.repository.MenuRepository;
import com.weeklymeal.weeklymeal.repository.RecipesRepository;
import com.weeklymeal.weeklymeal.repository.UserRepository;

@Component
public class Seeder implements CommandLineRunner{
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private RecipesRepository recipeRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		if(userRepository.findAll().isEmpty()) {
			User user = new User();
			
			user.setUserName("SeederUser");
			user.setEmail("SeederMail@gmail.com");
			user.setPassword("123");
			
			userRepository.save(user);
			
			if(recipeRepository.findAll().isEmpty()) {
				Recipe recipe = new Recipe();
				Recipe recipe1 = new Recipe();
				Recipe recipe2 = new Recipe();
				Recipe recipe3 = new Recipe();
				Recipe recipe4 = new Recipe();
				Recipe recipe5 = new Recipe();
				Recipe recipe6 = new Recipe();
				
				recipe.setName("SeederRecipe");
				recipe.setIngredients("semillas");
				recipe.setSteps("muchos pasos");
				recipe.setLabel("Fibra");
				recipe.setDescription("una semillas para probar");
				recipe.setUser(user);
				recipeRepository.save(recipe);
				
				recipe1.setName("SeederRecipe1");
				recipe1.setIngredients("semillas");
				recipe1.setSteps("muchos pasos");
				recipe1.setLabel("Fibra");
				recipe1.setDescription("una semillas para probar");
				recipe1.setUser(user);
				recipeRepository.save(recipe1);
				
				recipe2.setName("SeederRecipe2");
				recipe2.setIngredients("semillas");
				recipe2.setSteps("muchos pasos");
				recipe2.setLabel("Fibra");
				recipe2.setDescription("una semillas para probar");
				recipe2.setUser(user);
				recipeRepository.save(recipe2);
				
				recipe3.setName("SeederRecipe3");
				recipe3.setIngredients("semillas");
				recipe3.setSteps("muchos pasos");
				recipe3.setLabel("Fibra");
				recipe3.setDescription("una semillas para probar");
				recipe3.setUser(user);
				recipeRepository.save(recipe3);
				
				recipe4.setName("SeederRecipe4");
				recipe4.setIngredients("semillas");
				recipe4.setSteps("muchos pasos");
				recipe4.setLabel("Fibra");
				recipe4.setDescription("una semillas para probar");
				recipe4.setUser(user);
				recipeRepository.save(recipe4);
				
				recipe5.setName("SeederRecipe15");
				recipe5.setIngredients("semillas");
				recipe5.setSteps("muchos pasos");
				recipe5.setLabel("Fibra");
				recipe5.setDescription("una semillas para probar");
				recipe5.setUser(user);
				recipeRepository.save(recipe5);
				
				recipe6.setName("SeederRecipe16");
				recipe6.setIngredients("semillas");
				recipe6.setSteps("muchos pasos");
				recipe6.setLabel("Fibra");
				recipe6.setDescription("una semillas para probar");
				recipe6.setUser(user);
				recipeRepository.save(recipe6);
				
				List<Recipe> recipeListSeeder = new ArrayList<>();
				
				recipeListSeeder.add(recipe);
				recipeListSeeder.add(recipe1);
				recipeListSeeder.add(recipe2);
				recipeListSeeder.add(recipe3);
				recipeListSeeder.add(recipe4);
				recipeListSeeder.add(recipe5);
				recipeListSeeder.add(recipe6);
				
				recipeRepository.saveAll(recipeListSeeder);
				
				if(menuRepository.findAll().isEmpty()) {
					
					Menu menu = new Menu();
					
					menu.setDate(LocalDate.now());
					menu.setCreated(LocalDateTime.now());
					menu.setRecipes(recipeListSeeder);
					menu.setUser(user);
					
					menuRepository.save(menu);
					
					for(Recipe r : recipeListSeeder) {
						r.setMenu(menu);
					}
					
					recipeRepository.saveAll(recipeListSeeder);
					
				}
			}
		}
		
	}

}
