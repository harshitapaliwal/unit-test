package com.dellemc.iot.connectivity.provisioning.service.impl;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ElmsServiceImplTest {

	@InjectMocks
	ElmsServiceImpl elmsServiceImplMock;

	@Test(expected = Exception.class)
	public void testGetSerialNumberException() throws Exception {

		String ELMS_SWID_SERVICE_CREDS = "tesuser:Password1";
		when(elmsServiceImplMock.getSerialNumber("SUPPORTASSISTENTERPRISE-APPLIANCE","SAE", "11145366", "1230596", "PRAKAA13", null,
				ELMS_SWID_SERVICE_CREDS)).thenThrow(new Exception("Error occurred in Elms service"));
		elmsServiceImplMock.getSerialNumber("SUPPORTASSISTENTERPRISE-APPLIANCE","SAE", "11145366", "1230596", "PRAKAA13", null,
				null);
	}

	/*
	 * @Test(expected = Exception.class) public void testGetELMSResponseFallback()
	 * throws Exception {
	 * when(elmsServiceImplMock.getELMSResponseFallback(null)).thenThrow(new
	 * PvSServiceInternalException("", "500 Error occurred in Elms service",
	 * HttpStatus.INTERNAL_SERVER_ERROR));
	 * elmsServiceImplMock.getELMSResponseFallback(null); }
	 */

}
