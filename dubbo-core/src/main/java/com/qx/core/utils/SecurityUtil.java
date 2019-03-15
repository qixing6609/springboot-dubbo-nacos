package com.qx.core.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecurityUtil {
	
	public static String PWD = "ts-sunshine@2017";
	/***
	 * MD5 加密
	 */
	public static String MD5(String str) {
		if (str == null)
			return null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes("UTF-8"));
			byte[] digest = md5.digest();
			StringBuffer hexString = new StringBuffer();
			String strTemp;
			for (int i = 0; i < digest.length; i++) {
				strTemp = Integer.toHexString((digest[i] & 0x000000FF) | 0xFFFFFF00).substring(6);
				hexString.append(strTemp);
			}
			return hexString.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	// ==Base64加解密==================================================================
	/**
	 * Base64加密
	 */
	public static String Base64Encode(String str) throws UnsupportedEncodingException {
		return new BASE64Encoder().encode(str.getBytes("UTF-8"));
	}

	/**
	 * 解密
	 */
	public static String Base64Decode(String str) throws UnsupportedEncodingException, IOException {
//		str = str.replaceAll(" ", "+");
		return new String(new BASE64Decoder().decodeBuffer(str), "UTF-8");
	}
	/**
     * sha1计算后进行16进制转换
     *
     * @param data     待计算的数据
     * @param encoding 编码
     * @return 计算结果
     * @throws UnsupportedEncodingException 
     */
    public static String sha1X16(String data, String encoding) throws Exception {
        byte[] bytes = sha1(data.getBytes(encoding));
        return byte2Hex(bytes);
    }
    /**
     * sha1计算.
     *
     * @param data 待计算的数据
     * @return 计算结果
     */
    public static byte[] sha1(byte[] data) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.reset();
            md.update(data);
            return md.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	// ==Aes加解密==================================================================
	/**
	 * aes解密-128位
	 */
	public static String AesDecrypt(String encryptContent, String password) {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			keyGen.init(128, secureRandom);
			SecretKey secretKey = keyGen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(hex2Bytes(encryptContent)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// ==Aes加解密==================================================================
		/**
		 * aes解密-128位
		 */
		public static String AesDecrypts(String encryptContent, String password) {
			if (StringUtils.isEmpty(password) || password.length() != 16) {
				throw new RuntimeException("密钥长度为16位");
			}
			try {
				String key = password;
				String iv = password;
				byte[] encrypted1 = hex2Bytes(encryptContent);
				Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
				SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
				IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
				cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
				byte[] original = cipher.doFinal(encrypted1);
				return new String(original,"UTF-8").trim();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("aes解密发生错误", e);
			}
		}

	/**
	 * aes加密-128位
	 */
	public static String AesEncrypt(String content, String password) {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			keyGen.init(128, secureRandom);
			SecretKey secretKey = keyGen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return byte2Hex(cipher.doFinal(content.getBytes("UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * 将byte[] 转换成字符串
	 */
	public static String byte2Hex(byte[] srcBytes) {
		StringBuilder hexRetSB = new StringBuilder();
		for (byte b : srcBytes) {
			String hexString = Integer.toHexString(0x00ff & b);
			hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
		}
		return hexRetSB.toString();
	}

	/**
	 * 将16进制字符串转为转换成字符串
	 */
	public static byte[] hex2Bytes(String source) {
		byte[] sourceBytes = new byte[source.length() / 2];
		for (int i = 0; i < sourceBytes.length; i++) {
			sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
		}
		return sourceBytes;
	}

	/**
	 * DES加密
	 */
	public static String desEncrypt(String source, String desKey) {
		try {
			// 从原始密匙数据创建DESKeySpec对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(new DESKeySpec(desKey.getBytes()));
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey);
			// 现在，获取数据并加密
			byte[] destBytes = cipher.doFinal(source.getBytes("UTF-8"));
			StringBuilder hexRetSB = new StringBuilder();
			for (byte b : destBytes) {
				String hexString = Integer.toHexString(0x00ff & b);
				hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
			}
			return hexRetSB.toString();
		} catch (Exception e) {
			throw new RuntimeException("DES加密发生错误", e);
		}
	}

	/**
	 * DES解密
	 */
	public static String desDecrypt(String source, String desKey) {
		// 解密数据
		byte[] sourceBytes = new byte[source.length() / 2];
		for (int i = 0; i < sourceBytes.length; i++) {
			sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
		}
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(new DESKeySpec(desKey.getBytes()));
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey);
			// 现在，获取数据并解密
			byte[] destBytes = cipher.doFinal(sourceBytes);
			return new String(destBytes,"UTF-8");
		} catch (Exception e) {
			throw new RuntimeException("DES解密发生错误", e);
		}
	}

	/**
	 * 3DES加密
	 */
	public static byte[] threeDesEncrypt(byte[] src, byte[] keybyte) throws Exception {
		try {
			// 生成密钥
			byte[] key = new byte[24];
			if (keybyte.length < key.length) {
				System.arraycopy(keybyte, 0, key, 0, keybyte.length);
			} else {
				System.arraycopy(keybyte, 0, key, 0, key.length);
			}
			SecretKey deskey = new SecretKeySpec(key, "DESede");
			// 加密
			Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (Exception e) {
			throw new Exception("3DES加密发生错误", e);
		}
	}

	/**
	 * 3DES解密
	 */
	public static byte[] threeDesDecrypt(byte[] src, byte[] keybyte) throws Exception {
		try {
			// 生成密钥
			byte[] key = new byte[24];
			if (keybyte.length < key.length) {
				System.arraycopy(keybyte, 0, key, 0, keybyte.length);
			} else {
				System.arraycopy(keybyte, 0, key, 0, key.length);
			}
			SecretKey deskey = new SecretKeySpec(key, "DESede");
			// 解密
			Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (Exception e) {
			throw new Exception("3DES解密发生错误", e);
		}
	}

	/**
	 * 3DES加密
	 */
	public static String threeDesEncrypt(String src, String key) throws Exception {
		return byte2Hex(threeDesEncrypt(src.getBytes(), key.getBytes()));
	}

	/**
	 * 3DES加密
	 */
	public static String threeDesDecrypt(String src, String key) throws Exception {
		return new String(threeDesDecrypt(hex2Bytes(src), key.getBytes()));
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(desEncrypt("6222021001113527219", "card.encrypt.baofoo.com")); 
//		System.out.println(desDecrypt("2a69589767bb68cc1fe7e5e7ba03ea59b4988e3dcb9c126f", "fi_gw_express_order.card_no")); 
		System.out.println(desDecrypt("3cb647260f35d7a0ca1261931553e2db", SecurityUtil.PWD));
		
		
		String str = "基本过程就是对原来为明文的文件或数据按某种算法进行处理，使其成为不可读的一段代码，通常称为“密文”，" 
				+ "使其只能在输入相应的密钥之后才能显示出本来内容，通过这样的途径来达到保护数据不被非法人窃取、阅读的目的。 " 
				+ "该过程的逆过程为解密，即将该编码信息转化为其原来数据的过程。";
		str+=str;str+=str;str+=str;
		String PWD = "12345678";
//		str = "15727326557";
		str = "王北斗测试王北斗测试王北";
//		str = "411111111111111111111111";
		
		String des = "ea21bc0d4ffbcb624ef65d1bfd3f386ca4db9096a92985ddd0e947969c2403cece991fcca3711e44ad0dc254caa57473caafe5dc58450bc9469d575f7d2e540c6e631cc5ed5a36b455ddb2dc220e961cd7e7fca766070c73c056b630d4c704e586b26dc1ece979f46082978edf22acc0daac9b591cd4d29011ecbfce1db967294ef65d1bfd3f386c6c323ac49d38e0df";
		System.out.println("原文:[" + str.length() + "]" + str);
		System.out.println("==MD5===============");
		System.out.println(MD5(str));
		System.out.println("==Base64============");
		String strBase64 = Base64Encode(str);
		System.out.println("加密:[" + strBase64.length() + "]" + strBase64);
		System.out.println("解密:" + Base64Decode(strBase64));
		System.out.println("==Aes============");
		String strAes = AesEncrypt(str, PWD);
		System.out.println("加密:[" + strAes.length() + "]" + strAes);
		System.out.println("解密:" + AesDecrypt(strAes, PWD));
		System.out.println("==Des==============");
		String strDes = desEncrypt(str, PWD);
		System.out.println("加密:[" + des.length() + "]" + des);
		System.out.println("解密:" + desDecrypt(des, PWD));
		System.out.println("==3Des==============");
		String str3Des = threeDesEncrypt(str, PWD);
		System.out.println("加密:[" + str3Des.length() + "]" + str3Des);
		System.out.println("解密:" + threeDesDecrypt(str3Des, PWD));
		
		//==========================================
		int count=1;
		
		long t1=System.currentTimeMillis();   
		for (int i = 0; i < count; i++) 
			MD5(str);
		System.out.println("\nMD5:"+(System.currentTimeMillis()-t1));
		t1=System.currentTimeMillis();   
		for (int i = 0; i < count; i++) 
			Base64Encode(str);
		System.out.println("Base64:"+(System.currentTimeMillis()-t1));
		t1=System.currentTimeMillis();   
		for (int i = 0; i < count; i++) 
			AesEncrypt(str, PWD);
		System.out.println("Aes:"+(System.currentTimeMillis()-t1));
		t1=System.currentTimeMillis();   
		for (int i = 0; i < count; i++) 
			desEncrypt(str, PWD);
		System.out.println("Des:"+(System.currentTimeMillis()-t1));
		t1=System.currentTimeMillis();   
		for (int i = 0; i < count; i++) 
			threeDesEncrypt(str, PWD);
		System.out.println("3Des:"+(System.currentTimeMillis()-t1));
		//=======================================
		t1=System.currentTimeMillis();   
		for (int i = 0; i < count; i++) 
			Base64Decode(strBase64);
		System.out.println("\nBase64:"+(System.currentTimeMillis()-t1));
		t1=System.currentTimeMillis();   
		for (int i = 0; i < count; i++) 
			AesDecrypt(strAes, PWD);
		System.out.println("Aes:"+(System.currentTimeMillis()-t1));
		t1=System.currentTimeMillis();   
		for (int i = 0; i < count; i++) 
			desDecrypt(strDes, PWD);
		System.out.println("Des:"+(System.currentTimeMillis()-t1));
		t1=System.currentTimeMillis();   
		for (int i = 0; i < count; i++) 
			threeDesDecrypt(str3Des, PWD);
		System.out.println("3Des:"+(System.currentTimeMillis()-t1));

		
	}

	/**
	 * (这里用一句话描述这个方法的作用)<BR>
	 * 方法名：AesEncrypts<BR>
	 * 创建人：shixiaofei <BR>
	 * 时间：2018年5月17日-下午4:26:51 <BR>
	 * @param base64Encode
	 * @param aesKey
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	*/
	public static  String AesEncrypts(String content ,String key){
		if (StringUtils.isEmpty(key) || key.length() != 16) {
			throw new RuntimeException("密钥长度为16位");
		}
		try {
			String iv = key;
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = content.getBytes("utf-8");
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(plaintext);
			return byte2Hex(encrypted);

		} catch (Exception e) {
			throw new RuntimeException("aes加密发生错误", e);
		}
	}

}
