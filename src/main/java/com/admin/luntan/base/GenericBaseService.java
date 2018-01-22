package com.admin.luntan.base;


import com.admin.luntan.base.BaseEntity;
import com.admin.luntan.base.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * 所有自定义Service的顶级接口，封装常用的增删查改操作
 * <ul><li>Model : 代表数据库中的表所映射的Java对象类型
 * <li>PK :代表对象的主键类型</ul>
 *
 * @author BBF
 */
public interface GenericBaseService<Model extends BaseEntity, PK> {

    /**
     * 根据id获取数据
     *
     * @param id 主键ID
     * @return ServiceResult封装，实体对象
     */
    ServiceResult<Model> searchDataById(PK id);

    /**
     * 通过条件，查询数据集合，返回分页数据
     *
     * @param model 包含查询条件的对象实体
     * @return ServiceResult封装，符合条件的数据总数量
     */
    ServiceResult<Integer> searchDataCount(Model model);

    /**
     * 通过条件，查询数据集合，返回分页数据
     *
     * @param model 包含查询条件的对象实体
     * @param sorts 排序字段集合
     * @return ServiceResult封装，符合条件的数据分页集合
     */
    ServiceResult<List<Model>> searchData(Model model, Map<String, String> sorts);

    /**
     * 根据条件，查询符合条件的所有集合，不包含分页
     *
     * @param model 包含查询条件的实体对象
     * @param sorts 排序字段集合
     * @return ServiceResult封装，符合条件的数据集合
     */
    ServiceResult<List<Model>> searchAllData(Model model, Map<String, String> sorts);

    /**
     * 删除数据
     *
     * @param idsList 权限ID集合
     * @return ServiceResult封装，是否成功删除
     */
    ServiceResult<Boolean> deleteData(List<PK> idsList);

    /**
     * 删除数据
     * <p>根据实体内的多个条件</p>
     *
     * @param model 包含查询条件的实体对象
     * @return ServiceResult封装，是否成功删除
     */
    ServiceResult<Boolean> deleteData(Model model);

    /**
     * 保存数据
     *
     * @param model 权限实体对象
     * @param isNew 是否insert
     * @return ServiceResult封装，是否成功更新
     */
    ServiceResult<Boolean> saveData(Model model, boolean isNew);

}
