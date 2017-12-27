package com.yp.encrypt;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

/**
 * 含有密钥的摘要算法
 * 
 * @author 杨鹏
 * 
 */

public class HmacUtils {

	// hmacMD5 assign charset
	public static String hmacMD5Base64UrlSafeStr(String s, String cipher,
			String charset) {
		return Base64
				.encodeBase64URLSafeString(hmacMD5Bits(s, cipher, charset));
	}

	// hmacMD5 assign charset
	public static String hmacMD5Base64Str(String s, String cipher,
			String charset) {
		return Base64.encodeBase64String(hmacMD5Bits(s, cipher, charset));
	}

	// hmacMD5 assign charset
	public static String hmacMD5HexStr(String s, String cipher, String charset) {
		return Hex.toHexString(hmacMD5Bits(s, cipher, charset));
	}

	// hmacMD5 assign charset
	public static byte[] hmacMD5Bits(String s, String cipher, String charset) {
		HMac hmacMD5 = new HMac(new MD5Digest());
		try {
			hmacMD5.init(new KeyParameter(cipher.getBytes(charset)));
			byte[] bytes = s.getBytes(charset);
			hmacMD5.update(bytes, 0, bytes.length);
			byte[] hmacMD5bs = new byte[hmacMD5.getMacSize()];
			hmacMD5.doFinal(hmacMD5bs, 0);
			return hmacMD5bs;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	// hmacSHA1 assign charset
	public static String hmacSHA1Base64UrlSafeStr(String s, String cipher,
			String charset) {
		return Base64
				.encodeBase64URLSafeString(hmacSHA1Bits(s, cipher, charset));
	}

	// hmacSHA1 assign charset
	public static String hmacSHA1Base64Str(String s, String cipher,
			String charset) {
		return Base64.encodeBase64String(hmacSHA1Bits(s, cipher, charset));
	}

	// hmacSHA1 assign charset
	public static String hmacSHA1HexStr(String s, String cipher, String charset) {
		return Hex.toHexString(hmacSHA1Bits(s, cipher, charset));
	}

	// hmacSHA1 assign charset
	public static byte[] hmacSHA1Bits(String s, String cipher, String charset) {
		HMac hmacSHA1 = new HMac(new SHA1Digest());
		try {
			hmacSHA1.init(new KeyParameter(cipher.getBytes(charset)));
			byte[] bytes = s.getBytes(charset);
			hmacSHA1.update(bytes, 0, bytes.length);
			byte[] hmacSHA1bs = new byte[hmacSHA1.getMacSize()];
			hmacSHA1.doFinal(hmacSHA1bs, 0);
			return hmacSHA1bs;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	// hmacSHA256 assign charset
	public static String hmacSHA256Base64UrlSafeStr(String s, String cipher,
			String charset) {
		return Base64.encodeBase64URLSafeString(hmacSHA256Bits(s, cipher,
				charset));
	}

	// hmacSHA256 assign charset
	public static String hmacSHA256Base64Str(String s, String cipher,
			String charset) {
		return Base64.encodeBase64String(hmacSHA256Bits(s, cipher, charset));
	}

	// hmacSHA256 assign charset
	public static String hmacSHA256HexStr(String s, String cipher,
			String charset) {
		return Hex.toHexString(hmacSHA256Bits(s, cipher, charset));
	}

	// hmacSHA256 assign charset
	public static byte[] hmacSHA256Bits(String s, String cipher, String charset) {
		HMac hmacSHA256 = new HMac(new SHA256Digest());
		try {
			hmacSHA256.init(new KeyParameter(cipher.getBytes(charset)));
			byte[] bytes = s.getBytes(charset);
			hmacSHA256.update(bytes, 0, bytes.length);
			byte[] hmacSHA256bs = new byte[hmacSHA256.getMacSize()];
			hmacSHA256.doFinal(hmacSHA256bs, 0);
			return hmacSHA256bs;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	// hmacSHA512 assign charset
	public static String hmacSHA512Base64UrlSafeStr(String s, String cipher,
			String charset) {
		return Base64.encodeBase64URLSafeString(hmacSHA512Bits(s, cipher,
				charset));
	}

	// hmacSHA512 assign charset
	public static String hmacSHA512Base64Str(String s, String cipher,
			String charset) {
		return Base64.encodeBase64String(hmacSHA512Bits(s, cipher, charset));
	}

	// hmacSHA512 assign charset
	public static String hmacSHA512HexStr(String s, String cipher,
			String charset) {
		return Hex.toHexString(hmacSHA512Bits(s, cipher, charset));
	}

	// hmacSHA512 assign charset
	public static byte[] hmacSHA512Bits(String s, String cipher, String charset) {
		HMac hmacSHA512 = new HMac(new SHA512Digest());
		try {
			hmacSHA512.init(new KeyParameter(cipher.getBytes(charset)));
			byte[] bytes = s.getBytes(charset);
			hmacSHA512.update(bytes, 0, bytes.length);
			byte[] hmacSHA512bs = new byte[hmacSHA512.getMacSize()];
			hmacSHA512.doFinal(hmacSHA512bs, 0);
			return hmacSHA512bs;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	// hmacMD5 default charset
	public static String hmacMD5Base64UrlSafeStr(String s, String cipher) {
		return Base64.encodeBase64URLSafeString(hmacMD5Bits(s, cipher));
	}

	// hmacMD5 default charset
	public static String hmacMD5Base64Str(String s, String cipher) {
		return Base64.encodeBase64String(hmacMD5Bits(s, cipher));
	}

	// hmacMD5 default charset
	public static String hmacMD5HexStr(String s, String cipher) {
		return Hex.toHexString(hmacMD5Bits(s, cipher));
	}
	
	@Test
	public void generateTestPwd() {
		//py1234
		String dbPwd = hmacMD5HexStr("py1234", "pengyang");
		//b9501b87df416d8c13054c8ee6802bbb
		System.out.println(dbPwd);
	}

	// hmacMD5 default charset
	public static byte[] hmacMD5Bits(String s, String cipher) {
		HMac hmacMD5 = new HMac(new MD5Digest());
		hmacMD5.init(new KeyParameter(cipher.getBytes()));
		byte[] bytes = s.getBytes();
		hmacMD5.update(bytes, 0, bytes.length);
		byte[] hmacMD5bs = new byte[hmacMD5.getMacSize()];
		hmacMD5.doFinal(hmacMD5bs, 0);
		return hmacMD5bs;
	}

	// hmacSHA1 default charset
	public static String hmacSHA1Base64UrlSafeStr(String s, String cipher) {
		return Base64.encodeBase64URLSafeString(hmacSHA1Bits(s, cipher));
	}

	// hmacSHA1 default charset
	public static String hmacSHA1Base64Str(String s, String cipher) {
		return Base64.encodeBase64String(hmacSHA1Bits(s, cipher));
	}

	// hmacSHA1 default charset
	public static String hmacSHA1HexStr(String s, String cipher) {
		return Hex.toHexString(hmacSHA1Bits(s, cipher));
	}

	// hmacSHA1 default charset
	public static byte[] hmacSHA1Bits(String s, String cipher) {
		HMac hmacSHA1 = new HMac(new SHA1Digest());
		hmacSHA1.init(new KeyParameter(cipher.getBytes()));
		byte[] bytes = s.getBytes();
		hmacSHA1.update(bytes, 0, bytes.length);
		byte[] hmacSHA1bs = new byte[hmacSHA1.getMacSize()];
		hmacSHA1.doFinal(hmacSHA1bs, 0);
		return hmacSHA1bs;
	}

	// hmacSHA256 default charset
	public static String hmacSHA256Base64UrlSafeStr(String s, String cipher) {
		return Base64.encodeBase64URLSafeString(hmacSHA256Bits(s, cipher));
	}

	// hmacSHA256 default charset
	public static String hmacSHA256Base64Str(String s, String cipher) {
		return Base64.encodeBase64String(hmacSHA256Bits(s, cipher));
	}

	// hmacSHA256 default charset
	public static String hmacSHA256HexStr(String s, String cipher) {
		return Hex.toHexString(hmacSHA256Bits(s, cipher));
	}

	// hmacSHA256 default charset
	public static byte[] hmacSHA256Bits(String s, String cipher) {
		HMac hmacSHA256 = new HMac(new SHA256Digest());
		hmacSHA256.init(new KeyParameter(cipher.getBytes()));
		byte[] bytes = s.getBytes();
		hmacSHA256.update(bytes, 0, bytes.length);
		byte[] hmacSHA256bs = new byte[hmacSHA256.getMacSize()];
		hmacSHA256.doFinal(hmacSHA256bs, 0);
		return hmacSHA256bs;
	}

	// hmacSHA512 default charset
	public static String hmacSHA512Base64UrlSafeStr(String s, String cipher) {
		return Base64.encodeBase64URLSafeString(hmacSHA512Bits(s, cipher));
	}

	// hmacSHA512 default charset
	public static String hmacSHA512Base64Str(String s, String cipher) {
		return Base64.encodeBase64String(hmacSHA512Bits(s, cipher));
	}

	// hmacSHA512 default charset
	public static String hmacSHA512HexStr(String s, String cipher) {
		return Hex.toHexString(hmacSHA512Bits(s, cipher));
	}

	// hmacSHA512 default charset
	public static byte[] hmacSHA512Bits(String s, String cipher) {
		HMac hmacSHA512 = new HMac(new SHA512Digest());
		hmacSHA512.init(new KeyParameter(cipher.getBytes()));
		byte[] bytes = s.getBytes();
		hmacSHA512.update(bytes, 0, bytes.length);
		byte[] hmacSHA512bs = new byte[hmacSHA512.getMacSize()];
		hmacSHA512.doFinal(hmacSHA512bs, 0);
		return hmacSHA512bs;
	}

}
