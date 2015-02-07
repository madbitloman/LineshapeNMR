package us.plotof3d;

import org.jzy3d.chart.Chart;

import ukr.stochasticlineshape.*;

import org.jzy3d.chart.controllers.keyboard.camera.CameraKeyController;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.Quality;

import flanagan.complex.Complex;

public class ColorWaveDemo extends AbstractDemo {
	static int a1=  (Integer) ValuesCatch.getXField("a1",null);
	static int b1=  (Integer) ValuesCatch.getXField("b1",null);
	static int m_1=  (Integer) ValuesCatch.getXField("m_1",null);
	static int m_01=  (Integer) ValuesCatch.getXField("m_01",null);
	static int m_11=  (Integer) ValuesCatch.getXField("m_11",null);
	static int m_0=  (Integer) ValuesCatch.getXField("m_0",null);
	static Complex kom=  (Complex) ValuesCatch.getXField("kom",null);
	//static int size=  (Integer) test.getXField("size",null);
	


    public static void main(String[] args) throws Exception {
        DemoLauncher.openDemo(new ColorWaveDemo());
    }

    public ColorWaveDemo() {
    }

    @Override
    public void init() {
        // Define a function to plot
        Mapper mapper = new Mapper() {
            public double f(double x, double y) {
            	
            	Double x1= x;
            	Double y1 = y;
            	Double[][] W = TransRate.transitionMatrix(a1, b1, kom); // call class to assign W
        		
        		String[] Y = Main.genind(m_0,m_1,a1); // generating encoding table
        		Complex[][] prob = StochInd.stochasticf(a1, b1);
        		
        		Complex[][][][][][] hamatrix =MatrixCreation.matrixGeneration(x1,y1,a1, b1, m_0, m_1, m_01, m_11, W,prob);
    			
				Complex[][] trans =  (Complex[][]) Main.Transition(Y,hamatrix); 
		
		
				Complex[][] transcompl1 = Main.inversion(trans);
		
		
				Object[][][][][][] hamatrixnew =  Main.matrixturn( transcompl1, Y);
				Complex [] Norm = Main.norma(a1);
				Complex s1 =Main.summation(hamatrixnew, Norm);
		
				//Datatotal[i][j]=s1.getReal()*(-1);
            	
                return s1.getReal()*(-1);
            }
        };

        // Define range and precision for the function to plot
        Range range1 = new Range(-15, 15);
        Range range = new Range(-10, 10);
        int steps = 100;
        int steps1 =100;
        // Create the object to represent the function over the given range.
        final Shape surface = Builder.buildOrthonormal(new OrthonormalGrid(range1, steps1, range, steps), mapper);
        surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(false);
        chart.getAxeLayout().setXAxeLabel("Frequency");
        chart.getAxeLayout().setYAxeLabel("Fixed Field");
        chart.getAxeLayout().setZAxeLabel("Intensity");

        // Create a chart
        chart = new Chart(Quality.Advanced, getCanvasType());
        chart.getScene().getGraph().add(surface);
        chart.addController(new CameraKeyController());
    }
}
