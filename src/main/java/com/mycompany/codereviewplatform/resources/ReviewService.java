package com.mycompany.codereviewplatform.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    // ပရောဂျက်တစ်ခုအပေါ် Review အသစ် ရေးပေးမည့် လုပ်ဆောင်ချက် (Save Review)
    public Review saveReview(Review review, Long projectId, Long reviewerId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));

        User reviewer = userRepository.findById(reviewerId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + reviewerId));

        review.setProject(project);   // ဘယ် Project အပေါ် ပေးတာလဲတွဲပေးခြင်း
        review.setReviewer(reviewer); // ဘယ်သူက ပေးလိုက်တာလဲ (Reviewer) တွဲပေးခြင်း
        
        return reviewRepository.save(review);
    }

    // ပရောဂျက်တစ်ခုချင်းစီအလိုက် ရှိသမျှ Review တွေကို ပြန်ထုတ်ပေးခြင်း (Get Reviews By Project)
    public List<Review> getReviewsByProjectId(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        
        return reviewRepository.findByProject(project);
    }
}
