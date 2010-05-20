package com.google.buzz.model;

import java.util.ArrayList;
import java.util.List;

public class BuzzCommentsFeed
{
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
     * The feed list of comments
     */
    private List<BuzzComment> comments = new ArrayList<BuzzComment>( 0 );

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
     * @return the title
     */
    public String getTitle()
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
     * @return the titleType
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
     * @return the comments
     */
    public List<BuzzComment> getComments()
    {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments( List<BuzzComment> comments )
    {
        this.comments = comments;
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
        sb.append( newIndent + "Id: " + id );
        sb.append( newIndent + "Generator: " + generator );
        sb.append( newIndent + "Generator Uri: " + generatorUri );
        sb.append( newIndent + "Links: " );
        for ( int i = 0; i < links.size(); i++ )
        {
            sb.append( links.get( i ).toString( newIndent + "\t" ) );
        }
        sb.append( newIndent + "Comments: " );
        for ( int i = 0; i < comments.size(); i++ )
        {
            sb.append( comments.get( i ).toString( newIndent + "\t" ) );
        }
        return sb.toString();
    }
}
