# Travel Agency Application

A simple Travel Agency Application with Java and Spring Boot.

## Installation

It's a Spring Boot `2.5.2` and Java `11` application with a very little functionality. Here are the commands you need to run one by one-

```
git clone git@github.com:CodeMechanix/Travel-Agency-Application.git
cd Travel-Agency-Application
mvn clean
mvn spring-boot:run
```

Then you need to put your database credentials in the application-dev.yml file. I used PostgreSQL in this project. 

Then you can visit the base url in this url- `http://localhost:8090/v1`. 

## Uses

This application can be used both in logged in or logged out state. But for using the full potential, you should logged in.

This project has following features-

- Full fledged registration and login system
- Proper validation for each form
- Status Create, Update (With Public and Private Privacy).
- Location Master Data
- JWT 
- Global Exception Handling


## API Details

> Base End Point: http://localhost:8090/v1

> Location API

1. Location > Create
```text
METHOD       :  Post
URI          :  /api/location
Req. Ex.     : {base}/api/location
```
```text
Request Body Sample
{
   "name"  : "Dhaka",
   "active": true
}
```

2. Location > Get All Active
```text
METHOD     : Get
URI        : /api/location/getAll/active?page=0&pageSize=100
Req. Ex.   : {base}/api/getAll/active?page=0&pageSize=100
```
3. Location > Get All Inactive
```text
METHOD     : Get
URI        : /api/location/getAll/inactive?page=0&pageSize=100
Req. Ex.   : {base}/api/location/getAll/inactive?page=0&pageSize=100
```
4. Location > Get By ID
```text
METHOD     : GET
URI        : /api/location/get/{id}
Req. Ex.   : {base}/api/location/get/1
```
5. Location > Update
```text
METHOD     : PUT
URI        : /api/location
Req. Ex.   : {base}/api/location
```
6. Location > Get Dropdown Data
```text
METHOD      : Get
URI         : /api/location/getAll/active/dropdown
Req. Ex.    : {base}/api/location/getAll/active/dropdown
```
7. Location > Exists by name
```text
METHOD      : Get
URI         : /api/location/exists/byName?name=name
Req. Ex.    : {base}/api/location/exists/byName?name=name
```

> Status API

1. Status > Create
```text
METHOD       :  Post
URI          :  /api/status
Req. Ex.     : {base}/api/status
```
```text
Request Body Sample
{
   "name"  : "Completed",
   "havePrivacy": true,
   "locationId": 1,
   "userId": 1
}
```

2. Status > Get All Active
```text
METHOD     : Get
URI        : /api/status/getAll?page=0&pageSize=100
Req. Ex.   : {base}/api/status/getAll?page=0&pageSize=100
```

4. Status > Get By ID
```text
METHOD     : GET
URI        : /api/status/get/{id}
Req. Ex.   : {base}/api/status/get/1
```

5. Status > Update
```text
METHOD     : PUT
URI        : /api/status
Req. Ex.   : {base}/api/status
```

> User API

1. User > Exists by UserId
```text
METHOD      : Get
URI         : /api/user/exists/byUserId?userId=1
Req. Ex.    : {base}/api/user/exists/byUserId?userId=1
``` 

2. User > Exists by Email
```text
METHOD      : Get
URI         : /api/user/exists/byEmail?email=hasan@gmail.com
Req. Ex.    : {base}/api/user/exists/byEmail?email=hasan@gmail.com
``` 

> User API

1. Auth > SignIn

```text
METHOD       :  Post
URI          :  /api/auth/signIn
Req. Ex.     : {base}/api/auth/signIn
```
```text
Request Body Sample
{
   "name"  : "Completed",
   "havePrivacy": true,
   "locationId": 1,
   "userId": 1
}
```

2. Auth > SignUp

```text
METHOD       :  Post
URI          :  /api/auth/singUp
Req. Ex.     : {base}/api/auth/signUp
```

```text
Request Body Sample
{
   "name"  : "Completed",
   "havePrivacy": true,
   "locationId": 1,
   "userId": 1
}
```


## Developer

Hasan Mahmud<br>
hasan.mahmud8177@gmail.com<br>
<https://sites.google.com/view/codemechanixhasan/home>