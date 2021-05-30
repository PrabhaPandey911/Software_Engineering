package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


import junit.framework.TestCase;

/*Unit test file*/

public class ArgsExceptionTest extends TestCase {

  public static void main(String[] args) {
      Result result = JUnitCore.runClasses(ArgsExceptionTest.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
  }
  
  @Test
  public void testUnexpectedMessage() throws Exception {
    ErrorCodeAndParameter err = new ErrorCodeAndParameter(UNEXPECTED_ARGUMENT,null);
    ArgsException e = new ArgsException(err, 'x');
    assertEquals("Argument -x unexpected.", e.errorMessage());
  }

  @Test
  public void testMissingStringMessage() throws Exception {
    ErrorCodeAndParameter err = new ErrorCodeAndParameter(MISSING_STRING, null);
    ArgsException e = new ArgsException(err, 'x');
    assertEquals("Could not find string parameter for -x.", e.errorMessage());
  }

  @Test
  public void testInvalidIntegerMessage() throws Exception {
    ErrorCodeAndParameter err = new ErrorCodeAndParameter(INVALID_INTEGER,"Forty two");
    ArgsException e = new ArgsException(err, 'x');
    assertEquals("Argument -x expects an integer but was 'Forty two'.", e.errorMessage());
  }

  @Test
  public void testMissingIntegerMessage() throws Exception {
    ErrorCodeAndParameter err = new ErrorCodeAndParameter(MISSING_INTEGER, null);
    ArgsException e = new ArgsException(err, 'x');
    assertEquals("Could not find integer parameter for -x.", e.errorMessage());
  }

  @Test
  public void testInvalidDoubleMessage() throws Exception {
    ErrorCodeAndParameter err = new ErrorCodeAndParameter(INVALID_DOUBLE,  "Forty two");
    ArgsException e = new ArgsException(err, 'x');
    assertEquals("Argument -x expects a double but was 'Forty two'.", e.errorMessage());
  }

  @Test
  public void testMissingDoubleMessage() throws Exception {
    ErrorCodeAndParameter err = new ErrorCodeAndParameter(MISSING_DOUBLE, null);
    ArgsException e = new ArgsException(err, 'x');
    assertEquals("Could not find double parameter for -x.", e.errorMessage());
  }

  @Test
  public void testMissingMapMessage() throws Exception {
    ErrorCodeAndParameter err = new ErrorCodeAndParameter(MISSING_MAP, null);
    ArgsException e = new ArgsException(err, 'x');
    assertEquals("Could not find map string for -x.", e.errorMessage());
  }

  @Test
  public void testMalformedMapMessage() throws Exception {
    ErrorCodeAndParameter err = new ErrorCodeAndParameter(MALFORMED_MAP, null);
    ArgsException e = new ArgsException(err, 'x');
    assertEquals("Map string for -x is not of form k1:v1,k2:v2...", e.errorMessage());
  }

  @Test
  public void testInvalidArgumentName() throws Exception {
    ErrorCodeAndParameter err = new ErrorCodeAndParameter(INVALID_ARGUMENT_NAME, null);
    ArgsException e = new ArgsException(err, '#');
    assertEquals("'#' is not a valid argument name.", e.errorMessage());
  }

  @Test
  public void testInvalidFormat() throws Exception {
    ErrorCodeAndParameter err = new ErrorCodeAndParameter(INVALID_ARGUMENT_FORMAT, "$");
    ArgsException e = new ArgsException(err, 'x');
    assertEquals("'$' is not a valid argument format.", e.errorMessage());
  }
}

