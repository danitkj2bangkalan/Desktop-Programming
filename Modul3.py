
#!/usr/bin/python # -*- coding: utf-8 -*- #layout-grid-cal.py 
 
""" Membuat kerangka layout  aplikasi kalkulator """ 
import math
import sys
from PySide import QtGui, QtCore


class Example(QtGui.QWidget):
    def __init__(self):
        super(Example, self).__init__()

        self.pendingAdditiveOperator = ''
        self.pendingMultiplicativeOperator = ''

        self.sumInMemory = 0.0
        self.sumSoFar = 0.0
        self.factorSoFar = 0.0
        self.waitingForOperand = True
        
        self.display = QtGui.QLineEdit()
        self.display.setReadOnly(True)
        self.display.setAlignment(QtCore.Qt.AlignRight)
        self.display.setMaxLength(15)

        font = self.display.font()
        font.setPointSize(font.pointSize() + 8)
        self.display.setFont(font)

        self.initUI()

    def initUI(self):
        num='0789456123'
        self.digitButtons=[]
        names = ['Cls', 'Bck', '', 'Close', '7', '8', '9','/','4', '5', '6', '*', '1', '2', '3', '-', '0', '.', '=', '+']
        grid = QtGui.QGridLayout() 
        j = 0
##        pos = [(0, 0), (0, 1), (0, 2), (0, 3),(1, 0), (1, 1), (1, 2), (1, 3),(2, 0), (2, 1), (2, 2), (2, 3),(3, 0), (3, 1), (3, 2), (3, 3 ),                 (4, 0), (4, 1), (4, 2), (4, 3)] 
        for i in names:
            button = QtGui.QPushButton(i)   
            if i in num:
                for k in (num):
                    self.digitButtons.append(self.createButton(str(k),self.digitClicked))

        self.clearAllButton = self.createButton('Cls', self.clearAll)#0
        self.backSpaceButton = self.createButton('Bck', self.backSpace)#1 
        self.closeButton = self.createButton('Close',self.close)#3
        self.equalButton = self.createButton('=', self.equalClicked)#18
        self.plusButton = self.createButton('+', self.additiveOperatorClicked)
        self.minusButton = self.createButton('-',self.additiveOperatorClicked)#15
        self.multipleButton = self.createButton('\327',self.multiplicativeOperatorClicked)#11
        self.devideButton = self.createButton('\367',self.multiplicativeOperatorClicked)#7
        self.pointButton = self.createButton(".", self.pointClicked)
        
         
        for j in (num):
##            print(j)
            row = ((9 - int(j)) / 3) + 2
            column = ((int(j) - 1) % 3) 
            
            grid.addWidget(self.digitButtons[int(j)], row, column)
                
