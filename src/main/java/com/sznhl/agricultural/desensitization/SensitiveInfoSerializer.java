package com.sznhl.agricultural.desensitization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.Objects;

public class SensitiveInfoSerializer extends JsonSerializer<String> implements
        ContextualSerializer {
    private SensitiveType type;

    public SensitiveInfoSerializer() {
    }

    public SensitiveInfoSerializer( SensitiveType type) {
        this.type = type;
    }

    @Override
    public void serialize( String s,  JsonGenerator jsonGenerator,
                           SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        switch (this.type) {
            case CHINESE_NAME: {
                jsonGenerator.writeString(SensitiveInfoUtil.chineseName(s));
                break;
            }
            case ID_CARD: {
                jsonGenerator.writeString(SensitiveInfoUtil.idCardNum(s));
                break;
            }
            case MOBILE_PHONE: {
                jsonGenerator.writeString(SensitiveInfoUtil.mobilePhone(s));
                break;
            }
        }

    }

    @Override
    public JsonSerializer<?> createContextual( SerializerProvider serializerProvider,
                                               BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) { // 为空直接跳过
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) { // 非 String 类直接跳过
                SensitiveInfo sensitiveInfo = beanProperty.getAnnotation(SensitiveInfo.class);
                if (sensitiveInfo == null) {
                    sensitiveInfo = beanProperty.getContextAnnotation(SensitiveInfo.class);
                }
                if (sensitiveInfo != null) { // 如果能得到注解，就将注解的 value 传入 SensitiveInfoSerialize
                    return new SensitiveInfoSerializer(sensitiveInfo.value());
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(beanProperty);
    }
}
