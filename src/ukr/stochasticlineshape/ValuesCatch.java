package ukr.stochasticlineshape;
import java.lang.reflect.*;


public class ValuesCatch {

	/* This class saves all constants into object that can be accessible anywhere*/
	
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
	
}