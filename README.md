# LetsGo ✈️ — 여행 일정 계획·공유 플랫폼 (3차 스프린트)

> 여행 일정을 만들고 동선·예산·할일을 관리하며, 동반자와 공유하거나 게시판에 공개할 수 있는 웹 서비스.
> **2차 스프린트의 세션 기반 SSR 구조를 프론트엔드/백엔드가 분리된 REST API 아키텍처로 전환**하고,
> **React(SPA) 프론트엔드**, **JWT 무상태(Stateless) 인증 + Google OAuth2 소셜 로그인**, **Swagger(OpenAPI) 문서화**를 도입한 3차 스프린트 프로젝트입니다.

<p>
  <img src="https://img.shields.io/badge/Java-17-007396?logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5-6DB33F?logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/Spring%20Security-6-6DB33F?logo=springsecurity&logoColor=white">
  <img src="https://img.shields.io/badge/JWT-java--jwt-000000?logo=jsonwebtokens&logoColor=white">
  <img src="https://img.shields.io/badge/OAuth2-Google-4285F4?logo=google&logoColor=white">
  <img src="https://img.shields.io/badge/JPA-Hibernate-59666C?logo=hibernate&logoColor=white">
  <img src="https://img.shields.io/badge/MyBatis-PageHelper-red">
  <img src="https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?logo=swagger&logoColor=black">
  <img src="https://img.shields.io/badge/MariaDB-003545?logo=mariadb&logoColor=white">
  <img src="https://img.shields.io/badge/Docker-EC2-2496ED?logo=docker&logoColor=white">
</p>
<p>
  <img src="https://img.shields.io/badge/React-19-61DAFB?logo=react&logoColor=black">
  <img src="https://img.shields.io/badge/TypeScript-5-3178C6?logo=typescript&logoColor=white">
  <img src="https://img.shields.io/badge/Vite-8-646CFF?logo=vite&logoColor=white">
  <img src="https://img.shields.io/badge/TailwindCSS-4-06B6D4?logo=tailwindcss&logoColor=white">
  <img src="https://img.shields.io/badge/Zustand-state-764ABC">
  <img src="https://img.shields.io/badge/Axios-HTTP-5A29E4?logo=axios&logoColor=white">
</p>

---

## 📌 프로젝트 개요

| 항목 | 내용 |
|------|------|
| **개발 기간** | 3차 스프린트 · **2026.06.23 ~ 2026.07.03** (약 2주) |
| **개발 형태** | 팀 프로젝트 · 애자일(스프린트) 기반 · GitHub Flow 협업 |
| **진행 방식** | **1차** Servlet/JSP → **2차** Spring Boot 리팩토링 + AI 챗봇 → **3차** FE/BE 분리 + React SPA + JWT/OAuth2 |
| **핵심 목표** | 세션 기반 SSR → **FE/BE 분리 REST API** 전환 · **React SPA** · **JWT 인증** · **소셜 로그인(OAuth2)** · **API 문서 자동화(Swagger)** |
| **1차 스프린트** | https://github.com/sinee0601/letsgo-1st |
| **2차 스프린트** | https://github.com/sinee0601/letsgo-2nd |
| **프론트엔드(React)** | https://github.com/CloudKosta/letsgo-react-app |

---

## 🎯 3차 스프린트에서 달라진 점

> 2차의 **세션·서버 렌더링(SSR) 중심** 구조에서, 프론트엔드와 백엔드를 분리할 수 있는 **REST API + 토큰 인증** 구조로 진화시켰습니다.

