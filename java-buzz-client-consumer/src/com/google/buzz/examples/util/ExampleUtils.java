package com.google.buzz.examples.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Util variables and methods for the examples programs
 * 
 * @author roberto.estivill
 */
public class ExampleUtils
{
    /**
     * The new application should implement this method in order to get the verification code from
     * the google authentication website. <br/>
     * For development, we are just lunching a browser locally and manually pasting the verification
     * code in the example console.
     * 
     * @param verificationUrl the url to get the verification code if the user is authenticated.
     * @throws IOException
     */
    public static String getVerificationCode( String verificationUrl )
        throws IOException
    {

        /**
         * Launch the browser with the verification url. ( Only for Windows OS! )
         */
        Runtime.getRuntime().exec( "rundll32 url.dll,FileProtocolHandler " + verificationUrl );

        /**
         * Ask the user to paste the verification code into the java console.
         */
        System.out.println( "Paste the verification code and press 'enter' key:" );

        /**
         * Read verification code from console.
         */
        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
        String verificationCode = br.readLine();
        return verificationCode.trim();
    }

}
