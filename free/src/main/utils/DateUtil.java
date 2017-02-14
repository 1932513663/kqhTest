package utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil
{

    public DateUtil()
    {
    }

    public static Date checkDate(String dateStr)
    {
        if(dateStr == null)
            return null;
        if("".equals(dateStr))
            return null;
        try
        {
            Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse(dateStr);
            return date;
        }
        catch(ParseException e)
        {
            return null;
        }
    }

    public static Date StrToDayStartDate(String dateString)
    {
        if(dateString == null || "".equals(dateString))
            return null;
        try
        {
            String date = formatDate(parseDate(dateString), "yyyy-MM-dd");
            date = (new StringBuilder(String.valueOf(date))).append(" 00:00:00").toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return dateFormat.parse(date);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public static String StrToDayStartStr(String dateString)
    {
        if(dateString == null || "".equals(dateString))
            return null;
        try
        {
            String date = formatDate(parseDate(dateString), "yyyy-MM-dd");
            date = (new StringBuilder(String.valueOf(date))).append(" 00:00:00").toString();
            return date;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public static Date StrToDayEndDate(String dateString)
    {
        if(dateString == null || "".equals(dateString))
            return null;
        try
        {
            String date = formatDate(parseDate(dateString), "yyyy-MM-dd");
            date = (new StringBuilder(String.valueOf(date))).append(" 23:59:59").toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return dateFormat.parse(date);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public static String StrToDayEndStr(String dateString)
    {
        if(dateString == null || "".equals(dateString))
            return null;
        try
        {
            String date = formatDate(parseDate(dateString), "yyyy-MM-dd");
            date = (new StringBuilder(String.valueOf(date))).append(" 23:59:59").toString();
            return date;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public static String dateToStr(Date date)
    {
        if(date != null)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = dateFormat.format(date);
            return dateStr;
        } else
        {
            return null;
        }
    }

    public static String customDateToStr(Date date, String pattern)
    {
        if(date != null)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            String dateStr = dateFormat.format(date);
            return dateStr;
        } else
        {
            return null;
        }
    }

    public static Date getStandardDate(String source, String pattern)
    {
        if(source == null || source.trim().length() == 0)
            return null;
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        try
        {
            return sf.parse(source);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static Date addDate(Date date, int day)
    {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + (long)day * 24L * 3600L * 1000L);
        return c.getTime();
    }

    public static long getMillis(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    public static String getDateStr()
    {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }

    public static String getTimeStr()
    {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hhmmss");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }

    public static Date getSysDate()
    {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date getFirstDateOfMonth()
    {
        Calendar ca = Calendar.getInstance();
        ca.setTime(getSysDate());
        ca.set(5, 1);
        return ca.getTime();
    }

    public static Date preMonthFirstDay()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(2, -1);
        cal.set(5, cal.getActualMinimum(5));
        return StrToDayStartDate(sdf.format(cal.getTime()));
    }

    public static Date stringToDate(String str_date)
    {
        if(str_date == null || str_date.equals(""))
        {
            System.out.println("\u8F6C\u6362\u7684\u65E5\u671F\u5B57\u7B26\u4E32\u4E3A\u7A7A!");
            return null;
        }
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        try
        {
            dt = form.parse(str_date);
        }
        catch(ParseException e)
        {
            System.out.println((new StringBuilder("unparseable using ")).append(form).toString());
        }
        return dt;
    }

    public static Date getAnyDateBy(int field, int amount)
    {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return c.getTime();
    }

    public static int getYearTimeOfDate(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(1);
    }

    public static int getMonthTimeOfDate(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(2);
    }

    public static Date parseDate(Date datetime)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        if(datetime == null)
            return null;
        try
        {
            return formatter.parse(formatter.format(datetime));
        }
        catch(ParseException e)
        {
            return null;
        }
    }

    public static String getFormatDate(String type)
    {
        return formatDate(now(), type);
    }

    public static String getDefaultDate()
    {
        return formatDate(now(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getFormatDate()
    {
        return formatDate(now(), "yyyy-MM-dd");
    }

    public static String getFormatDate1()
    {
        return formatDate(now(), "yyyyMM");
    }

    public static String formatDate(Date date, String pattern)
    {
        if(date == null)
            return "";
        if(pattern == null)
            pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    public static Date formatDate(String dateStr, String pattern)
    {
        if(pattern == null || "".equals(pattern))
            pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date d = null;
        try
        {
            if(dateStr == null)
                d = null;
            else
                d = sdf.parse(dateStr);
        }
        catch(ParseException e)
        {
            return null;
        }
        return d;
    }

    public static String formatDateTime(Date date)
    {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDateTime()
    {
        return formatDate(now(), "yyyy-MM-dd HH:mm:ss");
    }

    public static Date now()
    {
        return new Date();
    }

    public static Date parseDateTime(String datetime)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(datetime == null || datetime.equals(""))
            return null;
        try
        {
            return formatter.parse(datetime);
        }
        catch(ParseException e)
        {
            return parseDate(datetime);
        }
    }

    public static Date parseDate(String date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        if(date == null || date.equals(""))
            return null;
        try
        {
            return formatter.parse(date);
        }
        catch(ParseException e)
        {
            return null;
        }
    }

    public static Date parseDate2(String date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        if(date == null || date.equals(""))
            return null;
        try
        {
            return formatter.parse(date);
        }
        catch(ParseException e)
        {
            return null;
        }
    }

    public static Date parseDate(Date datetime, String str)
    {
        if(str == null || "".equals(str))
            str = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(str);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        if(datetime == null)
            return null;
        try
        {
            return formatter.parse(formatter.format(datetime));
        }
        catch(ParseException e)
        {
            return null;
        }
    }

    public static String formatDate(Object o)
    {
        if(o == null)
            return "";
        if(o.getClass() == String.class)
            return formatDate((String)o);
        if(o.getClass() == Date.class)
            return formatDate((Date)o);
        if(o.getClass() == Timestamp.class)
            return formatDate(new Date(((Timestamp)o).getTime()));
        else
            return o.toString();
    }

    public static Date add(Date date, int field, int amount)
    {
        if(date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, amount);
        return cal.getTime();
    }

    public static Date addMilliSecond(Date date, int amount)
    {
        return add(date, 14, amount);
    }

    public static Date addSecond(Date date, int amount)
    {
        return add(date, 13, amount);
    }

    public static Date addMiunte(Date date, int amount)
    {
        return add(date, 12, amount);
    }

    public static Date addHour(Date date, int amount)
    {
        return add(date, 10, amount);
    }

    public static Date addDay(Date date, int amount)
    {
        return add(date, 5, amount);
    }

    public static Date addMonth(Date date, int amount)
    {
        return add(date, 2, amount);
    }

    public static Date addYear(Date date, int amount)
    {
        return add(date, 1, amount);
    }

    public static Date getDateTime()
    {
        return parseDateTime(formatDate(now(), "yyyy-MM-dd HH:mm:ss"));
    }

    public static Date getDateTime(Date d, String pattern)
    {
        if(pattern == null)
            pattern = "yyyy-MM-dd HH:mm:ss";
        return parseDateTime(formatDate(d, pattern));
    }

    public static String formatDate2(String Date)
    {
        if(Date != null && !Date.equals("") && Date.length() == 8 && !isNaN(Date))
            Date = (new StringBuilder(String.valueOf(Date.substring(0, 4)))).append("-").append(Date.substring(4, 6)).append("-").append(Date.substring(6, 8)).toString();
        else
        if(Date != null && !Date.equals("") && Date.length() == 14 && !isNaN(Date))
            Date = (new StringBuilder(String.valueOf(Date.substring(0, 4)))).append("-").append(Date.substring(4, 6)).append("-").append(Date.substring(6, 8)).append(" ").append(Date.substring(8, 10)).append(":").append(Date.substring(10, 12)).append(":").append(Date.substring(12, 14)).toString();
        return Date;
    }

    public static boolean isNaN(String num)
    {
        String number = "1234567890";
        for(int i = 0; i < num.length(); i++)
            if(number.indexOf(num.charAt(i)) == -1)
                return true;

        return false;
    }

    public static String getPart(String dateString, String part)
    {
        String result = "";
        String parts[] = dateString.split("-| |:");
        if(parts.length >= 0 && part.equalsIgnoreCase("year"))
            result = parts[0];
        if(parts.length >= 1 && part.equalsIgnoreCase("month"))
            result = parts[1];
        if(part.equalsIgnoreCase("week"))
            try
            {
                Calendar c = Calendar.getInstance();
                c.setTime(parseDate(dateString));
                result = String.valueOf(c.get(7));
            }
            catch(Exception e)
            {
                return "0";
            }
        if(parts.length >= 2 && part.equalsIgnoreCase("day"))
            result = parts[2];
        if(parts.length >= 3 && part.equalsIgnoreCase("Hour"))
            result = parts[3];
        if(parts.length >= 4 && part.equalsIgnoreCase("minute"))
            result = parts[4];
        if(parts.length >= 5 && part.equalsIgnoreCase("second"))
            result = parts[5];
        return result;
    }

    public static String formateToNumber(String dateString)
    {
        String result = null;
        if(dateString == null || dateString.trim().equals(""))
            result = "";
        else
            result = dateString.replaceAll("[- :]", "");
        return result;
    }

    public static boolean isTrueTime(String time)
    {
        boolean b = true;
        if(isNaN(time))
            return false;
        int length = time.length();
        if(length == 12)
            b = isTrueTimeExceptSecond(time);
        else
        if(length == 14)
        {
            boolean bSecond = true;
            int second = Integer.parseInt(time.substring(12, 14));
            if(second < 0 || second > 59)
                bSecond = false;
            b = isTrueTimeExceptSecond(time) && bSecond;
        } else
        {
            b = false;
        }
        return b;
    }

    private static boolean isTrueTimeExceptSecond(String time)
    {
        int year = Integer.parseInt(time.substring(0, 4));
        int month = Integer.parseInt(time.substring(4, 6));
        int day = Integer.parseInt(time.substring(6, 8));
        int hour = Integer.parseInt(time.substring(8, 10));
        int minute = Integer.parseInt(time.substring(10, 12));
        if(month < 1 || month > 12 || day < 1 || day > 31)
            return false;
        if(hour < 0 || hour > 23 || minute < 0 || minute > 59)
            return false;
        if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            return checkDay(true, month, day);
        else
            return checkDay(false, month, day);
    }

    private static boolean checkDay(boolean isLeapYear, int month, int day)
    {
        boolean b = true;
        int count = 28;
        if(isLeapYear)
            count = 29;
        switch(month)
        {
        case 3: // '\003'
        case 5: // '\005'
        case 7: // '\007'
        case 8: // '\b'
        case 10: // '\n'
        default:
            break;

        case 2: // '\002'
            if(day > count)
                b = false;
            break;

        case 4: // '\004'
            if(day > 30)
                b = false;
            break;

        case 6: // '\006'
            if(day > 30)
                b = false;
            break;

        case 9: // '\t'
            if(day > 30)
                b = false;
            break;

        case 11: // '\013'
            if(day > 30)
                b = false;
            break;
        }
        return b;
    }

    public static void main(String args[])
    {
        Date now = now();
        String foreEightHour = formateToNumber(formatDateTime(addHour(now, 48)));
        System.out.println(foreEightHour);
        System.out.println(isTrueTimeExceptSecond(formateToNumber(foreEightHour)));
    }

    @SuppressWarnings("deprecation")
	public static String getCronExpression(String now, String dtype)
    {
        StringBuffer cron = new StringBuffer();
        if(now.equals("yes"))
        {
            SimpleDateFormat fss = new SimpleDateFormat("ss");
            SimpleDateFormat fmi = new SimpleDateFormat("mm");
            SimpleDateFormat fhh = new SimpleDateFormat("HH");
            SimpleDateFormat fdate = new SimpleDateFormat("dd");
            SimpleDateFormat fmm = new SimpleDateFormat("MM");
            SimpleDateFormat fyy = new SimpleDateFormat("yyyy");
            String arr[] = dtype.split("_");
            String type = arr[0];
            int svalue = Integer.parseInt(arr[1]);
            Date nowdate = new Date();
            Date sdate = null;
            if(type.equals("second"))
            {
                sdate = nowdate;
                sdate.setTime(sdate.getTime() + (long)(svalue * 1000));
                cron.append(fss.format(sdate)).append(" ");
            } else
            {
                sdate = nowdate;
                sdate.setTime(sdate.getTime() + 3000L);
                cron.append(fss.format(sdate)).append(" ");
            }
            if(type.equals("minute"))
            {
                sdate = nowdate;
                sdate.setTime(sdate.getTime() + (long)(svalue * 60 * 1000));
                cron.append(fmi.format(sdate)).append(" ");
            } else
            {
                cron.append(fmi.format(nowdate)).append(" ");
            }
            if(type.equals("hour"))
            {
                sdate = nowdate;
                sdate.setTime(sdate.getTime() + (long)(svalue * 60 * 60 * 1000));
                cron.append(fhh.format(sdate)).append(" ");
            } else
            {
                cron.append(fhh.format(nowdate)).append(" ");
            }
            if(type.equals("date"))
            {
                cron.append(fdate.format(nowdate)).append(",");
                cron.append(fdate.format(addDate(nowdate, svalue))).append(" ");
            } else
            if(type.equals("seriesDate"))
            {
                cron.append(fdate.format(nowdate)).append(",");
                svalue--;
                for(int i = 1; i <= svalue; i++)
                    if(i < svalue)
                        cron.append(fdate.format(addDate(nowdate, i))).append(",");
                    else
                        cron.append(fdate.format(addDate(nowdate, i))).append(" ");

            } else
            {
                cron.append(fdate.format(nowdate)).append(" ");
            }
            cron.append(fmm.format(nowdate)).append(" ? ").append(fyy.format(nowdate));
        } else
        {
            String arr[] = dtype.split("_");
            String type = arr[0];
            if(type.equals("week"))
                cron.append("0 0 9 ? * mon");
            else
            if(type.equals("month"))
                cron.append("0 0 9 1 * ?");
            else
            if(type.equals("random"))
                try
                {
                    SimpleDateFormat formatSeconds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    SimpleDateFormat formatMinutes = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date dateRemindDate;
                    if(arr[1].toString().length() == 19)
                        dateRemindDate = formatSeconds.parse(arr[1].toString());
                    else
                        dateRemindDate = formatMinutes.parse(arr[1].toString());
                    int seconds = dateRemindDate.getSeconds();
                    int year = getYearTimeOfDate(dateRemindDate);
                    int month = getMonthTimeOfDate(dateRemindDate) + 1;
                    int day = dateRemindDate.getDate();
                    int hours = dateRemindDate.getHours();
                    int minutes = dateRemindDate.getMinutes();
                    cron.append(seconds).append(" ").append(minutes).append(" ").append(hours).append(" ").append(day).append(" ").append(month).append(" ? ").append(year);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            else
            if(type.equals("cronExpression"))
                return arr[1].toString();
        }
        return cron.toString();
    }
}