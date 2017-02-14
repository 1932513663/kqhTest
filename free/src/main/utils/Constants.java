package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: Constants
 * @Title: Constants.java
 * @Description: 常量类
 *
 * @author kang
 * @version V1.0
 * @company 麦田
 * @date 2017年1月18日 上午9:15:04
 */
public class Constants {

	/**
	 * 终端
	 */
	public static final int TERMINAL_WEB = 1;// WEB端
	public static final int TERMINAL_WX = 2;// 微信端
	public static final int TERMINAL_WAP = 3;// WAP端
	public static final int TERMINAL_APP = 4;// APP
	public static final int TERMINAL_OTHER = 5;// 其他

	/**
	 * JSON相关
	 */
	public static final String RES_CODE = "resCode";
	public static final String RES_MSG = "resMsg";
	public static final String RES_INFO = "info";
	public static final String RES_INTEGRAL = "integral";

	public static final String RES_CODE_SUCCESS = "0000";// 成功
	public static final String RES_CODE_PARMAS_EMPTY = "0001";
	public static final String RES_CODE_PARAMS_ERROR = "0002";
	public static final String RES_CODE_SYSTEM_ERROR = "0004";
	public static final String RES_CODE_STATUS_ERROR = "0005";
	public static final String RES_CODE_FAIL = "1000";// 失败

	public static String getMessage(String code) {
		return messageMaps.get(code);
	}

	private static final Map<String, String> messageMaps = new HashMap<String, String>();

	static {
		messageMaps.put(RES_CODE_PARMAS_EMPTY, "参数为空，不予处理");
		messageMaps.put(RES_CODE_PARAMS_ERROR, "参数错误");
		messageMaps.put(RES_CODE_SYSTEM_ERROR, "系统异常");
		messageMaps.put(RES_CODE_FAIL, "请求失败");
	}
	
	public static final String DEL_NO = "0";// 未删除
	public static final String DEL_YES = "1";// 删除
	
	public static final String ROOT_NODE = "00000000000000000000000000000000";// 根节点id
	public static final String ROOT_NODE_NAME = "根节点";// 根节点
}