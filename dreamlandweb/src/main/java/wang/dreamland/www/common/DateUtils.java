package wang.dreamland.www.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date StringToDate(String dateStr,String formatStr){
        DateFormat dd = new SimpleDateFormat(formatStr);
        Date date = null;
        try{
            date = dd.parse(dateStr);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }

    public static String formatDate(Date date,String format){
        if(date==null){
            return  null;
        }
        SimpleDateFormat formatter =  new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

}
