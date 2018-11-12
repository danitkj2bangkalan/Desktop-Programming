import sys
#module di python yang menyediakan akses ke beberapa variabel yang digunakan atau dipelihara oleh interpreter dan fungsi yang bersinteraksi kuat dengan interpreter. dan selalu tersedia.  
from PySide.QtGui import *
from PySide.QtCore import *
#dari module pyside mengimport class QtGui dan QtCore beserta semua function yang ada di QtGui dan QtCore 
from PySide.QtSql import *
#dari module pyside mengimport QtSql beserta semua function semua 
ID, FIRST_NAME, LAST_NAME, AGE, SEX, INCOME = range(6)
#membuat table panjang 6
class EmployeeForm(QDialog):
    # membuat class EmplyeeFrom turunan dari class QDialog
    FIRST, PREV, NEXT, LAST = range(4)
    #membuat table panjang 4
    def __init__(self):
        #membuat fungsi constructor dari class EmployeeForm
        QDialog.__init__(self)
        #memanggil constructor class QDialog
        firstNameEdit = QLineEdit()
        firstNameLabel = QLabel("First Name:")
        firstNameLabel.setBuddy(firstNameEdit)
        #membuat QlineEdit dan QLabel untuk FirstName, dan mengirim keyboard focus ke firstNameEdit di setBuddy
        lastNameEdit = QLineEdit()
        lastNameLabel = QLabel("Last Name:")
        lastNameLabel.setBuddy(lastNameEdit)
        #membuat QlineEdit dan QLabel untuk FirstName, dan mengirim keyboard focus ke lastNameEdit di setBuddy
        ageEdit = QLineEdit()
        ageLabel = QLabel("Age:")
        ageLabel.setBuddy(ageEdit)
        #membuat QlineEdit dan QLabel untuk FirstName, dan mengirim keyboard focus ke AgeEdit di setBuddy
        genderEdit = QLineEdit()
        genderLabel = QLabel("Gender:")
        genderLabel.setBuddy(genderEdit)
        #membuat QlineEdit dan QLabel untuk FirstName, dan mengirim keyboard focus ke genderEdit di setBuddy
        incomeEdit = QLineEdit()
        incomeLabel = QLabel("Income:")
        incomeLabel.setBuddy(incomeEdit)
        #membuat QlineEdit dan QLabel untuk FirstName, dan mengirim keyboard focus ke incomeEdit di setBuddy
        firstButton = QPushButton("<< First")
        previousButton = QPushButton("< Previous")
        nextButton = QPushButton("> Next")
        lastButton = QPushButton(">> Last")
        addButton = QPushButton("&Add")
        addButton.setIcon(QIcon("add.png"))
        deleteButton = QPushButton("&Delete")
        deleteButton.setIcon(QIcon("delete.png"))
        quitButton = QPushButton("&Quit")
        quitButton.setIcon(QIcon("quit.png"))
        #Qpushbutton membuat button di pyside dengan argumen berupa nama dari button itu, setIcon() digunaka untuk menambah icon di qpushbuttonnya
        fieldLayout = QGridLayout()
        #membuat layout dengan gridlayout
        fieldLayout.addWidget(firstNameLabel, 0, 0)
        fieldLayout.addWidget(firstNameEdit, 0, 1, 1, 3)
        fieldLayout.addWidget(lastNameLabel, 1, 0)
        fieldLayout.addWidget(lastNameEdit, 1, 1, 1, 3)
        fieldLayout.addWidget(ageLabel, 2, 0)
        fieldLayout.addWidget(ageEdit, 2, 1)
        fieldLayout.addWidget(genderLabel, 2, 2)
        fieldLayout.addWidget(genderEdit, 2, 3)
        fieldLayout.addWidget(incomeLabel, 3, 0)
        fieldLayout.addWidget(incomeEdit, 3, 1, 1, 3)
        #mengatur tata letak dari komponen untuk UI di gridlayout
        navigationLayout = QHBoxLayout()
        #membuat layout dengan horizontal layout
        navigationLayout.addWidget(firstButton)
        navigationLayout.addWidget(previousButton)
        navigationLayout.addWidget(nextButton)
        navigationLayout.addWidget(lastButton)
        #mengatur tata letak dari komponen untuk UI di horizontal layout
        fieldLayout.addLayout(navigationLayout, 4, 0, 1, 2)
        buttonLayout = QVBoxLayout()
        #membuat layout dengan vertical layout
        buttonLayout.addWidget(addButton)
        buttonLayout.addWidget(deleteButton)
        buttonLayout.addStretch()
        buttonLayout.addWidget(quitButton)
        #mengatur tata letak dari komponen untuk UI di gridlayout
        layout = QHBoxLayout()
        #membuat layout dengan horizontal layout
        layout.addLayout(fieldLayout)
        layout.addLayout(buttonLayout)
        self.setLayout(layout)
        #mengatur layout utama dengan layout horizontal
        
        self.model = QSqlTableModel(self)
        self.model.setTable("employee")
        self.model.setSort(FIRST_NAME, Qt.AscendingOrder)
        self.model.select()
        self.mapper = QDataWidgetMapper(self)
        self.mapper.setSubmitPolicy(QDataWidgetMapper.ManualSubmit)
        self.mapper.setModel(self.model)
        self.mapper.addMapping(firstNameEdit, FIRST_NAME)
        self.mapper.addMapping(lastNameEdit, LAST_NAME)
        self.mapper.addMapping(ageEdit, AGE)
        self.mapper.addMapping(genderEdit, SEX)
        self.mapper.addMapping(incomeEdit, INCOME)
        self.mapper.toFirst()
        
        self.connect(firstButton, SIGNAL("clicked()"),
                lambda: self.saveRecord(EmployeeForm.FIRST))
        self.connect(previousButton, SIGNAL("clicked()"),
                lambda:self.saveRecord(EmployeeForm.PREV))
        self.connect(nextButton, SIGNAL("clicked()"),
                lambda:self.saveRecord(EmployeeForm.NEXT))
        self.connect(lastButton, SIGNAL("clicked()"),
                lambda:self.saveRecord(EmployeeForm.LAST))
        self.connect(addButton, SIGNAL("clicked()"),
                lambda:self.addRecord)
        self.connect(deleteButton, SIGNAL("clicked()"),
                lambda:self.deleteRecord)
        self.connect(quitButton, SIGNAL("clicked()"),self.done)
        
        self.setWindowTitle("Employee Details")
        #memberi judul pada window
        
    def done(self, result=None):
        self.mapper.submit()
        QDialog.done(self, True)
        
    def addRecord(self):
        row = self.model.rowCount()
        self.mapper.submit()
        self.model.insertRow(row)
        self.mapper.setCurrentIndex(row)
        
    def deleteRecord(self):
        row = self.mapper.currentIndex()
        self.model.removeRow(row)
        self.model.submitAll()
        if row + 1 >= self.model.rowCount():
            row = self.model.rowCount() - 1
        self.mapper.setCurrentIndex(row)
        
    def saveRecord(self, where):
        row = self.mapper.currentIndex()
        self.mapper.submit()
        if where == EmployeeForm.FIRST:
            row = 0
        elif where == EmployeeForm.PREV:
            row = 0 if row <= 1 else row - 1
        elif where == EmployeeForm.NEXT:
            row += 1
            if row >= self.model.rowCount():
                row = self.model.rowCount() - 1
        elif where == EmployeeForm.LAST:
            row = self.model.rowCount() - 1
        self.mapper.setCurrentIndex(row)

