package com.kosa.kojaping.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LocationMapper {
    public Long selectLocationNoByLocationName(String locationName);
}
