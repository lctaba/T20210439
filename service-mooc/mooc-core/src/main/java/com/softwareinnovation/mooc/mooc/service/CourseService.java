package com.softwareinnovation.mooc.mooc.service;

import cc.eamon.open.error.Assert;
import cc.eamon.open.status.StatusException;
import com.softwareinnovation.mooc.mooc.datafilter.CourseFilter;
import com.softwareinnovation.mooc.mooc.datainterface.CourseDAO;
import com.softwareinnovation.mooc.mooc.dataobject.CourseDO;
import com.softwareinnovation.mooc.mooc.model.Course;
import com.softwareinnovation.mooc.mooc.model.CourseData;
import com.softwareinnovation.mooc.mooc.model.CourseDetailMapper;
import com.softwareinnovation.mooc.mooc.model.CourseFilterMapper;
import com.softwareinnovation.mooc.mooc.model.CoursePostMapper;
import com.softwareinnovation.mooc.mooc.model.CourseSimpleMapper;
import com.softwareinnovation.mooc.mooc.model.CourseUpdateMapper;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * Author: eamon
 * Email: eamon@eamon.cc */
@Service
public class CourseService {
  @Autowired
  private CourseDAO courseDAO;

  public Course getByPK(String key) {
    CourseDO entity = courseDAO.selectByPrimaryKey(key);
    return CourseData.convert(entity, null);
  }

  public Map<String, Object> getSimpleMapByPK(String key) {
    return CourseSimpleMapper.buildMap(getByPK(key));
  }

  public Map<String, Object> getDetailMapByPK(String key) {
    Course modelEntity = getByPK(key);
    if (modelEntity == null) return null;
    return CourseDetailMapper.buildMap(modelEntity);
  }

  public Long getCountByFilter(CourseFilterMapper filterMapper) {
    return courseDAO.countByExample(CourseFilter.initDOQueryFilter(filterMapper.buildMap()));
  }

  public List<Course> getListByFilter(CourseFilterMapper filterMapper) {
    List<Course> entityList = new ArrayList<>();
    for (CourseDO entity : courseDAO.selectByExample(CourseFilter.initDOQueryFilter(filterMapper.buildMap()))) {
      entityList.add(CourseData.convert(entity, new Course()));
    }
    return entityList;
  }

  public List<Map<String, Object>> getFilterMapList(CourseFilterMapper filterMapper) {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    getListByFilter(filterMapper).forEach(entity -> entityMapList.add(CourseSimpleMapper.buildMap(entity)));
    return entityMapList;
  }

  public List<Map<String, Object>> getFilterDetailMapList(CourseFilterMapper filterMapper) {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    // query course data;
    List<Course> modelEntityList = getListByFilter(filterMapper);
    // loop & batch find to release database pressure;
    for (Course modelEntity : modelEntityList) {
    }
    // load data from local database or remote service;
    // loop assembly data;
    for (Course modelEntity : modelEntityList) {
      entityMapList.add(CourseDetailMapper.buildMap(modelEntity));
    }
    return entityMapList;
  }

  public Map<String, Object> post(Course entity) {
    entity.setId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().substring(0, 32));
    try {
      courseDAO.insertSelective(CourseData.convert(entity, new CourseDO()));
    }
    catch(DuplicateKeyException e) {
      throw new StatusException("POST_ENTITY_DUPLICATE");
    }
    return CourseDetailMapper.buildMap(getByPK(entity.getId()));
  }

  public Map<String, Object> post(CoursePostMapper postMapper) {
    Course entity = postMapper.buildEntity();
    return post(entity);
  }

  public List<Map<String, Object>> postList(List<CoursePostMapper> postMappers) {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    for (CoursePostMapper postMapper : postMappers) {
      entityMapList.add(post(postMapper));
    }
    return entityMapList;
  }

  public Map<String, Object> update(Course entity) {
    Course modelEntity = getByPK(entity.getId());
    Assert.notNull(modelEntity, "UPDATE_ENTITY_NULL");
    courseDAO.updateByPrimaryKeySelective(CourseData.convert(entity, new CourseDO()));
    return CourseDetailMapper.buildMap(entity);
  }

  public Map<String, Object> update(CourseUpdateMapper updateMapper) {
    Course entity = updateMapper.buildEntity();
    return update(entity);
  }

  public List<Map<String, Object>> updateList(List<CourseUpdateMapper> updateMappers) {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    for (CourseUpdateMapper updateMapper : updateMappers) {
      entityMapList.add(update(updateMapper));
    }
    return entityMapList;
  }

  public Integer delete(String key) {
    AtomicInteger count = new AtomicInteger();
    count.addAndGet(courseDAO.deleteByPrimaryKey(key));
    return count.get();
  }

  public Integer deleteList(List<String> keys) {
    AtomicInteger count = new AtomicInteger();
    for (String key: keys) {
      count.addAndGet(delete(key));
    }
    return count.get();
  }
}
