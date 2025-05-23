package com.anypluspay.payment.infra.persistence.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 充值单
 * </p>
 *
 * @author wxj
 * @since 2025-05-20
 */
@TableName("tb_deposit_order")
public class DepositOrderDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 支付ID
     */
    @TableId(value = "payment_id", type = IdType.NONE)
    private String paymentId;

    /**
     * 支付指令ID
     */
    private String payProcessId;

    /**
     * 会员ID
     */
    private String memberId;

    /**
     * 账户号
     */
    private String accountNo;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 币种
     */
    private String currencyCode;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 扩展信息
     */
    private String extension;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 最后修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayProcessId() {
        return payProcessId;
    }

    public void setPayProcessId(String payProcessId) {
        this.payProcessId = payProcessId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "DepositOrderDO{" +
        "paymentId = " + paymentId +
        ", payProcessId = " + payProcessId +
        ", memberId = " + memberId +
        ", accountNo = " + accountNo +
        ", amount = " + amount +
        ", currencyCode = " + currencyCode +
        ", status = " + status +
        ", extension = " + extension +
        ", memo = " + memo +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
