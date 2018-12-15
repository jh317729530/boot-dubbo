package com.wenyao.combiz.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.github.pagehelper.Page;
import com.wenyao.combiz.context.RequestContext;
import com.wenyao.combiz.service.JacksonService;
import com.wenyao.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class JacksonServiceImpl<T> implements JacksonService<T> {

    @Override
    public ObjectMapper getObjectMapper(boolean isPage) {

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

        if (isPage) {
            builder.serializerByType(Page.class, new JsonSerializer<Page>() {
                @Override
                public void serialize(Page value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                    com.wenyao.domain.Page page = PageUtil.page2page(value);
                    gen.writeRawValue(getObjectMapper(false).writeValueAsString(page));
                    return;
                }
            });
        }




        ObjectMapper objectMapper = builder.build();


        objectMapper.setSerializerProvider(new CustomSerializerProvider());



        return objectMapper;
    }



    @Override
    public ObjectMapper getDefaultMapper() {
        return getObjectMapper(false);
    }

    @Override
    public T readValue(String content, Class<T> clazz) {
        try {
            return getDefaultMapper().readValue(content, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<T> getListByType(String jsonStr, Class<?> collectionClass, Class<T> elementClass) {
        JavaType javaType = getCollectionType(collectionClass, elementClass);
        try {
            return getDefaultMapper().readValue(jsonStr, javaType);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    @Override
    public String writeValueAsString(Object object) {
        String value = null;
        try {
            value = getObjectMapper(false).writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return value;
    }

    /**
     * 获取泛型的Collection Type
     * @param collectionClass 泛型的Collection
     * @param elementClasses 元素类
     * @return JavaType Java类型
     */
    public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return getDefaultMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}




class CustomSerializerProvider extends DefaultSerializerProvider {

    public CustomSerializerProvider() {
        super();
    }

    public CustomSerializerProvider(CustomSerializerProvider provider, SerializationConfig config,
                                    SerializerFactory jsf) {
        super(provider, config, jsf);
    }

    @Override
    public CustomSerializerProvider createInstance(SerializationConfig config, SerializerFactory jsf) {
        return new CustomSerializerProvider(this, config, jsf);
    }

    @Override
    public JsonSerializer<Object> findNullValueSerializer(BeanProperty property) throws JsonMappingException {
        if (property.getType().getRawClass().equals(String.class)) {
            return Serializers.EMPTY_STRING_SERIALIZER_INSTANCE;
        } else {
            return super.findNullValueSerializer(property);
        }
    }
}


class Serializers extends JsonSerializer<Object> {
    public static final JsonSerializer<Object> EMPTY_STRING_SERIALIZER_INSTANCE = new EmptyStringSerializer();

    public Serializers() {}

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeString("");
    }

    private static class EmptyStringSerializer extends JsonSerializer<Object> {
        public EmptyStringSerializer() {}

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException, JsonProcessingException {
            if (RequestContext.getRequestUri().startsWith("/app") && !RequestContext.getRequestUri().startsWith("/app/wx")) {
                jsonGenerator.writeString("");
            } else {
                jsonGenerator.writeObject(o);
            }

        }
    }

}
