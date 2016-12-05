import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.autana.crm.security.devices.SecurityUtils;
import com.dynamicdroides.db.virgendelcarmen.AlumnoBean;
import com.dynamicdroides.virgendelcarmen.manager.VirgenDelCarmenManager;


public class PWDGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String pass=SecurityUtils.getMd5Data("Montesori2014-");
		System.out.println(""+pass);

		GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
		//calendar.setFirstDayOfWeek(Calendar.MONDAY);
		
		
	}

}
