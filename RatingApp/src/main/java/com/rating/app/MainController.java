package com.rating.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MainController {
 
  
  @Autowired
  private movieRepository movieRepository;
  
  @Autowired
  private userRepository userRepository;
  
  @Autowired
  private ratingRepository ratingRepository;
  
  
  @PostMapping(path="/add/user" )
  public @ResponseBody ResponseEntity<Integer> addUser (HttpEntity<String> req) {
    try {
      String body = req.getBody();
      System.out.println(body);
      ObjectMapper mapper = new ObjectMapper();
      JsonFactory factory = mapper.getFactory();
      JsonParser parser = factory.createParser(body);
      JsonNode json = mapper.readTree(parser);
      User user = new User();
      user.setName(json.get("name").asText());
      user.setSurname(json.get("surname").asText());
      user.setBirthDate(Utils.parseDate(json.get("birthDate").asText()));
      userRepository.save(user);
      return new ResponseEntity<Integer>(user.getUserId(),  HttpStatus.OK);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return new ResponseEntity<Integer>(0,  HttpStatus.BAD_REQUEST);
    }
      
  }
  

  @PostMapping(path="/get/users")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }
  
  @PostMapping(path="/add/movie")
  public @ResponseBody ResponseEntity<Integer> addMovie (HttpEntity<String> req) {
    try {
      String body = req.getBody();
      System.out.println(body);
      ObjectMapper mapper = new ObjectMapper();
      JsonFactory factory = mapper.getFactory();
      JsonParser parser = factory.createParser(body);
      JsonNode json = mapper.readTree(parser);
      
      Movie movie = new Movie();
      movie.setTitle(json.get("title").asText());
      movie.setIsAdult(json.get("isAdult").asBoolean());
      movie.setDate(Utils.parseDateMovie(json.get("date").asText()));
      movie.setGenres(Utils.parseGenresToList(json.get("genres")));
      movieRepository.save(movie);
      return new ResponseEntity<Integer>(movie.getMovieId(),  HttpStatus.OK);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return new ResponseEntity<Integer>(0,  HttpStatus.BAD_REQUEST);
    }
      
  }



  @PostMapping(path="/get/movies")
  public @ResponseBody Iterable<Movie> getAllMovies(){
    return movieRepository.findAll();
  }
  
  
  @PostMapping(path="/add/rating")
  public @ResponseBody ResponseEntity<Integer> addRating (HttpEntity<String> req){
    try {
      String body = req.getBody();
      System.out.println(body);
      ObjectMapper mapper = new ObjectMapper();
      JsonFactory factory = mapper.getFactory();
      JsonParser parser = factory.createParser(body);
      JsonNode json = mapper.readTree(parser);
      
      Rating rating = new Rating();
      rating.setMovieId(json.get("movieId").asInt());
      rating.setUserId(json.get("userId").asInt());
      rating.setScore(json.get("score").asInt());
      rating.setDate(Utils.parseDate(json.get("date").asText()));
      ratingRepository.save(rating);
      return new ResponseEntity<Integer>(rating.getRatingId(),  HttpStatus.OK);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
    }
  }
  @PostMapping(path="/get/ratings")
  public @ResponseBody Iterable<Rating> getAllRatings(){
    return ratingRepository.findAll();
  }
  
  
  @PostMapping(path="/mostRating")
  public @ResponseBody Movie mostRatting(HttpEntity<String> req){
    try {
      String body = req.getBody();
      System.out.println(body);
      ObjectMapper mapper = new ObjectMapper();
      JsonFactory factory = mapper.getFactory();
      JsonParser parser = factory.createParser(body);
      JsonNode json = mapper.readTree(parser);
      
      Date startDate = Utils.parseDate(json.get("start").asText());
      Date endDate = Utils.parseDate(json.get("end").asText());
    
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
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return new Movie();
    }
  }
  
  @PostMapping(path="/leastRating")
  public @ResponseBody Movie leastRatting(HttpEntity<String> req){
    try {
      String body = req.getBody();
      System.out.println(body);
      ObjectMapper mapper = new ObjectMapper();
      JsonFactory factory = mapper.getFactory();
      JsonParser parser = factory.createParser(body);
      JsonNode json = mapper.readTree(parser);
      
      Date startDate = Utils.parseDate(json.get("start").asText());
      Date endDate = Utils.parseDate(json.get("end").asText());
    
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
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return new Movie();
    }
  }
   
  
}
