package telran.probes.service;

import telran.probes.dto.Range;

public interface SensorRangeProviderClientService {
	Range getSensorRange(long sensorId);
}
