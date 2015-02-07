package ukr.stochasticlineshape;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import flanagan.complex.Complex;


public class TransRate {
	
	/* this method for the assigning transition matrix */ 
		public static Double[][] transitionMatrix(int a1, int b1, Complex kom){
			//Double[] probabicos =(Double[]) Valuescatch.getXField("probabicos",null);
			//Double[] probaboctah =(Double[]) Valuescatch.getXField("probaboctah",null);
			//Double trate=  (Double) Valuescatch.getXField("trate",null);
			Double W[][] = new Double[a1][b1];
			double kom1=kom.getReal();
			/*
			Double Wicos[][] = new Double[][]{
			
			{(double) -5*kom1, kom1, (double) 0, (double) 0, kom1, kom1, (double) 0, (double) 0, kom1, kom1,  (double) 0,  (double) 0},
	        {kom1, (double)-5*kom1, (double) 0, (double) 0, (double) 0, (double) 0, kom1, kom1, kom1, kom1,  (double) 0,  (double) 0}, 
	        {(double) 0, (double) 0, (double) -5*kom1, kom1, kom1, kom1, (double) 0, (double) 0, (double) 0, (double) 0,  kom1,  kom1},  
	        {(double) 0, (double) 0, kom1, (double) -5*kom1, (double) 0, (double) 0, kom1, kom1, (double) 0, (double) 0,  kom1,  kom1},  
	        {kom1, (double) 0, kom1, (double) 0, (double)-5*kom1, kom1, (double) 0, (double) 0, kom1, (double) 0,  kom1,  (double) 0},
	        {kom1, (double) 0, kom1, (double) 0, kom1, (double) -5*kom1, (double) 0, (double) 0, (double) 0, kom1,  (double) 0,  kom1},
	        {(double) 0, kom1, (double) 0, kom1, (double) 0, (double) -5*kom1, (double) 0, kom1, kom1, (double) 0,  kom1,  (double) 0},
	        {(double) 0, kom1, (double) 0, kom1, (double) 0, (double) 0, kom1, (double)-5*kom1, (double) 0, kom1,  (double) 0,  kom1},
	        {kom1, kom1, (double) 0, (double) 0, kom1, (double) 0, kom1, (double) 0, (double)-5*kom1, (double) 0,  kom1,  (double) 0  },
	        {kom1, kom1, (double) 0, (double) 0, (double) 0, kom1, (double) 0, kom1, (double) 0, (double)-5*kom1,  (double) 0,  kom1 } ,
	        {(double) 0, (double) 0, kom1, kom1, kom1, (double) 0, kom1, (double) 0, kom1, (double) 0,  (double) -5*kom1,  (double) 0 }, 
	        { (double) 0, (double) 0, kom1, kom1, (double) 0, kom1, (double) 0, kom1, (double) 0, kom1,  (double) 0,  (double)-5*kom1}
	        
	        
		};
			*/
			
			Double Woctoh[][] = new Double[][]{
					
					{-4*kom1,kom1,kom1,kom1,kom1,0.0},
			        {kom1,-4*kom1,kom1,kom1,0.0,kom1}, 
			        {kom1,kom1,-4*kom1,0.0,kom1,kom1},  
			        {kom1,kom1,0.0,-4*kom1,kom1,kom1},   
			        {kom1,0.0,kom1,kom1,-4*kom1,kom1},
			        {0.0,kom1,kom1,kom1,kom1,-4*kom1}
			        
			        
				};
			W=Woctoh;
			
			/*
			for(int i=0; i<a1; i++){
	        	for(int j=0; j<b1; j++){
	         Wicos[i][j]=(probabicos[i]/probabicos[j])*Wicos[i][j];
	         Woctoh[i][j]=(probaboctah[i]/probaboctah[j])*Woctoh[i][j];
	        }
			}
			*/
			
			///////////////////////// Bucky Ball ///////////////////////////////////////
			/*
			Scanner input = new Scanner (new File("my_data_square.txt"));
			// pre-read in the number of rows/columns
			int rows = 0;
			int columns = 0;
			/*
			int Wnew[][]= new int[60][60];
			int Wnew1[][]= new int[60][60];
			Double Wnew2[][]= new Double[60][60];
			input = new Scanner(new File("my_data.txt"));
			for(int i = 0; i < 60; ++i)
			{
			    for(int j = 0; j < 60; ++j)
			    {
			        if(input.hasNextInt())
			        {
			            Wnew[i][j] = input.nextInt();
			        }
			    }
			}
			for(int i = 0; i < 60; ++i)
			{
			    for(int j = 0; j < 60; ++j)
			    {
			    		
			            Wnew1[i][i]+=Wnew[i][j];
			    		
			    		//Wnew1[i][j]=Wnew[i][j]+Wnew[i][j];
			            
			        
			    }
			}
			
			for(int i = 0; i < 60; ++i)
			{
			    for(int j = 0; j < 60; ++j)
			    {
			    		
			            Wnew1[i][j]=Wnew1[i][j]+Wnew[i][j];
			    		
			    		//Wnew1[i][j]=Wnew[i][j]+Wnew[i][j];
			            
			        
			    }
			}
			
	

			for(int a=0; a<a1; a++){
				for(int b=0;b<b1; b++){
					
					W[a][b]=new Complex(Wnew1[a][b],0).times(kom);
				}
			}
			return W;
			
			}
		
	*/
	////////////////////////////////////////////////////////////////////////
			
			/*
			for(int a=0; a<a1; a++){
				for(int b=0;b<b1; b++){
					
					//W[a][b]=new Complex(Wicos[a][b],0); // icosahedron case
					W[a][b]=new Complex(Woctoh[a][b],0); //octahedron
				}
			}
			return W;
			
			}
			*/
		
		/*
			for(int a=0; a<a1; a++){
				for(int b=0;b<b1; b++){
					if(a==b)
					W[a][b]=-kom1;
					if(a!=b)
					W[a][b]=kom1;	
				}
			}
			return W;
		}
		*/
		return W;
		}
		}
