package exceptions;

public class MissaoNaoDisponivelException extends RuntimeException {
    public MissaoNaoDisponivelException(String message) {
        super(message);
    }
}
