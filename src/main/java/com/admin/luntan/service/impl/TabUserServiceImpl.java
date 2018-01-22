package com.admin.luntan.service.impl;

import com.admin.luntan.api.TabUserService;
import com.admin.luntan.base.GenericBaseModel;
import com.admin.luntan.base.GenericBaseServiceImpl;
import com.admin.luntan.entity.TabUserEntity;
import com.admin.luntan.model.TabUserModel;

import javax.annotation.Resource;

/** 
 * TabUser接口的实现类
 * @author HTSS-AutoCoder
 */
public class TabUserServiceImpl extends GenericBaseServiceImpl<TabUserEntity, String>
    implements TabUserService {
  @Resource
  private TabUserModel tabUserModel;

  /**
   * 注入Model
   * @return tabUserModel
   */
  @Override
  public GenericBaseModel<TabUserEntity, String> getModel() {
    return tabUserModel;
  }
}