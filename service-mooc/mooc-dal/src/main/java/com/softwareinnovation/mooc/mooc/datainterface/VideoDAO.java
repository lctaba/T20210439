package com.softwareinnovation.mooc.mooc.datainterface;

import com.softwareinnovation.mooc.base.MyBatisBaseDAO;
import com.softwareinnovation.mooc.mooc.dataobject.VideoDO;
import com.softwareinnovation.mooc.mooc.dataobject.VideoDOExample;
import org.springframework.stereotype.Repository;

/**
 * VideoDAO extends base class
 */
@Repository
public interface VideoDAO extends MyBatisBaseDAO<VideoDO, String, VideoDOExample> {
}