package com.google.buzz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Model class to represent a feed
 * 
 * @author roberto.estivill
 */
public class BuzzFeed
{
    /**
     * Enum with all the different types of feed that the third party application will be able to
     * access
     * 
     * @author roberto.estivill
     */
    public enum Type
    {
        PUBLIC("@public"), PRIVATE("@self"), CONSUMPTION("@consumption"), FOLLOWERS("@followers"), FOLLOWING(
            "@following"), COMMENTS("@comments"), LIKED("@liked");

        /**
         * The feed type
         */
        private String feedType;

        /**
         * @param type to set
         */
        private Type( String type )
        {
            feedType = type;
        }

        /**
         * @return the name
         */
        public String getName()
        {
            return feedType;
        }
    }

    /**
     * The feed links
     */
    private List<BuzzLink> links = new ArrayList<BuzzLink>( 0 );

    /**
     * The feed title
     */
    private String title;

    /**
     * The feed title type
     */
    private String titleType;

    /**
     * The feed updated date
     */
    private Date updated;

    /**
     * The feed id
     */
    private String id;

    /**
     * The feed generator description
     */
    private String generator;

    /**
     * The feed generator uri
     */
    private String generatorUri;

    /**
     * The feed list of entries
     */
    private List<BuzzFeedEntry> entries = new ArrayList<BuzzFeedEntry>( 0 );

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
     * @return the feedTitle
     */
    public String getFeedTitle()
    {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle( String title )
    {
        this.title = title;
    }

    /**
     * @return the feedTitleType
     */
    public String getTitleType()
    {
        return titleType;
    }

    /**
     * @param titleType the titleType to set
     */
    public void setTitleType( String titleType )
    {
        this.titleType = titleType;
    }

    /**
     * @return the updated
     */
    public Date getUpdated()
    {
        return updated;
    }

    /**
     * @param updated the updated to set
     */
    public void setUpdated( Date updated )
    {
        this.updated = updated;
    }

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
     * @return the generator
     */
    public String getGenerator()
    {
        return generator;
    }

    /**
     * @param generator the generator to set
     */
    public void setGenerator( String generator )
    {
        this.generator = generator;
    }

    /**
     * @return the generatorUri
     */
    public String getGeneratorUri()
    {
        return generatorUri;
    }

    /**
     * @param generatorUri the generatorUri to set
     */
    public void setGeneratorUri( String generatorUri )
    {
        this.generatorUri = generatorUri;
    }

    /**
     * @return the entries
     */
    public List<BuzzFeedEntry> getEntries()
    {
        return entries;
    }

    /**
     * @param entries the entries to set
     */
    public void setEntries( List<BuzzFeedEntry> entries )
    {
        this.entries = entries;
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
        sb.append( indent + "BuzzFeed:" );
        sb.append( newIndent + "Title: " + title );
        sb.append( newIndent + "Title Type: " + titleType );
        sb.append( newIndent + "Updated: " + updated );
        sb.append( newIndent + "Id: " + id );
        sb.append( newIndent + "Generator: " + generator );
        sb.append( newIndent + "Generator Uri: " + generatorUri );
        sb.append( newIndent + "Links: " );
        for ( int i = 0; i < links.size(); i++ )
        {
            sb.append( links.get( i ).toString( newIndent + "\t" ) );
        }
        sb.append( newIndent + "Entries: " );
        for ( int i = 0; i < entries.size(); i++ )
        {
            sb.append( entries.get( i ).toString( newIndent + "\t" ) );
        }
        return sb.toString();
    }
}
