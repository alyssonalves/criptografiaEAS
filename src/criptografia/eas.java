package criptografia;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class eas {

	static String textoOriginal = "Teste de Criptografia para Projeto da Shell";
	static byte[] chaveencriptacao = new byte[32];

	public static void main(String[] args) {

		try {

			System.out.println("Texto Original: " + textoOriginal);

			byte[] textoencriptado = encrypt(textoOriginal, chaveencriptacao);

			System.out.print("Texto Encriptado: ");

			for (int i=0; i<textoencriptado.length; i++)
				System.out.print(new Integer(textoencriptado[i])+" ");

			System.out.println("");

			String textodecriptado = decrypt(textoencriptado, chaveencriptacao);

			System.out.println("Texto Decriptado: " + textodecriptado);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] encrypt(String textopuro, byte[] chaveencriptacao) throws Exception {
		
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao, "AES");
		
		encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(new byte[16]));
		
		return encripta.doFinal(textopuro.getBytes("UTF-8"));
	}

	public static String decrypt(byte[] textoencriptado, byte[] chaveencriptacao) throws Exception{
		
		Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao, "AES");
		
		decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(new byte[16]));
		
		return new String(decripta.doFinal(textoencriptado),"UTF-8");
	}

}
