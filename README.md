# Retro-VHS-Project 📼
Welcome to "Blast from the Past," our cutting-edge management system for a classic VHS rental experience. This project involves building a robust backend service to manage our inventory, users, and rentals.

## 1. Required components
 * java 21 or newer
 * maven 3.x
 * IDE that supports SpringBoot (optional)
 * git (optional)
 * Docker (optional)

## 2. Download git repository
### Bash
```bash
git clone https://github.com/pogiii22/Retro-VHS-Project.git
cd Retro-VHS-Project/
```

  ### IDE
  * download zip from this url: https://github.com/pogiii22/Retro-VHS-Project.git
  * unpack zip
  * open IDE in project folder

## 3. Run app
### Docker
1. Build image:
```bash
docker build -t vhs-app .
```
2. If first step failed, then do this:
```bash
mvn clean package
docker build -t vhs-app .
```
3. Run container in terminal:
```bash
docker run --name app -p 8080:8080 vhs-app
```
   Or run container in background:
```bash
docker run --name app -p 8080:8080 -d vhs-app
```
4. Read container logs:
```bash
docker logs -f [containerName]
```
5. Stop container (app)
```bash
docker stop [containerName]
```
6. Start container (app) again
```bash
docker start [containerName]
```
### IDE
 #### Bash / Terminal
 ```bash
 * $ mvn spring-boot:run
```
#### IDE
 * Start main app(DemoApplication.java) in IDE

## 4. Run test

### bash/terminal 
```bash
mvn test
```
### IDE
  * In folder Retro-VHS-Project/src/test/java/com/example/demo/ run CreateRentalTest.java

## 5. Postman
Import the Postman Collection from the Postman folder (.json file) into Postman.
Once imported, run the requests to test the application's functionality.





