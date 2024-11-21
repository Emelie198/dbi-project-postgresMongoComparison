package at.movierating.mongoembedded.controllers;

import at.movierating.mongo.model.EmbeddedReview;
import at.movierating.mongo.persistence.repositories.EmbeddedReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongo/embeddedreview")
public class EmbeddedReviewController {

    @Autowired
    private EmbeddedReviewRepository embeddedReviewRepository;

    @GetMapping
    public List<EmbeddedReview> getAllEmbeddedReviews() {
        return embeddedReviewRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmbeddedReview> getEmbeddedReviewById(@PathVariable int id) {
        Optional<EmbeddedReview> embeddedReview = embeddedReviewRepository.findById(id);
        return embeddedReview.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmbeddedReview createEmbeddedReview(@RequestBody EmbeddedReview embeddedReview) {
        return embeddedReviewRepository.save(embeddedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmbeddedReview(@PathVariable int id) {
        Optional<EmbeddedReview> embeddedReview = embeddedReviewRepository.findById(id);
        if (embeddedReview.isPresent()) {
            embeddedReviewRepository.delete(embeddedReview.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}