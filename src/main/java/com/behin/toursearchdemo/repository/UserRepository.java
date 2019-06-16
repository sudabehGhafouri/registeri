package com.behin.toursearchdemo.repository;

import Model.UserSignin;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.awt.print.Pageable;

public interface UserRepository extends ElasticsearchRepository<UserSignin, String> {

    Page<UserSignin> findByAuthorsName(String userName, Pageable pageable);

////    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
////    Page<Article> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);
////
////    @Query("{\"bool\": {\"must\": {\"match_all\": {}}, \"filter\": {\"term\": {\"tags\": \"?0\" }}}}")
////    Page<Article> findByFilteredTagQuery(String tag, Pageable pageable);
////
////    @Query("{\"bool\": {\"must\": {\"match\": {\"authors.name\": \"?0\"}}, \"filter\": {\"term\": {\"tags\": \"?1\" }}}}")
////    Page<Article> findByAuthorsNameAndFilteredTagQuery(String name, String tag, Pageable pageable);
}
