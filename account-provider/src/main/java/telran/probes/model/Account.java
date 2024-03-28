package telran.probes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Document(collection = "accounts")
@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class Account {
	@Id
	String email;
	String hashPassword;
	String roles[];
}
