package top.luoren.common.exception;

/**
 * @author luoren
 * @date 2019/9/3 17:16
 */
public class SbootException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SbootException(String message) {
        super(message);
    }

    public SbootException(Throwable cause) {
        super(cause);
    }

    public SbootException(String message, Throwable cause) {
        super(message, cause);
    }
}
