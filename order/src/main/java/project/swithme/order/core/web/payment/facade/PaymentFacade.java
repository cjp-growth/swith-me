package project.swithme.order.core.web.payment.facade;

import static project.swithme.order.core.domain.order.entity.OrderStatus.COMPLETE;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.payment.entity.PaymentType;
import project.swithme.order.core.domain.payment.entity.Toss;
import project.swithme.order.core.domain.payment.entity.command.TossPaymentCommand;
import project.swithme.order.core.web.order.application.OrderQueryUseCase;
import project.swithme.order.core.web.order.exception.OrderNotFoundException;
import project.swithme.order.core.web.payment.application.PaymentSaveUseCase;
import project.swithme.order.core.web.payment.exception.PaymentFailureException;
import project.swithme.order.core.web.payment.facade.validator.PaymentValidator;
import project.swithme.order.core.web.payment.out.PaymentPort;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final OrderQueryUseCase orderQueryUseCase;
    private final PaymentValidator paymentValidator;
    private final PaymentSaveUseCase paymentSaveUseCase;
    private final PaymentPort paymentPort;

    @Transactional
    public Long pay(
        String orderUniqueId,
        String paymentKey,
        PaymentType paymentType,
        BigDecimal amount
    ) {
        Order findOrder = orderQueryUseCase.findByUniqueId(orderUniqueId)
            .orElseThrow(OrderNotFoundException::new);

        paymentValidator.validate(findOrder, amount);

        TossPaymentCommand command = requestApproval(orderUniqueId, paymentKey, amount);
        if (command.isApproved()) {
            Toss tossPayment = save(paymentType, findOrder, command);
            findOrder.updateOrderStatus(COMPLETE);
            findOrder.updatePrice(tossPayment.getDiscountedAmount());
            return tossPayment.getId();
        }
        throw new PaymentFailureException();
    }

    private TossPaymentCommand requestApproval(
        String orderUniqueId,
        String paymentKey,
        BigDecimal amount
    ) {
        return paymentPort.requestApproval(
            paymentKey,
            orderUniqueId,
            amount
        );
    }

    private Toss save(
        PaymentType paymentType,
        Order findOrder,
        TossPaymentCommand paymentInfo
    ) {
        return paymentSaveUseCase.save(
            findOrder.getUserId(),
            findOrder.getId(),
            paymentType,
            paymentInfo
        );
    }
}
