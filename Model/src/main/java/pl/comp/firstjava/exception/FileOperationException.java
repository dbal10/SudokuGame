package pl.comp.firstjava.exception;

public class FileOperationException extends RuntimeException {
    public FileOperationException(Throwable cause) {
        super(cause);
    }
}
