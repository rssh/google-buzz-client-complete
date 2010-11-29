package com.google.buzz.parser.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.google.buzz.model.BuzzLink;

/**
 * Handler for xml element: <b>Link</b>
 * 
 * @author roberto.estivill
 */
public class LinkHandler
    extends BaseHandler
{
    /**
     * XML elements handled by this handler
     */
    private static final String LINK = "link";
    private static final String HREF = "href";
    private static final String REL = "rel";
    private static final String TYPE = "type";
    private static final String COUNT = "buzz:count";

    /**
     * Object to return
     */
    private BuzzLink link;

    /**
     * Constructor method to create a child handler.
     * 
     * @param parentHandler handler
     */
    public LinkHandler( BaseHandler aParent )
    {
        super( aParent );
        link = new BuzzLink();
    }

    /**
     * @return the created link object
     */
    public BuzzLink getBuzzLink()
    {
        return link;
    }

    /**
     * Method to be called every time an xml element starts
     */
    public void startElement( String uri, String name, String qName, Attributes attributes )
        throws SAXException
    {
        String href = attributes.getValue( HREF );
        if ( href != null && !href.equals( "" ) )
            link.setHref( href );
        String rel = attributes.getValue( REL );
        if ( rel != null && !rel.equals( "" ) )
            link.setRel( rel );
        String type = attributes.getValue( TYPE );
        if ( type != null && !type.equals( "" ) )
            link.setType( type );
        String count = attributes.getValue( COUNT );
        if ( count != null && !count.equals( "" ) )
            link.setCount( Integer.valueOf( count ) );
    }

    /**
     * Method to be called every time an xml element ends
     */
    public void endElement( String uri, String name, String qName )
        throws SAXException
    {
        if ( LINK.equals( name ) )
        {
            stopHandlingEvents();
            parentHandler.endElement( uri, name, qName );
        }
    }
}
