# Controller Analysis

## CustomerController

고객 관리 및 상담 이력 조회 기능을 담당하는 Controller이다.

Spring MVC 환경에서 HTTP 요청을 수신하고, Service 계층을 호출한 뒤 결과를 View 또는 JSON 형태로 반환하는 역할을 수행한다.

---

## 주요 기능

| URL               | Method | 기능          |
| ----------------- | ------ | ----------- |
| /cust             | GET    | 고객 관리 화면 조회 |
| /customerSearch   | GET    | 고객 검색 화면 조회 |
| /searchAjax       | POST   | 고객 검색       |
| /searchAllAjax    | POST   | 전체 고객 조회    |
| /searchOneAjax    | POST   | 고객 상세 조회    |
| /registerCustomer | POST   | 고객 등록       |
| /updateCustomer   | POST   | 고객 수정       |
| /deleteCustomer   | POST   | 고객 삭제       |
| /TextLogAjax      | POST   | 상담 이력 조회    |

---

## 요청 흐름

Browser
↓
CustomerController
↓
CustomerService
↓
CustomerMapper
↓
Database

---

## 특징

### View 반환과 API 역할을 동시에 수행

/customer, /customerSearch 요청은 JSP 화면을 반환한다.

searchAjax, registerCustomer 등의 요청은 JSON 데이터를 반환하는 AJAX API 형태로 동작한다.

### DTO 기반 데이터 전달

CustomerDto와 TextLogDto를 사용하여 계층 간 데이터를 전달한다.

### 입력값 검증 부재

일부 메서드에서 Long.parseLong()을 사용하고 있으나 입력값 검증이나 예외 처리가 존재하지 않는다.

예시:

* searchOneAjax()
* TextLogAjax()

잘못된 형식의 값이 전달될 경우 NumberFormatException이 발생할 수 있다.

---

## 개선 가능 사항

* 입력값 검증 추가
* 예외 처리 추가
* 응답 객체 표준화
* 로깅 체계 개선
* RESTful API 구조 검토
