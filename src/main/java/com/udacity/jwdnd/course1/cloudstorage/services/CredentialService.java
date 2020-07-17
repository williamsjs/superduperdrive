package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

@Service
public class CredentialService extends DefaultService<Credential> {

    public CredentialService(CredentialMapper credentialMapper) {
        super(credentialMapper);
    }

}
