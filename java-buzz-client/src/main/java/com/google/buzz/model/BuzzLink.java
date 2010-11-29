package com.google.buzz.model;

/**
 * Model class to represent a link
 * 
 * @author roberto.estivill
 */
public class BuzzLink
{
    /**
     * Enum with some types of links used in the application. <br/>
     * Since all the different types of links are not enumerated, will keep the type attribute as
     * String and two different setters methods to allow the developers set their own link type.
     * 
     * @author roberto.estivill
     */
    public enum Type
    {
        TEXT("text"), TEXT_HTML("text/html");

        /**
         * The link type
         */
        private String linkType;

        /**
         * Default constructor method
         * 
         * @param type to set
         */
        private Type( String type )
        {
            linkType = type;
        }

        /**
         * @return the link type
         */
        public String getType()
        {
            return linkType;
        }
    }

    /**
     * The link href
     */
    private String href;

    /**
     * The link rel
     */
    private String rel;

    /**
     * The link type
     */
    private String type;

    /**
     * The link count of liked
     */
    private int count;

    /**
     * @return the count
     */
    public int getCount()
    {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount( int count )
    {
        this.count = count;
    }

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
     * @return the rel
     */
    public String getRel()
    {
        return rel;
    }

    /**
     * @param rel the rel to set
     */
    public void setRel( String rel )
    {
        this.rel = rel;
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
     * @param type the type to set
     */
    public void setType( BuzzLink.Type type )
    {
        this.type = type.getType();
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
        sb.append( indent + "BuzzLink:" );
        sb.append( newIndent + "Href: " + href );
        sb.append( newIndent + "Type: " + type );
        sb.append( newIndent + "Count: " + count );
        sb.append( newIndent + "Rel: " + rel );
        return sb.toString();
    }

}
