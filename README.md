# 아키텍처 스터디
> 체스 도메인을 활용하여 아키텍처 스터디 진행

## 스터디 진행 방법
- step1. 콘솔 기반의 체스 게임을 만든다.
- step2. [만들면서 배우는 클린 아키텍처](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=283437942) 를 스터디 한다.
- step3. 책의 내용을 참고하여 콘솔 체스 게임을 리펙토링 해본다.
- step4. 콘솔 체스 게임에 DB를 연동해보면서 Hexagonal Architecture 구조로 설계해본다.
- step5. Spring Framework 에 적용해본다.

## Step1. 콘솔 체스 게임
### 콘솔 체스 설명
```
- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.
- 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.
- 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.
- King이 잡히는 경우 게임에서 진다. King이 잡혔을 때 게임을 종료한다.
```

### 실행 예시
```
> 체스 게임을 시작합니다.
> 게임 이동 : move source위치 target위치 - 예. move b2 b3

RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr

move b2 b3
RNBQKBNR
PPPPPPPP
........
........
........
.p......
p.pppppp
rnbqkbnr
```

### 구현 기능 목록
- [x] 체스 기물의 기본 행마법
- [x] 캐슬링
- [x] 앙파상
- [x] 프로모션
- [ ] 체크
- [ ] 체크메이트

## 참고 자료
- [만들면서 배우는 클린 아키텍처](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=283437942)
- [클린 아키텍처](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=202322454)
- [체스 위키](https://ko.wikipedia.org/wiki/%EC%B2%B4%EC%8A%A4_%EA%B7%9C%EC%B9%99)