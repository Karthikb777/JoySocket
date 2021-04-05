import time
import math as m
import pyvjoy

# initialize vJoyDevice
joyDevice = pyvjoy.VJoyDevice(1)

class Controller:

    def __init__(self):

        self.buttonQuit = 0

        self.buttonLeft = 0
        self.buttonRight = 0
        self.buttonUp = 0
        self.buttonDown = 0

        self.buttonX = 0
        self.buttonY = 0
        self.buttonA = 0
        self.buttonB = 0

        self.buttonLb = 0
        self.buttonRb = 0
        self.buttonView = 0
        self.buttonXbox = 0
        self.buttonMenu = 0

        self.leftHorizontal = 50
        self.rightHorizontal = 50
        self.lt = 0
        self.rt = 0

        self.InputLow = 0
        self.InputHigh = 100
        self.OutputLow = 1
        self.OutputHigh = 32768

# reverts all the instance properties(buttons only) to the default values after setting vjoy device input
    def revertToDefault(self):

        self.buttonQuit = 0

        self.buttonLeft = 0
        self.buttonRight = 0
        self.buttonUp = 0
        self.buttonDown = 0

        self.buttonX = 0
        self.buttonY = 0
        self.buttonA = 0
        self.buttonB = 0

        self.buttonLb = 0
        self.buttonRb = 0
        self.buttonView = 0
        self.buttonXbox = 0
        self.buttonMenu = 0

# we don't require these inputs to reset 
        # self.leftHorizontal = 50
        # self.rightHorizontal = 50
        # self.lt = 0
        # self.rt = 0

        time.sleep(0.01)
        joyDevice.data.lButtons = 0

# sets input to the instance properties recieved from the socket
    def setInputs(self, values):

        self.buttonQuit = int(values[0])

        self.buttonLeft = int(values[1])
        self.buttonRight = int(values[2])
        self.buttonUp = int(values[3])
        self.buttonDown = int(values[4])

        self.buttonX = int(values[5])
        self.buttonY = int(values[6])
        self.buttonA = int(values[7])
        self.buttonB = int(values[8])

        self.buttonLb = int(values[9])
        self.buttonRb = int(values[10])
        self.buttonView = int(values[11])
        self.buttonXbox = int(values[12])
        self.buttonMenu = int(values[13])

        self.leftHorizontal = int(values[14])
        self.rightHorizontal = int(values[15])
        self.lt = int(values[16])
        self.rt = int(values[17])


# applies the inputs to the vjoy device 
    def applyInputs(self, stopFlag = False):
        while self.buttonQuit == 0:

            leftHorizontalToHex = self.OutputHigh - (((self.leftHorizontal - self.InputLow) / (self.InputHigh - self.InputLow)) * (self.OutputHigh - self.OutputLow) + self.OutputLow)
            rightHorizontalToHex = ((self.rightHorizontal - self.InputLow) / (self.InputHigh - self.InputLow)) * (self.OutputHigh - self.OutputLow) + self.OutputLow

            ltToHex = self.OutputHigh - (((self.lt - self.InputLow) / (self.InputHigh - self.InputLow)) * (self.OutputHigh - self.OutputLow) + self.OutputLow)
            rtToHex = ((self.rt - self.InputLow) / (self.InputHigh - self.InputLow)) * (self.OutputHigh - self.OutputLow) + self.OutputLow

            print(
                f"""quit: {self.buttonQuit}, left button: {self.buttonLeft},
               right button: {self.buttonRight}, up button: {self.buttonUp}, down button: {self.buttonDown},
                X: {self.buttonX}, Y: {self.buttonY}, A: {self.buttonA}, B: {self.buttonB}, LB: {self.buttonLb},
        RB: {self.buttonRb}, View: {self.buttonView}, Xbox: {self.buttonXbox}, Menu: {self.buttonMenu}, 
        left horizontal: {self.leftHorizontal}, right horizontal: {self.rightHorizontal}, LT: {self.lt}, RT: {self.rt}"""
        )

#       set axes values of the controller input
            joyDevice.data.wAxisX = int(m.ceil(leftHorizontalToHex))
            joyDevice.data.wAxisY = int(m.ceil(rightHorizontalToHex))

            joyDevice.data.wAxisXRot = int(m.ceil(ltToHex))
            joyDevice.data.wAxisYRot = int(m.ceil(rtToHex))

#       set button values of controller input
            if self.buttonQuit == 1:
                joyDevice.data.lButtons = 1 

            if self.buttonLeft == 1:
                joyDevice.data.lButtons = 2 
            if self.buttonRight == 1:
                joyDevice.data.lButtons = 4 
            if self.buttonUp == 1:
                joyDevice.data.lButtons = 8 
            if self.buttonDown == 1:
                joyDevice.data.lButtons = 16

            if self.buttonX == 1:
                joyDevice.data.lButtons = 32
            if self.buttonY == 1:
                joyDevice.data.lButtons = 64
            if self.buttonA == 1:
                joyDevice.data.lButtons = 128
            if self.buttonB == 1:
                joyDevice.data.lButtons = 256

            if self.buttonLb == 1:
                joyDevice.data.lButtons = 512
            if self.buttonRb == 1:
                joyDevice.data.lButtons = 1024
            if self.buttonView == 1:
                joyDevice.data.lButtons = 2048
            if self.buttonXbox == 1:
                joyDevice.data.lButtons = 4096
            if self.buttonMenu == 1:
                joyDevice.data.lButtons = 8192

            joyDevice.update()
            self.revertToDefault()
            time.sleep(0.05)







