package Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtil {
	public static Date convertToDate(String date){
		SimpleDateFormat simpleDateFormat = null;
		Date dateObj=null;
		try{
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateObj=simpleDateFormat.parse(date);
			return dateObj;
			
		}
		catch(ParseException e){
			return dateObj;
		}
	} 
}
