package com.google.buzz.parser.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.google.buzz.exception.BuzzParsingException;
import com.google.buzz.model.BuzzFeed;

/**
 * Handler for xml element: <b>Feed</b>
 * 
 * @author roberto.estivill
 */
public class FeedHandler
    extends BaseHandler
{
    /**
     * XML elements handled by this handler
     */
    private static final String LINK = "link";
    private static final String TITLE = "title";
    private static final String TYPE = "type";
    private static final String UPDATED = "updated";
    private static final String ID = "id";
    private static final String GENERATOR = "generator";
    private static final String GENERATOR_URI = "uri";
    private static final String ENTRY = "entry";

    /**
     * Position flags
     */
    private boolean insideTitle = false;
    private boolean insideUpdated = false;
    private boolean insideId = false;
    private boolean insideGenerator = false;

    /**
     * Children handlers
     */
    private LinkHandler linkHandler;
    private FeedEntryHandler feedEntryHandler;

    /**
     * Object to return
     */
    private BuzzFeed feed;

    /**
     * Constructor method to create a root handler.
     * 
     * @param parentHandler handler
     */
    public FeedHandler( XMLReader xmlReader )
    {
        super( xmlReader );
        feed = new BuzzFeed();
    }

    /**
     * Constructor method to create a child handler.
     * 
     * @param parentHandler handler
     */
    public FeedHandler( BaseHandler aParent )
    {
        super( aParent );
        feed = new BuzzFeed();
    }

    /**
     * @return the created feed object
     */
    public BuzzFeed getFeed()
    {
        return feed;
    }

    /**
     * Method to be called every time an xml element starts
     */
    public void startElement( String uri, String name, String qName, Attributes attributes )
        throws SAXException
    {
        if ( LINK.equals( name ) )
        {
            linkHandler = new LinkHandler( this );
            linkHandler.startHandlingEvents();
            linkHandler.startElement( uri, name, qName, attributes );
        }
        else if ( TITLE.equals( name ) )
        {
            String titleType = attributes.getValue( TYPE );
            if ( titleType != null && !titleType.equals( "" ) )
            {
                feed.setTitleType( titleType );
            }
            insideTitle = true;
        }
        else if ( UPDATED.equals( name ) )
        {
            insideUpdated = true;
        }
        else if ( ID.equals( name ) )
        {
            insideId = true;
        }
        else if ( GENERATOR.equals( name ) )
        {
            insideGenerator = true;
            String generatorUri = attributes.getValue( GENERATOR_URI );
            if ( generatorUri != null && !generatorUri.equals( "" ) )
            {
                feed.setGeneratorUri( generatorUri );
            }
        }
        else if ( ENTRY.equals( name ) )
        {
            feedEntryHandler = new FeedEntryHandler( this );
            feedEntryHandler.startHandlingEvents();
            feedEntryHandler.startElement( uri, name, qName, attributes );
        }
    }

    /**
     * Method to be called every time an xml element ends
     */
    public void endElement( String uri, String name, String qName )
        throws SAXException
    {
        if ( LINK.equals( name ) )
        {
            feed.getLinks().add( linkHandler.getBuzzLink() );
        }
        else if ( TITLE.equals( name ) )
        {
            insideTitle = false;
        }
        else if ( UPDATED.equals( name ) )
        {
            insideUpdated = false;
        }
        else if ( ID.equals( name ) )
        {
            insideId = false;
        }
        else if ( GENERATOR.equals( name ) )
        {
            insideGenerator = false;
        }
        else if ( ENTRY.equals( name ) )
        {
            feed.getEntries().add( feedEntryHandler.getBuzzFeedEntry() );
        }
    }

    /**
     * Method to be called between the beginning and the end of the xml elements.
     */
    public void characters( char[] ch, int start, int length )
        throws SAXException
    {
        String content = ( new String( ch ).substring( start, start + length ) );
        if ( insideTitle )
        {
            feed.setTitle( content );
        }
        else if ( insideUpdated )
        {
            feed.setUpdated( parseDate( content ) );
        }
        else if ( insideId )
        {
            feed.setId( content );
        }
        else if ( insideGenerator )
        {
            feed.setGenerator( content );
        }
    }

    /**
     * Method to pase date fields.
     * 
     * @param date to parse
     * @return the date object
     * @throws BuzzParsingException
     */
    private Date parseDate( String date )
    {
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'" );
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
