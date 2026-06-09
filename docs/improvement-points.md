# Improvement Points

## Service Layer

현재 Service는 Mapper 호출을 중계하는 역할에 가깝다.

개선 방향
- 입력값 검증 추가
- 예외 처리 추가

## Search

현재 고객명 검색만 지원

개선 방향
- 이메일 검색
- 전화번호 검색

## Database

상담 이력 조회가 고객명 기준

개선 방향
- 고객번호 기준 조회

## CustomerDto

일부 필드 타입 개선 필요

- BRDT
- LAST_MDFCN_DT
- FRST_RGTR_SN