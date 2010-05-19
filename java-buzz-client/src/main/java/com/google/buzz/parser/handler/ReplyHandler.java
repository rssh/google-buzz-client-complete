package com.google.buzz.parser.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.google.buzz.model.BuzzReply;

/**
 * Handler for xml element: <b>Link</b>
 * 
 * @author roberto.estivill
 */
public class ReplyHandler
    extends BaseHandler
{
    /**
     * XML elements handled by this handler
     */
    private static final String REPLY = "thr:in-reply-to";
    private static final String HREF = "href";
    private static final String REF = "ref";
    private static final String TYPE = "type";

    /**
     * Object to return
     */
    private BuzzReply reply;

    /**
     * Constructor method to create a child handler.
     * 
     * @param parentHandler handler
     */
    public ReplyHandler( BaseHandler aParent )
    {
        super( aParent );
        reply = new BuzzReply();
    }

    /**
     * @return the created link object
     */
    public BuzzReply getBuzzReply()
    {
        return reply;
    }

    /**
     * Method to be called every time an xml element starts
     */
    public void startElement( String uri, String name, String qName, Attributes attributes )
        throws SAXException
    {
        String href = attributes.getValue( HREF );
        if ( href != null && !href.equals( "" ) )
            reply.setHref( href );
        String ref = attributes.getValue( REF );
        if ( ref != null && !ref.equals( "" ) )
            reply.setRef( ref );
        String type = attributes.getValue( TYPE );
        if ( type != null && !type.equals( "" ) )
            reply.setType( type );
    }

    /**
     * Method to be called every time an xml element ends
     */
    public void endElement( String uri, String name, String qName )
        throws SAXException
    {
        if ( REPLY.equals( name ) )
        {
            stopHandlingEvents();
            parentHandler.endElement( uri, name, qName );
        }
    }
}
