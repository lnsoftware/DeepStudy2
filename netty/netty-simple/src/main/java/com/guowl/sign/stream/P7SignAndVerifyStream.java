package com.guowl.sign.stream;

import java.util.HashMap;
import java.util.Map;

import com.guowl.sign.http.ServerUrl;
import com.guowl.utils.BytesUtils;

public class P7SignAndVerifyStream {
	public static void main(String[] args) throws Exception {
		// p7VerifyDetach();
		// p7VerifyAtach();
		p7SignAtach();
		// p7SignDetach();
	}

	// p7 验签 detach
	private static void p7VerifyDetach() {
		try {
			String signData = "MIIDjgYJKoZIhvcNAQcCoIIDfzCCA3sCAQExCzAJBgUrDgMCGgUAMAsGCSqGSIb3DQEHAaCCAnowggJ2MIIB36ADAgECAghFNQIwXoBHeTANBgkqhkiG9w0BAQUFADAsMQ8wDQYDVQQDEwZEZW1vQ0ExDDAKBgNVBAoTA0pJVDELMAkGA1UEBhMCQ04wHhcNMDUwMjIyMDkyNDU5WhcNMTUwMjIwMDkyNDU5WjBOMQ8wDQYDVQQDEwZub3JtYWwxIDAeBgkqhkiG9w0BCQEWEW5vcm1hbEBqaXQuY29tLmNuMQwwCgYDVQQKEwNqaXQxCzAJBgNVBAYTAmNuMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDd1AtiWh812bNP8pdQT2as0F1RDdf+ySvnASKY5D/E4Z54kR7h1Gr0x/LG4BHlceSMWaMu6mXMv9n1odyAfRhs98bNoAsYpkVKbybxJ/HVIbftcCF5GHlbRToAA1xeaiQXqF5zssZsRGcSGp2IW0UZyjPzo0DVkUD3L0VJgffjdQIDAQABo38wfTAfBgNVHSMEGDAWgBR2xdV7lc4wmeKx34ccYaaSziOs2TAuBgNVHR8EJzAlMCOgIaAfhh1odHRwOi8vMTkyLjE2OC45LjE0MC9jcmwxLmNybDALBgNVHQ8EBAMCBPAwHQYDVR0OBBYEFCODiz818Na/L2/HG2dp2b3Ksx0/MA0GCSqGSIb3DQEBBQUAA4GBABWaO3T2iFXKczvap5PRlLID4vwAZc/PCgXycqf34CoEamKN/LKFpcuoRV9eRUgOMnt2Cpx7hr1NOEhoPEITklSAOhL2DN9nnDshfT3fVBceGS+aXZ1hNi7Fcw6uVsHaIiHaX4UJlRCh5vUiSCNReS1RjCael/Xu5MDbpvS1OFd4MYHdMIHaAgEBMDgwLDEPMA0GA1UEAxMGRGVtb0NBMQwwCgYDVQQKEwNKSVQxCzAJBgNVBAYTAkNOAghFNQIwXoBHeTAJBgUrDgMCGgUAMA0GCSqGSIb3DQEBAQUABIGAMDHQTnqlOdnrohGw7KVZLwmmGLCZezop/bD2FrUfA0Xz+Jdwe3XV6i1VYi7zF84ahnoiegIvm6EqMCl+mjSAp7rsVL7i6j87WgmPeE2bBhGFloMajQHWzKyALb35fnMNT9OWsIHH14lFJ7mTOAAwhhmazwkJjy0jBA3hm/X3m+E=";
			String plainData = "YWJj";

			Map<String, String> params = new HashMap<String, String>();
			params.put("DSDigestALG", "SHA1");
			params.put("messageType", "http");
			params.put("businessType", "P7Verify");
			params.put("Data_Format", "DSignData=" + signData.length() + ",PlainData=" + plainData.length());

			HttpStreamClient client = new HttpStreamClient();
			// client.connect(ServerUrl.URI, signData + plainData, params);
		} catch (Exception e) {}
	}

