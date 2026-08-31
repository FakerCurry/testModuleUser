# 私有Maven仓库使用说明

## 已部署的模块

所有模块已成功部署到私有Maven仓库：

| 模块名称 | GroupId | ArtifactId | 版本 |
|---------|---------|------------|------|
| API模块 | com.company | project-api | 1.0.0-SNAPSHOT |
| 用户模块 | com.company | module-user | 1.0.0-SNAPSHOT |
| 订单模块 | com.company | module-order | 1.0.0-SNAPSHOT |
| 主应用模块 | com.company | project-main | 1.0.0-SNAPSHOT |

## 其他项目如何访问私有仓库

在需要使用这些模块的项目中，需要在 `pom.xml` 中进行如下配置：

### 1. 配置仓库地址

在 `<project>` 标签内添加仓库配置：

```xml
<repositories>
    <!-- 私有快照仓库 -->
    <repository>
        <id>private-snapshots</id>
        <name>Private Maven Snapshots Repository</name>
        <url>file:///Users/shenjiawei/Application/jiaxingfanlianyouxiangongsi/v3酒店开发/分jar包/模板/repository/snapshots</url>
        <releases>
            <enabled>false</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
    
    <!-- 私有发布仓库（当使用正式版本时） -->
    <repository>
        <id>private-releases</id>
        <name>Private Maven Releases Repository</name>
        <url>file:///Users/shenjiawei/Application/jiaxingfanlianyouxiangongsi/v3酒店开发/分jar包/模板/repository/releases</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </repository>
</repositories>
```

### 2. 添加依赖

在 `<dependencies>` 标签内添加需要的模块依赖：

```xml
<!-- 添加API模块依赖 -->
<dependency>
    <groupId>com.company</groupId>
    <artifactId>project-api</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>

<!-- 添加用户模块依赖 -->
<dependency>
    <groupId>com.company</groupId>
    <artifactId>module-user</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>

<!-- 添加订单模块依赖 -->
<dependency>
    <groupId>com.company</groupId>
    <artifactId>module-order</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>

<!-- 添加主应用模块依赖（如果需要） -->
<dependency>
    <groupId>com.company</groupId>
    <artifactId>project-main</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 3. 完整示例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>my-project</artifactId>
    <version>1.0.0-SNAPSHOT</version>

    <repositories>
        <repository>
            <id>private-snapshots</id>
            <name>Private Maven Snapshots Repository</name>
            <url>file:///Users/shenjiawei/Application/jiaxingfanlianyouxiangongsi/v3酒店开发/分jar包/模板/repository/snapshots</url>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>com.company</groupId>
            <artifactId>project-api</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>com.company</groupId>
            <artifactId>module-user</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
</project>
```

## 注意事项

1. **仓库路径**：确保URL路径正确指向私有仓库的实际位置
2. **版本类型**：
   - `-SNAPSHOT` 版本会从 snapshots 仓库获取
   - 正式版本（无 `-SNAPSHOT`）会从 releases 仓库获取
3. **核心代码安全**：私有仓库中仅包含编译后的jar包，不包含源码，确保核心代码安全
4. **更新依赖**：如果模块有更新，使用 `-U` 参数强制更新快照版本：
   ```bash
   mvn clean install -U
   ```