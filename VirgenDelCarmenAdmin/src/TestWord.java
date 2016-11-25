import java.io.FileNotFoundException;
import java.io.IOException;

import com.dynamicdroides.vdc.services.business.WordExtractor;


public class TestWord {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordExtractor extractor=new WordExtractor();
		
		try {
			extractor.readAndSave("C://produccion//Virgen del Carmen//MARZO 2014.doc");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
