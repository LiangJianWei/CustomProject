package com.liangjianwei.customproject.parser;

public interface Parser<T> {
    T parser(String content, Class<T> tClass);
}