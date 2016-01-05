package com.liangjianwei.customproject.Parser;

import com.google.gson.Gson;

public class DefaultParser<T> implements Parser<T> {
    @Override
    public T parser(String content, Class<T> tClass) {
        if (content == null || content.isEmpty()) {
            return null;
        }

        Gson gson = new Gson();
        T bean = gson.fromJson(content, tClass);

        return bean;
    }
}