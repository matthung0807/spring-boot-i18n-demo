package com.abc.demo.controller.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
public class DemoResponse {

    private String message;
    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public static DemoResponse success(Object data) {
        Objects.requireNonNull(data);

        return DemoResponse.builder()
                .data(data)
                .code(ResState.SUCCESS.getCode())
                .build();
    }

    public static DemoResponse success() {
        return DemoResponse.builder()
                .code(ResState.SUCCESS.getCode())
                .build();
    }

    public static DemoResponse empty() {
        return success();
    }

    public static DemoResponse fail() {
        return DemoResponse.builder()
                .code(ResState.FAIL.getCode())
                .build();
    }

    public static DemoResponse error() {
        return DemoResponse.builder()
                .code(ResState.ERROR.getCode())
                .build();
    }

    public static DemoResponse error(ResState resState) {
        return DemoResponse.builder()
                .code(resState.getCode())
                .build();
    }

}
