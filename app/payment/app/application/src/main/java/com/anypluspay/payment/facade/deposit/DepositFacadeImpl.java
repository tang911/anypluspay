package com.anypluspay.payment.facade.deposit;

import com.anypluspay.payment.application.AbstractPaymentService;
import com.anypluspay.payment.domain.biz.deposit.DepositOrder;
import com.anypluspay.payment.domain.process.PayProcess;
import com.anypluspay.payment.domain.repository.DepositOrderRepository;
import com.anypluspay.payment.types.PayResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 充值服务
 * @author wxj
 * 2025/5/14
 */
@RestController
public class DepositFacadeImpl extends AbstractPaymentService implements DepositFacade {

    @Autowired
    private DepositBuilder depositBuilder;

    @Autowired
    private DepositOrderRepository depositOrderRepository;

    @Override
    public DepositResponse apply(DepositRequest request) {
        DepositOrder depositOrder = depositBuilder.buildDepositOrder(request);
        PayProcess payProcess = depositBuilder.buildPayProcess(depositOrder, request.getPayerFundDetail());
        transactionTemplate.executeWithoutResult(status -> {
            depositOrderRepository.store(depositOrder);
            payProcessRepository.store(payProcess);
        });
        PayResult result = payProcessService.process(payProcess);
        return processResult(depositOrder.getPaymentId(), result);
    }

    private DepositResponse processResult(String paymentId, PayResult result) {
        DepositOrder depositOrder = depositOrderRepository.load(paymentId);
        DepositResponse response = new DepositResponse();
        response.setPaymentId(paymentId);
        response.setStatus(depositOrder.getStatus().getCode());
        response.setResult(result);
        return response;
    }
}
