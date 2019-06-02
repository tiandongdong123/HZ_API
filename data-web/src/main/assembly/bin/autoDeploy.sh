#!/bin/bash
cd `dirname $0`

# 备份已有的服务包（备份目录名格式：时间_服务名_backup,如：20180706143900_data-web_backup）
function backupService(){
    # 系统当前时间
    currentTime=$(date "+%Y%m%d%H%M%S")
    # 当前目录
    currentDir=$1
    # 服务名称,即解压后的目录名
    serviceName=$2
    # 服务压缩包名称
    serviceZip=*.tar.gz
    # 备份目录名称
    backupDirName=backup/${currentTime}_${serviceName}_backup

   # 判断是否已有其它版本的包，若存在，则备份
   if [ -d "${currentDir}/${serviceName}" ] || ls ${currentDir}/${serviceZip}>/dev/null 2>&1;then
        # 创建备份目录
        mkdir -p ${backupDirName}

        # 备份已有的服务包，即复制
        if [ -d "${currentDir}/${serviceName}" ];then
            mv ${currentDir}/${serviceName} ./${backupDirName}
            echo -e "\033[34m The service page of \"${serviceName}\" is successfully backed up! \033[0m"
        fi
        # 备份服务压缩包，即移动
#        if ls ${currentDir}/${serviceZip}>/dev/null 2>&1 ;then
#            mv ${currentDir}/${serviceZip} ./${backupDirName}
#            echo -e "\033[34m The service zip of \"${serviceZip}\" is successfully backed up! \033[0m"
#        fi
   else
        echo -e "\033[37m INFO: No files need to be backed up ! \033[0m \n"
   fi
}

# 结束
function exitShellScript(){
    echo -e "\033[36m ------------ Automatic deployment service end ------------ \033[0m \n"
    exit
}

# 当前目录
CURRENT_DIR=`pwd`
readonly CURRENT_DIR
# 分支名称
BRANCH_NAME=$1
readonly BRANCH_NAME
# 版本号
SERVICE_VERSION=$2
readonly SERVICE_VERSION
# 解压后的目录名
UNZIP_DIR_NAME=$3
readonly UNZIP_DIR_NAME
# 服务压缩包名称
SERVICE_ZIP=${UNZIP_DIR_NAME}-assembly-${SERVICE_VERSION}.tar.gz
readonly SERVICE_ZIP
# 服务压缩包路径
SERVICE_ZIP_PATH=${CURRENT_DIR}/${SERVICE_ZIP}
readonly SERVICE_ZIP_PATH
# bin目录
BIN_DIR=${CURRENT_DIR}/${UNZIP_DIR_NAME}/bin
readonly BIN_DIR

echo -e "\033[36m ------------ Automatic deployment service start ------------ \033[0m  \n"

# 判断必填参数是否为空
if [ "${BRANCH_NAME}" = "" ];then
  echo -e "\033[31m ERROR: The parameter value of the \"BRANCH_NAME\" is empty ! （参数1-分支名称） \033[0m  \n"
  exitShellScript
fi

if [ "${SERVICE_VERSION}" = "" ];then
  echo -e "\033[31m ERROR: The parameter value of the \"SERVICE_VERSION\" is empty ! （参数2-版本号） \033[0m \n"
  exitShellScript
fi

if [ "${UNZIP_DIR_NAME}" = "" ];then
  echo -e "\033[31m ERROR: The parameter value of the \"UNZIP_DIR_NAME\" is empty ! （参数3-解压后的目录名） \033[0m \n"
  exitShellScript
fi

# 检测当前目录下是否已包含要部署的服务包,若不存在，则下载
if [ -f "${SERVICE_ZIP_PATH}" ];then
    # 备份已有的服务包
    echo -e "\033[34m ************ backupService start ************ \033[0m"
    backupService ${CURRENT_DIR} ${UNZIP_DIR_NAME}
    echo -e "\033[34m ************ backupService end ************ \033[0m \n"

    # 解压gz
    echo -e "\033[34m ************ tar gz start ************ \033[0m"
    tar -zxvf ${SERVICE_ZIP_PATH} -C ${CURRENT_DIR}
    echo -e "\033[34m ************ tar gz end ************ \033[0m \n"

    # 执行sh脚本,重启服务
    echo -e "\033[34m ************ execute shell file start ************ \033[0m"
    bash ${CURRENT_DIR}/${UNZIP_DIR_NAME}/bin/restart.sh
    echo -e "\033[34m ************ execute shell file end ************ \033[0m \n"

    # 结束
    exitShellScript
else
    echo -e "\033[33m WARN: The zip of \"${SERVICE_ZIP}\" does not exist ! \033[0m \n"
    # 结束
    exitShellScript
fi

