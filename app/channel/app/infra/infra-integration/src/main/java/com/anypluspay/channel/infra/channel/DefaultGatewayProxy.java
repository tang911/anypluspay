package com.anypluspay.channel.infra.channel;

import com.anypluspay.channel.application.institution.gateway.GatewayProxy;
import com.anypluspay.channel.domain.channel.api.ChannelApi;
import com.anypluspay.channelgateway.ChannelGateway;
import com.anypluspay.channelgateway.request.GatewayRequest;
import com.anypluspay.channelgateway.request.RequestContent;
import com.anypluspay.channelgateway.result.GatewayResult;
import com.anypluspay.channelgateway.types.RequestResponseClass;
import com.anypluspay.commons.exceptions.BizException;
import com.anypluspay.commons.response.GlobalResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * 默认的网关代理
 *
 * @author wxj
 * 2024/7/9
 */
@Service
@Slf4j
public class DefaultGatewayProxy implements GatewayProxy {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RestTemplate restTemplate;

    private final WebClient webClient = WebClient.builder().build();


    @Override
    public GatewayResult invoke(ChannelApi channelApi, RequestContent content) {
        return switch (channelApi.getProtocol()) {
            case BEAN -> invokeBean(channelApi, content);
            case DISCOVERY -> invokeDiscovery(channelApi, content);
            case HTTP -> invokeHttp(channelApi, content);
        };
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private GatewayResult invokeBean(ChannelApi channelApi, RequestContent content) {
        ChannelGateway channelGateway = applicationContext.getBean(channelApi.getAddress(), ChannelGateway.class);
        GatewayRequest request = new GatewayRequest();
        request.setChannelCode(channelApi.getChannelCode());
        request.setChannelApiType(channelApi.getType());
        request.setContent(content);
        try {
            return channelGateway.call(request);
        } catch (Exception e) {
            log.error("调用网关异常", e);
            return buildExceptionResult(e);
        }
    }

    private GatewayResult invokeDiscovery(ChannelApi channelApi, Object content) {
        String[] url = channelApi.getAddress().split("/");
        String fullUrl = "http://" + url[0]
                + "?channelCode=" + channelApi.getChannelCode()
                + "&apiTypeCode=" + channelApi.getType().getCode()
                + "&bean=" + url[1];
        return (GatewayResult) restTemplate.postForObject(fullUrl, content, RequestResponseClass.getResponseClass(channelApi.getType()));
    }

    private GatewayResult invokeHttp(ChannelApi channelApi, Object content) {
        String fullUrl = channelApi.getAddress() + "?channelCode=" + channelApi.getChannelCode() + "&apiTypeCode=" + channelApi.getType().getCode();
        return (GatewayResult) request(fullUrl, content, RequestResponseClass.getResponseClass(channelApi.getType()));
    }

    private <T> T request(String url, Object content, Class<T> responseType) {
        Mono<T> mono = webClient.post().uri(url).bodyValue(content)
                .retrieve().bodyToMono(responseType);
        return mono.block();
    }

    private GatewayResult buildExceptionResult(Exception e) {
        GatewayResult gatewayResult = new GatewayResult();
        gatewayResult.setSuccess(false);
        if (e instanceof BizException bizException) {
            gatewayResult.setApiCode(bizException.getCode());
        } else {
            gatewayResult.setApiCode(GlobalResultCode.FAIL.getCode());
        }
        gatewayResult.setApiMessage(e.getMessage());
        gatewayResult.setReceiveTime(LocalDateTime.now());
        return gatewayResult;
    }

}