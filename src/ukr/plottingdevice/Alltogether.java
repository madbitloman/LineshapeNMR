package ukr.plottingdevice;
//import java.io.PrintWriter;
/*
import java.io.BufferedWriter;

import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;
*/
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;

import flanagan.complex.Complex;
import ukr.stochasticlineshape.*;

public class Alltogether {
	
	static int a1=  (Integer) ValuesCatch.getXField("a1",null);
	static int b1=  (Integer) ValuesCatch.getXField("b1",null);
	static int m_1=  (Integer) ValuesCatch.getXField("m_1",null);
	static int m_01=  (Integer) ValuesCatch.getXField("m_01",null);
	static int m_11=  (Integer) ValuesCatch.getXField("m_11",null);
	static int m_0=  (Integer) ValuesCatch.getXField("m_0",null);
	static Complex kom=  (Complex) ValuesCatch.getXField("kom",null);
	//static int size=  (Integer) test.getXField("size",null);
	static int setw=  (Integer) ValuesCatch.getXField("setw",null);
	static int setwn=  (Integer) ValuesCatch.getXField("setwn",null);
	static Double stepw=  (Double) ValuesCatch.getXField("stepw",null);
	/////////////////////////////////////////////////////////////
	static int seth=  (Integer) ValuesCatch.getXField("seth",null);
	static int sethn=  (Integer) ValuesCatch.getXField("sethn",null);
	static Double steph=  (Double) ValuesCatch.getXField("steph",null);
	///////////////////////////////////////////////////////
	static Complex H = (Complex) ValuesCatch.getXField("B0",null);
	public static int sizew=(int) ((setw+Math.abs(setwn))/stepw);
	public static int sizeh=(int) ((seth+Math.abs(sethn))/steph); 
	
	static double[][] Datatotal =new double[sizew][sizeh];
	static double [] x= new double[sizew];
	static double [] y = new double[sizeh];
	
	static double f=sethn;
	static double s = setwn;
	
	public static double[] xreturn(){
		
		for(int i=0;i<sizew;i++){
			
			s=s+stepw;
			x[i]=s;
		}
	
		return x;
	}
	
	
public static double[] yreturn(){
		
		for(int i=0;i<sizeh;i++){
			
			f=f+steph;
			y[i]=f;
		}
	
		return y;
	}
	
public static   double[][] vse (){
	
		Complex[][] W = TransRate.transitionMatrix(a1, b1, kom); // call class to assign W
		
		String[] Y = Main.genind(m_0,m_1,a1); // generating encoding table
		Complex[][] prob = StochInd.stochasticf(a1, b1);	
		
		//Scanner stdIn = new Scanner(System.in);
		PrintWriter writer;

		
		//\\ Loop for runnig through all values of frequencies //\\
		
		
		//Scanner scan = new Scanner(new File("myNumbers.txt"));
		//PrintStream output = new PrintStream(new File("output.txt"));
		try
		{
			System.out.print("Enter a filename: ");
			//writer = new PrintWriter(stdIn.nextLine());
		for(int i=0;i<sizew;i++){
			
			Complex p1 = new Complex(s,0);
			s=s+stepw;
			//x[i]=s;
			
			for(int j=0;j<sizeh;j++){
				
				Complex H = new Complex(f,0);
				f=f+steph;
				//y[j]=f;
			
			
					Complex[][][][][][] hamatrix =MatrixCreation.matrixGeneration(H,p1,a1, b1, m_0, m_1, m_01, m_11, W,prob);
			
					Complex[][] trans =  (Complex[][]) Main.Transition(Y,hamatrix); 
			
			
					Complex[][] transcompl1 = Main.inversion(trans);
			
			
					Object[][][][][][] hamatrixnew =  Main.matrixturn( transcompl1, Y);
					Complex [] Norm = Main.norma(a1);
					Complex s1 =Main.summation(hamatrixnew, Norm);
			
					Datatotal[i][j]=s1.getReal()*(-1);
					
					//Datatotal[i][j]=scan.nextInt();
					//output.println(Datatotal[i][j]);
					//writer.print(Datatotal[i][j] + "\n");
			
		} //writer.println();
			//scan.nextLine();

	}
		
		}	catch (FileNotFoundException e){
			System.out.println("Error: " + e.getMessage());			
		} return Datatotal;
		}
public static void write(double[][] data, Writer baseWriter) throws java.io.IOException {

       int rows = data.length;

       if (rows==0) { return; }

       int cols = data[0].length;

       BufferedWriter writer = new BufferedWriter(baseWriter);

       writer.write("" + rows); writer.newLine();

       writer.write("" + cols); writer.newLine();

       for(int row=0; row<rows; row++) {

           for(int col=0; col<cols; col++) {

               writer.write("" + data[row][col]); writer.newLine();

           }

       }

       writer.flush();

   }
	
public void write(double[][] data, String fileName) throws java.io.IOException {

        Writer writer = new FileWriter(fileName);

        write(data, writer);

        writer.close();

    

}
	
}


