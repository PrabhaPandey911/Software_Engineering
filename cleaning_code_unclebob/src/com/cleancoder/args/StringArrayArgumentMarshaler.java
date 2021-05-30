package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.*;

/*If the argument type is list of strings, the variable strings stores them via set method, 
  in case of exception respective error message is printed out.
  getValue returns the value stored in doubleValue.
*/

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
  private List<String> strings = new ArrayList<String>();

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      strings.add(currentArgument.next());
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_STRING);
    }
  }

  public static String[] getValue(ArgumentMarshaler am) {
    if (isInstanceOfStringArrayArgumentMarshaler(am))
      return ((StringArrayArgumentMarshaler) am).strings.toArray(new String[0]);
    else
      return new String[0];
  }

  private static boolean isInstanceOfStringArrayArgumentMarshaler(ArgumentMarshaler am){
    if (am == null)
      return false;
    else if (am instanceof StringArrayArgumentMarshaler)
      return true;
    else
      return false;
  }
}