package com.ray.power.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
/**
 * 身份证工具类
 * */
public class IdentityUtil {

	public static String getConfigPassword() {
		try {
			Properties configProperties = new Properties();
			configProperties.load(IdentityUtil.class.getClassLoader().getResourceAsStream("config.properties"));
			String ps = configProperties.getProperty("allps");
			return ps;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Resource
	private static Properties identityProperties = new Properties(); // 城市行政编码配置文件
	//private static PreparedStatement provincePs;
	//private static PreparedStatement cityPs;
	// static Connection conn = IReportUtil.getConn();
	private static Integer CHINA_ID_MIN_LENGTH = 15;
	private static Integer CHINA_ID_MAX_LENGTH = 18;

	public static final int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	static { // ZhaoHb2014.4.23
		try {
			identityProperties.load(IdentityUtil.class.getClassLoader().getResourceAsStream("identity.properties"));
			// provincePs = conn.prepareStatement("SELECT * FROM crm_sd_province
			// WHERE name = ?");
			// cityPs = conn.prepareStatement("SELECT * FROM crm_sd_city WHERE
			// name = ?");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据身份证号获得生日
	 * 
	 * @param identityStr
	 * @return
	 */
	public static Date getBirthday(String identityStr) {
		String birthdayStr = identityStr.substring(6, 14);
		Calendar cal = Calendar.getInstance();
		cal.setTime(RayDateUtils.strToDate("yyyyMMdd", birthdayStr));
		// 当月份超过12会向年进位，日超过31会向月份进位，判断年和月有没有进位，进位则返回 null
		if (cal.get(Calendar.YEAR) == Integer.valueOf(identityStr.substring(6, 10))
				&& cal.get(Calendar.MONTH) + 1 == Integer.valueOf(identityStr.substring(10, 12))) {
			return cal.getTime();
		}

		return null;
	}

	/**
	 * 根据身份证号获得年龄
	 * 
	 * @param identityStr
	 * @return
	 */
	public static Integer getAge(String identityStr) {
		String year = identityStr.substring(6, 10);
		Calendar cal = Calendar.getInstance();
		Integer currYear = cal.get(Calendar.YEAR);
		return currYear - Integer.valueOf(year);
	}

	/**
	 * 根据身份证号获得性别
	 * 
	 * @param identityStr
	 * @return 1男 0女
	 */
	public static Integer getGender(String identityStr) {
		String gender = identityStr.substring(16, 17);
		return Integer.valueOf(gender) % 2;
	}

	/**
	 * 将15位身份证号码转换为18位
	 * 
	 * @param idCard
	 *            15位身份编码
	 * @return 18位身份编码
	 */
	public static String conver15CardTo18(String idCard) {
		String idCard18 = "";
		if (idCard.length() != CHINA_ID_MIN_LENGTH) {
			return null;
		}
		if (isNum(idCard)) {
			// 获取出生年月日
			String birthday = idCard.substring(6, 12);
			Date birthDate = null;
			try {
				birthDate = RayDateUtils.strToDate("yyMMdd", birthday);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			if (birthDate != null)
				cal.setTime(birthDate);
			// 获取出生年(完全表现形式,如：2010)
			String sYear = String.valueOf(cal.get(Calendar.YEAR));
			idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
			// 转换字符数组
			char[] cArr = idCard18.toCharArray();
			if (cArr != null) {
				int[] iCard = converCharToInt(cArr);
				int iSum17 = getPowerSum(iCard);
				// 获取校验位
				String sVal = getCheckCode18(iSum17);
				if (sVal.length() > 0) {
					idCard18 += sVal;
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
		return idCard18;
	}

	/**
	 * 验证18位身份编码是否合法
	 *
	 * @param idCard
	 *            身份编码
	 * @return 是否合法
	 */
	public static boolean validateIdCard18(String idCard) {
		boolean bTrue = false;
		if (idCard.length() == CHINA_ID_MAX_LENGTH) {
			// 前17位
			String code17 = idCard.substring(0, 17);
			// 第18位
			String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
			if (isNum(code17)) {
				char[] cArr = code17.toCharArray();
				if (cArr != null) {
					int[] iCard = converCharToInt(cArr);
					int iSum17 = getPowerSum(iCard);
					// 获取校验位
					String val = getCheckCode18(iSum17);
					if (val.length() > 0) {
						if (val.equalsIgnoreCase(code18)) {
							bTrue = true;
						}
					}
				}
			}
		}
		return bTrue;
	}

	/**
	 * 数字验证
	 * 
	 * @param val
	 * @return 提取的数字。
	 */
	public static boolean isNum(String val) {
		return val == null || "".equals(val) ? false : val.matches("^[0-9]*$");
	}

	/**
	 * 将字符数组转换成数字数组
	 *
	 * @param ca
	 *            字符数组
	 * @return 数字数组
	 */
	public static int[] converCharToInt(char[] ca) {
		int len = ca.length;
		int[] iArr = new int[len];
		try {
			for (int i = 0; i < len; i++) {
				iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return iArr;
	}

	/**
	 * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
	 *
	 * @param iArr
	 * @return 身份证编码。
	 */
	public static int getPowerSum(int[] iArr) {
		int iSum = 0;
		if (power.length == iArr.length) {
			for (int i = 0; i < iArr.length; i++) {
				for (int j = 0; j < power.length; j++) {
					if (i == j) {
						iSum = iSum + iArr[i] * power[j];
					}
				}
			}
		}
		return iSum;
	}

	/**
	 * 将power和值与11取模获得余数进行校验码判断
	 *
	 * @param iSum
	 * @return 校验位
	 */
	public static String getCheckCode18(int iSum) {
		String sCode = "";
		switch (iSum % 11) {
		case 10:
			sCode = "2";
			break;
		case 9:
			sCode = "3";
			break;
		case 8:
			sCode = "4";
			break;
		case 7:
			sCode = "5";
			break;
		case 6:
			sCode = "6";
			break;
		case 5:
			sCode = "7";
			break;
		case 4:
			sCode = "8";
			break;
		case 3:
			sCode = "9";
			break;
		case 2:
			sCode = "x";
			break;
		case 1:
			sCode = "0";
			break;
		case 0:
			sCode = "1";
			break;
		}
		return sCode;
	}
}
