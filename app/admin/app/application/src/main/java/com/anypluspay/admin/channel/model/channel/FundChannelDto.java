package com.anypluspay.admin.channel.model.channel;

import com.anypluspay.commons.lang.std.CustomerLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 资金渠道
 * @author wxj
 * 2024/11/19
 */
@Data
public class FundChannelDto {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 所属机构代码
     */
    private String instCode;

    /**
     * 请求模式
     */
    private String requestType;

    /**
     * 请求模型名称
     */
    private String requestTypeName;

    /**
     * 支持的支付方式
     */
    private String payMethods;

    /**
     * 流入待清算账户
     */
    private String inClearingAccount;

    /**
     * 流出待清算账户
     */
    private String outClearingAccount;

    /**
     * 状态，是否可用
     */
    private Boolean enable;

    /**
     * 扩展信息
     */
    private String extra;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomerLocalDateTimeSerializer.class)
    private LocalDateTime gmtCreate;

    /**
     * 最后修改时间
     */
    @JsonSerialize(using = CustomerLocalDateTimeSerializer.class)
    private LocalDateTime gmtModified;

}
