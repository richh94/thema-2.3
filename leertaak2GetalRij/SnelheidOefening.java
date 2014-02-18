
public class SnelheidOefening {
	private static final int VALUE_A = 23432;
	private static final int VALUE_B = VALUE_A;
	private static final int VALUE_C = VALUE_A;
	private static final int VALUE_D = VALUE_A;
	
	static boolean inA;
	static boolean inB;
	static boolean inC;
	static boolean inD;
	
	/**
	 * @param args
	 */
	public static void main( String[] args){
		long startTime;
		long timeCost;
		
		startTime = tijd();
		System.out.println("filling getallen[]");
		GetalRij gr = new GetalRij( 500000, 2000000 );
		
		timeCost = tijd() - startTime;
		System.out.println("getallen[] filled " + timeCost + " ms");
		
		startTime = tijd();
		inA = gr.zitErinA(VALUE_A);
		timeCost = tijd() - startTime;
		
		System.out.println("Getal " + VALUE_A + " zit in A: " + inA + " Tijd: " + timeCost + " ms");
		
		startTime = tijd();
		inB = gr.zitErinB(VALUE_B);
		timeCost = tijd() - startTime;
		
		System.out.println("Getal " + VALUE_B + " zit in B: " + inB + " Tijd: " + timeCost + " ms");		
		
		startTime = tijd();
		inC = gr.zitErinC(VALUE_C);
		timeCost = tijd() - startTime;
		
		System.out.println("Getal " + VALUE_C + " zit in C: " + inC + " Tijd: " + timeCost + " ms");	
		
		startTime = tijd();
		inD = gr.zitErinC(VALUE_D);
		timeCost = tijd() - startTime;
		
		System.out.println("Getal " + VALUE_D + " zit in D: " + inD + " Tijd: " + timeCost + " ms");	
	}

	// Hulpmethode voor tijdsbepaling
	private static long tijd(){
		return System.currentTimeMillis();
	}
}
