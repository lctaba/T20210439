package com.softwareinnovation.mooc.mooc.datainterface;

import com.softwareinnovation.mooc.base.MyBatisBaseDAO;
import com.softwareinnovation.mooc.mooc.dataobject.UserDO;
import com.softwareinnovation.mooc.mooc.dataobject.UserDOExample;
import org.springframework.stereotype.Repository;

/**
 * UserDAO extends base class
 */
@Repository
public interface UserDAO extends MyBatisBaseDAO<UserDO, String, UserDOExample> {
}