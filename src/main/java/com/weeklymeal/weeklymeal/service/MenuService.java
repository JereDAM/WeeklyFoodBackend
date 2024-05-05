package com.weeklymeal.weeklymeal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weeklymeal.weeklymeal.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	MenuRepository menuRepository;
}
