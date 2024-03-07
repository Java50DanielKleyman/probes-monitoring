package telran.probes;

import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.probes.dto.ProbeData;
import telran.probes.service.AvgReducerServiceImpl;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class AvgReducerAppl {
	String producerBindingName = "avgReducerProducer-out-0";
	final AvgReducerServiceImpl avgReducerService;
	final StreamBridge streamBridge;

	public static void main(String[] args) {
		SpringApplication.run(AvgReducerAppl.class, args);

	}

	@Bean
	Consumer<ProbeData> avgReducerConsumer() {
		return probeData -> probeDataAvgReducer(probeData);
	}

	private void probeDataAvgReducer(ProbeData probeData) {
		log.trace("received probe: {}", probeData);		
		Double averageValue = avgReducerService.getAvgValue(probeData);
		if (averageValue != null) {
			long sensorId = probeData.id();
			ProbeData probeDataAvgValue = new ProbeData(sensorId, averageValue, System.currentTimeMillis());
			streamBridge.send(producerBindingName, probeDataAvgValue);
			log.debug("average value {} for sensor {} sent to {}", probeDataAvgValue, sensorId, producerBindingName);
		}

	}

}