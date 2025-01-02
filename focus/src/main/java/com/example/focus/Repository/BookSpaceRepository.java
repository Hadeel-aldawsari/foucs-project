package com.example.focus.Repository;

import com.example.focus.Model.BookSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookSpaceRepository extends JpaRepository<BookSpace, Integer> {
    BookSpace findRentalStudioRequestById(Integer studioId);
}
