import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import com.dynamicdroides.services.security.devices.SecurityUtils;
import com.dynamicdroides.virgendelcarmen.manager.VirgenDelCarmenManager;



public class PWDGenerator {
	public static String getDefaultCharEncoding(){
        byte [] bArray = {'w'};
        InputStream is = new ByteArrayInputStream(bArray);
        InputStreamReader reader = new InputStreamReader(is);
        String defaultCharacterEncoding = reader.getEncoding();
        return defaultCharacterEncoding;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

		String pass=SecurityUtils.getMd5Data("1234");
		System.out.println(""+pass);
	}

}
