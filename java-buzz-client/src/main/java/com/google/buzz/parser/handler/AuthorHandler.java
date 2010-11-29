package com.google.buzz.parser.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import com.google.buzz.model.BuzzAuthor;

/**
 * Handler for element: <b>Author</b>
 * 
 * @author roberto.estivill
 */
public class AuthorHandler
    extends BaseHandler
{
    /**
     * XML elements handled by this handler
     */
    private static final String AUTHOR = "author";
    private static final String POCO_ID = "poco:id";
    private static final String NAME = "name";
    private static final String URI = "uri";
    private static final String LINK = "link";
    private static final String ACTIVITY_OBJECT_TYPE = "activity:object-type";

    /**
     * Position flags
     */
    private boolean insidePocoId = false;
    private boolean insideName = false;
    private boolean insideUri = false;
    private boolean insideActivityObjectType = false;

    /**
     * Children handlers
     */
    private LinkHandler linkHandler;

    /**
     * Object to return
     */
    private BuzzAuthor author;

    /**
     * Constructor method to create a child handler.
     * 
     * @param parentHandler handler
     */
    public AuthorHandler( BaseHandler aParent )
    {
        super( aParent );
        author = new BuzzAuthor();
    }

    /**
     * @return the created author object
     */
    public BuzzAuthor getBuzzAuthor()
    {
        return author;
    }

    /**
     * Method to be called every time an xml element starts
     */
    public void startElement( String uri, String name, String qName, Attributes attributes )
        throws SAXException
    {
        if ( POCO_ID.equals( qName ) )
        {
            insidePocoId = true;
        }
        else if ( NAME.equals( name ) )
        {
            insideName = true;
        }
        else if ( URI.equals( name ) )
        {
            insideUri = true;
        }
        else if ( ACTIVITY_OBJECT_TYPE.equals( qName ) )
        {
            insideActivityObjectType = true;
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
        if ( POCO_ID.equals( qName ) )
        {
            insidePocoId = false;
        }
        else if ( NAME.equals( name ) )
        {
            insideName = false;
        }
        else if ( URI.equals( name ) )
        {
            insideUri = false;
        }
        else if ( ACTIVITY_OBJECT_TYPE.equals( qName ) )
        {
            insideActivityObjectType = false;
        }
        else if ( LINK.equals( name ) )
        {
            author.getLinks().add( linkHandler.getBuzzLink() );
        }
        else if ( AUTHOR.equals( name ) )
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
        if ( insidePocoId )
        {
            author.setId( content );
        }
        else if ( insideName )
        {
            author.setName( content );
        }
        else if ( insideUri )
        {
            author.setUri( content );
        }
        else if ( insideActivityObjectType )
        {
            author.setActivityObjectType( content );
        }
    }
}