| 구분 | 2차 스프린트 | 3차 스프린트 |
|------|-------------|-------------|
| **아키텍처** | SSR 단일 서버 | **FE/BE 분리** (React SPA ↔ REST API) |
| **프론트엔드** | Thymeleaf (SSR) | **React 19 + TypeScript + Vite** SPA |
| **인증 방식** | 세션(Session) + Form Login | **JWT(무상태) + OAuth2 소셜 로그인** |
| **세션 정책** | Stateful (동시 세션 제어) | **`SessionCreationPolicy.STATELESS`** |
| **API 형태** | 화면(Thymeleaf) 위주 | 도메인별 **`RestController`(JSON API)** 병행 |
| **로그인 수단** | 자체 회원가입/로그인 | 자체 로그인 **+ Google OAuth2** |
| **API 문서** | 없음 | **Swagger UI / OpenAPI 3** 자동 문서화 |
| **CORS** | 불필요(동일 출처) | **CORS 설정**으로 별도 출처 프론트엔드 연동 |

---

## 🏗️ 시스템 아키텍처

![LetsGo 3차 스프린트 아키텍처](LetsGO_3차_스프린트.drawio.png)

전체 시스템은 **React 기반 프론트엔드(SPA)** 와 **REST API를 제공하는 Spring Boot 백엔드**로 분리되며,
백엔드는 **AWS EC2 위 Docker 컨테이너**로 배포되고 **MariaDB**를 사용합니다.
프론트엔드는 `axios`로 백엔드 REST API를 호출하고, 발급받은 **JWT를 헤더에 실어** 인증합니다.

### 🖥️ 프론트엔드 (React SPA)

| 구성 | 기술 | 역할 |
|------|------|------|
| **UI / 라우팅** | React 19 · React Router 7 | 화면 구성 · 클라이언트 사이드 라우팅(SPA) |
| **상태 관리** | Zustand | 전역 상태(로그인/사용자/일정 등) 관리 |
| **HTTP** | Axios | REST API 통신 · JWT 토큰 헤더 주입 |
| **스타일** | TailwindCSS 4 | 유틸리티 기반 스타일링 |
| **에디터** | Tiptap | 게시글 리치 텍스트 작성 |
| **빌드** | Vite (dev 포트 `5432`) | 개발 서버 · 번들링 |

> 프론트엔드 저장소: **https://github.com/CloudKosta/letsgo-react-app**

### 🔐 인증 흐름 (JWT + OAuth2)

```
[자체 로그인]
  Client ──(ID/PW)──▶ JwtAuthenticationFilter ──▶ 인증 성공 시 JWT 발급
  Client ──(JWT in Header)──▶ JwtAuthorization ──▶ 요청별 토큰 검증 → 인가

[Google 소셜 로그인]
  Client ──▶ /oauth2/authorization/google ──▶ Google 인증
         ──▶ OAuth2SuccessHandler ──▶ 회원 조회/생성(findOrCreateOAuthUser)
         ──▶ JWT 발급 ──▶ redirect-uri(프론트)로 token 전달
```

### ⚙️ Spring Boot 계층 구조

| 계층 | 구성 요소 | 역할 |
|------|-----------|------|
| **Security** | `SecurityConfig` · `JwtAuthenticationFilter` · `JwtAuthorization` · `OAuth2SuccessHandler` · `CorsConfig` | 무상태(STATELESS) 인증 · JWT 발급/검증 · 소셜 로그인 · CORS |
| **Presentation** | `@Controller`(Thymeleaf) / `@RestController`(JSON) / `@(Rest)ControllerAdvice` | 화면 반환 · REST API · 예외 처리 분리 |
| **Service** | 도메인 서비스 | `@Transactional` · 권한 검증 · PageHelper/PageInfo 페이징 |
| **Persistence** | Spring Data JPA · MyBatis Mapper | JPA는 단순 CRUD, MyBatis는 동적 조회·성능 튜닝으로 **역할 분리** |
| **Docs** | `OpenApiConfig` (springdoc) | Swagger UI · OpenAPI 3 명세 자동 생성 |

### 🌍 외부 연동

