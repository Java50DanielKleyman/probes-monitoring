package telran.probes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;

import telran.probes.service.AvgReducerService;

class AvgReducerControllerTest {
	@MockBean
	AvgReducerService avgReducerService;
	@Autowired
	InputDestination producer;
	@Autowired
	OutputDestination consumer;
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
