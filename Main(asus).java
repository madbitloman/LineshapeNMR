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

	static double phi=(1+Math.sqrt(5))/2; //golden ratio
	//static int abvalue [] =Dialogbox.abvalueset();
	//static Double[] otherv= Dialogbox.othervalues(); 
	//static Double[] fswept= Dialogbox.fieldval(); 
	//static Complex probab[] = new Complex[]{new Complex(1/12,0),new Complex(1/12,0),new Complex(1/12,0),new Complex(1/12,0), new Complex(1/12,0), new Complex(1/12,0),new Complex(1/12,0), new Complex(1/12,0),new Complex(1/12,0), new Complex(1/12,0), new Complex(1/12,0), new Complex(1/12,0)};
	
	static Double[] probabicos = new Double[]{0.08,0.08,0.08,0.08,0.08,0.08,0.08,0.08,0.08,0.08,0.08,0.08};
	static Double[] probaboctah = new Double[]{0.16,0.16,0.16,0.16,0.16,0.16};
	
	//static Double[] probab = new Double[]{0.5,0.5};
	///////////////////////////////////////////////////////// 
	public final static int a1=6;//a stochastic indices// 
	public final static int b1=a1;//b stochastic indices//
	public static Double trate =(double) 0.100; // transition rate that defines values for W matrix
	////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////////////////////////////////////////
	public static int setw=60; //frequency set for fieldsweptz				//////
	public static int setwn=0; //frequency set for fieldsweptz negative domain//////
	//////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////////////
	public static Double seth=(double) 4; //frequency set for frequenc3dy swept////
	public static Double sethn=(double) -4; //frequency set for frequency swept///
	/////////////////////////////////////////////////////////////////////////////////
	
	//public final static Double h_x=(double) 0; //applied field in x direction		
	//public final static Double h_y=(double) 0; //applied field in y direction
	//public final static Double h_z=(double) 0; //applied field in z direction
	//public final static Double H=(double) 4; // fixed field
	/* 					Step Size         										*/
	public static Double stepw=(double) 0.1; //optimal step size for frequency plot 0.1 and 0.01 for field
	public static Double steph=0.01;
	public static int size;
	public static double pp=5; // set of frequency
	///////////////////////
	
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
	
/////////////////////////////
/* Setting varying G tensor*/
////////////////////////////
//public final static Double ang[]=  {Math.atan(phi/1),Math.atan(-phi/1),Math.atan(-phi/1),Math.atan(phi/1),(double) 0,(double) 0,(double) 0,(double) 0,Math.atan(1/phi),Math.atan(-1/phi),Math.atan(-1/phi),Math.atan(1/phi)};

	public final static Double ang[]=  {0.0,0.0,0.0,0.0,0.0,0.0};
	
	
//public final static Double angx[]=  {Math.atan(phi/1),Math.atan(-phi/1),Math.atan(-phi/1),Math.atan(phi/1),(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,(double) 0};
//public final static Double angy[]=  {(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,Math.atan(1/phi),Math.atan(-1/phi),Math.atan(-1/phi),Math.atan(1/phi)};
//public final static Double angz[]=  {(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,(double) 0,(double) 0};

//public final static Double ang[]= new Double[]{0.000001,0.1,0.1,0.1,0.1,0.1,0.999,0.1,0.1,0.1,0.123123,0.8,};

public static Double[] respolx(Double ang[]){
	Double[] hxx=new Double[a1];
	for(int i=0; i<a1; i++){
		
		//hxx[i]=(Math.sqrt(3/8)*Math.sin(ang[i])*Math.sin(ang[i])*g22*H11+0.5*(2*Math.cos(ang[i])*Math.cos(ang[i])-1)*g02)*H11;
		hxx[i]=(0.5*(3*Math.pow(Math.cos(ang[i]), 2)-1))*(gzz-0.5*(gxx+gyy))*H11*(Math.sqrt(2)/Math.sqrt(3));
		//hxx[i]=(double) 0;
		
	}
	return hxx;
	
	
	
}

public static Double[] respoly(Double ang[]){
	Double[] hyy=new Double[a1];
	for(int i=0; i<a1; i++){
		
		//yy[i]=(Math.sqrt(3/8)*Math.sin(ang[i])*Math.sin(ang[i])*g22*H11+0.5*(2*Math.cos(ang[i])*Math.cos(ang[i])-1)*g02)*H11;
		hyy[i]=(0.5*(3*Math.pow(Math.cos(ang[i]), 2)-1))*(gzz-0.5*(gxx+gyy))*H11*(Math.sqrt(2)/Math.sqrt(3));
		//hyy[i]=(double) 0;
		
	}
	return hyy;
	
	
	
}

