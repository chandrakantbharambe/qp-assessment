# qp-assessment
Grocery app 
# Requirement
  - mysql
# Build Application
   mvn clean install
# Run Application
  run as spring boot application
# Run Application from dokcer
  - docker pull cbharambe01/grocery-app
  - docker pull mysql
  - docker network create grocery-mysql-net (you can change network name)
  - docker run --name localhost --network grocery-mysql-net -e MYSQL_ROOT_PASSWORD=Chandu@123 -e MYSQL_DATABASE=grocery -d mysql (give password and database name as per application.properties file)
  - docker run --network localhost --name grocery-app -p 8080:8080 grocery-app
# Authentication
  - hit to /authenticate to get token for admin or user. User name and password given in GroceryApplication.java file
# Authorization
  - use jwt token from /authenticate to make call to every endpoint from grocerycontroller.
