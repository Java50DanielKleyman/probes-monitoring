package telran.probes;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import telran.probes.dto.Range;
import telran.probes.dto.SensorRange;
import telran.probes.service.RangeRepo;

@Component
@RequiredArgsConstructor
public class TestDb {
	final RangeRepo rangeRepo;
	static final Range RANGE = new Range(100, 200);
	static final long SENSOR_ID = 123l;	

	void createDb() {
		rangeRepo.deleteAll();
		SensorRange sensorRange = new SensorRange(SENSOR_ID, RANGE);
		rangeRepo.save(sensorRange);
	}
}
