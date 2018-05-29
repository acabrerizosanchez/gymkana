package com.rating.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

  public static Date parseDate(String date) {
    try {
      
      final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");     
      return dateFormat.parse(date);
      
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return new Date();
    }
  }
    
}
