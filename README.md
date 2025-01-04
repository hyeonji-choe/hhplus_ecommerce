<h1># hhplus_ecommerce 3주차~6주차</h1>
<h3>gitHub milestone</h3>
<div>
  <p>https://github.com/hyeonji-choe/hhplus_ecommerce/milestones</p>
</div>
<h3>이벤트스토밍</h3>
<div>
  <img src = "https://github.com/user-attachments/assets/ef49f718-5159-4ce0-bf5a-0a17b753a6c5">
  <img src = "https://github.com/user-attachments/assets/70809319-b7c1-48d9-831c-11b26fa81df1">
</div>
<h3>시퀀스다이어그램</h3>
<div>
  <p>LucidChart 공유폴더 링크</p>
  <p>https://lucid.app/folder/invitations/accept/inv_62edb212-2770-4467-a19a-aca1b6f3f2b9</p>
</div>


## How to run Mockserver

`./gradlew :api-mockserver:run`

## ERD
<div>
  <p>회원 (아이디/이름)</p>
  <p>회원자산자원(아이디/자산형태:자산or자원/혜택값/정율or정액/발급일시)</p>
  <p>회원자산자원히스토리(자산자원아이디/사용일시/사용값)</p>

  <p>쿠폰(아이디/쿠폰명/정율or정액/혜택값)</p>
  <p>쿠폰발급(아이디/쿠폰아이디/유저아이디/발급일시/발급양)</p>

  <p>상품(아이디/상품명/재고/상태)</p>

  <p>주문(주문아이디/유저아이디/주문일시/총금액/할인금액/주문결제금액/주문상태:완료or취소)</p>
  <p>주문상세(주문아이디/주문상세아이디/상품리스트(상품아이디/주문량))</p>
  <p>주문결제(주문아이디/주문결제아이디/총금액/결제금액/할인정보(쿠폰아이디/쿠폰수량)/결제상태:완료or취소)</p>
</div>
