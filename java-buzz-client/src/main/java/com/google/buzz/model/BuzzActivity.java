package com.google.buzz.model;

/**
 * Model class to represent an activity
 * 
 * @author roberto.estivill
 */
public class BuzzActivity
{

    /**
     * The activity object type
     */
    private String activityObjectType;

    /**
     * The activity content
     */
    private BuzzContent content;

    /**
     * The activity link
     */
    private BuzzLink link;

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
     * @return the link
     */
    public BuzzLink getLink()
    {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink( BuzzLink link )
    {
        this.link = link;
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
        sb.append( indent + "BuzzActivity:" );
        sb.append( newIndent + "Activity Object Type: " + activityObjectType );
        sb.append( newIndent + "BuzzContent: " + content.toString( newIndent ) );
        sb.append( newIndent + "BuzzLink: " + link.toString( newIndent ) );
        return sb.toString();
    }

}
