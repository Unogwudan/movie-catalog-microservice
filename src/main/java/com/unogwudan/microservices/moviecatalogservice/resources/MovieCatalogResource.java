package com.unogwudan.microservices.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.unogwudan.microservices.moviecatalogservice.models.CatalogItem;
import com.unogwudan.microservices.moviecatalogservice.models.Movie;
import com.unogwudan.microservices.moviecatalogservice.models.Rating;
import com.unogwudan.microservices.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
//	@Autowired
//	private WebClient.Builder webClientBuilder;

	@GetMapping("{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		 
		UserRating ratings = restTemplate
				.getForObject("http://localhost:8088/ratings/users/strats", UserRating.class);
		
		List<CatalogItem> movieCatalog = new ArrayList<>();
		ratings.getRatings().forEach(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8087/movies/" + 
					rating.getMovieId(), Movie.class);
			movieCatalog.add(new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating()));

//			Movie movie = webClientBuilder.build()
//				.get()
//				.uri("http://localhost:8087/movies/" + rating.getMovieId())
//				.retrieve()
//				.bodyToMono(Movie.class)
//				.block();
//			
//			movieCatalog.add(new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating()));
		});
		
		return movieCatalog;
	}
}
 