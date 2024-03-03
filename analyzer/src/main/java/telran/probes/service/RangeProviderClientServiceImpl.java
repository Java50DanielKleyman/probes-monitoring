package telran.probes.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.probes.dto.Range;
import telran.probes.dto.SensorUpdateData;

@Service
@RequiredArgsConstructor
@Slf4j
public class RangeProviderClientServiceImpl implements RangeProviderClientService {
	final RestTemplate restTemplate;
	final ServiceConfiguration serviceConfiguration;
	private final Map<Long, Range> mapRanges = new HashMap<>();
	private static final Range RANGE_DEFAULT = new Range(MIN_DEFAULT_VALUE, MAX_DEFAULT_VALUE);

	@Override
	public Range getRange(long sensorId) {
		Range range = mapRanges.get(sensorId);
		if (range == null) {
			range = serviceRequest(sensorId);
			if (range != RANGE_DEFAULT) {
				mapRanges.put(sensorId, range);
			}

		}

		return range;
	}

	private Range serviceRequest(long sensorId) {
		Range range = null;
		ResponseEntity<?> responseEntity;
		try {
			responseEntity = restTemplate.exchange(getUrl(sensorId), HttpMethod.GET, null, Range.class);
			if (responseEntity.getStatusCode().is4xxClientError() || responseEntity.getStatusCode().is5xxServerError()) {
				throw new Exception(responseEntity.getBody().toString());
			}
			range = (Range) responseEntity.getBody();
			log.debug("range value {}", range);
		} catch (Exception e) {
			log.error("error at service request: {}", e.getMessage());
			range = RANGE_DEFAULT;
			log.warn("default range value: {}", range);
		}
		return range;

	}

	private String getUrl(long sensorId) {
		String url = String.format("http://%s:%d%s%d", serviceConfiguration.getHost(), serviceConfiguration.getPort(),
				serviceConfiguration.getPath(), sensorId);
		log.debug("url created is {}", url);
		return url;
	}

	
	public void updateSensorData(SensorUpdateData sensorUpdateData) {
		if (sensorUpdateData.range() != null) {
			mapRanges.replace(sensorUpdateData.id(), sensorUpdateData.range());
		}
	}

}