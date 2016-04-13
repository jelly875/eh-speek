package zhongcai.cxl.com.ehspeek.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenjun on 16/4/13.
 */
public class FormatDate {

    public static final int ONE_DAY_MILLIS = 86400000;

    public static final int TODAY = 0;
    public static final int TOMORROW = 1;
    public static final int YESTADAY = -1;

    public static String getDateStr(Date relativeDate){
        Date currentDate = new Date();
        String filterTime = getFilterTime(relativeDate, "HH:mm");
        int tag = judgeDate(currentDate,relativeDate);
        switch(tag){
            case TODAY:{
                return "今天 "+filterTime;
            }
            case TOMORROW:{
                return "明天 "+filterTime;
            }
            case YESTADAY:{
                return "昨天 "+filterTime;
            }
            default :return getFilterTime(relativeDate,null);

        }
    }

    private static String getFilterTime(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern != null?pattern:"yyyy.MM.dd HH:mm");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    private static int judgeDate(Date currentDate,Date relativeDate){
        if(relativeDate == null){
            relativeDate = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr1 = sdf.format(relativeDate);//用于将历史日期定于0点；
        try {
            relativeDate = sdf.parse(dateStr1);
            long minus = currentDate.getTime() - relativeDate.getTime();
            if(minus >= 0){
                if(minus < ONE_DAY_MILLIS) {
                    return TODAY;
                }else if(minus >= ONE_DAY_MILLIS && minus < ONE_DAY_MILLIS*2){
                    return YESTADAY;
                }
            }else if(minus < 0 ){
                String dateStr2 = sdf.format(currentDate);
                Date d = sdf.parse(dateStr2);
                long remainder = ONE_DAY_MILLIS-(currentDate.getTime()-d.getTime());
                if(minus > -(remainder+ONE_DAY_MILLIS)){
                    return TOMORROW;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return -2;
    }
}
