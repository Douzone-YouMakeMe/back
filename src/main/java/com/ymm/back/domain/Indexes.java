/*
 * This file is generated by jOOQ.
 */
package com.ymm.back.domain;


import com.ymm.back.domain.tables.Agenda;
import com.ymm.back.domain.tables.Comment;
import com.ymm.back.domain.tables.FileStorage;
import com.ymm.back.domain.tables.Hashtag;
import com.ymm.back.domain.tables.Message;
import com.ymm.back.domain.tables.Project;
import com.ymm.back.domain.tables.ProjectMember;
import com.ymm.back.domain.tables.Voting;
import com.ymm.back.domain.tables.Work;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code></code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index AGENDA_FK_AGENDA_MEMBER1_IDX = Indexes0.AGENDA_FK_AGENDA_MEMBER1_IDX;
    public static final Index AGENDA_FK_AGENDA_PROJECT1_IDX = Indexes0.AGENDA_FK_AGENDA_PROJECT1_IDX;
    public static final Index COMMENT_FK_COMMENT_MEMBER1_IDX = Indexes0.COMMENT_FK_COMMENT_MEMBER1_IDX;
    public static final Index COMMENT_FK_COMMENT_WORK1_IDX = Indexes0.COMMENT_FK_COMMENT_WORK1_IDX;
    public static final Index FILE_STORAGE_FK_FILE_STORAGE_MESSAGE1_IDX = Indexes0.FILE_STORAGE_FK_FILE_STORAGE_MESSAGE1_IDX;
    public static final Index HASHTAG_FK_HASHTAG_WORK1_IDX = Indexes0.HASHTAG_FK_HASHTAG_WORK1_IDX;
    public static final Index PROJECT_MEMBER_FK_MEMBER_PROJECT1_IDX = Indexes0.PROJECT_MEMBER_FK_MEMBER_PROJECT1_IDX;
    public static final Index PROJECT_MEMBER_FK_MEMBER_USER1_IDX = Indexes0.PROJECT_MEMBER_FK_MEMBER_USER1_IDX;
    public static final Index MESSAGE_FK_MESSAGE_MEMBER1_IDX = Indexes0.MESSAGE_FK_MESSAGE_MEMBER1_IDX;
    public static final Index MESSAGE_FK_MESSAGE_PROJECT1_IDX = Indexes0.MESSAGE_FK_MESSAGE_PROJECT1_IDX;
    public static final Index PROJECT_FK_PROJECT_USER_IDX = Indexes0.PROJECT_FK_PROJECT_USER_IDX;
    public static final Index VOTING_FK_VOTING_AGENDA1_IDX = Indexes0.VOTING_FK_VOTING_AGENDA1_IDX;
    public static final Index VOTING_FK_VOTING_MEMBER1_IDX = Indexes0.VOTING_FK_VOTING_MEMBER1_IDX;
    public static final Index WORK_FK_WORK_MEMBER1_IDX = Indexes0.WORK_FK_WORK_MEMBER1_IDX;
    public static final Index WORK_FK_WORK_PROJECT1_IDX = Indexes0.WORK_FK_WORK_PROJECT1_IDX;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index AGENDA_FK_AGENDA_MEMBER1_IDX = Internal.createIndex("fk_agenda_member1_idx", Agenda.AGENDA, new OrderField[] { Agenda.AGENDA.MEMBER_ID }, false);
        public static Index AGENDA_FK_AGENDA_PROJECT1_IDX = Internal.createIndex("fk_agenda_project1_idx", Agenda.AGENDA, new OrderField[] { Agenda.AGENDA.PROJECT_ID }, false);
        public static Index COMMENT_FK_COMMENT_MEMBER1_IDX = Internal.createIndex("fk_comment_member1_idx", Comment.COMMENT, new OrderField[] { Comment.COMMENT.MEMBER_ID }, false);
        public static Index COMMENT_FK_COMMENT_WORK1_IDX = Internal.createIndex("fk_comment_work1_idx", Comment.COMMENT, new OrderField[] { Comment.COMMENT.WORK_ID }, false);
        public static Index FILE_STORAGE_FK_FILE_STORAGE_MESSAGE1_IDX = Internal.createIndex("fk_file_storage_message1_idx", FileStorage.FILE_STORAGE, new OrderField[] { FileStorage.FILE_STORAGE.MESSAGE_ID }, false);
        public static Index HASHTAG_FK_HASHTAG_WORK1_IDX = Internal.createIndex("fk_hashtag_work1_idx", Hashtag.HASHTAG, new OrderField[] { Hashtag.HASHTAG.WORK_ID }, false);
        public static Index PROJECT_MEMBER_FK_MEMBER_PROJECT1_IDX = Internal.createIndex("fk_member_project1_idx", ProjectMember.PROJECT_MEMBER, new OrderField[] { ProjectMember.PROJECT_MEMBER.PROJECT_ID }, false);
        public static Index PROJECT_MEMBER_FK_MEMBER_USER1_IDX = Internal.createIndex("fk_member_user1_idx", ProjectMember.PROJECT_MEMBER, new OrderField[] { ProjectMember.PROJECT_MEMBER.USER_ID }, false);
        public static Index MESSAGE_FK_MESSAGE_MEMBER1_IDX = Internal.createIndex("fk_message_member1_idx", Message.MESSAGE, new OrderField[] { Message.MESSAGE.MEMBER_ID }, false);
        public static Index MESSAGE_FK_MESSAGE_PROJECT1_IDX = Internal.createIndex("fk_message_project1_idx", Message.MESSAGE, new OrderField[] { Message.MESSAGE.PROJECT_ID }, false);
        public static Index PROJECT_FK_PROJECT_USER_IDX = Internal.createIndex("fk_project_user_idx", Project.PROJECT, new OrderField[] { Project.PROJECT.USER_ID }, false);
        public static Index VOTING_FK_VOTING_AGENDA1_IDX = Internal.createIndex("fk_voting_agenda1_idx", Voting.VOTING, new OrderField[] { Voting.VOTING.AGENDA_ID }, false);
        public static Index VOTING_FK_VOTING_MEMBER1_IDX = Internal.createIndex("fk_voting_member1_idx", Voting.VOTING, new OrderField[] { Voting.VOTING.MEMBER_ID }, false);
        public static Index WORK_FK_WORK_MEMBER1_IDX = Internal.createIndex("fk_work_member1_idx", Work.WORK, new OrderField[] { Work.WORK.MEMBER_ID }, false);
        public static Index WORK_FK_WORK_PROJECT1_IDX = Internal.createIndex("fk_work_project1_idx", Work.WORK, new OrderField[] { Work.WORK.PROJECT_ID }, false);
    }
}
