package ukr.stochasticlineshape;
import java.lang.reflect.Field;

import flanagan.complex.Complex;


public class MatrixCreation {
	
	
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
		
	
	
	 static Double [] H_z = Main.hzgener();
	 static double H=   (Double) ValuesCatch.getXField("H0",null);
	 //static Complex h=  (Complex) Valuescatch.getXField("h",null);
	 //static Complex hp=  (Complex) Valuescatch.getXField("hp",null);
	 //static Complex Hx=  (Complex) Valuescatch.getXField("Hx",null);
	 //static Complex Hy=  (Complex) Valuescatch.getXField("Hy",null);
	 
	 //static Double Hz = (Double) Valuescatch.getXField("hz",null);
	 
		
		// Actual assignment 
	 public static Complex[][][][][][] matrixGeneration(double H,Complex p,int a1,int b1,int m_0,int m_1,int m_01,int m_11, Double W[][], Complex prob[][]){	
			Complex[][][][][][] hamatrix = new Complex[a1][b1][m_0][m_1][m_01][m_11];
			Complex[][][][][][] hamatrix1 = new Complex[a1][b1][m_0][m_1][m_01][m_11];
			
			
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
										hamatrix1[i][j][k][l][m][n]=new Complex(0,0);
									
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
											
											hamatrix[i][j][k][l][m][n] =(Complex) (p.minus(new Complex(0,0.01)).times(CI)).minus(W[i][j]);
																			
										
										if((i!=j)&&(k==m)&&(l==n))
										
											hamatrix[i][j][k][l][m][n] =new Complex(-W[i][j],0);
										
										if((i==j)&&(k==m)&&(l==n)&&(k!=l)&&(m!=n)&&(k==0))
										
											
											hamatrix[i][j][k][l][m][n] = (Complex) (p.minus(new Complex(0,0.01)).times(CI)).minus(new Complex(W[i][j],0)).minus((CI.times((new Complex(H,0)).plus((new Complex(H_z[i],0)).times(prob[i][j]))))); //(Complex) I.times(-H-h_z).plus(p.minus(W[i][j]));
											//hamatrix[i][j][k][l][m][n] = (Complex) (p.minus(new Complex(0,0.01)).times(CI)).minus(new Complex(W[i][j],0)).minus((CI.times((new Complex(H,0)).plus((new Complex(Hz,0)).times(prob[i][j]))))); //(Complex) I.times(-H-h_z).plus(p.minus(W[i][j]));

										
										
										if((i==j)&&(m==k)&&(n==l)&&(l!=k)&&(n!=m)&&(k==1))
											
											hamatrix[i][j][k][l][m][n] = (Complex) (p.minus(new Complex(0,0.01)).times(CI)).minus(new Complex(W[i][j],0)).plus((CI.times(((new Complex(H,0)).plus((new Complex(H_z[i],0)).times(prob[i][j]))))));
											//hamatrix[i][j][k][l][m][n] = (Complex) (p.minus(new Complex(0,0.01)).times(CI)).minus(new Complex(W[i][j],0)).plus((CI.times(((new Complex(H,0)).plus((new Complex(Hz,0)).times(prob[i][j]))))));

										if((i==j)&&(l==n)&&(k==0)&&(m==1))
											
											hamatrix[i][j][k][l][m][n]= h.times(CI).times(prob[i][j]);
										
										if((i==j)&&(k==m)&&(n==0)&&(l==1))
											
											hamatrix[i][j][k][l][m][n]= h.times(CI).negate().times((prob[i][j]));
										
										
										if((i==j)&&(l==n)&&(k==1)&&(m==0))
											
											hamatrix[i][j][k][l][m][n]= hp.times(CI).times((prob[i][j]));
										
										if((i==j)&&(k==m)&&(n==1)&&(l==0))
											
											hamatrix[i][j][k][l][m][n]= hp.times(CI).negate().times((prob[i][j]));
										
									}
								}
							}
						}
					}
				}

				return hamatrix;
			
			}	
	 
	 
	 
	}
