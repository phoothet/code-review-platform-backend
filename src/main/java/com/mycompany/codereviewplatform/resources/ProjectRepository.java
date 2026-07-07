package com.mycompany.codereviewplatform.resources; 

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {  
    
    List<Project> findByUser(User user);
}