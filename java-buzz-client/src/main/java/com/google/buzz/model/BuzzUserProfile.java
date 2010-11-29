package com.google.buzz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class to reprensent a user profile
 * 
 * @author roberto.estivill
 */
public class BuzzUserProfile
{
    /**
     * The user profile id
     */
    private String id;

    /**
     * The user profile name
     */
    private String name;

    /**
     * The user profile url
     */
    private String profileUrl;

    /**
     * The user profile list of emails
     */
    private List<BuzzEmail> emails = new ArrayList<BuzzEmail>( 0 );

    /**
     * The user profile list of url's
     */
    private List<BuzzUrl> urls = new ArrayList<BuzzUrl>( 0 );

    /**
     * The user profile list of photos
     */
    private List<BuzzPhoto> photos = new ArrayList<BuzzPhoto>( 0 );

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId( String id )
    {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * @return the profileUrl
     */
    public String getProfileUrl()
    {
        return profileUrl;
    }

    /**
     * @param profileUrl the profileUrl to set
     */
    public void setProfileUrl( String profileUrl )
    {
        this.profileUrl = profileUrl;
    }

    /**
     * @return the emails
     */
    public List<BuzzEmail> getEmails()
    {
        return emails;
    }

    /**
     * @param emails the emails to set
     */
    public void setEmails( List<BuzzEmail> emails )
    {
        this.emails = emails;
    }

    /**
     * @return the urls
     */
    public List<BuzzUrl> getUrls()
    {
        return urls;
    }

    /**
     * @param urls the urls to set
     */
    public void setUrls( List<BuzzUrl> urls )
    {
        this.urls = urls;
    }

    /**
     * @return the photos
     */
    public List<BuzzPhoto> getPhotos()
    {
        return photos;
    }

    /**
     * @param photos the photos to set
     */
    public void setPhotos( List<BuzzPhoto> photos )
    {
        this.photos = photos;
    }

    /**
     * Overwrite the default toString method
     * 
     * @return the string representation of the object
     */
    public String toString()
    {
        return toString( "\n" );
    }

    /**
     * Print the object in a pretty way.
     * 
     * @param indent to print the attributes
     * @return a formatted string representation of the object
     */
    public String toString( String indent )
    {
        StringBuilder sb = new StringBuilder();
        String newIndent = indent + "\t";
        sb.append( indent + "BuzzUserProfile:" );
        sb.append( newIndent + "Id: " + id );
        sb.append( newIndent + "Name: " + name );
        sb.append( newIndent + "Profile Url: " + profileUrl );
        sb.append( newIndent + "Emails:" );
        for ( BuzzEmail email : emails )
        {
            sb.append( email.toString( indent + "\t" ) );
        }
        sb.append( newIndent + "Urls:" );
        for ( BuzzUrl url : urls )
        {
            sb.append( url.toString( newIndent + "\t" ) );
        }
        sb.append( newIndent + "Photos:" );
        for ( BuzzPhoto photo : photos )
        {
            sb.append( photo.toString( newIndent + "\t" ) );
        }
        return sb.toString();
    }
}
