package example.service;

import example.model.entity.VerificationToken;

public interface VerificationTokenService {

    VerificationToken findByToken(String token);
    void delete(VerificationToken verificationToken);
    void save(VerificationToken verificationToken);
}
