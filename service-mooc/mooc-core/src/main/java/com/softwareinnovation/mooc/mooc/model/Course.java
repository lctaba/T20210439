package com.softwareinnovation.mooc.mooc.model;

import cc.eamon.open.mapping.mapper.Mapper;
import cc.eamon.open.mapping.mapper.MapperConvert;
import cc.eamon.open.mapping.mapper.MapperExtra;
import cc.eamon.open.mapping.mapper.MapperIgnore;
import cc.eamon.open.mapping.mapper.MapperModify;
import com.softwareinnovation.mooc.mooc.dataobject.CourseDO;
import com.softwareinnovation.mooc.mooc.domain.CourseTO;
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
    name = {"CourseData","CourseDomain","CourseFilterMapper","CoursePostMapper","CourseUpdateMapper","CourseSimpleMapper","CourseDetailMapper",}
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
    type = {CourseDO.class,CourseTO.class,}
)
public class Course {
  /**
   * 教程ID */
  @MapperIgnore({"post",})
  private String id;

  /**
   * 教程名 */
  private String name;

  /**
   * 视频地址 */
  private String url;

  /**
   * 封面地址 */
  private String coverUrl;

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
