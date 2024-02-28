package telran.probes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.probes.UrlConstants;
import telran.probes.dto.Range;
import telran.probes.service.SensorRangeProviderClientService;

@RestController
@RequiredArgsConstructor

public class SensorRangeController {
	final SensorRangeProviderClientService sensorRangeProviderClientService;

	@GetMapping(UrlConstants.SENSOR + "{" + UrlConstants.SENSORID + "}")
	Range getSensorRange(@PathVariable(UrlConstants.SENSORID) long sensorId) {
		return sensorRangeProviderClientService.getSensorRange(sensorId);

	}
}