# slider.py 
 
""" 
Contoh aplikasi yang menghubungkan  sinyal dari tGui.QSlider  dan slot dari QtGui.QLCDNumber 
"""
import sys
from PySide import QtGui, QtCore
from PySide.QtGui import *
from PySide.QtGui import *
from PySide.QtCore import *

class Example(QtGui.QWidget):  	 
    def __init__(self): 
        super(Example, self).__init__() 
 	 
        self.initUI()
        
    def initUI(self): 
##        self.lcd = QtGui.QLCDNumber(self)
##        self.lcdm= QtGui.QLCDNumber(self)
        self.clockValue = QLCDNumber()
        self.clockValue.setSegmentStyle(QLCDNumber.Flat)
        self.clockValue.setFixedSize(QSize(400,150))
        self.clockValue.setDigitCount(12)

        self.barJam = QtGui.QSpinBox()
        self.barJam.setMaximum(23)
        self.barJam.setMinimum(0)
        
        self.barMenit = QtGui.QSpinBox()
        self.barMenit.setMaximum(59)
        self.barMenit.setMinimum(0)
        
        
        self.barDetik = QtGui.QSpinBox()
        self.barDetik.setMaximum(59)
        self.barDetik.setMinimum(0)

        self.viewButton = QtGui.QPushButton("Tampilkan Jam Digital")

        
        self.labelJam = QtGui.QLabel("Jam ")
        self.labelMenit = QtGui.QLabel("Menit ")
        self.labelDetik = QtGui.QLabel("Detik ")
        
        self.sliderMenit = QtGui.QDial(self)
        self.sliderDetik = QtGui.QSlider(QtCore.Qt.Horizontal, self)


        
        
        
        vbox = QtGui.QGridLayout()
##        vbox.addWidget(self.lcd)
        
        vbox.addWidget(self.labelJam, 0,0)
        vbox.addWidget(self.labelMenit, 1,0)
        vbox.addWidget(self.labelDetik, 2,0)
        
        vbox.addWidget(self.barJam, 0,1,1,3)
        vbox.addWidget(self.barMenit, 1,1,1,3)
        vbox.addWidget(self.barDetik, 2,1,1,3)
        vbox.addWidget(self.viewButton, 3,1,1,2)
        
        vbox.addWidget(self.sliderMenit, 1,4)
        vbox.addWidget(self.sliderDetik, 2,4)
##        vbox.addWidget(self.lcdm)

##        self.sliderMenit.valueChanged.connect(self.barMenit.)
##
        
        self.sliderMenit.valueChanged.connect(self.value_barMenit)
        self.barMenit.valueChanged.connect(self.value_barMenit)
        self.sliderDetik.valueChanged.connect(self.value_barDetik)
        self.barDetik.valueChanged.connect(self.value_barDetik)
        self.viewButton.clicked.connect(self.buatLCD)
        
##        self.sliderMenit.valueChanged.connect(self.barMenit.setValue)
##        self.sliderDetik.valueChanged.connect(self.barDetik.setValue)
                
        self.setLayout(vbox)
##        self.sld.valueChanged.connect(self.lcd.display)
##        self.sliderMenit.valueChanged.connect(self.barMenit.setText) 
        self.setGeometry(500, 500, 350, 250)
        self.setWindowTitle('Jam Sederhana')
        self.sliderMenit.setMaximum(59)
        self.sliderMenit.setNotchesVisible(59)
        self.sliderDetik.setMaximum(59)
        self.LCD=QLCDNumber()
        self.LCD.setDigitCount(8)
        self.LCD.resize(500,150)
##        self.connect(self.viewButton, SIGNAL("clicked()"), self.showClock) 
##        self.show()
        
##    def showClock(self):
##        wind = QWidget(self)
##        wind.setWindowTitle('Jam Digital')
##        emi = '%s : %s : %s' %(self.barJam.value(), self.barMenit.value(), self.barDetik.value())
##        self.clockValue.display(emi)
##        self.clockValue.show()
        
    def value_barDetik(self, value):
        self.barDetik.setValue(value)
        self.sliderDetik.setValue(value)

    def value_barMenit(self, value):
        self.barMenit.setValue(value)
        self.sliderMenit.setValue(value)


    def buatLCD(self):
        
        self.timer = QTimer(self)
        self.jam = self.barJam.value()
        self.menit= self.barMenit.value()
        self.detik= self.barDetik.value()

        self.currentTime = QtCore.QTime(self.jam,self.menit,self.detik)
        self.timer.timeout.connect(self.showTime)
        self.timer.start(1000)
        self.showTime()

    

    def showTime(self):
        self.currentTime = self.currentTime.addSecs(1)
        self.LCD.display(self.currentTime.toString('hh:mm:ss'))
        self.LCD.show()
        
def main(): 	 
    app = QtGui.QApplication(sys.argv)
    
    ex = Example()
    ex.show()
    sys.exit(app.exec_()) 

if __name__ == '__main__':
    main() 

#QDial
