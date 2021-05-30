package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.*;

/*If the argument type is double, the variable doubleValue stores the current value of argument passed
  via set method, in case of exception respective error message is printed out.
  getValue returns the value stored in doubleValue.
*/

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
  private double doubleValue = 0;

  public void set(Iterator<String> currentArgument) throws ArgsException {
    String parameter = null;
    try {
      parameter = currentArgument.next();
      doubleValue = Double.parseDouble(parameter);
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_DOUBLE);
    } catch (NumberFormatException e) {
      ErrorCodeAndParameter err = new ErrorCodeAndParameter(INVALID_DOUBLE, parameter);
      throw new ArgsException(err);
    }
  }

  public static double getValue(ArgumentMarshaler am) {
    if (isInstanceOfDoubleArgumentMarshaler(am))
      return ((DoubleArgumentMarshaler) am).doubleValue;
    else
      return 0.0;
  }

  private static boolean isInstanceOfDoubleArgumentMarshaler(ArgumentMarshaler am){
    if (am == null)
      return false;
    else if(am instanceof DoubleArgumentMarshaler)
      return true;
    else
      return false;
  }
}
