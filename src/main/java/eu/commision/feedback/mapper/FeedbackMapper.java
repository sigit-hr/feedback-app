package eu.commision.feedback.mapper;

import eu.commision.feedback.entity.Feedback;
import eu.commision.feedback.rest.dto.FeedbackRequestDto;
import eu.commision.feedback.rest.dto.FeedbackResponseDto;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMapper {

  public Feedback toEntity(FeedbackRequestDto dto) {
    return Feedback.builder()
        .name(dto.name())
        .email(dto.email())
        .contactType(dto.contactType())
        .message(dto.message())
        .build();
  }

  public FeedbackResponseDto toDto(Feedback entity) {
    return FeedbackResponseDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .email(entity.getEmail())
        .contactType(entity.getContactType())
        .message(entity.getMessage())
        .submittedAt(entity.getSubmittedAt())
        .build();
  }
}
