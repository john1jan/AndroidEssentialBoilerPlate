package com.johnfrancis.samplearch.utils;

/**
 * Created by john.francis on 05/06/16.
 */
public  class StringUtils {

  public static String getEmijoByUnicode(int unicode){
    return new String(Character.toChars(unicode));
  }

}
