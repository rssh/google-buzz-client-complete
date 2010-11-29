package com.google.buzz.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.buzz.exception.BuzzIOException;
import com.google.buzz.exception.BuzzParsingException;
import com.google.buzz.model.BuzzFeed;
import com.google.buzz.parser.handler.FeedHandler;

/**
 * Parser for element: <b>feed<b/>.
 * 
 * @author roberto.estivill
 */
public class BuzzFeedParser
{
    /**
     * Parse an xml string into a BuzzFeed model object.
     * 
     * @param xmlResponse to be parsed.
     * @return the feed object.
     * @throws BuzzIOException if any IO error occurs.
     * @throws BuzzParsingException if a parsing error occurs.
     */
    public static BuzzFeed parseFeed( String xmlResponse )
        throws BuzzParsingException, BuzzIOException
    {
        FeedHandler handler;
        XMLReader xr;
        try
        {
            xr = XMLReaderFactory.createXMLReader();
            handler = new FeedHandler( xr );
            xr.setContentHandler( handler );
            xr.setErrorHandler( handler );
            xr.parse( new InputSource( new ByteArrayInputStream( xmlResponse.getBytes( "UTF-8" ) ) ) );
        }
        catch ( SAXException e )
        {
            throw new BuzzParsingException( e );
        }
        catch ( IOException e )
        {
            throw new BuzzIOException( e );
        }
        return handler.getFeed();
    }
}