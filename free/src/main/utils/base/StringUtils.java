package utils.base;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.Assert;

public final class StringUtils {

	private StringUtils() {
	}

	public static String killNull(String str) {
		String returnStr = null;
		if (str == null || "null".equals(str.toLowerCase()))
			returnStr = "";
		else
			returnStr = str;
		return returnStr;
	}

	public static String trim(String str) {
		String returnStr = null;
		returnStr = killNull(str);
		returnStr = returnStr.trim();
		return returnStr;
	}

	public static String stringToASCII(String transParam) {
		if (transParam == null || transParam.length() == 0)
			return null;
		char transChars[] = transParam.toCharArray();
		String ascii = "";
		int charASCII = -1;
		for (int i = 0; i < transChars.length; i++) {
			charASCII = transChars[i];
			if (charASCII == 73 || charASCII == 79)
				charASCII++;
			ascii = (new StringBuilder(String.valueOf(ascii))).append(charASCII).toString();
		}

		return ascii;
	}

	public static boolean contains(String str, String options[]) {
		for (int i = 0; i < options.length; i++)
			if (options[i].equals(str))
				return true;

		return false;
	}

	public static boolean isStrLegalOrEmpty(String str, int maxLength) {
		if (str == null)
			return true;
		if (str.trim().equals(""))
			return true;
		else
			return isStrLegal(str, 0, maxLength);
	}

	public static boolean isStrLegal(String str, int maxLength) {
		return isStrLegal(str, 0, maxLength);
	}

	public static boolean isStrLegal(String str, int minLength, int maxLength) {
		boolean result = true;
		if (str == null)
			result = false;
		else if (str.trim().equals(""))
			result = false;
		else if (str.getBytes().length > maxLength || str.getBytes().length < minLength)
			result = false;
		return result;
	}

	public static boolean isNumLegal(String str, int maxLength) {
		return isNumLegal(str, 0, maxLength);
	}

	public static boolean isNumLegal(String str, int minLength, int maxLength) {
		if (!isStrLegal(str, minLength, maxLength))
			return false;
		else
			return isNumLegal(str);
	}

	public static boolean isNumLegal(String str) {
		if (str == null)
			return false;
		for (int i = 0; i < str.getBytes().length; i++) {
			char ch = str.charAt(i);
			if (ch < '0' || ch > '9')
				return false;
		}

		return true;
	}

	public static boolean isDateLegal(String dateStr) {
		if (dateStr == null)
			return false;
		if (dateStr.length() != 10)
			return false;
		String strArr[] = dateStr.split("-");
		if (strArr.length != 3)
			return false;
		for (int i = 0; i < strArr.length; i++)
			if (!isNumLegal(strArr[i]))
				return false;

		return true;
	}

	public static String replace(String source, String oldStr, String newStr) {
		return replace(source, oldStr, newStr, true);
	}

	public static String replace(String source, String oldStr, String newStr, boolean matchCase) {
		if (source == null)
			return null;
		if (source.toLowerCase().indexOf(oldStr.toLowerCase()) == -1)
			return source;
		int findStartPos = 0;
		for (int a = 0; a > -1;) {
			int b = 0;
			String str1 = source;
			String str2 = str1.toLowerCase();
			String str3 = oldStr;
			String str4 = str3.toLowerCase();
			String strA;
			String strB;
			if (matchCase) {
				strA = str1;
				strB = str3;
			} else {
				strA = str2;
				strB = str4;
			}
			a = strA.indexOf(strB, findStartPos);
			if (a > -1) {
				b = oldStr.length();
				findStartPos = a + b;
				StringBuffer bbuf = new StringBuffer(source);
				source = (new StringBuilder()).append(bbuf.replace(a, a + b, newStr)).toString();
				findStartPos = (findStartPos + newStr.length()) - b;
			}
		}

		return source;
	}

	public static String replaceHtmlCode(String content) {
		if (isEmpty(content))
			return "";
		String eventKeywords[] = { "onmouseover", "onmouseout", "onmousedown", "onmouseup", "onmousemove", "onclick", "ondblclick", "onkeypress",
				"onkeydown", "onkeyup", "ondragstart", "onerrorupdate", "onhelp", "onreadystatechange", "onrowenter", "onrowexit", "onselectstart",
				"onload", "onunload", "onbeforeunload", "onblur", "onerror", "onfocus", "onresize", "onscroll", "oncontextmenu" };
		content = replace(content, "<script", "&ltscript", false);
		content = replace(content, "</script", "&lt/script", false);
		content = replace(content, "<marquee", "&ltmarquee", false);
		content = replace(content, "</marquee", "&lt/marquee", false);
		content = replace(content, "\r\n", "<BR>");
		for (int i = 0; i < eventKeywords.length; i++)
			content = replace(content, eventKeywords[i], (new StringBuilder("_")).append(eventKeywords[i]).toString(), false);

		return content;
	}

