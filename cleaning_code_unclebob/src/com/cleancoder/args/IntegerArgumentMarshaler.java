package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.*;

/*If the argument type is integer, the variable intValue stores the current value of argument passed
  via set method, in case of exception respective error message is printed out.
  getValue returns the value stored in intValue.
*/

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
  private int intValue = 0;

  public void set(Iterator<String> currentArgument) throws ArgsException {
    String parameter = null;
    try {
      parameter = currentArgument.next();
      intValue = Integer.parseInt(parameter);
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_INTEGER);
    } catch (NumberFormatException e) {
      ErrorCodeAndParameter err = new ErrorCodeAndParameter(INVALID_INTEGER, parameter);
      throw new ArgsException(err);
    }
  }

  public static int getValue(ArgumentMarshaler am) {
    if (isInstanceOfIntegerArgumentMarshaler(am))
      return ((IntegerArgumentMarshaler) am).intValue;
    else
      return 0;
  }

  private static boolean isInstanceOfIntegerArgumentMarshaler(ArgumentMarshaler am){
    if (am == null)
      return false;
    else if (am instanceof IntegerArgumentMarshaler)
      return true;
    else
      return false;
  } 
}
