package example.service.impl;

import example.model.entity.VerificationToken;
import example.repository.VerificationTokenRepository;
import example.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenImpl implements VerificationTokenService {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public void delete(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }

    @Override
    public void save(VerificationToken verificationToken) {
        verificationTokenRepository.save(verificationToken);
    }
}
