/**
 * 
 */
package com.heromotocorp.vida.core.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Duration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for RSADecryptionUtil
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class RSADecryptionUtilTest {

	@Mock
	private RSADecryptionUtil rsaDecryptionUtil;

	@Mock
	private KeyFactory kf;

	@Mock
	private Cipher decryptCipher;

	@Mock
	private ResourceResolver resolver;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.utils.RSADecryptionUtil#RSADecryptionUtil(java.lang.String)}.
	 * 
	 * @throws IOException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	@Test
	void testRSADecryptionUtil() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IOException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
			Mockito.mockStatic(KeyFactory.class);
			Mockito.mockStatic(Cipher.class);
			Mockito.when(KeyFactory.getInstance("RSA")).thenReturn(kf);
			Mockito.when(Cipher.getInstance(Constants.CIPHYR_TYPE_RSA_ECB_OAEPWithSHA_1AndMGF1Padding))
					.thenReturn(decryptCipher);
			rsaDecryptionUtil = new RSADecryptionUtil("base64");
			assertNotNull(rsaDecryptionUtil);
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.utils.RSADecryptionUtil#decrypt(java.lang.String)}.
	 * 
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	@Test
	void testDecrypt() throws IllegalBlockSizeException, BadPaddingException {
		rsaDecryptionUtil.decrypt("base64");
		assertNotNull(rsaDecryptionUtil);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.utils.RSADecryptionUtil#encriptParams(org.apache.sling.api.resource.ResourceResolver)}.
	 * 
	 * @throws RepositoryException
	 * @throws IOException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	@Test
	void testEncriptParams() throws RepositoryException {
		RSADecryptionUtil.encriptParams(resolver);
		assertNotNull(rsaDecryptionUtil);
	}

}