##            if j == 2:
##                grid.addWidget(QtGui.QLabel(''), 0, 2)
##            else: grid.addWidget(button, pos[j][0], pos[j][1])
##            j = j + 1 
 
        self.setLayout(grid)
        grid.addWidget(self.display, 0,0,1,4)   
        grid.addWidget(self.digitButtons[0], 5, 0)
        grid.addWidget(self.clearAllButton, 1,0,1,1)
        grid.addWidget(self.backSpaceButton, 1,1,1,1)
        grid.addWidget(self.closeButton, 1,3,1,1)
        grid.addWidget(self.equalButton, 5,2,1,1)
       
        
        grid.addWidget(self.plusButton, 5,3,1,1)
        grid.addWidget(self.minusButton, 4,3,1,1)
        grid.addWidget(self.multipleButton, 3,3,1,1)
        grid.addWidget(self.devideButton, 2,3,1,1)
        grid.addWidget(self.pointButton, 5,1,1,1)

        
        grid.setSpacing(10)
        self.move(300, 150)
        self.setWindowTitle('Kalkulator')
        self.show()


    def pointClicked(self):
        if self.waitingForOperand:
            self.display.setText('0')

        if "." not in self.display.text():
            self.display.setText(self.display.text() + ".")

        self.waitingForOperand = False

    def additiveOperatorClicked(self):
        clickedButton = self.sender()
        clickedOperator = clickedButton.text()
        operand = float(self.display.text())

        if self.pendingMultiplicativeOperator:
            if not self.calculate(operand, self.pendingMultiplicativeOperator):
                self.abortOperation()
                return

            self.display.setText(str(self.factorSoFar))
            operand = self.factorSoFar
            self.factorSoFar = 0.0
            self.pendingMultiplicativeOperator = ''

        if self.pendingAdditiveOperator:
            if not self.calculate(operand, self.pendingAdditiveOperator):
                self.abortOperation()
                return

            self.display.setText(str(self.sumSoFar))
        else:
            self.sumSoFar = operand

        self.pendingAdditiveOperator = clickedOperator
        self.waitingForOperand = True

    def multiplicativeOperatorClicked(self):
        clickedButton = self.sender()
        clickedOperator = clickedButton.text()
        operand = float(self.display.text())

        if self.pendingMultiplicativeOperator:
            if not self.calculate(operand, self.pendingMultiplicativeOperator):
                self.abortOperation()
                return

            self.display.setText(str(self.factorSoFar))
        else:
            self.factorSoFar = operand

        self.pendingMultiplicativeOperator = clickedOperator
        self.waitingForOperand = True


    def calculate(self, rightOperand, pendingOperator):
        if pendingOperator == "+":
            self.sumSoFar += rightOperand
        elif pendingOperator == "-":
            self.sumSoFar -= rightOperand
        elif pendingOperator == u"\327":
            self.factorSoFar *= rightOperand
        elif pendingOperator == u"\367":
            if rightOperand == 0.0:
                return False

            self.factorSoFar /= rightOperand

        return True


    def equalClicked(self):
        operand = float(self.display.text())

        if self.pendingMultiplicativeOperator:
            if not self.calculate(operand, self.pendingMultiplicativeOperator):
                self.abortOperation()
                return

            operand = self.factorSoFar
            self.factorSoFar = 0.0
            self.pendingMultiplicativeOperator = ''

        if self.pendingAdditiveOperator:
            if not self.calculate(operand, self.pendingAdditiveOperator):
                self.abortOperation()
                return

            self.pendingAdditiveOperator = ''
        else:
            self.sumSoFar = operand

        self.display.setText(str(self.sumSoFar))
        self.sumSoFar = 0.0
        self.waitingForOperand = True


    def calculate(self, rightOperand, pendingOperator):
        if pendingOperator == "+":
            self.sumSoFar += rightOperand
        elif pendingOperator == "-":
            self.sumSoFar -= rightOperand
        elif pendingOperator == u"\327":
            self.factorSoFar *= rightOperand
        elif pendingOperator == u"\367":
            if rightOperand == 0.0:
                return False

            self.factorSoFar /= rightOperand

        return True

    def backSpace(self):
        if self.waitingForOperand:
            return

        text = self.display.text()[:-1]
        if not text:
            text = '0'
            self.waitingForOperand = True

        self.display.setText(text)

    def clearAll(self):
        self.sumSoFar = 0.0
        self.factorSoFar = 0.0
        self.pendingAdditiveOperator = ''
        self.pendingMultiplicativeOperator = ''
        self.display.setText('0')
        self.waitingForOperand = True

    def digitClicked(self):
        clickedButton = self.sender()
        digitValue = int(clickedButton.text())

        if self.display.text() == '0' and digitValue == 0.0:
            return
        if self.waitingForOperand:
            self.display.clear()
            self.waitingForOperand = False
        self.display.setText(self.display.text() + str(digitValue))

    def createButton(self, text, member):
        button = Button(text)
        button.clicked.connect(member)
        return button

    def digitClicked(self):
        clickedButton = self.sender()
        digitValue = int(clickedButton.text())

        if self.display.text() == '0' and digitValue == 0.0:
            return
        if self.waitingForOperand:
            self.display.clear()
            self.waitingForOperand = False
        self.display.setText(self.display.text() + str(digitValue))
        
class Button(QtGui.QToolButton):
    def __init__(self, text, parent=None):
        super(Button, self).__init__(parent)

        self.setSizePolicy(QtGui.QSizePolicy.Expanding,QtGui.QSizePolicy.Preferred)
        self.setText(text)

    def sizeHint(self):
        size = super(Button, self).sizeHint()
        size.setHeight(size.height() + 20)
        size.setWidth(max(size.width(), size.height()))
        return size
    
def main():
    app = QtGui.QApplication(sys.argv)
    ex = Example()
    ex.setWindowIcon(QtGui.QIcon('wpa.jpg'))
    sys.exit(app.exec_())

if __name__ == '__main__':
    main()
