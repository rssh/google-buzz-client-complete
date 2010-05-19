package com.google.buzz.model;

/**
 * Model class to represent a reply
 * 
 * @author roberto.estivill
 */
public class BuzzReply
{
    private String href;

    private String type;

    private String ref;

    /**
     * @return the href
     */
    public String getHref()
    {
        return href;
    }

    /**
     * @param href the href to set
     */
    public void setHref( String href )
    {
        this.href = href;
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
     * @return the ref
     */
    public String getRef()
    {
        return ref;
    }

    /**
     * @param ref the ref to set
     */
    public void setRef( String ref )
    {
        this.ref = ref;
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
        sb.append( indent + "BuzzReply:" );
        sb.append( newIndent + "Type: " + type );
        sb.append( newIndent + "Href: " + href );
        sb.append( newIndent + "Rel: " + ref );
        return sb.toString();
    }
}
