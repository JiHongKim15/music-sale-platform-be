# 좋아요 도메인 → 상품 도메인 수준 리팩토링 가이드

## 📋 상품 도메인 패턴 분석

### 1. CQRS 패턴 (Command-Query Responsibility Segregation)
상품 도메인은 명령(Command)과 조회(Query)를 **완전히 분리**했습니다.

#### 상품 도메인 구조
```
application/product/
├── port/inport/
│   ├── ProductCommandUseCase.kt  ← 명령 (CUD)
│   └── ProductQueryUseCase.kt    ← 조회 (R)
├── service/
│   ├── ProductCommandService.kt  ← 명령 구현
│   └── ProductQueryService.kt    ← 조회 구현
└── dto/
    ├── CreateProductInput        ← 생성용 Input
    ├── UpdateProductInput        ← 수정용 Input
    ├── SearchProductInput        ← 검색용 Input
    └── ProductOutput             ← 응답용 Output

api/product/
├── ProductCommandController.kt   ← POST, PUT, DELETE
└── ProductQueryController.kt     ← GET
```

#### 좋아요 도메인 현재 구조
```
application/like/
├── port/inport/
│   └── LikeUseCase.java          ← 명령+조회 통합 ❌
├── service/
│   └── LikeService.java          ← 명령+조회 통합 ❌
└── dto/
    ├── LikeOutput                ← Output만 있음
    └── LikeStatusOutput

api/like/
└── LikeController.java           ← POST, DELETE, GET 통합 ❌
```

---

## 🎯 리팩토링 체크리스트 (순서대로 진행)

### 📦 1단계: Input DTO 생성 (가장 쉬움)

#### 📝 작업 파일
- [ ] `music-application/src/main/java/com/music/sale/application/like/dto/AddLikeInput.java` 생성
- [ ] `music-application/src/main/java/com/music/sale/application/like/dto/GetMyLikesInput.java` 생성

#### 🔍 참고할 파일
- 상품: `CreateProductInput.kt` (line 37-48)
- 상품: `SearchProductInput.kt` (line 65-75)

#### 📌 체크 포인트
1. `AddLikeInput`에는 무엇이 필요한가?
   - `userId` (Long)
   - `likeableId` (Long)
   - `likeableType` (LikeableType)

2. `GetMyLikesInput`에는 무엇이 필요한가?
   - `userId` (Long)
   - `likeableType` (LikeableType)
   - `page` (int)
   - `size` (int)

#### ✅ 완료 확인
```bash
# DTO 파일 생성 확인
ls -l music-application/src/main/java/com/music/sale/application/like/dto/

# 3개 이상의 파일이 있어야 함
# AddLikeInput.java
# GetMyLikesInput.java
# LikeOutput.java
# LikeStatusOutput.java
```

---

### 📦 2단계: UseCase 분리 (Command/Query)

#### 📝 작업 파일
- [ ] `LikeCommandUseCase.java` 생성
- [ ] `LikeQueryUseCase.java` 생성
- [ ] 기존 `LikeUseCase.java` 삭제 또는 deprecated

#### 🔍 참고할 파일
- 상품 Command: `ProductCommandUseCase.kt` (line 7-13)
  ```kotlin
  interface ProductCommandUseCase {
      fun createProduct(input: CreateProductInput): ProductOutput
      fun updateProduct(input: UpdateProductInput): ProductOutput
      fun deleteProduct(id: Long): ProductOutput
  }
  ```

- 상품 Query: `ProductQueryUseCase.kt` (line 8-17)
  ```kotlin
  interface ProductQueryUseCase {
      fun getProducts(pageable: PageRequest): Page<ProductOutput>
      fun getProductById(id: Long): ProductOutput?
      fun searchProducts(input: SearchProductInput, pageable: PageRequest): Page<ProductOutput>
  }
  ```

#### 📌 체크 포인트
**LikeCommandUseCase**에 들어갈 메서드:
- `LikeOutput addLike(AddLikeInput input)`
- `void deleteLike(Long userId, Long likeableId, LikeableType likeableType)`

