# 도메인별 언어 구분표

이 프로젝트는 Java와 Kotlin을 혼용하여 개발됩니다. 각 도메인별로 사용하는 언어를 명확히 구분합니다.

## 🟡 Java 도메인

### Store (상점)
- **위치**: `music-domain/src/main/java/com/music/sale/domain/store/`
- **주요 클래스**: `Store.java`
- **Port**: `music-application/src/main/java/com/music/sale/application/store/`
- **Infrastructure**: `music-infrastructure/src/main/java/com/music/sale/persistence/store/`
- **특징**: 새로운 기능 개발 시 Java로 구현

## 🟦 Kotlin 도메인

### Product (상품)
- **위치**: `music-domain/src/main/kotlin/com/music/sale/domain/product/`
- **주요 클래스**: `Product.kt`, `ProductCondition.kt` 등

### User (사용자)
- **위치**: `music-domain/src/main/kotlin/com/music/sale/domain/user/`
- **주요 클래스**: `User.kt`, `UserRole.kt`, `Gender.kt` 등

### Category (카테고리)
- **위치**: `music-domain/src/main/kotlin/com/music/sale/domain/category/`
- **주요 클래스**: `Category.kt`

### Order (주문)
- **위치**: `music-domain/src/main/kotlin/com/music/sale/domain/order/`
- **주요 클래스**: `Order.kt`, `Payment.kt`, `Shipping.kt` 등

### Cart (장바구니)
- **위치**: `music-domain/src/main/kotlin/com/music/sale/domain/cart/`
- **주요 클래스**: `Cart.kt`

### Wishlist (위시리스트)
- **위치**: `music-domain/src/main/kotlin/com/music/sale/domain/wishlist/`
- **주요 클래스**: `Wishlist.kt`

### Shipping (배송)
- **위치**: `music-domain/src/main/kotlin/com/music/sale/domain/shipping/`
- **주요 클래스**: `ShippingPolicy.kt`, `ShippingInfo.kt`

### ViewCount (조회수)
- **위치**: `music-domain/src/main/kotlin/com/music/sale/domain/viewcount/`
- **주요 클래스**: `ViewCount.kt`

### Search (검색)
- **위치**: `music-domain/src/main/kotlin/com/music/sale/domain/search/`

### Shop (샵)
- **위치**: `music-domain/src/main/kotlin/com/music/sale/domain/shop/`
- **주요 클래스**: `Shop.kt`

## 🔧 공통 인프라

### BaseEntity
- **Java**: `music-infrastructure/src/main/java/com/music/sale/persistence/common/BaseEntity.java`
- **설명**: QueryDSL 호환성을 위해 Java로 변환됨

## 📋 개발 가이드라인

### 새로운 도메인 추가 시
1. **Java 도메인**: `music-domain/src/main/java/com/music/sale/domain/{domain}/` 에 생성
2. **Kotlin 도메인**: `music-domain/src/main/kotlin/com/music/sale/domain/{domain}/` 에 생성

### 기존 도메인 수정 시
1. **각 도메인의 현재 언어 확인** (이 문서 참조)
2. **해당 언어로 개발 진행**
3. **언어 변경이 필요한 경우 팀 논의 후 결정**

### 상호 운용성
- Java에서 Kotlin 클래스 호출 가능
- Kotlin에서 Java 클래스 호출 가능
- Named argument는 Java 클래스에서 사용 불가

## 🗂️ 빠른 참조

**Java 도메인을 찾으려면:**
```bash
find . -name "*.java" -path "*/domain/*"
```

**Kotlin 도메인을 찾으려면:**
```bash
find . -name "*.kt" -path "*/domain/*"
```

**특정 도메인의 언어 확인:**
```bash
# Store 도메인 확인 (Java)
ls music-domain/src/main/java/com/music/sale/domain/store/

# Product 도메인 확인 (Kotlin)
ls music-domain/src/main/kotlin/com/music/sale/domain/product/
```

---
📅 **마지막 업데이트**: 2025-10-09
🔄 **다음 업데이트**: 새로운 도메인 추가 또는 언어 변경 시