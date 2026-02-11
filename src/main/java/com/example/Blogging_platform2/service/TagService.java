package com.example.Blogging_platform2.service;

import com.example.Blogging_platform2.model.Tag;
import com.example.Blogging_platform2.dao.TagDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagDao tagDao;

    public TagService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public Tag saveTag(Tag tag) {
        return tagDao.save(tag);
    }

    public Optional<Tag> getTagById(Long id) {
        return tagDao.findById(id);
    }

    public Optional<Tag> getTagByName(String name) {
        return tagDao.findByName(name);
    }

    public List<Tag> getAllTags() {
        return tagDao.findAll();
    }

    public void deleteTag(Long id) {
        tagDao.deleteById(id);
    }
}