**LikeQueryUseCase**에 들어갈 메서드:
- `LikeStatusOutput getLikeStatus(Long userId, Long likeableId, LikeableType likeableType)`
- `Page<Object> getMyLikes(GetMyLikesInput input)`

#### ⚠️ 주의사항
- Command는 **@Transactional** 필요
- Query는 **@Transactional(readOnly = true)** 필요
- 각 UseCase는 **독립적인 인터페이스**

#### ✅ 완료 확인
```bash
# UseCase 파일 확인
ls -l music-application/src/main/java/com/music/sale/application/like/port/inport/

# 2개 파일이 있어야 함
# LikeCommandUseCase.java
# LikeQueryUseCase.java
```

---

### 📦 3단계: Service 분리

#### 📝 작업 파일
- [ ] `LikeCommandService.java` 생성
- [ ] `LikeQueryService.java` 생성
- [ ] 기존 `LikeService.java`에서 메서드 이동
- [ ] 기존 `LikeService.java` 삭제

#### 🔍 참고할 파일
- 상품 Command: `ProductCommandService.kt` (line 16-64)
- 상품 Query: `ProductQueryService.kt`

#### 📌 체크 포인트
**LikeCommandService.java**
```java
@Service
@Transactional
public class LikeCommandService implements LikeCommandUseCase {
    private final LikePort likePort;
    private final LikeMapper likeMapper;
    
    // Constructor injection
    
    @Override
    public LikeOutput addLike(AddLikeInput input) {
        // 기존 LikeService.addLike 로직 복사
    }
    
    @Override
    public void deleteLike(Long userId, Long likeableId, LikeableType likeableType) {
        // 기존 LikeService.deleteLike 로직 복사
    }
}
```

**LikeQueryService.java**
```java
@Service
@Transactional(readOnly = true)  // ← 읽기 전용!
public class LikeQueryService implements LikeQueryUseCase {
    private final LikePort likePort;
    
    // Constructor injection
    
    @Override
    public LikeStatusOutput getLikeStatus(Long userId, Long likeableId, LikeableType likeableType) {
        // 기존 LikeService.getLikeStatus 로직 복사
    }
    
    @Override
    public Page<Object> getMyLikes(GetMyLikesInput input) {
        // 기존 LikeService.getMyLikes 로직 복사
    }
}
```

#### ⚠️ 주의사항
1. **메서드 3줄 룰** 유지
2. 기존 `LikeService`의 private 메서드들도 그대로 복사
3. `@Transactional` vs `@Transactional(readOnly = true)` 구분

#### ✅ 완료 확인
```bash
# Service 파일 확인
ls -l music-application/src/main/java/com/music/sale/application/like/service/

# 2개 파일이 있어야 함
# LikeCommandService.java
# LikeQueryService.java

# 컴파일 확인
./gradlew :music-application:compileJava
```

---

### 📦 4단계: Controller 분리

#### 📝 작업 파일
- [ ] `LikeCommandController.java` 생성
- [ ] `LikeQueryController.java` 생성
- [ ] 기존 `LikeController.java`에서 메서드 이동
- [ ] 기존 `LikeController.java` 삭제

#### 🔍 참고할 파일
- 상품 Command: `ProductCommandController.kt` (line 14-56)
- 상품 Query: `ProductQueryController.kt`

#### 📌 체크 포인트
**LikeCommandController.java** (POST, DELETE)
```java
@RestController
@RequestMapping("/api/v1")
public class LikeCommandController {
    private final LikeCommandUseCase commandUseCase;
    private final LikeWebMapper mapper;
    
    // POST /api/v1/products/{productId}/likes
    // DELETE /api/v1/products/{productId}/likes
    // POST /api/v1/stores/{storeId}/likes
    // DELETE /api/v1/stores/{storeId}/likes
    // POST /api/v1/sellers/{sellerId}/likes
    // DELETE /api/v1/sellers/{sellerId}/likes
}
```

