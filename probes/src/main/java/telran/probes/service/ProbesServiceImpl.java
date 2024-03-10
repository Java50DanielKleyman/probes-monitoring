package telran.probes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.probes.dto.*;
import telran.probes.repo.RangeRepo;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProbesServiceImpl implements ProbesService {
	final RangeRepo rangeRepo;

	@Override
	public ProbeData getRandomProbeData() {
		List<SensorRange> listOfRanges = rangeRepo.findAll();
		Map<Long, Range> rangeMap = listOfRanges.stream()
				.collect(Collectors.toMap(SensorRange::id, SensorRange::range));
		long randomSensorId = getRandomId(rangeMap);
		Range range = rangeMap.get(randomSensorId);
		double randomValue = generateValue(range);
		long timestamp = System.currentTimeMillis();
		log.debug("Generated ProbeData with sensor ID {}, value {}, and timestamp {}", randomSensorId, randomValue,
				timestamp);
		return new ProbeData(randomSensorId, randomValue, timestamp);
	}

	private double generateValue(Range range) {
		double probability = Math.random();
		double deviationProbability = 0.3;
		double minValue = range.minValue();
		double maxValue = range.maxValue();
		double rangeWidth = maxValue - minValue;
		if (probability < deviationProbability) {
			double newValue = generateValueOutsideRange(minValue, maxValue, rangeWidth);
			log.debug("Generated value outside range: {}", newValue);
			return newValue;
		} else {
			double newValue = minValue + Math.random() * rangeWidth;
			log.debug("Generated value within range: {}", newValue);
			return newValue;
		}
	}

	private double generateValueOutsideRange(double minValue, double maxValue, double rangeWidth) {
		double newValue = 0;
		if (Math.random() < 0.5) {
			newValue = minValue - Math.random() * rangeWidth;
		} else {
			newValue = maxValue + Math.random() * rangeWidth;
		}
		return newValue;
	}

	private long getRandomId(Map<Long, Range> rangeMap) {
		Random random = new Random();
		List<Long> idList = new ArrayList<>(rangeMap.keySet());
		Long randomId = idList.get(random.nextInt(idList.size()));
		log.debug("Random ID: {}", randomId);
		return randomId;
	}

}
