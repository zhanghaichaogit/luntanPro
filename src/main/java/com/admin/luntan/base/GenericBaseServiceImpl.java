package com.admin.luntan.base;

import com.admin.luntan.util.JsonUtil;
import com.admin.luntan.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * GenericService的实现类
 * <p>其他的自定义 ServiceImpl, 继承自它,可以获得常用的增删查改操作, 未实现的方法有 子类各自实现</p>
 * <ul><li>Model : 代表数据库中的表所映射的Java对象类型
 * <li>PK :代表对象的主键类型</ul>
 * @author BBF
 */
public abstract class GenericBaseServiceImpl<Model extends BaseEntity, PK>
    implements GenericBaseService<Model, PK> {
  protected static final Logger LOGGER = LogManager.getLogger(GenericBaseServiceImpl.class);

  /**
   * 定义成抽象方法,由子类实现,完成Model的注入`
   * @return GenericModel实现类
   */
  public abstract GenericBaseModel<Model, PK> getModel();

  /**
   * childClassSimpleName : 子类名
   */
  private String childClassSimpleName = StringUtil.EMPTY;

  public GenericBaseServiceImpl() {
    // childClassName = this.getClass().getName();//包名.类名
    childClassSimpleName = this.getClass().getName();// 子类名
  }

  /**
   * 根据id获取数据
   * @param id 主键ID
   * @return ServiceResult封装，实体对象
   */
  public ServiceResult<Model> searchDataById(final PK id) {
    ServiceResult<Model> result = new ServiceResult<>();
    try {
      result.setResult(getModel().searchDataById(id));
      result.setSuccess(true);
    } catch (Exception e) {
      LOGGER.error("[{}.searchDataById]id = {}", this.childClassSimpleName, JsonUtil.toJson(id), e);
      result.setError(e.getMessage());
    }
    return result;
  }

  /**
   * 通过条件，查询数据集合，返回分页数据
   * @param model 包含查询条件的对象实体
   * @return ServiceResult封装，符合条件的数据总数量
   */
  public ServiceResult<Integer> searchDataCount(final Model model) {
    ServiceResult<Integer> result = new ServiceResult<>();
    try {
      result.setResult(getModel().searchDataCount(model));
      result.setSuccess(true);
    } catch (Exception e) {
      LOGGER.error("[{}.searchDataCount]entity = {}", this.childClassSimpleName,
          JsonUtil.toJson(model), e);
      result.setError(e.getMessage());
    }
    return result;
  }

  /**
   * 通过条件，查询数据集合，返回分页数据
   * @param model 包含查询条件的对象实体
   * @param sorts 排序字段集合
   * @return ServiceResult封装，符合条件的数据分页集合
   */
  public ServiceResult<List<Model>> searchData(final Model model, final Map<String, String> sorts) {
    ServiceResult<List<Model>> result = new ServiceResult<>();
    try {
      result.setResult(getModel().searchData(model, sorts));
      result.setSuccess(true);
    } catch (Exception e) {
      LOGGER.error("[{}.searchData]entity = {}", this.childClassSimpleName, JsonUtil.toJson(model),
          e);
      result.setError(e.getMessage());
    }
    return result;
  }

  /**
   * 根据条件，查询符合条件的所有集合，不包含分页
   * @param model 包含查询条件的实体对象
   * @param sorts 排序字段集合
   * @return ServiceResult封装，符合条件的数据集合
   */
  public ServiceResult<List<Model>> searchAllData(final Model model,
                                                  final Map<String, String> sorts) {
    ServiceResult<List<Model>> result = new ServiceResult<>();
    try {
      result.setResult(getModel().searchAllData(model, sorts));
      result.setSuccess(true);
    } catch (Exception e) {
      LOGGER.error("[{}.searchAllData]entity = {}", this.childClassSimpleName,
          JsonUtil.toJson(model), e);
      result.setError(e.getMessage());
    }
    return result;
  }

  /**
   * 删除数据
   * @param idsList 权限ID集合
   * @return ServiceResult封装，是否成功删除
   */
  public ServiceResult<Boolean> deleteData(final List<PK> idsList) {
    ServiceResult<Boolean> result = new ServiceResult<>();
    try {
      result.setResult(getModel().deleteData(idsList));
      result.setSuccess(true);
    } catch (Exception e) {
      LOGGER.error("[{}.deleteData]idsList = {}", this.childClassSimpleName,
          JsonUtil.toJson(idsList), e);
      result.setError(e.getMessage());
      throw new BusinessException(e.getMessage());
    }
    return result;
  }

  /**
   * 删除数据
   * <p>根据实体内的多个条件</p>
   * @param model 包含查询条件的实体对象
   * @return ServiceResult封装，是否成功删除
   */
  public ServiceResult<Boolean> deleteData(final Model model) {
    ServiceResult<Boolean> result = new ServiceResult<>();
    try {
      result.setResult(getModel().deleteData(model));
      result.setSuccess(true);
    } catch (Exception e) {
      LOGGER.error("[{}.deleteData]model = {}", this.childClassSimpleName, JsonUtil.toJson(model),
          e);
      result.setError(e.getMessage());
      throw new BusinessException(e.getMessage());
    }
    return result;
  }

  /**
   * 保存数据
   * @param model 权限实体对象
   * @param isNew 是否insert
   * @return ServiceResult封装，是否成功更新
   */
  public ServiceResult<Boolean> saveData(final Model model, final boolean isNew) {
    ServiceResult<Boolean> result = new ServiceResult<>();
    try {
      result.setResult(getModel().saveData(model, isNew));
      result.setSuccess(true);
    } catch (Exception e) {
      LOGGER.error("[{}.saveData]isNew = {},entity = {}", this.childClassSimpleName, isNew,
          JsonUtil.toJson(model), e);
      result.setError(e.getMessage());
      throw new BusinessException(e.getMessage());
    }
    return result;
  }
}
