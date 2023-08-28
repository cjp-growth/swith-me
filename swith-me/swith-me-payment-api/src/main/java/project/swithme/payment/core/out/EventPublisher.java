package project.swithme.payment.core.out;

public interface EventPublisher<T> {

    void publishEvent(T t);
}
