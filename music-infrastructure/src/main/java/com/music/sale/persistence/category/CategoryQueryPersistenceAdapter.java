package com.music.sale.persistence.category;

import com.music.sale.application.category.port.outport.CategoryQueryPort;
import com.music.sale.domain.category.Category;
import com.music.sale.domain.category.CategoryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class CategoryQueryPersistenceAdapter implements CategoryQueryPort {

    private static final Logger log = LoggerFactory.getLogger(CategoryQueryPersistenceAdapter.class);

    @Override
    public Category getCategoryById(Long id) {
        log.warn("getCategoryById is not implemented yet");
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Category> findAll() {
        log.warn("findAll is not implemented yet");
        return Collections.emptyList();
    }

    @Override
    public Category findById(Long id) {
        log.warn("findById is not implemented yet");
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Category> findByType(CategoryType type) {
        log.warn("findByType is not implemented yet");
        return Collections.emptyList();
    }

    @Override
    public List<Category> findRootCategories() {
        log.warn("findRootCategories is not implemented yet");
        return Collections.emptyList();
    }

    @Override
    public List<Category> findByParentId(Long parentId) {
        log.warn("findByParentId is not implemented yet");
        return Collections.emptyList();
    }

    @Override
    public Category save(Category category) {
        log.warn("save is not implemented yet");
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void delete(Long id) {
        log.warn("delete is not implemented yet");
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
