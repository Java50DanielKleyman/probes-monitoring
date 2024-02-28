import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.probes.UrlConstants;
import telran.probes.dto.Range;
import telran.probes.service.SensorRangeProviderClientService;

@WebMvcTest
class SensorRangeControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	SensorRangeProviderClientService sensorRangeProviderClientService;
	@Autowired
	ObjectMapper mapper;
	private static final Range range = new Range(100, 200);
	private static final long sensorId = 123l;
	private static final String HOST = "http://localhost:8080/";
	private static final String URL_SENSOR_ID = HOST + UrlConstants.SENSOR;

	@Test
	void getSensorRangeTest() throws Exception {
		when(sensorRangeProviderClientService.getSensorRange(sensorId)).thenReturn(range);
		String sensorJson = mapper.writeValueAsString(sensorId);
		String response = mockMvc.perform(get(URL_SENSOR_ID).contentType(MediaType.APPLICATION_JSON)
				.content(sensorJson)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Range responseRange = mapper.readValue(response, Range.class);
		assertEquals(range, responseRange);
	}

}
