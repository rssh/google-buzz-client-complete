package com.google.buzz.parser.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.google.buzz.model.BuzzUserProfile;

/**
 * Handler for element: <b>Users Profiles</b>
 * 
 * @author roberto.estivill
 */
public class UsersProfilesHandler
    extends BaseHandler
{
    /**
     * XML elements handled by this handler
     */
    private static final String ENTRY = "entry";
    private UserProfileHandler userProfileHandler;

    /**
     * Object to return
     */
    private List<BuzzUserProfile> followers;

    /**
     * Constructor method to create a root handler.
     * 
     * @param parentHandler handler
     */
    public UsersProfilesHandler( XMLReader xmlReader )
    {
        super( xmlReader );
        followers = new ArrayList<BuzzUserProfile>( 0 );
    }

    /**
     * @return the created profiles objects
     */
    public List<BuzzUserProfile> getBuzzUserFollowers()
    {
        return followers;
    }

    /**
     * Method to be called every time an xml element starts
     */
    public void startElement( String uri, String name, String qName, Attributes attributes )
        throws SAXException
    {
        if ( ENTRY.equals( name ) )
        {
            userProfileHandler = new UserProfileHandler( this );
            userProfileHandler.startHandlingEvents();
            userProfileHandler.startElement( uri, name, qName, attributes );
        }
    }

    /**
     * Method to be called every time an xml element ends
     */
    public void endElement( String uri, String name, String qName )
        throws SAXException
    {
        if ( ENTRY.equals( name ) )
        {
            followers.add( userProfileHandler.getProfile() );
        }
    }
}
