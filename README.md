# 🏨 Vinst Hotel
Java MVC 기반 호텔 예약 웹 서비스

Vinst Hotel은 Java MVC 아키텍처를 기반으로 개발된 호텔 예약 웹 애플리케이션입니다.  
사용자는 객실을 조회하고 예약할 수 있으며, 관리자는 객실 / 예약 / 시설을 관리할 수 있습니다.

실제 호텔 웹사이트를 참고하여 사용자 경험(UI/UX)과 관리자 기능을 함께 고려한 Full-Stack 웹 프로젝트입니다.

---

# 🌐 Live Demo

https://vinsthotel.duckdns.org

---

# 📌 프로젝트 개요

| 항목 | 내용 |
|---|---|
| 프로젝트명 | Vinst Hotel |
| 개발 기간 | 2026.03 ~ 2026.03 |
| 개발 인원 | 5명 |
| 개발 방식 | Java MVC Architecture |
| Repository | https://github.com/yhfsd/beanst |

---

# 🛠 기술 스택

### Backend
- Java 17
- JSP / Servlet
- Apache Tomcat 10
- MVC Architecture
- Front Controller Pattern

### Database
- MariaDB
- SQL

### Frontend
- HTML5
- CSS3
- JavaScript
- Swiper.js

### Infrastructure
- Oracle Cloud Infrastructure (OCI)
- Linux Server

### Version Control
- Git
- GitHub

---

# 🏗 시스템 아키텍처

```
Client (Browser)
       │
       ▼
FrontController (Servlet)
       │
       ▼
HandlerMapper
       │
       ▼
Controller (Action)
       │
       ▼
Service Layer
       │
       ▼
DAO Layer
       │
       ▼
MariaDB
```

---

# 🗄 데이터베이스 구조

### 주요 테이블

```
users
room
room_image
facility_image
hotel_facility
qna
faq
room_manage
reservation
```

---

# ✨ 주요 기능

## 👤 사용자 기능

- 회원가입 / 로그인
- 객실 목록 조회
- 객실 상세 조회
- 객실 예약
- 호텔 시설 조회
- Q&A 문의

---

## 🔧 관리자 기능

### 객실 관리
- 객실 등록
- 객실 수정
- 객실 삭제
- 객실 이미지 관리

### 예약 관리
- 예약 조회
- 예약 관리

### 시설 관리
- 호텔 시설 등록
- 시설 수정
- 시설 삭제

### Q&A 관리
- 문의 관리
- 답변 작성

---

# 📂 프로젝트 구조

```
src
 ├─ controller
 │   ├─ FrontController
 │   ├─ admin
 │   └─ user
 │
 ├─ service
 ├─ dao
 ├─ vo
 └─ filter

webapp
 ├─ WEB-INF
 │   ├─ views
 │   │   ├─ admin
 │   │   └─ user
 │   └─ web.xml
 │
 ├─ assets
 │   ├─ css
 │   ├─ js
 │   └─ images
```

---

# 🚀 실행 방법

### 1. 프로젝트 클론

```bash
git clone https://github.com/yhfsd/beanst.git
```

### 2. 데이터베이스 생성

```sql
CREATE DATABASE beanst;
```

### 3. DB 연결 설정

DB 설정 파일 수정

```
db.properties
```

예시

```
url=jdbc:mariadb://localhost:3306/beanst
username=your_id
password=your_password
```


# 👨‍💻 프로젝트에서의 역할

| 이름 | 역할 |
|---|---|
| 김영훈 | 관리자 페이지 전역 / 프론트 layout 구조 설계 / 메인페이지 구조 설계 |
| 강태준 | DB 설계 전반 담당 및 테이블 간 외래키 관계 정의 / 회원가입 / 로그인 기능 구현 (세션 기반 인증) / 객실 예약 프로세스 설계 및 개발 / 관리자 페이지 회원 관리 기능 개발 |

---

# 💡 프로젝트 특징

- MVC 패턴 기반 웹 애플리케이션
- Front Controller 패턴 구현
- MariaDB 기반 데이터 설계
- 실제 호텔 사이트 UI 구현
- Oracle Cloud 서버 배포

---

# 🔧 개선 사항

- 결제 시스템 연동
- REST API 구조 개선
- Spring Boot 기반 리팩토링
- 모바일 UI 개선

---

# 📄 License

This project is for educational purposes.
