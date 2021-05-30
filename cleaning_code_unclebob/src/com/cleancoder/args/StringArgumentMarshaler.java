package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.MISSING_STRING;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*If the argument type is string, the variable stringValue stores the current value of argument passed
  via set method, in case of exception respective error message is printed out.
  getValue returns the value stored in stringValue.
*/

public class StringArgumentMarshaler implements ArgumentMarshaler {
  private String stringValue = "";

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      stringValue = currentArgument.next();
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_STRING);
    }
  }

  public static String getValue(ArgumentMarshaler am) {
    if (isInstanceOfStringArgumentMarshaler(am))
      return ((StringArgumentMarshaler) am).stringValue;
    else
      return "";
  }

  private static boolean isInstanceOfStringArgumentMarshaler(ArgumentMarshaler am){
    if (am == null)
      return false;
    else if (am instanceof StringArgumentMarshaler)
      return true;
    else
      return false;
  }
}
