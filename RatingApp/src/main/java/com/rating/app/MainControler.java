package com.rating.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainControler {
 
  
  @Autowired
  private movieRepository movieRepository;
  
  @Autowired
  private userRepository userRepository;
  
  @Autowired
  private ratingRepository ratingRepository;
  
  @RequestMapping(path="/add/user")
  public @ResponseBody long addUser (@RequestParam String name, @RequestParam String surname, @RequestParam String birthDate) {
      User user = new User();
      user.setName(name);
      user.setSurname(surname);
      user.setBirthDate(Utils.parseDate(birthDate));
      userRepository.save(user);
      return user.getUserId();
      
  }
  
  @RequestMapping(path="/get/users")
  public @ResponseBody Iterable<User> getAllUsers(){
    return userRepository.findAll();
  }
  
  @RequestMapping(path="/add/movie")
  public @ResponseBody Integer addMovie (@RequestParam String title, @RequestParam String isAdult, @RequestParam String date) {
      Movie movie = new Movie();
      movie.setTitle(title);
      movie.setIsAdult(new Boolean(isAdult));
      movie.setDate(Utils.parseDate(date));
      movieRepository.save(movie);
      return movie.getMovieId();
      
  }
  @RequestMapping(path="/get/movies")
  public @ResponseBody Iterable<Movie> getAllMovies(){
    return movieRepository.findAll();
  }
  
  
  @RequestMapping(path="/add/rating")
  public @ResponseBody long addRating (@RequestParam String movieId, @RequestParam String userId, @RequestParam String score, @RequestParam String date){
    Rating rating = new Rating();
    rating.setMovieId(new Integer(movieId));
    rating.setUserId(new Integer(userId));
    rating.setScore(new Integer(score));
    rating.setDate(Utils.parseDate(date));
    ratingRepository.save(rating);
    return rating.getRatingId();
  }
  @RequestMapping(path="/get/ratings")
  public @ResponseBody Iterable<Rating> getAllRatings(){
    return ratingRepository.findAll();
  }
  
  
  @RequestMapping(path="/mostRating")
  public @ResponseBody Movie mostRatting(String start, String end){
    Date startDate = Utils.parseDate(start);
    Date endDate = Utils.parseDate(end);
    
    HashMap<Integer, ArrayList<Integer>> mapMovieRatings= new HashMap<>();
    for (Rating r : ratingRepository.findAll()){
      if (r.getDate().after(startDate) && r.getDate().before(endDate)){
        if (!mapMovieRatings.containsKey(r.getMovieId())){
          mapMovieRatings.put(r.getMovieId(), new ArrayList<>());
        }else {
          mapMovieRatings.get(r.getMovieId()).add(r.getScore());
        }
      }
    }
    Integer movieId = 0;
    Double maxRate= -1.;
    for (Integer id : mapMovieRatings.keySet()){
      Integer size = mapMovieRatings.get(id).size();
      Double rate = (double) (mapMovieRatings.get(id).stream().reduce(0, Integer::sum)/size);
      if (rate> maxRate){
        movieId = id;
        maxRate = rate;
      }
    }
    
    if (movieId==0)
      return new Movie();
    else 
      return movieRepository.findById(movieId).orElse(new Movie());
  }
  
  @RequestMapping(path="/leastRating")
  public @ResponseBody Movie leastRatting(String start, String end){
    Date startDate = Utils.parseDate(start);
    Date endDate = Utils.parseDate(end);
    
    HashMap<Integer, ArrayList<Integer>> mapMovieRatings= new HashMap<>();
    for (Rating r : ratingRepository.findAll()){
      if (r.getDate().after(startDate) && r.getDate().before(endDate)){
        if (!mapMovieRatings.containsKey(r.getMovieId())){
          mapMovieRatings.put(r.getMovieId(), new ArrayList<>());
        }else {
          mapMovieRatings.get(r.getMovieId()).add(r.getScore());
        }
      }
    }
    Integer movieId = 0;
    Double minRate= 6.;
    for (Integer id : mapMovieRatings.keySet()){
      Integer size = mapMovieRatings.get(id).size();
      Double rate = (double) (mapMovieRatings.get(id).stream().reduce(0, Integer::sum)/size);
      if (rate< minRate){
        movieId = id;
        minRate = rate;
      }
    }
    
    if (movieId==0)
      return new Movie();
    else 
      return movieRepository.findById(movieId).orElse(new Movie());
  }
   
  
}
