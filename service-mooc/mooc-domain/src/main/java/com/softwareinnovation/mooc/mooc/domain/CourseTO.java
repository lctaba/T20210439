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
public class CourseTO implements Serializable {
  /**
   * 教程ID */
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
  private Date gmtUpdate;

  /**
   * 创建时间 */
  private Date gmtCreate;
}
