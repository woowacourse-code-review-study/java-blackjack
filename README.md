# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

### 1단계 기능 목록

#### 이름 입력

- [x] 10글자 이상의 이름이 들어오면 예외 처리
- [x] 숫자, 특수문자가 들어오면 예외 처리
- [x] 게임 참여자 수는 딜러를 포함해 2명 이상 8명 이하이다.

#### 카드 생성

- [x] 값: A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K
- [x] 모양: 스페이드, 클로버, 다이아몬드, 하트
- [x] 값과 모양을 가지는 `domain.card.Card` 객체를 생성한다.
- [x] 위에 없는 값과 모양의 객체는 예외 처리한다.
- [x] `domain.card.Cards` 객체는 랜덤으로 카드를 반환한다.

#### 카드 받기

- [x] 모두 2장의 카드를 받는다.
- [x] 플레이어는 2장의 카드를 모두 공개한다.
- [x] **딜러**는 합계가 16점 이하면 반드시 1장의 카드를 추가로 받아야 한다.
- [x] **딜러**는 합계가 17점 이상이면 카드를 추가로 받을 수 없다.
- [x] 플레이어에게 카드를 더 받을 지 물어본다.
    - [x] 플레이어가 `y`를 입력하면, 카드를 하나 더 반환한다.
    - [x] 플레이어가 `n`을 입력하면, 넘어간다.
    - [x] `y, n`이 아닌 다른 문자가 들어오면 예외 처리한다.

#### 점수 집계

- [x] 모든 플레이어는 카드를 받을 때마다 점수를 추가해서 저장한다.
- [x] J, Q, K는 10으로 계산한다.
- [x] Ace는 11로 계산하되, 합계가 21이 넘는 경우 1로 계산한다.
- [x] 모든 딜러와 플레이어는 점수가 21을 초과하면 `Bust` 된다.

#### 결과 출력

- [x] 딜러가 `Bust`되지 않은 경우
    - [x] 딜러보다 낮은 점수를 가진 플레이어는 `패`이다.
    - [x] `Bust`한 플레이어는 `패`이다.
    - [x] 딜러보다 높은 점수를 가진 플레이어는 `승`이다.
    - [x] 딜러와 같은 점수를 가진 플레이어는 `무`이다.
- [x] 딜러가 `Bust`된 경우
    - [x] `Bust`한 플레이어는 `무`이다.
    - [x] `Bust`하지 않은 플레이어는 `승`이다.

### 1단계 리팩터링

### 1단계 궁금증

1. 상속 클래스를 사용했을 때 `다형성`을 어디까지 이용할 수 있을까?
    - `Participant`를 상속받아 `Player`와 `Dealer`를 만들었다.
    - 처음에는 `Participants`에서 `Player`와 `Dealer`를 모아 놓은 `List<Participant> participants`로 관리했다.
    - 하지만 외부에서 `participants`의 형 변환 과정에서 어려움이 있어서, `List<Player> player`와 `Dealer`로 나누게 되었다.
    - 상속을 통해서 `중복 기능에 대한 코드 중복 제거`라는 목적을 달성하긴 했는데, 상위 클래스로 관리함에 있어서 불편함이 있다면 이런 방식으로 관리하는 것도 괜찮을까?
2. `Participants`를 `Dealer`와 `Players`에 대한 클래스로 분리하는 것 중 어떤 방법이 더 좋을까?
    - 현재 `Participants` 클래스의 주요한 역할은 생성자에 있다. 생성 시에 모든 참가자에게 `card`를 두 장씩 나누어주는 역할을 한다.
    - 이 작업은 `Dealer`를 포함하는 클래스이기 때문에 가능한 것 같기 때문에 현재 방식의 장점이다.
    - 이외의 메서드 `getPlayerNames()`처럼 `Players` 클래스로 나누었다면 `getNames()`로 더 자연스럽게 사용할 수 있을 것 같은 메서드가 보이며, 이것은 현재 방식의 단점이다.
