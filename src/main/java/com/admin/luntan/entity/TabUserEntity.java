package com.admin.luntan.entity;


import com.admin.luntan.base.BaseEntity;

/**
 * TabUser实体类
 * <p>Table: tab_user</p>
 * @author HTSS-AutoCoder
 */
public class TabUserEntity extends BaseEntity {
  //TODO:添加已生成的串行版本标识，静态终态 serialVersionUID 字段

  private static final long serialVersionUID = 3401448111466898055L;


  /** 
   * 
   */
  private String id;

  /** 
   * 
   */
  private String username;

  /** 
   * 
   */
  private String password;

  /** 
   * 
   */
  private String nickname;

  /** 
   * 
   */
  private String telephone;

  /** 
   * 1ç”·2å¥³
   */
  private Integer sex;

  /** 
   * 1æ­£å¸¸ï¼Œ2é»‘åå•ï¼Œ3æ³¨é”€
   */
  private Integer state;

  /** 
   * 
   */
  private Integer created;


  /** 
   * 获取id属性
   * @return id
   */
  //@ExcelField(title = "id", type = ExcelEnum.BOTH, align = ExcelEnum.LEFT, sort = 10)
  public String getId(){
    return this.id;
  }

  /** 
   * 设置id属性
   * @param id id
   */
  public void setId(String id){
    this.id = id;
  }

  /** 
   * 获取username属性
   * @return username
   */
  //@ExcelField(title = "username", type = ExcelEnum.BOTH, align = ExcelEnum.LEFT, sort = 20)
  public String getUsername(){
    return this.username;
  }

  /** 
   * 设置username属性
   * @param username username
   */
  public void setUsername(String username){
    this.username = username;
  }

  /** 
   * 获取password属性
   * @return password
   */
  //@ExcelField(title = "password", type = ExcelEnum.BOTH, align = ExcelEnum.LEFT, sort = 30)
  public String getPassword(){
    return this.password;
  }

  /** 
   * 设置password属性
   * @param password password
   */
  public void setPassword(String password){
    this.password = password;
  }

  /** 
   * 获取nickname属性
   * @return nickname
   */
  //@ExcelField(title = "nickname", type = ExcelEnum.BOTH, align = ExcelEnum.LEFT, sort = 40)
  public String getNickname(){
    return this.nickname;
  }

  /** 
   * 设置nickname属性
   * @param nickname nickname
   */
  public void setNickname(String nickname){
    this.nickname = nickname;
  }

  /** 
   * 获取telephone属性
   * @return telephone
   */
  //@ExcelField(title = "telephone", type = ExcelEnum.BOTH, align = ExcelEnum.LEFT, sort = 50)
  public String getTelephone(){
    return this.telephone;
  }

  /** 
   * 设置telephone属性
   * @param telephone telephone
   */
  public void setTelephone(String telephone){
    this.telephone = telephone;
  }

  /** 
   * 获取sex属性(1ç”·2å¥³)
   * @return 1ç”·2å¥³
   */
  //@ExcelField(title = "1ç”·2å¥³", type = ExcelEnum.BOTH, align = ExcelEnum.LEFT, sort = 60)
  public Integer getSex(){
    return this.sex;
  }

  /** 
   * 设置sex属性
   * @param sex 1ç”·2å¥³
   */
  public void setSex(Integer sex){
    this.sex = sex;
  }

  /** 
   * 获取state属性(1æ­£å¸¸ï¼Œ2é»‘åå•ï¼Œ3æ³¨é”€)
   * @return 1æ­£å¸¸ï¼Œ2é»‘åå•ï¼Œ3æ³¨é”€
   */
  //@ExcelField(title = "1æ­£å¸¸ï¼Œ2é»‘åå•ï¼Œ3æ³¨é”€", type = ExcelEnum.BOTH, align = ExcelEnum.LEFT, sort = 70)
  public Integer getState(){
    return this.state;
  }

  /** 
   * 设置state属性
   * @param state 1æ­£å¸¸ï¼Œ2é»‘åå•ï¼Œ3æ³¨é”€
   */
  public void setState(Integer state){
    this.state = state;
  }

  /** 
   * 获取created属性
   * @return created
   */
  //@ExcelField(title = "created", type = ExcelEnum.BOTH, align = ExcelEnum.LEFT, sort = 80)
  public Integer getCreated(){
    return this.created;
  }

  /** 
   * 设置created属性
   * @param created created
   */
  public void setCreated(Integer created){
    this.created = created;
  }
}
