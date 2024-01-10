package com.springMovie.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ControllerMVC {
	
	static final List<Movie> movieList = new ArrayList<>();

	ControllerMVC() {
		movieList.add(new Movie(1, "SDA", 2001));
		movieList.add(new Movie(2, "Rambo", 1980));
		movieList.add(new Movie(3, "Shining", 1980));
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/form")
	public String form() {
		return "form";
	}
	
	@RequestMapping("/update")
	public String update(Model model) {
		
		//lamba to get all Ids
		List<Long> idList = movieList.stream().map(Movie::getId).collect(Collectors.toList());
		//instead of Movie::getId I could've used     item -> item.getId()
		
		model.addAttribute("idList", idList);
		return "update";
	}
	
	
	
	
	
	

}
