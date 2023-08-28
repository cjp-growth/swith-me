package project.swithme.notification.core.out;

public interface OrderQueryPort {

    String findOrderTitleByUniqueId(String orderUniqueId);
}
