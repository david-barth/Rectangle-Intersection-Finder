package appClient;


//Java Language Imports and local imports: 
import java.io.FileReader;
import java.util.ArrayList;
import rectangle.Rectangle;
import algorithmIMPL.IntersectionReporter;


//External Library classes: 
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


public class Application {
	public static void main(String[] args) {
		try {
			String jsonFilePath = args[0]; 
			FileReader jsonReader = new FileReader(jsonFilePath);
			JSONTokener tokener = new JSONTokener(jsonReader); 
			JSONObject parser = new JSONObject(tokener); 
			JSONArray rectangleCoordinates = parser.getJSONArray("rects");
			ArrayList<Rectangle> rectangles = createRectangles(rectangleCoordinates);
			IntersectionReporter reporter = new IntersectionReporter(rectangles);
			reporter.reportIntersections();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			System.out.println("Done");
		}
	}

	
	private static ArrayList<Rectangle> createRectangles(JSONArray coordinates) {
		ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>(); 
		
		//Potential use for a try-catch block?: 
		for (int i = 0; i < coordinates.length(); i++) {
			JSONObject rectangleInfo = coordinates.getJSONObject(i); 
			int deltaX = rectangleInfo.getInt("delta_x"); 
			int deltaY = rectangleInfo.getInt("delta_y"); 
			int x = rectangleInfo.getInt("x"); 
			int y = rectangleInfo.getInt("y");
			Rectangle rectangle = new Rectangle(deltaX, deltaY, x, y, i + 1); 
			rectangles.add(rectangle); 
		}
		
		return rectangles; 
	}
	
}



/* Notes on how to properly compile code from the commandline in order to allow for file input to be read: 
 * 
 * ---> For some reason class path additions from eclipse does not get accounted for in the powershell compilation launches. 
 * 
 * ---> To amend this, a commandline level compilation is needed with a full specification of the class path as follows in the example for this file: 
 * 
 * 		java -cp C:\Users\dzb10\Programming\Java\Java_MasterClass\intersection.rectangles\libs\*  .\src\appClient\Application.java
 * 
 * 
 * 		Generalized: 
 * 
 * 		java -cp <absolute path to libs folder>\* <relative path to java file>.java  inputs...
 * 
 * 		---> Next priority is to find a way to circumvent this roundabout method of compiling. 
 * 
 * 
 * ---> IMPORTANT:  Note that eclipse runs its own internalized JDK that is separate from the JDK that is installed on the local machine.  
 * 
 * 		---> This means that files imported in eclipse will not be resolved or found if the program is compiled and run from the commandline. 
 * 
 * 		---> eclipse is thus used to create the code and debug within its own internal environment and not within the machine environment. 
 * 
 * 			---> The class path needs to be adjusted manually via commandline if the program is to be run from the commandline. 
 * 
 * 
 * */
 