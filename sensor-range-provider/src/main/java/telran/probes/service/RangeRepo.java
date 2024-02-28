package telran.probes.service;

import telran.probes.dto.SensorRange;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface RangeRepo extends MongoRepository<SensorRange, Long>{
	SensorRange findById(long id);
}
