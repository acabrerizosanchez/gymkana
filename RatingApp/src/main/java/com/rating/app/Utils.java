package com.rating.app;

import java.security.KeyStore.Entry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import com.fasterxml.jackson.databind.JsonNode;

public class Utils {

  public static Date parseDate(String date) {
    try {
      System.out.println(date);
      final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");     
      return dateFormat.parse(date);
      
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return new Date();
    }
  }
  
  public static Date parseDateMovie (String date) {
    try {
      System.out.println(date);
      final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");     
      return dateFormat.parse(date);
      
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return new Date();
    }
    
  }
  
  public static ArrayList<String> parseGenresToList(JsonNode list) {
    ArrayList<String> ret = new ArrayList<>();
    if ( !list.isContainerNode()) {
      return ret;
    }else {
      
      while(list.fields().hasNext()) {
        if (ret.size()<3) {
          String g = list.fields().next().getKey();
          ret.add(g);
        }else {
          break;
        }
      }
    }
    System.out.println(ret);
    return ret;
  }
    
}
