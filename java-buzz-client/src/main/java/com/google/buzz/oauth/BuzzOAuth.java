package com.google.buzz.oauth;

import java.net.URLConnection;
import java.net.URLEncoder;

import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
// Made signpost 1.2.1.1 compliant
//import oauth.signpost.signature.SignatureMethod;
import com.google.buzz.exception.BuzzAuthenticationException;

/**
 * This class is intended to be use as a wrapper of OAuth library tasks, facilitating the execution
 * of OAuth methods to the main Buzz class.
 * 
 * @author roberto.estivill
 */
public class BuzzOAuth
{
    /**
     * OAuth google endpoint to retrieve an access token
     */
    public static final String GET_ACCESS_TOKEN_URL = "https://www.google.com/accounts/OAuthGetAccessToken";

    /**
     * OAuth google endpoint to retrieve an request token
     */
    public static final String GET_REQUEST_TOKEN_URL = "https://www.google.com/accounts/OAuthGetRequestToken";

    /**
     * OAuth google endpoint to authorize the token
     */
    public static final String AUTHORIZE_TOKEN_URL = "https://www.google.com/buzz/api/auth/OAuthAuthorizeToken";

    /**
     * The OAuth consumer ( Third party application )
     */
    private DefaultOAuthConsumer consumer;

    /**
     * The OAuth provider ( Google ).
     */
    private OAuthProvider provider;

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
// Made signpost 1.2.1.1 compliant
//    public String getAuthenticationUrl( SignatureMethod method, String scope, String consumerKey,
//                                        String consumerSecret, String callbackUrl )
    public String getAuthenticationUrl( String scope, String consumerKey,
                                        String consumerSecret, String callbackUrl )
        throws BuzzAuthenticationException
    {

        String authUrl = null;
// Made signpost 1.2.1.1 compliant
//        consumer = new DefaultOAuthConsumer( consumerKey, consumerSecret, method );
        consumer = new DefaultOAuthConsumer( consumerKey, consumerSecret);

        try
        {
// Made signpost 1.2.1.1 compliant
//            provider = new DefaultOAuthProvider( consumer, GET_REQUEST_TOKEN_URL + "?scope="
            provider = new DefaultOAuthProvider(GET_REQUEST_TOKEN_URL + "?scope="
                + URLEncoder.encode( scope, "utf-8" ), GET_ACCESS_TOKEN_URL, AUTHORIZE_TOKEN_URL + "?scope="
                + URLEncoder.encode( scope, "utf-8" ) + "&domain=" + consumerKey );

// Made signpost 1.2.1.1 compliant
//            authUrl = provider.retrieveRequestToken( callbackUrl );
            authUrl = provider.retrieveRequestToken(consumer, callbackUrl );
        }
        catch ( Exception e )
        {
            throw new BuzzAuthenticationException( e );
        }
        return authUrl;
    }

    /**
     * Retrieves the access token that will be used to sign requests by the consumer.<br/>
     * 
     * @param accessToken to sign requets
     * @throws BuzzAuthenticationException if any OAuth error occurs
     */
    public void setAccessToken( String accessToken )
        throws BuzzAuthenticationException
    {
        try
        {
// Made signpost 1.2.1.1 compliant
//            provider.retrieveAccessToken( accessToken );
            provider.retrieveAccessToken(consumer, accessToken );
        }
        catch ( Exception e )
        {
            throw new BuzzAuthenticationException( e );
        }
    }
    
    /**
     * Set the token and secret to be used to authentication procedure.<br/>
     * 
     * @param accessToken
     * @param tokenSecret
     * @throws BuzzAuthenticationException if any OAuth error occurs
     */
    public void setTokenWithSecret(String accessToken, String tokenSecret) {
		consumer.setTokenWithSecret(accessToken, tokenSecret);
	}

    /**
     * Sign the request to be send. <br/>
     * <b>BuzzOAuth.setAccessToken</b> method should be called before this method.
     * 
     * @param request to be signed with the access token
     * @throws BuzzAuthenticationException if an OAuth problem occurs
     */
    public void signRequest( URLConnection request )
        throws BuzzAuthenticationException
    {
        try
        {
            consumer.sign( request );
        }
        catch ( Exception e )
        {
            throw new BuzzAuthenticationException( e );
        }
    }
}
