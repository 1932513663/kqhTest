package junit;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.free.modules.dao.MemberMapper;
import cn.free.modules.model.Member;
import shiro.dao.sys.SysMenuMapper;
import shiro.dao.sys.SysRoleMapper;
import shiro.dao.sys.SysUserMapper;
import shiro.dao.sys.SysUserRoleMapper;
import shiro.model.sys.SysCompany;
import shiro.model.sys.SysMenu;
import shiro.model.sys.SysRole;
import shiro.model.sys.SysUserRole;
import shiro.service.SysCompanyIService;
import utils.SpringContextHolder;
import utils.base.Tree;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring*.xml" })
public class QurtTest {
	private static Logger logger = Logger.getLogger(QurtTest.class);

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private SysCompanyIService sysCompanyIService;

	@Test
	public void daoTest() {
		sysUserMapper.selectAll();
		logger.info("success------------------------------");
	}

	@Test
	public void add() {
		//sysUserRoleMapper.deleteByUserId("");
		//List<SysUserRole> list =new ArrayList<SysUserRole>();
		//sysUserRoleMapper.insertUserRoleOfBatch(list);
		//System.out.println("-------------");
		/*for (int i = 0; i < 7; i++) {
			SysMenu sysMenu =new SysMenu();
			sysMenu.setParentId("00000000000000000000000000000000");
			sysMenu.setId("0000000000000000000000000000001"+i);
			sysMenu.setMenuName("菜单0"+i);
			sysMenuMapper.insert(sysMenu);
			logger.info("success------------------------------");
		}*/
		/*SysMenu sysMenu =new SysMenu();
		sysMenu.setParentId("");
		List<SysMenu> selectAll = sysMenuMapper.select(sysMenu);
		System.out.println(0);*/
		/*for (int i = 0; i < 7; i++) {
			SysRole sysRole = new SysRole();
			sysRoleMapper.insert(sysRole);
			logger.info("success------------------------------");
		}*/
		
		/*Member member = new Member();
		memberMapper.insert(member);
		System.out.println(member.getId());
		System.out.println("----------------start");
		SysCompany sysCompany=new SysCompany();
		sysCompany.setId("22d7a57ff00c461698d1b5cbf732bfc4");
		sysCompany.setParentId("da7ce9326b324ec4ba06fbd91a774213");
		List<Tree> findCompanyTreeParents = sysCompanyIService.findCompanyTreeParents(sysCompany);
		for (Tree tree : findCompanyTreeParents) {
			System.out.println(tree.getText());
		}
		System.out.println("----------------end");*/
	}
}
