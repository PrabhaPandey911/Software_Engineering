package com.cleancoder.args;

import java.util.Iterator;

/*If the argument type is boolean, the variable booleanValue stores the current value of argument (true/false)
  via set method, in case of exception respective error message is printed out.
  getValue returns the value stored in booleanValue.
*/

public class BooleanArgumentMarshaler implements ArgumentMarshaler {
  private boolean booleanValue = false;

  public void set(Iterator<String> currentArgument) throws ArgsException {
    booleanValue = true;
  }

  public static boolean getValue(ArgumentMarshaler am) {
    if (isInstanceOfBooleanArgumentMarshaler(am))
      return ((BooleanArgumentMarshaler) am).booleanValue;
    else
      return false;
  }

  private static boolean isInstanceOfBooleanArgumentMarshaler(ArgumentMarshaler am){
    if (am == null)
      return false;
    else if (am instanceof BooleanArgumentMarshaler)
      return true;
    else
      return false;
  }
}
