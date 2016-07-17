@echo off 
@echo ================================================ 
@echo  windows环境下Oracle数据库的自动备份脚本 
@echo  1、备份导出文件时自动使用当前日期进行命名。
@echo  2、自动删除7天前的备份
@echo ================================================
echo %date% 
echo %date:~0,4%
echo %date:~5,2%
echo %date:~8,2%
set mydate=%DATE:~0,10%
::设置用户名、密码
set USER=kpjd
set PASSWORD=1
set ORACLE=SFJD_LOCALHOST
::创建备份用目录
if not exist "backup\data\" 		mkdir backup\data\
if not exist "backup\log\" 		mkdir backup\log\
set DATA_CURDIR=backup\data
set LOG_CURDIR=backup\log
exp %USER%/%PASSWORD%@%ORACLE%  file=F:\backup\data\%date:~0,4%%date:~5,2%%date:~8,2%.dmp log=F:\backup\log\%date:~0,4%%date:~5,2%%date:~8,2%.log  
@echo::删除30天前备份
@echo forfiles /p "F:\backup\data" /s /m *.* /d -30 /c "cmd /c del @path"
@echo forfiles /p "F:\backup\log" /s /m *.* /d -30 /c "cmd /c del @path"
exit
 