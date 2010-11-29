package com.google.buzz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class to represent an Author
 * 
 * @author roberto.estivill
 */
public class BuzzAuthor
{
    /**
     * The author id
     */
    private String id;

    /**
     * The author name
     */
    private String name;

    /**
     * The author uri
     */
    private String uri;

    /**
     * The author links
     */
    private List<BuzzLink> links = new ArrayList<BuzzLink>( 0 );

    /**
     * The author activity object type
     */
    private String activityObjectType;

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
     * @return the uri
     */
    public String getUri()
    {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri( String uri )
    {
        this.uri = uri;
    }

    /**
     * @return the links
     */
    public List<BuzzLink> getLinks()
    {
        return links;
    }

    /**
     * @param links the links to set
     */
    public void setLinks( List<BuzzLink> links )
    {
        this.links = links;
    }

    /**
     * @return the activityObjectType
     */
    public String getActivityObjectType()
    {
        return activityObjectType;
    }

    /**
     * @param activityObjectType the activityObjectType to set
     */
    public void setActivityObjectType( String activityObjectType )
    {
        this.activityObjectType = activityObjectType;
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
        sb.append( indent + "BuzzAuthor:" );
        sb.append( newIndent + "Id: " + id );
        sb.append( newIndent + "Name: " + name );
        sb.append( newIndent + "Uri: " + uri );
        sb.append( newIndent + "Activity Object Type: " + activityObjectType );
        sb.append( newIndent + "Links: " );
        for ( int i = 0; i < links.size(); i++ )
        {
            sb.append( links.get( i ).toString( newIndent ) );
        }
        return sb.toString();
    }
}
