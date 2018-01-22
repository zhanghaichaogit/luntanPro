package com.admin.luntan.util;

import com.admin.luntan.base.BaseConstant;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author BBF
 */
public class StringUtil extends StringUtils {
  protected static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);
  public static final String EMPTY = StringUtils.EMPTY;
  /**
   * 获取html标签内容的正则
   */
  private static final Pattern HTML_CONTENT_REG = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
  private static final Pattern IMG_REG = Pattern.compile("<img.+?>");
  private static final Pattern HTML_REG = Pattern.compile("<.+?>");

  /**
   * 隐藏构造器
   */
  protected StringUtil() {
  }

  /**
   * 转换为字节数组
   * @param str 要转换的字符串
   * @return byte[]
   */
  public static byte[] getBytes(final String str) {
    if (null != str) {
      return str.getBytes(BaseConstant.CHARSET_UTF8);
    } else {
      return null;
    }
  }

  /**
   * 字节数组转换为字符串
   * @param bytes 字节数组
   * @return 字符串
   */
  public static String toString(final byte[] bytes) {
    return new String(bytes, BaseConstant.CHARSET_UTF8);
  }

  /**
   * 是否包含字符串
   * @param str 验证字符串
   * @param strs 字符串组
   * @return 包含返回true
   */
  public static boolean inString(final CharSequence str, CharSequence... strs) {
    return ArrayUtils.contains(strs, str);
  }

  /**
   * 是否包含字符串
   * @param strs 字符串组
   * @param str 验证字符串
   * @return 包含返回true
   */
  public static boolean contains(final CharSequence[] strs, final CharSequence str) {
    return ArrayUtils.contains(strs, str);
  }

  /**
   * 替换掉HTML标签方法
   * @param html 包含HTML的字符串
   * @return 替换掉HTML的字符串
   */
  public static String replaceHtml(final String html) {
    if (isBlank(html)) {
      return EMPTY;
    }
    String regEx = "<.+?>";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(html);
    return m.replaceAll(EMPTY);
  }

  /**
   * 替换为手机识别的HTML，去掉样式及属性，保留回车。
   * @param html 包含HTML的字符串
   * @return 手机识别的HTML
   */
  public static String replaceMobileHtml(final String html) {
    if (null == html) {
      return EMPTY;
    }
    return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
  }

  /**
   * 转换为Double类型
   * @param val 要转换的内容
   * @return Double
   */
  public static Double toDouble(final Object val) {
    if (null == val) {
      return 0d;
    }
    try {
      return Double.valueOf(trim(val.toString()));
    } catch (Exception e) {
      LOGGER.error("[StringUtil.toDouble]", e);
      return 0d;
    }
  }

  /**
   * 转换为Float类型
   * @param val 要转换的内容
   * @return Float
   */
  public static Float toFloat(final Object val) {
    return toDouble(val).floatValue();
  }

  /**
   * 转换为Long类型
   * @param val 要转换的内容
   * @return Long
   */
  public static long toLong(final Object val) {
    return toDouble(val).longValue();
  }

  /**
   * 转换为Integer类型
   * @param val 要转换的内容
   * @return int
   */
  public static int toInteger(final Object val) {
    return toDouble(val).intValue();
  }

  /**
   * 字符串转int
   * <pre>
   *   NumberUtils.toInt(null) = 0
   *   NumberUtils.toInt("")   = 0
   *   NumberUtils.toInt("1")  = 1
   * </pre>
   * @param str 字符串
   * @return int 转换失败返回0
   */
  public static int toInt(final String str) {
    return NumberUtils.toInt(str);
  }

  /**
   * 字符串转int
   * <pre>
   *   StringUtil.toInt(null, 1) = 1
   *   StringUtil.toInt("", 1)   = 1
   *   StringUtil.toInt("1", 0)  = 1
   * </pre>
   * @param str 字符串
   * @param defaultValue 默认值
   * @return int
   */
  public static int toInt(final String str, final int defaultValue) {
    return NumberUtils.toInt(str, defaultValue);
  }

  /**
   * 将集合拼接成字符串
   * @param arrs 字符串数组
   * @return 拼接后的字符串
   */
  public static String Join(final String... arrs) {
    return join(arrs);
  }

  /**
   * 将集合拼接成字符串
   * @param arrs 字符串数组
   * @param underline 分隔符
   * @return 拼接后的字符串
   */
  public static String Join(final String[] arrs, final char underline) {
    return join(arrs, underline);
  }

  /**
   * 将集合拼接成字符串
   * @param list 字符串集合
   * @param underline 分隔符
   * @return 拼接后的字符串
   */
  public static String Join(final List<String> list, final char underline) {
    return join(list, underline);
  }

  /**
   * 将集合拼接成字符串
   * @param arrs 字符串数组
   * @param underline 分隔符
   * @return 拼接后的字符串
   */
  public static String Join(final String[] arrs, final String underline) {
    return join(arrs, underline);
  }

  /**
   * 将集合拼接成字符串
   * @param list 字符串集合
   * @param underline 分隔符
   * @return 拼接后的字符串
   */
  public static String Join(final List<String> list, final String underline) {
    return join(list, underline);
  }

  /**
   * URI编码
   * @param uri URI地址
   * @return 编码后的URI
   */
  public static String uriEncode(final String uri) {
    try {
      return URLEncoder.encode(uri, BaseConstant.US_ASCII);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * URI解码
   * @param uri URI地址
   * @return 解码后的URI
   */
  public static String uriDecode(final String uri) {
    try {
      // URLDecode decodes '+' to a space, as for form encoding.  So protect plus signs.
      return URLDecoder.decode(uri.replace("+", "%2B"), BaseConstant.US_ASCII);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  /**
   * 校验密码强度
   * @param password 密码
   * @return 强度值
   */
  public static int getPwdSafety(String password) {
    int baseScore = 0;
    int capitalLetters = 0;
    int lowercaseLetters = 0;
    int specialCharacters = 0;
    int numbers = 0;
    if (password.length() >= 11) {
      baseScore += 10;
    } else if (password.length() >= 6 && password.length() <= 10) {
      baseScore += 5;
    }
    for (int index = 0; index < password.length(); index++) {
      char c = password.charAt(index);
      if (c >= 48 && c <= 57) {
        numbers++;
      } else if (c >= 60 && c <= 90) {
        capitalLetters++;
      } else if (c >= 97 && c <= 122) {
        lowercaseLetters++;
      } else {
        specialCharacters++;
      }
    }
    if (((lowercaseLetters == 0 && capitalLetters > 0)
        || (lowercaseLetters > 0 && capitalLetters == 0)) && numbers > 0) {
      baseScore += 5;
    } else if (capitalLetters > 0 && lowercaseLetters > 0 && numbers > 0) {
      baseScore += 10;
    }
    if (numbers == 1 && specialCharacters > 0) {
      baseScore += 5;
    } else if (numbers >= 3 && specialCharacters > 1) {
      baseScore += 10;
    }
    if (specialCharacters > 0 && ((capitalLetters > 0 && lowercaseLetters == 0)
        || (capitalLetters == 0 && lowercaseLetters > 0))) {
      baseScore += 5;
    } else if (specialCharacters > 0 && capitalLetters > 0 && lowercaseLetters > 0) {
      baseScore += 10;
    }
    if (capitalLetters > 0 && lowercaseLetters > 0 && numbers > 0 && specialCharacters > 0) {
      baseScore += 5;
    }
    if (baseScore >= 25) {
      return 3;
    } else if (baseScore >= 15) {
      return 2;
    } else {
      return 1;
    }
  }

  /**
   * 替换掉HTML标签方法，将img替换成指定字符串
   * @param html 包含HTML的字符串
   * @param imgReplace img标签替换成的文字
   * @return 替换掉HTML的字符串
   */
  public static String replaceHtmlWithoutImg(final String html, final String imgReplace) {
    if (isBlank(html)) {
      return EMPTY;
    }
    return HTML_REG.matcher(IMG_REG.matcher(html).replaceAll(imgReplace)).replaceAll(EMPTY);
  }


  /**
   * 按照指定长度切割字符串
   * @param input 需要切割的源字符串
   * @param splitLen 指定的长度
   * @return 字符串数组
   */
  public static String[] getDivLines(final String input, final int splitLen) {
    int inputLen = input.length();
    int remainder = inputLen % splitLen;
    //计算分段数
    int number = (int) Math.floor(inputLen / splitLen);
    String[] divList = new String[(0 < remainder ? 1 + number : number)];
    for (int i = 0; i < number; i++) {
      int start = splitLen * i;
      divList[i] = input.substring(start, start + splitLen);
    }
    if (0 < remainder) {
      divList[number] = input.substring(number * splitLen, inputLen);
    }
    return divList;
  }

  /**
   * <p>判断字符串第一位是否为英文字母</p>
   * @param str 字符串
   * @return true是，flase否
   */
  public static boolean isFirstLetter(String str) {
    if (isBlank(str)) {
      return false;
    }
    return StringUtil.isAlpha(str.substring(0, 1));
  }


  /**
   * <p>Checks if any one of the CharSequences are empty ("") or null.</p>
   * * <pre>
   * StringUtils.isAnyEmpty(null)             = true
   * StringUtils.isAnyEmpty(null, "foo")      = true
   * StringUtils.isAnyEmpty("", "bar")        = true
   * StringUtils.isAnyEmpty("bob", "")        = true
   * StringUtils.isAnyEmpty("  bob  ", null)  = true
   * StringUtils.isAnyEmpty(" ", "bar")       = false
   * StringUtils.isAnyEmpty("foo", "bar")     = false
   * </pre>
   * @param str 字符串
   * @return {@code true} if any of the CharSequences are empty or null
   */
  public static boolean isAnyEmpty(final CharSequence... str) {
    return StringUtils.isAnyEmpty(str);
  }


}
