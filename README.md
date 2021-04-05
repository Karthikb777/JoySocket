# JoySocket

A system designed to use a phone as a wireless joystick using websockets.


## Why a phone as a controller?

This project was created because I wanted to play forza horizon 4 on a controller, but didn't have one.

## Working

The android app which acts as a socket io client is made with kotlin, and a socket io server is made using python, and the server feeds the data recieved into a vJoy device which then feeds the data to x360ce emulator, thus emulating a real joystick.

## Things used

	Kotlin - Android app.
	Python - server and feeder.
	socket io - for wireless connection.
	vjoy - for joystick sdk
  
## Releases

Here's the [Android app.](https://github.com/Karthikb777/JoySocket/releases/tag/v1.0)

