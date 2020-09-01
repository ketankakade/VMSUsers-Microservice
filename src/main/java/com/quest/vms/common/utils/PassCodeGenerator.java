package com.quest.vms.common.utils;

import java.io.Serializable;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.quest.vms.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

/** The type Pass code generator. */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Slf4j
public class PassCodeGenerator implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	private String specialCharacter = "!@#$%^&*()_+";

	/**
	 * Generate secure password string.
	 *
	 * @param password the password
	 * @param nonce    the nonce
	 * @return the string
	 */
	public String generateSecurePassword(String password, String nonce) {
		try {
			byte[] decodeByte = Base64.getDecoder().decode(nonce);
			byte[] oddPositionNonce = new byte[decodeByte.length / 2];

			for (int i = 0; i < oddPositionNonce.length; i++) {
				oddPositionNonce[i] = decodeByte[(i * 2) + 1];
			}
			KeySpec spec = new PBEKeySpec(password.toCharArray(), oddPositionNonce, 1000, 512);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			byte[] bytes = factory.generateSecret(spec).getEncoded();
			// Get complete hashed password in hex format
			return new String(Base64.getEncoder().encode(bytes));
		} catch (Exception e) {
			log.error("Passcode generation failed");
			throw new ServiceException("Passcode generation failed");
		}
	}

	/**
	 * create nonce
	 * 
	 * @return nonce
	 */
	public String createNonce() {
		try {
			String dateTimeString = Long.toString(new Date().getTime());
			byte[] nonceByte = dateTimeString.getBytes();
			return (Base64.getEncoder().encodeToString(nonceByte));
		} catch (Exception e) {
			log.error("nonce generation failed" + e.getMessage());
			throw new ServiceException("nonce generation failed");
		}
	}

	/**
	 * Generate random password string.
	 *
	 * @return the string
	 */
	public String generateRandomPassword() {
		log.info("generate random passcode");
		PasswordGenerator gen = new PasswordGenerator();
		CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
		CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
		lowerCaseRule.setNumberOfCharacters(2);

		CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
		CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
		upperCaseRule.setNumberOfCharacters(2);

		CharacterData digitChars = EnglishCharacterData.Digit;
		CharacterRule digitRule = new CharacterRule(digitChars);
		digitRule.setNumberOfCharacters(2);

		CharacterData specialChars = new CharacterData() {
			public String getErrorCode() {
				return "ERROR_CODE";
			}

			public String getCharacters() {
				return specialCharacter;
			}
		};
		CharacterRule splCharRule = new CharacterRule(specialChars);
		splCharRule.setNumberOfCharacters(2);

		return gen.generatePassword(2 + 2 + 2 + 2, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
	}

	/**
	 * verify the password
	 * 
	 * @param providedPassword provided password
	 * @param securedPassword  password from database
	 * @param nonce            created nonce
	 * @return boolean as per result
	 */
	public boolean verifyUserPassword(String providedPassword, String securedPassword, String nonce) {
		boolean isValidPassword = false;
		// Generate New secure password with the same salt
		String newSecurePassword = generateSecurePassword(providedPassword, nonce);
		// Check if two passwords are equal
		isValidPassword = newSecurePassword.equalsIgnoreCase(securedPassword);
		return isValidPassword;
	}
}
