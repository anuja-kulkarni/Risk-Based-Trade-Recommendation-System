package strategyPattern;

import java.util.ArrayList;

public interface Strategy {
	public float[] combine(ArrayList <Float> large,ArrayList <Float> med,ArrayList <Float> small);
	public int[] DispType(ArrayList<Integer> typ1 ,ArrayList<Integer> typ2,ArrayList<Integer> typ3);
	public String[] DispSymb(ArrayList<String> symb1,ArrayList<String> symb2,ArrayList<String> symb3);

}
