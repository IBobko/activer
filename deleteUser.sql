delete from account_friend_relation where FRIEND_ACCOUNT_ID in (50,361);
delete from account_friend_relation where ACCOUNT_ID in (50,361);

delete from account_gift where ACCOUNT_ID in (50,361);
delete from account_gift where ACCOUNT_FROM_ID in (50,361);

delete from account_photo where ACCOUNT_ID in (50,361);

delete from authority where ACCOUNT_USERNAME like 'alexmashkovcev@yandex.ru';
delete from authority WHERE ACCOUNT_USERNAME like 'Alexmashkovcev@yandex.ru';

delete from BALANCE where ACCOUNT_ID in (50,361);

delete from children where ACCOUNT_ID in (50,361);

delete from DISPUTE_MESSAGE where DISPUTE_ID in (select id from HAPPENED_DISPUTE where ACCOUNT_INIT in (63,361) or ACCOUNT_APPLIED in (50,361));

delete from HAPPENED_DISPUTE where ACCOUNT_INIT in (63,361) or ACCOUNT_APPLIED in (50,361);


delete from FLIRT_MESSAGE where FLIRT_ID in (select id from HAPPENED_FLIRT where ACCOUNT_INIT in (50,361) or ACCOUNT_APPLIED in (50,361));


delete from HAPPENED_FLIRT where ACCOUNT_INIT in (50,361) or ACCOUNT_APPLIED in (50,361);


delete from DREAM where ACCOUNT_ID  in (50,361);

delete from EDUCATION where ACCOUNT_ID in (50,361);

delete from INTEREST where ACCOUNT_ID in (50,361);

delete from JOB where ACCOUNT_ID in (50,361);

delete from message where ACCOUNT_FROM in (50,361) or ACCOUNT_TO in (50,361);

delete from NETWORK_LIST_CACHE where ACCOUNT_ID in (50,361) or OWNER_ACCOUNT_ID  in (50,361);

delete from NEWS where ACCOUNT_ID  in (50,361);

delete from PAYMENT_CREDIT where ACCOUNT_ID  in (50,361);

delete from PAYMENT_DEBIT where ACCOUNT_ID  in (50,361);

delete from PHOTO where ACCOUNT_ID  in (50,361);

delete from PHOTO_ALBUM where ACCOUNT_ID  in (50,361);


delete from SETTING where ACCOUNT_ID  in (50,361);

delete from trip where ACCOUNT_ID  in (50,361);

delete from video where ACCOUNT_ID  in (50,361);

delete from wall where ACCOUNT_ID  in (50,361);

delete from account where id in (50,361);