public static Double[] respolz(Double ang[]){
	Double[] hzz=new Double[a1];
	for(int i=0; i<a1; i++){
		
		//hzz[i]= (0.5*(3*Math.cos(ang[i])*Math.cos(ang[i])-1)*g02)*H11;
		hzz[i]=(0.5*(3*Math.pow(Math.cos(ang[i]), 2)-1))*(gzz-0.5*(gxx+gyy))*H11*(Math.sqrt(2)/Math.sqrt(3));
		//hzz[i]=(double) 3;
		
	}
	return hzz;
	
	
	
}
/*
public static Double[] respolhh(Double ang[]){
	Double[] hhh=new Double[12];
	for(int i=0; i<12; i++){
		
		//hzz[i]= (0.5*(3*Math.cos(ang[i])*Math.cos(ang[i])-1)*g02)*H11;
		hhh[i]=(0.5*(3*Math.pow(Math.cos(ang[i]), 2)-1))*(gzz-0.5*(gxx+gyy))*H1*(Math.sqrt(2)/Math.sqrt(3))+(gxx+gyy+gzz)*H1/3;
		//hzz[i]=(double) 3;
		
	}
	return hhh;
	
	
	
}
*/



public final static Double gxx=2.0;
public final static Double gyy=2.0;
public final static Double gzz=2.2;
public final static Double g00=(gxx+gyy+gzz)*(1/Math.sqrt(3));
//public final static Double g02=(gzz-0.5*(gxx+gyy))*Math.sqrt(2)/Math.sqrt(3);
//public final static Double g22=0.5*(gxx-gyy);

/*
public final Complex G00= new Complex(g00,0);
public final Complex G02= new Complex(g02,0);
public final Complex G22= new Complex(g22,0);
*/
//public Double L = 2*Math.cos(2*gamma)*Math.sqrt(3/8)*Math.sin(betta)*Math.sin(betta)*g22+0.5*(2*Math.cos(betta)*Math.cos(betta)-1)*g02;

public static Double H11=(double) 4;
public static Double H1=(double) 3;
//public static Double H=(double) 10000000;
//public static Double h_z = (Math.sqrt(3/8)*Math.sin(0)*Math.sin(0)*g22*H11+0.5*(2*Math.cos(0)*Math.cos(0)-1)*g02)*H11;
//public static Double h_x = (Math.sqrt(3/8)*Math.sin(Math.PI/2)*Math.sin(Math.PI/2)*g22*H11+0.5*(2*Math.cos(Math.PI/2)*Math.cos(Math.PI/2)-1)*g02)*H11;
//public static Double h_y = (Math.sqrt(3/8)*Math.sin(Math.PI/2)*Math.sin(Math.PI/2)*g22*H11+0.5*(2*Math.cos(Math.PI/2)*Math.cos(Math.PI/2)-1)*g02)*H11;
//public static Double hnew = (Math.sqrt(3/8)*Math.sin(Math.PI/2)*Math.sin(Math.PI/2)*g22*H11+0.5*(2*Math.cos(Math.PI/2)*Math.cos(Math.PI/2)-1)*g02)*H11;

