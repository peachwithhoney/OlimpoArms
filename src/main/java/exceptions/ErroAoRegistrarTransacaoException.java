package exceptions;

public class ErroAoRegistrarTransacaoException extends RuntimeException {
    public ErroAoRegistrarTransacaoException(String message) {
        super(message);
    }
}
