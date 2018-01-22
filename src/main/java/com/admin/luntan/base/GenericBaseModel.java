package com.admin.luntan.base;


import com.admin.luntan.util.CollectionUtil;
import com.admin.luntan.util.JsonUtil;
import com.admin.luntan.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 所有自定义Service的顶级接口，封装常用的增删查改操作
 * <ul><li>Model : 代表数据库中的表所映射的Java对象类型，必须继承BaseEntity类
 * <li>PK :代表对象的主键类型</ul>
 * @author BBF
 */
public abstract class GenericBaseModel<Model extends BaseEntity, PK> {

  protected static final Logger LOGGER = LogManager.getLogger(GenericBaseModel.class);

  private String childClassSimpleName = StringUtil.EMPTY;

  public GenericBaseModel() {
    childClassSimpleName = this.getClass().getName();// 子类名
  }

  /**
   * 抽象方法,由子类实现，完成Dao的注入
   * @return GenericDao实现类
   */
  public abstract GenericBaseReadDao<Model, PK> getDao();

  /**
   * 抽象方法,由子类实现，完成WriteDao的注入
   * @return GenericWriteDao实现类
   */
  public abstract GenericBaseWriteDao<Model, PK> getWriteDao();

  /**
   * 抽象方法,由子类实现，完成实体与数据库字段的映射
   * @return 实体字段和表字段的对应Map
   */
   
  public abstract Map<String, String> getPropertyColumn();

  /**
   * 抽象方法，获取默认排序字段
   * @return 排序集合
   */
  public abstract Map<String, String> getDefaultSort();

  /**
   * 抽象方法，获取使用GBK模式排序的数据库字段集合
   * @return 使用GBK模式排序的数据库字段集合
   */
  public abstract String[] getSortWithGBK();

  /**
   * 根据id获取数据
   * @param id 主键ID
   * @return 实体对象
   */
  public Model searchDataById(final PK id) {
    try {
      return getDao().selectByPrimaryKey(id);
    } catch (Exception e) {
      LOGGER.error("[{}.searchDataById]id = {}", this.childClassSimpleName, JsonUtil.toJson(id), e);
      throw new BusinessException(e.getMessage());
    }
  }

  /**
   * 根据条件，查询符合条件的总数
   * @param model 包含查询条件的实体对象
   * @return 总数
   */
  
  public Integer searchDataCount(final Model model) {
    try {
      return getDao().searchDataCount(model);
    } catch (Exception e) {
      LOGGER.error("[{}.searchDataCount]entity = {}", this.childClassSimpleName,
          JsonUtil.toJson(model), e);
      throw new BusinessException(e.getMessage());
    }
  }

  /**
   * 根据条件，查询符合条件的集合
   * @param model 包含查询条件的实体对象
   * @param sorts 排序字段集合
   * @return 集合
   */
  
  public List<Model> searchData(final Model model, Map<String, String> sorts) {
    try {
      String orderSql = this.getSearchOrder(CollectionUtil.isEmpty(sorts) ? getDefaultSort() : sorts);
      model.setOrderSql(orderSql);
      return getDao().searchData(model);
    } catch (Exception e) {
      LOGGER.error("[{}.searchData]entity = {}", this.childClassSimpleName, JsonUtil.toJson(model),
          e);
      throw new BusinessException(e.getMessage());
    }
  }

  /**
   * 根据条件，查询符合条件的集合
   * @param model 包含查询条件的实体对象
   * @param sorts 排序字段集合
   * @return 集合
   */
  
  public List<Model> searchAllData(final Model model, Map<String, String> sorts) {
    try {
      String orderSql = this.getSearchOrder(sorts);
      model.setOrderSql(orderSql);
      return getDao().searchAllData(model);
    } catch (Exception e) {
      LOGGER.error("[{}.searchAllData]entity = {}", this.childClassSimpleName,
          JsonUtil.toJson(model), e);
      throw new BusinessException(e.getMessage());
    }
  }

  /**
   * 删除数据
   * @param idsList 主键ID集合
   * @return 删除的数量
   */
  
  public Boolean deleteData(final List<PK> idsList) {
    if (CollectionUtil.isEmpty(idsList)) {
      throw new BusinessException("没有需要删除的数据！");
    }
    try {
      if (1 < idsList.size()) {
        getWriteDao().deleteByIds(idsList);
      } else {
        getWriteDao().deleteByPrimaryKey(idsList.get(0));
      }
      return true;
    } catch (Exception e) {
      // 当抛出RuntimeException，AOP将会自动回滚
      LOGGER.error("[{}.deleteData]idsList = {}", this.childClassSimpleName,
          JsonUtil.toJson(idsList), e);
      throw new BusinessException(e.getMessage());
    }
  }

  /**
   * 删除数据
   * <p>根据实体内的多个条件</p>
   * @param model 包含查询条件的实体对象
   * @return 删除的数量
   */
  
  public Boolean deleteData(final Model model) {
    try {
      getWriteDao().deleteSelective(model);
      return true;
    } catch (Exception e) {
      // 当抛出RuntimeException，AOP将会自动回滚
      LOGGER.error("[{}.deleteData]model = {}", this.childClassSimpleName, JsonUtil.toJson(model),
          e);
      throw new BusinessException(e.getMessage());
    }
  }

  /**
   * 保存数据
   * @param model 实体对象
   * @param isNew 是否insert
   * @return 是否成功更新
   */
  
  public Boolean saveData(final Model model, final Boolean isNew) {
    if (isNew) {
      // 添加数据
      try {
        return 1 == getWriteDao().insertSelective(model);
      } catch (Exception e) {
        LOGGER.error("[{}.searchAllData]isNew = {},entity = {}", this.childClassSimpleName, isNew,
            JsonUtil.toJson(model), e);
        throw new BusinessException(e.getMessage());
      }
    } else {
      try {
        return 1 == getWriteDao().updateByPrimaryKeySelective(model);
      } catch (Exception e) {
        LOGGER.error("[{}.saveData]isNew = {},entity = {}", this.childClassSimpleName, isNew,
            JsonUtil.toJson(model), e);
        throw new BusinessException(e.getMessage());
      }
    }
  }

  /**
   * 排序字段集合拼合成SQL语句
   * @param sorts 排序字段集合
   * @return 拼合的SQL语句，集合为空则返回空字符串
   */

  public String getSearchOrder(final Map<String, String> sorts) {
    //获取表字段与实体对象的映射
    final Map<String, String> tableColumn = getPropertyColumn();
    if (CollectionUtil.isEmpty(sorts)) {
      return StringUtil.EMPTY;
    }
    List<String> sortSql = new ArrayList<>(1);
    Iterator<Map.Entry<String, String>> it = sorts.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<String, String> entry = it.next();
      String value = entry.getValue().toLowerCase().trim();
      String column = tableColumn.get(entry.getKey());
      if (StringUtil.isBlank(column)) {
        continue;
      }
      boolean useGBK = StringUtil.contains(getSortWithGBK(), column);
      if (StringUtil.equals(BaseConstant.ASC, value)) {
        sortSql.add(String.format(useGBK ? BaseConstant.orderTempleteGBK : BaseConstant.orderTemplete, column, "asc"));
      }
      if (StringUtil.equals(BaseConstant.DESC, value)) {
        sortSql.add(String.format(useGBK ? BaseConstant.orderTempleteGBK : BaseConstant.orderTemplete, column, "desc"));
      }
    }
    return StringUtil.join(sortSql, BaseConstant.COMMA);
  }
}