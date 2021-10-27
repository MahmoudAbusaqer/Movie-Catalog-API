/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootmicroservices.moviecatalogservice.Services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springbootmicroservices.moviecatalogservice.models.CatalogItem;
import com.springbootmicroservices.moviecatalogservice.models.Movie;
import com.springbootmicroservices.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Mahmoud_Abusaqer
 */
@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCataItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());

    }

    public CatalogItem getFallbackCataItem(Rating rating) {
        return new CatalogItem("Movie name not found", "", rating.getRating());

    }
}
