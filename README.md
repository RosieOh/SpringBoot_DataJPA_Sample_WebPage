# SpringBoot_DataJPA_Sample_WebPage
👨‍💻SpringBoot_DataJPA_Sample_WebPage👨‍💻

## 기술 스택

### 📚 Tech Stack
#### 💻 Development
<img src="https://skillicons.dev/icons?i=java,spring,mysql,docker& perline="/>

#### ⌛ Developed Period
##### 2024.01.05

# 이슈사항
<details>
<summary>TUI Editor에서 Thymeleaf로 전달된 컨텐츠를 출력할 때 따옴표가 발생하는 문제가 발생</summary>
<div markdown="1">

## 원인이 뭘까? 🧐
> Thymeleaf의 특성과 TUI Editor의 초기화 방식 사이에 호환성 문제가 있었기 때문

## 어떻게 해결하나요? 🧐
- Thymeleaf에서 컨텐츠를 안전하게 출력하도록 th:utext 대신 th:attr를 사용하여 데이터 속성으로 값을 전달합니다.
- JavaScript에서 데이터 속성을 가져와 TUI Editor에 적용합니다.
  <br/>
  <br/>
- 기존 코드
```html
<div id="viewerSection" th:utext="${content}"></div>
```
```javascript
const viewer = new tui.Editor.factory({
  el: document.querySelector('#viewerSection'),
  viewer: true,
  height: 'fit-content'
})
viewer.setValue(`[[${content}]]`);
```
<br/>

- 수정 코드
```html
<div id="viewerSection" th:attr="data-content=${content}"></div>
```
```javascript
const viewer = new tui.Editor.factory({
    el: document.querySelector('#viewerSection'),
    viewer: true,
    height: 'fit-content'
})
viewer.setValue(document.querySelector('#viewerSection').getAttribute('data-content'));
```
## 변경 이유🧐
> Thymeleaf의 th:utext는 텍스트를 안전하게 출력하지만, JavaScript에서 TUI Editor에 값을 설정할 때 따옴표 문제가 발생할 수 있습니다.
<br/>
따라서 th:attr를 사용하여 데이터 속성으로 값을 전달하고, JavaScript에서 해당 속성을 통해 안전하게 값을 가져와 TUI Editor에 적용하는 방식으로 수정했습니다.

</div>
</details>


<details>
<summary> 데이터베이스에 문자열을 저장할 때 문자열 오류 발생</summary>
<div markdown="1">

## 원인이 뭘까? 🧐
> 데이터베이스에 문자열을 저장할 때 문자 집합이 올바르게 처리되지 않아 발생. 특히, 한글과 같은 유니코드 문자가 올바르게 처리되지 않아 Incorrect string value 에러가 발생했습니다.

## 어떻게 해결하나요? 🧐
> 데이터베이스 문자 집합 확인:
- 데이터베이스의 문자 집합을 확인하고, UTF-8 또는 유니코드로 설정되어 있는지 확인합니다. 데이터베이스 테이블과 컬럼의 문자 집합이 일치해야 합니다.
> Hibernate 설정 확인
- Hibernate 설정에서도 문자 집합이 UTF-8로 설정되어 있는지 확인합니다.
> 데이터베이스 연결 URL에 문자 집합 설정
- 데이터베이스 연결 URL에 characterEncoding과 useUnicode 매개변수를 추가하여 UTF-8을 사용하도록 설정합니다.
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sample?characterEncoding=UTF-8&useUnicode=true
```
-  데이터 베이스 생성 시 Encoding 설정을 UTF-8로 설정해주는 SQL 구문을 삽입합니다.

```mariadb
-- 윈도우 노트북 한글 인코딩 안될 때 사용
ALTER DATABASE sampleport DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```
<br/>

</div>
</details>


<!--
> TUI Editor에서 Thymeleaf로 전달된 컨텐츠를 출력할 때 따옴표가 발생하는 문제가 발생

## 원인이 뭘까? 🧐
> Thymeleaf의 특성과 TUI Editor의 초기화 방식 사이에 호환성 문제가 있었기 때문

## 어떻게 해결하나요? 🧐
- Thymeleaf에서 컨텐츠를 안전하게 출력하도록 th:utext 대신 th:attr를 사용하여 데이터 속성으로 값을 전달합니다.
- JavaScript에서 데이터 속성을 가져와 TUI Editor에 적용합니다.
<br/>
<br/>
- 기존 코드
```html
<div id="viewerSection" th:utext="${content}"></div>
```
```javascript
const viewer = new tui.Editor.factory({
  el: document.querySelector('#viewerSection'),
  viewer: true,
  height: 'fit-content'
})
viewer.setValue(`[[${content}]]`);
```
<br/>

- 수정 코드
```html
<div id="viewerSection" th:attr="data-content=${content}"></div>
```
```javascript
const viewer = new tui.Editor.factory({
    el: document.querySelector('#viewerSection'),
    viewer: true,
    height: 'fit-content'
})
viewer.setValue(document.querySelector('#viewerSection').getAttribute('data-content'));
```
## 변경 이유🧐
> Thymeleaf의 th:utext는 텍스트를 안전하게 출력하지만, JavaScript에서 TUI Editor에 값을 설정할 때 따옴표 문제가 발생할 수 있습니다. 
<br/>
따라서 th:attr를 사용하여 데이터 속성으로 값을 전달하고, JavaScript에서 해당 속성을 통해 안전하게 값을 가져와 TUI Editor에 적용하는 방식으로 수정했습니다.
-->


## 💡 Commit Convention

|       Tags       |               Explanation               |
| :--------------: | :-------------------------------------: |
|       Feat       |            새로운 기능 추가             |
|       Fix        |                버그 수정                |
| !BREAKING CHANGE |         커다란 API 변경의 경우          |
|     !HOTFIX      |          급한 치명적 버그 수정          |
|      Build       |           빌드 관련 파일 수정           |
|      Design      |        CSS를 포함 UI 디자인 변경        |
|       Docs       |                문서 수정                |
|      Style       | 코드 포맷팅, 세미콜론 누락, 코드 변경 X |
|     Refactor     |              코드 리팩토링              |
|     Comment      |        필요한 주석 추가 및 변경         |
|       Test       |            테스트 코드 수정             |
|      Rename      |         파일, 폴더명 이름 수정          |
|      Remove      |             파일, 폴더 삭제             |
|      chore       |            빌드, 패키지 수정            |