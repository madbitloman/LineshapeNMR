package ukr.stochasticlineshape;
//import java.io.PrintWriter;

import java.io.FileNotFoundException;

import flanagan.complex.Complex;
	public class FieldSwept {
	


		static int a1=  (Integer) ValuesCatch.getXField("a1",null);
		static int b1=  (Integer) ValuesCatch.getXField("b1",null);
		static int m_1=  (Integer) ValuesCatch.getXField("m_1",null);
		static int m_01=  (Integer) ValuesCatch.getXField("m_01",null);
		static int m_11=  (Integer) ValuesCatch.getXField("m_11",null);
		static int m_0=  (Integer) ValuesCatch.getXField("m_0",null);
		static Complex kom=  (Complex) ValuesCatch.getXField("kom",null);
		static Double seth=   (Double) ValuesCatch.getXField("seth",null);
		static Double sethn=  (Double) ValuesCatch.getXField("sethn",null);
		static Double step1=  (Double) ValuesCatch.getXField("steph",null);
		static Double set1=  (Double) ValuesCatch.getXField("H",null); //field value for determining size of array
		static Double pp = (Double) ValuesCatch.getXField("pp",null);
	
		//public static int size=(int) (seth/step1);
		public static int size1=(int) ((seth+Math.abs(sethn))/step1); 
		
		
		public static double[][] finalniy () throws FileNotFoundException{
		
		Complex p = new Complex(pp,0);
		
		Double[][] W = TransRate.transitionMatrix(a1, b1, kom); // call class to assign W
		
		String[] Y = Main.genind(m_0,m_1,a1); // generating encoding table
		Complex[][] prob = StochInd.stochasticf(a1, b1);	
		
		
		double[][] all =new double[size1][2];
		
		
		//\\ Loop for runnig through all values of frequencies //\\
		double f=sethn;
		
		for(double zz=0;zz<size1;zz++){
			double H = f;
			f=f+step1;
			
			
			
			Complex[][][][][][] hamatrix =MatrixCreation.matrixGeneration(H,p,a1, b1, m_0, m_1, m_01, m_11, W,prob);
			
			Complex[][] trans =  (Complex[][]) Main.Transition(Y,hamatrix); 
			
			
			Complex[][] transcompl1 = Main.inversion(trans);
			
			
			Object[][][][][][] hamatrixnew =  Main.matrixturn( transcompl1, Y);
			Complex s1 =Main.summation(hamatrixnew);
			
			all[(int) zz][1]=f;
			all[(int) zz][0]=s1.getReal()*(-1);
			
			
			
			
		}
		return all;
		
		
		}
		
	}
		





