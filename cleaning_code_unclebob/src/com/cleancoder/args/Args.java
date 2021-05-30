package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.*;

/*Args class takes 2 elements into picture, first the schema and second arguments.
  Schema defines how the arguments are to be parsed.
  marshalers stores the type of argument marshaler a character refers to.
    eg: for "l,p#,d*", l corresponds to boolean, p to integer and d to string.
  argsFound hash map keeps track of all the characters encountered so far.

  For parsing the schema, we have ParseSchema class.
  For parsing argument string, parseArgumentStrings is called which calls 
  ParseArgumentCharacters for each -Char encountered in arguments passed.

  getter and setter methods are used by ParseSchema and ParseArgumentCharacters classes.
*/

public class Args {

  private static Map<Character, ArgumentMarshaler> marshalers;
  private static Set<Character> argsFound;
  private static ListIterator<String> currentArgument;


  public Args(String schema, String[] args) throws ArgsException {
    marshalers = new HashMap<Character, ArgumentMarshaler>();
    argsFound = new HashSet<Character>();
    new ParseSchema(schema);
    parseArgumentStrings(Arrays.asList(args));
  }
  
  private void parseArgumentStrings(List<String> argsList) throws ArgsException {
    for (currentArgument = argsList.listIterator(); currentArgument.hasNext();) {
      String argString = currentArgument.next();
      if (argString.startsWith("-"))
        new ParseArgumentCharacters(argString.substring(1));
      else {
        currentArgument.previous();
        break;
      }
    }
  }

  public boolean has(char arg) {
    return argsFound.contains(arg);
  }

  public int nextArgument() {
    return currentArgument.nextIndex();
  }

  public boolean getBoolean(char arg) {
    return BooleanArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public String getString(char arg) {
    return StringArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public int getInt(char arg) {
    return IntegerArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public double getDouble(char arg) {
    return DoubleArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public String[] getStringArray(char arg) {
    return StringArrayArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public Map<String, String> getMap(char arg) {
    return MapArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public static Map<Character, ArgumentMarshaler> getMarshaler() {
    return marshalers;
  }
  public static Set<Character> getArgsFound() {
    return argsFound;
  }
  public static ListIterator<String> getCurrentArgument() {
    return currentArgument;
  }

  public static void setMarshaler(Map<Character, ArgumentMarshaler> marshaler){
    marshalers = marshaler;
  }

  public static void setArgsFound(Set<Character> argFound){
    argsFound = argsFound;
  }

}