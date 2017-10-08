package common
/**
 * Created by pablo on 07/10/17.
 */

class ServiceException extends RuntimeException {
    def ServiceException() {}

    def ServiceException(String message) {
        super(message)
    }
}
