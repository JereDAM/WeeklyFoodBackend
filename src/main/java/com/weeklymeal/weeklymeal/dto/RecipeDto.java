package com.weeklymeal.weeklymeal.dto;

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
public class RecipeDto {

	private Long id;
	private String name;
	private String ingredients;
	private String steps;
	private String label;
	private String description;
	private String weekDay;
}
