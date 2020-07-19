package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService extends DefaultService<Credential> {

    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        super(credentialMapper);
        this.encryptionService = encryptionService;
    }

    @Override
    public void add(Credential credential) {
        encrypt(credential);
        super.add(credential);
    }

    @Override
    public void update(Credential credential) {
        encrypt(credential);
        super.update(credential);
    }

    @Override
    public List<Credential> getByUserId(int userId) {
        List<Credential> credentials = super.getByUserId(userId);

        for (Credential credential : credentials) {
            String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
            credential.setDecryptedPassword(decryptedPassword);
        }

        return credentials;
    }

    private void encrypt(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
    }

}
