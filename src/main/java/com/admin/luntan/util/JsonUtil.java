package com.admin.luntan.util;

import com.admin.luntan.base.BaseUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * JSON工具类，封装了com.alibaba.fastjson.JSON
 *
 * @author BBF
 */
public final class JsonUtil extends BaseUtil {
    /**
     * 隐藏构造器
     */
    private JsonUtil() {
    }

    /**
     * 将JSON字符串反序列化为Java对象。
     *
     * @param <T>      泛型
     * @param json     com.alibaba.fastjson.JSON对象
     * @param classOfT 类的泛型，用于简单类型的反序列化。
     *                 <p><strong>参数类型：</strong>xxx.class</p>
     * @return Java对象
     */
    public static <T> T fromJson(final JSON json, final Class<T> classOfT) {
        return JSON.toJavaObject(json, classOfT);
    }

    /**
     * 将JSON字符串反序列化为Java对象。
     *
     * @param <T>      泛型
     * @param json     JSON字符串
     * @param classOfT 类的泛型，用于简单类型的反序列化。
     *                 <p><strong>参数类型：</strong>xxx.class</p>
     * @return Java对象 <ol><li>json字符串为空时返回null；<li>json字符串为无效JSON格式时，会输出log日志，返回null；</li></ol>
     */
    public static <T> T fromJson(final String json, final Class<T> classOfT) {
        return fromJson(json, (Type) classOfT);
    }

    /**
     * 将JSON字符串反序列化为Java对象。
     *
     * @param <T>     泛型
     * @param json    JSON字符串
     * @param typeOfT 包含泛型类型。 com.alibaba.fastjson.TypeReference；
     *                <p><strong>参数类型：</strong>java.lang.reflect.Type typeOfT = new TypeReference&lt;T&gt;(){}.getType();</p>
     * @return Java对象 <ol><li>json字符串为空时返回null；<li>json字符串为无效JSON格式时，会输出log日志，返回null；</li></ol>
     */
    public static <T> T fromJson(final String json, final Type typeOfT) {
        if (StringUtil.isEmpty(json))
            return null;
        try {
            return JSON.parseObject(json, typeOfT);
        } catch (Exception e) {
            LOGGER.error("[JsonUtil2.fromJson]JSON格式错误：{}", json, e);
            return null;
        }
    }

    /**
     * 将JSON字符串反序列化为Java对象。
     *
     * @param <T>      泛型
     * @param is       JSON输入流
     * @param classOfT 类的泛型，用于简单类型的反序列化。
     *                 <p><strong>参数类型：</strong>xxx.class</p>
     * @return Java对象 <ol><li>json字符串为空时返回null；<li>json字符串为无效JSON格式时，会输出log日志，返回null；</li></ol>
     */
    public static <T> T fromJson(final InputStream is, final Class<T> classOfT) {
        return fromJson(is, (Type) classOfT);
    }

    /**
     * 将JSON字符串反序列化为Java对象。
     *
     * @param <T>     泛型
     * @param is      JSON输入流
     * @param typeOfT 包含泛型类型。 com.alibaba.fastjson.TypeReference；
     *                <p><strong>参数类型：</strong>java.lang.reflect.Type typeOfT = new TypeReference&lt;T&gt;(){}.getType();</p>
     * @return Java对象 <ol><li>json字符串为空时返回null；<li>json字符串为无效JSON格式时，会输出log日志，返回null；</li></ol>
     */
    public static <T> T fromJson(final InputStream is, final Type typeOfT) {
        try {
            return JSON.parseObject(is, typeOfT);
        } catch (Exception e) {
            LOGGER.error("[JsonUtil2.fromJson]JSON格式错误。", e);
            return null;
        }
    }

    /**
     * 将JSON字符串反序列化为List对象。
     *
     * @param <T>      泛型
     * @param json     JSON字符串
     * @param classOfT List包裹的对象的泛型。 用于简单类型的反序列化。
     * @return Java对象 <ol><li>json字符串为空时返回null；<li>json字符串为无效JSON格式时，会输出log日志，返回null；</li></ol>
     */
    public static <T> List<T> fromJsonList(final String json, final Class<T> classOfT) {
        if (StringUtil.isEmpty(json))
            return null;
        try {
            return JSON.parseArray(json, classOfT);
        } catch (Exception e) {
            //LOGGER.error("[JsonUtil2.fromJsonList]JSON格式错误：{}", json, e);
            return null;
        }
    }

    /**
     * 将Java对象序列化成JSON字符串。
     *
     * @param obj Java对象
     * @return JSON字符串
     */
    public static String toJson(final Object obj) {
        if (null == obj)
            return null;
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            //LOGGER.error("[JsonUtil2.toJson]序列化失败。", e);
            return null;
        }
    }

    /**
     * 将Java对象序列化成JSON字符串。
     *
     * @param obj      Java对象
     * @param features 序列化选项
     * @return JSON字符串
     */
    public static String toJson(final Object obj, final SerializerFeature... features) {
        if (null == obj)
            return null;
        try {
            return JSON.toJSONString(obj, features);
        } catch (Exception e) {
            //LOGGER.error("[JsonUtil2.toJson]序列化失败。", e);
            return null;
        }
    }

    /**
     * 将Java对象序列化成JSON字符串。关闭引用检测
     * <p style="color:red">关闭引用检测后，重复引用对象时就不会被$ref代替，但是在循环引用时也会导致StackOverflowError异常。</p>
     *
     * @param obj Java对象
     * @return JSON字符串
     */
    public static String toJsonNoRef(final Object obj) {
        return toJson(obj, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 将Java对象序列化成JSON字符串。
     * <p>输出null值。</p>
     * <ul><li>数值字段如果为null,输出为0,而非null</li>
     * <li>List字段如果为null,输出为[],而非null</li>
     * <li>字符类型字段如果为null,输出为"",而非null</li>
     * <li>Boolean字段如果为null,输出为false,而非null</li></ul>
     *
     * @param obj Java对象
     * @return JSON字符串
     */
    public static String toJsonWriteNull(final Object obj) {
        return toJson(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse);
    }

    /**
     * JSON字符串转换为JSONObject对象
     *
     * @param json JSON字符串
     * @return JSONObject对象
     */
    public static JSONObject toJsonObj(final String json) {
        return JSON.parseObject(json);
    }

}
