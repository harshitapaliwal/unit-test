package com.dellemc.iot.connectivity.provisioning.service.impl;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CertificateServiceImplTest {

	@InjectMocks
	CertificateServiceImpl certificateServiceImplMock;

	@Test(expected = Exception.class)
	public void testUnmarshellCertificateResponseJAXBException() throws Exception {
		when(certificateServiceImplMock.unmarshellCertificateResponse("ABCDEF"))
				.thenThrow(new Exception("Internal Server Error"));
	}

	@Test(expected = Exception.class)
	public void testUnmarshellCertificateResponsePvsException() throws Exception {

		certificateServiceImplMock.unmarshellCertificateResponse(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + "<certificateResponse>\r\n"
						+ "    <status>1</status>\r\n" + "    <content><![CDATA[-----BEGIN CERTIFICATE-----\r\n"
						+ "MIIFgjCCBGqgAwIBAgITSwAAi/tgpjd24sydDAAAAACL+zANBgkqhkiG9w0BAQsF\r\n"
						+ "ADCBrzELMAkGA1UEBhMCVVMxDjAMBgNVBAgTBVRleGFzMRMwEQYDVQQHEwpSb3Vu\r\n"
						+ "ZCBSb2NrMR8wHQYDVQQKExZEZWxsIFRlY2hub2xvZ2llcyBJbmMuMSQwIgYDVQQL\r\n"
						+ "ExtEZWxsIFNlY3VyZSBSZW1vdGUgU2VydmljZXMxNDAyBgNVBAMTK0RlbGwgU2Vj\r\n"
						+ "dXJlIFJlbW90ZSBTZXJ2aWNlcyBUZXN0IElzc3VpbmcgQ0EwHhcNMjAwMjI1MDkz\r\n"
						+ "NTAwWhcNNDUwMjE4MDkzNTAwWjCBqTELMAkGA1UEBhMCVVMxDjAMBgNVBAgTBVRl\r\n"
						+ "eGFzMRMwEQYDVQQHEwpSb3VuZCBSb2NrMR8wHQYDVQQKExZEZWxsIFRlY2hub2xv\r\n"
						+ "Z2llcyBJbmMuMSQwIgYDVQQLExtEZWxsIFNlY3VyZSBSZW1vdGUgU2VydmljZXMx\r\n"
						+ "LjAsBgNVBAMMJVNFUlZJQ0UgUExBTk5JTkcgLSBTVlRfRUxNU0FFMDEyMFdYWTkw\r\n"
						+ "ggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDGEHmp8MDUQ2vaCtHzXWdW\r\n"
						+ "Z+weOjAOIul79xDnPjBC8M8EI5HCGBIrqWMJLKJ+B42DYLJ9Ohy9sA4h9FbSP6y6\r\n"
						+ "1fj7+/MNJMOORgI3nCVO6J4V3zMZBsmiN3Qm9YV3a9iituVthkFLkqTWGzxOcm9t\r\n"
						+ "qaekluRZgTlAeY7l0lA8gyxV7XXLc6ah0UbZQroKCwpmhEKN1fwYSzg+dmiZr7Kz\r\n"
						+ "roVjF/RDR/fdbR1f8BwH7JriicqXVOWxYxUSIjeFT1SQr6RxLuzExFXgYllfDCQy\r\n"
						+ "zpWF/JjtST1HXWjxfRlNGOcGPQzhnzDGsAEEkPWka3VlObWpCmkhYR6JINSWe0dh\r\n"
						+ "AgMBAAGjggGZMIIBlTA9BgkrBgEEAYI3FQcEMDAuBiYrBgEEAYI3FQiHuNlsgYT4\r\n"
						+ "CIa9jQ+F84A1grOZWjOGk70ygv/VYAIBZAIBIzATBgNVHSUEDDAKBggrBgEFBQcD\r\n"
						+ "AjALBgNVHQ8EBAMCBaAwGwYJKwYBBAGCNxUKBA4wDDAKBggrBgEFBQcDAjAdBgNV\r\n"
						+ "HQ4EFgQUqRI11QrAX38RNjn3F3P624zCdZ4wHwYDVR0jBBgwFoAUkunYFeME7J8X\r\n"
						+ "XgO8zUTK1fvzliAwZAYDVR0fBF0wWzBZoFegVYZTaHR0cDovL2VzcnMtY3JsLmVt\r\n"
						+ "Yy5jb20vRGVsbCUyMFNlY3VyZSUyMFJlbW90ZSUyMFNlcnZpY2VzJTIwVGVzdCUy\r\n"
						+ "MElzc3VpbmclMjBDQS5jcmwwbwYIKwYBBQUHAQEEYzBhMF8GCCsGAQUFBzAChlNo\r\n"
						+ "dHRwOi8vZXNycy1jcmwuZW1jLmNvbS9EZWxsJTIwU2VjdXJlJTIwUmVtb3RlJTIw\r\n"
						+ "U2VydmljZXMlMjBUZXN0JTIwSXNzdWluZyUyMENBLmNydDANBgkqhkiG9w0BAQsF\r\n"
						+ "AAOCAQEAbfgpN4w7bghkl+bTOq7ivSCYdmEhxjXhvx+hZQHg6sP4hWKnCeZ4NMcu\r\n"
						+ "RcQ0v5vOFU+tTCRsXQe2C3xqNlYonS6OGCAX2NcJ4xsiEqfTCM7rA9QoYlv1gyZo\r\n"
						+ "bOVKiYOdz9Qcrh+kXZAycn9mpIkG+0tHf3legCXvqvZrg5jthQ6l+BpSvGoNC1cZ\r\n"
						+ "VA7yuV7EU7EOO6bEuzVcdaLqQaO6BFm+OCjRowM63KMoRYVOyB4TuUbel73nH2T6\r\n"
						+ "ZjuQCz9ytfwBYE7FpYyNMt+2WTnaZD2x3PLhUwHdA0HaJB4PqxlYc0H+KolNe3my\r\n"
						+ "TYrXzc1QMAaOx9N4ChMXpI3LJp4GPg==\r\n" + "-----END CERTIFICATE-----\r\n" + "]]></content>\r\n"
						+ "</certificateResponse>\r\n" + "");
	}

	@Test(expected = Exception.class)
	public void testUnmarshellCertificateResponseException() throws Exception {
		certificateServiceImplMock.unmarshellCertificateResponse(null);
	}

	@Test(expected = Exception.class)
	public void testJAXBException() throws Exception {

		certificateServiceImplMock.unmarshellCertificateResponse(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + "<certificateResponse>\r\n"
						+ "    <status>1</status\r\n" + "    <content><![CDATA[-----BEGIN CERTIFICATE-----\r\n"
						+ "MIIFgjCCBGqgAwIBAgITSwAAi/tgpjd24sydDAAAAACL+zANBgkqhkiG9w0BAQsF\r\n"
						+ "ADCBrzELMAkGA1UEBhMCVVMxDjAMBgNVBAgTBVRleGFzMRMwEQYDVQQHEwpSb3Vu\r\n"
						+ "ZCBSb2NrMR8wHQYDVQQKExZEZWxsIFRlY2hub2xvZ2llcyBJbmMuMSQwIgYDVQQL\r\n"
						+ "ExtEZWxsIFNlY3VyZSBSZW1vdGUgU2VydmljZXMxNDAyBgNVBAMTK0RlbGwgU2Vj\r\n"
						+ "dXJlIFJlbW90ZSBTZXJ2aWNlcyBUZXN0IElzc3VpbmcgQ0EwHhcNMjAwMjI1MDkz\r\n"
						+ "NTAwWhcNNDUwMjE4MDkzNTAwWjCBqTELMAkGA1UEBhMCVVMxDjAMBgNVBAgTBVRl\r\n"
						+ "eGFzMRMwEQYDVQQHEwpSb3VuZCBSb2NrMR8wHQYDVQQKExZEZWxsIFRlY2hub2xv\r\n"
						+ "Z2llcyBJbmMuMSQwIgYDVQQLExtEZWxsIFNlY3VyZSBSZW1vdGUgU2VydmljZXMx\r\n"
						+ "LjAsBgNVBAMMJVNFUlZJQ0UgUExBTk5JTkcgLSBTVlRfRUxNU0FFMDEyMFdYWTkw\r\n"
						+ "ggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDGEHmp8MDUQ2vaCtHzXWdW\r\n"
						+ "Z+weOjAOIul79xDnPjBC8M8EI5HCGBIrqWMJLKJ+B42DYLJ9Ohy9sA4h9FbSP6y6\r\n"
						+ "1fj7+/MNJMOORgI3nCVO6J4V3zMZBsmiN3Qm9YV3a9iituVthkFLkqTWGzxOcm9t\r\n"
						+ "qaekluRZgTlAeY7l0lA8gyxV7XXLc6ah0UbZQroKCwpmhEKN1fwYSzg+dmiZr7Kz\r\n"
						+ "roVjF/RDR/fdbR1f8BwH7JriicqXVOWxYxUSIjeFT1SQr6RxLuzExFXgYllfDCQy\r\n"
						+ "zpWF/JjtST1HXWjxfRlNGOcGPQzhnzDGsAEEkPWka3VlObWpCmkhYR6JINSWe0dh\r\n"
						+ "AgMBAAGjggGZMIIBlTA9BgkrBgEEAYI3FQcEMDAuBiYrBgEEAYI3FQiHuNlsgYT4\r\n"
						+ "CIa9jQ+F84A1grOZWjOGk70ygv/VYAIBZAIBIzATBgNVHSUEDDAKBggrBgEFBQcD\r\n"
						+ "AjALBgNVHQ8EBAMCBaAwGwYJKwYBBAGCNxUKBA4wDDAKBggrBgEFBQcDAjAdBgNV\r\n"
						+ "HQ4EFgQUqRI11QrAX38RNjn3F3P624zCdZ4wHwYDVR0jBBgwFoAUkunYFeME7J8X\r\n"
						+ "XgO8zUTK1fvzliAwZAYDVR0fBF0wWzBZoFegVYZTaHR0cDovL2VzcnMtY3JsLmVt\r\n"
						+ "Yy5jb20vRGVsbCUyMFNlY3VyZSUyMFJlbW90ZSUyMFNlcnZpY2VzJTIwVGVzdCUy\r\n"
						+ "MElzc3VpbmclMjBDQS5jcmwwbwYIKwYBBQUHAQEEYzBhMF8GCCsGAQUFBzAChlNo\r\n"
						+ "dHRwOi8vZXNycy1jcmwuZW1jLmNvbS9EZWxsJTIwU2VjdXJlJTIwUmVtb3RlJTIw\r\n"
						+ "U2VydmljZXMlMjBUZXN0JTIwSXNzdWluZyUyMENBLmNydDANBgkqhkiG9w0BAQsF\r\n"
						+ "AAOCAQEAbfgpN4w7bghkl+bTOq7ivSCYdmEhxjXhvx+hZQHg6sP4hWKnCeZ4NMcu\r\n"
						+ "RcQ0v5vOFU+tTCRsXQe2C3xqNlYonS6OGCAX2NcJ4xsiEqfTCM7rA9QoYlv1gyZo\r\n"
						+ "bOVKiYOdz9Qcrh+kXZAycn9mpIkG+0tHf3legCXvqvZrg5jthQ6l+BpSvGoNC1cZ\r\n"
						+ "VA7yuV7EU7EOO6bEuzVcdaLqQaO6BFm+OCjRowM63KMoRYVOyB4TuUbel73nH2T6\r\n"
						+ "ZjuQCz9ytfwBYE7FpYyNMt+2WTnaZD2x3PLhUwHdA0HaJB4PqxlYc0H+KolNe3my\r\n"
						+ "TYrXzc1QMAaOx9N4ChMXpI3LJp4GPg==\r\n" + "-----END CERTIFICATE-----\r\n" + "]]></content>\r\n"
						+ "</certificateResponse>\r\n" + "");
	}
}
