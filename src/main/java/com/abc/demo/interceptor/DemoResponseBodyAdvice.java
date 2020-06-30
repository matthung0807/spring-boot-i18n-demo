package com.abc.demo.interceptor;

import com.abc.demo.controller.dto.res.DemoResponse;
import com.abc.demo.controller.dto.res.ResState;
import com.abc.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class DemoResponseBodyAdvice implements ResponseBodyAdvice<DemoResponse> {

    @Value("${demo.locale}")
    private String locale;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supports(
            MethodParameter methodParameter,
            Class<? extends HttpMessageConverter<?>> aClass) {

        return methodParameter.getParameterType().equals(DemoResponse.class);
    }

    @Override
    public DemoResponse beforeBodyWrite(
            DemoResponse demoResponse,
            MethodParameter methodParameter,
            MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> aClass,
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse) {

        String code = demoResponse.getCode();

        String localeMessage = messageService.getMessage(
                ResState.getResStateByCode(code).orElse(ResState.UNKNOWN));

        demoResponse.setMessage(localeMessage);

        return demoResponse;
    }
}
