package com.google.buzz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Model class to represent an feed entry
 * 
 * @author roberto.estivill
 */
public class BuzzFeedEntry
{
    /**
     * The entry title
     */
    private String title;

    /**
     * The entry published date
     */
    private Date published;

    /**
     * The entry updated date
     */
    private Date updated;

    /**
     * The entry id
     */
    private String id;

    /**
     * The entry list of links
     */
    private List<BuzzLink> links = new ArrayList<BuzzLink>( 0 );

    /**
     * The entry author
     */
    private BuzzAuthor author;

    /**
     * The entry content
     */
    private BuzzContent content;

    /**
     * The entry activity verb
     */
    private String activityVerb;

    /**
     * The entry crosspost source id
     */
    private String crosspostSourceId;

    /**
     * The entry source activity title
     */
    private String sourceActivityTitle;

    /**
     * The entry list of visibility objects
     */
    private List<BuzzAclEntry> visibility = new ArrayList<BuzzAclEntry>( 0 );

    /**
     * The entry activity
     */
    private BuzzActivity activity;

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
     * @return the published
     */
    public Date getPublished()
    {
        return published;
    }

    /**
     * @param published the published to set
     */
    public void setPublished( Date published )
    {
        this.published = published;
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
     * @return the author
     */
    public BuzzAuthor getAuthor()
    {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor( BuzzAuthor author )
    {
        this.author = author;
    }

    /**
     * @return the content
     */
    public BuzzContent getContent()
    {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent( BuzzContent content )
    {
        this.content = content;
    }

    /**
     * @return the activityVerb
     */
    public String getActivityVerb()
    {
        return activityVerb;
    }

    /**
     * @param activityVerb the activityVerb to set
     */
    public void setActivityVerb( String activityVerb )
    {
        this.activityVerb = activityVerb;
    }

    /**
     * @return the crosspostSourceId
     */
    public String getCrosspostSourceId()
    {
        return crosspostSourceId;
    }

    /**
     * @param crosspostSourceId the crosspostSourceId to set
     */
    public void setCrosspostSourceId( String crosspostSourceId )
    {
        this.crosspostSourceId = crosspostSourceId;
    }

    /**
     * @return the sourceActivityTitle
     */
    public String getSourceActivityTitle()
    {
        return sourceActivityTitle;
    }

    /**
     * @param sourceActivityTitle the sourceActivityTitle to set
     */
    public void setSourceActivityTitle( String sourceActivityTitle )
    {
        this.sourceActivityTitle = sourceActivityTitle;
    }

    /**
     * @return the visibility
     */
    public List<BuzzAclEntry> getVisibility()
    {
        return visibility;
    }

    /**
     * @param visibility the visibility to set
     */
    public void setVisibility( List<BuzzAclEntry> visibility )
    {
        this.visibility = visibility;
    }

    /**
     * @return the activity
     */
    public BuzzActivity getActivity()
    {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity( BuzzActivity activity )
    {
        this.activity = activity;
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
        sb.append( indent + "BuzzFeedEntry:" );
        sb.append( newIndent + "Title: " + title );
        sb.append( newIndent + "Published: " + published );
        sb.append( newIndent + "Updated: " + updated );
        sb.append( newIndent + "Id: " + id );
        sb.append( newIndent + "Author: " + author.toString( newIndent + "\t" ) );
        sb.append( newIndent + "Content: " + content.toString( newIndent + "\t" ) );
        sb.append( newIndent + "Activity Verb: " + activityVerb );
        sb.append( newIndent + "Cross Post Source Id: " + crosspostSourceId );
        sb.append( newIndent + "Source Activity Service Title: " + sourceActivityTitle );
        sb.append( newIndent + "Buzz Activity: " + activity.toString( newIndent + "\t" ) );
        sb.append( newIndent + "Links: " );
        for ( int i = 0; i < links.size(); i++ )
        {
            sb.append( links.get( i ).toString( newIndent + "\t" ) );
        }
        sb.append( newIndent + "Visibility: " );
        for ( int i = 0; i < visibility.size(); i++ )
        {
            sb.append( visibility.get( i ).toString( newIndent + "\t" ) );
        }

        return sb.toString();
    }
}
