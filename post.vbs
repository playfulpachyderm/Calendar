sub wait(msg, time)
	do while IE.busy
		wScript.sleep 10
	loop
	wScript.sleep time
	wScript.echo msg
end sub

person = wScript.arguments(0)

set shell = wScript.CreateObject("WScript.Shell")

set IE = createObject("InternetExplorer.Application")
wait "launched IE", 1500
IE.visible = 1
IE.navigate("https://www.facebook.com")

wait "navigated to facebook", 3000 

on error resume next

IE.document.getElementById("email").value = "wilde.jagd@hotmail.com"
IE.document.getElementById("pass").value = "noncomprehendingly"
IE.document.getElementById("u_0_b").click()
on error goto 0

wait "logged in", 3000

message = "Happy birthday " & person & "!"

set textArea = IE.document.getElementById("u_0_u")
wait "textArea.focus()", 10 



for i=1 to len(message)
	
	set objWMIService = GetObject("winmgmts:{impersonationLevel=impersonate}!\\.\root\cimv2")
	set processes = objWMIService.execQuery("select * from Win32_Process where name = 'iexplore.exe'")
	for each process in processes
		shell.appActivate(process.ProcessID)
	next
	
	wait "appActivated iexplore.exe", 1

	textArea.focus()
	wait "textArea.focus()", 1
	
	shell.sendKeys( mid (message,i,1))
	
	wait "sent a key", 5
next

wait "sent keys", 300 

set button = IE.document.getElementsByClassName("_42ft _42fu _11b selected _42g-")(0)
button.click()

wait "clicked 'post'", 3000 

IE.quit
