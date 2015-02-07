package ukr.stochasticlineshape;
import flanagan.complex.Complex;


public class StochInd {
	
/* This Class particularly assigns values of the stochastic function f(n) as diagonal of +1 and -1*/
	


	public static Complex[][] stochasticf(int a1, int b1){
		Complex[][] stoch= new Complex[a1][b1];
		
		int k = 0;
		
		for(int i=0;i<a1;i++){
			for(int j=0;j<b1;j++){
				k=(int) Math.pow(-1,2+i);
				if(i==j)
				stoch[i][j]= new Complex(1,0);		
				//stoch[i][j]= new Complex(k,0);
				
			}
		}
		
		return stoch;
	}
	
}
