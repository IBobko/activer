  delete from account_friend_relation where FRIEND_ACCOUNT_ID in (1113);
  delete from account_friend_relation where ACCOUNT_ID in (1113);

  delete from account_gift where ACCOUNT_ID in (1113);
  delete from account_gift where ACCOUNT_FROM_ID in (1113);

  delete from account_photo where ACCOUNT_ID in (1113);

  delete from authority where ACCOUNT_USERNAME like 'limit-speed@yandex.com';

  delete from BALANCE where ACCOUNT_ID in (1113);

  delete from children where ACCOUNT_ID in (1113);

  delete from DISPUTE_MESSAGE where DISPUTE_ID in (select id from HAPPENED_DISPUTE where ACCOUNT_INIT in (1113) or ACCOUNT_APPLIED in (1113));

  delete from HAPPENED_DISPUTE where ACCOUNT_INIT in (1113) or ACCOUNT_APPLIED in (1113);


  delete from FLIRT_MESSAGE where FLIRT_ID in (select id from HAPPENED_FLIRT where ACCOUNT_INIT in (1113) or ACCOUNT_APPLIED in (1113));


  delete from HAPPENED_FLIRT where ACCOUNT_INIT in (1113) or ACCOUNT_APPLIED in (1113);


  delete from DREAM where ACCOUNT_ID  in (1113);

  delete from EDUCATION where ACCOUNT_ID in (1113);

  delete from INTEREST where ACCOUNT_ID in (1113);

  delete from JOB where ACCOUNT_ID in (1113);

  delete from message where ACCOUNT_FROM in (1113) or ACCOUNT_TO in (1113);

  delete from NETWORK_LIST_CACHE where ACCOUNT_ID in (1113) or OWNER_ACCOUNT_ID  in (1113);

  delete from NEWS where ACCOUNT_ID  in (1113);

  delete from PAYMENT_CREDIT where ACCOUNT_ID  in (1113);

  delete from PAYMENT_DEBIT where ACCOUNT_ID  in (1113);

  delete from PHOTO where ACCOUNT_ID  in (1113);

  delete from PHOTO_ALBUM where ACCOUNT_ID  in (1113);


  delete from SETTING where ACCOUNT_ID  in (1113);

  delete from trip where ACCOUNT_ID  in (1113);

  delete from video where ACCOUNT_ID  in (1113);

  delete from wall where ACCOUNT_ID  in (1113);

  delete from account where id in (1113);
