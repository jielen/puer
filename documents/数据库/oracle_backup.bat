@echo off 
@echo ================================================ 
@echo  windows������Oracle���ݿ���Զ����ݽű� 
@echo  1�����ݵ����ļ�ʱ�Զ�ʹ�õ�ǰ���ڽ���������
@echo  2���Զ�ɾ��7��ǰ�ı���
@echo ================================================
echo %date% 
echo %date:~0,4%
echo %date:~5,2%
echo %date:~8,2%
set mydate=%DATE:~0,10%
::�����û���������
set USER=kpjd
set PASSWORD=1
set ORACLE=SFJD_LOCALHOST
::����������Ŀ¼
if not exist "backup\data\" 		mkdir backup\data\
if not exist "backup\log\" 		mkdir backup\log\
set DATA_CURDIR=backup\data
set LOG_CURDIR=backup\log
exp %USER%/%PASSWORD%@%ORACLE%  file=F:\backup\data\%date:~0,4%%date:~5,2%%date:~8,2%.dmp log=F:\backup\log\%date:~0,4%%date:~5,2%%date:~8,2%.log  
@echo::ɾ��30��ǰ����
@echo forfiles /p "F:\backup\data" /s /m *.* /d -30 /c "cmd /c del @path"
@echo forfiles /p "F:\backup\log" /s /m *.* /d -30 /c "cmd /c del @path"
exit
 