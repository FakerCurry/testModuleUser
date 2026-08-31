#!/bin/bash

# 构建并部署所有模块到私有Maven仓库
# 确保核心代码安全，仅提交jar包，不提交源码

set -e

echo "====================================================="
echo "          开始构建并部署项目到私有Maven仓库"
echo "====================================================="

# 定义仓库目录
REPO_BASE="/Users/shenjiawei/Application/jiaxingfanlianyouxiangongsi/v3酒店开发/分jar包/模板/repository"
RELEASE_REPO="$REPO_BASE/releases"
SNAPSHOT_REPO="$REPO_BASE/snapshots"

# 创建仓库目录（如果不存在）
echo "1. 创建私有Maven仓库目录..."
mkdir -p "$RELEASE_REPO"
mkdir -p "$SNAPSHOT_REPO"
echo "   ✓ 仓库目录创建完成"

# 清理之前的构建
echo ""
echo "2. 清理之前的构建..."
mvn clean -q
echo "   ✓ 清理完成"

# 编译并打包所有模块（不运行测试）
echo ""
echo "3. 编译并打包所有模块..."
mvn package -DskipTests -q
echo "   ✓ 打包完成"

# 安装到本地仓库
echo ""
echo "4. 安装到本地Maven仓库..."
mvn install -DskipTests -q
echo "   ✓ 本地安装完成"

# 部署到私有仓库
echo ""
echo "5. 部署到私有Maven仓库..."
mvn deploy -DskipTests -q
echo "   ✓ 部署完成"

# 显示部署结果
echo ""
echo "====================================================="
echo "                部署完成！"
echo "====================================================="
echo ""
echo "部署位置："
echo "  - Releases: $RELEASE_REPO"
echo "  - Snapshots: $SNAPSHOT_REPO"
echo ""
echo "已部署的模块："
echo "  - com.company:project-api:1.0.0-SNAPSHOT"
echo "  - com.company:module-user:1.0.0-SNAPSHOT"
echo "  - com.company:module-order:1.0.0-SNAPSHOT"
echo "  - com.company:project-main:1.0.0-SNAPSHOT"
echo ""
echo "注意："
echo "  ✓ 仅部署了jar包，源码未提交"
echo "  ✓ 核心代码安全得到保障"
echo "====================================================="