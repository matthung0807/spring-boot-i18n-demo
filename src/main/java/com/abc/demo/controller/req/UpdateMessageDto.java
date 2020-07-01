package com.abc.demo.controller.req;

import lombok.Data;

@Data
public class UpdateMessageDto {

    private String languageTag;
    private String key;
    private String value;

}
