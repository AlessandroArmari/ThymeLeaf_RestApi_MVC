package com.springMovie.demo.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import model.Movie;

@RestController
public class RestControllerApi {

	List<Movie> movieList = new ArrayList<>();

	RestControllerApi() {
		movieList.add(new Movie(1, "SDA", 2001));
		movieList.add(new Movie(2, "Rambo", 1980));
		movieList.add(new Movie(3, "Shining", 1980));
	}

	@GetMapping("/getAll")
	public List<Movie> getAll() {

		return movieList;
	}

	@GetMapping("/getById")
	public ResponseEntity<Movie> getById(@RequestParam("id") long id) {

		// lamba expression
		Optional<Movie> movieOpt = movieList.stream().filter(item -> item.getId() == id).findFirst();
		if (movieOpt.isEmpty())
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		else {
			Movie movie = movieOpt.get();
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
		}

	}
	
	
	//I didn't use @RequestBody because this data come from a <form> tag,
	//and they seem not to allow @RequestBody
	//application/x-www-form-urlencoded
	@PostMapping(value="/create")
	public ResponseEntity<Movie> create(Movie newMovie) {

		long maxId = movieList.stream().mapToLong(item -> item.getId()).max().getAsLong();
		newMovie.setId(++maxId);
		boolean ok = movieList.add(newMovie);
		if (!ok)
			return new ResponseEntity<Movie>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Movie>(newMovie, HttpStatus.OK);
	}
	
	

	@PutMapping("/updateById")
	public Movie updateById(@RequestParam("id") long id, @RequestBody Movie newMovie) {

		// lamba expression
		Optional<Movie> oldMovieOpt = movieList.stream().filter(item -> item.getId() == id).findFirst();
		if (oldMovieOpt.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no movie found");
		else {
			Movie oldMovie = oldMovieOpt.get();
			oldMovie.setTitle(newMovie.getTitle());
			oldMovie.setYear(newMovie.getYear());
			return oldMovie;
		}

	}

	@DeleteMapping("/deleteById")
	public ResponseEntity<List<Movie>> delete(@RequestParam("id") long id) {

		Optional<Movie> movieToDelete = movieList.stream().filter(item -> item.getId() == id).findFirst();

		if (movieToDelete.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no movie found");
		else {
			movieList.remove(movieToDelete.get());
			return new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
		}
	}

}
