# Getting Started

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Intellij Idea](https://img.shields.io/badge/Intellij%20Idea-000?logo=intellij-idea&style=for-the-badge)
![Java](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)

## Prerequisites

Before you begin, ensure you have met the following requirements:

* You have installed the JDK Version 11
* You have a `<Windows/Linux/Mac>` machine.
* You have installed intellij idea

## How to Run Project
* Clone the project <br>
  > git clone <repository_url>
* Navigate to the Project Directory  <br>
  > cd <project_directory>
* Build the Project <br>
  > ./mvnw clean package
* Run the Project <br>
  > ./mvnw spring-boot:run

## Simple Architectur And CI/CD
![Image Alt text](https://raw.githubusercontent.com/bimosyah/ic-test/master/images/arch%20diagram.jpg)

![Image Alt text](https://github.com/bimosyah/ic-test/blob/master/images/cicd%20pipeline.jpg?raw=true)

## API Doc

<summary><code>BASE URL</code> <code><b>http://54.197.214.81:8081</b></code></summary>
<br>
<details>
 <summary><code>GET</code> <code><b>/asteroid</b></code> <code>(Get Top 10 Closest Asteroid)</code></summary>

##### Parameters

| name         | type           | data type | description                      |
|--------------|----------------|-----------|----------------------------------|
| `start_date` | `not required` | string    | set first date asteroid approach |
| `end_date`   | `not required` | string    | set end date asteroid approach   |

##### Responses

| http code | content-type       | response |
 |-----------|--------------------|----------|
| `200`     | `application/json` | JSON     |

##### Example cURL

 ```javascript
  curl --location 'http://54.197.214.81:8081/asteroid?start_date=2020-10-10&end_date=2020-10-16'
 ```

</details>

<details>
 <summary><code>GET</code> <code><b>/asteroid/detail/{asteroidId}</b></code></summary>

##### Parameters

| name         | type       | data type | description |
|--------------|------------|-----------|-------------|
| `asteroidId` | `required` | string    | asteroid id |

##### Responses

| http code | content-type       | response |
 |-----------|--------------------|----------|
| `200`     | `application/json` | JSON     |

##### Example cURL

 ```javascript
  curl --location 'http://54.197.214.81:8081/asteroid/detail/2162162'
 ```

</details>

