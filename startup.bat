@echo on
cd %cd%
#ngrok -proto=tcp 22
#ngrok start web
ngrok -config=ngrok.cfg -log=ngrok.log -subdomain=gjh 8080