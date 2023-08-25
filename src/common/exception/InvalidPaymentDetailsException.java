package common.exception;

public class InvalidPaymentDetailsException extends RuntimeException {
	public InvalidPaymentDetailsException() {}
	public InvalidPaymentDetailsException(String msg, Throwable err) {
		super(msg, err);
	}
}