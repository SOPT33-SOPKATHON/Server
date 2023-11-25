# ✅ 콩 (Cong)

> 33기 DO SOPT 솝커톤
> 
> 당신의 축하를 기록하세요

<hr>

## ✅ Contributors
|                                                                     이동섭🍋                                                                      |                                           박경린🍇                                            |
|:----------------------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------:|
| <img src="https://github.com/GOSOPT-CDS-TEAM7-DeskTop/Backend/assets/67463603/3c92f168-1ff0-42a4-a91f-9fbd11189d35" width="200" height="200"/> | <img src="" width="200" height="200"/> |
|                                                   [ddongseop](https://github.com/ddongseop)                                                    |                            [pkl0912](https://github.com/pkl0912)                             |

<hr>

## ✅️ Role

| 담당 역할                |      섭팟      |
|:---------------------|:------------:|
| 초기 세팅 후 푸시 🧵        | 이동섭🍋, 박경린🍇 |
| ERD 및 엔티티 설계 🏛️     | 이동섭🍋, 박경린🍇 |
| ✅ API 개발             |    이동섭🍋     |
| ✅ API 개발             |    박경린🍇     |
| 리드미, 아키텍처 구조도 작성 📄  |    박경린🍇     |
| AWS 세팅 후 HTTPS 배포 🚀 |    이동섭🍋     |

<hr>

## ✅ API Docs

### 🔗 [Link](https://rileybyeon.notion.site/API-1e4864ddfd764d13898c922e340c76b6?pvs=4) ✅링크
✅ 사진
<hr>

## ✅️ ERD
<hr>
![image](https://github.com/SOPT33-SOPKATHON/Server/assets/67463603/810827a4-647d-45f7-931e-44ae04625af2)


## ✅ Project Architecture
<img width="700" alt="image" src="https://github.com/SOPT33-SOPKATHON/Server/assets/67463603/ddac0047-3ccd-4f9d-8709-90f2bd5525db">

## ✅ Project Structure

`도메인 패키지 구조` 사용

```
🗂 sopkathonServer
    🗂 common (공통 처리 관련 클래스)
        🗂 config
        🗂 domain
        🗂 dto
        🗂 exception
    🗂 ✅
    🗂 ✅
```

## 🌱 Branch

`main branch` : 배포 단위 branch

`develop branch` : 주요 개발 branch, main merge 전 거치는 branch (default 브랜치)

`feature branch`: 각자 개발하는 branch

-   API별로 feature branch를 생성
-   feat/#{이슈번호}-{스네이크 케이스로 작성된 기능 단위}
-   ex) feat/#3-remind_push_alarm


-   해당 branch 작업 완료 후 PR 보내기
    -   항상 local에서 충돌 해결 후 → remote에 올리기
    -   reviewer에 서로 tag후 code-review
    -   comment 전 merge 불가!


<hr>

## ✅ Commit Convention

`ex) [FEAT] #2 댓글 작성 API 기능 구현`

```
- [FEAT] : 새로운 기능 구현
- [FIX] : 버그, 오류 해결
- [CHORE] : 동작에 영향 없는 코드 or 변경 없는 변경사항(주석 추가 등) or 파일명, 폴더명 수정 or 파일, 폴더 삭제 or 디렉토리 구조 변경
- [REFACTOR] : 전면 수정, 코드 리팩토링
- [DOCS] : README나 WIKI 등의 문서 수정
- [MERGE] : 다른 브랜치와 병합
```

- 세부 기능 기준
- 이슈번호 붙이는 단위 : **[FEAT], [FIX], [REFACTOR]**

## ✅ Code Convention

- **작업하고 커밋하기 전에 잊지 말 것**
  - 코드 포맷팅 ⇒ window : ctrl + alt + l , mac : command + option + l
  - 미사용 Import문 삭제 ⇒ 공통 : ctrl + alt + o
- 객체 생성은 팩토리 메서드 패턴 사용

```
1. 기본적으로 네이밍은 누구나 알 수 있는 쉬운 단어를 선택한다.
   - 우리는 외국인이 아니다. 쓸데없이 어려운 고급 어휘를 피한다.
   
2. 변수는 CamelCase를 기본으로 한다.
    - userEmail, userCellPhone ...
    
3. URL, 파일명 등은 kebab-case를 사용한다.
    - /user-email-page ...
    
4. 패키지명은 단어가 달라지더라도 무조건 소문자를 사용한다.
    - frontend, useremail ...
    
5. ENUM이나 상수는 대문자로 네이밍한다.
    - NORMAL_STATUS ...
    
6. 함수명은 소문자로 시작하고동사로 네이밍한다.
    - getUserId(), isNormal() ...
    
7. 클래스명은 명사로 작성하고 UpperCamelCase를 사용한다.
    - UserEmail, Address ...
    
8. 객체 이름을 함수 이름에 중복해서 넣지 않는다. (= 상위 이름을 하위 이름에 중복시키지 않는다.)
    - line.getLength() (O) / line.getLineLength() (X)
    
9. 컬렉션은 복수형을 사용하거나 컬렉션을 명시해준다.
    - List ids, Map<User, Int> userToIdMap ...
    
10. 이중적인 의미를 가지는 단어는 지양한다.
    - event, design ...
    
11. 의도가 드러난다면 되도록 짧은 이름을 선택한다.
    - retreiveUser() (X) / getUser() (O)
    - 단, 축약형을 선택하는 경우는 개발자의 의도가 명백히 전달되는 경우이다. 명백히 전달이 안된다면 축약형보다 서술형이 더 좋다.

12. 함수의 부수효과를 설명한다.
    - 함수는 한가지 동작만 수행하는 것이 좋지만, 때에 따라 부수 효과를 일으킬 수도 있다.
        fun getOrder() {
          if (order == null) {
              order = Order()
          }
        return order
        }
    - 위 함수는 단순히 order만 가져오는 것이 아니라, 없으면 생성해서 리턴한다.
    - 그러므로 getOrder() (X) / getOrCreateOrder() (O)
    
13. LocalDateTime -> xxxAt, LocalDate -> xxxDt로 네이밍

14. 객체를 조회하는 함수는 JPA Repository에서 findXxx 형식의 네이밍 쿼리메소드를 사용하므로 개발자가 작성하는 Service단에서는 되도록이면 getXxx를 사용하자.
```

## ✅ Code Review Convention
- 뱅크샐러드 코드 리뷰 방식 참고 (https://blog.banksalad.com/tech/banksalad-code-review-culture/)
- ex. `[P1] 이건 꼭 반영해주셔야해요!`
- 코드 수정이 필요한 것에 대해서만 `Pn` 붙이기
- 그냥 단순 칭찬이나 감탄사는 `Pn` 안붙이기


- 서로 상대 실수 한 것 없는지 귀찮아도 꼭 읽어보기
- 긍정적인 코멘트 적극적으로 남겨주기
