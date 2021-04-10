package com.softwareinnovation.mooc.mooc.model;

import cc.eamon.open.mapping.mapper.Mapper;
import cc.eamon.open.mapping.mapper.MapperConvert;
import cc.eamon.open.mapping.mapper.MapperExtra;
import cc.eamon.open.mapping.mapper.MapperIgnore;
import cc.eamon.open.mapping.mapper.MapperModify;
import com.softwareinnovation.mooc.mooc.dataobject.UserDO;
import com.softwareinnovation.mooc.mooc.domain.UserTO;
import java.lang.Integer;
import java.lang.Long;
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
@Mapper(
    value = {"data","domain","filter","post","update","simple","detail",},
    name = {"UserData","UserDomain","UserFilterMapper","UserPostMapper","UserUpdateMapper","UserSimpleMapper","UserDetailMapper",}
)
@MapperExtra(
    value = {"filter","filter","filter","filter","filter","filter",},
    name = {"page","rows","gmtUpdateFrom","gmtUpdateTo","gmtCreateFrom","gmtCreateTo",},
    type = {Long.class,Integer.class,Long.class,Long.class,Long.class,Long.class,},
    typeArgs = {"null","null","null","null","null","null",},
    list = {false,false,false,false,false,false,}
)
@MapperConvert(
    value = {"data","domain",},
    type = {UserDO.class,UserTO.class,}
)
public class User {
  /**
   * 用户ID */
  @MapperIgnore({"post",})
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
  @MapperIgnore({"post", "update","filter", })
  @MapperModify(
      value = {"post", "update", "simple", "detail", "filter",},
      modify = {"modifyTime", "modifyTime", "modifyTime", "modifyTime", "modifyTime",},
      recover = {"recoverTime", "recoverTime", "recoverTime", "recoverTime", "recoverTime",}
  )
  private Date gmtUpdate;

  /**
   * 创建时间 */
  @MapperIgnore({"post", "update","filter", })
  @MapperModify(
      value = {"post", "update", "simple", "detail", "filter",},
      modify = {"modifyTime", "modifyTime", "modifyTime", "modifyTime", "modifyTime",},
      recover = {"recoverTime", "recoverTime", "recoverTime", "recoverTime", "recoverTime",}
  )
  private Date gmtCreate;

  Long modifyTime(Date date) {
    if (date == null) return null;
    return date.getTime();
  }

  Date recoverTime(Long time) {
    if (time == null) return null;
    return new Date(time);
  }
}
