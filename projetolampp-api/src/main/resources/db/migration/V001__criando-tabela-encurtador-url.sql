CREATE TABLE encurtador_url (
    id bigint NOT NULL PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    codigo VARCHAR(30)
);

alter table encurtador_url
add constraint uk_encurtador_url unique (codigo);