package com.google.buzz.parser.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.google.buzz.model.BuzzEmail;

/**
 * Handler for xml element: <b>Activity</b>
 * 
 * @author roberto.estivill
 */
public class EmailHandler
    extends BaseHandler
{
    /**
     * XML elements handled by this handler
     */
    private static final String EMAILS = "emails";
    private static final String VALUE = "value";
    private static final String TYPE = "type";
    private static final String PRIMARY = "primary";

    /**
     * Position flags
     */
    private boolean insideValue = false;
    private boolean insideType = false;
    private boolean insidePrimary = false;

    /**
     * Object to return
     */
    private BuzzEmail email;

    /**
     * Constructor method to create a child handler.
     * 
     * @param parentHandler handler
     */
    public EmailHandler( BaseHandler aParent )
    {
        super( aParent );
        email = new BuzzEmail();
    }

    /**
     * @return the created email object
     */
    public BuzzEmail getBuzzEmail()
    {
        return email;
    }

    /**
     * Method to be called every time an xml element starts
     */
    public void startElement( String uri, String name, String qName, Attributes attributes )
        throws SAXException
    {
        if ( VALUE.equals( name ) )
        {
            insideValue = true;
        }
        else if ( TYPE.equals( name ) )
        {
            insideType = true;
        }
        else if ( PRIMARY.equals( name ) )
        {
            insidePrimary = true;
        }
    }

    /**
     * Method to be called every time an xml element ends
     */
    public void endElement( String uri, String name, String qName )
        throws SAXException
    {
        if ( VALUE.equals( name ) )
        {
            insideValue = false;
        }
        else if ( TYPE.equals( name ) )
        {
            insideType = false;
        }
        else if ( PRIMARY.equals( name ) )
        {
            insidePrimary = false;
        }
        else if ( EMAILS.equals( name ) )
        {
            stopHandlingEvents();
            parentHandler.endElement( uri, name, qName );
        }
    }

    /**
     * Method to be called between the beginning and the end of the xml elements.
     */
    public void characters( char[] ch, int start, int length )
        throws SAXException
    {
        String content = ( new String( ch ).substring( start, start + length ) );
        if ( insideValue )
        {
            email.setValue( content );
        }
        else if ( insideType )
        {
            email.setType( content );
        }
        else if ( insidePrimary )
        {
            email.setPrimary( Boolean.valueOf( content ) );
        }
    }
}
