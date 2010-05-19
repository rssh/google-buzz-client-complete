package com.google.buzz.examples;

import java.io.IOException;

import com.google.buzz.Buzz;
import com.google.buzz.examples.util.ExampleUtils;
import com.google.buzz.exception.BuzzAuthenticationException;
import com.google.buzz.exception.BuzzIOException;
import com.google.buzz.exception.BuzzParsingException;
import com.google.buzz.exception.BuzzValidationException;
import com.google.buzz.model.BuzzContent;
import com.google.buzz.model.BuzzFeedEntry;
import com.google.buzz.model.BuzzLink;

/**
 * This example class demonstrates how to use the <b>Buzz.java</b> API to create a post with a link
 * on Google Buzz.
 * 
 * @author roberto.estivill
 */
public class PostActivityWithLink
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
     * The content of the post activity
     */
    public static String content;

    /**
     * The link to be attached in the post activity
     */
    public static String link;

    /**
     * Example main method
     * 
     * @param program arguments.<br/>
     *            <ul>
     *            <li>Consumer Key</li>
     *            <li>Consumer Secret</li>
     *            <li>User Id</li>
     *            <li>Post content</li>
     *            <li>Post link</li>
     *            </ul>
     * @throws BuzzIOException if any IO error occurs ( networking ).
     * @throws BuzzAuthenticationException if any OAuth error occurs
     * @throws BuzzValidationException if any required element of the new post is missing
     * @throws BuzzParsingException if a parsing error occurs
     * @throws IOException if an error ocurrs getting the verification code from the console.
     */
    public static void main( String[] args )
        throws BuzzAuthenticationException, IOException, BuzzIOException, BuzzValidationException, BuzzParsingException
    {
        /**
         * Check for arguments
         */
        if ( args.length != 5 )
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
        content = args[3];
        link = args[4];

        /**
         * Create a new instance of the API
         */
        Buzz buzz = new Buzz();

        /**
         * Get the url to authenticated the user. <br/>
         * The user has to grant access to this application, to manage Buzz Content.
         */
        String verificationUrl = buzz.getAuthenticationUrl( Buzz.BUZZ_SCOPE_WRITE, consumerKey, consumerSecret );

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
         * Create the content of the post
         */
        BuzzContent buzzContent = new BuzzContent();
        buzzContent.setContent( content );
        buzzContent.setType( "text" );

        /**
         * Create the link that is going to be included in the post.
         */
        BuzzLink buzzLink = new BuzzLink();
        buzzLink.setHref( link );
        buzzLink.setRel( "Google Buzz Api" );
        buzzLink.setType( BuzzLink.Type.TEXT );

        /**
         * Execute API method to post an entry with a link.
         */
        BuzzFeedEntry entry = buzz.createPost( userId, buzzContent, buzzLink );

        /**
         * Print results
         */
        System.out.println( "Entry created: " );
        System.out.println( entry.getTitle() );
        System.out.println( entry.getId() );
    }
}