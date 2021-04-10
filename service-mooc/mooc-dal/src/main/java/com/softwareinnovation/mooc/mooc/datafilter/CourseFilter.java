package com.softwareinnovation.mooc.mooc.datafilter;

import com.softwareinnovation.mooc.mooc.dataobject.CourseDOExample;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.Date;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc */
public class CourseFilter {
  public static CourseDOExample initDOQueryFilter(Map<String, Object> filterMap) {
    CourseDOExample example = new CourseDOExample();
    CourseDOExample.Criteria criteria = example.createCriteria();
    if (filterMap.get("id") != null) criteria.andIdEqualTo((String) filterMap.get("id"));
    if (filterMap.get("name") != null) criteria.andNameLike("%" + (String) filterMap.get("name") + "%");
    if (filterMap.get("url") != null) criteria.andUrlEqualTo((String) filterMap.get("url"));
    if (filterMap.get("coverUrl") != null) criteria.andCoverUrlEqualTo((String) filterMap.get("coverUrl"));
    if (filterMap.get("gmtUpdateFrom") != null) criteria.andGmtUpdateGreaterThan(new Date((Long) filterMap.get("gmtUpdateFrom")));
    if (filterMap.get("gmtUpdateTo") != null) criteria.andGmtUpdateLessThan(new Date((Long) filterMap.get("gmtUpdateTo")));
    if (filterMap.get("gmtCreateFrom") != null) criteria.andGmtCreateGreaterThan(new Date((Long) filterMap.get("gmtCreateFrom")));
    if (filterMap.get("gmtCreateTo") != null) criteria.andGmtCreateLessThan(new Date((Long) filterMap.get("gmtCreateTo")));
    Long page = 0L;
    Integer rows = 0;
    if (filterMap.get("page") != null && filterMap.get("rows") != null) {
      page = (Long) filterMap.get("page");
      rows = (Integer) filterMap.get("rows");
    }
    if (page > 0) {
      Long offset = (page - 1L) * rows;
      example.setLimit(rows);
      example.setOffset(offset);
    }
    return example;
  }
}
