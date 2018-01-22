package com.admin.luntan.dao.read;

import com.admin.luntan.base.GenericBaseReadDao;
import com.admin.luntan.entity.TabUserEntity;
import org.apache.ibatis.annotations.Mapper;

/** 
 * TabUser只读DAO类
 * @author HTSS-AutoCoder
 */
@Mapper
public interface TabUserReadDao extends GenericBaseReadDao<TabUserEntity, String> {

}
