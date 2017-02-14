package cn.free.modules.service.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.free.modules.dao.MemberMapper;
import cn.free.modules.model.Member;
import cn.free.modules.service.member.MemberService;
import core.base.service.impl.BaseServiceImpl;

@Service
public class MemberServiceImpl extends BaseServiceImpl<Member> implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public List<Member> findAll() {
		List<Member> selectAll = memberMapper.selectAll();
		for (Member member : selectAll) {
			System.out.println("ID=" + member.getId());
		}
		return selectAll;
	}
}
