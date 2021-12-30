package teachmakitra.microservices.spring.common.exception;

public interface ErrorCode {

    default String getValue() {
        if (this instanceof Enum<?>) {
            return ((Enum<?>)this).name();
        }
        return this.toString();
    }
}