	// p7 验签 atach
	private static void p7VerifyAtach() {
		try {
			String signData = "MIIDlQYJKoZIhvcNAQcCoIIDhjCCA4ICAQExCzAJBgUrDgMCGgUAMBIGCSqGSIb3DQEHAaAFBANhYmOgggJ6MIICdjCCAd+gAwIBAgIIRTUCMF6AR3kwDQYJKoZIhvcNAQEFBQAwLDEPMA0GA1UEAxMGRGVtb0NBMQwwCgYDVQQKEwNKSVQxCzAJBgNVBAYTAkNOMB4XDTA1MDIyMjA5MjQ1OVoXDTE1MDIyMDA5MjQ1OVowTjEPMA0GA1UEAxMGbm9ybWFsMSAwHgYJKoZIhvcNAQkBFhFub3JtYWxAaml0LmNvbS5jbjEMMAoGA1UEChMDaml0MQswCQYDVQQGEwJjbjCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA3dQLYlofNdmzT/KXUE9mrNBdUQ3X/skr5wEimOQ/xOGeeJEe4dRq9MfyxuAR5XHkjFmjLuplzL/Z9aHcgH0YbPfGzaALGKZFSm8m8Sfx1SG37XAheRh5W0U6AANcXmokF6hec7LGbERnEhqdiFtFGcoz86NA1ZFA9y9FSYH343UCAwEAAaN/MH0wHwYDVR0jBBgwFoAUdsXVe5XOMJnisd+HHGGmks4jrNkwLgYDVR0fBCcwJTAjoCGgH4YdaHR0cDovLzE5Mi4xNjguOS4xNDAvY3JsMS5jcmwwCwYDVR0PBAQDAgTwMB0GA1UdDgQWBBQjg4s/NfDWvy9vxxtnadm9yrMdPzANBgkqhkiG9w0BAQUFAAOBgQAVmjt09ohVynM72qeT0ZSyA+L8AGXPzwoF8nKn9+AqBGpijfyyhaXLqEVfXkVIDjJ7dgqce4a9TThIaDxCE5JUgDoS9gzfZ5w7IX0931QXHhkvml2dYTYuxXMOrlbB2iIh2l+FCZUQoeb1IkgjUXktUYwmnpf17uTA26b0tThXeDGB3TCB2gIBATA4MCwxDzANBgNVBAMTBkRlbW9DQTEMMAoGA1UEChMDSklUMQswCQYDVQQGEwJDTgIIRTUCMF6AR3kwCQYFKw4DAhoFADANBgkqhkiG9w0BAQEFAASBgDAx0E56pTnZ66IRsOylWS8JphiwmXs6Kf2w9ha1HwNF8/iXcHt11eotVWIu8xfOGoZ6InoCL5uhKjApfpo0gKe67FS+4uo/O1oJj3hNmwYRhZaDGo0B1sysgC29+X5zDU/TlrCBx9eJRSe5kzgAMIYZms8JCY8tIwQN4Zv195vh";
			Map<String, String> params = new HashMap<String, String>();
			params.put("DSDigestALG", "SHA1");
			params.put("messageType", "http");
			params.put("businessType", "P7Verify");
			params.put("DSignMode", "3");
			params.put("Data_Format", "DSignData=" + signData.length());

			HttpStreamClient client = new HttpStreamClient();
			// client.connect(ServerUrl.URI, signData, params);
		} catch (Exception e) {}
	}

	// p7 签 atach
	private static void p7SignAtach() {
		try {
			int length = 1024 * 1024 * 10 ;
			Map<String, String> params = new HashMap<String, String>();
			params.put("certid", "rsa");
			params.put("messageType", "http");
			params.put("businessType", "attachsign");
			params.put("digestalg", "SHA1");
			params.put("version", "1.0.1");

			HttpStreamClient client = new HttpStreamClient();
			client.connect(ServerUrl.URI, BytesUtils.getBytes(length), params);
		} catch (Exception e) {}
	}

	// p7 签 detach
	private static void p7SignDetach() {
		try {
			String plainData = "YWJj";

			Map<String, String> params = new HashMap<String, String>();
			params.put("ApplyID", "rsa");
			params.put("messageType", "http");
			params.put("businessType", "P7Sign");
			params.put("DSDigestALG", "SHA1");
			params.put("DSignMode", "1");
			params.put("Data_Format", "PlainData=" + plainData.length());

			HttpStreamClient client = new HttpStreamClient();
			// client.connect(ServerUrl.URI, plainData, params);
		} catch (Exception e) {}
	}

}
