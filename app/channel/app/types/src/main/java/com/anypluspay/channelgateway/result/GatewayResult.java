package com.anypluspay.channelgateway.result;

import com.anypluspay.channel.types.ChannelExtKey;
import com.anypluspay.channel.types.result.ProcessResult;
import com.anypluspay.commons.lang.types.Extension;
import com.anypluspay.commons.lang.types.Money;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 网关专用结果，需要映射决策订单状态
 *
 * @author wxj
 * 2024/9/15
 */
@Data
public class GatewayResult extends ProcessResult {

    /**
     * 机构请求是否成功
     */
    private boolean success;

    /**
     * 提交机构订单号
     */
    private String instRequestNo;

    /**
     * 机构返回流水号
     */
    private String instResponseNo;

    /**
     * 需要返回给到收银台支付用到的信息
     */
    private Extension responseExt = new Extension();

    /**
     * 返回时间
     */
    private LocalDateTime receiveTime;

    /**
     * 实际金额
     */
    private Money realAmount;

    public void addExtra(ChannelExtKey channelExtKey, String value) {
        responseExt.add(channelExtKey.getCode(), value);
    }

    public void addExtra(String extKey, String value) {
        responseExt.add(extKey, value);
    }

}
