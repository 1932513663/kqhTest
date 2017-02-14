package shiro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.base.service.impl.BaseServiceImpl;
import shiro.dao.sys.SysPostMapper;
import shiro.model.sys.SysPost;
import shiro.service.SysPostIService;
import utils.Constants;
import utils.JsonUtils;
import utils.base.Tree;

@Service
public class SysPostServiceImpl extends BaseServiceImpl<SysPost> implements SysPostIService {

	@Autowired
	private SysPostMapper sysPostMapper;
	@Override
	public List<Tree> findPostTree(SysPost sysPost) {
		List<SysPost> postList = findByEntity(sysPost);
		List<Tree> list = new ArrayList<Tree>();
		SysPost postNew=new SysPost();
		for (SysPost post : postList) {
			Tree tree = new Tree();
			tree.setId(post.getId());
			postNew.setParentId(post.getId());
			List<SysPost> postChildrenList = findByEntity(postNew);
			String state = "open";
			if (postChildrenList != null && postChildrenList.size() > 0) {
				state = "closed";
			}
			tree.setState(state);
			tree.setText(post.getPostName());
			list.add(tree);
		}
		return list;
	}
	
	@Override
	public List<Tree> findPostTreeParents(SysPost sysPost) {
		List<SysPost> postList = findByEntity(sysPost);
		//递归时用来存放每次的查询结果
		List<Tree> listCurrent = new ArrayList<Tree>();
		//选择节点，如果有子节点则关闭，无子节点则展开
		String selectNodeState="open";
		if(postList!=null&&postList.size()>0){
			selectNodeState="closed";
		}
		//查找当前选中节点及所有兄弟节点及所有父节点及父节点的兄弟节点
		List<Tree> list = findPostParents(listCurrent, sysPost,selectNodeState);
		return list;
	}
	
	public List<Tree> findPostParents(List<Tree> postList,SysPost sysPost,String selectNodeState){
		List<Tree> listParent = new ArrayList<Tree>();
		String parentId = sysPost.getParentId();
		String companyId = sysPost.getCompanyId();
		String deptId = sysPost.getDeptId();
		SysPost postParent=new SysPost();
		postParent.setId(parentId);
		//postParent:当前需要选择的节点
		SysPost findPostParent = findById(parentId);
		//根据业务需求findPostParent不可能为空
		if(findPostParent!=null){
			//当前选择节点信息
			Tree treeParent = new Tree();
			treeParent.setId(parentId);
			treeParent.setText(findPostParent.getPostName());
			treeParent.setChildren(postList);
			treeParent.setState(selectNodeState);
			//查询父节点同级节点条件
			SysPost postParentCurrent=new SysPost();
			String parentIdCurrent = findPostParent.getParentId();
			postParentCurrent.setParentId(parentIdCurrent);
			postParentCurrent.setCompanyId(companyId);
			postParentCurrent.setDeptId(deptId);
			//查询当前父节点同级所有节点
			List<SysPost> postListCurrent = findByEntity(postParentCurrent);
			SysPost postParentNew=new SysPost();
			for (SysPost post : postListCurrent) {
				String id=post.getId();
				if(!id.equals(parentId)){
					Tree tree = new Tree();
					tree.setId(id);
					postParentNew.setParentId(id);
					postParentNew.setCompanyId(companyId);
					postParentNew.setDeptId(deptId);
					List<SysPost> postChildrenList = findByEntity(postParentNew);
					String state = "open";
					if (postChildrenList != null && postChildrenList.size() > 0) {
						state = "closed";
					}
					tree.setState(state);
					tree.setText(post.getPostName());
					listParent.add(tree);
				}else{
					listParent.add(treeParent);
				}
			}
			List<Tree> findPostParents = findPostParents(listParent,findPostParent,"open");
			if(findPostParents!=null&&findPostParents.size()>0){
				listParent=findPostParents;
			}
		}
		return listParent;
	}
	
	@Override
	public List<Tree> findPostTreeOfSelect(String companyId,String deptId,SysPost sysPost) {
		List<Tree> list = new ArrayList<Tree>();
		if(sysPost==null){
			Tree tree = new Tree();
			tree.setId("00000000000000000000000000000000");
			SysPost postRoot=new SysPost();
			postRoot.setParentId("00000000000000000000000000000000");
			postRoot.setCompanyId(companyId);
			postRoot.setDeptId(deptId);
			List<SysPost> postChildrenList = findByEntity(postRoot);
			String state = "open";
			if (postChildrenList != null && postChildrenList.size() > 0) {
				state = "closed";
			}
			tree.setState(state);
			tree.setText("根节点");
			list.add(tree);
		}else{
			sysPost.setCompanyId(companyId);
			sysPost.setDeptId(deptId);
			List<SysPost> postList = findByEntity(sysPost);
			SysPost postNew=new SysPost();
			for (SysPost post : postList) {
				Tree tree = new Tree();
				tree.setId(post.getId());
				postNew.setParentId(post.getId());
				List<SysPost> postChildrenList = findByEntity(postNew);
				String state = "open";
				if (postChildrenList != null && postChildrenList.size() > 0) {
					state = "closed";
				}
				tree.setState(state);
				tree.setText(post.getPostName());
				list.add(tree);
			}
		}
		return list;
	}
	
	@Override
	public List<Tree> selectPost(SysPost sysPost) {
		List<Tree> list = new ArrayList<Tree>();
		List<SysPost> postList = findByEntity(sysPost);
		SysPost postNew=new SysPost();
		for (SysPost post : postList) {
			Tree tree = new Tree();
			tree.setId(post.getId());
			postNew.setParentId(post.getId());
			List<SysPost> postChildrenList = findByEntity(postNew);
			String state = "open";
			if (postChildrenList != null && postChildrenList.size() > 0) {
				state = "closed";
			}
			tree.setState(state);
			tree.setText(post.getPostName());
			list.add(tree);
		}
		return list;
	}
	
	@Override
	public List<SysPost> findByEntity(SysPost post) {
		post.setDel(Constants.DEL_NO);
		return super.findByEntity(post);
	}
	
	@Override
	public String delDeptById(String id) {
		SysPost postOld = findById(id);
		String companyId = postOld.getCompanyId();
		String deptId = postOld.getDeptId();
		SysPost post=new SysPost();
		post.setParentId(id);
		post.setCompanyId(companyId);
		post.setDeptId(deptId);
		List<SysPost> childrenList = findByEntity(post);
		if(childrenList!=null&&childrenList.size()>0){
			return JsonUtils.returnJsonError("该职位下面存在子职位不允许删除！");
		}
		postOld.setDel(Constants.DEL_YES);
		update(postOld);
		return JsonUtils.returnJsonSuccess();
	}

	@Override
	public String save(SysPost post) {
		sysPostMapper.savePost(post);
		return JsonUtils.returnJsonSuccess(post);
	}

	@Override
	public String updatePost(SysPost post) {
		super.update(post);
		return JsonUtils.returnJsonSuccess(post);
	}
}
