package com.google.buzz.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.buzz.exception.BuzzIOException;
import com.google.buzz.exception.BuzzParsingException;
import com.google.buzz.model.BuzzFeedEntry;
import com.google.buzz.parser.handler.FeedEntryHandler;

/**
 * Parser for element: <b>feed entry<b/>.
 * 
 * @author roberto.estivill
 */
public class BuzzFeedEntryParser
{
    /**
     * Parse an xml string into a BuzzFeedEntry model object.
     * 
     * @param xmlResponse to be parsed.
     * @return a feed entry.
     * @throws BuzzIOException if any IO error occurs.
     * @throws BuzzParsingException if a parsing error occurs.
     */
    public static BuzzFeedEntry parseFeedEntry( String xmlResponse )
        throws BuzzParsingException, BuzzIOException
    {
        FeedEntryHandler handler;
        XMLReader xr;
        try
        {
            xr = XMLReaderFactory.createXMLReader();
            handler = new FeedEntryHandler( xr );
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
        return handler.getBuzzFeedEntry();
    }
}