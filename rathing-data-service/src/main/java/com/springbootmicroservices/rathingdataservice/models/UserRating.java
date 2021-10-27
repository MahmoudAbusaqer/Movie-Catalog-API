/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootmicroservices.rathingdataservice.models;

import java.util.Arrays;
import java.util.List;
import com.springbootmicroservices.rathingdataservice.models.Rating;

/**
 *
 * @author Mahmoud_Abusaqer
 */
public class UserRating {

    private String userId;
    private List<Rating> userRating;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Rating> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }
    
    public void initData(String userId) {
        this.setUserId(userId);
        this.setUserRating(Arrays.asList(
                new Rating("100", 3),
                new Rating("200", 4)
        ));
    }

}
