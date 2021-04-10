package com.softwareinnovation.mooc.mooc.facade;

import com.softwareinnovation.mooc.mooc.domain.CourseFacade;
import com.softwareinnovation.mooc.mooc.domain.CourseTO;
import com.softwareinnovation.mooc.mooc.model.Course;
import com.softwareinnovation.mooc.mooc.model.CourseDomain;
import com.softwareinnovation.mooc.mooc.model.CourseFilterMapper;
import com.softwareinnovation.mooc.mooc.service.CourseService;
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
@RequestMapping("facade/mooc/course")
public class CourseFacadeImpl implements CourseFacade {
  @Autowired
  private CourseService courseService;

  @RequestMapping(
      value = "filter",
      method = RequestMethod.GET
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @ResponseBody
  @Override
  public List<CourseTO> getFilterList(@RequestParam(value = "id", required = false) String id,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "url", required = false) String url,
      @RequestParam(value = "coverUrl", required = false) String coverUrl,
      @RequestParam(value = "gmtUpdateFrom", required = false) Long gmtUpdateFrom,
      @RequestParam(value = "gmtUpdateTo", required = false) Long gmtUpdateTo,
      @RequestParam(value = "gmtCreateFrom", required = false) Long gmtCreateFrom,
      @RequestParam(value = "gmtCreateTo", required = false) Long gmtCreateTo,
      @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
      @RequestParam(value = "rows", required = false, defaultValue = "15") Integer rows) {
    CourseFilterMapper mapper = new CourseFilterMapper();
    mapper.id = id;
    mapper.name = name;
    mapper.url = url;
    mapper.coverUrl = coverUrl;
    mapper.gmtUpdateFrom = gmtUpdateFrom;
    mapper.gmtUpdateTo = gmtUpdateTo;
    mapper.gmtCreateFrom = gmtCreateFrom;
    mapper.gmtCreateTo = gmtCreateTo;
    mapper.page = page;
    mapper.rows = rows;
    List<CourseTO> toList = new ArrayList<>();
    courseService.getListByFilter(mapper).forEach(entity -> toList.add(CourseDomain.convert(entity, new CourseTO())));
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
  public List<CourseTO> getFilterDetailList(@RequestParam(value = "id", required = false) String id,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "url", required = false) String url,
      @RequestParam(value = "coverUrl", required = false) String coverUrl,
      @RequestParam(value = "gmtUpdateFrom", required = false) Long gmtUpdateFrom,
      @RequestParam(value = "gmtUpdateTo", required = false) Long gmtUpdateTo,
      @RequestParam(value = "gmtCreateFrom", required = false) Long gmtCreateFrom,
      @RequestParam(value = "gmtCreateTo", required = false) Long gmtCreateTo,
      @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
      @RequestParam(value = "rows", required = false, defaultValue = "15") Integer rows) {
    CourseFilterMapper mapper = new CourseFilterMapper();
    mapper.id = id;
    mapper.name = name;
    mapper.url = url;
    mapper.coverUrl = coverUrl;
    mapper.gmtUpdateFrom = gmtUpdateFrom;
    mapper.gmtUpdateTo = gmtUpdateTo;
    mapper.gmtCreateFrom = gmtCreateFrom;
    mapper.gmtCreateTo = gmtCreateTo;
    mapper.page = page;
    mapper.rows = rows;
    List<CourseTO> toList = new ArrayList<>();
    courseService.getListByFilter(mapper).forEach(entity -> toList.add(CourseDomain.convert(entity, new CourseTO())));
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
  public CourseTO getSingleByPK(@RequestParam(value = "id", required = true) String id) {
    Course entity = courseService.getByPK(id);
    return entity != null ? CourseDomain.convert(entity, new CourseTO()) : null;
  }

  @RequestMapping(
      value = "",
      method = RequestMethod.POST
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @Override
  public void post(@RequestBody CourseTO entityTO) {
    courseService.post(CourseDomain.convert(entityTO, new Course()));
  }

  @RequestMapping(
      value = "",
      method = RequestMethod.PATCH
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @Override
  public void patch(@RequestBody CourseTO entityTO) {
    courseService.update(CourseDomain.convert(entityTO, new Course()));
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
    courseService.delete(id);
  }
}
