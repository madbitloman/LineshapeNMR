package ukr.stochasticlineshape;
import java.lang.reflect.Field;

import flanagan.complex.Complex;
//import flanagan.complex.ComplexMatrix;

public class Matrixcreation {
	
	
	public static Object getXField(String name, Main object) {
        try {
            Field f = Main.class.getDeclaredField(name);

            f.setAccessible(true);

            return f.get(object);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
		
	 //static Complex H_z=  (Complex) Valuescatch.getXField("H_z",null);
	 //static Complex H_x=  (Complex) Valuescatch.getXField("H_x",null);
	 //static Complex H_y=  (Complex) Valuescatch.getXField("H_y",null);
	 //static Double H=  	 (Double) Valuescatch.getXField("H",null);
	 //static Complex h=  (Complex) Valuescatch.getXField("h",null);
	 //static Complex h1=  (Complex) Valuescatch.getXField("h1",null);
	 static Double[] ang =(Double[]) Valuescatch.getXField("ang",null);  
	// static Double[] angy =(Double[]) Valuescatch.getXField("angy",null); 
	 //static Double[] angz =(Double[]) Valuescatch.getXField("angz",null); 
	 static Double[] hxx=Main.respolx(ang);
	 static Double[] hyy=Main.respoly(ang);
	 static Double[] hzz=Main.respolz(ang);
	 //static Double[] HH=Main.respolhh(ang);
		
		// Actual assignment 
	 public static Complex[][][][][][] matrixGeneration(Complex H,Complex p,int a1,int b1,int m_0,int m_1,int m_01,int m_11, Complex W[][], Complex prob[][]){	
			Complex[][][][][][] hamatrix = new Complex[a1][b1][m_0][m_1][m_01][m_11];
			Complex[][][][][][] hamatrix1 = new Complex[a1][b1][m_0][m_1][m_01][m_11];
			//Complex[][][][][][] hamatrixm0 = new Complex[a1][b1][m_0][m_1][m_01][m_11];
			Complex CIM=new Complex(0,-1);
			Complex CI=new Complex(0,1);
			// making zeros to avoid Null
			
				for (int i=0; i<a1; i++){
					for (int j=0; j<b1; j++){
						for (int k=0; k<m_0; k++ ){
							for (int l=0; l<m_1; l++ ){
								for (int m=0; m<m_01; m++){
									for(int n=0;n<m_11; n++){
									
										
										hamatrix[i][j][k][l][m][n]=new Complex(0,0);
										hamatrix1[i][j][k][l][m][n] = new Complex(0,0);
									
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
										
										if((i==j)&&(k==m)&&(l==n)&&(k==n)&&(l==m)&&(k==l))		
											
											hamatrix[i][j][k][l][m][n] =(Complex) ((p.plus((CI).times(0.5))).times(CI)).minus(W[i][j]);
																			
										
										if((i!=j)&&(k==m)&&(l==n))
										
											hamatrix[i][j][k][l][m][n] =(Complex) W[i][j].negate();
										
										if((i==j)&&(k==m)&&(l==n)&&(k!=l)&&(m!=n)&&(k==0))
										
											
											//hamatrix[i][j][k][l][m][n] = (Complex) ((p.plus((CI).times(0.5))).times(CI)).minus(W[i][j]).minus((CI.times((new Complex(HH[i],0)).plus((new Complex(hzz[i],0)).times(prob[i][j]))))); //(Complex) I.times(-H-h_z).plus(p.minus(W[i][j]));
											hamatrix[i][j][k][l][m][n] = (Complex) ((p.plus((CI).times(0.5))).times(CI)).minus(W[i][j]).minus((CI.times((H).plus((new Complex(hzz[i],0)).times(prob[i][j]))))); //(Complex) I.times(-H-h_z).plus(p.minus(W[i][j]));

										
										
										if((i==j)&&(m==k)&&(n==l)&&(l!=k)&&(n!=m)&&(k==1))
											
											hamatrix[i][j][k][l][m][n] = (Complex) ((p.plus((CI).times(0.5))).times(CI)).minus(W[i][j]).plus((CI.times((H).plus((new Complex(hzz[i],0)).times(prob[i][j])))));
											//hamatrix[i][j][k][l][m][n] = (Complex) ((p.plus((CI).times(0.5))).times(CI)).minus(W[i][j]).plus((CI.times((new Complex(HH[i],0)).plus((new Complex(hzz[i],0)).times(prob[i][j])))));
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
										
													
										//h_x
										
										////////////////////////////////////////////
										
										if((i==j)&&(k==m)&&(n==1)&&(l==0))
											
											hamatrix[i][j][k][l][m][n] = (Complex) CI.times(new Complex(hxx[i],0)).times(prob[i][j]);
										
										
										if((i==j)&&(k==m)&&(n==0)&&(l==1))
											
											hamatrix[i][j][k][l][m][n] = (Complex) CI.times(new Complex(hxx[i],0)).times(prob[i][j]);
										
										///////////////////
										if((i==j)&&(l==n)&&(m!=k)&&(m==1)&&(k==0)) 
											
											hamatrix[i][j][k][l][m][n] =(Complex) CI.times(new Complex(hxx[i],0)).negate().times(prob[i][j]);
										
										
										if((i==j)&&(l==n)&&(m==0)&&(k==1)) 
											
											hamatrix[i][j][k][l][m][n] =(Complex) CI.times(new Complex(hxx[i],0)).negate().times(prob[i][j]);
									
											
									
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
										
											
										
										/* H_y */
										////////////////////////////////////////////
										
										if((i==j)&&(k==m)&&(n==1)&&(l==0))
											
											hamatrix1[i][j][k][l][m][n] = (Complex) CIM.times(new Complex(hyy[i],0).conjugate()).times(prob[i][j]);
										
										
										if((i==j)&&(k==m)&&(n==0)&&(l==1))
											
											hamatrix1[i][j][k][l][m][n] = (Complex) CIM.times(new Complex(hyy[i],0)).times(prob[i][j]);
										
										///////////////////
										if((i==j)&&(l==n)&&(m!=k)&&(m==1)&&(k==0)) 
											
											hamatrix1[i][j][k][l][m][n] =(Complex) CI.times(new Complex(hyy[i],0).conjugate()).negate().times(prob[i][j]);
										
										
										if((i==j)&&(l==n)&&(m==0)&&(k==1)) 
											
											hamatrix1[i][j][k][l][m][n] =(Complex) CI.times(new Complex(hyy[i],0)).negate().times(prob[i][j]);
									
											
									
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
									
										
										hamatrix[i][j][k][l][m][n]=hamatrix1[i][j][k][l][m][n].plus(hamatrix[i][j][k][l][m][n]);
										
									
											}
										}
									}
								}
							}
						}
				
				
				return hamatrix;
			
			}	
	 
	 
	 
	}
