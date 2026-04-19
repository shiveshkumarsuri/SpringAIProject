package com.shivesh.ai.java.movie.recommender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieRecommender {

    private Map<String, Map<String,Integer>> userRatings;

    public MovieRecommender() {
        this.userRatings = new HashMap<>();
    }

    public void addRating(String user, String movie,int rating){
        userRatings.computeIfAbsent(user, key -> new HashMap<>()).put(movie, rating);
    }

    /*private double calculateSimilarity(String user1, String user2){
        List<String> commonMovies = new ArrayList<>();
        for (String movie : userRatings.keySet()){
            if(user)
        }
    }*/
}
