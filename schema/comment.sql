CREATE TABLE TBL_COMMENTS (
    COMMENT_ID BIGINT NOT NULL PRIMARY KEY,
    CREATED_AT TIMESTAMP(6) NOT NULL,
    MODIFIED_AT TIMESTAMP(6),
    COMMENT CHARACTER VARYING(100),
    STATUS CHARACTER VARYING(255),
    MEMBER_ID BIGINT,
    POST_ID BIGINT
);
