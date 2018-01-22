package com.admin.luntan.dao.write;

import com.admin.luntan.base.GenericBaseWriteDao;
import com.admin.luntan.entity.TabUserEntity;
import org.apache.ibatis.annotations.Mapper;

/** 
 * TabUser可写DAO类
 * @author HTSS-AutoCoder
 */
@Mapper
public interface TabUserWriteDao extends GenericBaseWriteDao<TabUserEntity, String> {

}
