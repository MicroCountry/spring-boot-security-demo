# spring-boot-security-demo
1. ｒｕｎ　import.sql
2. run DemoApplication.java
3. http://127.0.0.1:8080/securityDemo/auth/token  you can get access_token and refresh_token by post data 
   {"username":"admin","password":"admin"}
4. http://127.0.0.1:8080/securityDemo/auth/refresh with header Authorization=Bearer access_token ,will refresh token
