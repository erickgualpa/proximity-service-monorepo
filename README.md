# 🧭 Proximity Service 🗺️

![CI/CD status](https://github.com/erickgualpa/proximity-service-monorepo/actions/workflows/maven.yml/badge.svg)
[![](https://img.shields.io/badge/Spring%20Boot%20Version-3.4.4-blue)](/pom.xml)
[![](https://img.shields.io/badge/Java%20Version-21-blue)](/pom.xml)
[![](https://img.shields.io/badge/Kotlin%20Version-2.0.0-blue)](/pom.xml)

🧪 Run tests
<br>

```shell script
./mvnw clean verify
```

🚀 Build and deploy containers locally! 🐳
<br>

[//]: # (Grant permission to run file with 'chmod +x build_and_deploy.sh')

```shell script
./build_and_deploy.sh
```

💤 Clear service containers

```shell script
docker compose down --rmi local
```