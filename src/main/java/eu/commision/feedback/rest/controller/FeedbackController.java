package eu.commision.feedback.rest.controller;

import eu.commision.feedback.entity.ContactType;
import eu.commision.feedback.rest.dto.FeedbackRequestDto;
import eu.commision.feedback.rest.dto.FeedbackResponseDto;
import eu.commision.feedback.service.FeedbackService;
import eu.commision.feedback.mapper.FeedbackMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feedback")
@CrossOrigin
public class FeedbackController {

    private final FeedbackService service;
    private final FeedbackMapper mapper;

    public FeedbackController(FeedbackService service, FeedbackMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public FeedbackResponseDto submit(@Valid @RequestBody FeedbackRequestDto dto) {
        var entity = mapper.toEntity(dto);
        var saved = service.submitFeedback(entity);
        return mapper.toDto(saved);
    }

    @GetMapping
    public Page<FeedbackResponseDto> list(
            @RequestParam(required = false) ContactType type,
            Pageable pageable
    ) {
        return service.getFeedback(type, pageable)
                .map(mapper::toDto);
    }
}
