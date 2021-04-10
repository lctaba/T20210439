package com.softwareinnovation.mooc.mooc.service;

import cc.eamon.open.error.Assert;
import cc.eamon.open.status.StatusException;
import com.softwareinnovation.mooc.mooc.datafilter.VideoFilter;
import com.softwareinnovation.mooc.mooc.datainterface.VideoDAO;
import com.softwareinnovation.mooc.mooc.dataobject.VideoDO;
import com.softwareinnovation.mooc.mooc.model.Video;
import com.softwareinnovation.mooc.mooc.model.VideoData;
import com.softwareinnovation.mooc.mooc.model.VideoDetailMapper;
import com.softwareinnovation.mooc.mooc.model.VideoFilterMapper;
import com.softwareinnovation.mooc.mooc.model.VideoPostMapper;
import com.softwareinnovation.mooc.mooc.model.VideoSimpleMapper;
import com.softwareinnovation.mooc.mooc.model.VideoUpdateMapper;
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
public class VideoService {
  @Autowired
  private VideoDAO videoDAO;

  public Video getByPK(String key) {
    VideoDO entity = videoDAO.selectByPrimaryKey(key);
    return VideoData.convert(entity, null);
  }

  public Map<String, Object> getSimpleMapByPK(String key) {
    return VideoSimpleMapper.buildMap(getByPK(key));
  }

  public Map<String, Object> getDetailMapByPK(String key) {
    Video modelEntity = getByPK(key);
    if (modelEntity == null) return null;
    return VideoDetailMapper.buildMap(modelEntity);
  }

  public Long getCountByFilter(VideoFilterMapper filterMapper) {
    return videoDAO.countByExample(VideoFilter.initDOQueryFilter(filterMapper.buildMap()));
  }

  public List<Video> getListByFilter(VideoFilterMapper filterMapper) {
    List<Video> entityList = new ArrayList<>();
    for (VideoDO entity : videoDAO.selectByExample(VideoFilter.initDOQueryFilter(filterMapper.buildMap()))) {
      entityList.add(VideoData.convert(entity, new Video()));
    }
    return entityList;
  }

  public List<Map<String, Object>> getFilterMapList(VideoFilterMapper filterMapper) {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    getListByFilter(filterMapper).forEach(entity -> entityMapList.add(VideoSimpleMapper.buildMap(entity)));
    return entityMapList;
  }

  public List<Map<String, Object>> getFilterDetailMapList(VideoFilterMapper filterMapper) {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    // query video data;
    List<Video> modelEntityList = getListByFilter(filterMapper);
    // loop & batch find to release database pressure;
    for (Video modelEntity : modelEntityList) {
    }
    // load data from local database or remote service;
    // loop assembly data;
    for (Video modelEntity : modelEntityList) {
      entityMapList.add(VideoDetailMapper.buildMap(modelEntity));
    }
    return entityMapList;
  }

  public Map<String, Object> post(Video entity) {
    entity.setId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().substring(0, 32));
    try {
      videoDAO.insertSelective(VideoData.convert(entity, new VideoDO()));
    }
    catch(DuplicateKeyException e) {
      throw new StatusException("POST_ENTITY_DUPLICATE");
    }
    return VideoDetailMapper.buildMap(getByPK(entity.getId()));
  }

  public Map<String, Object> post(VideoPostMapper postMapper) {
    Video entity = postMapper.buildEntity();
    return post(entity);
  }

  public List<Map<String, Object>> postList(List<VideoPostMapper> postMappers) {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    for (VideoPostMapper postMapper : postMappers) {
      entityMapList.add(post(postMapper));
    }
    return entityMapList;
  }

  public Map<String, Object> update(Video entity) {
    Video modelEntity = getByPK(entity.getId());
    Assert.notNull(modelEntity, "UPDATE_ENTITY_NULL");
    videoDAO.updateByPrimaryKeySelective(VideoData.convert(entity, new VideoDO()));
    return VideoDetailMapper.buildMap(entity);
  }

  public Map<String, Object> update(VideoUpdateMapper updateMapper) {
    Video entity = updateMapper.buildEntity();
    return update(entity);
  }

  public List<Map<String, Object>> updateList(List<VideoUpdateMapper> updateMappers) {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    for (VideoUpdateMapper updateMapper : updateMappers) {
      entityMapList.add(update(updateMapper));
    }
    return entityMapList;
  }

  public Integer delete(String key) {
    AtomicInteger count = new AtomicInteger();
    count.addAndGet(videoDAO.deleteByPrimaryKey(key));
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
