package com.google.buzz.examples;

import java.io.IOException;

import com.google.buzz.Buzz;
import com.google.buzz.examples.util.ExampleUtils;
import com.google.buzz.exception.BuzzAuthenticationException;
import com.google.buzz.exception.BuzzIOException;

/**
 * This example class demonstrates how to use the <b>Buzz.java</b> API to delete a comment in a
 * Google Buzz post.
 * 
 * @author roberto.estivill
 */
public class DeleteComment
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
     * The userId to be used in the deletion request
     */
    private static String userId;

    /**
     * The id of the activity where the comment is posted
     */
    private static String activityId;

    /**
     * The id of the comment to be deleted
     */
    private static String commentId;

    /**
     * Example main method
     * 
     * @param program arguments.<br/>
     *            <ul>
     *            <li>Consumer Key</li>
     *            <li>Consumer Secret</li>
     *            <li>User Id</li>
     *            <li>Activity Id</li>
     *            <li>Comment Id</li>
     *            </ul>
     * @throws BuzzIOException if any IO error occurs ( networking ).
     * @throws BuzzAuthenticationException if any OAuth error occurs
     * @throws IOException if an error ocurrs getting the verification code from the console.
     */
    public static void main( String[] args )
        throws BuzzAuthenticationException, IOException, BuzzIOException
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
        activityId = args[3];
        commentId = args[4];

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
         * Execute API method to delete a comment.
         */
        buzz.deleteComment( userId, activityId, commentId );

        /**
         * Print results
         */
        System.out.println( "The comment: " + commentId + " has been deleted." );
    }
}
