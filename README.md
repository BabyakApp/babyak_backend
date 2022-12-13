# babyak_backend

실행 조건

1. h2 실행
local 에 있는 h2.sh 파일 찾아서 terminal에서 ./h2.sh 명령어로 h2 실행

2. redis 실행
docker에서 redis 실행
   (1) docker run -d -p 6379:6379 redis
    : 포트 6379로 Redis 백그라운드 실행.
   (2) docker ps
    : Redis 컨테이너의 containerId 확인
   (3) docker exec -it [containerId] /bin/bash
    : Redis 컨테이너 내부 접속
   (4) redis-cli
    : Redis 접속