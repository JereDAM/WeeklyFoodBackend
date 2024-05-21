package com.weeklymeal.weeklymeal.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuDto {
	
	private Long id;
	private LocalDate date;
	private LocalDateTime created;
	private List<RecipeDto> recipes;

}
