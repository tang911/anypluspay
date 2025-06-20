import cn.hutool.json.JSONUtil;
import com.alipay.api.*;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.anypluspay.channelgateway.alipay.AlipayParam;
import com.anypluspay.commons.lang.utils.StringUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author wxj
 * 2025/6/3
 */
public class AliPayMainTest {

    public static void main(String[] args) throws AlipayApiException {
        AlipayParam alipayParam = new AlipayParam();
        alipayParam.setEnv("sandbox");
        alipayParam.setAppId("2021000149640040");
        alipayParam.setSignType("RSA2");
        alipayParam.setPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCYkiyYdovwZK+nhpp7ie8DX53MuSEgIqjYujiQL6xCYtT9Z3vDNeQ9LucsB6p/U+S6QrxQqxWoPIgyxZ3tyvNh76K3DEdjtZYTRznKhay6pc/+NZQfeLMU/Pt2vHZzrQU2A6qTf5yVBXjncU7R7m1bHfb164uvTMftZZ99vHO2lmhE6QaYipucOFLc4DpvnIhgnp0zkgTizFvTs02sv8s6xRWTXulyMfACy9jpUrP2wH1msKrB2hqlHUBX27Sfdyz5IwvTjumxK5vCeo9hnUBePwbuFNhzOz8P7M0jhR2Z8rRheqhHgS8UEcg4hpIoOK+PAWaTTe/93XeC/5188k2LAgMBAAECggEAI9grvAIV7HtrPTuFbcLBMZS0ORXBb5BVBN3zTgS1Pr+DdKlsTTeLEMjFLPX8G3/1O8FUBsu7AdR+1xbQfL5yoa6wFrs+xnEx+gOA01DGfO9Zx0qFK0OrJyIsXf/3AERE9iS0XYTcn7IyboWTVATlEkQe8jXNknjCe8alSuAMe3fKpkAwOTYVa1C6XMAPx6yXeitPZUY/cQEIV0SljRP4Z4DcbwyJBaaV6kFCl1PNUA/cpNTXc+5Y3F9rlS/fWwtLB8yLdbyUY/F0vsY9tYkiuD0t1mnj5XbMFNdAfgTVgNdeI/OIdKQASwlZ9w1B0QUlbhmeT8UPRXLkgbgqReqJYQKBgQDoAPpMxpG+yWAwPdK1FtPy6WwE4PnCzFtG6r8RqL7kCX5+ZTrmO0GyeSbKX2i6AjF6PReoXz0RevDqP37jc87dXQlsfeRBu8sQpzuaNJ7tgubyADINhJK1gXZbFeoOLLaQX2pZd44dijT0VK/ZRLHxOSHttkqwCIEWjMzo4Pzl+wKBgQCoWfcqD1G4q7PpRKWYgUvhuCDLjl+VLuzC5wrMvP/MN1tO8GgUq2GTGdPuBh/4D+gRxAjEyZGZo1fh1O8fTOZge/fIblK/wlYB3dPTC4bS7OdJF/0wzDJL4LWBNk1fPWb83RuwuTrdA7EVQlc7RSROc2rppt7Jd/5SnpCL1uLxsQKBgQCIyKLKBzzAnlu+tYR4TfgyPFr6WWYH6pJaMmbqU3IQYmSYwrhCK2elgPZU1IGbQVXIeyJqm5QWrURC56K5GXs6WfMHawXbl4u2ytJCLYoSAF6HCYR5IdOZfbW5KCdxqf0NC6RZETrRwb1M3G09DNpYnVWaoCJSvQDWubbKCnQwZwKBgAfk2AsX1swi+PeqKV2iSm2WFauVzHORVLa64K3C+veXbOehrC+z7YpZPq+9h8g47rfOil632OTsXNWRB3lfQLorde3nBmhJc4D45tLRiovfYeTyhAPCv2UBb2FUq4IYvfiDYaYCuXjdkHMfvjlgP5iSHuQ/19h4kOtM1hhIhjGxAoGAB4V1SdRbCOgp3HZ1s+JJ8U5G6vz2Kci+jv7jdq60nwmUtaH3WM9iT1puURzURtuJZi+8oo70/bBUCtbg60YvFqNmzMJ6joLGcdbjXGXirmRPdkrdj1KNQXfshCMR8DXm/chgJILNhV9XJ0+EPLIbzdD6MWi7zfRNHv95nlFwChQ=");
        alipayParam.setAlipayPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjI4HGdIHAxUsaXYPLKJBy8hIhkcKPxpve/oYSoKgMx5t3v/FIS9ldW58Xr2ZsM2Tbbl04D6BgBy3MIfrIQIm5id2cJg8nEog5A5/bM/IQdIzFM1YgHHxaWxfv9m42haEgG7FLmf/ncM8S8DDtLaQveEIOpfo6cbXIwmAir6vE88gcddC6RCPIqCnVHcLVivOncW7nyJ0J4yMzYkoPnpwrMpxlNqKB5QvYopFElvPivbXOFGmZFWvPHFYIQXCD5IlgyImAQDA/HacVOf2E9kCoQ7FdzAIqT2AorcprDqHlg+JeGUUkHlDDpHkXWSu5i2mXJLNS5Cfuy+c2BgZlfimaQIDAQAB");
        System.out.println(JSONUtil.toJsonStr(alipayParam));
    }

