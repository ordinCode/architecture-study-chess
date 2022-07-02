# 아키텍처 스터디
> 체스 도메인을 활용하여 아키텍처 스터디 진행

## 스터디 진행 방법
- step1. 콘솔 기반의 체스 게임을 만든다.
- step2. [만들면서 배우는 클린 아키텍처](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=283437942) 를 스터디 한다.
- step3. 책의 내용을 참고하여 콘솔 체스 게임을 리펙토링 해본다.
- step4. 콘솔 체스 게임에 DB를 연동해보면서 Hexagonal Architecture 구조로 설계해본다.
- step5. Spring Framework 에 적용해본다.

## Step2. 콘솔 체스 게임 (with DB)
- Step1 에서 구현한 체스에 DB를 연동시킨다.
- 어플리케이션 종류 후에 다시 실행시켜도 이전 상태를 유지할 수 있도록 한다.