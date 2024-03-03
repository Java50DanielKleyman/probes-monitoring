package telran.probes.service;

public interface EmailsProviderClientService {
	String[] DEFAULT_EMAILS = { "email1@gmail.com", "email2.gmail.com" };

	String[] getMails(long sensorId);
}
