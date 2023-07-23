# Insurance-system

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>

구 신동아화재(현 한화손해보험) 보험사 시스템 제안요청서를 **분석**하여 Usecase 시나리오를 작성하고, Java RMI 분산시스템을 적용하여 **설계&구현**한 CLI 보험사 시스템 

## 유스케이스 다이어그램
  
![image](https://github.com/dtd1614/Insurance-system/assets/116648310/e3c788a1-53f4-4709-b067-50e0769a3e7c)

실제로 구현한 액터와 유스케이스는 붉은색으로 표시

## 컴포넌트 다이어그램

![image](https://github.com/dtd1614/Insurance-system/assets/116648310/56036a59-4d6d-453a-b84e-31439d7df9e7)

Mysql을 제외한 컴포넌트는 rmi를 이용하여 서로 통신

## 클래스 다이어그램

<details>
<summary>Domain</summary>
<div markdown="1">     

![image](https://github.com/dtd1614/Inusurance-system/assets/116648310/bc66761e-655c-4654-bd4d-5a2f0c470daf)

Client와 Server가 데이터를 주고 받을 때 데이터의 타입으로 사용

</div>
</details>

<details>
<summary>Enumeration</summary>
<div markdown="1">    

![image](https://github.com/dtd1614/Inusurance-system/assets/116648310/d7ba7bbe-ef9f-40b4-ab17-d69312683f2e)

</div>
</details>

<details>
<summary>Client</summary>
<div markdown="1">       

![image](https://github.com/dtd1614/Insurance-system/assets/116648310/902ee451-3dda-48e9-9bda-5249ee237e57)

Client는 interface를 통해 Server와 통신

</div>
</details>

<details>
<summary>AccidentServer</summary>
<div markdown="1">       

![image](https://github.com/dtd1614/Insurance-system/assets/116648310/75372ced-891b-421b-a37d-cebcaa48f98d)

Server는 interface를 통해 Client의 요청을 받고, service에서 데이터를 처리하고 dao로 데이터를 넘김  
dao는 db에 데이터 처리를 하고 응답에 필요한 데이터를 service로 넘김  
service 데이터를 처리하고 interface를 통해 데이터와 함께 Client에 응답  
데이터 타입은 Domain을 사용

</div>
</details>

<details>
<summary>CalculationFormulaServer</summary>
<div markdown="1">       

![image](https://github.com/dtd1614/Insurance-system/assets/116648310/86ed677c-d3fe-4273-9bc7-6b5708f01f79)

</div>
</details>

<details>
<summary>CompensationServer</summary>
<div markdown="1">       

![image](https://github.com/dtd1614/Insurance-system/assets/116648310/04b983d0-1388-42d5-98d2-05dbe0b218d7)
다른 Server의 interface를 통해 Sever끼리 통신

</div>
</details>

<details>
<summary>ContractServer</summary>
<div markdown="1">       

![image](https://github.com/dtd1614/Insurance-system/assets/116648310/887edfc5-1978-457f-b6ce-ee1adcce96ad)

</div>
</details>

<details>
<summary>CustomerServer</summary>
<div markdown="1">       

![image](https://github.com/dtd1614/Insurance-system/assets/116648310/889c368b-4cd1-4ac0-a233-e754248f8a1c)

</div>
</details>

<details>
<summary>EmployeeServer</summary>
<div markdown="1">       

![image](https://github.com/dtd1614/Insurance-system/assets/116648310/887085c1-bbb9-4d7a-89a4-9ef360d4d5a8)

</div>
</details>

<details>
<summary>InsuranceServer</summary>
<div markdown="1">       

![image](https://github.com/dtd1614/Insurance-system/assets/116648310/4ffebc9e-f9e2-476a-ae9a-58fb375e9b28)

</div>
</details>

<details>
<summary>SaleServer</summary>
<div markdown="1">       

![image](https://github.com/dtd1614/Insurance-system/assets/116648310/6d47e686-9a7d-4d7d-a6dd-632172796bd3)

</div>
</details>

## ERD

![image](https://github.com/dtd1614/Insurance-system/assets/116648310/82845d72-7aad-4dfd-a77c-df858c70ef34)

## 콘솔화면

<details>
<summary>로그인&회원가입</summary>
<div markdown="1">       
  
![image](https://github.com/dtd1614/Insurance-system/assets/116648310/39e26516-fbb9-478d-b3cc-c53200769b80)

![image](https://github.com/dtd1614/Insurance-system/assets/116648310/7c4c8a29-188a-4724-a181-90be1afe91c1)

</div>
</details>

<details>
<summary>고객</summary>
<div markdown="1">       
  
![image](https://github.com/dtd1614/Insurance-system/assets/116648310/ef9b4f3f-fd22-4449-b580-d5be2b78e1e8)

</div>
</details>

<details>
<summary>상품개발자</summary>
<div markdown="1">       
  
![image](https://github.com/dtd1614/Insurance-system/assets/116648310/ad245832-d307-40a5-9d66-d1967e9fd860)

</div>
</details>

<details>
<summary>상품관리자</summary>
<div markdown="1">       
  
![image](https://github.com/dtd1614/Insurance-system/assets/116648310/ed8566e1-2ca1-4a38-856f-3f96897306c2)

</div>
</details>

<details>
<summary>영업사원</summary>
<div markdown="1">       
  
![image](https://github.com/dtd1614/Insurance-system/assets/116648310/abea8dac-d7e6-432c-96d3-63a0c5cd654e)

</div>
</details>

<details>
<summary>언더라이터</summary>
<div markdown="1">       
  
![image](https://github.com/dtd1614/Insurance-system/assets/116648310/7e5000c4-cb74-493a-a757-3350f5013147)

</div>
</details>

<details>
<summary>보상담당자</summary>
<div markdown="1">       
  
![image](https://github.com/dtd1614/Insurance-system/assets/116648310/a4590755-8ebb-499e-ad00-29c7e43deae3)

</div>
</details>

## 실행
mysql에 이름이 insurance인 데이터베이스를 생성&접속하여 DDL.txt의 쿼리를 실행

모든 Server 프로젝트의 Dao 생성자에서 userName과 password를 본인 mysql계정에 맞게 수정

StartRMIRegistryHere[JDK11] 폴더에서 cmd를 켜고 start rmiregistry 입력

Server 프로젝트를 모두 실행하고 Client 프로젝트 실행 (JDK11 사용)

