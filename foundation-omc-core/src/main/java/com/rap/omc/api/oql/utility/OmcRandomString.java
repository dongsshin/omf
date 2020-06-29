package com.rap.omc.api.oql.utility;

import java.util.Random;

public class OmcRandomString {
	  private static final char[] symbols;
	  static 
	  {
	      StringBuilder tmp = new StringBuilder();
	      tmp.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
	      symbols = tmp.toString().toCharArray();
	  }   
	  private final Random random = new Random();
	  private final char[] buf;
	  public OmcRandomString(int length) {
	    if (length < 1)
	      throw new IllegalArgumentException("Cannot be less than 1: " + length);
	    buf = new char[length];
	  }
	  public String nextString() 
	  {
	      for (int idx = 0; idx < buf.length; ++idx)  buf[idx] = symbols[random.nextInt(symbols.length)];
	      return new String(buf);
	  }
}