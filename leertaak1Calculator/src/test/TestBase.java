package test;

import junit.framework.TestCase;
import multiformat.Base;
import multiformat.NumberBaseException;
import multiformat.OctodadBase;

import org.junit.Before;
import org.junit.Test;

public class TestBase extends TestCase {
	Base ob;
	
	public TestBase(String arg0) {
		super(arg0);
	}
	
	@Before
	public void setUp(){
		ob = new OctodadBase();
	}
	
	@Test(expected = NumberBaseException.class)
	public void testOctodadBase() throws NumberBaseException {
		ob.parse("9");
	}
}
