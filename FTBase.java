package com.dellemc.iot.connectivity.provisioning;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import io.restassured.RestAssured;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { FunctionalTestApp.class, ConnectivityProvisioningApplication.class })
@ActiveProfiles({ "test" })
@Slf4j
@Getter
@Setter
public class FTBase {

	private static final String ELMS_PATH = "/srs/generate/swid";

	private static Map<String, String> FILENAME_DATA_MAP = new HashMap<>();

	@Value("${local.server.port}")
	private int serverPort = 1690;
	private String mockFileName;
	private String deviceKeyFileName;
	private String responseFileName;
	private String digitalTwinFileName;
	private String serviceLinkFileName;
	private int respStatus = 200;
	private static MockWebServer mockWebServer = new MockWebServer();

	static {
		try {
			// Integer availabePort = SocketUtils.findAvailableTcpPort();
			Integer availabePort = 1690;
			mockWebServer.start(availabePort);

		} catch (Exception e) {
			log.error("Error while starting the mock server" + e.getMessage(), e);
			throw new RuntimeException("Error while starting the mock server", e);
		}
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		RestAssured.port = serverPort;

		final Dispatcher dispatcher = new Dispatcher() {

			@Override
			public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
				if (request.getPath().contains(ELMS_PATH)) {
					return new MockResponse().setBody(readFileToString(mockFileName)).addHeader("Content-Type", "application/json");
				}else if(request.getPath().contains("/device/key")) {
					return new MockResponse().setBody(readFileToString(deviceKeyFileName)).addHeader("Content-Type", "application/json").setResponseCode(respStatus);
				}else if(request.getPath().contains("/digitaltwin/request")) {
					return new MockResponse().setBody(readFileToString(digitalTwinFileName)).addHeader("Content-Type", "application/json").setResponseCode(respStatus);
				}else if(request.getPath().contains("/servicelinkentry")) {
					return new MockResponse().setBody(readFileToString(serviceLinkFileName)).addHeader("Content-Type", "application/json").setResponseCode(respStatus);
				}
				return new MockResponse().setBody(readFileToString(responseFileName))
						.addHeader("Content-Type", "application/json").setResponseCode(respStatus);
			}
		};
		mockWebServer.setDispatcher(dispatcher);
	}

	/**
	 * Read file to string.
	 *
	 * @param filePath
	 * @return file content
	 */
	protected static String readFileToString(final String filePath) {

		if (FILENAME_DATA_MAP.get(filePath) != null) {
			return FILENAME_DATA_MAP.get(filePath);
		}

		try (BufferedReader br = new BufferedReader(
				new FileReader(Thread.currentThread().getContextClassLoader().getResource(filePath).getFile()))) {

			String fileData = br.lines().collect(Collectors.joining());
			FILENAME_DATA_MAP.put(filePath, fileData);

			return fileData;
		} catch (IOException e) {
			log.error("Error while reading the testcase file " + filePath + "Error is " + e.getMessage(), e);
			throw new RuntimeException("Error while reading the testcase file ", e);
		}
	}

}
