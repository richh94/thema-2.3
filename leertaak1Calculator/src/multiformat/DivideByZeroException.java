
package multiformat;

public class DivideByZeroException extends Exception {
 	private static final long serialVersionUID = 1L;

 	public DivideByZeroException(String msg) {
 		super(msg);
 		
 		System.out.println(msg);
 	}
}