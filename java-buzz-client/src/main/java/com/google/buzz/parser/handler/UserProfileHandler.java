package com.google.buzz.parser.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.google.buzz.model.BuzzUserProfile;

/**
 * Handler for element: <b>User Profile</b>
 * 
 * @author roberto.estivill
 */
public class UserProfileHandler
    extends BaseHandler
{
    /**
     * XML elements handled by this handler
     */
    private static final String ENTRY = "entry";
    private static final String ID = "id";
    private static final String DISPLAY_NAME = "displayName";
    private static final String PROFILE_URL = "profileUrl";
    private static final String EMAILS = "emails";
    private static final String URLS = "urls";
    private static final String PHOTOS = "photos";

    /**
     * Position flags
     */
    private boolean insideId = false;
    private boolean insideDisplayName = false;
    private boolean insideProfileUrl = false;
    private UrlHandler urlHandler;
    private EmailHandler emailHandler;
    private PhotoHandler photoHandler;

    /**
     * Object to return
     */
    private BuzzUserProfile profile;

    /**
     * Constructor method to create a root handler.
     * 
     * @param parentHandler handler
     */
    public UserProfileHandler( XMLReader xmlReader )
    {
        super( xmlReader );
    }

    /**
     * Constructor method to create a child handler.
     * 
     * @param parentHandler handler
     */
    public UserProfileHandler( BaseHandler aParent )
    {
        super( aParent );
        profile = new BuzzUserProfile();
    }

    /**
     * @return the profile object
     */
    public BuzzUserProfile getProfile()
    {
        return profile;
    }

    /**
     * Method to be called every time an xml element starts
     */
    public void startElement( String uri, String name, String qName, Attributes attributes )
        throws SAXException
    {
        if ( ENTRY.equals( name ) )
        {
            profile = new BuzzUserProfile();
        }
        else if ( ID.equals( name ) )
        {
            insideId = true;
        }
        else if ( DISPLAY_NAME.equals( name ) )
        {
            insideDisplayName = true;
        }
        else if ( PROFILE_URL.equals( name ) )
        {
            insideProfileUrl = true;
        }
        else if ( EMAILS.equals( name ) )
        {
            emailHandler = new EmailHandler( this );
            emailHandler.startHandlingEvents();
            emailHandler.startElement( uri, name, qName, attributes );
        }
        else if ( URLS.equals( name ) )
        {
            urlHandler = new UrlHandler( this );
            urlHandler.startHandlingEvents();
            urlHandler.startElement( uri, name, qName, attributes );
        }
        else if ( PHOTOS.equals( name ) )
        {
            photoHandler = new PhotoHandler( this );
            photoHandler.startHandlingEvents();
            photoHandler.startElement( uri, name, qName, attributes );
        }
    }

    /**
     * Method to be called every time an xml element ends
     */
    public void endElement( String uri, String name, String qName )
        throws SAXException
    {
        if ( ENTRY.equals( name ) && parentHandler != null )
        {
            stopHandlingEvents();
            parentHandler.endElement( uri, name, qName );
        }
        if ( ID.equals( name ) )
        {
            insideId = false;
        }
        else if ( DISPLAY_NAME.equals( name ) )
        {
            insideDisplayName = false;
        }
        else if ( PROFILE_URL.equals( name ) )
        {
            insideProfileUrl = false;
        }
        else if ( EMAILS.equals( name ) )
        {
            profile.getEmails().add( emailHandler.getBuzzEmail() );
        }
        else if ( URLS.equals( name ) )
        {
            profile.getUrls().add( urlHandler.getBuzzUrl() );
        }
        else if ( PHOTOS.equals( name ) )
        {
            profile.getPhotos().add( photoHandler.getBuzzPhoto() );
        }
    }

    /**
     * Method to be called between the beginning and the end of the xml elements.
     */
    public void characters( char[] ch, int start, int length )
        throws SAXException
    {
        String content = ( new String( ch ).substring( start, start + length ) );
        if ( insideId )
        {
            profile.setId( content );
        }
        else if ( insideDisplayName )
        {
            profile.setName( content );
        }
        else if ( insideProfileUrl )
        {
            profile.setProfileUrl( content );
        }
    }
}
