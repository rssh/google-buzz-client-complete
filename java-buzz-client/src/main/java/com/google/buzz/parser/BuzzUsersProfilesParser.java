package com.google.buzz.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.buzz.exception.BuzzIOException;
import com.google.buzz.exception.BuzzParsingException;
import com.google.buzz.model.BuzzUserProfile;
import com.google.buzz.parser.handler.UsersProfilesHandler;

/**
 * Parser for element: <b>users profiles<b/>.
 * 
 * @author roberto.estivill
 */
public class BuzzUsersProfilesParser
{
    /**
     * Parse an xml string into a list of BuzzUserProfile. <br/>
     * Used to generate followers and following lists. <br/>
     * 
     * @param xmlResponse to be parsed.
     * @return the list of profiles.
     * @throws BuzzIOException if any IO error occurs.
     * @throws BuzzParsingException if a parsing error occurs.
     */
    public static List<BuzzUserProfile> parseUsersProfiles( String xmlResponse )
        throws BuzzParsingException, BuzzIOException
    {
        UsersProfilesHandler handler;
        XMLReader xr;
        try
        {
            xr = XMLReaderFactory.createXMLReader();
            handler = new UsersProfilesHandler( xr );
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
        return handler.getBuzzUserFollowers();
    }
}