//public static Double h_zz = (Math.sqrt(3/8)*Math.sin(0)*Math.sin(0)*g22*H11+0.5*(2*Math.cos(0)*Math.cos(0)-1)*g02)*H11;
//public static Double h_xx = (Math.sqrt(3/8)*Math.sin(Math.PI/2)*Math.sin(Math.PI/2)*g22*H11+0.5*(2*Math.cos(Math.PI/2)*Math.cos(Math.PI/2)-1)*g02)*H1;
//public static Double h_yy = (Math.sqrt(3/8)*Math.sin(Math.PI/2)*Math.sin(Math.PI/2)*g22*H11+0.5*(2*Math.cos(Math.PI/2)*Math.cos(Math.PI/2)-1)*g02)*H1;
//public static Double hnew = (Math.sqrt(3/8)*Math.sin(Math.PI/2)*Math.sin(Math.PI/2)*g22*H11+0.5*(2*Math.cos(Math.PI/2)*Math.cos(Math.PI/2)-1)*g02)*H1;
//public static Double hy = (2*Math.cos(2*Math.PI/2)*Math.sqrt(3/8)*Math.sin(Math.PI/2)*Math.sin(Math.PI/2)*g22+0.5*(2*Math.cos(Math.PI/2)*Math.cos(Math.PI/2)-1)*g02)*H;
//public static Double  H=g00*H1+h_xx+h_yy+h_zz;
public static Double  H=(gxx+gyy+gzz)*H1/3;
//public static Double  h_z=g00*H11+hnew;
	
	
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
	//public final static Complex H_x= new Complex(h_x,0); //applied field in z direction
	//public final static Complex H_y= new Complex(h_y,0); //applied field in z direction
	public final static Complex kom= new Complex(trate,0); // transitions
	public final static Complex CI = new Complex(0,1); // setting complex i
	public final static Complex zero = new Complex(0,0); // zero
	//public final static Complex h = new Complex(h_x*0.5,h_y*0.5); //setting h^+
	//public final static Complex h1 = h.conjugate(); //setting h^-
	
	
	public final static int max=m_0*m_1*a1; //matrix dimension
	public final static Double[][] miller ={{Math.sqrt(0.5),Math.sqrt(0.5)},{Math.sqrt(0.5),-Math.sqrt(0.5)}}; // H matrix with miller indicies 
	public final static Double[][] millert ={{Math.sqrt(0.5),Math.sqrt(0.5)},{Math.sqrt(0.5),-Math.sqrt(0.5)}}; //transpose of H
	public final static Double frac = (double)1/(2*I1+1);
	
	
	
	public final static Complex B0=new Complex(H,0);

	
	
	
	

	
	
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
			
		
		//m0=(int) Math.floor((f/100)); // up to a=10
		
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
		//m_1=(int) Math.floor((Y[k]*100-(Y[k]*10)*10));
		
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
		/*
		for(int aa=0; aa<a1; aa++){
			for(int bb=0; bb<b1;bb++){
			for(int kk=0; kk<m_0;kk++){
				for(int ll=0; ll<m_1; ll++){
					for (int mm=0; mm<m_01; mm++){
						for(int nn=0;nn<m_11; nn++){
					
					 // Nuzhno s indeksaciey utochnit
					trans[index(kk,ll,aa,Y)][index(mm,nn,bb,Y)]=zero;
					
					
				}
				
			}
		}
	}
			}
		}
		*/
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
				
					transcompl[k][l]=new Complex(0,0);
						
			}		
		}
		for(int k=0; k<max;k++){
			for(int l=0; l<max; l++){
				
					transcompl1[k][l]=new Complex(0,0);
						
			}		
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
	
	
	public static Object[][][][][][] matrixturn(Complex transcompl1[][], String Y[]){
		Complex[][][][][][] hamatrixnew= new Complex[a1][b1][m_0][m_1][m_01][m_11];
		
		
		for(int k=0; k<max;k++){
			for(int l=0; l<max; l++){
		
		hamatrixnew[a1indexback(k,Y)][b1indexback(l,Y)][m0indexback(k,Y)][m1indexback(k,Y)][m01indexback(l,Y)][m11indexback(l,Y)]=transcompl1[k][l];
		
			}
		}
    	
		return hamatrixnew;
	}
	
	public static Complex[] norma(int a1){
		
	Complex[] Norm = new Complex[a1];
	
	for(int i=0; i<a1;i++){
		
		Norm[i]= new Complex(0.16667,0);
		
	}
		
	return Norm;
	}
	
	
	//  
	public static Complex summation(Object[][][][][][] hamatrixnew, Complex Norm[]){
		Object[][][][][][] hamatrixnew1 = new Object[a1][b1][m_0][m_1][m_01][m_11];
		
			
		Complex s=new Complex(0,0);
		
		for (int i=0; i<a1; i++){
			for (int j=0; j<b1; j++){
				for (int k=0; k<m_0; k++ ){
					for (int l=0; l<m_1; l++ ){
						for (int m=0; m<m_01; m++){
							for(int n=0;n<m_11; n++){
								if((k!=l)&&(m!=n))
									
								 hamatrixnew1[i][j][k][l][m][n]=hamatrixnew[i][j][k][l][m][n];
								
							
									}
								}
							}
						}
					}
				}
			
	
		
			for (int i=0; i<a1; i++){
				for (int j=0; j<b1; j++){
					for (int k=0; k<m_0; k++ ){
						for (int l=0; l<m_1; l++ ){
							for (int m=0; m<m_01; m++){
								for(int n=0;n<m_11; n++){
									if((k!=l)&&(m!=n))
										
									//s=s.plus(((Complex) hamatrixnew1[i][j][k][l][m][n]).times(miller[l][k]).times(millert[n][m]).times(frac).times(new Complex(probabicos[i],0)));
									s=s.plus(((Complex) hamatrixnew1[i][j][k][l][m][n]).times(miller[l][k]).times(millert[n][m]).times(frac).times(new Complex(probaboctah[i],0)));

								
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
	
		
		double[][] all = Frequencyswept.finalniy();
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
		
		
		/*
		DemoLauncher.openDemo(new WireSurfaceDemo());
		/*
		DemoLauncher.openDemo(new ColorWaveDemo());
		*/
		
		}
	
	
	
	


	
}	
	

	

