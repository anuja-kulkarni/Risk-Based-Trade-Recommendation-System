package strategyPattern;

import java.util.ArrayList;

public class Context {
	
	private final Strategy strategy;
	public int num;
	public float price[] = new float[10];
	public String symbol[] = new String[10];
	public int type[] = new int[10];
	 
	public Context(Strategy strategy) {
	       this.strategy = strategy;
	}   
	
	public void arrange(
	    		ArrayList<Float> large, 
	    		ArrayList<Float> med, 
	    		ArrayList<Float> small, 
	    		ArrayList<Integer> typ1,
	    		ArrayList<Integer> typ2,
	    		ArrayList<Integer> typ3,
	    		ArrayList<String> symb1,
	    		ArrayList<String> symb2,
	    		ArrayList<String> symb3) {
		
	       price = strategy.combine(large,med,small);
	       type = strategy.DispType(typ1,typ2,typ3);
	       symbol = strategy.DispSymb(symb1,symb2,symb3);
	       
	    }    

}
