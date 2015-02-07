package ukr.stochasticlineshape;

import java.io.*;	

import javax.swing.JFrame;

import org.jfree.ui.RefineryUtilities;







import org.math.plot.Plot3DPanel;

import flanagan.complex.Complex;
import flanagan.complex.ComplexMatrix;
import ukr.plottingdevice.*;
import us.plotof3d.ColorWaveDemo;
import us.plotof3d.DemoLauncher;
import us.plotof3d.WireSurfaceDemo;

public class Main {

	
	//static int abvalue [] =Dialogbox.abvalueset();
	//static Double[] otherv= Dialogbox.othervalues(); 
	//static Double[] fswept= Dialogbox.fieldval(); 
	  
	public final static int a1=6;//a stochastic indices 
	public final static int b1=a1;//b stochastic indices
	public static Double trate =(double) 0.0500; // transition rate that defines values for W matrix
	
	public static int setw=12; //frequency set for fieldsweptz
	public static int setwn=0; //frequency set for fieldsweptz negative domain
	
	public static Double seth=(double) 4; //frequency set for frequenc3dy swept
	public static Double sethn=(double) 0; //frequency set for frequency swept
	//public final static Double hx=(double) 0; //applied field in x direction		
	//public final static Double hy=(double) 0; //applied field in y direction
	
	public final static Double hz0=(double) 30; //applied field in z direction
	public final static Double H0=(double) 4; // fixed field  
	public static Double stepw=(double) 0.01; //optimal step size for frequency plot 0.1 and 0.01 for field
	public static Double steph=0.01;
	public static int size;
	public static double pp=5; // set of frequency
	/* g-tensor */
	
	public static double gxx=2.04;
	public static double gyy=2.02;
	public static double gzz=2.0;
	
	public static Double[] alpha =new Double[]{0.0,Math.PI*0.5,Math.PI*0.5,Math.PI*0.5,Math.PI*0.5,Math.PI};
	public static Double[] beta =new Double[]{0.0,Math.PI*0.5,Math.PI*0.5,Math.PI*0.5,Math.PI*0.5,Math.PI};
	public static Double[] gama =new Double[]{0.0,0.0,Math.PI*0.5,1.5*Math.PI,Math.PI,0.0};
	
