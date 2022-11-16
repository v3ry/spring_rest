package com.example.rest2;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BlogController {

    @Autowired
    BlogRespository blogRespository;

    @GetMapping("/blogs")
    public List<Blog> index(){
        return blogRespository.findAll();
    }

    @GetMapping("/blogs/{id}")
    public Blog show(@PathVariable int id){
        return blogRespository.findById(id).get();
    }

    @PostMapping("/blogs/search")
    public List<Blog> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return blogRespository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/blogs")
    public Blog create(@RequestBody Blog blog){
        return blogRespository.save(blog);
    }

    @PutMapping("/blogs/{id}")
    public Blog update(@PathVariable int id, @RequestBody Blog blog){
        // getting blog
        Blog blogToUpdate = blogRespository.findById(id).get();
        blogToUpdate.setTitle(blog.getTitle());
        blogToUpdate.setContent(blog.getContent());
        return blogRespository.save(blogToUpdate);
    }

    @DeleteMapping("blogs/{id}")
    public boolean delete(@PathVariable int id){
        blogRespository.deleteById(id);
        return true;
    }
}