def initializeModel(model):
    model.setTable("employee")
    model.setEditStrategy(QSqlTableModel.OnManualSubmit)
    model.select()
    model.setHeaderData(0, Qt.Horizontal, "ID")
    model.setHeaderData(1, Qt.Horizontal, "First Name")
    model.setHeaderData(2, Qt.Horizontal, "Last Name")
    model.setHeaderData(3, Qt.Horizontal, "Age")
    model.setHeaderData(4, Qt.Horizontal, "Gender")
    model.setHeaderData(5, Qt.Horizontal, "Income")
def createView(title, model):
    view = QTableView()
    view.setModel(model)
    view.setWindowTitle(title)
    return view
def createConnection():
    db = QSqlDatabase.addDatabase('QSQLITE')
    db.setDatabaseName('sample.db')
    ok = db.open()
    if not ok:
        return False
    myQuery = QSqlQuery()
    myQuery.exec_("""CREATE TABLE employee (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, first_name CHAR(20) NOT NULL,last_name CHAR(20), age INT, sex CHAR(1), income FLOAT)""")
    myQuery.exec_("""INSERT INTO employee (first_name, last_name, age, sex, income) VALUES ('Alice', 'A', 30, 'F', 5000.00)""")
    myQuery.exec_("""INSERT INTO employee (first_name, last_name, age, sex, income) VALUES ('Bob', 'B', 31, 'M', 5100.00)""")
    myQuery.exec_("""INSERT INTO employee (first_name, last_name, age, sex, income) VALUES ('Caesar', 'C', 32, 'F', 5200.00)""")
    myQuery.exec_("""INSERT INTO employee (first_name, last_name, age, sex, income) VALUES ('Danny', 'D', 34, 'M', 5300.00)""")
    myQuery.exec_("""INSERT INTO employee (first_name, last_name, age, sex, income) VALUES ('Eziekel', 'E', 35, 'F', 5400.00)""")
    return True
if __name__ =='__main__':
    try:
        myApp = QApplication(sys.argv)
        if not createConnection():
            print("Error Connecting to Database")
            sys.exit(1)
        myForm = EmployeeForm()
        myForm.show()
        model = QSqlTableModel()
        initializeModel(model)
        view1 = createView("Table Model - Example1", model)
        view1.setGeometry(100, 100, 500, 220)
        view1.show()
        myApp.exec_()
        sys.exit(0)
    except NameError:
        print("Name Error:", sys.exc_info()[1])
    except SystemExit:
        print("Closing Window...")