	public static String replaceHtmlToText(String input) {
		if (isEmpty(input))
			return "";
		else
			return setBr(setTag(input));
	}

	public static String setTag(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		for (int i = 0; i < j; i++)
			if (s.charAt(i) == '<')
				stringbuffer.append("&lt");
			else if (s.charAt(i) == '>')
				stringbuffer.append("&gt");
			else if (s.charAt(i) == '&')
				stringbuffer.append("&amp");
			else
				stringbuffer.append(s.charAt(i));

		return stringbuffer.toString();
	}

	public static String setBr(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		for (int i = 0; i < j; i++)
			if (s.charAt(i) == '\n')
				stringbuffer.append("");
			else if (s.charAt(i) == '\r')
				stringbuffer.append("");
			else
				stringbuffer.append(s.charAt(i));

		return stringbuffer.toString();
	}

	public static String setNbsp(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		for (int i = 0; i < j; i++)
			if (s.charAt(i) == ' ')
				stringbuffer.append("&nbsp;");
			else
				stringbuffer.append(s.charAt(i));

		return stringbuffer.toString();
	}

	public static String toChi(String input) {
		try {
			byte bytes[] = input.getBytes("ISO8859-1");
			return new String(bytes);
		} catch (Exception exception) {
			return null;
		}
	}

	public static String replaceSql(String input) {
		return replace(input, "'", "''");
	}

	public static String encode(String value) throws UnsupportedEncodingException {
		if (isEmpty(value))
			return "";
		else
			return URLEncoder.encode(value, "UTF-8");
	}

	public static String decode(String value) throws UnsupportedEncodingException {
		if (isEmpty(value))
			return "";
		else
			return URLDecoder.decode(value, "UTF-8");
	}

	public static boolean isEmpty(String input) {
		return input == null || input.length() <= 0;
	}

	public static String smilies(String temp) {
		if (isEmpty(temp)) {
			return "";
		} else {
			temp = replace(temp, "/:)", "<IMG border=0 SRC=images/brow/regular_smile.gif>");
			temp = replace(temp, "/:d", "<IMG border=0 SRC=images/brow/teeth_smile.gif>");
			temp = replace(temp, "/:o", "<IMG border=0 SRC=images/brow/omg_smile.gif>");
			temp = replace(temp, "/:p", "<IMG border=0 SRC=images/brow/tounge_smile.gif>");
			temp = replace(temp, "/;)", "<IMG border=0 SRC=images/brow/wink_smile.gif>");
			temp = replace(temp, "/:(", "<IMG border=0 SRC=images/brow/sad_smile.gif>");
			temp = replace(temp, "/:s", "<IMG border=0 SRC=images/brow/confused_smile.gif>");
			temp = replace(temp, "/:|", "<IMG border=0 SRC=images/brow/whatchutalkingabout_smile.gif>");
			temp = replace(temp, "/:$", "<IMG border=0 SRC=images/brow/embaressed_smile.gif>");
			return temp;
		}
	}

	public static String getExtension(String fileName) {
		if (fileName != null) {
			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1)
				return fileName.substring(i + 1).toLowerCase();
		}
		return "";
	}

	public static String getPrefix(String fileName) {
		if (fileName != null) {
			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1)
				return fileName.substring(0, i);
		}
		return "";
	}

	public static int ignoreCaseIndexOfByRegex(String str, String regex) {
		Assert.hasText(str);
		Assert.hasText(regex);
		Pattern p = Pattern.compile(regex, 2);
		Matcher m = p.matcher(str);
		if (m.find())
			return str.indexOf(m.group());
		else
			return -1;
	}

	public static int indexOfByRegex(String str, String regex) {
		Assert.hasText(str);
		Assert.hasText(regex);
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if (m.find())
			return str.indexOf(m.group());
		else
			return -1;
	}

	public static String ignoreCaseReplaceAll(String regex, String str, String replacement) {
		Assert.hasText(str);
		Assert.hasText(regex);
		Pattern p = Pattern.compile(regex, 2);
		Matcher m = p.matcher(str);
		StringBuffer sb = new StringBuffer();
		for (; m.find(); m.appendReplacement(sb, replacement))
			;
		m.appendTail(sb);
		return sb.toString();
	}
}