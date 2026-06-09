\# Troubleshooting



\## Spring MVC 프로젝트 실행 환경 복원



부트캠프에서 개발했던 CRM 프로젝트를 로컬 환경에서 다시 실행하는 과정에서 여러 실행 오류가 발생하였다.



해당 프로젝트는 Spring Boot 기반이 아니라, Tomcat에 배포되는 Spring MVC 기반 프로젝트였기 때문에 실행 환경과 배포 구조를 다시 확인하는 과정이 필요했다.



\---



\## 프로젝트 환경



\* Spring MVC 5.2.4.RELEASE

\* MyBatis

\* PostgreSQL

\* Maven

\* Apache Tomcat 9

\* IntelliJ IDEA Community

\* Smart Tomcat



\---



\## 1. Tomcat 배포 경로 문제



\### 문제 상황



초기 실행 시 Tomcat이 프로젝트를 정상적으로 찾지 못하는 문제가 발생하였다.



```text

The main resource set specified

\[...] is not a directory or war file

```



\### 원인



Smart Tomcat의 `CATALINA\_BASE` 또는 `docBase` 설정이 잘못되어 있었다.



Tomcat이 실제 웹 애플리케이션 경로가 아니라 잘못된 `webapps` 경로를 바라보고 있었고, 존재하지 않는 경로를 웹 애플리케이션으로 인식하려고 하였다.



\### 해결



Smart Tomcat 설정을 다시 확인하고, 실제 웹 리소스가 존재하는 경로를 배포 경로로 설정하였다.



```text

src/main/webapp

```



이를 통해 Tomcat이 `WEB-INF`, JSP, Spring 설정 파일을 정상적으로 읽을 수 있도록 하였다.



\---



\## 2. Tomcat 버전 호환성 문제



\### 문제 상황



처음에는 Tomcat 10으로 프로젝트를 실행하려고 하였으나, 프로젝트가 정상적으로 구동되지 않았다.



\### 원인



해당 프로젝트는 `javax.servlet` 기반의 Spring MVC 프로젝트이다.



`pom.xml`에서도 Servlet API 2.5를 사용하고 있었다.



```xml

<dependency>

&#x20;   <groupId>javax.servlet</groupId>

&#x20;   <artifactId>servlet-api</artifactId>

&#x20;   <version>2.5</version>

&#x20;   <scope>provided</scope>

</dependency>

```



하지만 Tomcat 10부터는 `javax.servlet`이 아니라 `jakarta.servlet` 기반으로 변경되었기 때문에 기존 Spring MVC 프로젝트와 호환되지 않을 가능성이 높았다.



\### 해결



Tomcat 10 대신 Tomcat 9를 사용하도록 변경하였다.



```text

Apache Tomcat 9.0.118

```



Spring MVC 기반의 기존 프로젝트에서는 Servlet API 버전과 Tomcat 버전의 호환성을 먼저 확인해야 한다는 점을 알 수 있었다.



\---



\## 3. Maven 의존성 및 클래스패스 문제



\### 문제 상황



Tomcat 실행 중 다음 오류가 발생하였다.



```text

java.lang.ClassNotFoundException:

org.springframework.web.context.ContextLoaderListener

```



\### 원인



`ContextLoaderListener`는 Spring Web 라이브러리에 포함되어 있다.



`pom.xml`에는 Spring MVC 의존성이 존재했지만, IntelliJ에서 Maven 프로젝트로 제대로 인식되지 않거나 Smart Tomcat이 Maven classpath를 제대로 참조하지 못하면 해당 클래스가 Tomcat 실행 시점에 로딩되지 않는다.



즉, IDE에서 Maven 라이브러리가 보이는 것과 실제 Tomcat 실행 classpath에 포함되는 것은 별개의 문제였다.



\### 확인한 내용



IntelliJ의 External Libraries에 Maven 의존성이 정상적으로 추가되어 있는지 확인하였다.



또한 Smart Tomcat의 `Use classpath of module` 설정이 실제 Maven 모듈을 바라보는지 확인하였다.



\### 해결



`pom.xml`을 Maven 프로젝트로 다시 로드하고, Smart Tomcat에서 실제 프로젝트 모듈을 classpath로 사용하도록 설정하였다.



```text

Use classpath of module: project-crm-master

```



\---



\## 4. IntelliJ 모듈 구조 문제



\### 문제 상황



프로젝트 폴더가 이중으로 구성되어 있었다.



```text

project-crm-master

└── project-crm-master

&#x20;   ├── pom.xml

&#x20;   ├── src

&#x20;   └── target

```



처음에는 바깥쪽 `project-crm-master` 폴더를 IntelliJ에서 열었기 때문에, 실제 Maven 프로젝트가 아닌 상위 폴더가 모듈로 잡히는 문제가 있었다.



\### 원인



IntelliJ와 Smart Tomcat이 실제 Maven 프로젝트가 아닌 잘못된 모듈의 classpath를 참조할 수 있었다.



이로 인해 `target/classes`에 존재하는 리소스를 런타임 classpath에서 찾지 못하는 문제가 발생하였다.



\### 해결



실제 `pom.xml`이 존재하는 안쪽 프로젝트 폴더를 기준으로 Maven 프로젝트를 다시 로드하였다.



