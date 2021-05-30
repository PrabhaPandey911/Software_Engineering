package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.*;

/* ParseSchema class parses the schema sent as argument, and then define the type of each argument as one
   of the following classes based on their definition:
      BooleanArgumentMarshaler, StringArgumentMarshaler, StringArrayArgumentMarshaler
      IntegerArgumentMarshaler, DoubleArgumentMarshaler, MapArgumentMarshaler.
*/
public class ParseSchema {

  public ParseSchema(String schema) throws ArgsException {
    for (String element : schema.split(","))
      if (element.length() > 0)
        parseSchemaElement(element.trim());
  }

  private void parseSchemaElement(String element) throws ArgsException {
    char elementId = element.charAt(0);
    String elementTail = element.substring(1);
    validateSchemaElementId(elementId);
    identifyArgumentType(elementTail,elementId);
  }

  private void validateSchemaElementId(char elementId) throws ArgsException {
    if (!Character.isLetter(elementId)){
      ErrorCodeAndParameter err = new ErrorCodeAndParameter(INVALID_ARGUMENT_NAME, null);
      throw new ArgsException(err, elementId);
    }
  }

  private void identifyArgumentType(String elementTail, char elementId) throws ArgsException {
  	Map<Character, ArgumentMarshaler> marshalers = Args.getMarshaler();

    if (elementTail.length() == 0)
      marshalers.put(elementId, new BooleanArgumentMarshaler());
    else if (elementTail.equals("*"))
      marshalers.put(elementId, new StringArgumentMarshaler());
    else if (elementTail.equals("#"))
      marshalers.put(elementId, new IntegerArgumentMarshaler());
    else if (elementTail.equals("##"))
      marshalers.put(elementId, new DoubleArgumentMarshaler());
    else if (elementTail.equals("[*]"))
      marshalers.put(elementId, new StringArrayArgumentMarshaler());
    else if (elementTail.equals("&"))
      marshalers.put(elementId, new MapArgumentMarshaler());
    else{
      ErrorCodeAndParameter err = new ErrorCodeAndParameter(INVALID_ARGUMENT_FORMAT, elementTail) ;
      throw new ArgsException(err, elementId);
    }
    Args.setMarshaler(marshalers);
  }
}