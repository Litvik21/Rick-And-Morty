<div id="header" align="center">
  <img src="https://1000logos.net/wp-content/uploads/2022/03/Rick-and-Morty.png" width="500"/>
</div>

## 📖 Description
This app can get characters of Rick and Morty, and save them to your database.

## 📋 Project structure
**The project has an 3-Tier Architecture**
- Controller - This level allows the user to work with this application.
- Service - This level of architecture is responsible for processing the data received from the DAO level.
- Repository - This level of architecture is responsible for communicating with the database.

## 🎯 Features
- Get a random character
- Get character or characters by part of name
- Save/Update data at a specific time

## 🖥️ Technologies
- Java 17
- Maven
- MySQL
- Tomcat
- Swagger 
- Liquibase
- Spring Web/Boot

## ⚡️Quickstart
1. Fork this repository
2. Copy link of project
3. Create new project from Version Control
4. Edit resources/application.properties - set the necessary parameters
``` java
    spring.datasource.driver-class-name=YOUR_DRIVER
    spring.datasource.url=YOUR_URL
    spring.datasource.username=YOUR_USERNAME
    spring.datasource.password=YOUR_PASSWORD
```
5. Create the necessary name of DB
6. Run project

## 👀 Example of parameters for db.properties
``` java
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/NameOfDataBase?useUnicode=true&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=123456
```

<div id="header" align="center">
  <img src="https://www.freepnglogos.com/uploads/rick-and-morty-png/rick-and-morty-portal-shoes-white-clothing-zavvi-23.png" width="200"/>
</div>
