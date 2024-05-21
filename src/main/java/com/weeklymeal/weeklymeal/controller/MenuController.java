package com.weeklymeal.weeklymeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weeklymeal.weeklymeal.dto.MenuDto;
import com.weeklymeal.weeklymeal.dto.MenuRequest;
import com.weeklymeal.weeklymeal.entity.Menu;
import com.weeklymeal.weeklymeal.service.MenuService;

@RestController
@RequestMapping("/api/v1")
public class MenuController {
	
	@Autowired
	MenuService menuService;
	
	//Get all menus
	@GetMapping("/menu")
	public List<MenuDto> allmenus(){
		return menuService.getAllMenus();
	}
	
	//Get an specific menu
	@GetMapping("/menu/{idMenu}")
	public Menu findByMenuId(@PathVariable Long idMenu) {
		return menuService.findById(idMenu);
	}
	
	//Get all menus from a user
	@GetMapping("/menus/{userId}")
	public ResponseEntity<?> allUserMenus(@PathVariable Long userId){
		return menuService.findUserMenus(userId);
	}
	
	//Create a menu
	@PostMapping("/menu/create/{idUser}")
    public MenuDto createMenu(@RequestBody MenuRequest createMenuRequest, @PathVariable Long idUser) {
        return menuService.createMenu(createMenuRequest.getMenuDto(), createMenuRequest.getRecipeDtoList(), idUser);
    }
	
	//Deletes a menu via id
	@DeleteMapping("/menu/{menuId}")
	public String deleteMenu(@PathVariable Long menuId) {
		return menuService.deleteMenu(menuId);
	}
	
	//Update a menu via id
	@PutMapping("/menu/{menuId}")
	public ResponseEntity<Menu> updateMenu(@PathVariable Long menuId, @RequestBody Menu menu) {
		return menuService.updateMenu(menuId, menu);
	}
	
	//Get latest menu
	@GetMapping("/menu/latest")
    public ResponseEntity<MenuDto> getLastCreatedMenu() {
        return menuService.getLastCreatedMenu();
    }
	

}
