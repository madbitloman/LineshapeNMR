package us.plotof3d;

//import java.awt.Dimension;

import java.io.FileNotFoundException;

import org.jzy3d.chart.Chart;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.Quality;
//import org.jzy3d.plot3d.rendering.legends.colorbars.ColorbarLegend;


import ukr.stochasticlineshape.Main;
import ukr.stochasticlineshape.MatrixCreation;
import ukr.stochasticlineshape.StochInd;
import ukr.stochasticlineshape.TransRate;
import ukr.stochasticlineshape.ValuesCatch;
import flanagan.complex.Complex;

public class WireSurfaceDemo extends AbstractDemo {
	static int a1=  (Integer) ValuesCatch.getXField("a1",null);
	static int b1=  (Integer) ValuesCatch.getXField("b1",null);
	static int m_1=  (Integer) ValuesCatch.getXField("m_1",null);
	static int m_01=  (Integer) ValuesCatch.getXField("m_01",null);
	static int m_11=  (Integer) ValuesCatch.getXField("m_11",null);
	static int m_0=  (Integer) ValuesCatch.getXField("m_0",null);
	static Complex kom=  (Complex) ValuesCatch.getXField("kom",null);
	//static int size=  (Integer) test.getXField("size",null);
	//static int setw=  (Integer) Valuescatch.getXField("setw",null);
	//static int setwn=  (Integer) Valuescatch.getXField("setwn",null);
	//static Double stepw=  (Double) Valuescatch.getXField("stepw",null);
	/////////////////////////////////////////////////////////////
	//static int seth=  (Integer) Valuescatch.getXField("seth",null);
	//static int sethn=  (Integer) Valuescatch.getXField("sethn",null);
	//static Double steph=  (Double) Valuescatch.getXField("steph",null);
	///////////////////////////////////////////////////////
	//static Complex H = (Complex) Valuescatch.getXField("B0",null);
	//public static int sizew=(int) ((setw+Math.abs(setwn))/stepw);
	//public static int sizeh=(int) ((seth+Math.abs(sethn))/steph); 
	
	//static double[][] Datatotal =new double[sizew][sizeh];
	//static double [] x= new double[sizew];
	//static double [] y = new double[sizeh];
	
	//static double f=sethn;
	//static double s = setwn;
    public static void main(String[] args) throws Exception {
        DemoLauncher.openDemo(new WireSurfaceDemo());
    }

    public WireSurfaceDemo() {
    }

    @Override
    public void init() {
        // Define a function to plot
        Mapper mapper = new Mapper() {
            public double f(double x, double y) {
            	
            	Complex x1= new Complex(x,0);
            	Complex y1 = new Complex(y,0);
            	Double[][] W;
			
				W = TransRate.transitionMatrix(a1, b1, kom);
				
        		
        		String[] Y = Main.genind(m_0,m_1,a1); // generating encoding table
        		Complex[][] prob = StochInd.stochasticf(a1, b1);
        		
        		Complex[][][][][][] hamatrix =MatrixCreation.matrixGeneration(x,y1,a1, b1, m_0, m_1, m_01, m_11, W,prob);
    			
				Complex[][] trans =  (Complex[][]) Main.Transition(Y,hamatrix); 
		
		
				Complex[][] transcompl1 = Main.inversion(trans);
		
		
				Object[][][][][][] hamatrixnew =  Main.matrixturn( transcompl1, Y);
	
				Complex s1 =Main.summation(hamatrixnew);
		
				//Datatotal[i][j]=s1.getReal()*(-1);
            	
                return s1.getReal();
            }
        };

        // Define range and precision for the function to plot
        Range range1 = new Range(0, 15);
        Range range = new Range(0, 4);
        int steps = 100;
        int steps1 =100;

        // Create the object to represent the function over the given range.
        final Shape surface = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range1, steps1), mapper);
        surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(true);
        surface.setWireframeColor(Color.BLACK);
      
        // Create a chart and add surface
        chart = new Chart(Quality.Advanced, getCanvasType());
        chart.getScene().getGraph().add(surface);
        chart.getAxeLayout().setXAxeLabel("Frequency");
        chart.getAxeLayout().setYAxeLabel("Fixed Field");
        chart.getAxeLayout().setZAxeLabel("Intensity");
    }

    @Override
    public String getPitch() {
        return "Show a simple surface based on a mathematical function";
    }
}
