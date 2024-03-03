package telran.probes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import telran.probes.service.EmailsProviderClientService;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmailNotifierServiceTest {
	private static final long SENSOR_ID = 123;
	private static final String DEFAULT_EMAIL1 = "email1@gmail.com";
	private static final String DEFAULT_EMAIL2 = "email2@gmail.com";
	private static final String EMAIL3 = "email3@gmail.com";
	private static final String EMAIL4 = "email4@gmail.com";
	private static final String[] DEFAULT_EMAILS = { DEFAULT_EMAIL1, DEFAULT_EMAIL2 };
	private static final String[] EMAILS = { EMAIL3, EMAIL4 };
	private static final String URL = "http://localhost:8282/range/emails/";
	private static final String SENSOR_NOT_FOUND_MESSAGE = "sensor not found";
	private static final long SENSOR_ID_NOT_FOUND = 124;
	private static final long SENSOR_ID_UNAVAILABLE = 170;

	@Autowired
	EmailsProviderClientService providerService;
	@MockBean

	RestTemplate restTemplate;

	@Test
	@Order(1)
	void normalFlowNoCache() {
		ResponseEntity<String[]> responseEntity = new ResponseEntity<>(EMAILS, HttpStatus.OK);
		when(restTemplate.exchange(getUrl(SENSOR_ID), HttpMethod.GET, null, String[].class)).thenReturn(responseEntity);
		assertEquals(EMAILS, providerService.getMails(SENSOR_ID));
	}

//	@SuppressWarnings("unchecked")
//	@Test
//	@Order(2)
//	void normalFlowWithCache() {
//		when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(Class.class)))
//		.thenAnswer(new Answer<ResponseEntity<?>>() {
//			@Override
//			public ResponseEntity<?> answer(InvocationOnMock invocation) throws Throwable {
//				fail("method exchange should not be called");
//				return null;
//			}
//		});
//		assertEquals(EMAILS, providerService.getMails(SENSOR_ID));
//	}
//
//	@Test
//	@Order(3)
//	void sensorNotFoundTest() {
//		ResponseEntity<String> responseEntity = new ResponseEntity<>(SENSOR_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
//		when(restTemplate.exchange(getUrl(SENSOR_ID_NOT_FOUND), HttpMethod.GET, null, String.class))
//				.thenReturn(responseEntity);
//		assertEquals(DEFAULT_EMAILS, providerService.getMails(SENSOR_ID_NOT_FOUND));
//
//	}
//
//	@Test
//	@Order(4)
//	void defaultRangeNotInCache() {
//		ResponseEntity<String[]> responseEntity = new ResponseEntity<>(EMAILS, HttpStatus.OK);
//		when(restTemplate.exchange(getUrl(SENSOR_ID_NOT_FOUND), HttpMethod.GET, null, String[].class))
//				.thenReturn(responseEntity);
//		assertEquals(EMAILS, providerService.getMails(SENSOR_ID_NOT_FOUND));
//	}
//
	private String getUrl(long sensorId) {

		return URL + sensorId;
	}
//
//	@SuppressWarnings("unchecked")
//	@Test
//	void remoteWebServiceUnavailable() {
//		when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(Class.class)))
//		.thenThrow(new RestClientException("Internal Server Error"));
//		assertEquals(DEFAULT_EMAILS, providerService.getMails(SENSOR_ID_UNAVAILABLE));
//	}
}
