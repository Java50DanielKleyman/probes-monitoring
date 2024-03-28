package telran.probes.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.exceptions.NotFoundException;
import telran.probes.dto.AccountDto;
import telran.probes.model.Account;
import telran.probes.repo.AccountProviderRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountProviderServiceImpl implements AccountProviderService {
	final AccountProviderRepo accountProviderRepo;

	@Override
	public AccountDto getAccount(String email) {
		Account account = accountProviderRepo.findById(email)
				.orElseThrow(() -> new NotFoundException(String.format("account with email %s not found", email)));
		log.debug("account {} found in DB", account);
		return new AccountDto(account.getEmail(), account.getHashPassword(), account.getRoles());
	}

}
