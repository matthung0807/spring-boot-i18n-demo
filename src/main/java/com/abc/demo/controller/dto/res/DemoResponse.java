package com.abc.demo.controller.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DemoResponse {

    private String message;
    private String code;

    public static DemoResponse state(ResState resState) {
        return DemoResponse.builder()
                .code(resState.getCode())
                .build();
    }

}
