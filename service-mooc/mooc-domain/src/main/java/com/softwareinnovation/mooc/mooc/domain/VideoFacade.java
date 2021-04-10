package com.softwareinnovation.mooc.mooc.domain;

import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: eamon
 * Email: eamon@eamon.cc */
@FeignClient(
    value = "mooc",
    path = "/facade/mooc/video"
)
public interface VideoFacade {
  @RequestMapping(
      value = "filter",
      method = RequestMethod.GET
  )
  @ResponseBody
  List<VideoTO> getFilterList(@RequestParam(value = "id", required = false) String id,
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
      @RequestParam(value = "rows", required = false, defaultValue = "15") Integer rows);

  @RequestMapping(
      value = "filter/detail",
      method = RequestMethod.GET
  )
  @ResponseBody
  List<VideoTO> getFilterDetailList(@RequestParam(value = "id", required = false) String id,
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
      @RequestParam(value = "rows", required = false, defaultValue = "15") Integer rows);

  @RequestMapping(
      value = "single",
      method = RequestMethod.GET
  )
  VideoTO getSingleByPK(@RequestParam(value = "id", required = true) String id);

  @RequestMapping(
      value = "",
      method = RequestMethod.POST
  )
  void post(@RequestBody VideoTO entityTO);

  @RequestMapping(
      value = "",
      method = RequestMethod.PATCH
  )
  void patch(@RequestBody VideoTO entityTO);

  @RequestMapping(
      value = "",
      method = RequestMethod.DELETE
  )
  void delete(@RequestParam(value = "id", required = true) String id);
}
