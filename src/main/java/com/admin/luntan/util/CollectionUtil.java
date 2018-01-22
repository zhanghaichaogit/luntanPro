package com.admin.luntan.util;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

/**
 * Collections工具集
 * @author BBF
 */
public final class CollectionUtil {
  /**
   * 隐藏构造器
   */
  private CollectionUtil() {
  }


  /**
   * 转换Collection所有元素(通过toString())为String, 每个元素的前面加入prefix，后面加入postfix
   * @param collection 来源集合
   * @param prefix 前缀
   * @param postfix 后缀
   * @return 组合后的字符串
   */
  public static String convertToString(final Collection<?> collection, final String prefix,
                                       final String postfix) {
    if (!isEmpty(collection)) {
      StringBuilder builder = new StringBuilder();
      for (Object o : collection) {
        builder.append(prefix).append(o).append(postfix);
      }
      return builder.toString();
    }
    return StringUtil.EMPTY;
  }

  /**
   * 判断集合是否为空
   * @param collection 来源集合
   * @return true - 空
   */
  public static boolean isEmpty(final Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  /**
   * 判断Map是否为空
   * @param map Map集合
   * @return true - 空
   */
  public static boolean isEmpty(final Map<?, ?> map) {
    return map == null || map.isEmpty();
  }

  /**
   * 取得Collection的第一个元素，如果collection为空返回null
   * @param <T> 泛型
   * @param collection 来源集合
   * @return 第一个元素
   */
  public static <T> T getFirst(final Collection<T> collection) {
    if (!isEmpty(collection)) {
      return collection.iterator().next();
    }
    return null;
  }

  /**
   * 是否包含某个值
   * @param collection 数据集合
   * @param value 值
   * @return true - 包含
   */
  public static boolean contains(final Collection<?> collection, final Object value) {
    if (!isEmpty(collection)) {
      return collection.contains(value);
    }
    return false;
  }

  /**
   * 是否包含某个值
   * @param array 数据集合
   * @param objectToFind 值
   * @return true - 包含
   */
  public static boolean contains(final Object[] array, final Object objectToFind) {
    return ArrayUtils.contains(array, objectToFind);
  }

  /**
   * 是否包含某个值
   * @param array 数据集合
   * @param objectToFind 值
   * @return true - 包含
   */
  public static boolean contains(final String[] array, final String objectToFind) {
    return ArrayUtils.contains(array, objectToFind);
  }

  /**
   * 从集合中删除某一个值
   * @param <T> 泛型
   * @param collection 数据集合
   * @param value 值
   * @return 删除后的集合
   */
  public static <T> List<T> remove(final Collection<T> collection, final T value) {
    if (!isEmpty(collection)) {
      List<T> one = singletonList(value);
      collection.removeAll(one);
      return new ArrayList<>(collection);
    }
    return new ArrayList<>(0);
  }

  /**
   * 集合去重复,保持原顺序
   * @param <T> 泛型
   * @param collection 数据集合
   * @return 去重后的集合
   */
  public static <T> List<T> distinctKeepOrder(final Collection<T> collection) {
    if (!isEmpty(collection)) {
      Set<T> set = new HashSet<>(0);
      List<T> newList = new ArrayList<>(0);
      Iterator<T> iter = collection.iterator();
      while (iter.hasNext()) {
        T element = iter.next();
        if (set.add(element)) {
          newList.add(element);
        }
      }
      set.clear();
      return newList;
    }
    return new ArrayList<>(0);
  }

  /**
   * 集合去重复，自动排序
   * @param <T> 泛型
   * @param collection 数据集合
   * @return 去重后的集合
   */
  public static <T> List<T> distinct(final Collection<T> collection) {
    if (!isEmpty(collection)) {
      Set<T> set = new HashSet<>(collection);
      return new ArrayList<>(set);
    }
    return new ArrayList<>(0);
  }

  /**
   * 返回a+b的新List
   * @param <T> 泛型
   * @param a 集合A
   * @param b 集合B
   * @return 返回a+b的新List
   */
  public static <T> List<T> union(final Collection<T> a, final Collection<T> b) {
    if (isEmpty(a)) {
      if (!isEmpty(b)) {
        return new ArrayList<>(b);
      }
    } else {
      List<T> c = new ArrayList<>(a);
      if (!isEmpty(b)) {
        c.addAll(b);
      }
      return c;
    }
    return new ArrayList<>(0);
  }

  /**
   * 返回a-b的新List
   * @param <T> 泛型
   * @param a  集合A
   * @param b 集合B
   * @return 返回a-b的新List
   */
  public static <T> List<T> subtract(final Collection<T> a, final Collection<T> b) {
    if (!isEmpty(a)) {
      List<T> c = new ArrayList<>(a);
      if (!isEmpty(b)) {
        c.removeAll(b);
      }
      return new ArrayList<>(c);
    }
    return new ArrayList<>(0);
  }

  /**
   * 返回a+b的新List（并集，去重复）
   * @param <T> 泛型
   * @param a  集合A
   * @param b 集合B
   * @return 返回a与b的并集的新List
   */
  public static <T> List<T> unionDistinct(final Collection<T> a, final Collection<T> b) {
    return distinct(union(a, b));
  }

  /**
   * 返回a与b的交集的新List
   * @param <T> 泛型
   * @param a  集合A
   * @param b 集合B
   * @return 返回a与b的交集的新List
   */
  public static <T> List<T> intersection(final Collection<T> a, final Collection<T> b) {
    List<T> c = new ArrayList<>(a);
    c.retainAll(b);
    return c;
  }

  /**
   * 数组转换成集合
   * @param <T> 泛型
   * @param arr 数组
   * @return List集合
   */
  public static <T> List<T> asList(final T[] arr) {
    if (null != arr && 0 < arr.length) {
      return Arrays.asList(arr);
    }
    return new ArrayList<>(0);
  }

  /**
   * 返回一个只包含指定对象的不可变 set。此对象可以是任意对象
   * <p>请参阅：java.util.Collections#singleton(T o)</p>
   * @param <T> 泛型
   * @param obj 任意对象
   * @return 只有一个元素的只读Set对象
   */
  public static <T> Set<T> singleton(final T obj) {
    return Collections.singleton(obj);
  }

  /**
   * 返回一个只包含指定对象的不可变 List。此对象可以是任意对象
   * <p>请参阅：java.util.Collections#singletonList(T o)</p>
   * @param <T> 泛型
   * @param obj 任意对象
   * @return 只有一个元素的只读List对象
   */
  public static <T> List<T> singletonList(final T obj) {
    return Collections.singletonList(obj);
  }

  /**
   * 返回一个不可变的映射，它只将指定键映射到指定值
   * <p>请参阅：java.util.Collections#singletonMap(K key, V value)</p>
   * @param <K> Map的key泛型
   * @param <V> Map的value泛型
   * @param key 键名
   * @param value 键值
   * @return 只有一个元素的只读Map对象
   */
  public static <K, V> Map<K, V> singletonMap(final K key, final V value) {
    return Collections.singletonMap(key, value);
  }

  /**
   * 使用Map按key进行排序
   * @param <K> 实体泛型
   * @param <V> 实体泛型
   * @param map 要排序的Map集合
   * @return 排序后的Map集合
   */
  public static <K, V> Map<K, V> sortMapByKey(Map<K, V> map) {
    if (map == null || map.isEmpty()) {
      return null;
    }
    Map<K, V> sortMap = new TreeMap<K, V>(new Comparator<K>() {
      @Override
      public int compare(K obj1, K obj2) {
        // 降序排序
        return obj2.toString().compareTo(obj1.toString());
      }
    });
    sortMap.putAll(map);
    return sortMap;
  }

  /**
   * <p>Adds all the elements of the given arrays into a new array.
   * <p>The new array contains all of the element of {@code array1} followed
   * by all of the elements {@code array2}. When an array is returned, it is always
   * a new array.
   *
   * <pre>
   * ArrayUtils.addAll(null, null)     = null
   * ArrayUtils.addAll(array1, null)   = cloned copy of array1
   * ArrayUtils.addAll(null, array2)   = cloned copy of array2
   * ArrayUtils.addAll([], [])         = []
   * ArrayUtils.addAll([null], [null]) = [null, null]
   * ArrayUtils.addAll(["a", "b", "c"], ["1", "2", "3"]) = ["a", "b", "c", "1", "2", "3"]
   * </pre>
   *
   * @param <T> the component type of the array
   * @param array1  the first array whose elements are added to the new array, may be {@code null}
   * @param array2  the second array whose elements are added to the new array, may be {@code null}
   * @return The new array, {@code null} if both arrays are {@code null}.
   *      The type of the new array is the type of the first array,
   *      unless the first array is null, in which case the type is the same as the second array.
   * @since 2.1
   * @throws IllegalArgumentException if the array types are incompatible
   */
  @SafeVarargs
  public static <T> T[] addAll(final T[] array1, final T... array2) {
    return ArrayUtils.addAll(array1, array2);
  }

}
