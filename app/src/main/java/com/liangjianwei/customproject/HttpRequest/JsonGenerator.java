package com.liangjianwei.customproject.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Http 传值加密
 */
public final class JsonGenerator {
    private JSONObject jsonObject;

    private JsonGenerator() {
        this.jsonObject = new JSONObject();
    }

    public static JsonGenerator getJsonGenerator() {
        return new JsonGenerator();
    }

    public void putParam(String name, String value) {
        try {
            this.jsonObject.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putParam(String name, boolean value) {
        try {
            this.jsonObject.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putParam(String name, int value) {
        try {
            this.jsonObject.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putParam(String name, long value) {
        try {
            this.jsonObject.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putParam(String name, double value) {
        try {
            this.jsonObject.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String generateJson() {
        return this.jsonObject.toString();
    }
}