package com.anypluspay.payment.facade.response;

import lombok.Data;

/**
 * @author wxj
 * 2025/2/14
 */
@Data
public class RefundResponse {

    /**
     * 支付总单号
     */
    private String paymentId;

    /**
     * 退款请求单号
     */
    private String requestId;

    /**
     * 退款单号
     */
    private String refundOrderId;

    /**
     * 原请求单号
     */
    private String origRequestId;

    /**
     * 原订单号
     */
    private String origOrderId;

    /**
     * 退款状态
     */
    private String orderStatus;

    /**
     * 返回结果码
     */
    private String resultCode;

    /**
     * 返回信息
     */
    private String resultMessage;
}
