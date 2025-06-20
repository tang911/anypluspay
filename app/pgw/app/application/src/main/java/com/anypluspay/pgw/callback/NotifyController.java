package com.anypluspay.pgw.callback;

import cn.hutool.json.JSONUtil;
import com.anypluspay.channel.facade.NotifyFacade;
import com.anypluspay.channel.facade.OrderQueryFacade;
import com.anypluspay.channel.facade.request.NotifyRequest;
import com.anypluspay.channel.facade.result.FundResult;
import com.anypluspay.channel.types.ChannelExtKey;
import com.anypluspay.channelgateway.types.CallbackType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通知处理器
 *
 * @author wxj
 * 2025/6/11
 */
@RestController
public class NotifyController {

    @Autowired
    private NotifyFacade notifyFacade;

    @Autowired
    private OrderQueryFacade orderQueryFacade;

    /**
     * 异步通知
     *
     * @param apiType       接口类型
     * @param instRequestNo 机构请求号
     * @param request       请求参数
     * @return 结果
     */
    @RequestMapping("/notify/{apiType}/{instRequestNo}")
    public String notify(@Validated @NotBlank @PathVariable String apiType, @Validated @NotBlank @PathVariable String instRequestNo, HttpServletRequest request) {
        Map<String, String> requestData = getPostRequestParams(request);
        FundResult fundResult = notifyFacade.notify(buildNotifyRequest(apiType, instRequestNo, CallbackType.SERVER, requestData));
        return fundResult.getResponseExt().get(ChannelExtKey.NOTIFY_RESPONSE_DATA.getCode());
    }

    /**
     * 前端同步返回页面
     * alipay:http://local.anypluspay.com/api/demo/pay/return?charset=UTF-8&out_trade_no=TFI00125060400026604&method=alipay.trade.page.pay.return&total_amount=50.00&sign=hE8MjqAHtSihaRgPR5gJlL2IG7MPy291GdXdaXcXZUIQ%2FYhMGROVMBlvaKDES%2BoM8a7YqCgmHlO4EJLtRqqvZZtuZX8nsszcIbxcQ3IE%2FwRE%2BPCFHd4yA%2FqcmMK3rndarEyLqwadNityyw9GeS%2BB96cOfJX40WVzdfMxm9OedKnAdAfgvi9weKnrq%2FWN6RoneIXw1NHTf21IR%2FU0BlfCzhLWcJo8DAy%2BJGX95KBVjw0GWbNXPhDLBW4NhiqDxm5FixZ04a4PQwkpq4CtcE5tkzGQNO8YSfxEzpxfR4oVAy4Eov7Yl9RcnisQo30hb7T27Zka6WjHimwJCutd47ibfQ%3D%3D&trade_no=2025060422001460380505960218&auth_app_id=2021000149640040&version=1.0&app_id=2021000149640040&sign_type=RSA2&seller_id=2088721069460375&timestamp=2025-06-04+11%3A52%3A07
     *
     * @param instRequestNo 机构请求号
     * @param request       请求参数
     * @return 结果
     */
    @RequestMapping("/return-page/{instRequestNo}")
    @ResponseBody
    public String returnPage(@Validated @NotBlank @PathVariable String instRequestNo, HttpServletRequest request) {
        FundResult fundResult = orderQueryFacade.queryByInstRequestNo(instRequestNo, true);
        return ToStringBuilder.reflectionToString(fundResult);
    }

    private NotifyRequest buildNotifyRequest(String apiType, String instRequestNo, CallbackType callbackType, Map<String, String> requestData) {
        NotifyRequest notifyRequest = new NotifyRequest();
        notifyRequest.setInstRequestNo(instRequestNo);
        notifyRequest.setChannelApiType(apiType);
        notifyRequest.setCallbackType(callbackType.getCode());
        notifyRequest.setRequestBody(JSONUtil.toJsonStr(requestData));
        return notifyRequest;
    }

    /**
     * 获取POST请求参数
     *
     * @param request
     * @return
     */
    private Map<String, String> getPostRequestParams(HttpServletRequest request) {
        Map<String, String> requestData = new ConcurrentHashMap<>();
        for (String item : request.getParameterMap().keySet()) {
            String value = request.getParameter(item);
            // 过滤value值为空
            if (value == null) {
                continue;
            }
            requestData.put(item, value);
        }
        return requestData;
    }
}
