package pl.vgtworld.resourceobserver.timers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class HashUtil {

	private HashUtil() {
	}

	static String generateHash(byte[] input) throws HashException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.reset();
			md.update(input);
			byte[] digest = md.digest();

			StringBuilder result = new StringBuilder();
			for (byte element : digest) {
				result.append(Integer.toString((element & 0xFF) + 0x100, 16).substring(1));
			}
			return result.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new HashException(e);
		}
	}

}
