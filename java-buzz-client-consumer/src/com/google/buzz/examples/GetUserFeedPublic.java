package com.google.buzz.examples;

import java.io.IOException;

import com.google.buzz.Buzz;
import com.google.buzz.exception.BuzzAuthenticationException;
import com.google.buzz.exception.BuzzIOException;
import com.google.buzz.exception.BuzzParsingException;
import com.google.buzz.model.BuzzFeed;
import com.google.buzz.model.BuzzFeedEntry;

/**
 * This example class demonstrates how to use the <b>Buzz.java</b> API to retrieve the user public
 * feed ( @public )
 * 
 * @author roberto.estivill
 */
public class GetUserFeedPublic
{
    /**
     * User account to be used.<br/>
     * Can not use @me value here, since the request is not authenticated. Therefore, there is no
     * user logged in.
     */
    public static String userId;

    /**
     * Example main method
     * 
     * @param arguments to the program.
     *            <ul>
     *            <li>User Id</li>
     *            </ul>
     * @throws BuzzIOException if any IO error occurs ( networking ).
     * @throws BuzzAuthenticationException if any OAuth error occurs
     * @throws BuzzParsingException if a parsing error occurs
     * @throws IOException if an error ocurrs getting the verification code from the console.
     */
    public static void main( String[] args )
        throws BuzzAuthenticationException, IOException, BuzzIOException, BuzzParsingException
    {
        /**
         * Check for arguments
         */
        if ( args.length != 1 )
        {
            System.err.println( "Missing arguments." );
            System.exit( 0 );
        }

        /**
         * Assign arguments
         */
        userId = args[0];

        /**
         * Create a new instance of the API
         */
        Buzz buzz = new Buzz();

        /**
         * Execute API method to retrieve the user public feed.
         */
        BuzzFeed feed = buzz.getPosts( userId, BuzzFeed.Type.PUBLIC );

        /**
         * Print results
         */
        System.out.println( feed.getFeedTitle() );
        if ( !feed.getEntries().isEmpty() )
        {
            for ( BuzzFeedEntry entry : feed.getEntries() )
            {
                System.out.print( "Entry Title: " + entry.getTitle() );
                System.out.println( " | Entry Date: " + entry.getPublished() );
            }
        }
        else
        {
            System.out.println( "The feed doesn't have any entries." );
        }
    }
}
