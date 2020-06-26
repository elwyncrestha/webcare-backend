BEGIN
    DECLARE @count_user BIGINT
    SET @count_user = (SELECT COUNT(*) FROM users WHERE id = 1)

    IF (@count_user = 0)
        BEGIN
            SET IDENTITY_INSERT users ON
            INSERT INTO users(id, created_at, last_modified_at, created_by, last_modified_by,
                              version, username, password, name, email, status, user_type)
            VALUES (1, '2020-06-20 15:30:00', '2020-06-20 15:30:00', NULL, NULL, 0, 'spadmin',
                    '$2a$10$CGkAwfRBRIMVoEX8Ui9yx.NC03wKCjE19KIGVNET2F1mn0o58jkly',
                    'The Administrator', NULL, 1, 0)
            SET IDENTITY_INSERT users OFF
        END
END;
