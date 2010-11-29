package com.google.buzz.parser.handler;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Base handler. <br/>
 * The rest of the handlers should extend BaseHandler.<br/>
 * BaseHandler provides methods to it's children to chain handlers. This allows to reuse handlers of
 * elements that might appear in different paths on the xml ( ie: link, url, etc).
 * 
 * @author roberto.estivill
 */
public class BaseHandler
    extends DefaultHandler
{
    /**
     * The parent handler
     */
    public BaseHandler parentHandler = null;

    /**
     * The xmlreader object
     */
    public XMLReader xmlReader = null;

    /**
     * Default constructor is provided so a handler may be created by Class.newInstance. This puts
     * the burden on the creator to setParent() and/or setReader().
     */
    public BaseHandler()
    {
        super();
    }

    /**
     * Constructor for first handler in the chain. It has no parent, but needs to know its XMLReader
     * 
     * @param aReader The SAX XMLReader that's running things
     */
    public BaseHandler( XMLReader aReader )
    {
        this();
        xmlReader = aReader;
    }

    /**
     * Constructor for child handlers in the chain. Given the parent they can get the XMLReader.
     * 
     * @param aParent - Previous handler in the chain
     */
    public BaseHandler( BaseHandler aParent )
    {
        super();
        parentHandler = aParent;
        setReader( aParent.getReader() );
    }

    /**
     * Tell the parser this object will handle events now. On startElement for a tag that is handled
     * by a child, the parent calls this method on the child.
     */
    public void startHandlingEvents()
    {
        xmlReader.setContentHandler( this );
        xmlReader.setErrorHandler( this );
    }

    /**
     * Return control to the parent handler. On endElement for the tag being handled, a child calls
     * this method on itself.
     */
    public void stopHandlingEvents()
    {
        xmlReader.setContentHandler( parentHandler );
        xmlReader.setErrorHandler( parentHandler );
    }

    /**
     * @return the xmlReader
     */
    public XMLReader getReader()
    {
        return xmlReader;
    }

    /**
     * @param xmlReader to set
     */
    public void setReader( XMLReader xmlReader )
    {
        this.xmlReader = xmlReader;
    }

    /**
     * @param parentHandler handler to set
     */
    public void setParent( BaseHandler parentHandler )
    {
        this.parentHandler = parentHandler;
        setReader( parentHandler.getReader() );
    }
}
