package shiro.utils;

import java.util.ResourceBundle;

public class SysConstant {

	static {
		ResourceBundle rb = ResourceBundle.getBundle("config/sys");
		COMPANY_ID_MAITIAN = rb.getString("COMPANY_ID_MAITIAN");
		COMPANY_ID_BEIJING = rb.getString("COMPANY_ID_BEIJING");
		BUSINESS_DEPARTMENT_ID = rb.getString("BUSINESS_DEPARTMENT_ID");
		ZONGBU_DEPARTMENT_ID = rb.getString("ZONGBU_DEPARTMENT_ID");
		SYSTEM_MENU_ID = rb.getString("SYSTEM_MENU_ID");
		ROOT_MENU_ID = rb.getString("ROOT_MENU_ID");
	}

	// 导出数据后 第五次导数据后 2013-12-12
	public static final String COMPANY_ID_MAITIAN;// 麦田集团ID
	public static final String COMPANY_ID_BEIJING;// 北京公司ID
	public static final String BUSINESS_DEPARTMENT_ID;// 业务部的ID
	public static final String ZONGBU_DEPARTMENT_ID;// 总部的ID
	public static final String SYSTEM_MENU_ID;// 系统ID
	public static final String ROOT_MENU_ID;// 根菜单ID
	
	/** 副总经理职位ID **/
	public static final String VICEGENERAL_POST_ID = "59F35878C4A74483A23E76CB808BF4AF";
	/** 高级副总经理职位ID **/
	public static final String SENIOR_VICEGENERAL_POST_ID = "32BCDE48F3474EB3A12D702C18100409";
		
	
	public static final String ROOT_POST_ID = "00000000000000000000000000000000";// 根职位ID
	public static final String ROOT_DEPT_ID = "00000000000000000000000000000000";// 根部门ID

	/*********************   角色名称  *************************************************/
	/** 1.角色名称-系统管理员 **/
	public static final String ROLE_SYS_ADMIN = "sys_admin";
	/** 2.角色名称-管理员 **/
	public static final String ROLE_ADMIN = "admin";
	/** 3.角色名称-区域总监 **/
	public static final String ROLE_AREA_DIRECTOR = "area_director";
	/** 4.角色名称-区域经理 **/
	public static final String ROLE_AREA_MANAGER = "area_manager";
	/** 5.角色名称-店组长 **/
	public static final String ROLE_GROUP_LEADER = "group_leader";
	/** 6.角色名称-见习店组长 **/
	public static final String ROLE_TRAINEE_GROUP_LEADER = "trainee_group_leader";
	/** 7.角色名称-经纪人 **/
	public static final String ROLE_AGENT = "agent";
	/** 8.角色名称-试用经纪人 **/
	public static final String ROLE_PROBATION_AGENT = "probation_agent";
	/** 9.角色名称-房产顾问 **/
	public static final String ROLE_CONSULTANT = "consultant";
	/** 10.角色名称-统计分析权限组   ps:主要是为了判断顶部导航是否显示【统计分析】跳转图标 **/
	public static final String ROLE_STAT_PERMISSION_GROUP = "stat_permission_group";
	/** 11.角色名称-楼盘字典部   ps:主要是为了使楼盘字典部能够查看【销控表】**/
	public static final String ROLE_HOUSES_DICT_DEPARTMENT = "houses_dict_department";
	/** 12.角色名称-租赁专员/助理 **/
	public static final String ROLE_RENT_ASSISTANT = "rent_assistant";
	/** 13.角色名称-区域助理**/
	public static final String ROLE_AREA_ASSISTANT = "area_assistant";
	/** 14.角色名称-大区会议助理 **/
	public static final String ROLE_MEETING_ASSISTANT = "meeting_assistant";
	/** 15.角色名称-总监助理 **/
	public static final String ROLE_DIRECTOR_ASSISTANT = "area_director_assistant";
	/** 16.角色名称-大区组训 **/
	public static final String ROLE_GROUP_TRAINING  = "group_training";
	/** 17.角色名称-总部HR **/
	public static final String ROLE_HR_ZONGBU  = "hr_zongbu";
	/** 18.角色名称-业务线HR **/
	public static final String ROLE_HR_BUSINESS  = "hr_business";
	/** 19.角色名称-副总经理 **/
	public static final String ROLE_VICE_GENERAL  = "vice_general";
	/** 20.角色名称-总部人员 **/
	public static final String ROLE_ZONGBU_MEMBER = "the_zongbu_member";
	/** 21.角色名称-业务线人员 **/
	public static final String ROLE_BUSINESS_MEMBER = "the_business_member";
	/** 22.角色名称-所有主管 **/
	public static final String ROLE_MANAGER_MEMBER = "the_manager_member";
	/** 23.角色名称-所有总部主管 **/
	public static final String ROLE_MANAGER_ZONGBU = "the_manager_zongbu";
	/** 24.角色名称-所有业务线主管 **/
	public static final String ROLE_MANAGER_BUSINESS  = "the_manager_business";
	/** 25.角色名称-考勤-我的管理-待审核 **/
	public static final String ROLE_ATT_MYMANAGE_PENDING_CHECK  = "att_mymanage_pending_check";
	/** 26.角色名称-业务助理 **/
	public static final String ROLE_BUSINESS_ASSISTANT  = "the_business_assistant";
	/** 27.角色名称-事业部总经理助理 **/
	public static final String ROLE_BUSINESS_GENERAL_ASSISTANT  = "the_business_general_assistant";
	/** 28.角色名称-片区助理 **/
	public static final String ROLE_BUSINESS_PIANQU_ASSISTANT  = "the_business_pianqu_assistant";
	