**LikeQueryController.java** (GET)
```java
@RestController
@RequestMapping("/api/v1")
public class LikeQueryController {
    private final LikeQueryUseCase queryUseCase;
    private final LikeWebMapper mapper;
    
    // GET /api/v1/products/{productId}/likes/status
    // GET /api/v1/users/me/likes/products
    // GET /api/v1/stores/{storeId}/likes/status
    // GET /api/v1/users/me/likes/stores
    // GET /api/v1/sellers/{sellerId}/likes/status
    // GET /api/v1/users/me/likes/sellers
}
```

#### ⚠️ 주의사항
1. Command Controller → `LikeCommandUseCase` 주입
2. Query Controller → `LikeQueryUseCase` 주입
3. 메서드 3줄 룰 유지
4. `@RequestMapping` 경로 동일하게 유지

#### ✅ 완료 확인
```bash
# Controller 파일 확인
ls -l music-api/src/main/java/com/music/sale/web/like/

# 2개 Controller가 있어야 함
# LikeCommandController.java
# LikeQueryController.java

# 컴파일 확인
./gradlew :music-api:compileJava

# API 엔드포인트 개수 확인 (12개)
grep -E "@(Post|Get|Delete)Mapping" music-api/src/main/java/com/music/sale/web/like/*.java | wc -l
```

---

### 📦 5단계: 테스트 코드 작성

#### 📝 작업 파일
- [ ] `LikeCommandServiceTest.java` (기존 LikeServiceTest에서 분리)
- [ ] `LikeQueryServiceTest.java` (기존 LikeServiceTest에서 분리)
- [ ] `LikePersistenceAdapterTest.java` (통합 테스트 - 새로 작성)
- [ ] `LikeCommandControllerTest.java` (Controller 테스트 - 새로 작성)
- [ ] `LikeQueryControllerTest.java` (Controller 테스트 - 새로 작성)

#### 🔍 참고할 파일
- 기존: `LikeServiceTest.java` (단위 테스트 예시)
- Spring Boot Test 문서

#### 📌 체크 포인트
**통합 테스트 작성 예시**
```java
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LikePersistenceAdapterTest {
    @Autowired
    private LikeRepository likeRepository;
    
    private LikePersistenceAdapter adapter;
    
    @BeforeEach
    void setUp() {
        adapter = new LikePersistenceAdapter(likeRepository);
    }
    
    @Test
    @DisplayName("좋아요 저장 후 조회 - 통합 테스트")
    void save_and_find() {
        // Given
        Like like = Like.create(1L, 100L, LikeableType.PRODUCT);
        
        // When
        Like saved = adapter.save(like);
        
        // Then
        assertThat(saved.getId()).isNotNull();
        assertThat(adapter.exists(1L, 100L, LikeableType.PRODUCT)).isTrue();
    }
}
```

**Controller 테스트 작성 예시**
```java
@WebMvcTest(LikeCommandController.class)
class LikeCommandControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private LikeCommandUseCase commandUseCase;
    
    @MockBean
    private LikeWebMapper mapper;
    
    @Test
    @DisplayName("상품 찜하기 API - 성공")
    void likeProduct_Success() throws Exception {
        // Given
        AddLikeInput input = new AddLikeInput(1L, 100L, LikeableType.PRODUCT);
        LikeOutput output = new LikeOutput(1L, 1L, 100L, LikeableType.PRODUCT, LocalDateTime.now());
        given(commandUseCase.addLike(any())).willReturn(output);
        
        // When & Then
        mockMvc.perform(post("/api/v1/products/100/likes")
                .header("X-User-Id", 1L))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("LIKE_CREATED"));
    }
}
```

#### ⚠️ 주의사항
1. 단위 테스트: Mockito로 의존성 모킹
2. 통합 테스트: 실제 DB 사용 (H2 또는 TestContainers)
3. Controller 테스트: MockMvc 사용

#### ✅ 완료 확인
```bash
# 테스트 파일 확인
find music-application/src/test -name "*Like*Test.java"

# 테스트 실행 (전체)
./gradlew :music-application:test
./gradlew :music-api:test

# 테스트 커버리지 확인 (선택)
./gradlew jacocoTestReport
```

---

## 📊 리팩토링 진행 상황 체크

