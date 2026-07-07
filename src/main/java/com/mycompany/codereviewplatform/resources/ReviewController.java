package com.mycompany.codereviewplatform.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*") // Front-end UI ကနေ လှမ်းခေါ်ခွင့်ပြုခြင်း
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // HTTP POST: http://localhost:8080/api/reviews/submit?projectId=1&reviewerId=1
    // ပရောဂျက်တစ်ခုကို Review/Comment ပေးမည့် API
    @PostMapping("/submit")
    public ResponseEntity<?> submitReview(
            @RequestBody Review review,
            @RequestParam Long projectId,
            @RequestParam Long reviewerId) {
        try {
            Review savedReview = reviewService.saveReview(review, projectId, reviewerId);
            return ResponseEntity.ok(savedReview);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // HTTP GET: http://localhost:8080/api/reviews/project/1
    // ပရောဂျက်တစ်ခုချင်းစီအလိုက် ရှိသမျှ Review တွေအားလုံးကို ပြန်ထုတ်ပေးမည့် API
    @GetMapping("/project/{projectId}")
    public List<Review> getReviewsByProjectId(@PathVariable Long projectId) {
        return reviewService.getReviewsByProjectId(projectId);
    }
}