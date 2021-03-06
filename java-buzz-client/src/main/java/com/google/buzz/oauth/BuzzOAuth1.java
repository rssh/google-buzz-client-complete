package com.google.buzz.oauth;

import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;

import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
// Made signpost 1.2.1.1 compliant
//import oauth.signpost.signature.SignatureMethod;
import com.google.buzz.exception.BuzzAuthenticationException;

/**
 * This class is intended to be use as a wrapper of OAuth library tasks, facilitating the execution
 * of OAuth methods to the main Buzz class.
 * 
 * @author roberto.estivill
 */
public class BuzzOAuth1 implements BuzzOAuth
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


    public int getOAuthVersion()
    { return 1; }

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
        throws BuzzAuthenticationException
    {

        String authUrl = null;

        setConsumerForScope(consumerKey, consumerSecret, scope);
        try
        {
            authUrl = provider.retrieveRequestToken(consumer, callbackUrl );
        }
        catch ( Exception e )
        {
            throw new BuzzAuthenticationException( e );
        }
        return authUrl;
    }

    public void setConsumerForScope(String consumerKey, 
                                    String consumerSecret,
                                    String scope) 
        throws BuzzAuthenticationException
    {
      try {
       consumer = new DefaultOAuthConsumer( consumerKey, consumerSecret);
       //provider = new DefaultOAuthProvider(
       // see http://code.google.com/p/oauth-signpost/issues/detail?id=60
       provider = new CommonsHttpOAuthProvider(
                          GET_REQUEST_TOKEN_URL + "?scope="
                              + URLEncoder.encode( scope, "utf-8" ), 
                          GET_ACCESS_TOKEN_URL, AUTHORIZE_TOKEN_URL + "?scope="
                              + URLEncoder.encode( scope, "utf-8" ) 
                                   + "&domain=" + consumerKey );
     } catch (UnsupportedEncodingException ex) {
         // impossible.
         throw new BuzzAuthenticationException("excepton dring setting provider",ex);
     }
    }

    /**
     * Retrieves the access token that will be used to sign requests by the consumer.<br/>
     * 
     * @param code for retrieve accessToken 
     * @throws BuzzAuthenticationException if any OAuth error occurs
     */
    public void retrieveAccessToken( String code, String redirectUri )
        throws BuzzAuthenticationException
    {
        try
        {
            provider.retrieveAccessToken(consumer, code );
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
     * <b>BuzzOAuth.retrieveAccessToken</b> method should be called before this method.
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


    public String getToken()
    { return consumer.getToken(); }

    public String getTokenSecret()
    { return consumer.getTokenSecret(); }

}
