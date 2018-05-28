package com.rating.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainControler {
 
  @Autowired
  private filmRepository filmRepository;
  
  @Autowired
  private userRepository userRepository;
  
  @Autowired
  private ratingRepository ratingRepository;
  
  
  
  
}