| API | 용도 |
|-----|------|
| **Google OAuth2** | 소셜 로그인 및 사용자 프로필(email, name) 취득 |
| **한국관광콘텐츠랩 (TourAPI)** | 관광지·숙박·음식점 등 공공 관광 데이터 수집 |
| **NaverMap API** | 지도 렌더링 및 장소 시각화 |
| **도로명주소 API** | 주소 검색·정규화 |
| **AI 챗봇 (FastAPI/Gemini)** | 여행 관련 자연어 질의응답 (프론트 `chatBot` 화면 연동) |

---

## 🧩 도메인 구성 (Domain-Driven Packaging)

> 기술 계층이 아닌 **도메인 단위로 패키지 경계**를 그어, 변경의 영향 범위를 한 도메인으로 응집시켰습니다.
> 각 도메인은 `controller(View + Rest) · service · repository · vo` 로 계층을 분리하며, 3차에서는 도메인마다 **REST Controller를 추가**했습니다.

| 도메인 | 설명 |
|--------|------|
| 👤 **user** | 회원 가입·로그인·인증, 아이디/비밀번호 찾기, OAuth2 회원 처리 |
| 🔐 **auth** | JWT 발급/검증 · Spring Security 설정 · OAuth2 성공 핸들러 · CORS |
| 🗓️ **myschedule** | 나의 여행 일정 · 동선/예산/할일 관리 · 동반자 공유 및 리소스 인가 |
| 📢 **postschedule** | 일정 공유 게시판 · 좋아요 · 신고 |
| 📍 **place** | 장소 조회 (관광/숙박/음식점) · 페이지네이션 |
| 🌍 **tourapi** | 한국관광콘텐츠랩 공공데이터 수집·적재 |
| 🛠️ **admin** | 신고 관리 · 콘텐츠 관리 |
| 🧰 **common** | 공통 응답(`PageResponse`) · OpenAPI 설정 |

---

## 🛠️ 기술 스택

| 구분 | 기술 |
|------|------|
| **Language** | Java 17, TypeScript |
| **Frontend** | React 19, React Router 7, Zustand, Axios, TailwindCSS 4, Tiptap, Vite |
| **Backend** | Spring Boot 3.5, Spring MVC, Spring Security 6 |
| **Auth** | JWT (auth0 java-jwt), Spring Security OAuth2 Client (Google) |
| **Persistence** | Spring Data JPA (Hibernate), MyBatis, PageHelper |
| **API Docs** | springdoc-openapi (Swagger UI) |
| **Database** | MariaDB |
| **외부 API / AI** | Google OAuth2, TourAPI, NaverMap API, 도로명주소 API, Gemini(챗봇) |
| **Infra** | AWS EC2, Docker |
| **Build / Tool** | Maven(백엔드), Vite/npm(프론트), Git/GitHub |

---

## ✨ 주요 구현 포인트

- **무상태(Stateless) JWT 인증** — `SessionCreationPolicy.STATELESS`로 세션을 제거하고, `JwtAuthenticationFilter`(인증)·`JwtAuthorization`(인가) 필터로 요청마다 토큰을 검증하도록 전환
- **Google OAuth2 소셜 로그인** — `OAuth2SuccessHandler`에서 사용자 조회/자동 생성 후 JWT를 발급하고, 프론트엔드 `redirect-uri`로 토큰을 전달하는 소셜 로그인 플로우 구현
- **REST API 병행 제공** — 도메인별 `RestController`를 추가해 JSON 기반 API를 제공, 프론트엔드 분리 및 클라이언트 연동 기반 마련
- **Swagger(OpenAPI 3) 자동 문서화** — `OpenApiConfig` + springdoc로 API 명세를 자동 생성하여 협업·테스트 효율 향상 (`/swagger-ui.html`)
- **CORS 지원** — `CorsConfig`를 필터 체인에 등록해 별도 출처의 프론트엔드에서 API 호출 가능
- **JPA + MyBatis 하이브리드 영속성** — 단순 CRUD는 JPA로 생산성을, 복잡한 동적 조회·페이징은 MyBatis + PageHelper로 성능을 확보하도록 **저장소 전략 이원화**(2차부터 계승)
- **리소스 단위 인가** — 공유받은 일정 접근 시 소유자/공유자 권한을 검증하여 타인의 일정 무단 조회를 차단

