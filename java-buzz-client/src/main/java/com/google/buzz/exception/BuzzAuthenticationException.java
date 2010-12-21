package com.google.buzz.exception;

/**
 * BuzzException child class to wrap OAuth and authentication errors
 * 
 * @author roberto.estivill
 */
public class BuzzAuthenticationException
    extends BuzzException
{

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -332572935152901699L;

    /**
     * Creates a BuzzException using another Throwable
     * 
     * @param e the cause of the exception
     */
    public BuzzAuthenticationException( Throwable e )
    {
        super( e );
    }

    /**
     * Creates a BuzzException with a custom message.
     * 
     * @param message detail of the exception
     */
    public BuzzAuthenticationException( String message )
    {
        super( message );
    }

    /**
     * Creates a BuzzException with a custom message and cause
     * 
     * @param message detail of the exception
     */
    public BuzzAuthenticationException( String message, Throwable e )
    {
        super( message, e );
    }

}
