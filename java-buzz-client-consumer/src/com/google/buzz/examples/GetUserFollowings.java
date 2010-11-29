package com.google.buzz.examples;

import java.io.IOException;
import java.util.List;

import com.google.buzz.Buzz;
import com.google.buzz.examples.util.ExampleUtils;
import com.google.buzz.exception.BuzzAuthenticationException;
import com.google.buzz.exception.BuzzIOException;
import com.google.buzz.exception.BuzzParsingException;
import com.google.buzz.model.BuzzUserProfile;

/**
 * This example class demonstrates how to use the <b>Buzz.java</b> API to retrieve the people that
 * the user is following
 * 
 * @author roberto.estivill
 */
public class GetUserFollowings
{
    /**
     * The consumer application key for OAuth.
     */
    private static String consumerKey;

    /**
     * The consumer application secret for OAuth.
     */
    private static String consumerSecret;

    /**
     * User account to be used.<br/>
     * If @me, the authentication will be executed with the user that is logged in on the browser.
     */
    public static String userId;

    /**
     * Example main method
     * 
     * @param program arguments.<br/>
     *            <ul>
     *            <li>Consumer Key</li>
     *            <li>Consumer Secret</li>
     *            <li>User Id</li>
     *            </ul>
     * @throws BuzzIOException if any IO error occurs ( networking ).
     * @throws BuzzAuthenticationException if any OAuth error occurs
     * @throws BuzzParsingException if a parsing error occurs
     * @throws IOException if an error ocurrs getting the verification code from the console.
     */
    public static void main( String[] args )
        throws BuzzIOException, BuzzAuthenticationException, IOException, BuzzParsingException
    {
        /**
         * Check for arguments
         */
        if ( args.length != 3 )
        {
            System.err.println( "Missing arguments." );
            System.exit( 0 );
        }

        /**
         * Assign arguments
         */
        consumerKey = args[0];
        consumerSecret = args[1];
        userId = args[2];

        /**
         * Create a new instance of the API
         */
        Buzz buzz = new Buzz();

        /**
         * Get the url to authenticated the user. <br/>
         * The user has to grant access to this application, to manage Buzz Content.
         */
        String verificationUrl = buzz.getAuthenticationUrl( Buzz.BUZZ_SCOPE_READONLY, consumerKey, consumerSecret );

        /**
         * Redirect the user to the verificationUrl and read the verification code. <br/>
         * The new application should implement a similar method in order to get the verification
         * code from the google authentication website.<br/>
         * For development, we are lunching a browser locally and manually pasting the verification
         * code in the example console.
         */
        String verificationCode = ExampleUtils.getVerificationCode( verificationUrl );

        /**
         * Set the verificationCode (A.K.A. access token) to the API to be used on the request
         * signature, for authenticated requests.
         */
        buzz.setAccessToken( verificationCode );

        /**
         * Set the verificationCode (A.K.A. access token) to the API to be used on the request
         * signature, for authenticated requests.
         */
        List<BuzzUserProfile> response = buzz.following( userId );

        /**
         * Print results
         */
        if ( !response.isEmpty() )
        {
            for ( BuzzUserProfile profile : response )
            {
                System.out.print( "Name: " + profile.getName() );
                System.out.println( " | Id: " + profile.getId() );
            }
        }
        else
        {
            System.out.println( "The user is not following anyone." );
        }
    }
}
