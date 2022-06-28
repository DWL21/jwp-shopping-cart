# 장바구니 1단계 - 회원 기능
장바구니 미션 저장소

## 미션 내용
- JWT를 이용한 액세스 토큰 발급 기능 구현
- 발급한 토큰을 이용하여 로그인이 필요한 기능 요청 시 포함하여 보냄
    - 회원 가입
    - 로그인 (토큰 발급)
    - 회원 정보 조회 (토큰 활용)
    - 회원 정보 수정 (토큰 활용)
    - 회원 탈퇴 (토큰 활용)
- 위 기능 동작하도록 구현

### 회원 정보
- Email
  - 이메일 형식 검사
- Password
  - 최소 8자 이상 16자 이하
  - 대소문자 구분 각각 1개 이상, 숫자 1개 이상, 특수문자 1개 이상
- 이름
  - 최소 1자 이상 30자 이하
- 전화번호
  - 010-xxxx-xxxx 형식
- 주소

## API
- [x] 회원 가입
    - POST
    - 201
    - /customers
    - Request
        - Email (email)
        - PW (password)
        - 이름 (name)
        - 전화번호 (phone)
        - 주소 (address)
    - Response
        - Email (email)
        - 이름 (name)
        - 전화번호 (phone)
        - 주소 (address)
- [x] 로그인
    - POST
    - 200
    - /customers/login
    - Request (TokenRequest)
        - Email (email)
        - PW (password)
    - Response (TokenResponse)
        - accessToken
- [x] 회원 정보 조회
    - GET
    - 200
    - /customers
    - Request
        - Token 사용
    - Response
        - Email (email)
        - 이름 (name)
        - 전화번호 (phone)
        - 주소 (address)
- [x] 회원 정보 수정
    - PUT
    - 200
    - /customers
    - Request
        - 이름 (name)
        - 전화번호 (phone)
        - 주소 (address)
        - Password (password)
    - Response
        - X
- [x] 회원 탈퇴
    - DELETE
    - /customers
    - 204
    - Request
        - PW (password)



