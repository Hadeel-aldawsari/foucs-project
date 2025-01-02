package com.example.focus.Repository;

import com.example.focus.Model.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Integer> {
    Studio findStudioById(Integer id);
    Studio findStudioByUsername(String username);
}
