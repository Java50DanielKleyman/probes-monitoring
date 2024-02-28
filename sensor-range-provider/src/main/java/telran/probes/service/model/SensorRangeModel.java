package telran.probes.service.model;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;
import lombok.NoArgsConstructor;
import telran.probes.dto.Range;
import telran.probes.dto.SensorRange;

@Document(collection = "sensors_ranges")
@Getter
@NoArgsConstructor
public class SensorRangeModel {
	@Id
	private long id;
	private Range range;

	public SensorRangeModel(SensorRange sensorRange) {
		id = sensorRange.id();
		range = sensorRange.range();
	}

	public SensorRange build() {
		return new SensorRange(id, range);
	}
}
