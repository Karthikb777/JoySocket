from aiohttp import web
import socketio
from controller import Controller
import threading

# create a new socket sevrer
newServer = socketio.AsyncServer()
c = Controller()

# create a new web app
app = web.Application()

# attach our socket to the app
newServer.attach(app)
t1 = threading.Thread(target=c.applyInputs)

# on connection
@newServer.on('connect')
def startBroadCast(sid, message):
     t1.start()

# when a client is disconnected
@newServer.on('disconnect')
def stopBroadCast(sid):
     print(f'{sid} disconnected')

# when a client sends values
@newServer.on('values')
async def printMessage(sid, message):
     val = message.split(',')
     c.setInputs(val)

if __name__ == '__main__':
    web.run_app(app)