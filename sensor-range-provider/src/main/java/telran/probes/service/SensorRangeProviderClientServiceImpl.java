package telran.probes.service;

import org.springframework.stereotype.Service;
import telran.probes.dto.Range;

@Service
public class SensorRangeProviderClientServiceImpl implements SensorRangeProviderClientService {

	private final RangeRepo rangeRepo;

	public SensorRangeProviderClientServiceImpl(RangeRepo rangeRepo) {
		this.rangeRepo = rangeRepo;
	}

	@Override
	public Range getSensorRange(long sensorId) {

		return rangeRepo.findById(sensorId);
	}
}
