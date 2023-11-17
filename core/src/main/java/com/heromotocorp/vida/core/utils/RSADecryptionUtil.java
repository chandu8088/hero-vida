package com.heromotocorp.vida.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;

/**
 * Simple RSA utility to ensure PII data passed as encrypted String from UI are
 * decrypted before it send to downstream system.
 * 
 * @author shissriv
 *
 */
public class RSADecryptionUtil {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(RSADecryptionUtil.class);

	/** The decrypt cipher. */
	Cipher decryptCipher = null;

	/**
	 * Instantiates a new RSA decryption util.
	 *
	 * @param privateKeyContent the private key content
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws InvalidKeySpecException  the invalid key spec exception
	 * @throws IOException              Signals that an I/O exception has occurred.
	 * @throws InvalidKeyException      the invalid key exception
	 * @throws NoSuchPaddingException   the no such padding exception
	 */
	public RSADecryptionUtil(String privateKeyContent) throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException, InvalidKeyException, NoSuchPaddingException {

		String pkcsKey = privateKeyContent;
		pkcsKey = removeHeaderFooter(privateKeyContent);

		byte[] pkcs8EncodedBytes = Base64.getDecoder().decode(pkcsKey);

		// extract the private key

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey privKey = kf.generatePrivate(keySpec);

		decryptCipher = Cipher.getInstance(Constants.CIPHYR_TYPE_RSA_ECB_OAEPWithSHA_1AndMGF1Padding);
		decryptCipher.init(Cipher.DECRYPT_MODE, privKey);

	}

	/**
	 * Decrypt.
	 *
	 * @param encryptedStr the encrypted str
	 * @return the string
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException       the bad padding exception
	 */
	public String decrypt(String encryptedStr) throws IllegalBlockSizeException, BadPaddingException {
		byte[] encryptedEmailBytes = encryptedStr.getBytes(StandardCharsets.UTF_8);

		byte[] decryptedEmailBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedEmailBytes));

		return new String(decryptedEmailBytes, StandardCharsets.UTF_8);

	}

	/**
	 * Removes the header footer.
	 *
	 * @param pkcs8Pem the pkcs 8 pem
	 * @return the string
	 */
	private static String removeHeaderFooter(String pkcs8Pem) {
		pkcs8Pem = pkcs8Pem.replace(Constants.KEY_HEADER_BEGIN_PRIVATE_KEY, StringUtils.EMPTY);
		pkcs8Pem = pkcs8Pem.replace(Constants.KEY_FOOTER_END_PRIVATE_KEY, StringUtils.EMPTY);
		pkcs8Pem = pkcs8Pem.replaceAll(Constants.REGEX_ALL_WHITESPACE, StringUtils.EMPTY);
		return pkcs8Pem;
	}

	/**
	 * Encript params.
	 *
	 * @param resolver the resolver
	 * @return the RSA decryption util
	 */
	public static RSADecryptionUtil encriptParams(ResourceResolver resolver) {

		String privateKeyContentAsString = StringUtils.EMPTY;

		try {
			if (null != resolver) {
				Resource fileResource = resolver.getResource(Constants.KEY_PATH);
				if (null != fileResource) {
					Node fileNode = fileResource.adaptTo(Node.class);

					if (null != fileNode && fileNode.hasProperty(JcrConstants.JCR_DATA)) {
						InputStream content = fileNode.getProperty(JcrConstants.JCR_DATA).getBinary().getStream();
						byte[] bytes = IOUtils.toByteArray(content);
						privateKeyContentAsString = new String(bytes);
					}
				}
			}
		} catch (

				RepositoryException | IOException e) {
			LOG.error("IOException while reading InputStream into String : {}", e.getMessage());

		}

		RSADecryptionUtil util = null;
		try {
			util = new RSADecryptionUtil(privateKeyContentAsString);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
				| IOException e) {
			LOG.error("IOException while reading InputStream into String : {}", e.getMessage());
		}

		return util;
	}

}
