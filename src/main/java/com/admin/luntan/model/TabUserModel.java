package com.admin.luntan.model;

import com.admin.luntan.base.BaseUtil;
import com.admin.luntan.base.GenericBaseModel;
import com.admin.luntan.base.GenericBaseReadDao;
import com.admin.luntan.base.GenericBaseWriteDao;
import com.admin.luntan.dao.read.TabUserReadDao;
import com.admin.luntan.dao.write.TabUserWriteDao;
import com.admin.luntan.entity.TabUserEntity;
import com.admin.luntan.util.CollectionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/** 
 * TabUser逻辑处理类
 * @author HTSS-AutoCoder
 */
@Service
public class TabUserModel extends GenericBaseModel<TabUserEntity, String> {
  @Resource
  private TabUserReadDao tabUserReadDao;
  @Resource
  private TabUserWriteDao tabUserWriteDao;
  /**
   * 实体与数据表字段的映射
   */
  private static final Map<String, String> propertyColumn;
  /**
   * 排序集合
   */
  private static final Map<String, String> sortColumn;

  /**
   * 使用GBK模式排序的数据库字段名集合
   */
  private static final String[] sortWithGBK = { };

  static {
    propertyColumn = new HashMap<>(5);
    propertyColumn.put("id", "id");
    propertyColumn.put("username", "username");
    propertyColumn.put("password", "password");
    propertyColumn.put("nickname", "nickname");
    propertyColumn.put("telephone", "telephone");
    propertyColumn.put("sex", "sex");
    propertyColumn.put("state", "state");
    propertyColumn.put("created", "created");
    //使用单实例，如果多字段，需要用HashMap实例对象
    sortColumn = CollectionUtil.singletonMap("id", BaseUtil.ASC);
  }

  /**
   * 注入只读DAO
   * @return tabUserReadDao
   */
  @Override
  public GenericBaseReadDao<TabUserEntity, String> getDao() {
    return tabUserReadDao;
  }

  /**
   * 注入可写DAO
   * @return tabUserWriteDao
   */
  @Override
  public GenericBaseWriteDao<TabUserEntity, String> getWriteDao() {
    return tabUserWriteDao;
  }

  /**
   * 注入实体与数据库字段的映射
   * @return key：实体属性名，value：数据库字段名
   */
  @Override
  public Map<String, String> getPropertyColumn() {
    return propertyColumn;
  }

  /**
   * 注入排序集合
   * @return 排序集合
   */
  @Override
  public Map<String, String> getDefaultSort() {
    return sortColumn;
  }

  /**
   * 获取使用GBK模式排序的数据库字段集合
   * @return 使用GBK模式排序的数据库字段集合
   */
  @Override
  public String[] getSortWithGBK() {
    return sortWithGBK;
  }
}
