package com.google.buzz.exception;

/**
 * Main Buzz Exception
 * 
 * @author roberto.estivill
 */
public class BuzzException
    extends Exception
{

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7077261136696049203L;

    /**
     * Creates a BuzzException using another Throwable
     * 
     * @param e the cause of the exception
     */
    public BuzzException( Throwable e )
    {
        super( e );
    }

    /**
     * Creates a BuzzException with a custom message.
     * 
     * @param message detail of the exception
     */
    public BuzzException( String message )
    {
        super( message );
    }

    /**
     * Creates a BuzzException with a custom message and cause.
     * 
     * @param message detail of the exception
     */
    public BuzzException( String message, Throwable cause )
    {
        super( message, cause );
    }


}

