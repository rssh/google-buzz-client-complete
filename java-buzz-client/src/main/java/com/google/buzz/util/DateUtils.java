package com.google.buzz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date utilities class.
 * 
 * @author roberto.estivill
 */
public class DateUtils
{
    /**
     * Format used by google buzz feeds
     */
    private static final String FORMAT = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'";

    /**
     * Method to format date fields.
     * 
     * @param date to format
     * @return the string representation of the date
     */
    public static String format( Date date )
    {
        SimpleDateFormat format = new SimpleDateFormat( FORMAT );
        String dateString = format.format( date );
        return dateString;
    }

    /**
     * Method to pase date fields.
     * 
     * @param date to parse
     * @return the date object
     */
    public static Date parseDate( String date )
    {
        SimpleDateFormat format = new SimpleDateFormat( FORMAT );
        Date dateObj = null;
        try
        {
            dateObj = format.parse( date );
        }
        catch ( ParseException e )
        {
            return null;
        }
        return dateObj;
    }
}
