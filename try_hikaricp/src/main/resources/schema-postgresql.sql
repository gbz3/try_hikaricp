create table if not exists customer (
    id serial primary key,
    name text,
    email text
);

INSERT INTO customer( name, email ) VALUES( 'hoge', 'hoge@gmail.com' );
INSERT INTO customer( name, email ) VALUES( 'fuga', 'fuga@gmail.com' );
