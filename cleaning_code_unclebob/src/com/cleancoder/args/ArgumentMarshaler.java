package com.cleancoder.args;

import java.util.Iterator;

/*Interface for providing set method to all argument types.*/
public interface ArgumentMarshaler {
  void set(Iterator<String> currentArgument) throws ArgsException;
}