	public static double g00=-(gxx+gyy+gzz)/Math.sqrt(3);
	public static double g22=(gzz-0.5*(gyy+gxx))*(Math.sqrt(2)/Math.sqrt(3));
	public static double g20=0.5*(gxx-gyy);
	
	
	public static double A00=-(1/Math.sqrt(3))*H0;
	public static double A22p=0.0;
	public static double A22m=A22p;
	public static double A21p=-1/2*hz0;
	public static double A21m=-A21p;
	public static double A20=(Math.sqrt(2)/Math.sqrt(3))*hz0;
	
	
	//public static Complex h= new Complex(hx,-hy);
	//public static Complex hp= new Complex(hx,hy);

	
	public static Double[] hzgener (){
	Double[] hz = new Double[a1];
	Complex[] hm = new Complex[a1];
	Complex[] hp = new Complex[a1];
		for(int i=0; i<a1;i++){
		
			hz[i]=A20*(0.5*g20*(3*Math.pow(Math.cos(beta[i]),2)-1)+g22*(Math.sqrt(3)/Math.sqrt(2))*Math.cos(2*gama[i])*Math.pow(Math.sin(beta[i]),2));
			
			
			//hm[i]=new Complex(0, -Math.exp(-alpha[i])).times(Math.sin(beta[i])*Math.cos(beta[i])*Math.cos(2*gama[i])).minus(new Complex(0,Math.sin(beta[i])*Math.sin(2*gama[i])));;
	}
	
	return hz;
	}
	
	
	 /* ///////////////////////
	/* Editable Variables from actual box*/
	///////////////////////
	/*
	public final static int a1=abvalue[0];//a stochastic indices 
	public final static int b1=abvalue[1];//b stochastic indices
	public static Double trate = otherv[4]; // transition rate that defines values for W matrix
	
	public static int setw=abvalue[2]; //frequency set for fieldsweptz
	public static int setwn=abvalue[3]; //frequency set for fieldsweptz negative domain
	
	public static Double seth=fswept[0]; //frequency set for frequenc3dy swept
	public static Double sethn=fswept[1]; //frequency set for frequency swept
	public final static Double h_x=otherv[1]; //applied field in x direction		
	public final static Double h_y=otherv[2]; //applied field in y direction
	public final static Double h_z=otherv[3]; //applied field in z direction
	public final static Double H=otherv[0]; // fixed field 
	public static Double stepw=(double) 0.1; //optimal step size for frequency plot 0.1 and 0.01 for field
	public static Double steph=0.01;
	public static int size;
	public static double pp=fswept[2]; // set of frequency
	*/
	//////////////////////////
	/* Uneditable Variables*/
	/////////////////////////
	public final static int m_0=2;//m_o
	public final static int m_1=2;//m_1
	public final static int m_01=2;//m_0'
	public final static int m_11=2;//m_1'
	public final static Double I1 = 0.5; // spin I_0=I_1
	//public final static Complex B0=new Complex(H,0); // fixed field 
	//public final static Complex H_z= new Complex(h_z,0); //applied field in z direction 
	//public final static Complex Hx= new Complex(hx,0); //applied field in z direction
	//public final static Complex Hy= new Complex(hy,0); //applied field in z direction
	public final static Complex kom= new Complex(trate,0); // transitions
	public final static Complex zero = new Complex(0,0); // zero
	//public final static Complex h = new Complex(h_x*0.5,h_y*0.5); //setting h^+
	//public final static Complex h1 = h.conjugate(); //setting h^-
	
	
	public final static int max=m_0*m_1*a1; //matrix dimension
	public final static Double[][] miller ={{Math.sqrt(0.5),Math.sqrt(0.5)},{Math.sqrt(0.5),-Math.sqrt(0.5)}}; // H matrix with miller indicies 
	public final static Double[][] millert ={{Math.sqrt(0.5),Math.sqrt(0.5)},{Math.sqrt(0.5),-Math.sqrt(0.5)}}; //transpose of H
	public final static Double frac = (double)1/(2*I1+1);
	
	
	
	

	
	
	/////////////////////////////////
	////////////////////////////////
	/* NEVER EDIT BELOW THIS LINE */
	////////////////////////////////
	
	public static String[] genind(int m_0,int m_1, int a1 ){
	/*Indexing*/
	int x=1;
	String Y[] = new String[max];
	
		for(int k=0;k<m_0;k++){
			for(int l=0;l<m_1;l++){
				for(int a=0;a<a1;a++){
					
					Y[x-1]=String.format("%04d",a+100*k+1000*l);
					
					x++;
					
				}
				
			}
			
		}
		
		return Y;
		
		}
	
	
	
	
	public static int index(int m_0, int m_1, int a1, String Y[]){
		int index=0;
		/*Indexing*/
		
		for(int i=0;i<max;i++){
			
			if (Y[i].equals(String.format("%04d",a1+100*m_0+1000*m_1)))
				
				index=i;
			
			
		}
			

		return index;
		
		}
	
////////////////////////////////////////////////////
////////////////////////////////////////////////////
/*Indexing back*/
	public static int m0indexback( int k, String Y[]){
		int m0=0;
		int	f=Integer.parseInt(Y[k]);
			
		
		
		
		m0=(int) Math.floor((f/1000)); // 10<a<100
			
		return m0;
		
	}
	public static int m1indexback( int k,String Y[]){
		int m1=0;
		int	f=Integer.parseInt(Y[k]);
		
		
		m1=(int) Math.floor((f-Math.floor(f/1000)*1000)/100);
		
		return m1;
		
	}
	public static int a1indexback(int k,String Y[]){
		int	f=Integer.parseInt(Y[k]);
		int a1=0;
		
		
		a1= (int) (Math.floor(f-Math.floor(f/1000)*1000)-Math.floor((f-Math.floor(f/1000)*1000)/100)*100);
		
		return a1;
	
	}
/////////////////////////////////////////////////////	
////////////////////////////////////////////////////
	public static int m01indexback( int l, String Y[]){
		int	f=Integer.parseInt(Y[l]);
		int m01=0;
	
		
		m01=(int) Math.floor((f/1000)); // 10<a<100
	
		return m01;

}
	public static int m11indexback( int l,String Y[]){
		int	f=Integer.parseInt(Y[l]);
		int m11=0;
		
		
		m11=(int) Math.floor((f-Math.floor(f/1000)*1000)/100);
		
		
return m11;

}
	public static int b1indexback(int l,String Y[]){
	int	f=Integer.parseInt(Y[l]);
	int b1=0;
	
	
	b1= (int) (Math.floor(f-Math.floor(f/1000)*1000)-Math.floor((f-Math.floor(f/1000)*1000)/100)*100);
	
	
return b1;

}
////////////////////////////////////////////////////
	
