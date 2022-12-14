# babyak_backend

실행 조건

1.h2 실행
<br>
local 에 있는 h2.sh 파일 찾아서 terminal에서 ./h2.sh 명령어로 h2 실행

2.redis 실행
<br>
docker에서 redis 실행
<br>
   (1) docker run -d -p 6379:6379 redis
   <br>
    : 포트 6379로 Redis 백그라운드 실행.
    <br>
   (2) docker ps
   <br>
    : Redis 컨테이너의 containerId 확인
    <br>
   (3) docker exec -it [containerId] /bin/bash
   <br>
    : Redis 컨테이너 내부 접속
    <br>
   (4) redis-cli
   <br>
    : Redis 접속
