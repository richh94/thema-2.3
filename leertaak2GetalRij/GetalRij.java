import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GetalRij {
	private int[] getallen;
	private int[] getallenSorted;
	private int[] getallenSortedFreeSlot;
	
	public GetalRij( int aantal, int max ){
		// Belangrijke aanname: aantal < max, anders kunnen de getallen niet uniek zijn.
		getallen = new int[aantal];
		getallenSorted = new int[aantal];
		getallenSortedFreeSlot = new int[aantal + 1];
		
		vulArrayMetUniekeWaarden( aantal, max );
	}

	private void vulArrayMetUniekeWaarden(int aantal, int max) {
		// Vul een hulplijst met getallen 0, ..., max
		ArrayList<Integer> hulpLijst = new ArrayList<Integer>( max );
		for ( int i=0; i<max; i++){
			hulpLijst.add( i );
		}
		
		// Stop 'aantal' random waarden in getallen
		Random r = new Random();
		for ( int i=0; i < aantal; i++){
			// Het omzetten van Integer naar int gaat sinds Java 1.5 automatisch (unboxing).
			int getal = (Integer) (hulpLijst.remove( r.nextInt( hulpLijst.size())));
			getallen[i] = getal;
			
			System.out.println(i);
		}
		
		getallenSorted = getallen;
		System.arraycopy(getallenSorted, 0, getallenSortedFreeSlot, 0, getallenSorted.length);
		Arrays.sort(getallenSorted);
	}
	
	public boolean zitErinA( int zoekWaarde ){
		boolean exists = false;

		int i = 0;
	    while (i < getallen.length){
	    	if (getallen[i] == zoekWaarde) {
	    		exists = true;
	    	}
	    	
	    	i++;
	    }
	    
	    if (exists) {
	    	return true;
	    } else {
	    	return false;
	    }
	}

	public boolean zitErinB( int zoekWaarde ){
		int i = 0;
	    while (i < getallen.length){
	    	if (getallen[i] == zoekWaarde) {
	    		return true;
	    	}
	    	
	    	i++;
	    }
	    
	    return false;
	}
	
	/*
	public boolean zitErinC( int zoekWaarde ){
		getallenSortedFreeSlot[getallenSortedFreeSlot.length - 1] = zoekWaarde;
		
		int i = 0;
		while (getallenSortedFreeSlot[i] != zoekWaarde) {
			i++;
		} 
		
		return i < getallenSorted.length;
	}*/
	
	public boolean zitErinC( int zoekWaarde ){
	    for (int i = 0; i < getallen.length; i++){
	    	if (getallen[i] == zoekWaarde) {
	    		return true;
	    	}
	    }
	    
	    return false;
	}
	
	public boolean zitErinD( int zoekWaarde ){
		return linearArraySearch (zoekWaarde, 0, (getallenSorted.length - 1));
	}
	
	public boolean linearArraySearch ( int zoekWaarde, int firstPosition, int lastPosition) {
		int actualPosition = (int) Math.ceil(((lastPosition - firstPosition) / 2.0) + firstPosition); 
		
		if (zoekWaarde == getallenSorted[actualPosition]) {
			//System.out.println("equals" + getallenSorted[actualPosition]);
			return true;
		}
		
		if ((lastPosition - firstPosition) == 1) 	return false;
		
		if (zoekWaarde > getallenSorted[actualPosition]) {
			//System.out.println("more " + getallenSorted[actualPosition]);
			return linearArraySearch(zoekWaarde, actualPosition, lastPosition);
		} else {
			//System.out.println("less " + getallenSorted[actualPosition]);
			return linearArraySearch(zoekWaarde, firstPosition, actualPosition);
		}
	}
	
	public void sorteer(){
		Arrays.sort( getallen);
	}
	
	public void print(){
		for( int i=0; i<getallen.length; i++)
			System.out.println(getallen[i]);
	}
}
