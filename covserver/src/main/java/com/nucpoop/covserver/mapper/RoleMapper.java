package com.nucpoop.covserver.mapper;

import java.util.Optional;

import com.nucpoop.covserver.model.Role;
import com.nucpoop.covserver.model.RoleName;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    Role findByName(RoleName roleName);
}
