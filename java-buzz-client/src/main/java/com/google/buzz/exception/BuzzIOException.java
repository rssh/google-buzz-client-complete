package com.google.buzz.exception;

/**
 * BuzzException child class to wrap IO errors.
 * 
 * @author roberto.estivill
 */
public class BuzzIOException
    extends BuzzException
{

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5197669272040014458L;

    /**
     * Creates a BuzzException using another Throwable
     * 
     * @param e the cause of the exception
     */
    public BuzzIOException( Throwable e )
    {
        super( e );
    }

    /**
     * Creates a BuzzException with a custom message.
     * 
     * @param message detail of the exception
     */
    public BuzzIOException( String message )
    {
        super( message );
    }
}
