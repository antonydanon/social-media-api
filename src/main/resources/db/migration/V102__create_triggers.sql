create or replace function insert_creation_date_for_post()
    returns trigger
    language 'plpgsql'
    cost 100
    volatile not leakproof
as $BODY$
begin
    new.creation_date := CURRENT_DATE;
    return new;
end;
$BODY$;

create or replace trigger insert_creation_date_for_post_trg
    before insert
    on post
    for each row
    execute function insert_creation_date_for_post();

create or replace function insert_creation_date_for_chat()
    returns trigger
    language 'plpgsql'
    cost 100
    volatile not leakproof
as $BODY$
begin
new.creation_date := CURRENT_DATE;
return new;
end;
$BODY$;

create or replace trigger insert_creation_date_for_chat_trg
    before insert
    on chat
    for each row
    execute function insert_creation_date_for_chat();

create or replace function insert_send_date_for_message()
    returns trigger
    language 'plpgsql'
    cost 100
    volatile not leakproof
as $BODY$
begin
new.send_date := CURRENT_DATE;
return new;
end;
$BODY$;

create or replace trigger insert_send_date_for_message_trg
    before insert
    on chat
    for each row
    execute function insert_send_date_for_message();