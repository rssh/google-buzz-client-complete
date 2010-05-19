package com.google.buzz.exception;

/**
 * BuzzException child class to wrap validation of content errors
 * 
 * @author roberto.estivill
 */
public class BuzzValidationException
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
    public BuzzValidationException( Throwable e )
    {
        super( e );
    }

    /**
     * Creates a BuzzException with a custom message.
     * 
     * @param message detail of the exception
     */
    public BuzzValidationException( String message )
    {
        super( message );
    }
}
