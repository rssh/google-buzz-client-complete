package com.google.buzz.parser.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.google.buzz.model.BuzzAclEntry;

/**
 * Handler for element: <b>Visibility</b>
 * 
 * @author roberto.estivill
 */
public class VisibilityHandler
    extends BaseHandler
{
    /**
     * XML elements handled by this handler
     */
    private static final String BUZZ_VISIBILITY = "buzz:visibility";
    private static final String BUZZ_ACL_ENTRY = "buzz:aclentry";
    private static final String TYPE = "type";
    private static final String ID = "poco:id";
    private static final String URI = "uri";
    private static final String NAME = "poco:name";

    /**
     * Position flags
     */
    private boolean insideId = false;
    private boolean insideUri = false;
    private boolean insideName = false;

    /**
     * Object to return
     */
    private List<BuzzAclEntry> buzzAclEntries;
    private BuzzAclEntry buzzAclEntry;

    /**
     * Constructor method to create a child handler.
     * 
     * @param parentHandler handler
     */
    public VisibilityHandler( BaseHandler parentHandler )
    {
        super( parentHandler );
        buzzAclEntries = new ArrayList<BuzzAclEntry>();
    }

    /**
     * @return the created buzzAclEntries objects
     */
    public List<BuzzAclEntry> getBuzzAclEntries()
    {
        return buzzAclEntries;
    }

    /**
     * Method to be called every time an xml element starts
     */
    public void startElement( String uri, String name, String qName, Attributes attributes )
        throws SAXException
    {
        if ( BUZZ_ACL_ENTRY.equals( qName ) )
        {
            buzzAclEntry = new BuzzAclEntry();
            buzzAclEntries.add( buzzAclEntry );
            String type = attributes.getValue( TYPE );
            if ( type != null && !type.equals( "" ) )
            {
                buzzAclEntry.setType( type );
            }
        }
        else if ( ID.equals( qName ) )
        {
            insideId = true;
        }
        else if ( URI.equals( qName ) )
        {
            insideUri = true;
        }
        else if ( NAME.equals( qName ) )
        {
            insideName = true;
        }
    }

    /**
     * Method to be called every time an xml element ends
     */
    public void endElement( String uri, String name, String qName )
        throws SAXException
    {
        if ( ID.equals( qName ) )
        {
            insideId = false;
        }
        else if ( URI.equals( qName ) )
        {
            insideUri = false;
        }
        else if ( NAME.equals( qName ) )
        {
            insideName = false;
        }
        else if ( BUZZ_VISIBILITY.equals( qName ) )
        {
            stopHandlingEvents();
            parentHandler.endElement( uri, name, qName );
        }
    }

    /**
     * Method to be called between the beginning and the end of the xml elements.
     */
    public void characters( char[] ch, int start, int length )
        throws SAXException
    {
        String content = ( new String( ch ).substring( start, start + length ) );
        if ( insideId )
        {
            buzzAclEntry.setId( content );
        }
        else if ( insideUri )
        {
            buzzAclEntry.setUri( content );
        }
        else if ( insideName )
        {
            buzzAclEntry.setName( content );
        }
    }
}
