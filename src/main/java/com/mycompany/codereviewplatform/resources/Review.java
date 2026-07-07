package com.mycompany.codereviewplatform.resources;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    private int rating; // ၁ မှ ၅ အထိ ကြယ်ပွင့်ပြမှတ် (e.g., 1 to 5 stars)

    private LocalDateTime createdAt;

    // မည်သည့် Project အပေါ် Review ပေးလိုက်တာလဲဆိုတာ ချိတ်ဆက်ခြင်း
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    // မည်သည့်အသုံးပြုသူ (Reviewer) က ရေးပေးလိုက်တာလဲဆိုတာ ချိတ်ဆက်ခြင်း
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User reviewer;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public User getReviewer() { return reviewer; }
    public void setReviewer(User reviewer) { this.reviewer = reviewer; }
}