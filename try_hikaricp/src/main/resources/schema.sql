create table if not exists customer (
    id int primary key auto_increment,
    name varchar(50),
    email varchar(50)
);

INSERT INTO customer( name, email ) VALUES( 'hoge', 'hoge@gmail.com' );
INSERT INTO customer( name, email ) VALUES( 'fuga', 'fuga@gmail.com' );
