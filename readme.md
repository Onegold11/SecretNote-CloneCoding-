## SecretNote
---
비밀번호를 사용해서 잠금을 해제하고 글을 저장할 수 있는 다이어리

## 사용한 요소
---
- UI
    - Custom Font
    - AppCompatButton
    - Custom Theme
- 비동기
    - Handler
- Data
    - SharedPreference
- Dialog
    - AlertDialog

## Custom Font
---
1. res 폴더에 font 폴더 생성
2. font 폴더에 사용할 ttf 폰트 파일 저장
3. view의 fontFamily 속성으로 폰트 지정

ex) android:fontFamily="@font/roboto_medium"

## AppCompatButton
---
- Button 뷰를 사용할 경우 background 속성을 지정해도 색상이 변하지 않음
- Button의 색상이 colorPrimary로 설정됐기 때문
- Button 대신 AppCompatButton을 사용하면 색상이 정상적으로 변경됨

## Handler
---
- 비동기 처리를 해야 할 때 사용
- Runnable 객체를 만들고 실행할 동작을 만듬
- handler의 post 메소드를 호출해 runnable 객체를 실행
- postDelayed 메소드를 사용해 딜레이를 줄 수 있음
```kotlin
val runnable = Runnable {...}
val handler = Handler(Lopper.getMainLooper())

// 이미 설정된 runnable 제거
handler.removeCallbacks(runnable)

// 실행
handler.postDelayer(runnable, 500)
```

## SharedPreference
---
- 간단한 데이터를 저장할 때 사용
- 앱을 종료해도 데이터가 저장
- 값을 저장하고 apply() 또는 commit() 을 호출해야 함
- apply() : 비동기로 저장
- commit() : 동기로 저장
- edit 메소드의 매개변수로 true를 주면 commit 실행, false를 주면 apply 실행
```kotlin
val preferences = getSharedPreferences("diary", Context.MODE_PRIVATE)

// 데이터 저장
preferences.edit{
    putString("detail", diaryEditText.text.toString())
}

// 데이터 호출
preferences.getString("detail", "")
```