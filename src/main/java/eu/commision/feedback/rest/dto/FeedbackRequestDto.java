package eu.commision.feedback.rest.dto;

import eu.commision.feedback.entity.ContactType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record FeedbackRequestDto(
    @Size(max = 100) String name,
    @Size(max = 100) String email,
    @NotNull ContactType contactType,
    @NotBlank @Size(max = 1000) String message) {}
