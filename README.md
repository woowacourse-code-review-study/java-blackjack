# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

### 1단계 기능 목록

#### 이름 입력

- [x] 10글자 이상의 이름이 들어오면 예외 처리
- [ ] 숫자, 특수문자가 들어오면 예외 처리

#### 카드 생성

- [ ] 값: A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K
- [ ] 모양: 스페이드, 클로버, 다이아몬드, 하트
- [ ] 값과 모양을 가지는 `Card` 객체를 생성한다.
- [ ] `Cards` 객체는 랜덤으로 카드를 반환한다.
- [ ] 이미 반환한 카드는 `Cards`에서 `이미반환한Set`에 추가한다.

#### 카드 받기

- [ ] 모두 2장의 카드를 받는다.
- [ ] 딜러는 2장 중 더 작은 수의 카드를 공개한다.
    - [ ] Ace가 나오면 다른 카드를 공개한다.
    - [ ] 두 개의 수가 같으면 앞의 카드를 공개한다.
- [ ] 플레이어는 2장의 카드를 모두 공개한다.
- [ ] **딜러**는 합계가 16점 이하면 반드시 1장의 카드를 추가로 받아야 한다.
- [ ] **딜러**는 합계가 17점 이상이면 카드를 추가로 받을 수 없다.
- [ ] 플레이어에게 카드를 더 받을 지 물어본다.
    - [ ] 플레이어가 `y`를 입력하면, 카드를 하나 더 반환한다.
    - [ ] 플레이어가 `n`을 입력하면, 넘어간다.
    - [ ] `y, n`이 아닌 다른 문자가 들어오면 예외 처리한다.

#### 점수 집계

- [ ] 모든 플레이어는 카드를 받을 때마다 점수를 추가해서 저장한다.
- [ ] J, Q, K는 10으로 계산한다.
- [ ] Ace는 11로 계산하되, 합계가 21이 넘는 경우 1로 계산한다.
- [ ] 모든 딜러와 플레이어는 점수가 21을 초과하면 `Bust` 된다.

#### 결과 출력

- [ ] 딜러가 `Bust`되지 않은 경우
    - [ ] 딜러보다 낮은 점수를 가진 플레이어는 `패`이다.
    - [ ] `Bust`한 플레이어는 `패`이다.
    - [ ] 딜러보다 높은 점수를 가진 플레이어는 `승`이다.
    - [ ] 딜러와 같은 점수를 가진 플레이어는 `무`이다.
- [ ] 딜러가 `Bust`된 경우
    - [ ] `Bust`한 플레이어는 `무`이다.
    - [ ] `Bust`하지 않은 플레이어는 `승`이다.
