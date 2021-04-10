package com.softwareinnovation.mooc.mooc.datainterface;

import com.softwareinnovation.mooc.base.MyBatisBaseDAO;
import com.softwareinnovation.mooc.mooc.dataobject.CourseDO;
import com.softwareinnovation.mooc.mooc.dataobject.CourseDOExample;
import org.springframework.stereotype.Repository;

/**
 * CourseDAO extends base class
 */
@Repository
public interface CourseDAO extends MyBatisBaseDAO<CourseDO, String, CourseDOExample> {
}