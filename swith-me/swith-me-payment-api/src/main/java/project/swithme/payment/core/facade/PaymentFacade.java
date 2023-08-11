package project.swithme.payment.core.facade;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.swithme.domain.core.payment.entity.Payment;
import project.swithme.domain.core.payment.entity.PaymentType;
import project.swithme.domain.core.payment.entity.command.PaymentCommand;
import project.swithme.payment.common.command.OrderValidationCommand;
import project.swithme.payment.core.application.PaymentSaveUseCase;
import project.swithme.payment.core.exception.PaymentFailureException;
import project.swithme.payment.core.facade.validator.PaymentValidator;
import project.swithme.payment.core.out.OrderQueryPort;
import project.swithme.payment.core.out.PaymentPort;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final OrderQueryPort orderQueryPort;
    private final PaymentValidator paymentValidator;
    private final PaymentSaveUseCase paymentSaveUseCase;
    private final PaymentPort paymentPort;

    @Transactional
    public Long requestApproval(
        String orderUniqueId,
        String paymentKey,
        PaymentType paymentType,
        BigDecimal amount
    ) {
        OrderValidationCommand findOrderCommand = orderQueryPort.findOrderByUniqueId(orderUniqueId);

        paymentValidator.validate(findOrderCommand, amount);

        PaymentCommand paymentCommand = paymentPort.requestApproval(
            paymentKey,
            orderUniqueId,
            amount
        );
        if (!paymentCommand.isApproved()) {
            throw new PaymentFailureException();
        }

        Payment newPayment = paymentSaveUseCase.save(
            findOrderCommand.getUserId(),
            findOrderCommand.getOrderId(),
            paymentType,
            paymentCommand
        );
        return newPayment.getId();
    }
}
