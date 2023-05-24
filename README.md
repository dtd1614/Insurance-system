## 작업
* 꼭 본인의 이름 브랜치로 작업(pull, add, commit, push)할 것
* 작업이 모두 끝나면 main 브랜치로 pull request할 것
## Service 구현
### 구현 방법
* ServiceIF에 먼저 함수를 정의하고 Service에 Override한 후 구현 (ServiceIF에 함수 정의할 때 throws RemoteException 해줄 것)
* Service에 필요한 ListImpl은 필드에 private final로 선언하고 생성자 파라미터로 받아서 주입
* Service를 구현하는데 필요한 ListImpl의 함수도 구현
* Service 함수 구현할 때 ListImpl로부터 가져온 데이터가 null인 경우 NoDataException, 가져온 리스트가 비어있는 경우 EmptyListException 던질 것 (TimeDelayException은 나중에)
### 파트
* 김남훈
  * PayServiceIF & PaySerivce (요금을 납부하다)
  * ReportAccidentIF & ReportAccident (사고를 접수하다)
* 성유진
  * CompensateServiceIF & CompensateService (보상을 결정하다)
  * MakePolicyServiceIF & MakePolicyService (보상지침을 수립하다)
* 이우성
  * ApplyInsuranceServiceIF & ApplayInsuranceService (보험가입을 신청하다)
  * UnderwriteServiceIF & UnderwriteService (인수심사를 하다)
* 최성훈
  * ConcludeServiceIF & ConcludeService (계약을 체결하다)
  * OfferServiceIF & OfferService (보험을 제안하다)
