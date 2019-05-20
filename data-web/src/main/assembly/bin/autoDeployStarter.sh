#!/bin/bash
cd `dirname $0`

# 自动部署（参数1：分支名称；参数2：版本号；参数3：解压后的目录名；
./autoDeploy.sh master prod data-web