### 완료 여부 확인
```bash
# 1. Input DTO 개수 (최소 2개)
find music-application/src/main/java/com/music/sale/application/like/dto -name "*Input.java" | wc -l

# 2. UseCase 개수 (2개)
find music-application/src/main/java/com/music/sale/application/like/port/inport -name "*UseCase.java" | wc -l

# 3. Service 개수 (2개)
find music-application/src/main/java/com/music/sale/application/like/service -name "*Service.java" | wc -l

# 4. Controller 개수 (2개)
find music-api/src/main/java/com/music/sale/web/like -name "*Controller.java" | wc -l

# 5. Test 개수 (최소 5개)
find music-application/src/test -name "*Like*Test.java" | wc -l
```

### 컴파일 확인
```bash
# 전체 빌드
./gradlew clean build

# 모듈별 컴파일
./gradlew :music-domain:compileJava
./gradlew :music-application:compileJava
./gradlew :music-infrastructure:compileJava
./gradlew :music-api:compileJava
```

### 테스트 확인
```bash
# 전체 테스트
./gradlew test

# 좋아요 도메인만
./gradlew test --tests "*Like*"
```

---

## 📝 어제한일 / 오늘할일 / Blocker 템플릿

### 어제 한 일 (Yesterday)
```
1. ✅ Likes 도메인 기본 구조 구축 완료
   - Domain Layer: Like.java, LikeableType.java
   - Application Layer: LikeUseCase, LikeService, LikePort
   - Infrastructure Layer: LikeEntity, LikeRepository, LikePersistenceAdapter
   - API Layer: LikeController (12개 엔드포인트)

2. ✅ DB 스키마 구축 및 Docker MySQL 연동
   - V1__create_likes_table.sql 작성
   - Unique 제약조건 및 Index 설정
   - Docker Compose 설정 완료

3. ✅ 단위 테스트 작성
   - LikeServiceTest (12개 테스트 케이스)
   - Mockito를 사용한 의존성 모킹

4. ✅ Clean Code 원칙 적용
   - 메서드 3줄 이하 규칙 준수
   - DDD 원칙에 따른 의존성 분리

5. ✅ README 및 온보딩 가이드 작성
   - 팀원 온보딩 가이드 추가
   - .env.example 템플릿 생성
```

### 오늘 할 일 (Today)
```
1. 🎯 CQRS 패턴 적용 (상품 도메인 수준)
   - [ ] Input DTO 생성 (AddLikeInput, GetMyLikesInput)
   - [ ] UseCase 분리 (LikeCommandUseCase, LikeQueryUseCase)
   - [ ] Service 분리 (LikeCommandService, LikeQueryService)
   - [ ] Controller 분리 (LikeCommandController, LikeQueryController)

2. 🧪 테스트 코드 보완
   - [ ] 통합 테스트 작성 (LikePersistenceAdapterTest)
   - [ ] Controller 테스트 작성 (LikeCommandControllerTest, LikeQueryControllerTest)
   - [ ] 테스트 커버리지 80% 이상 달성

3. 📝 코드 리뷰 및 정리
   - [ ] 메서드 3줄 룰 재확인
   - [ ] DDD 원칙 준수 확인
   - [ ] Import 정리 및 코드 포맷팅

4. 🚀 Git Push
   - [ ] develop 브랜치에 Push
   - [ ] PR 생성 및 팀원 리뷰 요청
```

### Blocker (차단 이슈)
```
현재 차단 이슈: 없음

[만약 있다면 예시]
1. ❌ MySQL 포트 충돌 문제
   - 문제: 로컬 MySQL이 3306 포트 사용 중
   - 해결 방안: Docker MySQL을 3307로 변경 또는 로컬 MySQL 종료
   - 담당자: 본인
   - 예상 해결 시간: 30분

2. ⚠️ 통합 테스트 환경 미구축
   - 문제: TestContainers 설정 필요
   - 해결 방안: build.gradle.kts에 의존성 추가
   - 담당자: 본인
   - 예상 해결 시간: 1시간
```

---

## 🎓 학습 포인트

