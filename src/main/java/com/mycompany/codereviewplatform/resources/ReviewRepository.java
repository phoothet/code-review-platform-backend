package com.mycompany.codereviewplatform.resources;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    // ပရောဂျက်တစ်ခုချင်းစီအလိုက် ပေးထားတဲ့ Review Comments တွေကို ရှာဖွေပေးမယ့် လုပ်ဆောင်ချက်
    List<Review> findByProject(Project project);
}
