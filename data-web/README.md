# 1.data-web
    此模块主要对外提供工商数据的http接口，目前通过ip白名单来拒绝非白名单内访问者的调用请求。
    
# 2.现有接口
## 2.1企业信息查询（/productservice/productOutInterface/product_companyInfoOut）
    调用方可通过企业名称、统一社会信用代码或组织结构代码查询企业信息。
    
# 3.部署
    本模块采用maven profiles结合assembly插件来构建不同环境使用的jar包，src/main/assembly/conf目录下的dev、test和prod分别代表开发环境、测试环境和生产环境。
## 3.1配置文件（及资源文件）
    1.src/main/assembly/assembly.xml为assembly插件生成相应目录结构配置文件，根据实际需求可进行相应修改；
    2.src/main/assembly/*/application.properties为spring boot的配置文件，根据实际需求可进行相应修改；
    3.src/main/assembly/*/logback.xml为logback日志的配置文件，根据实际需求可进行相应修改；
    4.src/main/assembly/*/init.properties为开发者自定义的配置文件，根据实际需求可进行相应修改；
    5.src/main/assembly/*/dataCodeSetReferenceDoc_v*.xlsx为数据代码集对照excel文件，根据实际需求可进行相应修改(注：修改时与init.properties中的相应配置保持一致)；
## 3.2构建
    结合程序需要运行的环境，构建相应的环境版本，maven命令:mvn clean package -P *,如：mvn clean package -P dev(dev为pom.xml中的“profiles.profile.id”属性值)
# 3.3程序启动及停止
    1.start.sh:程序启动脚本；stop.sh:程序停止脚本；restart.sh:程序重启脚本；start.bat:程序启动批处理脚本
    2.若在Linux操作系统下，执行.sh文件出现“$'\r': 未找到命令”、“附近有语法错误”等错误，先通过以下步骤尝试解决：
        1）.通过“vim ***.sh”、“:set ff”来查看该.sh文件的编码格式（“***”为文件名，替换即可）；
        2）.若执行结果为“fileformat=dos”,则通过“:set ff=unix”、“:wq”命令来修改文件编码；若执行结果为“fileformat=unix”,说明shell脚本格式正确，但存在错误，请联系开发人员；
    3.若在Linux操作系统下，执行.sh文件出现“权限不够”错误，请通过“chmod +x ***.sh”命令来给相应的.sh文件添加可执行权限。