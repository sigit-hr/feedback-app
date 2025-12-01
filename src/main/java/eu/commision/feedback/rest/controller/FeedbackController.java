package eu.commision.feedback.rest.controller;

import eu.commision.feedback.entity.ContactType;
import eu.commision.feedback.rest.dto.FeedbackRequestDto;
import eu.commision.feedback.rest.dto.FeedbackResponseDto;
import eu.commision.feedback.service.FeedbackService;
import eu.commision.feedback.mapper.FeedbackMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

    private final FeedbackService service;
    private final FeedbackMapper mapper;

    public FeedbackController(FeedbackService service, FeedbackMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public FeedbackResponseDto submit(@Valid @RequestBody FeedbackRequestDto dto) {
        log.info("Received feedback request: {}", dto);
        var entity = mapper.toEntity(dto);
        var saved = service.submitFeedback(entity);
        return mapper.toDto(saved);
    }

    @GetMapping
    public Page<FeedbackResponseDto> list(
            @RequestParam(name = "contactType", required = false)  ContactType contactType,
            Pageable pageable
    ) {
        log.info("Received request to list feedbacks for {}", contactType);
        return service.getFeedback(contactType, pageable)
                .map(mapper::toDto);
    }
}
