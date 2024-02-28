package telran.probes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.RequiredArgsConstructor;


@SpringBootApplication
@RequiredArgsConstructor
public class SensorRangeProviderAppl {
    
    public static void main(String[] args) {
        SpringApplication.run(SensorRangeProviderAppl.class, args);
    }
}
