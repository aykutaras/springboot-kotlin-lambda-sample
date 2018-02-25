# springboot-kotlin-lambda-sample

[![Build Status](https://travis-ci.org/aykutaras/springboot-kotlin-lambda-sample.svg?branch=master)](https://travis-ci.org/aykutaras/springboot-kotlin-lambda-sample)

Sample application for spring boot with kotlin. It can be deployed to aws lambda. 
It implements basic spring boot functionality and a swagger ui for api references

- <host>/v1/diff/<ID>/left and <host>/v1/diff/<ID>/right
- <host>/v1/diff/<ID> to get diff-ed data

### Build and Deploy
- To build, run `./gradlew clean build`
- To deploy, run `serverless deploy`
- To run as an API `./gradlew bootRun`
- Documentation can be found from `http://localhost:8080/swagger-ui.html`
