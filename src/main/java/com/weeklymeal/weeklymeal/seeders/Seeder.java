package com.weeklymeal.weeklymeal.seeders;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
		
		//Creates mocking data
		
		if(userRepository.findAll().isEmpty()) {
			User user = new User();
			
			User admin = new User();
			
			user.setUserName("SeederUser");
			user.setEmail("SeederMail@gmail.com");
			user.setPassword("123");
			user.setRole(User.ROLE_USER);
			
			admin.setUserName("AdminMeal");
			admin.setEmail("adminMeal@gmail.com");
			admin.setPassword("123admin");
			admin.setRole(User.ROLE_ADMIN);
			
			userRepository.save(user);
			
			userRepository.save(admin);
			
			if(recipeRepository.findAll().isEmpty()) {	
				
				Set<Recipe> recipes = new HashSet<>();

                for (int i = 0; i < 7; i++) {
                    Recipe recipe = new Recipe();
                    recipe.setName("SeederRecipe" + i);
                    recipe.setIngredients("semillas");
                    recipe.setSteps("muchos pasos");
                    recipe.setLabel("fibra");
                    recipe.setDescription("una semilla para probar");
                    recipe.setWeekDay("");
                    recipe.setUser(user);
                    recipeRepository.save(recipe);
                    recipes.add(recipe);
                }
                
                if (menuRepository.findAll().isEmpty()) {
                    Menu menu = new Menu();
                    menu.setCreated(LocalDateTime.now());
                    menu.setRecipes(recipes);
                    menu.setUser(user);
                    menuRepository.save(menu);
                }
			}
		}
		
	}

}