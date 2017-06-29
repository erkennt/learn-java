package learn.second.domain.service.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodePassword {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Base64エンコードを行う.
	 *
	 * @param bytes
	 *            Base64エンコードを施す対象データ
	 * @return Base64エンコード処理後の文字列
	 */
	public static String base64Encode(byte[] bytes) {
		return (new Base64()).encodeToString(bytes);
	}

	/**
	 * Base64デコードを行う.
	 *
	 * @param encoded
	 *            Base64エンコード後の文字列.
	 * @return デコード後の文字列.
	 * @throws UnsupportedEncodingException
	 *             不正な文字エンコードを指定した場合にthrowされる.
	 */
	public static String base64Decode(String encoded) throws UnsupportedEncodingException {
		byte[] buff = (new Base64()).decode(encoded);
		return new String(buff, "utf-8");
	}

	/**
	 * 認証キーの作成
	 *
	 * @param target
	 *            認証元の文字列
	 * @param signatureKey
	 *            認証キーを作成する署名キー
	 * @param algorithm
	 *            アルゴリズム <br>
	 *            AES, ARCFOUR, Blowfish, DES, DESede, HmacMD5, HmacSHA1,
	 *            HmacSHA256, HmacSHA384, HmacSHA512 が利用可
	 * @return 生成した認証キー
	 * @throws NoSuchAlgorithmException
	 *             存在しないアルゴリズムの場合throw
	 * @throws InvalidKeyException
	 *             "Message Authentication Code" (MAC)
	 *             algorithmに適さないKeyを指定するとthrow
	 */
	public static String generateAuthenticationKey(String target, String signatureKey, String algorithm)
			throws NoSuchAlgorithmException, InvalidKeyException {
		// 秘密鍵の作成
		SecretKey secretKey = new SecretKeySpec(signatureKey.getBytes(), algorithm);

		// 認証キーの作成
		Mac mac = Mac.getInstance(algorithm);
		mac.init(secretKey);
		mac.update(target.getBytes());

		// 暗号化
		byte[] encryptedData = mac.doFinal();

		// base64エンコード
		return base64Encode(encryptedData);
	}



	   /**
	    * 文字列を16文字の秘密鍵でAES暗号化してBase64した文字列で返す
	    */
	   public static String encrypt(String originalSource, String secretKey, String algorithm)
	         throws NoSuchAlgorithmException, NoSuchPaddingException,
	         InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

	      byte[] originalBytes = originalSource.getBytes();
	      byte[] encryptBytes = cipher(
	            Cipher.ENCRYPT_MODE, originalBytes, secretKey, algorithm);
	      byte[] encryptBytesBase64 = Base64.encodeBase64(encryptBytes, false);
	      return new String(encryptBytesBase64);
	   }

	   /**
	    * Base64されたAES暗号化文字列を元の文字列に復元する
	    */
	   public static String decrypt(String encryptBytesBase64String,
	         String secretKey, String algorithm) throws NoSuchAlgorithmException,
	         NoSuchPaddingException, InvalidKeyException,
	         IllegalBlockSizeException, BadPaddingException {

	      byte[] encryptBytes = Base64.decodeBase64(encryptBytesBase64String);
	      byte[] originalBytes = cipher(
	            Cipher.DECRYPT_MODE, encryptBytes, secretKey, algorithm);
	      return new String(originalBytes);
	   }

	   /**
	    * 暗号化/複合化の共通部分
	    */
	   private static byte[] cipher(
	         int mode, byte[] source, String secretKey, String algorithm)
	               throws InvalidKeyException, NoSuchAlgorithmException,
	               NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
	      byte[] secretKeyBytes = secretKey.getBytes();

	      SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, algorithm);
	      Cipher cipher = Cipher.getInstance(algorithm);
	      cipher.init(mode, secretKeySpec);
	      return cipher.doFinal(source);
	   }


}