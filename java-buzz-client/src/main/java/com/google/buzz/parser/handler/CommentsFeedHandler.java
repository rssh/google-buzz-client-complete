package com.google.buzz.parser.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.google.buzz.model.BuzzCommentsFeed;

/**
 * Handler for xml element: <b>Comments</b>
 * 
 * @author roberto.estivill
 */
public class CommentsFeedHandler
    extends BaseHandler
{
    /**
     * XML elements handled by this handler
     */
    private static final String LINK = "link";
    private static final String TITLE = "title";
    private static final String TYPE = "type";
    private static final String ID = "id";
    private static final String GENERATOR = "generator";
    private static final String GENERATOR_URI = "uri";
    private static final String ENTRY = "entry";

    /**
     * Position flags
     */
    private boolean insideTitle = false;
    private boolean insideId = false;
    private boolean insideGenerator = false;

    /**
     * Children handlers
     */
    private LinkHandler linkHandler;
    private CommentHandler commentHandler;

    /**
     * Object to return
     */
    private BuzzCommentsFeed commentsFeed;

    /**
     * Constructor method to create a root handler.
     * 
     * @param parentHandler handler
     */
    public CommentsFeedHandler( XMLReader xmlReader )
    {
        super( xmlReader );
        commentsFeed = new BuzzCommentsFeed();
    }

    /**
     * Constructor method to create a child handler.
     * 
     * @param parentHandler handler
     */
    public CommentsFeedHandler( BaseHandler aParent )
    {
        super( aParent );
        commentsFeed = new BuzzCommentsFeed();
    }

    /**
     * @return the created feed object
     */
    public BuzzCommentsFeed getFeed()
    {
        return commentsFeed;
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
                commentsFeed.setTitleType( titleType );
            }
            insideTitle = true;
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
                commentsFeed.setGeneratorUri( generatorUri );
            }
        }
        else if ( ENTRY.equals( name ) )
        {
            commentHandler = new CommentHandler( this );
            commentHandler.startHandlingEvents();
            commentHandler.startElement( uri, name, qName, attributes );
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
            commentsFeed.getLinks().add( linkHandler.getBuzzLink() );
        }
        else if ( TITLE.equals( name ) )
        {
            insideTitle = false;
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
            commentsFeed.getComments().add( commentHandler.getBuzzComment() );
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
            commentsFeed.setTitle( content );
        }
        else if ( insideId )
        {
            commentsFeed.setId( content );
        }
        else if ( insideGenerator )
        {
            commentsFeed.setGenerator( content );
        }
    }
}
