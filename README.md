# JoySocket

A system designed to use a phone as a wireless joystick using websockets.


## Why a phone as a controller?

This project was created because I wanted to play forza horizon 4 on a controller, but didn't have one.

## Working

The android app which acts as a [socket io](https://socket.io/) client is made with kotlin, and a socket io server is made using python, and the server feeds the data recieved into a vJoy device which then feeds the data to x360ce emulator, thus emulating a real joystick.  [Here's a video of the system in action.](https://youtu.be/KFWroH2s_rc)

## How to use?

Download the android app from the link below.  
Download [x360ce.](https://www.x360ce.com/)  
Download and install [vJoy.](http://vjoystick.sourceforge.net/site/index.php/download-a-install/download)  
Run the server.py file present in the SocketServer folder.  
Enter IP address of Your PC in the app.  
Configure the x360ce emulator.  
You are ready to play!    

Note: The PC must be connected to the phone's wifi hotspot.

## Things used

	Kotlin - Android app.
	Python - server and feeder.
	socket io - for wireless connection.
	vjoy - for joystick sdk
  
## Releases

Here's the [Android app.](https://github.com/Karthikb777/JoySocket/releases/tag/v1.0)

