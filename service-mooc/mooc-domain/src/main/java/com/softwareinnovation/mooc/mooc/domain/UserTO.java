package com.softwareinnovation.mooc.mooc.domain;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Author: eamon
 * Email: eamon@eamon.cc */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserTO implements Serializable {
  /**
   * 用户ID */
  private String id;

  /**
   * 用户名 */
  private String name;

  /**
   * 手机号 */
  private String phone;

  /**
   * 邮箱 */
  private String email;

  /**
   * 密码 */
  private String password;

  /**
   * 用户类型 */
  private String type;

  /**
   * 头像 */
  private String head;

  /**
   * 更新时间 */
  private Date gmtUpdate;

  /**
   * 创建时间 */
  private Date gmtCreate;
}
