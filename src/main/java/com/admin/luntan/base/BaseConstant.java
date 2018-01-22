package com.admin.luntan.base;

import java.nio.charset.Charset;

/**
 * 基础相关常量
 *
 * @author BBF
 */
public interface BaseConstant {
    /**
     * ASCII : 通用变量
     */
    String US_ASCII = "US-ASCII";
    /**
     * US_ASCII : 字符集
     */
    Charset CHARSET_US_ASCII = Charset.forName(US_ASCII);
    /**
     * UTF8 : 通用变量
     */
    String UTF8 = "UTF-8";
    /**
     * UTF8 : 字符集
     */
    Charset CHARSET_UTF8 = Charset.forName(UTF8);
    /**
     * GBK : 通用变量
     */
    String GBK = "GBK";
    /**
     * GBK : 字符集
     */
    Charset CHARSET_GBK = Charset.forName(GBK);
    /**
     * ISO859-1 : 通用变量
     */
    String ISO859 = "ISO-8859-1";
    /**
     * ISO859-1 : 字符集
     */
    Charset CHARSET_ISO859 = Charset.forName(ISO859);
    /**
     * UNICODE : 通用变量
     */
    String UNICODE = "UNICODE";
    /**
     * UNICODE : 字符集
     */
    Charset CHARSET_UNICODE = Charset.forName(UNICODE);

    /**
     * 数据库升序
     */
    String ASC = "asc";

    /**
     * 数据库降序
     */
    String DESC = "desc";

    /**
     * 普通排序
     */
    String orderTemplete = " `%s` %s ";
    /**
     * 汉字排序
     */
    String orderTempleteGBK = " convert(`%s` using gbk) collate gbk_chinese_ci %s ";

    /**
     * 数据库错误信息提示，适用于MessageFormat.format
     */
    String dbErrRead = "数据库错误，无法读取数据";
    /**
     * 数据库错误信息提示，适用于MessageFormat.format
     */
    String dbErrSave = "数据库错误，无法保存数据";

    String serviceErr = "系统错误";


    /**
     * 字符串：“0”
     */
    String ZERO = "0";

    String EMPTY = "";

    /**
     * 分隔符，字符常量“,”
     */
    char COMMA = ',';
    /**
     * 分隔符，字符串常量“,”
     */
    String COMMAS = ",";
    /**
     * 分隔符，字符常量“_”
     */
    char UNDERLINE = '_';
}
