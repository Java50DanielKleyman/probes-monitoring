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

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailsProviderClientServiceImpl implements EmailsProviderClientService {
	final RestTemplate restTemplate;
	final ServiceConfiguration serviceConfiguration;
	private final Map<Long, String[]> mapEmails = new HashMap<>();

	@Override
	public String[] getMails(long sensorId) {
		String[] emails = mapEmails.get(sensorId);
		if (emails == null) {
			emails = serviceRequest(sensorId);
			if (emails != DEFAULT_EMAILS) {
				mapEmails.put(sensorId, emails);
			}
		}
		return emails;
	}

	private String[] serviceRequest(long sensorId) {
		String[] emails = null;
		ResponseEntity<?> responseEntity;
		try {
			responseEntity = restTemplate.exchange(getUrl(sensorId), HttpMethod.GET, null, Range.class);
			if (responseEntity.getStatusCode().is4xxClientError()
					|| responseEntity.getStatusCode().is5xxServerError()) {
				throw new Exception(responseEntity.getBody().toString());
			}
			emails = (String[]) responseEntity.getBody();
			log.debug("emails {}", (Object[]) emails);
		} catch (Exception e) {
			log.error("error at service request: {}", e.getMessage());
			emails = DEFAULT_EMAILS;
			log.warn("default emails: {}", (Object[]) emails);
		}
		return emails;
	}

	private String getUrl(long sensorId) {
		String url = String.format("http://%s:%d%s%d", serviceConfiguration.getHost(), serviceConfiguration.getPort(),
				serviceConfiguration.getPath(), sensorId);
		log.debug("url created is {}", url);
		return url;
	}
}
