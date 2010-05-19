package com.google.buzz.parser.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.google.buzz.model.BuzzPhoto;

/**
 * Handler for xml element: <b>Photo</b>
 * 
 * @author roberto.estivill
 */
public class PhotoHandler
    extends BaseHandler
{
    /**
     * XML elements handled by this handler
     */
    private static final String PHOTOS = "photos";
    private static final String VALUE = "value";
    private static final String TYPE = "type";

    /**
     * Position flags
     */
    private boolean insideValue = false;
    private boolean insideType = false;

    /**
     * Object to return
     */
    private BuzzPhoto photo;

    /**
     * Constructor method to create a child handler.
     * 
     * @param parentHandler handler
     */
    public PhotoHandler( BaseHandler aParent )
    {
        super( aParent );
        photo = new BuzzPhoto();
    }

    /**
     * @return the created photo object
     */
    public BuzzPhoto getBuzzPhoto()
    {
        return photo;
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
        else if ( PHOTOS.equals( name ) )
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
            photo.setValue( content );
        }
        else if ( insideType )
        {
            photo.setType( content );
        }
    }
}
