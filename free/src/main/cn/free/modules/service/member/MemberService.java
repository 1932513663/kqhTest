package cn.free.modules.service.member;

import java.util.List;

import cn.free.modules.model.Member;
import core.base.service.BaseIService;

public interface MemberService extends BaseIService<Member> {
	public List<Member> findAll();
}
