package com.shop.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {
    FOOD("TYPE_FOOD"),
    SNACK("TYPE_SNACK"),
    TOY("TYPE_TOY"),
    PAD("TYPE_PAD"),
    BEAUTY("TYPE_BEAUTY");

    private final String value;
}
