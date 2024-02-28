import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import telran.probes.service.RangeRepo;
import telran.probes.service.SensorRangeProviderClientService;
import telran.probes.dto.Range;

@SpringBootTest
class SensorRangeProviderClientServiceTest {
	@Autowired
	SensorRangeProviderClientService sensorRangeProviderClientService;
	@Autowired
	RangeRepo rangeRepo;
	@Autowired
	TestDb testDb;
	static final long SENSOR_ID = 123l;
	static final Range RANGE = new Range(100, 200);

	@BeforeEach
	void setUp() {
		testDb.createDb();
	}

	@Test
	void test() {
		Range res = sensorRangeProviderClientService.getSensorRange(SENSOR_ID);
		assertEquals(RANGE, res);
	}

}