### CQRS 패턴을 적용하는 이유
1. **책임 분리**: 명령과 조회의 책임을 명확히 구분
2. **성능 최적화**: Query는 readOnly 트랜잭션으로 성능 향상
3. **확장성**: Command와 Query를 독립적으로 확장 가능
4. **유지보수**: 각 서비스의 역할이 명확하여 코드 이해가 쉬움

### 상품 도메인과의 차이점
| 항목 | 상품 도메인 | 좋아요 도메인 (리팩토링 전) | 좋아요 도메인 (리팩토링 후) |
|------|------------|--------------------------|--------------------------|
| CQRS | ✅ 적용 | ❌ 미적용 | ✅ 적용 |
| Input DTO | ✅ 있음 | ❌ 없음 | ✅ 있음 |
| UseCase 분리 | ✅ 분리 | ❌ 통합 | ✅ 분리 |
| Service 분리 | ✅ 분리 | ❌ 통합 | ✅ 분리 |
| Controller 분리 | ✅ 분리 | ❌ 통합 | ✅ 분리 |
| 테스트 | ❌ 없음 | ✅ 단위 테스트 | ✅ 단위+통합+Controller |

---

## ⚠️ 주의사항

### 리팩토링 순서 준수
1. **Input DTO 먼저** → 인터페이스 시그니처 변경 최소화
2. **UseCase 분리** → Service가 구현할 인터페이스 확정
3. **Service 분리** → Controller가 주입받을 Service 확정
4. **Controller 분리** → API 엔드포인트 변경 없음 확인
5. **테스트 작성** → 마지막에 검증

### 실수하기 쉬운 부분
1. ❌ Service를 먼저 분리하면 UseCase 인터페이스 변경이 복잡해짐
2. ❌ Controller를 먼저 분리하면 Service 주입이 꼬임
3. ❌ `@Transactional(readOnly = true)`를 빼먹으면 성능 저하
4. ❌ 메서드 3줄 룰을 지키지 않으면 코드 리뷰 거부

### 컴파일 오류 해결
```bash
# 오류 발생 시
./gradlew clean
./gradlew :music-application:compileJava --stacktrace
./gradlew :music-api:compileJava --stacktrace

# Import 정리
# IntelliJ: Ctrl + Alt + O (macOS: Cmd + Option + O)
```

---

## 🎉 완료 기준

### 리팩토링 완료 조건
- [ ] 모든 파일이 컴파일 됨 (`./gradlew build` 성공)
- [ ] 모든 테스트가 통과 (`./gradlew test` 성공)
- [ ] API 엔드포인트 12개 모두 정상 동작 (Postman 테스트)
- [ ] 메서드 3줄 룰 준수
- [ ] DDD 원칙 준수 (Domain Layer에 외부 의존성 없음)
- [ ] 테스트 커버리지 80% 이상

### 최종 확인
```bash
# 1. 빌드 성공
./gradlew clean build

# 2. 테스트 성공
./gradlew test

# 3. 애플리케이션 실행
./gradlew :music-api:bootRun --args='--spring.profiles.active=local'

# 4. API 테스트 (별도 터미널)
curl -X POST http://localhost:8080/api/v1/products/1/likes -H "X-User-Id: 1"
curl -X GET http://localhost:8080/api/v1/products/1/likes/status -H "X-User-Id: 1"
```

---

## 📞 도움이 필요한 경우

### 막힐 때 확인할 것
1. 컴파일 오류: `./gradlew :모듈명:compileJava --stacktrace`
2. 테스트 실패: `./gradlew test --info`
3. Import 오류: IntelliJ에서 "Optimize Imports" 실행
4. 의존성 오류: `./gradlew dependencies`

### 질문 템플릿
```
[문제 상황]
- 작업 단계: (예: 2단계 UseCase 분리 중)
- 오류 메시지: (전체 오류 메시지 복사)
- 시도한 해결 방법: (무엇을 시도했는지)

[코드]
// 문제가 발생한 코드 붙여넣기
```

---

끝까지 포기하지 말고 **단계별로 천천히** 진행하세요! 🚀