	/*******************************************************************************/
	

	public static final String DEPARTMENT_TYPE_BUSINESS = "0";// 部门类型为【大片区】
	public static final String DEPARTMENT_TYPE_BIGAREA = "1";// 部门类型为【大区】
	public static final String DEPARTMENT_TYPE_AREA = "2";// 部门类型为【区域】
	public static final String DEPARTMENT_TYPE_ELSE = "3";// 部门类型为【其它】

	// 角色有效状态：0-无效，1-有效
	public static final String ROLE_IS_AVAILABLE_ZERO = "0";
	public static final String ROLE_IS_AVAILABLE_ONE = "1";

	// 用户的状态(离职、异动)
	public static final Integer USER_STATUS_RESIGNATION = 0; // 离职
	public static final Integer USER_STATUS_MOVE = 1; // 异动

	// 用户的状态
	public static final String USER_STATUS_NORMAL = "1"; // 在职
	public static final String USER_STATUS_DIMISSION = "0"; // 离职
	public static final String USER_STATUS_DELETE = "2"; // 删除

	// 用户的锁定状态
	public static final String USER_LOCK_STATUS_NORMAL = "0"; // 正常
	public static final String USER_LOCK_STATUS_ACCOUNT_LOCK = "1"; // 帐号锁定
	public static final String USER_LOCK_STATUS_DIMISSION_LOCK = "2"; // 离职锁定
	public static final String USER_LOCK_STATUS_ATTENDANCE_LOCK = "3"; // 考勤锁定
	public static final String USER_LOCK_STATUS_MOVE_LOCK = "4"; // 平级异动锁定

	// 通用删除状态
	public static final String DELETE_STATUS_NORMAL = "0"; // 正常
	public static final String DELETE_STATUS_DELETE = "1"; // 删除
	
	
	/** 离职异动历史-操作类型 0:入职 **/
	public static final String USER_CHANGE_TYPE_ENTRY = "0";
	/** 离职异动历史-操作类型 1:离职 **/
	public static final String USER_CHANGE_TYPE_QUIT = "1";
	/** 离职异动历史-操作类型 2:异动 **/
	public static final String USER_CHANGE_TYPE_CHANGE = "2";
	/** 离职异动历史-操作类型 3:复职 **/
	public static final String USER_CHANGE_TYPE_ENTRY_AGAIN = "3";
	/** 离职异动历史-读取类型 0:未读 **/
	public static final String USER_CHANGE_TYPE_UNREAD = "0";
	/** 离职异动历史-读取类型 0:已读 **/
	public static final String USER_CHANGE_TYPE_READ = "1";
	
	/** WEB服务-系统名称 **/
	public static final String WEBSERVICE_SYSTEM_NAME_DUTY = "责任盘"; 
	public static final String WEBSERVICE_SYSTEM_NAME_FORSALES = "房源"; 
	public static final String WEBSERVICE_SYSTEM_NAME_PUBLICMANAGE = "公共管理"; 
	public static final String WEBSERVICE_SYSTEM_NAME_STAT = "统计分析"; 
	public static final String WEBSERVICE_SYSTEM_NAME_PLATE = "公盘"; 
	public static final String WEBSERVICE_SYSTEM_NAME_UNKNOWN = "未知"; 
	public static final String WEBSERVICE_SYSTEM_NAME_MID = "中间系统"; 
	public static final String WEBSERVICE_SYSTEM_NAME_ATT = "考勤系统"; 
	
