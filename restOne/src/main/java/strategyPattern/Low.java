package strategyPattern;

import java.util.ArrayList;

public class Low implements Strategy{
	float[] recommendation=new float[10];
	int[] type=new int[10];
	String[] symbol=new String[10];
	@Override
	public float[] combine(ArrayList <Float> large,ArrayList <Float> med,ArrayList <Float> small)
	{
	for(int i=0;i<10;i++)
	{
		for(int j=0;j<=5;j++)
		{
			recommendation[i]=large.get(j);
			i=i+1;
		}
		
		for(int k=0;k<=2;k++)
		{
			recommendation[i]=med.get(k);
			i=i+1;
		}
		
		for(int l=0;l<1;l++)
		{
			recommendation[i]=small.get(l);
			i=i+1;
		}
		
		
	}
	return recommendation;

}
@Override
	
public int[] DispType(ArrayList<Integer> typ1,ArrayList<Integer>typ2,ArrayList<Integer>typ3)
{
	{
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<=5;j++)
			{
				type[i]=typ1.get(j);
				i=i+1;
			}
			
			for(int k=0;k<=2;k++)
			{
				type[i]=typ2.get(k);
				i=i+1;
			}
			
			for(int l=0;l<1;l++)
			{
				type[i]=typ3.get(l);
				i=i+1;
			}
			
			
		}
		return type;
	}
	}
@Override
public String[] DispSymb(ArrayList<String> symb1,ArrayList<String>symb2,ArrayList<String>symb3)
{	for(int i=0;i<10;i++)
	{		
		for(int j=0;j<=5;j++)
		{
			symbol[i]=symb1.get(j);
			i=i+1;
		}
		
		for(int k=0;k<=2;k++)
		{
			symbol[i]=symb2.get(k);
			i=i+1;
		}
		
		for(int l=0;l<1;l++)
		{
			symbol[i]=symb3.get(l);
			i=i+1;
		}
		
		
	}
	return symbol;

}
}
