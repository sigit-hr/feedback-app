package eu.commision.feedback.rest.dto;

import eu.commision.feedback.entity.ContactType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record FeedbackRequestDto(
    @Max(100) String name,
    @Email @Max(100) String email,
    @NotNull ContactType contactType,
    @NotBlank @Max(1000) String message) {}