	public static Object Transition(String Y[], Complex hamatrix[][][][][][]){
		Complex trans[][]= new Complex[max][max];
		
		for(int k=0; k<m_0;k++){
			for(int l=0; l<m_1; l++){
				for (int m=0; m<m_01; m++){
					for(int n=0;n<m_11; n++){
						for(int a=0; a<a1; a++){
							for(int b=0; b<b1;b++){
					
					 
					trans[index(k,l,a,Y)][index(m,n,b,Y)]=hamatrix[a][b][k][l][m][n];
					
					
							}
						}
					}
				}
			}
		}
		return trans;
	
	}
	
	
	public static Complex[][] inversion(Complex trans[][]){
		/*Matrix inversion*/
		Complex transcompl[][] = new Complex[max][max];
		Complex transcompl1[][] = new Complex[max][max];
		ComplexMatrix inversm = new ComplexMatrix(max,max);
		/*
		for(int k=0; k<max;k++){
			for(int l=0; l<max; l++){
				
 					System.out.print(trans[k][l]+ " ");
						
			}		
			System.out.println();
		}
		*/
		
		for(int k=0; k<max;k++){
			for(int l=0; l<max; l++){
				
					transcompl[k][l]=(Complex) trans[k][l];
						
			}		
		}
		
		ComplexMatrix comp1= new ComplexMatrix(transcompl);
    	inversm=comp1.inverse();
    	
    	for(int k=0; k<max;k++){
			for(int l=0; l<max; l++){
				
					transcompl1[k][l]=inversm.getElementReference(k,l);
					
			}
		}
    	
    	return transcompl1;
	}
	
	/* Invertiing from 2-D back to 6-D */
	public static Object[][][][][][] matrixturn(Complex transcompl1[][], String Y[]){
		Complex[][][][][][] hamatrixnew= new Complex[a1][b1][m_0][m_1][m_01][m_11];
		
		
		for(int k=0; k<max;k++){
			for(int l=0; l<max; l++){
		
		hamatrixnew[a1indexback(k,Y)][b1indexback(l,Y)][m0indexback(k,Y)][m1indexback(k,Y)][m01indexback(l,Y)][m11indexback(l,Y)]=transcompl1[k][l];
		
			}
		}
    	
		return hamatrixnew;
	}
	

	
	
	//  
	public static Complex summation(Object[][][][][][] hamatrixnew){
		
Complex s=new Complex(0,0);
		
	
		/* Summation goes here */
for (int i=0; i<a1; i++){
	for (int j=0; j<b1; j++){
		for (int k=0; k<m_0; k++ ){
			for (int l=0; l<m_1; l++ ){
				for (int m=0; m<m_01; m++){
					for(int n=0;n<m_11; n++){
						if((k!=l)&&(m!=n))
										/*Unnormalized summation*/
							s=s.plus(((Complex) hamatrixnew[i][j][k][l][m][n]).times(miller[l][k]).times(millert[n][m]).times(frac).times(0.1666666));
									
								
							}
						}
					}
				}
			}
		}
				
return s;
		
	}
	///End////
	
	
     

