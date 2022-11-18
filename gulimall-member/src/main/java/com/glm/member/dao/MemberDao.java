package com.glm.member.dao;

import com.glm.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 20:59:29
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
