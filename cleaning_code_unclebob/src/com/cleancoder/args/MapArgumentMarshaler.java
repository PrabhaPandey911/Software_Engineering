package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/*If the argument is passed in pairs, the variable map stores the pair (eg: "p1:a1,p2:a2" is stored as <p1,a1>,<p2,a2>
  via set method, in case of exception respective error message is printed out.
  getValue returns the value stored in map.
*/

public class MapArgumentMarshaler implements ArgumentMarshaler {
  private Map<String, String> map = new HashMap<>();

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      String[] mapEntries = currentArgument.next().split(",");
      for (String entry : mapEntries) {
        String[] entryComponents = entry.split(":");
        if (entryComponents.length != 2)
          throw new ArgsException(MALFORMED_MAP);
        map.put(entryComponents[0], entryComponents[1]);
      }
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_MAP);
    }
  }

  public static Map<String, String> getValue(ArgumentMarshaler am) {
    if (isInstanceOfMapArgumentMarshaler(am))
      return ((MapArgumentMarshaler) am).map;
    else
      return new HashMap<>();
  }

  private static boolean isInstanceOfMapArgumentMarshaler(ArgumentMarshaler am){
    if (am == null)
      return false;
    else if (am instanceof MapArgumentMarshaler)
      return true;
    else
      return false;
  }  
}
