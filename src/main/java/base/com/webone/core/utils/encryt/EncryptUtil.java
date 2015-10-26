package com.webone.core.utils.encryt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.webone.core.utils.hexbinary.BinaryUtil;


/**
 * 加密、解密工具类
 * @author skz
 * @date 2015年10月25日
 * @time 下午9:04:52
 */
public class EncryptUtil {

	private final static String PASSWORD_CRYPT_KEY = "__skz&J_";
	private final static String DES = "DES";
	
	
	
	/**
	 * DES算法加密
	 * @param src	加密数据
	 * @param key	密钥,长度须为8的倍数
	 * @return 返回加密后的数据
	 */
	public static byte[] DESEncrypt(byte[] src, byte[] key) throws Exception{
		
		//DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		
		//从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		
		//创建一个密钥工厂，然后用它把DESKeySpec转成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey secureKey = keyFactory.generateSecret(dks);
		
		//Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		//用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, secureKey,sr);
		
		//获取数据并加密
		return cipher.doFinal(src);
	}
	
	
	
	
	/**
	 * DES算法解密
	 * @param src	数据
	 * @param key	密钥,长度须为8的倍数
	 * @return	返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] DESDecrypt(byte[] src,byte[] key) throws Exception{
		
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		
		Cipher cipher = Cipher.getInstance(DES);
		
		//解密
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		
		return cipher.doFinal(src);
	}
	
	
	/**
	 * DES 算法加密密码
	 * @param password
	 * @return
	 */
	public final static String DESEncryptPassword(String password){
		
		try {
			return BinaryUtil.byte2hex(DESEncrypt(password.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * TODO
	 * @see http://blog.csdn.net/huahuagongzi99999/article/details/8535751
	 * DES算法解密密码
	 * @param data
	 * @return
	 */
	public final static String DESDecrptPassword(String data){
		
		try {
			return new String(DESDecrypt(BinaryUtil.hex2byte(data.getBytes()),
					PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
