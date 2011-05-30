package com.google.buzz.oauth;

import java.net.URLConnection;
import com.google.buzz.exception.BuzzAuthenticationException;

/**
 *  Wrapper for OAuth implementation
 */
public interface BuzzOAuth
{

    /**
     * get version of OAuth
     **/
    public int getOAuthVersion();

    /**
     * This page is going to be used by the user to allow third parties applications access his/her
     * Google Buzz information and activities.
     * 
     * @param scope either BUZZ_SCOPE_READONLY or BUZZ_SCOPE_WRITE
     * @param consumerKey to retrieve the request token
     * @param consumerSecret to retrieve the request token
     * @param callbackUrl the url google should redirect the user after a successful login
     * @return the authentication url for the user to log in
     */
    public String getAuthenticationUrl( String scope, String consumerKey,
                                  String consumerSecret, String callbackUrl )
        throws BuzzAuthenticationException;


    public void setConsumerForScope(String consumerKey, 
                                    String consumerSecret,
                                    String scope)
        throws BuzzAuthenticationException;

    public void retrieveAccessToken( String code, String redirectUri )
        throws BuzzAuthenticationException;


    /**
     * Set the token and secret to be used to authentication procedure.<br/>
     * 
     * @param accessToken
     * @param tokenSecret
     * @throws BuzzAuthenticationException if any OAuth error occurs
     */
    public void setTokenWithSecret(String accessToken, String tokenSecret);

    /**
     * get token
     **/
    public String getToken();

    /**
     * get token secret.  
     * This is tokenSecret for OAuth 1 and refreshTpken for
     *                   
     **/
    public String getTokenSecret();

    /**
     * Sign the request to be send. <br/>
     * <b>BuzzOAuth.retrieveAccessToken</b> method should be called before this method.
     * 
     * @param request to be signed with the access token
     * @throws BuzzAuthenticationException if an OAuth problem occurs
     */
    public void signRequest( URLConnection request )
        throws BuzzAuthenticationException;


}
