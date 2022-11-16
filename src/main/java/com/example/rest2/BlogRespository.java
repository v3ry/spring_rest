package com.example.rest2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRespository extends JpaRepository<Blog, Integer> {

    List<Blog> findByTitleContainingOrContentContaining(String text, String textAgain);

}
