package com.kosa.kojaping.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    public String selectRoleNameByRoleNo(Long roleNo);

}
