/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootmicroservices.moviecatalogservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springbootmicroservices.moviecatalogservice.Services.MovieInfo;
import com.springbootmicroservices.moviecatalogservice.Services.UserRatingInfo;
import com.springbootmicroservices.moviecatalogservice.models.CatalogItem;
import com.springbootmicroservices.moviecatalogservice.models.Movie;
import com.springbootmicroservices.moviecatalogservice.models.Rating;
import com.springbootmicroservices.moviecatalogservice.models.UserRating;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author Mahmoud_Abusaqer
 */
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    MovieInfo movieInfo;
    @Autowired
    UserRatingInfo userRatingInfo;
    @RequestMapping("/{userId}")
//    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatlog(@PathVariable("userId") String userId) {
        //Get all rated movir IDs
        UserRating ratings = userRatingInfo.getUserRating(userId);
        return ratings.getUserRating().stream()
                .map(rating
                        -> //For each movie ID, call movie info service and get details
                        //                    Movie movie = webClientBuilder.build()
                        //                            .get()
                        //                            .uri("http://localhost:8082/movies/"+ rating.getMovieId())
                        //                            .retrieve()
                        //                            .bodyToMono(Movie.class)
                        //                            .block();
                        //Put them all togerher
                        movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());

//        return Collections.singletonList(
//                new CatalogItem("Transformers", "Test", 4));
    }

    

    

    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
        return Arrays.asList(new CatalogItem("No movie", "", 0));
    }

}
