package com.rscinema.finalproject.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

//involves tracking and logging every change we make to our persisted data
public class AuditorAwareImpl implements AuditorAware<Integer> {
    @Override
    public @NotNull Optional<Integer> getCurrentAuditor() {
        return SecurityUtils.getLoggedUserId() != null ? Optional.of(SecurityUtils.getLoggedUserId()) : Optional.empty();

    }
}
