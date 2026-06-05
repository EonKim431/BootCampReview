# Database Analysis

## 주요 테이블

### KDT_CUST_INFO_BASC

고객 기본 정보를 저장하는 테이블

주요 컬럼

* CUST_SN
* CUST_NM
* EML_ADDR
* HOME_TELNO
* MBL_TELNO
* PRIDTF_NO
* ROAD_NM_ADDR
* BRDT

---

### TEXTLOG

고객 상담 이력을 저장하는 테이블

주요 컬럼

* cus_nm
* man_nm
* date
* hour
* content

---

## 테이블 관계

KDT_CUST_INFO_BASC
↓
고객명(cust_nm)
↓
TEXTLOG

현재 상담 이력은 고객명을 기준으로 조회된다.

---

## 분석 결과

### 장점

* 고객 정보와 상담 이력 데이터가 분리되어 있음
* CRUD 구현이 단순하고 이해하기 쉬움

### 개선 가능 사항

* 고객번호(PK)를 기준으로 상담 이력 연결
* 검색 조건 다양화
* 페이징 기능 추가
* 조회 성능 개선
