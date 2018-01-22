package com.admin.luntan.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 所有自定义WriteDao的顶级接口,
 * <p>封装常用的增删查改操作，WriteDAO类需要继承GenericWriteDao。</p>
 * <ul><li>Model : 代表数据库中的表所映射的Java对象类型
 * <li>PK :代表对象的主键类型</ul>
 * @author BBF
 */
public interface GenericBaseWriteDao<Model extends BaseEntity, PK> {
  /**
   * 通过主键, 删除对象
   * @param id 主键
   * @return 影响行数
   */
  int deleteByPrimaryKey(@Param("id") final PK id);

  /**
   * 多选删除
   * @param idsList 主键数组
   * @return 影响行数
   */
  int deleteByIds(final List<PK> idsList);

  /**
   * 非主键，通过对象属性, 选择性删除对象
   * @param model 对象
   * @return 影响行数
   */
  int deleteSelective(final Model model);

  /**
   * 插入对象
   * @param model 对象
   * @return 影响行数
   */
  int insert(final Model model);

  /**
   * 插入对象，选择性插入
   * @param model 对象
   * @return 影响行数
   */
  int insertSelective(final Model model);

  /**
   * 更新对象，选择性更新
   * @param model 对象
   * @return 影响行数
   */
  int updateByPrimaryKeySelective(final Model model);

  /**
   * 更新对象
   * @param model 对象
   * @return 影响行数
   */
  int updateByPrimaryKey(final Model model);

}
