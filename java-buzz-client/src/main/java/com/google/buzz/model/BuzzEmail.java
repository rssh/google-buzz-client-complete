package com.google.buzz.model;

/**
 * Model class to represent an Email
 * 
 * @author roberto.estivill
 */
public class BuzzEmail
{
    /**
     * The email value ( address )
     */
    private String value;

    /**
     * The email type
     */
    private String type;

    /**
     * Email primary flag
     */
    private boolean primary;

    /**
     * @return the address
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @param address the address to set
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
     * @return the primary
     */
    public boolean isPrimary()
    {
        return primary;
    }

    /**
     * @param primary the primary to set
     */
    public void setPrimary( boolean primary )
    {
        this.primary = primary;
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
        sb.append( indent + "BuzzEmail:" );
        sb.append( newIndent + "Value: " + value );
        sb.append( newIndent + "Type: " + type );
        sb.append( newIndent + "Primary: " + primary );
        return sb.toString();
    }
}
