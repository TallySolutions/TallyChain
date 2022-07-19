package com.tally.chain.utils;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
import java.util.UUID;

public class ChainUtils {
	
	public static KeyPair genKeyPair(String passPhrase) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDsA", "SC");
	    ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
	    keyGen.initialize(ecSpec, new SecureRandom());
	    return keyGen.generateKeyPair();
	}
	
	public static UUID  id() {
		
		return UUID.randomUUID();
	}
	
	public static String hash(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest sha356 = MessageDigest.getInstance("SHA-256");
		return toHexString(sha356.digest(data));
	}
	
	public Boolean verifySignature() {
		return true;
	}
	
	public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);
 
        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));
 
        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
 
        return hexString.toString();
    }
}