또한 Smart Tomcat 설정에서 실제 Maven 모듈을 classpath로 사용하도록 조정하였다.



\---



\## 5. MyBatis Mapper XML 경로 문제



\### 문제 상황



Spring ApplicationContext 초기화 중 MyBatis 설정에서 오류가 발생하였다.



```text

Could not resolve resource location pattern \[classpath:mapper/\*.xml]



class path resource \[mapper/] cannot be resolved to URL because it does not exist

```



\### 원인



`mybatis-context.xml`에서 MyBatis Mapper XML 파일을 다음 경로에서 찾도록 설정되어 있었다.



```xml

<property name="mapperLocations" value="classpath:mapper/\*.xml" />

```



따라서 Mapper XML 파일은 classpath 기준으로 다음 위치에 존재해야 했다.



```text

src/main/resources/mapper

```



실제 Mapper XML 파일은 존재했지만, IntelliJ 모듈 및 Smart Tomcat classpath 설정이 꼬인 상태에서는 런타임에서 해당 리소스를 찾지 못했다.



\### 확인한 내용



프로젝트 구조와 빌드 결과를 확인하였다.



```text

src/main/resources/mapper/customer\_sql.xml

target/classes/mapper/customer\_sql.xml

```



`src/main/resources`가 Resources Root로 지정되어 있는지 확인하고, 빌드 후 `target/classes/mapper` 아래에 XML 파일이 복사되는지 확인하였다.



\### 해결



classpath 탐색 범위를 넓히기 위해 MyBatis 설정을 다음과 같이 변경하였다.



```xml

<property name="mapperLocations" value="classpath\*:mapper/\*.xml" />

```



이를 통해 MyBatis가 classpath 전체에서 Mapper XML 파일을 탐색하도록 하였다.



\---



\## 6. 한글 경로로 인한 log4j 설정 파일 파싱 문제



\### 문제 상황



MyBatis Mapper 경로 문제를 해결한 뒤, 새로운 오류가 발생하였다.



```text

log4j:ERROR Could not parse url

\[file:/C:/Users/earne/OneDrive/문서/project-crm-master/project-crm-master/target/classes/log4j.xml]



Path contains invalid character

```



이후 프로젝트를 바탕화면으로 옮겼을 때도 다음과 같은 오류가 발생하였다.



```text

Path contains invalid character: 바

```



\### 원인



프로젝트 경로에 한글이 포함되어 있었고, 오래된 log4j 1.x가 `log4j.xml` 경로를 URI로 파싱하는 과정에서 한글 경로를 정상적으로 처리하지 못했다.



문제가 된 경로 예시는 다음과 같다.



```text

C:\\Users\\earne\\OneDrive\\문서\\...

C:\\Users\\earne\\OneDrive\\바탕 화면\\...

```



즉, 프로젝트 위치를 바탕화면으로 옮기더라도 Windows의 실제 경로에 한글이 포함되어 있으면 동일한 문제가 발생할 수 있었다.



\### 해결



프로젝트를 한글이 포함되지 않은 영문 경로로 이동하였다.



예시:



```text

C:\\dev\\project-crm

```



Git 저장소는 `.git` 폴더를 함께 이동하면 기존 커밋 기록과 원격 저장소 연결이 유지된다.



SourceTree를 사용하는 경우에는 새 경로의 저장소를 다시 열어 기존 Git 저장소를 그대로 사용할 수 있다.



\---



\## 최종 정리



이번 실행 환경 복원 과정에서 발생한 문제는 하나의 원인이 아니라 여러 설정 문제가 단계적으로 연결된 결과였다.



해결 과정은 다음 순서로 진행되었다.



1\. Tomcat 배포 경로 확인

2\. Tomcat 10에서 Tomcat 9로 변경

3\. Maven 의존성 및 classpath 확인

4\. IntelliJ 모듈 구조 확인

5\. Smart Tomcat 모듈 설정 확인

6\. MyBatis Mapper XML 경로 확인

7\. 한글이 포함되지 않은 프로젝트 경로로 이동



\---



\## 배운 점



\* Spring Boot와 Spring MVC 프로젝트는 실행 방식이 다르며, Spring MVC 프로젝트는 WAS 배포 구조를 이해해야 한다.

\* Spring MVC 프로젝트에서는 Tomcat과 Servlet API 버전의 호환성을 확인해야 한다.

\* Maven 의존성이 IDE에 표시되는 것과 실제 Tomcat 실행 classpath에 포함되는 것은 별개의 문제일 수 있다.

\* Smart Tomcat에서는 `Deployment directory`와 `Use classpath of module` 설정이 중요하다.

\* MyBatis Mapper XML은 classpath 기준 위치가 정확해야 하며, resources 폴더가 빌드 결과에 포함되는지 확인해야 한다.

\* IntelliJ에서 프로젝트를 열 때 실제 `pom.xml`이 위치한 Maven 프로젝트 루트를 기준으로 열어야 한다.

\* 로그 분석 시 가장 마지막 `Caused by`를 기준으로 실제 원인을 추적해야 한다.

\* 오래된 라이브러리에서는 한글 경로, 공백 경로, OneDrive 경로가 문제를 일으킬 수 있다.

\* 기존 프로젝트를 다시 실행하는 과정 자체도 프로젝트 구조와 실행 환경을 이해하는 데 중요한 학습 과정이다.



