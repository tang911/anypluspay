package com.anypluspay.channel.facade;

import com.anypluspay.channel.facade.request.NotifyRequest;
import com.anypluspay.channel.facade.result.FundResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 通知
 *
 * @author wxj
 * 2024/9/24
 */
@FeignClient(name = ApiConstants.SERVICE_NAME, contextId = ApiConstants.SERVICE_NAME + "NotifyFacade")
public interface NotifyFacade {

    String PREFIX = "/notify";

    /**
     * 通知
     *
     * @param request 通知内容
     * @return
     */
    @PostMapping(PREFIX + "/apply")
    FundResult notify(@RequestBody NotifyRequest request);

}
