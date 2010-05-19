package com.google.buzz.parser.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.google.buzz.model.BuzzFeedEntry;

/**
 * Handler for xml element: <b>Feed Entry</b>
 * 
 * @author roberto.estivill
 */
public class FeedEntryHandler
    extends BaseHandler
{
    /**
     * XML elements handled by this handler
     */
    private static final String ENTRY = "entry";
    private static final String TITLE = "title";
    private static final String PUBLISHED = "published";
    private static final String UPDATED = "updated";
    private static final String ID = "id";
    private static final String LINK = "link";
    private static final String AUTHOR = "author";
    private static final String CONTENT = "content";
    private static final String ACTIVITY_VERB = "activity:verb";
    private static final String ACTIVITY_OBJECT = "activity:object";
    private static final String CROSSPOST_SOURCE = "crosspost:source";
    private static final String SOURCE = "source";
    private static final String ACTIVITY_SERVICE = "activity:service";
    private static final String BUZZ_VISIBILITY = "buzz:visibility";
    private static final String IN_REPLY_TO = "thr:in-reply-to";

    /**
     * Position flags
     */
    private boolean insideTitle = false;
    private boolean insidePublished = false;
    private boolean insideUpdated = false;
    private boolean insideId = false;
    private boolean insideActivityVerb = false;
    private boolean insideCrosspostSource = false;
    private boolean insideSource = false;
    private boolean insideActivityService = false;

    /**
     * Children handlers
     */
    private LinkHandler linkHandler;
    private AuthorHandler authorHandler;
    private ContentHandler contentHandler;
    private ActivityHandler activityHandler;
    private VisibilityHandler visibilityHandler;
    private ReplyHandler replyHandler;

    /**
     * Object to return
     */
    private BuzzFeedEntry entry;

    /**
     * Constructor method to create a child handler.
     * 
     * @param parentHandler handler
     */
    public FeedEntryHandler( BaseHandler aParent )
    {
        super( aParent );
        entry = new BuzzFeedEntry();
    }

    /**
     * Constructor method to create a root handler.
     * 
     * @param parentHandler handler
     */
    public FeedEntryHandler( XMLReader xmlReader )
    {
        super( xmlReader );
        entry = new BuzzFeedEntry();
    }

    /**
     * @return the created entry object
     */
    public BuzzFeedEntry getBuzzFeedEntry()
    {
        return entry;
    }

    /**
     * Method to be called every time an xml element starts
     */
    public void startElement( String uri, String name, String qName, Attributes attributes )
        throws SAXException
    {
        if ( TITLE.equals( name ) )
        {
            insideTitle = true;
        }
        else if ( PUBLISHED.equals( name ) )
        {
            insidePublished = true;
        }
        else if ( UPDATED.equals( name ) )
        {
            insideUpdated = true;
        }
        else if ( ID.equals( name ) )
        {
            insideId = true;
        }
        else if ( LINK.equals( name ) )
        {
            linkHandler = new LinkHandler( this );
            linkHandler.startHandlingEvents();
            linkHandler.startElement( uri, name, qName, attributes );
        }
        else if ( AUTHOR.equals( name ) )
        {
            authorHandler = new AuthorHandler( this );
            authorHandler.startHandlingEvents();
            authorHandler.startElement( uri, name, qName, attributes );
        }
        else if ( CONTENT.equals( name ) )
        {
            contentHandler = new ContentHandler( this );
            contentHandler.startHandlingEvents();
            contentHandler.startElement( uri, name, qName, attributes );
        }
        else if ( ACTIVITY_VERB.equals( qName ) )
        {
            insideActivityVerb = true;
        }
        else if ( CROSSPOST_SOURCE.equals( qName ) )
        {
            insideCrosspostSource = true;
        }
        else if ( SOURCE.equals( name ) )
        {
            insideSource = true;
        }
        else if ( ACTIVITY_SERVICE.equals( qName ) )
        {
            insideActivityService = true;
        }
        else if ( BUZZ_VISIBILITY.equals( qName ) )
        {
            visibilityHandler = new VisibilityHandler( this );
            visibilityHandler.startHandlingEvents();
            visibilityHandler.startElement( uri, name, qName, attributes );
        }
        else if ( ACTIVITY_OBJECT.equals( qName ) )
        {
            activityHandler = new ActivityHandler( this );
            activityHandler.startHandlingEvents();
            activityHandler.startElement( uri, name, qName, attributes );
        }
        else if ( IN_REPLY_TO.equals( qName ) )
        {
            replyHandler = new ReplyHandler( this );
            replyHandler.startHandlingEvents();
            replyHandler.startElement( uri, name, qName, attributes );
        }
    }

    /**
     * Method to be called every time an xml element ends
     */
    public void endElement( String uri, String name, String qName )
        throws SAXException
    {
        if ( TITLE.equals( name ) )
        {
            insideTitle = false;
        }
        else if ( PUBLISHED.equals( name ) )
        {
            insidePublished = false;
        }
        else if ( UPDATED.equals( name ) )
        {
            insideUpdated = false;
        }
        else if ( ID.equals( name ) )
        {
            insideId = false;
        }
        else if ( AUTHOR.equals( name ) )
        {
            entry.setAuthor( authorHandler.getBuzzAuthor() );
        }
        else if ( CONTENT.equals( name ) )
        {
            entry.setContent( contentHandler.getBuzzContent() );
        }
        else if ( ACTIVITY_VERB.equals( qName ) )
        {
            insideActivityVerb = false;
        }
        else if ( CROSSPOST_SOURCE.equals( qName ) )
        {
            insideCrosspostSource = false;
        }
        else if ( SOURCE.equals( name ) )
        {
            insideSource = false;
        }
        else if ( ACTIVITY_SERVICE.equals( qName ) )
        {
            insideActivityService = false;
        }
        else if ( LINK.equals( name ) )
        {
            entry.getLinks().add( linkHandler.getBuzzLink() );
        }
        else if ( ENTRY.equals( name ) && parentHandler != null )
        {
            stopHandlingEvents();
            parentHandler.endElement( uri, name, qName );
        }
        else if ( BUZZ_VISIBILITY.equals( qName ) )
        {
            entry.setVisibility( visibilityHandler.getBuzzAclEntries() );
        }
        else if ( ACTIVITY_OBJECT.equals( qName ) )
        {
            entry.setActivity( activityHandler.getBuzzActivity() );
        }
        else if ( IN_REPLY_TO.equals( qName ) )
        {
            entry.setReply( replyHandler.getBuzzReply() );
        }
    }

    /**
     * Method to be called between the beginning and the end of the xml elements.
     */
    public void characters( char[] ch, int start, int length )
        throws SAXException
    {
        String content = ( new String( ch ).substring( start, start + length ) );
        if ( insideCrosspostSource && insideId )
        {
            entry.setCrosspostSourceId( content );
        }
        else if ( insideSource && insideActivityService && insideTitle )
        {
            entry.setSourceActivityTitle( content );
        }
        else if ( insideTitle )
        {
            entry.setTitle( content );
        }
        else if ( insidePublished )
        {
            entry.setPublished( parseDate( content ) );
        }
        else if ( insideUpdated )
        {
            entry.setUpdated( parseDate( content ) );
        }
        else if ( insideId )
        {
            entry.setId( content );
        }
        else if ( insideActivityVerb )
        {
            entry.setActivityVerb( content );
        }
    }

    /**
     * Method to pase date fields.
     * 
     * @param date to parse
     * @return the date object
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
