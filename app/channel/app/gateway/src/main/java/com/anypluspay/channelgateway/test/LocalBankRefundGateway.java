package com.anypluspay.channelgateway.test;

import cn.hutool.core.lang.Assert;
import com.anypluspay.channel.types.test.TestConstants;
import com.anypluspay.channel.types.test.TestFlag;
import com.anypluspay.channelgateway.api.refund.RefundContent;
import com.anypluspay.channelgateway.api.refund.RefundGateway;
import com.anypluspay.channelgateway.request.GatewayRequest;
import com.anypluspay.channelgateway.result.GatewayResult;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/7/14
 */
@Service
public class LocalBankRefundGateway extends AbstractLocalBankGateway implements RefundGateway {
    @Override
    public void refund(GatewayRequest<RefundContent> gatewayRequest, RefundContent refundOrder, GatewayResult result) {
        result.setSuccess(true);
        Assert.isTrue(refundOrder.getOrigInstRequestNo() != null || refundOrder.getOrigInstResponseNo() != null);
        if (isTest(refundOrder)) {
            testProcess(refundOrder, result);
        }
    }

    private void testProcess(RefundContent refundOrder, GatewayResult result) {
        TestFlag testFlag = getTestFlag(refundOrder);
        if (TestConstants.S.equals(testFlag.getD())) {
            result.setApiCode("SUCCESS");
        } else if (TestConstants.P.equals(testFlag.getD())) {
            result.setApiCode("PROCESS");
        } else if (TestConstants.F.equals(testFlag.getD())) {
            result.setApiCode("PARAM_ERROR");
            result.setApiMessage("参数错误");
        } else {
            result.setApiCode("unkown");
            result.setApiMessage("未知");
        }
    }
}
