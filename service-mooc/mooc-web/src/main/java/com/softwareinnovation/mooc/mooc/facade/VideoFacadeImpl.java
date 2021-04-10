package com.softwareinnovation.mooc.mooc.facade;

import com.softwareinnovation.mooc.mooc.domain.VideoFacade;
import com.softwareinnovation.mooc.mooc.domain.VideoTO;
import com.softwareinnovation.mooc.mooc.model.Video;
import com.softwareinnovation.mooc.mooc.model.VideoDomain;
import com.softwareinnovation.mooc.mooc.model.VideoFilterMapper;
import com.softwareinnovation.mooc.mooc.service.VideoService;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: eamon
 * Email: eamon@eamon.cc */
@RestController
@RequestMapping("facade/mooc/video")
public class VideoFacadeImpl implements VideoFacade {
  @Autowired
  private VideoService videoService;

  @RequestMapping(
      value = "filter",
      method = RequestMethod.GET
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @ResponseBody
  @Override
  public List<VideoTO> getFilterList(@RequestParam(value = "id", required = false) String id,
      @RequestParam(value = "ownerId", required = false) String ownerId,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "url", required = false) String url,
      @RequestParam(value = "coverUrl", required = false) String coverUrl,
      @RequestParam(value = "status", required = false) String status,
      @RequestParam(value = "gmtUpdateFrom", required = false) Long gmtUpdateFrom,
      @RequestParam(value = "gmtUpdateTo", required = false) Long gmtUpdateTo,
      @RequestParam(value = "gmtCreateFrom", required = false) Long gmtCreateFrom,
      @RequestParam(value = "gmtCreateTo", required = false) Long gmtCreateTo,
      @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
      @RequestParam(value = "rows", required = false, defaultValue = "15") Integer rows) {
    VideoFilterMapper mapper = new VideoFilterMapper();
    mapper.id = id;
    mapper.ownerId = ownerId;
    mapper.name = name;
    mapper.url = url;
    mapper.coverUrl = coverUrl;
    mapper.status = status;
    mapper.gmtUpdateFrom = gmtUpdateFrom;
    mapper.gmtUpdateTo = gmtUpdateTo;
    mapper.gmtCreateFrom = gmtCreateFrom;
    mapper.gmtCreateTo = gmtCreateTo;
    mapper.page = page;
    mapper.rows = rows;
    List<VideoTO> toList = new ArrayList<>();
    videoService.getListByFilter(mapper).forEach(entity -> toList.add(VideoDomain.convert(entity, new VideoTO())));
    return toList;
  }

  @RequestMapping(
      value = "filter/detail",
      method = RequestMethod.GET
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @ResponseBody
  @Override
  public List<VideoTO> getFilterDetailList(@RequestParam(value = "id", required = false) String id,
      @RequestParam(value = "ownerId", required = false) String ownerId,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "url", required = false) String url,
      @RequestParam(value = "coverUrl", required = false) String coverUrl,
      @RequestParam(value = "status", required = false) String status,
      @RequestParam(value = "gmtUpdateFrom", required = false) Long gmtUpdateFrom,
      @RequestParam(value = "gmtUpdateTo", required = false) Long gmtUpdateTo,
      @RequestParam(value = "gmtCreateFrom", required = false) Long gmtCreateFrom,
      @RequestParam(value = "gmtCreateTo", required = false) Long gmtCreateTo,
      @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
      @RequestParam(value = "rows", required = false, defaultValue = "15") Integer rows) {
    VideoFilterMapper mapper = new VideoFilterMapper();
    mapper.id = id;
    mapper.ownerId = ownerId;
    mapper.name = name;
    mapper.url = url;
    mapper.coverUrl = coverUrl;
    mapper.status = status;
    mapper.gmtUpdateFrom = gmtUpdateFrom;
    mapper.gmtUpdateTo = gmtUpdateTo;
    mapper.gmtCreateFrom = gmtCreateFrom;
    mapper.gmtCreateTo = gmtCreateTo;
    mapper.page = page;
    mapper.rows = rows;
    List<VideoTO> toList = new ArrayList<>();
    videoService.getListByFilter(mapper).forEach(entity -> toList.add(VideoDomain.convert(entity, new VideoTO())));
    return toList;
  }

  @RequestMapping(
      value = "single",
      method = RequestMethod.GET
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @Override
  public VideoTO getSingleByPK(@RequestParam(value = "id", required = true) String id) {
    Video entity = videoService.getByPK(id);
    return entity != null ? VideoDomain.convert(entity, new VideoTO()) : null;
  }

  @RequestMapping(
      value = "",
      method = RequestMethod.POST
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @Override
  public void post(@RequestBody VideoTO entityTO) {
    videoService.post(VideoDomain.convert(entityTO, new Video()));
  }

  @RequestMapping(
      value = "",
      method = RequestMethod.PATCH
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @Override
  public void patch(@RequestBody VideoTO entityTO) {
    videoService.update(VideoDomain.convert(entityTO, new Video()));
  }

  @RequestMapping(
      value = "",
      method = RequestMethod.DELETE
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @Override
  public void delete(@RequestParam(value = "id", required = true) String id) {
    videoService.delete(id);
  }
}
