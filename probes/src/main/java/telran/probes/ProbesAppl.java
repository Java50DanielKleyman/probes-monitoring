package telran.probes;

import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.probes.dto.ProbeData;
import telran.probes.service.ProbesService;

@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class ProbesAppl {
	private final ProbesService probesService;

	public static void main(String[] args) {
		SpringApplication.run(ProbesAppl.class, args);

	}

	@Bean
	@Scheduled(fixedRate = 1000)
	Supplier<ProbeData> probesSupplier() {
		return this::probeGeneration;
	}

	ProbeData probeGeneration() {
		return getProbeData();
	}

	private ProbeData getProbeData() {
		ProbeData probeData = probesService.getRandomProbeData();
		 log.info("Generated ProbeData: {}", probeData);
		return probeData;
	}

}