---

## 🚀 실행 방법

### 1) 백엔드 (Spring Boot · 포트 5531)

```bash
git clone https://github.com/sinee0601/letsgo-3rd-sprint-springboot.git
cd letsgo-3rd-sprint-springboot

# 환경 변수 설정 (application.yml 참고)
#   - DB_URL / DB_USERNAME / DB_PASSWORD       (MariaDB 접속 정보)
#   - GOOGLE_CLIENT_ID / GOOGLE_CLIENT_SECRET  (Google OAuth2)
#   - SERVICE_KEY                              (TourAPI 서비스 키)

# 빌드 & 실행
./mvnw spring-boot:run     # Windows: mvnw.cmd spring-boot:run
```

- **Swagger UI**: `http://localhost:5531/swagger-ui.html`
- **OpenAPI 명세**: `http://localhost:5531/api-docs`
- DB 스키마 및 더미 데이터는 [`SQL/`](SQL/) 디렉터리의 `MariaDB_DDL.sql`, `DummyData.sql`(또는 `NewDummy.sql`) 참고.

### 2) 프론트엔드 (React · 포트 5432)

```bash
git clone https://github.com/CloudKosta/letsgo-react-app.git
cd letsgo-react-app

npm install
npm run dev     # http://127.0.0.1:5432
```

> 백엔드의 OAuth2 `redirect-uri`는 프론트엔드의 `http://127.0.0.1:5432/oauth/callback`로 설정되어 있습니다.

---

## 📂 프로젝트 구조

### 백엔드 (Spring Boot)

```
letsgo-3rd-sprint-springboot
├─ src/main/java/com/travel/letsgospringboot
│  ├─ auth           # JWT · Spring Security · OAuth2 · CORS
│  ├─ user           # 회원·인증·소셜 회원 처리
│  ├─ myschedule     # 나의 일정·공유 (View + Rest)
│  ├─ postschedule   # 공유 게시판·신고 (View + Rest)
│  ├─ place          # 장소 조회 (View + Rest)
│  ├─ tourapi        # 관광 공공데이터 연동
│  ├─ admin          # 신고·관리 (View + Rest)
│  ├─ advice         # 전역 예외 처리
│  ├─ common         # 공통 응답 · OpenAPI 설정
│  └─ exception      # 커스텀 예외
├─ src/main/resources
│  ├─ mappers        # MyBatis Mapper XML
│  ├─ templates/html # Thymeleaf 뷰
│  └─ static         # CSS · JS · 이미지
├─ SQL               # DDL · 더미 데이터 · 쿼리
└─ GIT_COMMIT_CONVENTION.md   # 커밋 메시지 컨벤션
```

### 프론트엔드 (React · 별도 저장소)

```
letsgo-react-app
├─ src
│  ├─ api            # Axios 기반 백엔드 REST API 호출
│  ├─ components     # 공통/레이아웃/공유 컴포넌트
│  ├─ screens        # 화면 단위 (User · mySchedule · postSchedule · place · cart · chatBot)
│  ├─ store          # Zustand 전역 상태
│  ├─ hooks          # 커스텀 훅
│  ├─ types          # TypeScript 타입 정의
│  └─ utils          # 공통 유틸
├─ public
└─ vite.config.ts    # Vite 설정 (dev 포트 5432)
```

---

## 📝 커밋 컨벤션

`feat` · `fix` · `docs` · `style` · `refactor` 5가지 타입을 사용합니다.
자세한 규칙은 [`GIT_COMMIT_CONVENTION.md`](GIT_COMMIT_CONVENTION.md) 참고.
