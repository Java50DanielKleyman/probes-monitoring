package telran.probes.service;

import java.util.List;
import java.util.Map;
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
		return null;
	}

}