	/** 公盘-提供的服务名称 **/
	public static final String WEBSERVICE_SERVICE_NAME_SMS = "公盘-短信";
	public static final String WEBSERVICE_SERVICE_NAME_RENT = "公盘-租赁";
	public static final String WEBSERVICE_SERVICE_NAME_PROMPT = "公盘-冒泡提醒";
	public static final String WEBSERVICE_SERVICE_NAME_WARRANT = "公盘-权证";
	
	/** 责任盘-提供的服务名称 **/
	public static final String WEBSERVICE_SERVICE_NAME_DUTY_FOR_USER = "责任盘-人员";
	public static final String WEBSERVICE_SERVICE_NAME_DUTY_FOR_STAT = "责任盘->统计分析";
	public static final String WEBSERVICE_SERVICE_NAME_DUTY_PROPERTY = "责任盘-物业";
	public static final String WEBSERVICE_SERVICE_NAME_DUTY_PERSONAL = "责任盘-个人消息";
	public static final String WEBSERVICE_SERVICE_NAME_DUTY_FOR_LEASE = "责任盘->租赁";
	public static final String WEBSERVICE_SERVICE_NAME_DUTY_FOR_BUILD = "责任盘->房源";
	public static final String WEBSERVICE_SERVICE_NAME_DUTY_FOR_WARRANT = "责任盘->权证";
	public static final String WEBSERVICE_SERVICE_NAME_DUTY_FOR_LOGIN = "责任盘-登陆";
	
	/** 房源-提供的服务名称 **/
	public static final String WEBSERVICE_SERVICE_NAME_FORSALES_FOR_DUTY = "房源->责任盘";
	public static final String WEBSERVICE_SERVICE_NAME_FORSALES_FOR_STAT = "房源->统计分析";
	public static final String WEBSERVICE_SERVICE_NAME_FORSALES_FOR_PM = "房源->公共管理";
	
	/** 公共管理-提供的服务名称 **/
	public static final String WEBSERVICE_SERVICE_NAME_PUBLIC_FOR_USER = "公共管理-人员";
	public static final String WEBSERVICE_SERVICE_NAME_PUBLIC_FOR_MESSAGE = "公共管理-通知";
	public static final String WEBSERVICE_SERVICE_NAME_PUBLIC_FOR_LOGIN = "公共管理-登陆";
	
	/** 统计分析-提供的服务名称 **/
	public static final String WEBSERVICE_SERVICE_NAME_STAT_FOR_USER = "统计分析-人员";
	public static final String WEBSERVICE_SERVICE_NAME_STAT_PERSONAL = "统计分析-个人消息";
	public static final String WEBSERVICE_SERVICE_NAME_STAT_FOR_LOGIN = "统计分析-登陆";
	public static final String WEBSERVICE_SERVICE_NAME_STAT_FOR_DUTY = "统计分析-责任盘";
	public static final String WEBSERVICE_SERVICE_NAME_STAT_FOR_BUILD = "统计分析-房源";
	
	/** 中间服务-提供的服务名称 **/
	public static final String WEBSERVICE_SERVICE_NAME_MID_FOR_USER = "中间服务-人员";
	
	/** 考勤-提供的服务名称 **/
	public static final String WEBSERVICE_SERVICE_NAME_ATT = "考勤系统";
	
	/** WEB服务-调用类型 **/
	public static final String WEBSERVICE_CALL_TYPE_INITIATIVE  = "0"; //调用
	public static final String WEBSERVICE_CALL_TYPE_PASSIVE  = "1"; // 被调用
	
	/** WEB服务-结果类型 **/
	public static final String WEBSERVICE_RESULT_TYPE_NORMAL  = "0"; // 正常
	public static final String WEBSERVICE_RESULT_TYPE_EXCEPTION  = "1"; // 异常
	public static final String WEBSERVICE_RESULT_TYPE_INTERFACE_EXCEPTION  = "2"; // 调用接口异常
	public static final String WEBSERVICE_RESULT_TYPE_INTERFACE_EXCEPTION_COMPLETE  = "3"; // 调用接口异常处理完成
	public static final String WEBSERVICE_RESULT_TYPE_500_EXCEPTION  = "4"; // 500异常
	public static final String WEBSERVICE_RESULT_TYPE_500_EXCEPTION_COMPLETE  = "5"; // 500异常处理完成
	
}
