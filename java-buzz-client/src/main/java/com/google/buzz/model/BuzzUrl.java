package com.google.buzz.model;

/**
 * Model class to represent an URL
 * 
 * @author roberto.estivill
 */
public class BuzzUrl
{

    /**
     * The url itself
     */
    private String value;

    /**
     * The url type
     */
    private String type;

    /**
     * @return the value
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue( String value )
    {
        this.value = value;
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
        sb.append( indent + "BuzzUrl:" );
        sb.append( newIndent + "Value: " + value );
        sb.append( newIndent + "Type: " + type );
        return sb.toString();
    }
}
