package com.google.buzz.model;

/**
 * Model class to represent a Content
 * 
 * @author roberto.estivill
 */
public class BuzzContent
{

    /**
     * The content itself
     */
    private String text;

    /**
     * The content type
     */
    private String type;

    /**
     * @return the content
     */
    public String getText()
    {
        return text;
    }

    /**
     * @param text the content to set
     */
    public void setText( String text )
    {
        this.text = text;
    }

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType( String type )
    {
        this.type = type;
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
        sb.append( indent + "BuzzContent:" );
        sb.append( newIndent + "Text: " + text );
        sb.append( newIndent + "Type: " + type );
        return sb.toString();
    }
}
