package ukr.stochasticlineshape;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Dialogbox {

	public static int[] abvalueset(){
		
		
	
	    
		  JPanel p = new JPanel();
				
		  JTextField avalue = new JTextField(4); //length of the text field for a
		  JTextField bvalue = new JTextField(4);//length of the text field for b
		  JTextField wplus = new JTextField(3);
		  JTextField wminus = new JTextField(3);
		 // JTextField trr = new JTextField(3);
		  /* actual caption of the values*/
		  p.add(new JLabel("Value of a :")); 
		  p.add(avalue);
		  p.add(new JLabel("Value of b : "));
		  p.add(bvalue);
		  p.add(new JLabel("Set frequency w (negative terminal)  : "));
		  p.add(wplus);
		  p.add(new JLabel("Set frequency w (positive terminal) : "));
		  p.add(wminus);
		  
		  
		  JOptionPane.showConfirmDialog(null, p, "Set of variables to define matrix size and frequency range 2D plot : ", JOptionPane.OK_CANCEL_OPTION);
		  String nik=avalue.getText();
		  String nik1=bvalue.getText();
		  String nik2=wplus.getText();
		  String nik3=wminus.getText();
		
		  /* from string to int */
		  int value =Integer.parseInt(nik); //a
		  int value1 =Integer.parseInt(nik1); //b
		  int value2 = Integer.parseInt(nik2); // positive w
		  int value3 = Integer.parseInt(nik3); // negative w
		  int[] tvalue = new int[4]; // initialization of values array
		  tvalue[0]=value;
		  tvalue[1]=value1;
		  tvalue[2]=value3;
		  tvalue[3]=value2;
		  return tvalue;
		
		
	}
	
	public static Double[] othervalues(){
		
		JPanel p = new JPanel();
		  JTextField H = new JTextField(3);
		  JTextField hx = new JTextField(3);
		  JTextField hy = new JTextField(3);
		  JTextField hz = new JTextField(3);
		  JTextField trr = new JTextField(4);
		  p.add(new JLabel("Value of fixed field H : "));
		  p.add(H);
		  p.add(new JLabel("Value of h_x :"));
		  p.add(hx);
		  p.add(new JLabel("Value of h_y : "));
		  p.add(hy);
		  p.add(new JLabel("Value of h_z : "));
		  p.add(hz);
		  p.add(new JLabel("Transition rate W : "));
		  p.add(trr);
		  
		  
		  JOptionPane.showConfirmDialog(null, p, "Set of variables for the field : ", JOptionPane.OK_CANCEL_OPTION);
		  String nik=H.getText();
		  String nik1=hx.getText();
		  String nik2=hy.getText();
		  String nik3=hz.getText();
		  String nik4=trr.getText();
		  Double value =Double.parseDouble(nik);
		  Double value1 =Double.parseDouble(nik1);
		  Double value2 = Double.parseDouble(nik2);
		  Double value3 = Double.parseDouble(nik3);
		  Double value4 = Double.parseDouble(nik4);
		  Double[] tvalue = new Double[5];
		  tvalue[0]=value;
		  tvalue[1]=value1;
		  tvalue[2]=value2;
		  tvalue[3]=value3;
		  tvalue[4]=value4;
		return tvalue;
		
		
	}
	
	public static Double[] fieldval(){
		JPanel p = new JPanel();
		  JTextField hp = new JTextField(3);
		  JTextField hm = new JTextField(3);
		  JTextField w = new JTextField(3);
		  
		  p.add(new JLabel("Value of fixed field H (negative terminal) : "));
		  p.add(hp);
		  p.add(new JLabel("Value of fixed H (positive terminal):"));
		  p.add(hm);
		  p.add(new JLabel("Value of frequency w : "));
		  p.add(w);
		  
		  
		  
		  JOptionPane.showConfirmDialog(null, p, "Set of variables for the fieldswept : ", JOptionPane.OK_CANCEL_OPTION);
		  String nik=hp.getText();
		  String nik1=hm.getText();
		  String nik2=w.getText();
		  
		  Double value =Double.parseDouble(nik);
		  Double value1 =Double.parseDouble(nik1);
		  Double value2 = Double.parseDouble(nik2);
		  
		  Double[] fieldvalu = new Double[3];
		  fieldvalu[0]=value1;
		  fieldvalu[1]=value;
		  fieldvalu[2]=value2;
		  
		return fieldvalu;
		
		
	}
	
	
}
