package com.admin.luntan.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 所有自定义Dao的顶级接口
 * <p>封装常用的增删查改操作，DAO类需要继承GenericDao</p>
 * <ul><li>Model : 代表数据库中的表所映射的Java对象类型
 * <li>PK :代表对象的主键类型</ul>
 * @author BBF
 */
public interface GenericBaseReadDao<Model extends BaseEntity, PK> {
  /**
   * 通过主键, 查询对象
   * @param id 主键
   * @return 实体对象
   */
  Model selectByPrimaryKey(@Param("id") final PK id);

  /**
   * 通过条件，查询数据总量
   * @param model 包含查询条件的对象实体
   * @return 数量
   */
  int searchDataCount(final Model model);

  /**
   * 通过条件，查询数据集合，返回分页数据
   * @param model 包含查询条件的对象实体
   * @return 实体集合
   */
  List<Model> searchData(final Model model);

  /**
   * 通过条件，查询数据集合，返回全部数据
   * @param model 包含查询条件的对象实体
   * @return 实体集合
   */
  List<Model> searchAllData(final Model model);
}
