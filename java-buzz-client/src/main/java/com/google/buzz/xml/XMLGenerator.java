package com.google.buzz.xml;

import com.google.buzz.exception.BuzzValidationException;
import com.google.buzz.model.BuzzContent;
import com.google.buzz.model.BuzzLink;

public class XMLGenerator
{
    /**
     * Generates new post request body.
     * 
     * @param content of the new post
     * @param link (if any)
     * @return the xml representation of the new entry.
     * @throws BuzzValidationException if any required value is missing.
     */
    public static String constructPayload( BuzzContent content, BuzzLink link )
        throws BuzzValidationException
    {
        StringBuilder sb = new StringBuilder();
        sb.append( "<entry xmlns=\"http://www.w3.org/2005/Atom\">" );
        sb.append( constructContent( content ) );
        sb.append( link != null ? constructLink( link ) : "" );
        sb.append( "</entry>" );
        return sb.toString();
    }

    public static String constructActivityIdPayload( String activityId, String annotation ) {
        StringBuilder sb = new StringBuilder();
        sb.append( "<entry xmlns=\"http://www.w3.org/2005/Atom\" ")
          .append("  xmlns:activity=\"http://activitystrea.ms/spec/1.0\">\n");
          sb.append("<buzz:annotation>");
          if(annotation!=null) {
              sb.append(annotation);
          } else {
              sb.append(" ");
          }
          sb.append("</buzz:annotation>\n");
          sb.append("<title>reshare</title>");
        sb.append("<activity:verb>http://activitystrea.ms/schema/1.0/share</activity:verb>");
        sb.append("<activity:object>")
          .append("<id>").append(activityId).append("</id>")
          .append("</activity:object>")
          .append("</entry>");
        return sb.toString();
    }

    /**
     * Generates the Content xml element to be included in the request body
     * 
     * @param link object to write the xml element.
     * @return the xml element.
     * @throws BuzzValidationException if the object is missing any required value.
     */
    private static String constructContent( BuzzContent content )
        throws BuzzValidationException
    {
        if ( content == null || content.getText() == null || content.getText().equals( "" ) )
        {
            throw new BuzzValidationException( "The content is invalid. null or required attributes are empty?" );
        }
        StringBuilder sb = new StringBuilder();
        sb.append( "<content type=\"" );
        sb.append( content.getType() );
        sb.append( "\">" );
        sb.append( content.getText() );
        sb.append( "</content>" );
        return sb.toString();
    }

    /**
     * Generates the Link xml element to be included in the request body.
     * 
     * @param link object to write the xml element
     * @return the xml element
     * @throws BuzzValidationException if the object is missing any required value
     */
    private static String constructLink( BuzzLink link )
        throws BuzzValidationException
    {
        if ( link == null || link.getHref() == null || link.getType() == null || link.getRel() == null
            || link.getHref().equals( "" ) || link.getType().equals( "" ) || link.getRel().equals( "" ) )
        {
            throw new BuzzValidationException( "The content is invalid. null or empty attributes?" );
        }
        StringBuilder sb = new StringBuilder();
        sb.append( "<link rel=\"" );
        sb.append( link.getRel() );
        sb.append( "\" type=\"" );
        sb.append( link.getType() );
        sb.append( "\" href=\"" );
        sb.append( link.getHref() );
        sb.append( "\" />" );
        return sb.toString();
    }
}
