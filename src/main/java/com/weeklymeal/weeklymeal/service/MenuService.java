package com.weeklymeal.weeklymeal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.weeklymeal.weeklymeal.entity.Menu;
import com.weeklymeal.weeklymeal.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	MenuRepository menuRepository;
	
	//Get a menu
	public Menu findById(Long id) {
		return menuRepository.findById(id).get();
	}
	
	//Get all menus
	public List<Menu> findAllMenus(){
		return menuRepository.findAll();
	}
	
	//Create a new menu
	public ResponseEntity<Menu> createMenu(Menu menu){
		menuRepository.save(menu);
		return ResponseEntity.status(HttpStatus.CREATED).body(menu);
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
	public ResponseEntity<?> findUserMenus(Long userId){
		try {
			List<Menu> menuList = menuRepository.findMenusByUserId(userId);
			if(menuList.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no menus");
			}else {
				List<Menu> userMenus = new ArrayList<>();
				for(Menu menus : menuList) {
					userMenus.add(menus);
				}
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(userMenus);
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("There was an error finding user menus");
		}
	}
	
}