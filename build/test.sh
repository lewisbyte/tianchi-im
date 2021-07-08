# room test
echo '\n --------------start test ------------------ \n';
date;


#room API 测试
curl -X 'POST' \
  'http://localhost:8080/room' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer 1412452156027965442' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "stringjjojojoojo"
}';
echo '\n 1#room \n';
# 测试没有token场景
curl -X 'POST' \
  'http://localhost:8080/room' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "string"
}';
echo '\n 2#room \n';


# /userLogin
curl -X 'GET' \
  'http://localhost:8080/userLogin?username=djka01wq&password=string' \
  -H 'accept: */*';
echo '\n 11# /userLogin \n';



#/room/{roomid}/enter
curl -X 'PUT' \
  'http://localhost:8080/room/1412450112839880706/enter' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer 1412452156027965442';
echo '\n  3#/room/{roomid}/enter \n';
#/room/{roomid}/enter
# 测试没有token场景
curl -X 'PUT' \
  'http://localhost:8080/room/1412450112839880706/enter' \
  -H 'accept: */*' ;
echo '\n  4#/room/{roomid}/enter \n';




#/room/{roomid}
curl -X 'GET' \
  'http://localhost:8080/room/1412450112839880706' \
  -H 'accept: */*';
echo '\n 7#/room/{roomid} \n';




#/room/{roomid}/users
curl -X 'GET' \
  'http://localhost:8080/room/1412450112839880706/users' \
  -H 'accept: application/json';
echo '\n 8#/room/{roomid}/users \n';



#/roomList
curl -X 'POST' \
  'http://localhost:8080/roomList' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "pageIndex": 0,
  "pageSize": 100
}';
echo '\n 9#/roomList \n';





#user api 测试
#/user
curl -X 'POST' \
  'http://localhost:8080/user' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "username": "1412452156027965442",
  "firstName": "1412452156027965442",
  "lastName": "1412452156027965442",
  "email": "1412452156027965442",
  "password": "1412452156027965442",
  "phone": "1412452156027965442"
}';
echo '\n 10#/user \n';







#/user/{username}
curl -X 'GET' \
  'http://localhost:8080/user/zhangsan' \
  -H 'accept: application/json';
echo '\n 12#/user/{username} \n';





# message api 测试
#/message/send
curl -X 'POST' \
  'http://localhost:8080/message/send' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer 1412452156027965442' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": "wobushixiaoxi",
  "text": "wobushixiaoxi"
}';
echo '\n 13#/message/send \n';
#/message/send
curl -X 'POST' \
  'http://localhost:8080/message/send' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": "wobushixiaoxi",
  "text": "wobushixiaoxi"
}';
echo '\n 14#/message/send \n';




#/message/retrieve
curl -X 'POST' \
  'http://localhost:8080/message/retrieve' \
  -H 'accept: application/json' \
  -H 'Authorization: Bearer 1412452156027965442' \
  -H 'Content-Type: application/json' \
  -d '{
  "pageIndex": -1,
  "pageSize": 100
}';
echo '\n 15#/message/retrieve \n';
curl -X 'POST' \
  'http://localhost:8080/message/retrieve' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "pageIndex": -1,
  "pageSize": 100
}';
echo '\n 16#/message/retrieve \n';


#/roomLeave
curl -X 'PUT' \
  'http://localhost:8080/roomLeave' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer 1412452156027965442';
echo '\n 5#/roomLeave \n';
#/roomLeave
curl -X 'PUT' \
  'http://localhost:8080/roomLeave' \
  -H 'accept: */*' ;
echo '\n 6#/roomLeave \n';



echo '\n --------------end test ------------------ \n';