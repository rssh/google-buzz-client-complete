package com.google.buzz.exception;

/**
 * BuzzException child class to wrap parsing errors
 * 
 * @author roberto.estivill
 */
public class BuzzParsingException
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
    public BuzzParsingException( Throwable e )
    {
        super( e );
    }

    /**
     * Creates a BuzzException with a custom message.
     * 
     * @param message detail of the exception
     */
    public BuzzParsingException( String message )
    {
        super( message );
    }
}
