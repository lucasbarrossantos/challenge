-- Liquibase formatted SQL

-- changeset lucas:created-user-table
CREATE TABLE IF NOT EXISTS public.app_user (
    "id" UUID NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY(id)
);
-- rollback DROP TABLE user;