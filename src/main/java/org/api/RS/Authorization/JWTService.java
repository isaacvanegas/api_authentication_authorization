package org.api.RS.Authorization;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Dependent
public class JWTService {
    private static final Logger LOG = LoggerFactory.getLogger(JWTService.class);

}
