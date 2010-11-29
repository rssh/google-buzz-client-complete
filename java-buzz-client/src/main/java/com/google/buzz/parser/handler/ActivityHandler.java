package com.google.buzz.parser.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import com.google.buzz.model.BuzzActivity;

/**
 * Handler for element: <b>Activity</b>
 * 
 * @author roberto.estivill
 */
public class ActivityHandler
    extends BaseHandler
{
    /**
     * XML elements handled by this handler
     */
    private static final String ACTIVITY_OBJECT = "activity:object";
    private static final String ACTIVITY_OBJECT_TYPE = "activity:object-type";
    private static final String CONTENT = "content";
    private static final String LINK = "link";

    /**
     * Position flags
     */
    private boolean insideActivityObjectType = false;

    /**
     * Object to return
     */
    private BuzzActivity activity;

    /**
     * Children handlers
     */
    private ContentHandler contentHandler;
    private LinkHandler linkHandler;

    /**
     * Constructor method to create a child handler.
     * 
     * @param parentHandler handler
     */
    public ActivityHandler( BaseHandler aParent )
    {
        super( aParent );
        activity = new BuzzActivity();
    }

    /**
     * @return the created activity object
     */
    public BuzzActivity getBuzzActivity()
    {
        return activity;
    }

    /**
     * Method to be called every time an xml element starts
     */
    public void startElement( String uri, String name, String qName, Attributes attributes )
        throws SAXException
    {
        if ( ACTIVITY_OBJECT_TYPE.equals( qName ) )
        {
            insideActivityObjectType = true;
        }
        else if ( CONTENT.equals( name ) )
        {
            contentHandler = new ContentHandler( this );
            contentHandler.startHandlingEvents();
            contentHandler.startElement( uri, name, qName, attributes );
        }
        else if ( LINK.equals( name ) )
        {
            linkHandler = new LinkHandler( this );
            linkHandler.startHandlingEvents();
            linkHandler.startElement( uri, name, qName, attributes );
        }
    }

    /**
     * Method to be called every time an xml element ends
     */
    public void endElement( String uri, String name, String qName )
        throws SAXException
    {
        if ( ACTIVITY_OBJECT.equals( qName ) )
        {
            stopHandlingEvents();
            parentHandler.endElement( uri, name, qName );
        }
        if ( ACTIVITY_OBJECT_TYPE.equals( qName ) )
        {
            insideActivityObjectType = false;
        }
        else if ( CONTENT.equals( name ) )
        {
            activity.setContent( contentHandler.getBuzzContent() );
        }
        else if ( LINK.equals( name ) )
        {
            activity.setLink( linkHandler.getBuzzLink() );
        }
    }

    /**
     * Method to be called between the beginning and the end of the xml elements.
     */
    public void characters( char[] ch, int start, int length )
        throws SAXException
    {
        String content = ( new String( ch ).substring( start, start + length ) );
        if ( insideActivityObjectType )
        {
            activity.setActivityObjectType( content );
        }
    }
}
