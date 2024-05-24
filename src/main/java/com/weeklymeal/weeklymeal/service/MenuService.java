package com.weeklymeal.weeklymeal.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.weeklymeal.weeklymeal.dto.MenuDto;
import com.weeklymeal.weeklymeal.dto.RecipeDto;
import com.weeklymeal.weeklymeal.dto.RecipeDtoMapper;
import com.weeklymeal.weeklymeal.dto.menuMapper;
import com.weeklymeal.weeklymeal.entity.Menu;
import com.weeklymeal.weeklymeal.entity.Recipe;
import com.weeklymeal.weeklymeal.entity.User;
import com.weeklymeal.weeklymeal.repository.MenuRepository;
import com.weeklymeal.weeklymeal.repository.RecipesRepository;
import com.weeklymeal.weeklymeal.repository.UserRepository;


@Service
public class MenuService {

	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RecipesRepository recipeRepository;
	
	
	
	//Get a menu
	public Menu findById(Long id) {
		return menuRepository.findById(id).get();
	}
	
	//Get all menus from database
    public List<MenuDto> getAllMenus() {
    	// Retrieves all menus from the database
        List<Menu> menus = menuRepository.findAll();
        
        // Maps each Menu entity to a MenuDto using the menuMapper and collects them into a list
        return menus.stream().map(menuMapper::toMenuDto).collect(Collectors.toList());
    }
    
    //Creates the menu
    public MenuDto createMenu(List<RecipeDto> recipeDtos, Long userId) {
    	
    	//Searches the user, if not found, throws an exception
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Converts the list of RecipeDto to a set of Recipe using the mapping defined in RecipeDtoMapper
        Set<Recipe> recipes = recipeDtos.stream().map(RecipeDtoMapper::toRecipe).collect(Collectors.toSet());

        //Create the menu
        Menu menu = new Menu();
        menu.setCreated(LocalDateTime.now());
        menu.setUser(user);
        menu.setRecipes(recipes);

        //Saves the menu
        Menu savedMenu = menuRepository.save(menu);

        return menuMapper.toMenuDto(savedMenu);
    }

	
	//Delete a menu via ID
	public String deleteMenu(Long id) {
		menuRepository.deleteById(id);
		return "Receta eliminada";
	}
	
	//Updates just the date from the menu
	public ResponseEntity<Menu> updateMenu(Long id, Menu newMenu){
		
		//Searches the menu via id
		Optional<Menu> optionalMenu = menuRepository.findById(id);
		
		//If the menu is present, sets the new values
		if(optionalMenu.isPresent()) {
			Menu existingMenu = optionalMenu.get();
			Menu updatedMenu = menuRepository.save(existingMenu);
			return ResponseEntity.ok(updatedMenu);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//Gets all user menus
	public ResponseEntity<?> findUserMenus(Long userId) {
        try {
        	//Find all the menus from a user
            List<Menu> menuList = menuRepository.findMenusByUserId(userId);
            
            //If menus are not found returns a message
            if (menuList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no menus");
            } else {
            	
            	// If menus found, map each Menu entity to a MenuDto and collect them into a list
                List<MenuDto> userMenus = menuList.stream()
                        .map(menuMapper::toMenuDto)
                        .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK).body(userMenus);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error finding user menus");
        }
    }
	
    public MenuDto getLastCreatedMenu(Long userId) {
        Menu lastMenu = menuRepository.findTopByUserIdOrderByIdDesc(userId);
        if (lastMenu != null) {
            return menuMapper.toMenuDto(lastMenu);
        } else {
            return null;
        }
    }
	
}
