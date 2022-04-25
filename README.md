# 球賽約戰與影片分析平台 game cmmunication and video analysis

## 簡介
使用Spring Boot開發，搭配資料庫PostgreSQL，且部署於AWS雲端平台。此平台讓愛好籃球的朋友不只可以在上面聊天交流，也可以發布球局，呼朋引伴一起流汗，享受運動帶來的樂趣，而影片分析功能是想搭配論文使用過的技術整合進平台，目前本專案尚未完全開發完成。<br/>

下面會介紹平台每個功能與使用甚麼技術，畢竟這專案我一開始是為了練習spring security、i18n多國語系、web socket、hibernate與前端三寶 HTML、javascript、css 還有jquery、cookie，並且學習一下如何部署於雲端平台以及設定自己的網域名與https。

目前平台網址 http://basketball-env.eba-a8wssv9i.us-east-1.elasticbeanstalk.com <br/>
目前有4個帳號 可以在不同瀏覽器重複登入 <br/>

帳號| 密碼
--------------|:-----:
jordan | 123
kobe   | 123
mary   | 12345
尹覺    | 54321

<br/>

## 功能介紹

1. 登入畫面<br/>
以spring security進行使用者登入驗證<br/>
<img src="https://github.com/jaylee840831/game_cmmunication_video-analysis/blob/main/login%E6%B5%81%E7%A8%8B.jpg" />
2. 主畫面<br/>
球局資訊查詢，點擊查看會進入該球局聊天室，並帶有cookie分辨是哪場球局
3. 球局聊天室<br/>
此為多人聊天室，以web socket開發，目前僅做完聊天功能，尚未針對不同球局開發聊天室，所以從哪個球局進來都是同一聊天室 :)
4. 個人資料<br/>
修改個人資料，姓名會連動修改登入的帳號名稱以及球局發起人姓名
5. 發起球局(尚未開發)<br/>
可發起自己的球局
6. 球局歷史紀錄(尚未開發)<br/>
會紀錄收藏過的球局、進過的球局聊天室以及自己發起過的球局
7. 影片分析(尚未開發)<br/>
希望能整合我論文的球賽分析技術，讓使用者可以在網路上自動剪輯球賽影片等等
8. 聊天室<br/>
此為一對一聊天室，以web socket開發，可以與好友即時聊天，但是因為沒存對話紀錄所以同時在聊天室頁面才會收到訊息 :)
