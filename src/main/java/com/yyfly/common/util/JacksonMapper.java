package com.yyfly.common.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Jackson 封装工具
 *
 * @author : yyfly / mwgao@vip.qq.com
 * @date   : 2018-08-08
 */
public class JacksonMapper extends ObjectMapper {

    private static JacksonMapper mapper;

    public JacksonMapper() {
        this(Include.NON_EMPTY);
    }

    /**
     * Jackson mapper.
     *
     * @param include the include
     */
    public JacksonMapper(Include include) {
        // 设置输出时包含属性的风格
        if (include != null) {
            setSerializationInclusion(include);
        }
        // 允许单引号、允许不带引号的字段名称
        enableSimple();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 空值处理为空串
        getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
                jgen.writeString("");
            }
        });
    }


    /**
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
     *
     * @return the jackson mapper
     * @author : yyfly / 2018-09-12
     */
    public static JacksonMapper apiMapper() {

        if (mapper == null) {
            mapper = new JacksonMapper(Include.NON_EMPTY);
        }
        return mapper;
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
     *
     * @return the jackson mapper
     * @author : yyfly / 2018-09-12
     */
    public static JacksonMapper innerMapper() {

        if (mapper == null) {
            mapper = new JacksonMapper(Include.NON_DEFAULT);
        }
        return mapper;
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
     *
     * @return the jackson mapper
     * @author : yyfly / 2018-09-12
     */
    public static JacksonMapper getInstance() {

        if (mapper == null) {
            mapper = new JacksonMapper(Include.NON_DEFAULT);
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            mapper.enableSimple();
        }
        return mapper;
    }

    /**
     * 允许单引号
     * 允许不带引号的字段名称
     *
     * @return jackson mapper
     * @author : yyfly / 2018-09-12
     * @Date mw.gao / 2014-2-20
     */
    public JacksonMapper enableSimple() {
        configure(Feature.ALLOW_SINGLE_QUOTES, true);
        configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return this;
    }

    /**
     * Object可以是POJO，也可以是Collection或数组。
     * 如果对象为Null, 返回"null".
     * 如果集合为空集合, 返回"[]".
     *
     * @param object the object
     * @return the string
     * @author : yyfly / 2018-09-12
     */
    public String toJson(Object object) {

        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 反序列化POJO或简单Collection如List<String>.
     * <p>
     * 如果JSON字符串为Null或"null"字符串, 返回Null.
     * 如果JSON字符串为"[]", 返回空集合.
     * <p>
     * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String,JavaType)
     *
     * @param <T>        the type parameter
     * @param jsonString the json string
     * @param clazz      the clazz
     * @return the t
     * @author : yyfly / 2018-09-12
     * @see #fromJson(String, JavaType) #fromJson(String, JavaType)
     */
    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * jackjson把json字符串转换为Java对象的实现方法
     * <p>
     * return JackJson.fromJsonToObject(this.answersJson, new
     * TypeReference<List<StanzaAnswer>>(){});
     *
     * @param <T>           转换为的java对象
     * @param json          json字符串
     * @param typeReference jackjson自定义的类型
     * @return 返回Java对象 t
     * @author : yyfly / 2018-09-12
     */
    public <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化复杂Collection如List<Bean>, 先使用函數createCollectionType构造类型,然后调用本函数.
     *
     * @param <T>        the type parameter
     * @param jsonString the json string
     * @param javaType   the java type
     * @return the t
     * @author : yyfly / 2018-09-12
     */
    public <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 当json里只含有bean的部分属性时，更新一个已存在的bean，只覆盖该部分属性
     *
     * @param <T>        the type parameter
     * @param jsonString the json string
     * @param object     the object
     * @return t t
     * @author : yyfly / 2018-09-12
     */
    public <T> T update(String jsonString, T object) {
        try {
            return mapper.readerForUpdating(object).readValue(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转换,获取json中所包含的字符串
     *
     * @param jsonString json数据
     * @param fieldName 需要取除的字符串
     * @return string
     * @author : yyfly / 2018-09-12
     */
    public static String getFieldValue(String jsonString, String fieldName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(jsonString);
            if (node.has(fieldName)) {
                return node.get(fieldName).toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fieldName;
    }
}
