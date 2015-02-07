package ukr.stochasticlineshape;
import java.io.FileNotFoundException;

import flanagan.complex.Complex;

/* This class sets the values for lineshape of frequency swept case */


public class FrequencySwept {
	static int a1=  (Integer) ValuesCatch.getXField("a1",null);
	static int b1=  (Integer) ValuesCatch.getXField("b1",null);
	static int m_1=  (Integer) ValuesCatch.getXField("m_1",null);
	static int m_01=  (Integer) ValuesCatch.getXField("m_01",null);
	static int m_11=  (Integer) ValuesCatch.getXField("m_11",null);
	static int m_0=  (Integer) ValuesCatch.getXField("m_0",null);
	static Complex kom=  (Complex) ValuesCatch.getXField("kom",null);
	static int setw=  (Integer) ValuesCatch.getXField("setw",null);
	static int setwn=  (Integer) ValuesCatch.getXField("setwn",null);
	static Double stepw=  (Double) ValuesCatch.getXField("stepw",null);
	static double H = (Double) ValuesCatch.getXField("H0",null);
	public static int size=(int) ((setw+Math.abs(setwn))/stepw);
	public static double[][] finalniy () throws FileNotFoundException{
	 
	
	
	Double[][] W = TransRate.transitionMatrix(a1, b1, kom); // call class to assign W
	
	String[] Y = Main.genind(m_0,m_1,a1); // generating encoding table
	Complex[][] prob = StochInd.stochasticf(a1, b1);	
	
	
	double[][] all =new double[size][2];
	
	
	double s=setwn;
	//\\ Loop for runnig through all values of frequencies //\\
	
	
	for(double zz=0;zz<size;zz++){
		
		
		Complex p = new Complex(s,0);

		s=s+stepw;
		
		
		Complex[][][][][][] hamatrix =MatrixCreation.matrixGeneration(H,p,a1, b1, m_0, m_1, m_01, m_11, W,prob);
		Complex[][] trans =  (Complex[][]) Main.Transition(Y,hamatrix); 
		Complex[][] transcompl1 = Main.inversion(trans);
		Object[][][][][][] hamatrixnew =  Main.matrixturn( transcompl1, Y);
		Complex s1 =Main.summation(hamatrixnew);
		
		all[(int) zz][1]=s;
		all[(int) zz][0]=s1.getReal();
		
		
	}
	return all;
	
	
	}
	
}
	


