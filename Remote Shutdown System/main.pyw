import socket
import os
import pyttsx3
engine = pyttsx3.init('sapi5')
voices = engine.getProperty('voices')
# print(voices[1].id)
engine.setProperty('voice', voices[1].id)

def speak(audio):
    engine.say(audio)
    engine.runAndWait()


def takeCommandViaAndroid():  # socket programming
    listensocket = socket.socket()
    port = 7800
    maxConnections = 999
    IP = socket.gethostname()
    listensocket.bind(('', port))
    listensocket.listen(maxConnections)
    try:
        while True:
            (clientsocket, address) = listensocket.accept()
            message = clientsocket.recv(1024).decode()
            # print("\nKaren: ", message)
            return message
    except IOError:
        print("Error: can \t find file or read data")


def Queries(query):
    if 'shutdown' in query:
        speak("shutting down sir, see you soon...")
        os.startfile("D:\dsktop stuff\project\CN\Remote Shutdown System\Remote Shutdown System\shutdown.lnk")

    elif 'restart' in query:
        speak("Rebooting laptop...")
        os.startfile("D:\dsktop stuff\project\CN\Remote Shutdown System\Remote Shutdown System\Restart.lnk")

    elif 'logout' in query:
        speak("logging out sir, see you soon...")
        os.startfile("D:\dsktop stuff\project\CN\Remote Shutdown System\Remote Shutdown System\logout.lnk")

while True:
    query=takeCommandViaAndroid()
    Queries(query)



