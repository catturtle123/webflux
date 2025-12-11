# 📗 Spring WebFlux & Multi-LLM Integration Study

## **[소개]**

- **설명**: Spring WebFlux를 활용한 반응형 웹 애플리케이션 개발 및 멀티 LLM(GPT, Gemini) 통합 서비스 구현
- **기간**: 2025.9 

## **[주요 기능]**

### 1. **One-Shot 채팅**
- **엔드포인트**: `POST /chat/oneshot`
- **설명**: LLM에게 단일 요청을 보내고 전체 응답을 한 번에 받음
- **지원 모델**: GPT-4, GPT-3.5-Turbo, Gemini-2.0-Flash

### 2. **스트리밍 채팅**
- **엔드포인트**: `POST /chat/oneshot/stream`
- **설명**: LLM 응답을 실시간 스트리밍으로 받아 사용자 경험 향상
- **기술**: Server-Sent Events(SSE)와 Flux를 활용한 반응형 스트리밍

### 3. **Chain of Thought (CoT) 채팅**
- **엔드포인트**: `POST /chat/cot`
- **설명**: 복잡한 문제를 단계별로 분해하여 추론하는 고급 프롬프트 엔지니어링 기법
- **처리 단계**:
  1. **단계 계획**: 사용자 요청 분석을 위한 단계 생성
  2. **단계별 분석**: 각 단계를 순차적으로 LLM에게 요청
  3. **최종 응답**: 모든 분석 결과를 종합하여 최종 답변 생성

### 4. **Facade API**
- **엔드포인트**: `POST /facade/home`
- **설명**: 사용 가능한 LLM 모델 목록 조회

## **[배운점]**

### **1. 반응형 프로그래밍 패러다임**
- Spring WebFlux 기반 비동기 논블로킹 아키텍처 구축
- **Mono**: 0~1개의 데이터를 비동기로 처리 (`/chat/oneshot`)
- **Flux**: 0~N개의 데이터 스트림을 비동기로 처리 (`/chat/oneshot/stream`, `/chat/cot`)
- WebClient를 통한 비동기 HTTP 통신으로 블로킹 없는 외부 API 호출 구현
- `flatMap`, `flatMapSequential`, `map`, `collectList` 등 리액티브 연산자 활용

### **2. 멀티 LLM 통합 아키텍처 설계**
- **전략 패턴(Strategy Pattern)** 적용으로 여러 LLM 제공자 추상화
  ```java
  public interface LlmWebClientService {
      Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto requestDto);
      Flux<LlmChatResponseDto> getChatCompletionStream(LlmChatRequestDto requestDto);
      LlmType getLlmType();
  }
  ```
- `GptWebClientService`, `GeminiWebClientService` 각각 구현하여 LLM 제공자별 API 차이 처리
- **Map 기반 서비스 선택**: `Map<LlmType, LlmWebClientService>`로 런타임에 동적 LLM 선택
  ```java
  @Bean
  public Map<LlmType, LlmWebClientService> getLlmWebClientServiceMap(
      List<LlmWebClientService> llmWebClientServiceList) {
      return llmWebClientServiceList.stream()
          .collect(Collectors.toMap(
              LlmWebClientService::getLlmType, 
              Function.identity()
          ));
  }
  ```
- 새로운 LLM 추가 시 인터페이스 구현만으로 통합 가능한 **개방-폐쇄 원칙(OCP)** 적용

### **3. Chain of Thought(CoT) 프롬프트 엔지니어링**
- **단계적 추론 로직** 구현으로 복잡한 문제 해결 능력 향상
- **3단계 CoT 파이프라인**:
  1. **단계 계획**: LLM에게 문제 분석에 필요한 단계를 JSON 형식으로 요청
  2. **순차적 분석**: `flatMapSequential`로 각 단계를 순서대로 처리
  3. **결과 종합**: `collectList`로 모든 분석 결과를 모아 최종 답변 생성
- **Flux.create**를 활용한 커스텀 스트림 생성으로 중간 결과를 실시간으로 클라이언트에 전달
- JSON 형식 응답 강제 및 파싱 로직으로 구조화된 데이터 처리

### **4. Facade 패턴을 통한 복잡도 관리**
- `FacadeService`로 여러 서비스 간 협업 로직을 단순한 인터페이스로 제공
- 클라이언트는 복잡한 내부 구현(LLM 선택, CoT 처리 등)을 알 필요 없이 간단한 API 사용
- 비즈니스 로직의 중앙 집중화로 유지보수성 강화

### **5. 에러 처리 및 복원력(Resilience)**
- 리액티브 에러 처리: `onErrorResume`으로 에러 발생 시 대체 응답 제공
- 커스텀 예외 타입(`ErrorTypeException`, `CustomErrorType`)으로 세밀한 에러 분류
- HTTP 에러 상태에 따른 에러 핸들링: `onStatus`로 4xx 에러를 커스텀 예외로 변환
- `getChatCompletionWithCatchException` 메서드로 에러를 데이터로 변환하여 스트림 중단 방지

### **6. 실시간 스트리밍 구현**
- **Server-Sent Events(SSE)** 방식으로 LLM 응답을 청크 단위로 스트리밍
- `Flux<UserChatResponseDto>`를 반환하여 클라이언트가 응답을 실시간으로 수신
- `takeWhile`로 스트림 종료 조건 제어 (GPT의 경우 `finish_reason`이 null일 때까지)
- 비동기 스트리밍으로 첫 토큰까지의 대기 시간(TTFT) 최소화

## **[프로젝트 구조]**

```
src/main/java/com/example/webflux/
├── config/                      # 설정
│   ├── CommonConfig            # LLM 서비스 맵 빈 등록
│   ├── CorsGlobalConfig        # CORS 설정
│   └── WebClientConfig         # WebClient 설정
├── controller/
│   ├── facade/
│   │   └── FacadeController    # 파사드 API 엔드포인트
│   └── user/chat/
│       └── UserChatController  # 채팅 API 엔드포인트
├── service/
│   ├── facade/
│   │   └── FacadeService       # 파사드 비즈니스 로직
│   ├── llmclient/
│   │   ├── LlmWebClientService        # LLM 공통 인터페이스
│   │   ├── GptWebClientService        # OpenAI GPT 구현체
│   │   └── GeminiWebClientService     # Google Gemini 구현체
│   └── user/chat/
│       ├── UserChatService            # 일반 채팅 서비스
│       └── ChainOfThoughtService      # CoT 프롬프트 서비스
├── model/                       # DTO 및 도메인 모델
│   ├── llmclient/
│   │   ├── gpt/                # GPT 요청/응답 DTO
│   │   ├── gemini/             # Gemini 요청/응답 DTO
│   │   └── jsonFormat/         # JSON 포맷 정의
│   └── user/chat/              # 사용자 채팅 DTO
├── exception/                   # 예외 처리
└── util/                        # 유틸리티
```