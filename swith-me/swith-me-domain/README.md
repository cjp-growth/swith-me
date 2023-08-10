## ⛺️ Domain 모듈

도메인에 관한 모듈 입니다. 현재 프로젝트에서는 `도메인 엔티티와 영속 엔티티를 별도로 구분하지 않습니다.`

<br/><br/><br/>

## 👪 패키지 간 의존관계

Domain 모듈은 다른 모듈에 의존하지 않습니다.

| Domain Module | API Module | Storage Module | Support Module |
|:-------------:|:----------:|:--------------:|:--------------:|
|       -       |     X      |       X        |       X        |

&nbsp;&nbsp; - Domain: 도메인 모듈 <br/>
&nbsp;&nbsp; - API: API 모듈 <br/>
&nbsp;&nbsp; - Storage: 데이터베이스 연관 모듈 <br/>
&nbsp;&nbsp; - Support: 로깅, 모니터링 등 API 지원 모듈 <br/>

<br/>
