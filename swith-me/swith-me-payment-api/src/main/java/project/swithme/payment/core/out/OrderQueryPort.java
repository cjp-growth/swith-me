package project.swithme.payment.core.out;

import project.swithme.payment.common.command.OrderValidationCommand;

public interface OrderQueryPort {

    OrderValidationCommand findOrderByUniqueId(String orderUniqueId);
}
