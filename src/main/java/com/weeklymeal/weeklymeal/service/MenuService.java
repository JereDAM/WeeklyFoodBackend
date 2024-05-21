package com.weeklymeal.weeklymeal.service;


import java.util.List;
import java.util.Optional;
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
import com.weeklymeal.weeklymeal.entity.User;
import com.weeklymeal.weeklymeal.repository.MenuRepository;
import com.weeklymeal.weeklymeal.repository.UserRepository;

@Service
public class MenuService {

	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	UserRepository userRepository;
	
	//Get a menu
	public Menu findById(Long id) {
		return menuRepository.findById(id).get();
	}
	
	//Get all menus from database
    public List<MenuDto> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream().map(menuMapper::toMenuDto).collect(Collectors.toList());
    }
	
	//Create a new menu
	/*public ResponseEntity<Menu> createMenu(Menu menu, Long idUser, List<Recipe> recipeList){
		
		User user = userRepository.findById(idUser).get();
		List<Menu> totalMenus = menuRepository.findAll();
		totalMenus.add(menu);
		user.setMenus(totalMenus);
		menu.setUser(user);
		menu.setRecipes(recipeList);
		
		menuRepository.save(menu);
		return ResponseEntity.status(HttpStatus.CREATED).body(menu);
	}*/
    
    public MenuDto createMenu(MenuDto menuDto, List<RecipeDto> recipeDtoList, Long userId) {
        Menu menu = menuMapper.toMenu(menuDto);
        menu.setUser(userRepository.findById(userId).orElse(null));
        menu.setRecipes(recipeDtoList.stream().map(RecipeDtoMapper::toRecipe).collect(Collectors.toList()));
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
		Optional<Menu> optionalMenu = menuRepository.findById(id);
		
		if(optionalMenu.isPresent()) {
			Menu existingMenu = optionalMenu.get();
			existingMenu.setDate(newMenu.getDate());
			Menu updatedMenu = menuRepository.save(existingMenu);
			return ResponseEntity.ok(updatedMenu);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//Gets all user menus
	public ResponseEntity<?> findUserMenus(Long userId) {
        try {
            List<Menu> menuList = menuRepository.findMenusByUserId(userId);
            if (menuList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no menus");
            } else {
                List<MenuDto> userMenus = menuList.stream()
                        .map(menuMapper::toMenuDto)
                        .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK).body(userMenus);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error finding user menus");
        }
    }
	
	//Gets last menu created
	public ResponseEntity<MenuDto> getLastCreatedMenu() {
	    MenuDto lastMenuDto = null;
	    List<MenuDto> allMenus = getAllMenus();
	    
	    if (!allMenus.isEmpty()) {
	    	
	    	//Obtain the last menu here
	        lastMenuDto = allMenus.get(allMenus.size() - 1);
	        return ResponseEntity.ok(lastMenuDto);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
}
