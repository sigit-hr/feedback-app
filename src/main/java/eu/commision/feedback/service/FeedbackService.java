package eu.commision.feedback.service;

import eu.commision.feedback.entity.ContactType;
import eu.commision.feedback.entity.Feedback;
import eu.commision.feedback.repository.FeedbackRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository repository;

    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    public Feedback submitFeedback(Feedback feedback) {
        return repository.save(feedback);
    }

    public Page<Feedback> getFeedback(ContactType type, Pageable pageable) {
        if (type != null) {
            return repository.findByContactType(type, pageable);
        }
        return repository.findAll(pageable);
    }
}
