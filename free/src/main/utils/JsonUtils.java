package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import net.sf.json.JSONObject;
import utils.base.StringUtils;

public class JsonUtils {

	/**
	 * @Title: returnJsonSuccess @Description: 返回JSON成功，不带参数 @param 入参 @return 出参 @throws
	 */
	public static String returnJsonSuccess() {
		return returnJsonSuccess(null, null);
	}

	/**
	 * @Title: returnJsonSuccessMsg @Description: 返回JSON成功，并可指定描述信息 @param 入参 @return 出参 @throws
	 */
	public static String returnJsonSuccessMsg(String resMsg) {
		return returnJsonSuccess(resMsg, null);
	}

	/**
	 * @Title: returnJsonSuccess @Description: 返回JSON成功,并返回具体的INFO信息 @param 入参 @return 出参 @throws
	 */
	public static String returnJsonSuccess(Object info) {
		return returnJsonSuccess(null, info);
	}

	/**
	 * @Title: returnJsonSuccess @Description: 返回JSON成功, 并可指定描述信息,并返回具体的INFO信息 @param 入参 @return 出参 @throws
	 */
	public static String returnJsonSuccess(String resMsg, Object info) {
		JSONObject res = new JSONObject();
		res.accumulate(Constants.RES_CODE, Constants.RES_CODE_SUCCESS);
		if (!StringUtils.isEmpty(resMsg)) {
			res.accumulate(Constants.RES_MSG, resMsg);
		}
		if (info != null && !"".equals(info)) {
			res.accumulate(Constants.RES_INFO, info);
		}
		return res.toString();
	}

	/**
	 * @Title: returnJsonSuccess @Description: 返回JSON失败, 并可指定描述信息,并返回具体的INFO信息 @param 入参 @return 出参 @throws
	 */
	public static String returnJsonErrorAndInfo(String resMsg, String info) {
		JSONObject res = new JSONObject();
		res.accumulate(Constants.RES_CODE, Constants.RES_CODE_FAIL);
		if (!StringUtils.isEmpty(resMsg)) {
			res.accumulate(Constants.RES_MSG, resMsg);
		}
		if (!StringUtils.isEmpty(info)) {
			res.accumulate(Constants.RES_INFO, info);
		}
		return res.toString();
	}

	/**
	 * @Title: returnJsonError @Description: 返回JSON失败，并可指定描述失败原因 @param 入参 @return 出参 @throws
	 */
	public static String returnJsonError(String resMsg) {
		JSONObject res = new JSONObject();
		res.accumulate(Constants.RES_CODE, Constants.RES_CODE_FAIL);
		res.accumulate(Constants.RES_MSG, resMsg);
		return res.toString();
	}

	/**
	 * @Title: returnJsonError @Description: 返回JSON失败，并可指定描述失败原因 @param 入参 @return 出参 @throws
	 */
	public static String returnJsonError(String resCode, String resMsg) {
		JSONObject res = new JSONObject();
		res.accumulate(Constants.RES_CODE, resCode);
		res.accumulate(Constants.RES_MSG, resMsg);
		return res.toString();
	}

	/**
	 * 解析传入的JSON数据，返回是否调用成功，true为成功，false为失败
	 * 
	 * @param res
	 * @return
	 */
	public static Boolean callResult(String res) {
		try {
			JSONObject json = JSONObject.fromObject(res);
			if (Constants.RES_CODE_SUCCESS.equals(json.getString(Constants.RES_CODE))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 解析传入的JSON数据，判断是否是正确的结果。判断依据是res_code=0000
	 * 
	 * @param res
	 * @return
	 */
	public static boolean isSuccess(String res) {
		try {
			JSONObject json = JSONObject.fromObject(res);
			if (Constants.RES_CODE_SUCCESS.equals(json.getString(Constants.RES_CODE))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 解析传入的JSON数据，判断是否是正确的结果。判断依据是res_code=0000
	 * 
	 * @param res
	 * @return
	 */
	public static boolean isSuccess(JSONObject res) {
		try {
			if (Constants.RES_CODE_SUCCESS.equals(res.getString(Constants.RES_CODE))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 解析传入的JSON串，判断RES_CODE是否与参数一致。
	 * 
	 * @param res
	 * @param resCode
	 * @return
	 */
	public static boolean existResCode(String res, String resCode) {
		JSONObject json = JSONObject.fromObject(res);
		if (resCode.equals(json.getString(Constants.RES_CODE))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 解析传入的JSON数据，返回RES_MSG数据
	 * 
	 * @param res
	 * @return
	 */
	public static String getResMsg(String res) {
		if (!StringUtils.isEmpty(res)) {
			JSONObject json = JSONObject.fromObject(res);
			return json.getString(Constants.RES_MSG);
		}
		return null;
	}

	/**
	 * 解析传入的JSON数据，返回info数据，如果没有调用成功则返回false
	 * 
	 * @param res
	 * @return
	 */
	public static boolean getInfo(String res) {
		try {
			JSONObject json = JSONObject.fromObject(res);
			if (Constants.RES_CODE_SUCCESS.equals(json.getString(Constants.RES_CODE))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 解析传入的JSON数据，返回info数据，如果没有调用成功则返回null
	 * 
	 * @param res
	 * @return
	 */
	public static Object getInfo(String res, String key) {
		try {
			JSONObject json = JSONObject.fromObject(res);
			if (Constants.RES_CODE_SUCCESS.equals(json.getString(Constants.RES_CODE))) {
				return json.getJSONObject(Constants.RES_INFO).get(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: getInfoStr
	 * @Description: 获取infro信息 转字符串
	 * @param @param
	 *            res
	 * @param @return
	 * @return String
	 *
	 * @author WeiGd
	 * @date 2016年9月27日 下午6:09:12
	 */
	public static String getInfoStr(String res) {
		try {
			JSONObject json = JSONObject.fromObject(res);
			if (Constants.RES_CODE_SUCCESS.equals(json.getString(Constants.RES_CODE))) {
				return json.getString(Constants.RES_INFO);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * java生成随机数字和字母组合
	 * 
	 * @param length[生成随机数的长度]
	 * @return
	 */
	public static String getCharAndNumr(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 随机生成num位数字
	 * 
	 * @param num
	 */
	public static String getRandomNum(int num) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}

	/**
	 * 根据当前时间算出前N月天数
	 * 
	 * @param month
	 * @return
	 */
	public static Date getCalendarMonth(int month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.MONTH, -month);
		ca.add(Calendar.DATE, +1);
		String startDate = sdf.format(ca.getTime());
		try {
			return sdf.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据当前时间算出前N天天数
	 * 
	 * @param month
	 * @return
	 */
	public static Date getCalendarDate(int month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.DATE, -month);
		String startDate = sdf.format(ca.getTime());
		try {
			return sdf.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * MD5加密
	 * 
	 * @param plainText
	 * @return
	 */
	public static String getMd5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
			// 16位的加密
			// return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

	}
}
