package com.abc.demo.controller.dto.res;

import lombok.Getter;

import java.util.Optional;

public enum ResState {

    SUCCESS("0000", "demo.res.state.success"),
    FAIL("0001", "demo.res.state.fail"),
    ERROR("9000", "demo.res.state.error"),
    NO_CONNECTION("9001", "demo.res.state.no-connection"),
    UNKNOWN("9999", "demo.res.state.unknown");

    @Getter
    private String code;
    @Getter
    private String messageKey;

    ResState(String code, String messageKey) {
        this.code = code;
        this.messageKey = messageKey;
    }

    public static Optional<ResState> getResStateByCode(String code) {
        for (ResState resState : ResState.values()) {
            if(resState.code.equals(code)) {
                return Optional.of(resState);
            }
        }
        return Optional.empty();
    }

}
