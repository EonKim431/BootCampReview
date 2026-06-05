# Service Analysis

## CustomerService

CustomerController와 CustomerMapper 사이에서 데이터를 전달하는 Service 계층이다.

---

## 주요 기능

| Method             | 설명       |
| ------------------ | -------- |
| getDTOInfo()       | 상담 이력 조회 |
| list()             | 전체 고객 조회 |
| listFind()         | 고객 검색    |
| searchAdminOne()   | 고객 상세 조회 |
| registerCustomer() | 고객 등록    |
| updateCustomer()   | 고객 수정    |
| deleteCustomer()   | 고객 삭제    |

---

## 데이터 흐름

Controller
↓
CustomerService
↓
CustomerMapper
↓
Database

---

## 분석 결과

현재 Service 계층은 계층 분리 구조는 갖추고 있으나, 대부분의 메서드가 CustomerMapper를 직접 호출하는 형태로 구현되어 있다.

예시:

```java
public List<CustomerDto> list() {
    return customerMapper.list();
}
```

```java
public void deleteCustomer(Long CUST_SN) {
    customerMapper.deleteCustomer(CUST_SN);
}
```

비즈니스 로직, 데이터 검증, 예외 처리 등의 기능은 포함되어 있지 않으며, 실질적으로는 Mapper 호출을 중계하는 Wrapper 역할에 가깝다.

---

## 개선 가능 사항

### 입력값 검증

고객 번호, 검색어 등의 유효성 검증 추가

### 예외 처리

잘못된 요청이나 데이터 처리 실패에 대한 예외 처리 구현

### 로깅 추가

조회, 등록, 수정, 삭제 작업에 대한 로그 기록

### 비즈니스 로직 분리

중복 데이터 검증

고객 상태 검증

등록 정책 적용

등의 로직 추가 가능

---

## 정리

현재 Service 계층은 Controller와 Mapper 사이의 중계 역할을 수행하고 있으며, 향후 비즈니스 로직을 수용할 수 있는 확장 지점으로 활용할 수 있다.
