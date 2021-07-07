# room test

#room
curl -X 'POST' \
  'https://editor.swagger.io/room' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer woshitokenhhhh' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "string"
}';

#/room/{roomid}/enter
curl -X 'PUT' \
  'https://editor.swagger.io/room/roomiddhaskhka/enter' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer woshitokenhhhh';


#/roomLeave
curl -X 'PUT' \
  'https://editor.swagger.io/roomLeave' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer woshitokenhhhh';

#/room/{roomid}
curl -X 'GET' \
  'https://editor.swagger.io/room/roomiddhaskhka' \
  -H 'accept: */*';

#/room/{roomid}/users
curl -X 'GET' \
  'https://editor.swagger.io/room/roomiddhaskhka/users' \
  -H 'accept: application/json';

#/roomList
curl -X 'POST' \
  'https://editor.swagger.io/roomList' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "pageIndex": 0,
  "pageSize": 100
}';


#user
#/user
curl -X 'POST' \
  'https://editor.swagger.io/user' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "username": "zhangsan",
  "firstName": "zhangsan",
  "lastName": "zhangsan",
  "email": "zhangsan",
  "password": "zhangsan",
  "phone": "zhangsan"
}';

# /userLogin
curl -X 'GET' \
  'https://editor.swagger.io/userLogin?username=zhangsan&password=zhangsan' \
  -H 'accept: */*';

#/user/{username}
curl -X 'GET' \
  'https://editor.swagger.io/user/zhangsan' \
  -H 'accept: application/json';


# message

#/message/send
curl -X 'POST' \
  'https://editor.swagger.io/message/send' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer woshitokenhhhh' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": "wobushixiaoxi",
  "text": "wobushixiaoxi"
}';
#/message/retrieve
curl -X 'POST' \
  'https://editor.swagger.io/message/retrieve' \
  -H 'accept: application/json' \
  -H 'Authorization: Bearer woshitokenhhhh' \
  -H 'Content-Type: application/json' \
  -d '{
  "pageIndex": -1,
  "pageSize": 100
}';




