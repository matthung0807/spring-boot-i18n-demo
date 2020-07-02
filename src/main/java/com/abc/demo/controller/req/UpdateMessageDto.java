package com.abc.demo.controller.req;

import lombok.Data;

import java.util.List;

@Data
public class UpdateMessageDto {

    private String languageTag;
    private List<KeyValue<String, String>> keyValueList;

}