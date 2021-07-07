# room test

#room API 测试
curl -X 'POST' \
  'http://localhost:8080/room' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer test_only_token' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "string"
}';
echo '\n #room \n';

#/room/{roomid}/enter
curl -X 'PUT' \
  'http://localhost:8080/room/test_only_room_id/enter' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer test_only_token';
echo '\n  #/room/{roomid}/enter \n';

#/roomLeave
curl -X 'PUT' \
  'http://localhost:8080/roomLeave' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer test_only_token';
echo '\n #/roomLeave \n';

#/room/{roomid}
curl -X 'GET' \
  'http://localhost:8080/room/test_only_room_id' \
  -H 'accept: */*';
echo '\n #/room/{roomid} \n';
#/room/{roomid}/users
curl -X 'GET' \
  'http://localhost:8080/room/test_only_room_id/users' \
  -H 'accept: application/json';
echo '\n #/room/{roomid}/users \n';
#/roomList
curl -X 'POST' \
  'http://localhost:8080/roomList' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "pageIndex": 0,
  "pageSize": 100
}';
echo '\n #/roomList \n';

#user api 测试
#/user
curl -X 'POST' \
  'http://localhost:8080/user' \
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
echo '\n #/user \n';
# /userLogin
curl -X 'GET' \
  'http://localhost:8080/userLogin?username=zhangsan&password=zhangsan' \
  -H 'accept: */*';
echo '\n # /userLogin \n';
#/user/{username}
curl -X 'GET' \
  'http://localhost:8080/user/zhangsan' \
  -H 'accept: application/json';
echo '\n #/user/{username} \n';

# message api 测试
#/message/send
curl -X 'POST' \
  'http://localhost:8080/message/send' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer test_only_token' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": "wobushixiaoxi",
  "text": "wobushixiaoxi"
}';
echo '\n #/message/send \n';
#/message/retrieve
curl -X 'POST' \
  'http://localhost:8080/message/retrieve' \
  -H 'accept: application/json' \
  -H 'Authorization: Bearer test_only_token' \
  -H 'Content-Type: application/json' \
  -d '{
  "pageIndex": -1,
  "pageSize": 100
}';
echo '\n #/message/retrieve \n';



