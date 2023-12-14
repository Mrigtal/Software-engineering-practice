package com.example.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;

/**
 * 统一配置 Long类型使用 ToStringSerializer序列化
 */
@Configuration
public class JsonConfig extends SimpleModule {
    public JsonConfig() {
      this.addSerializer(Long.class, ToStringSerializer.instance);
      this.addSerializer(Long.TYPE, ToStringSerializer.instance);
    }
}