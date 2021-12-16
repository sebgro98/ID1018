package B;

import java.util.Random;

public class ValjPolylinje {
		
	public static final Random rand = new Random ();
	public static final int ANTAL_POLYLINJER = 10;
		
	public static void main (String[] args) {
	
		// skapa ett antal slumpm�ssiga polylinjer
		Polylinje[] polylinjer = new Polylinje[ANTAL_POLYLINJER];
		for (int i = 0; i < ANTAL_POLYLINJER; i++)
			polylinjer[i] = slumpPolylinje ();
		// visa polylinjerna
		for(int i = 0; i < polylinjer.length; i++)
	           System.out.println("Polyline " + (i+1) + ": " + polylinjer[i] + polylinjer[i].getFarg());
		System.out.println();
		
		// best�m den kortaste av de polylinjer som �r gula
	    int min = ANTAL_POLYLINJER * 10;
	    Polylinje minGul = new Polylinje();

	    for (int i = 0; i < ANTAL_POLYLINJER; i++) {
	    	if (polylinjer[i].getFarg().equals("yellow") && polylinjer[i].langd() < min) {
	    		minGul = polylinjer[i];
	        }
	    }   
	        // visa den valda polylinjen
	        System.out.println("Den kortaste gula polylinjen �r:  " + minGul);    
	}

		// slumpPunkt returnerar en punkt med ett slumpm�ssigt namn, som �r en stor bokstav i
		// det engelska alfabetet, och slumpm�ssiga koordinater.
		public static Punkt slumpPunkt () {
		
			String n = "" + (char) (65 + rand.nextInt (26));
			int x = rand.nextInt (11);
			int y = rand.nextInt (11);
		
		return new Punkt (n, x, y);
		}
		
		// slumpPolylinje returnerar en slumpm�ssig polylinje, vars f�rg �r antingen bl�, eller r�d
		// eller gul. Namn p� polylinjens h�rn �r stora bokst�ver i det engelska alfabetet. Tv� h�rn
		// kan inte ha samma namn.
		public static Polylinje slumpPolylinje () {
			
			// skapa en tom polylinje, och l�gg till h�rn till den
			Polylinje polylinje = new Polylinje ();
			int antalHorn = 2 + rand.nextInt (7);
			int antalValdaHorn = 0;
			boolean[] valdaNamn = new boolean[26];
			// ett och samma namn kan inte f�rekomma flera g�nger
			Punkt valdPunkt = null;
			char valtChar = 0;
			while (antalValdaHorn < antalHorn)
			{
				valdPunkt = slumpPunkt();
				valtChar = valdPunkt.getNamn().charAt(0);
				if(valdaNamn[(int)(valtChar - 65)] == false) { // om platsen i arrayn inte �r tagen s� g� vidare
					valdaNamn[(int)(valtChar - 65)] = true;	
					polylinje.laggTill(new Punkt (slumpPunkt()));
					antalValdaHorn++;
				}
			}
			polylinje.setFarg(randomFarg());
			System.out.println(polylinje);
		return polylinje;
		}		
		
		// s�tt f�rg
		public static String randomFarg() {
	        String farg;
	        int fargCode = rand.nextInt(3);
	        if (fargCode == 0) {
	            farg = "blue";
	        } else if (fargCode == 1) {
	            farg = "red";
	        } else {
	            farg = "yellow";
	        }
	        return farg;
	    }
}
