package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.*;

/* ParseArgumentCharacters parses each -x (character) passed as argument.
   checks if it already exists, if not adds it into marshalers of Args.java class.
   Throws errors whereever necessary.
*/

public class ParseArgumentCharacters {

  public  ParseArgumentCharacters(String argChars) throws ArgsException {
    for (int i = 0; i < argChars.length(); i++)
      parseArgumentCharacter(argChars.charAt(i));
  }

  private void parseArgumentCharacter(char argChar) throws ArgsException {
    Map<Character, ArgumentMarshaler> marshalers = Args.getMarshaler();
    ArgumentMarshaler m = marshalers.get(argChar);
    checkIfArgumentMarshalerExists(m, argChar);
  }

  private void checkIfArgumentMarshalerExists(ArgumentMarshaler m, char argChar) throws ArgsException {
    if (m == null) {
      ErrorCodeAndParameter err = new ErrorCodeAndParameter(UNEXPECTED_ARGUMENT, null);
      throw new ArgsException(err, argChar); 
    }
    else 
      addArgsInListAndUpdateMarshaler(m, argChar);
  }

  private void addArgsInListAndUpdateMarshaler(ArgumentMarshaler m, char argChar) throws ArgsException {
    Set<Character> argsFound = Args.getArgsFound();
    argsFound.add(argChar);
    try {
      m.set(Args.getCurrentArgument());
      Args.setArgsFound(argsFound);
    } 
    catch (ArgsException e) {
      e.setErrorArgumentId(argChar);
      throw e;
    }
  }

}