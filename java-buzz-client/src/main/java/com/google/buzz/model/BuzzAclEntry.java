package com.google.buzz.model;

/**
 * Model class to represent a Acl Entry
 * 
 * @author roberto.estivill
 */
public class BuzzAclEntry
{
    /**
     * the buzz acl entry type
     */
    private String type;

    /**
     * the buzz acl entry id
     */
    private String id;

    /**
     * the buzz acl entry url
     */
    private String uri;

    /**
     * the buzz acl entry name
     */
    private String name;

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
        sb.append( indent + "BuzzAclEntry:" );
        sb.append( newIndent + "Type: " + type );
        sb.append( newIndent + "Id: " + id );
        sb.append( newIndent + "Uri: " + uri );
        sb.append( newIndent + "Name: " + name );
        return sb.toString();
    }
}
