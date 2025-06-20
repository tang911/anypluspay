package com.anypluspay.channelgateway.api.verify;

import com.anypluspay.channelgateway.ChannelGateway;
import com.anypluspay.channelgateway.request.GatewayRequest;
import com.anypluspay.channelgateway.result.GatewayResult;

/**
 * @author wxj
 * 2024/9/16
 */
public interface VerifySignGateway extends ChannelGateway<VerifyModel> {
    @Override
    default GatewayResult call(GatewayRequest<VerifyModel> gatewayRequest) throws Exception {
        VerifySignResult result = new VerifySignResult();
        notify(gatewayRequest, gatewayRequest.getContent(), result);
        return result;
    }

    void notify(GatewayRequest<VerifyModel> request, VerifyModel verifyModel, VerifySignResult result) throws Exception;

}