    private static void testWebPay() throws AlipayApiException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(StringUtil.randomId());
        model.setSubject("订单标题"); //订单标题
        model.setBody("订单描述"); //订单描述信息
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        model.setTotalAmount("1");  //支付金额
//        request.setNotifyUrl(""); // 设置异步通知地址
        request.setBizModel(model);

        AlipayTradePagePayResponse response = build().pageExecute(request, "GET");
        String pageRedirectionData = response.getBody();
        System.out.println(pageRedirectionData);
    }


    private static AlipayClient build() {
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        alipayConfig.setFormat("json");
        alipayConfig.setCharset("UTF-8");
        alipayConfig.setSignType("RSA2");
        alipayConfig.setAppId("2021000149640040");
        alipayConfig.setPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCYkiyYdovwZK+nhpp7ie8DX53MuSEgIqjYujiQL6xCYtT9Z3vDNeQ9LucsB6p/U+S6QrxQqxWoPIgyxZ3tyvNh76K3DEdjtZYTRznKhay6pc/+NZQfeLMU/Pt2vHZzrQU2A6qTf5yVBXjncU7R7m1bHfb164uvTMftZZ99vHO2lmhE6QaYipucOFLc4DpvnIhgnp0zkgTizFvTs02sv8s6xRWTXulyMfACy9jpUrP2wH1msKrB2hqlHUBX27Sfdyz5IwvTjumxK5vCeo9hnUBePwbuFNhzOz8P7M0jhR2Z8rRheqhHgS8UEcg4hpIoOK+PAWaTTe/93XeC/5188k2LAgMBAAECggEAI9grvAIV7HtrPTuFbcLBMZS0ORXBb5BVBN3zTgS1Pr+DdKlsTTeLEMjFLPX8G3/1O8FUBsu7AdR+1xbQfL5yoa6wFrs+xnEx+gOA01DGfO9Zx0qFK0OrJyIsXf/3AERE9iS0XYTcn7IyboWTVATlEkQe8jXNknjCe8alSuAMe3fKpkAwOTYVa1C6XMAPx6yXeitPZUY/cQEIV0SljRP4Z4DcbwyJBaaV6kFCl1PNUA/cpNTXc+5Y3F9rlS/fWwtLB8yLdbyUY/F0vsY9tYkiuD0t1mnj5XbMFNdAfgTVgNdeI/OIdKQASwlZ9w1B0QUlbhmeT8UPRXLkgbgqReqJYQKBgQDoAPpMxpG+yWAwPdK1FtPy6WwE4PnCzFtG6r8RqL7kCX5+ZTrmO0GyeSbKX2i6AjF6PReoXz0RevDqP37jc87dXQlsfeRBu8sQpzuaNJ7tgubyADINhJK1gXZbFeoOLLaQX2pZd44dijT0VK/ZRLHxOSHttkqwCIEWjMzo4Pzl+wKBgQCoWfcqD1G4q7PpRKWYgUvhuCDLjl+VLuzC5wrMvP/MN1tO8GgUq2GTGdPuBh/4D+gRxAjEyZGZo1fh1O8fTOZge/fIblK/wlYB3dPTC4bS7OdJF/0wzDJL4LWBNk1fPWb83RuwuTrdA7EVQlc7RSROc2rppt7Jd/5SnpCL1uLxsQKBgQCIyKLKBzzAnlu+tYR4TfgyPFr6WWYH6pJaMmbqU3IQYmSYwrhCK2elgPZU1IGbQVXIeyJqm5QWrURC56K5GXs6WfMHawXbl4u2ytJCLYoSAF6HCYR5IdOZfbW5KCdxqf0NC6RZETrRwb1M3G09DNpYnVWaoCJSvQDWubbKCnQwZwKBgAfk2AsX1swi+PeqKV2iSm2WFauVzHORVLa64K3C+veXbOehrC+z7YpZPq+9h8g47rfOil632OTsXNWRB3lfQLorde3nBmhJc4D45tLRiovfYeTyhAPCv2UBb2FUq4IYvfiDYaYCuXjdkHMfvjlgP5iSHuQ/19h4kOtM1hhIhjGxAoGAB4V1SdRbCOgp3HZ1s+JJ8U5G6vz2Kci+jv7jdq60nwmUtaH3WM9iT1puURzURtuJZi+8oo70/bBUCtbg60YvFqNmzMJ6joLGcdbjXGXirmRPdkrdj1KNQXfshCMR8DXm/chgJILNhV9XJ0+EPLIbzdD6MWi7zfRNHv95nlFwChQ=");
        alipayConfig.setAlipayPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjI4HGdIHAxUsaXYPLKJBy8hIhkcKPxpve/oYSoKgMx5t3v/FIS9ldW58Xr2ZsM2Tbbl04D6BgBy3MIfrIQIm5id2cJg8nEog5A5/bM/IQdIzFM1YgHHxaWxfv9m42haEgG7FLmf/ncM8S8DDtLaQveEIOpfo6cbXIwmAir6vE88gcddC6RCPIqCnVHcLVivOncW7nyJ0J4yMzYkoPnpwrMpxlNqKB5QvYopFElvPivbXOFGmZFWvPHFYIQXCD5IlgyImAQDA/HacVOf2E9kCoQ7FdzAIqT2AorcprDqHlg+JeGUUkHlDDpHkXWSu5i2mXJLNS5Cfuy+c2BgZlfimaQIDAQAB");
        AlipayClient alipayClient = null;
        try {
            alipayClient = new DefaultAlipayClient(alipayConfig);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        return alipayClient;
    }
}