	public static void main(String[] args) throws Exception{
		
		/*
		
		// call class
		
       
		try{
		
		Complexmatan solution = new Complexmatan();		// class start
		
		Complex[][] W = Transrate.transitionMatrix(a1, b1, kom); // call class to assign W
		
		String[] Y = solution.genind(m_0,m_1,a1); // generating encoding table
		Complex[][] prob = Stochind.stochasticf(a1, b1);	
		//////////////////////////////////////////////////////
		PrintWriter pr = new PrintWriter("data_generated111.txt");
		double[] Data = new double[(int) (size)];
		Double[] y = new Double[(int) (size)];
		double[][] all =new double[size][2];
		//Complex s=new Complex(0,0);	
		//////////////////////////////////////////////////////
		int count=0;
		
		//\\ Loop for runnig through all values of frequencies //\\
		
		
		for(double zz=0;zz<set;zz=zz+step){
			Complex p = new Complex(zz,0);
			count++;
			
			
			
			Complex[][][][][][] hamatrix =Matrixcreation.matrixGeneration(p,a1, b1, m_0, m_1, m_01, m_11, W,prob);
			
			Complex[][] trans =  (Complex[][]) solution.Transition(Y,hamatrix); 
			
			
			Complex[][] transcompl1 = solution.inversion(trans);
			
			
			Object[][][][][][] hamatrixnew =  solution.matrixturn( transcompl1, Y);
			Complex [] Norm = solution.norma(a1);
			Complex s1 =solution.summation(hamatrixnew, Norm);
			
			all[count-1][0]=zz;
			all[count-1][1]=s1.getReal()*(-1);
			Data[count-1] = s1.getReal()*(-1);
			//all[count-1][0]=s1.getReal()*(-1);;
			//all[(int) zz][1]=zz;
			
			pr.println(Data[count-1]);
			/*
			Plot2DPanel plot = new Plot2DPanel();
			 plot.addLinePlot("my plot", all);
			 
			  // put the PlotPanel in a JFrame, as a JPanel
			  JFrame frame = new JFrame("a plot panel");
			  frame.setContentPane(plot);
			  frame.setVisible(true);
			final Hamplot demo = new Hamplot("XY Series Demo",all );
		    demo.pack();
		    RefineryUtilities.centerFrameOnScreen(demo);
		    demo.setVisible(true);
			 */
		/*
		}
		
		pr.close();
		}
		
		catch (Exception e)
		{
		    e.printStackTrace();
		    System.out.println("No such file exists.");
		}

		*/
		 
		/*
		Double[][] K =new Double[size][2];
		K=Dataloader.reading();
	*/
	
		
		double[][] all = FrequencySwept.finalniy();
		final Frequencyplot plot = new Frequencyplot("I vs w plot",all );
		plot.pack();
		RefineryUtilities.centerFrameOnScreen(plot);
		plot.setVisible(true);
		
		
		/*
		double[][] all1 = Fieldswept.finalniy();
		final Fieldplot demo1 = new Fieldplot("I vs H plot",all1 );
		demo1.pack();
	    RefineryUtilities.centerFrameOnScreen(demo1);
	    demo1.setVisible(true);
		
		*/
		/*
		double [] x = Alltogether.xreturn();
		double [] y = Alltogether.yreturn();
		double [][]data = Alltogether.vse();
		
		Plot3DPanel plot1 = new Plot3DPanel("SOUTH");
		 
        // add grid plot to the PlotPanel
        plot1.addGridPlot("z=cos(PI*x)*sin(PI*y)", y, x, data);
      
        JFrame frame = new JFrame("a plot panel");
        frame.setSize(600, 600);
        frame.setContentPane(plot1);
        frame.setVisible(true);
		*/
		
		
		
		//DemoLauncher.openDemo(new WireSurfaceDemo());
		
		//DemoLauncher.openDemo(new ColorWaveDemo());
		
		
		}
	
	
	
	


	
}	
	

	

