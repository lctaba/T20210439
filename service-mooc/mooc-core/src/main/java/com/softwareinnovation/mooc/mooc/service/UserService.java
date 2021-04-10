package com.softwareinnovation.mooc.mooc.service;

import cc.eamon.open.error.Assert;
import cc.eamon.open.status.StatusException;
import com.softwareinnovation.mooc.mooc.datafilter.UserFilter;
import com.softwareinnovation.mooc.mooc.datainterface.UserDAO;
import com.softwareinnovation.mooc.mooc.dataobject.UserDO;
import com.softwareinnovation.mooc.mooc.model.User;
import com.softwareinnovation.mooc.mooc.model.UserData;
import com.softwareinnovation.mooc.mooc.model.UserDetailMapper;
import com.softwareinnovation.mooc.mooc.model.UserFilterMapper;
import com.softwareinnovation.mooc.mooc.model.UserPostMapper;
import com.softwareinnovation.mooc.mooc.model.UserSimpleMapper;
import com.softwareinnovation.mooc.mooc.model.UserUpdateMapper;
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
public class UserService {
  @Autowired
  private UserDAO userDAO;

  public User getByPK(String key) {
    UserDO entity = userDAO.selectByPrimaryKey(key);
    return UserData.convert(entity, null);
  }

  public Map<String, Object> getSimpleMapByPK(String key) {
    return UserSimpleMapper.buildMap(getByPK(key));
  }

  public Map<String, Object> getDetailMapByPK(String key) {
    User modelEntity = getByPK(key);
    if (modelEntity == null) return null;
    return UserDetailMapper.buildMap(modelEntity);
  }

  public Long getCountByFilter(UserFilterMapper filterMapper) {
    return userDAO.countByExample(UserFilter.initDOQueryFilter(filterMapper.buildMap()));
  }

  public List<User> getListByFilter(UserFilterMapper filterMapper) {
    List<User> entityList = new ArrayList<>();
    for (UserDO entity : userDAO.selectByExample(UserFilter.initDOQueryFilter(filterMapper.buildMap()))) {
      entityList.add(UserData.convert(entity, new User()));
    }
    return entityList;
  }

  public List<Map<String, Object>> getFilterMapList(UserFilterMapper filterMapper) {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    getListByFilter(filterMapper).forEach(entity -> entityMapList.add(UserSimpleMapper.buildMap(entity)));
    return entityMapList;
  }

  public List<Map<String, Object>> getFilterDetailMapList(UserFilterMapper filterMapper) {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    // query user data;
    List<User> modelEntityList = getListByFilter(filterMapper);
    // loop & batch find to release database pressure;
    for (User modelEntity : modelEntityList) {
    }
    // load data from local database or remote service;
    // loop assembly data;
    for (User modelEntity : modelEntityList) {
      entityMapList.add(UserDetailMapper.buildMap(modelEntity));
    }
    return entityMapList;
  }

  public Map<String, Object> post(User entity) {
    entity.setId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().substring(0, 32));
    try {
      userDAO.insertSelective(UserData.convert(entity, new UserDO()));
    }
    catch(DuplicateKeyException e) {
      throw new StatusException("POST_ENTITY_DUPLICATE");
    }
    return UserDetailMapper.buildMap(getByPK(entity.getId()));
  }

  public Map<String, Object> post(UserPostMapper postMapper) {
    User entity = postMapper.buildEntity();
    return post(entity);
  }

  public List<Map<String, Object>> postList(List<UserPostMapper> postMappers) {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    for (UserPostMapper postMapper : postMappers) {
      entityMapList.add(post(postMapper));
    }
    return entityMapList;
  }

  public Map<String, Object> update(User entity) {
    User modelEntity = getByPK(entity.getId());
    Assert.notNull(modelEntity, "UPDATE_ENTITY_NULL");
    userDAO.updateByPrimaryKeySelective(UserData.convert(entity, new UserDO()));
    return UserDetailMapper.buildMap(entity);
  }

  public Map<String, Object> update(UserUpdateMapper updateMapper) {
    User entity = updateMapper.buildEntity();
    return update(entity);
  }

  public List<Map<String, Object>> updateList(List<UserUpdateMapper> updateMappers) {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    for (UserUpdateMapper updateMapper : updateMappers) {
      entityMapList.add(update(updateMapper));
    }
    return entityMapList;
  }

  public Integer delete(String key) {
    AtomicInteger count = new AtomicInteger();
    count.addAndGet(userDAO.deleteByPrimaryKey(key));
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
