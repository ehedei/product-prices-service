package dev.ehedei.product.prices.service.exceptions;

/**
 * Exception thrown when the provided search criteria for product prices is invalid.
 * This exception extends IllegalArgumentException, indicating that it is a type of runtime exception
 * that occurs due to invalid arguments being passed to a method.
 */
public class InvalidPriceSearchCriteriaException extends IllegalArgumentException{
    public InvalidPriceSearchCriteriaException(final String message) {
        super(message);
    }
}
