/*
 * This file is generated by jOOQ.
 */
package com.ymm.back.domain.tables.daos;


import com.ymm.back.domain.tables.Project;
import com.ymm.back.domain.tables.records.ProjectRecord;

import java.time.LocalDateTime;
import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProjectDao extends DAOImpl<ProjectRecord, com.ymm.back.domain.tables.pojos.Project, Integer> {

    /**
     * Create a new ProjectDao without any configuration
     */
    public ProjectDao() {
        super(Project.PROJECT, com.ymm.back.domain.tables.pojos.Project.class);
    }

    /**
     * Create a new ProjectDao with an attached configuration
     */
    public ProjectDao(Configuration configuration) {
        super(Project.PROJECT, com.ymm.back.domain.tables.pojos.Project.class, configuration);
    }

    @Override
    public Integer getId(com.ymm.back.domain.tables.pojos.Project object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchRangeOfId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Project.PROJECT.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchById(Integer... values) {
        return fetch(Project.PROJECT.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.ymm.back.domain.tables.pojos.Project fetchOneById(Integer value) {
        return fetchOne(Project.PROJECT.ID, value);
    }

    /**
     * Fetch records that have <code>user_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchRangeOfUserId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Project.PROJECT.USER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchByUserId(Integer... values) {
        return fetch(Project.PROJECT.USER_ID, values);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Project.PROJECT.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchByName(String... values) {
        return fetch(Project.PROJECT.NAME, values);
    }

    /**
     * Fetch records that have <code>contents BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchRangeOfContents(String lowerInclusive, String upperInclusive) {
        return fetchRange(Project.PROJECT.CONTENTS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>contents IN (values)</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchByContents(String... values) {
        return fetch(Project.PROJECT.CONTENTS, values);
    }

    /**
     * Fetch records that have <code>view_count BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchRangeOfViewCount(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Project.PROJECT.VIEW_COUNT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>view_count IN (values)</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchByViewCount(Integer... values) {
        return fetch(Project.PROJECT.VIEW_COUNT, values);
    }

    /**
     * Fetch records that have <code>thumbnail BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchRangeOfThumbnail(String lowerInclusive, String upperInclusive) {
        return fetchRange(Project.PROJECT.THUMBNAIL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>thumbnail IN (values)</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchByThumbnail(String... values) {
        return fetch(Project.PROJECT.THUMBNAIL, values);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(Project.PROJECT.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchByDescription(String... values) {
        return fetch(Project.PROJECT.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>authority BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchRangeOfAuthority(String lowerInclusive, String upperInclusive) {
        return fetchRange(Project.PROJECT.AUTHORITY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>authority IN (values)</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchByAuthority(String... values) {
        return fetch(Project.PROJECT.AUTHORITY, values);
    }

    /**
     * Fetch records that have <code>total BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchRangeOfTotal(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Project.PROJECT.TOTAL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>total IN (values)</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchByTotal(Integer... values) {
        return fetch(Project.PROJECT.TOTAL, values);
    }

    /**
     * Fetch records that have <code>started_time BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchRangeOfStartedTime(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Project.PROJECT.STARTED_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>started_time IN (values)</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchByStartedTime(LocalDateTime... values) {
        return fetch(Project.PROJECT.STARTED_TIME, values);
    }

    /**
     * Fetch records that have <code>create_time BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchRangeOfCreateTime(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Project.PROJECT.CREATE_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>create_time IN (values)</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchByCreateTime(LocalDateTime... values) {
        return fetch(Project.PROJECT.CREATE_TIME, values);
    }

    /**
     * Fetch records that have <code>update_time BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchRangeOfUpdateTime(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Project.PROJECT.UPDATE_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>update_time IN (values)</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchByUpdateTime(LocalDateTime... values) {
        return fetch(Project.PROJECT.UPDATE_TIME, values);
    }

    /**
     * Fetch records that have <code>finished_time BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchRangeOfFinishedTime(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Project.PROJECT.FINISHED_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>finished_time IN (values)</code>
     */
    public List<com.ymm.back.domain.tables.pojos.Project> fetchByFinishedTime(LocalDateTime... values) {
        return fetch(Project.PROJECT.FINISHED_TIME, values);
    }
}
