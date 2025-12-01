package eu.commision.feedback.rest.dto;

import eu.commision.feedback.entity.ContactType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FeedbackResponseDto(
    Long id,
    String name,
    String email,
    ContactType contactType,
    String message,
    LocalDateTime submittedAt) {}
