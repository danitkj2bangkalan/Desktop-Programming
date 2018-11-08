#!/usr/bin/python # -*- coding: utf-8 -*- # aplikasi.py 
 
""" Program ini membuat kerangka dari GUI klasik dengan melibatkan menubar, toolbar, statusbar, dan widget utama """ 
 
import sys 
from PySide import QtGui

class Example(QtGui.QMainWindow):
    def __init__(self):
        super(Example, self).__init__()
        self.initUI()

    def initUI(self):
        #text edit
        self.textEdit = QtGui.QTextEdit()
        self.textEdit.setObjectName("textEdit")
        self.setCentralWidget(self.textEdit)

        #statusbar bawah notif
        self.statusbar = QtGui.QStatusBar()
        self.statusbar.setObjectName("statusbar")
        self.setStatusBar(self.statusbar)

        #menubar atas
        menubar = self.menuBar()

        #File -> New File
        newAction = QtGui.QAction('New File', self)
        newAction.setShortcut('Ctrl+N')
        newAction.setStatusTip('New File')
        newAction.triggered.connect(self.new)

        #File -> Exit
        exitAction = QtGui.QAction('Exit', self)
        exitAction.setShortcut('Ctrl+Q')
        exitAction.setStatusTip('Exit application')
        exitAction.triggered.connect(self.close)

        #Edit -> Calculate Char
        calculateAction = QtGui.QAction('Calculate Char', self)
        calculateAction.setStatusTip('Calculate Char')
        calculateAction.triggered.connect(self.hitungBanyak)

        #Help -> Help About
        aboutAction = QtGui.QAction('About', self)
        aboutAction.setStatusTip('About')
        aboutAction.triggered.connect(self.about)


        fileMenu = menubar.addMenu('&File') #add object atas
        fileMenu.addAction(newAction)   #add sub object dari atas
        fileMenu.addAction(exitAction)  #add sub object dari atas

        editMenu = menubar.addMenu('&Edit') #add object atas
        editMenu.addAction(calculateAction) #add sub object dari atas

        helpMenu = menubar.addMenu('&Help')    #add object atas
        helpMenu.addAction(aboutAction)     #add sub object dari atas

        self.setGeometry(450, 200, 350, 250)
        self.setWindowTitle('Main window')
        self.show()

    #fungsi dari pemanggilan triggered
    def new(self):
        self.textEdit.clear()
        self.statusbar.showMessage("")

    #fungsi dari pemanggilan triggered
    def hitungBanyak(self):
        huruf = self.textEdit.toPlainText()
        panjangHuruf = str(len(huruf))
        self.statusbar.showMessage("Panjang Karakter = "+ panjangHuruf)

    #fungsi dari pemanggilan triggered
    def about(self):
        msgBox = QtGui.QMessageBox()
        msgBox.setText("NIM : 170411100107 \nNama : Moh Romadhani Firdaus")
        msgBox.exec_()

def main():
    app = QtGui.QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_()) 
 
 
if __name__ == '__main__':
    main() 
