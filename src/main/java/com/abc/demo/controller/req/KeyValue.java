package com.abc.demo.controller.req;

import lombok.Data;

@Data
public class KeyValue<K, V> {

    private K key;
    private V value;

}
