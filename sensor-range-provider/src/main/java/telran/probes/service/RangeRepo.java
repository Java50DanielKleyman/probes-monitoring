package telran.probes.service;

import telran.probes.dto.Range;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface RangeRepo extends MongoRepository<Range,Long>{

	Range findById(long id);

}
