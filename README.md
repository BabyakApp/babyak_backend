# babyak_backend


서버 배포를 했으나, Google Login은 실제 배포 시 사용하려면 google의 승인을 받아야 해서 일단 테스트는 로컬로만 가능합니다.


개인정보 보호 문제 때문에 github에 업로드 되지 않은 파일이 있어, 과제함에 있는 '추가설정파일'을 토대로 파일을 추가해야 합니다.
<br>
application.yml이 있는 패키지에, application-db.yml과 application-oauth.yml 파일을 추가해줘야 합니다.
<br>
MariaDB 서버와 Redis 서버는 배포가 완료된 상태이기 때문에 로컬에 설치하거나 하는 등의 추가 setting 할 필요 없이 바로 사용할 수 있습니다.


Google login은 현재 배포가 아닌, 테스트 모드로
<br>
Google 약관에 따르면 미리 설정한 계정으로밖에 테스트를 하지 못 합니다.
<br>
따라서, 새로운 계정으로 테스트하고자 한다면 따로 말씀을 주셔야 테스트가 가능합니다.


