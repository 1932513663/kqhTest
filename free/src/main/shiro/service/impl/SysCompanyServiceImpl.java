package shiro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.base.service.impl.BaseServiceImpl;
import shiro.dao.sys.SysCompanyMapper;
import shiro.model.sys.SysCompany;
import shiro.service.SysCompanyIService;
import utils.Constants;
import utils.JsonUtils;
import utils.base.Tree;

@Service
public class SysCompanyServiceImpl extends BaseServiceImpl<SysCompany> implements SysCompanyIService {

	@Autowired
	private SysCompanyMapper sysCompanyMapper;
	
	@Override
	public List<Tree> findCompanyTree(SysCompany sysCompany) {
		List<SysCompany> companyList = findByEntity(sysCompany);
		List<Tree> list = new ArrayList<Tree>();
		SysCompany companyNew=new SysCompany();
		for (SysCompany company : companyList) {
			Tree tree = new Tree();
			tree.setId(company.getId());
			companyNew.setParentId(company.getId());
			List<SysCompany> companyChildrenList = findByEntity(companyNew);
			String state = "open";
			if (companyChildrenList != null && companyChildrenList.size() > 0) {
				state = "closed";
			}
			tree.setState(state);
			tree.setText(company.getCompanyName());
			list.add(tree);
		}
		return list;
	}
	
	@Override
	public List<Tree> findCompanyTreeOfSelect(SysCompany sysCompany) {
		List<Tree> list = new ArrayList<Tree>();
		if(sysCompany==null){
			Tree tree = new Tree();
			tree.setId("00000000000000000000000000000000");
			SysCompany companyRoot=new SysCompany();
			companyRoot.setParentId("00000000000000000000000000000000");
			List<SysCompany> companyChildrenList = findByEntity(companyRoot);
			String state = "open";
			if (companyChildrenList != null && companyChildrenList.size() > 0) {
				state = "closed";
			}
			tree.setState(state);
			tree.setText("根节点");
			list.add(tree);
		}else{
			List<SysCompany> companyList = findByEntity(sysCompany);
			SysCompany companyNew=new SysCompany();
			for (SysCompany company : companyList) {
				Tree tree = new Tree();
				tree.setId(company.getId());
				companyNew.setParentId(company.getId());
				List<SysCompany> companyChildrenList = findByEntity(companyNew);
				String state = "open";
				if (companyChildrenList != null && companyChildrenList.size() > 0) {
					state = "closed";
				}
				tree.setState(state);
				tree.setText(company.getCompanyName());
				list.add(tree);
			}
		}
		return list;
	}
	
	@Override
	public List<Tree> selectCompany(SysCompany sysCompany) {
		List<Tree> list = new ArrayList<Tree>();
		List<SysCompany> companyList = findByEntity(sysCompany);
		SysCompany companyNew=new SysCompany();
		for (SysCompany company : companyList) {
			Tree tree = new Tree();
			tree.setId(company.getId());
			companyNew.setParentId(company.getId());
			List<SysCompany> companyChildrenList = findByEntity(companyNew);
			String state = "open";
			if (companyChildrenList != null && companyChildrenList.size() > 0) {
				state = "closed";
			}
			tree.setState(state);
			tree.setText(company.getCompanyName());
			list.add(tree);
		}
		return list;
	}
	
	@Override
	public List<Tree> findCompanyTreeParents(SysCompany sysCompany) {
		List<SysCompany> companyList = findByEntity(sysCompany);
		//递归时用来存放每次的查询结果
		List<Tree> listCurrent = new ArrayList<Tree>();
		//选择节点，如果有子节点则关闭，无子节点则展开
		String selectNodeState="open";
		if(companyList!=null&&companyList.size()>0){
			selectNodeState="closed";
		}
		//查找当前选中节点及所有兄弟节点及所有父节点及父节点的兄弟节点
		List<Tree> list = findCompanyParents(listCurrent, sysCompany,selectNodeState);
		return list;
	}
	
	public List<Tree> findCompanyParents(List<Tree> companyList,SysCompany sysCompany,String selectNodeState){
		List<Tree> listParent = new ArrayList<Tree>();
		String parentId = sysCompany.getParentId();
		SysCompany companyParent=new SysCompany();
		companyParent.setId(parentId);
		//postParent:当前需要选择的节点
		SysCompany findCompanyParent = findById(parentId);
		//根据业务需求findPostParent不可能为空
		if(findCompanyParent!=null){
			//当前选择节点信息
			Tree treeParent = new Tree();
			treeParent.setId(parentId);
			treeParent.setText(findCompanyParent.getCompanyName());
			treeParent.setChildren(companyList);
			treeParent.setState(selectNodeState);
			//查询父节点同级节点条件
			SysCompany companyParentCurrent=new SysCompany();
			String parentIdCurrent = findCompanyParent.getParentId();
			companyParentCurrent.setParentId(parentIdCurrent);
			//查询当前父节点同级所有节点
			List<SysCompany> companyListCurrent = findByEntity(companyParentCurrent);
			SysCompany companyParentNew=new SysCompany();
			for (SysCompany company : companyListCurrent) {
				String id=company.getId();
				if(!id.equals(parentId)){
					Tree tree = new Tree();
					tree.setId(id);
					companyParentNew.setParentId(id);
					List<SysCompany> companyChildrenList = findByEntity(companyParentNew);
					String state = "open";
					if (companyChildrenList != null && companyChildrenList.size() > 0) {
						state = "closed";
					}
					tree.setState(state);
					tree.setText(company.getCompanyName());
					listParent.add(tree);
				}else{
					listParent.add(treeParent);
				}
			}
			List<Tree> findCompanyParents = findCompanyParents(listParent,findCompanyParent,"open");
			if(findCompanyParents!=null&&findCompanyParents.size()>0){
				listParent=findCompanyParents;
			}
		}
		return listParent;
	}

	@Override
	public String delCompanyById(String id) {
		SysCompany company=new SysCompany();
		company.setParentId(id);
		List<SysCompany> childrenList = findByEntity(company);
		if(childrenList!=null&&childrenList.size()>0){
			return JsonUtils.returnJsonError("该公司下面存在子公司不允许删除！");
		}
		SysCompany companyOld = findById(id);
		companyOld.setDel(Constants.DEL_YES);
		update(companyOld);
		return JsonUtils.returnJsonSuccess();
	}

	@Override
	public List<SysCompany> findByEntity(SysCompany company) {
		company.setDel(Constants.DEL_NO);
		return super.findByEntity(company);
	}

	@Override
	public String save(SysCompany company) {
		sysCompanyMapper.saveCompany(company);
		return JsonUtils.returnJsonSuccess(company);
	}

	@Override
	public String updateCompany(SysCompany company) {
		super.update(company);
		return JsonUtils.returnJsonSuccess(company);
	}
}
