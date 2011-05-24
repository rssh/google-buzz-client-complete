package com.google.buzz.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.google.buzz.exception.BuzzIOException;

public class BuzzIO
{
    /**
     * The buffer size to be use when reading from the response
     */
    private static final int BUFFER_SIZE = 4096;

    /**
     * Content type header name
     */
    private static final String HEADER_CONTENT_TYPE = "Content-Type";

    /**
     * Content length header name
     */
    private static final String HEADER_CONTENT_LENGTH = "Content-Length";

    /**
     * Http method constant for PUT
     */
    public static final String HTTP_METHOD_PUT = "PUT";

    /**
     * Http method constant for DELETE
     */
    public static final String HTTP_METHOD_DELETE = "DELETE";

    /**
     * Http method constant for POST
     */
    public static final String HTTP_METHOD_POST = "POST";

    /**
     * Http method constant for GET
     */
    public static final String HTTP_METHOD_GET = "GET";

    /**
     * Create the request object.
     * 
     * @param feedUrl of the url to be requested
     * @param httpMethod to use for the request
     * @param headers for the request
     * @return the HttpsUrlConnection object
     * @throws BuzzIOException if any IO error occurs ( networking ).
     */
    public static HttpsURLConnection createRequest( String feedUrl, String httpMethod, Map<String, String> headers )
        throws BuzzIOException
    {
        HttpsURLConnection con;
        try
        {
            URL url = new URL( feedUrl );

            con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod( httpMethod );

            if ( headers != null && !headers.isEmpty() )
            {
                for ( String key : headers.keySet() )
                {
                    con.setRequestProperty( key, headers.get( key ) );
                }
            }
        }
        catch ( IOException e )
        {
            throw new BuzzIOException( e );
        }
        return con;
    }

    /**
     * Convinient overloaded method with no headers by default.<br/>
     * Used to post/delete information such as activities and comments.
     * 
     * @param feedUrl of the url to be requested
     * @param httpMethod to be used.
     * @return the HttpsUrlConnection object
     * @throws BuzzIOException if any IO error occurs ( networking ).
     */
    public static HttpsURLConnection createRequest( String feedUrl, String httpMethod )
        throws BuzzIOException
    {
        return createRequest( feedUrl, httpMethod, null );
    }

    /**
     * Convinient overloaded method with <b>HTTP_METHOD_GET</b> as default and no headers.<br/>
     * Used to retrieve all the feeds. ( @following, @followers, @public, @consumption, @self ).
     * 
     * @param feedUrl of the url to be requested
     * @return the HttpsUrlConnection object
     * @throws BuzzIOException if any IO error occurs ( networking ).
     */
    public static HttpsURLConnection createRequest( String feedUrl )
        throws BuzzIOException
    {
        return createRequest( feedUrl, HTTP_METHOD_GET, null );
    }

    /**
     * Adds the body and the correspondent headers to the request object.
     * 
     * @param request to use
     * @param body to be added to the request
     * @return the request with the body added
     * @throws BuzzIOException if any IO error occurs ( networking ).
     */
    public static HttpsURLConnection addBody( HttpsURLConnection request, String body )
        throws BuzzIOException
    {
        try
        {
            if ( body != null && !body.equals( "" ) )
            {
                request.setRequestProperty( HEADER_CONTENT_LENGTH, String.valueOf( body.length() ) );
                if (request.getRequestProperty(HEADER_CONTENT_TYPE)==null) {
                  request.setRequestProperty( HEADER_CONTENT_TYPE, "application/atom+xml" );
                }
                request.setDoOutput( true );
                OutputStream outStream = request.getOutputStream();
                outStream.write( body.getBytes() );
                outStream.flush();
                outStream.close();
                request.disconnect();
            }
        }
        catch ( IOException e )
        {
            throw new BuzzIOException( e );
        }
        return request;
    }

    /**
     * Sends the request over the wire and read the response.
     * 
     * @param request to be send
     * @return the response body
     * @throws BuzzIOException if any IO error occurs ( networking ).
     */
    public static String send( HttpsURLConnection request )
        throws BuzzIOException
    {
	//StringBuilder response = null;
        String response = null;
        try
        {
            request.connect();
            InputStream is = request.getInputStream();
            response = inputStreamToString(is,"UTF-8");
        }
        catch ( Exception e )
        {
            InputStream es = request.getErrorStream();
            if (es==null) {
               throw new BuzzIOException( e );
            } else {
               try {
                 response = inputStreamToString(es,"UTF-8");
               }catch(IOException ex) {
                  // suppress second-level exception.
                  ;
               }
               throw new BuzzIOException(response,e);
            }
        }
        return response.toString();
    }

    public static String inputStreamToString(InputStream is, String encoding) 
                                                           throws IOException 
    {
      InputStreamReader in;
      if (encoding == null)
          in = new InputStreamReader(is);
      else
         in = new InputStreamReader(is, encoding);

      BufferedReader br = new BufferedReader(in);
      String line = "";
      StringBuilder sb = new StringBuilder();
      while ((line = br.readLine()) != null)
                        sb.append(line);

      return sb.toString();
   }


}
