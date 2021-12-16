package B;

import java.util.Arrays;

public class Polylinje {
	
	    private Punkt[]    horn; //Skapas en array av typen Punkt och horn �r en referens inte ett objekt. 
	    private String     farg = "svart"; 
	    private int        bredd = 1; 
	 
	    public Polylinje () 
	    { 
	        this.horn = new Punkt[0];  
	        //Skapar en array med objekter av typen Punkt, 
	        //och l�t referensen med index 0 referera till objekt som finns p� index 0.
	    } 
	 
	    public Polylinje (Punkt[] horn) 
	    { 
	        this.horn = new Punkt[horn.length]; 
	        for (int i = 0; i < horn.length; i++) 
	            this.horn[i] = new Punkt (horn[i]); 
	    } 
	 
	    public String toString () {
	        StringBuilder    sb = new StringBuilder(); 
	        for (int i= 0; i < this.horn.length; i++)
	        {
	            sb.append ( this.horn[i] ); 
	        }
	        sb.append("(" + ", " + this.farg + ", " + this.bredd + ")"); 
	        return sb.toString(); 
	    } 
	 
	    public Punkt[] getHorn () {
	        return Arrays.copyOf(this.horn, this.horn.length);
	    } 
	 
	    public String getFarg () {
	        return this.farg;
	    } 
	 
	    public int getBredd () {
	        return this.bredd;  
	    }

	    public void setFarg (String farg) {
	        this.farg = farg; 
	    } 
	 
	    public void setBredd (int bredd) {
	        this.bredd = bredd;
	    } 
	 
	    public double langd () {
	    	double langd = 0;

	        for (int i = 1; i < this.horn.length; i++) {
	            langd += this.horn[i-1].avstand(this.horn[i]);
	        }
	        return langd;
	    }
	    //L�gger till ett h�rn i slutet av vektorn 
	    // genom att skapa en vecktor som har ett mer plats �n den aktuella vecktr
	    // och lgger till punkter samt den nya punkt i sista position. 
	    public void laggTill (Punkt horn) 
	    { 
	        Punkt[]    h = new Punkt[this.horn.length + 1]; 
	        int    i = 0; 
	        for (i = 0; i < this.horn.length; i++) 
	            h[i] = this.horn[i]; 
	        h[i] = new Punkt (horn); 
	 
	        this.horn = h; 
	    } 

	    // L�gger till ett h�rn framf�r den h�rn som har samma namn.
	    public void laggTillFramfor (Punkt horn, String hornNamn) {
	        Punkt newH = new Punkt(horn); // skapar en ny punkt som refererar till den gamla
	    	Punkt[]    h = new Punkt[this.horn.length + 1]; // l�ngden+1 f�r den aktuella h�rn.
	        int pos = 0; 
	        for (int i = 0; i < this.horn.length; i++) {
	            h[pos] = this.horn[i];
	                if (hornNamn.equals(this.horn[i].getNamn())){ //Kollar om de har samma namn
	                     h[pos] = newH; //L�gger till den nya h�rnen
	                     h[++pos] = this.horn[i]; // l�gger den aktuella h�rn p� n�sta plats
	                }  
	            pos++; 
	        }
	        this.horn = h; // 
	    }

	    // tar bort horn med ett visst h�rnNamn. 
	    public void taBort (String hornNamn) {
	        Punkt[]    h = new Punkt[this.horn.length - 1]; // l�ngden -1 f�r den aktuella h�rn.
	        int    pos = 0;
	        for (int i = 0; i < this.horn.length; i++) {
	            if (this.horn[i].getNamn().equals(hornNamn)) {
	                i++;   
	            }
	        h[pos] = this.horn[i];
	        pos++; 
	        }
	        this.horn = h; 
	    } 
	    
	   public class PolylinjeIterator {

	    	private int aktuell = -1;
	    	
	    	public PolylinjeIterator () {
	    	
	    		if (Polylinje.this.horn.length > 0)
	    			aktuell = 0;
	    	}
	    	
	    	public boolean finnsHorn () {
	    		
	    		return aktuell != -1;
	    	}
	    	
	    	public Punkt horn () throws java.util.NoSuchElementException {
	    	
	    		if (!this.finnsHorn ()) throw new java.util.NoSuchElementException ("slut av iterationen");
	    	
	    		Punkt horn = Polylinje.this.horn[aktuell];
	    		return horn;
	    	}
	    	
	    	public void gaFram () {
	    		if (aktuell >= 0 &&
	    				aktuell < Polylinje.this.horn.length - 1)
	    			aktuell++;
	    		else
	    			aktuell = -1;
	    	}
	    }
	}
	
