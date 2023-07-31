package com.rscinema.finalproject.configuration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
//involves tracking and logging every change we make to our persisted data
public class AuditorAwareImpl implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {
        return SecurityUtils.getLoggedUserId()!=null?Optional.of(SecurityUtils.getLoggedUserId()):Optional.empty();

    }
}
