import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.NumberFormat as NumberFormat
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import groovy.sql.Sql as Sql

int flagWarning = 0
'Assign directori file excel ke global variabel'
String userDir = System.getProperty('user.dir')

'Assign directori file excel ke global variabel'
String filePath = userDir + GlobalVariable.Path

'Assign directori file excel ke global variabel'
GlobalVariable.DataFilePath = filePath

'Click add asset'
WebUI.click(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Add Asset'))

WebUI.delay(5)

'click button supplier lookup'
WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Supplier Name_btn btn-raised btn-primary'))

'input supplier code'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_SupplierCode'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 3))

'input supplier name'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Supplier Name_supplierName'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 4))

'click search button'
WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Search Supplier'))

'verify input error'
if (WebUI.verifyElementPresent(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'), 
    5, FailureHandling.OPTIONAL)) {
    'click select'
    WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'))
} else {
    'click X'
    WebUI.click(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_XAccessories'))

    'click button cancel'
    WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Cancel'))

    CustomKeywords.'writetoexcel.writeToExcel.writeToExcelFunction'(GlobalVariable.DataFilePath, '7.TabAssetData', 0, GlobalVariable.NumofColm - 
        1, GlobalVariable.StatusFailed)

    'Pengecekan jika new consumer finance belum diexpand'
    if (WebUI.verifyElementNotVisible(findTestObject('LoginR3BranchManagerSuperuser/a_CUSTOMER MAIN DATA'), FailureHandling.OPTIONAL)) {
        'Klik new consumer finance'
        WebUI.click(findTestObject('LoginR3BranchManagerSuperuser/a_New Consumer Finance'))
    }
}

'select sales person'
WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/select_SalesPerson'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 5), false)

if(findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 6).length()>0){
	'select admin head'
	WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/select_AdminHead'),
		findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
			GlobalVariable.NumofColm, 6), false)
}


'click button asset lookup'
WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Asset Name_btn btn-raised btn-primary'))

'input asset name'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_FullAssetCode'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 8))

'click search button'
WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Search Asset'))

'verify input error'
if (WebUI.verifyElementPresent(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'), 
    5, FailureHandling.OPTIONAL)) {
    'click select'
    WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'))
} else {
    'click X'
    WebUI.click(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_XAccessories'))

    'click button cancel'
    WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Cancel'))

    CustomKeywords.'writetoexcel.writeToExcel.writeToExcelFunction'(GlobalVariable.DataFilePath, '7.TabAssetData', 0, GlobalVariable.NumofColm - 
        1, GlobalVariable.StatusFailed)

    'Pengecekan jika new consumer finance belum diexpand'
    if (WebUI.verifyElementNotVisible(findTestObject('LoginR3BranchManagerSuperuser/a_CUSTOMER MAIN DATA'), FailureHandling.OPTIONAL)) {
        'Klik new consumer finance'
        WebUI.click(findTestObject('LoginR3BranchManagerSuperuser/a_New Consumer Finance'))
    }
}

'select asset condition'
WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/select_Asset ConditionNew Used'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 9), false)

'input asset price'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Asset Price_assetPriceAmt'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 10))

'select asset usage'
WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/select_-Select One- Commercial  Non Commercial'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 11), false)

'input color'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Color'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 12))



if(findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 13).length()>0){
	'click button BPKB Lookup'
	WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_BPKB City Issuer_btn btn-raised btn-primary'))
	
	'input district code'
	WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_District Code'),
		findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
			GlobalVariable.NumofColm, 13))
	
	'click button search'
	WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_SearchBPKB city Issuer'))
	
	'verify input error'
	if (WebUI.verifyElementPresent(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'),
		5, FailureHandling.OPTIONAL)) {
		'click select'
		WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'))
	} else {
		'click X'
		WebUI.click(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_XAccessories'))
		
		if(findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 9)=="New"){
		
			flagWarning++
	
		}
	}
	
}
	

'input BPKP issue date'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_BPKB Issue Date'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 14))

'input notes'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/textarea_Notes'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 15))

'input manufacturing year'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Manufacturing Year'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 16))

'select security deposit type'
WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/Select_Downpayment Asset Information'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 17), false)


if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
    GlobalVariable.NumofColm, 17) == 'Percentage') {

	'Untuk handle jika field input percentage terlock'
	WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/Select_Downpayment Asset Information'),
	"Amount", false)
	WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/Select_Downpayment Asset Information'),
		"Percentage", false)

    WebUI.sendKeys(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Down Payment (Amt)_downPaymentPrctg'), 
        Keys.chord(Keys.CONTROL, 'a'))

    WebUI.sendKeys(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Down Payment (Amt)_downPaymentPrctg'), 
        Keys.chord(Keys.BACK_SPACE))

    WebUI.sendKeys(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Down Payment (Amt)_downPaymentPrctg'), 
        Keys.chord(Keys.HOME))

    WebUI.sendKeys(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Down Payment (Amt)_downPaymentPrctg'), 
        Keys.chord(Keys.RIGHT, findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
                GlobalVariable.NumofColm, 18)))
} else if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
    GlobalVariable.NumofColm, 17) == 'Amount') {
    'input security deposit amount'
    WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Down Payment (Amt)_downPaymentAmt'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 19))
}

'input chasis number'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_CHASIS NUMBER'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 20))

'input engine number'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_ENGINE NUMBER'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 21))

NumberFormat decimalFormat = NumberFormat.getPercentInstance()

def AssetPrice = WebUI.getAttribute(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Asset Price_assetPriceAmt'), 
    'value').split(',').join()

def DownPaymentAmt = WebUI.getAttribute(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Down Payment (Amt)_downPaymentAmt'), 
    'value').split(',').join()

def DownPaymentPrctg = WebUI.getAttribute(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Down Payment (Amt)_downPaymentPrctg'), 
    'value').replaceAll('\\s', '')

BigDecimal intDownPaymentAmt = Integer.parseInt(DownPaymentAmt)

BigDecimal intAssetPrice = Integer.parseInt(AssetPrice)

float floatDownPaymentPrctg = decimalFormat.parse(DownPaymentPrctg)

Number NumberDownPaymentPrctg = decimalFormat.parse(DownPaymentPrctg)

if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
    GlobalVariable.NumofColm, 15) == 'Percentage') {
    int multiplyAssetPricexDownPaymentPrctg = intAssetPrice * NumberDownPaymentPrctg

    'verify security deposit value equal'
    WebUI.verifyEqual(multiplyAssetPricexDownPaymentPrctg, intDownPaymentAmt)
} else if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
    GlobalVariable.NumofColm, 15) == 'Amount') {
    float divideDownPaymentAmtAssetPrice = intDownPaymentAmt / intAssetPrice

    'verify security deposit value equal'
    WebUI.verifyEqual(divideDownPaymentAmtAssetPrice, floatDownPaymentPrctg)
}

'input license plate number'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_LICENSE PLATE NUMBER'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 22))

if(WebUI.verifyElementPresent(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Serial4'), 5, FailureHandling.OPTIONAL)){
	
'input serial 4'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Serial4'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 23), FailureHandling.OPTIONAL)
}


if(WebUI.verifyElementPresent(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Serial5'), 5, FailureHandling.OPTIONAL)){
'input serial 5'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Serial5'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 24), FailureHandling.OPTIONAL)
}

if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
    GlobalVariable.NumofColm, 9) == 'Used') {
	if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
		GlobalVariable.NumofColm, 62) == 'Yes') {
		'click button check rapindo'
		WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_CheckRapindo'))
	
	}
}

//'select region'
//WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/select_--Select One--  REGION1  REGION2  REGION3'), 
//    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
//        GlobalVariable.NumofColm, 26), false)

String servername = findTestData('Login/Login').getValue(1, 8)
	
String instancename = findTestData('Login/Login').getValue(2, 8)
	
String username = findTestData('Login/Login').getValue(3, 8)
	
String password = findTestData('Login/Login').getValue(4, 8)
	
String database = findTestData('Login/Login').getValue(5, 8)
	
String driverclassname = findTestData('Login/Login').getValue(6, 8)
	
String url = (((servername + ';instanceName=') + instancename) + ';databaseName=') + database

println(url)
	
'connect DB'
Sql sqlconnection = CustomKeywords.'dbconnection.connectDB.connect'(url, username, password, driverclassname)
	
String Fullassetcode = findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
		GlobalVariable.NumofColm, 8)
	
'count asset attribute'
String countAssetAtrtibute = CustomKeywords.'dbconnection.CountRowAssetAttribute.countRowAssetAttribute'(sqlconnection,
		Fullassetcode)


for (i = 1; i <= Integer.parseInt(countAssetAtrtibute); i++) {
	String newAssetAtrributeInput = ('//*[@id="RefAttrContent"]/div[2]/div/div[' + i) + ']/div/div/input'

	'modify button Asset Attribute Input'
	modifyObjectAssetAttributeInput = WebUI.modifyObjectProperty(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/AssetAttribute'),
		'xpath', 'equals', newAssetAtrributeInput, true)

	if (WebUI.verifyElementPresent(modifyObjectAssetAttributeInput, 2, FailureHandling.OPTIONAL)) {
		WebUI.setText(modifyObjectAssetAttributeInput, 'Attribute', FailureHandling.OPTIONAL)
	} else {
		String newAssetAtrributeSelect = ('//*[@id="RefAttrContent"]/div[2]/div/div[' + i) + ']/div/div/select'

		'modify button Asset Attribute Select'
		modifyObjectAssetAttributeSelect = WebUI.modifyObjectProperty(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/AssetAttribute'),
			'xpath', 'equals', newAssetAtrributeSelect, true)

		'select option index 1'
		WebUI.selectOptionByIndex(modifyObjectAssetAttributeSelect, 1, FailureHandling.OPTIONAL)
		
	}
}

int modifyObjectIndex = 1
GlobalVariable.AssetPrice = 0.00
GlobalVariable.TotalAccessoriesPrice = 0.00
for (GlobalVariable.NumofAccessories = 2; GlobalVariable.NumofAccessories <= (Integer.parseInt(GlobalVariable.CountofAccessories) + 
1); (GlobalVariable.NumofAccessories)++) {
    if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
        GlobalVariable.NumofAccessories, 2).equalsIgnoreCase(findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP1-CustomerData/TabCustomerData').getValue(
        GlobalVariable.NumofColm, 3))) {
        'click button add'
        WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Add'))

        String newButtonSupplierXpath = ('//*[@id="accessoriesData"]/div[2]/table/tbody/tr[' + modifyObjectIndex) + ']/td[2]/lib-uclookupgeneric/div/div/div[1]/span/button'

        'modify button supplier lookup'
        modifyObjectButtonSupplier = WebUI.modifyObjectProperty(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Lookup Supplier'), 
            'xpath', 'equals', newButtonSupplierXpath, true)

        String newButtonAccessoriesXpath = ('//*[@id="accessoriesData"]/div[2]/table/tbody/tr[' + modifyObjectIndex) + ']/td[3]/lib-uclookupgeneric/div/div/div[1]/span/button'

        'modify button accessories lookup'
        modifyObjectButtonAccessories = WebUI.modifyObjectProperty(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Lookup Accessories'), 
            'xpath', 'equals', newButtonAccessoriesXpath, true)

        String newAccessoriesPriceXpath = ('//*[@id="accessoryPriceAmt' + (modifyObjectIndex - 1)) + '"]'

        'modify input accessories price amount'
        modifyObjectAccessoriesPrice = WebUI.modifyObjectProperty(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_accessoryPriceAmt0'), 
            'xpath', 'equals', newAccessoriesPriceXpath, true)

        String newDownPaymentTypeXpath = ('//*[@id="accessoriesData"]/div[2]/table/tbody/tr[' + modifyObjectIndex) + ']/td[5]/select'

        'modify select security deposit type'
        modifyObjectDownPaymentType = WebUI.modifyObjectProperty(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/select_-Select One- Amount  Percentage'), 
            'xpath', 'equals', newDownPaymentTypeXpath, true)

        String newInputPercentageXpath = ('//*[@id="AccessoryDownPaymentPrcnt' + (modifyObjectIndex - 1)) + '"]'

        'modify input security deposit percentage'
        modifyObjectInputPercentage = WebUI.modifyObjectProperty(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_AccessoryDownPaymentPrcnt0'), 
            'xpath', 'equals', newInputPercentageXpath, true)

        String newInputAmountXpath = ('//*[@id="AccessoryDownPaymentAmt' + (modifyObjectIndex - 1)) + '"]'

        'modify input security deposit amount'
        modifyObjectInputAmount = WebUI.modifyObjectProperty(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_AccessoryDownPaymentAmt0'), 
            'xpath', 'equals', newInputAmountXpath, true)

        String newNoteAccessoriesXpath = ('//*[@id="accessoriesData"]/div[2]/table/tbody/tr[' + modifyObjectIndex) + ']/td[7]/textarea'

        'modify input note'
        modifyObjectInputNoteAccessories = WebUI.modifyObjectProperty(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/textarea_Note Accessories'), 
            'xpath', 'equals', newNoteAccessoriesXpath, true)
		
		String newButtonDeleteXpath = '//*[@id="accessoriesData"]/div[2]/table/tbody/tr[' +modifyObjectIndex+ ']/td[8]/a/i'
		 
		 'modify button delete'
		 modifyObjectButtonDelete = WebUI.modifyObjectProperty(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Delete'),
			 'xpath', 'equals', newButtonDeleteXpath, true)

        'click lookup supplier'
        WebUI.click(modifyObjectButtonSupplier, FailureHandling.OPTIONAL)

        'input Supplier Code'
        WebUI.setText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_SupplierCodeAccessories'), 
            findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
                GlobalVariable.NumofAccessories, 3))

        'input supplier name'
        WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Supplier Name_supplierName'), 
            findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
                GlobalVariable.NumofAccessories, 4))

        'click button search'
        WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Search (1)'))

        if (WebUI.verifyElementPresent(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'), 
            5, FailureHandling.OPTIONAL)) {
            'click select'
            WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'))
        } else {
            'click button x'
            WebUI.click(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_XAccessories'))

            CustomKeywords.'writetoexcel.writeToExcel.writeToExcelFunction'(GlobalVariable.DataFilePath, '7a.Accessories', 
                0, GlobalVariable.NumofAccessories - 1, GlobalVariable.StatusWarning)
			
			'click delete'
			WebUI.click(modifyObjectButtonDelete,FailureHandling.OPTIONAL)
			
			'click ok pada alert'
			WebUI.acceptAlert(FailureHandling.OPTIONAL)

            continue
        }
        
        'click button accessories lookup'
        WebUI.click(modifyObjectButtonAccessories, FailureHandling.OPTIONAL)

        'input Accessories Code'
        WebUI.setText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_AssetAccessoriesCode'), 
            findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
                GlobalVariable.NumofAccessories, 5))

        'input accessories name'
        WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Asset Accessory Name_accessoryName'), 
            findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
                GlobalVariable.NumofAccessories, 6))

        'click button search'
        WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Search (1)'))

        if (WebUI.verifyElementPresent(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'), 
            5, FailureHandling.OPTIONAL)) {
            'click select'
            WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'))
        } else {
            'click button x'
            WebUI.click(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_XAccessories'))

            CustomKeywords.'writetoexcel.writeToExcel.writeToExcelFunction'(GlobalVariable.DataFilePath, '7a.Accessories', 
                0, GlobalVariable.NumofAccessories - 1, GlobalVariable.StatusWarning)
			
			'click delete'
			WebUI.click(modifyObjectButtonDelete,FailureHandling.OPTIONAL)
			
			'click ok pada alert'
			WebUI.acceptAlert(FailureHandling.OPTIONAL)

            continue
        }
        
        'input accessories price'
        WebUI.setText(modifyObjectAccessoriesPrice, findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
                GlobalVariable.NumofAccessories, 7), FailureHandling.OPTIONAL)

        'select security deposit type'
        WebUI.selectOptionByLabel(modifyObjectDownPaymentType, findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
                GlobalVariable.NumofAccessories, 8), false, FailureHandling.OPTIONAL)

        if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
            GlobalVariable.NumofAccessories, 8) == 'Percentage') {
            'input security deposit percentage'
            WebUI.sendKeys(modifyObjectInputPercentage, Keys.chord(Keys.CONTROL, 'a'), FailureHandling.OPTIONAL)

            WebUI.sendKeys(modifyObjectInputPercentage, Keys.chord(Keys.BACK_SPACE), FailureHandling.OPTIONAL)

            WebUI.sendKeys(modifyObjectInputPercentage, Keys.chord(Keys.HOME), FailureHandling.OPTIONAL)

            WebUI.sendKeys(modifyObjectInputPercentage, Keys.chord(Keys.RIGHT, findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
                        GlobalVariable.NumofAccessories, 9)), FailureHandling.OPTIONAL)
        } else if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
            GlobalVariable.NumofAccessories, 8) == 'Amount') {
            'input security deposit amount'
            WebUI.setText(modifyObjectInputAmount, findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
                    GlobalVariable.NumofAccessories, 10), FailureHandling.OPTIONAL)
        }
        
        'input accessories notes'
        WebUI.setText(modifyObjectInputNoteAccessories, findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
                GlobalVariable.NumofAccessories, 11), FailureHandling.OPTIONAL)
		
		if(WebUI.getAttribute(modifyObjectAccessoriesPrice,'value')==""||WebUI.getAttribute(modifyObjectInputPercentage,'value')==""||WebUI.getAttribute(modifyObjectInputAmount,'value')==""){
			'write to excel WARNING'
			CustomKeywords.'writetoexcel.writeToExcel.writeToExcelFunction'(GlobalVariable.DataFilePath, '7a.Accessories',
				0, GlobalVariable.NumofAccessories - 1, GlobalVariable.StatusWarning)
			
			'click delete'
			WebUI.click(modifyObjectButtonDelete,FailureHandling.OPTIONAL)
			
			'click ok pada alert'
			WebUI.acceptAlert(FailureHandling.OPTIONAL)

			continue
		}

        modifyObjectIndex++

        NumberFormat decimalFormatAccessories = NumberFormat.getPercentInstance()

        def AccessoriesPrice = WebUI.getAttribute(modifyObjectAccessoriesPrice, 'value').split(',').join()

        def AccessoriesInputPrctg = WebUI.getAttribute(modifyObjectInputPercentage, 'value').replaceAll('\\s', '')

        def AccessoriesInputAmt = WebUI.getAttribute(modifyObjectInputAmount, 'value').split(',').join()

        BigDecimal BDAccessoriesPrice = Integer.parseInt(AccessoriesPrice)

        float floatBDAccessoriesInputPrctg = decimalFormatAccessories.parse(AccessoriesInputPrctg).floatValue()

        Number NumberBDAccessoriesInputPrctg = decimalFormatAccessories.parse(AccessoriesInputPrctg)

        BigDecimal BDAccessoriesInputAmt = Integer.parseInt(AccessoriesInputAmt)

        if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
            GlobalVariable.NumofAccessories, 8) == 'Percentage') {
            int multiplyAccessoriesPricexDownPaymentPrctg = BDAccessoriesPrice * NumberBDAccessoriesInputPrctg

            'verify securitydeposit value equal'
            WebUI.verifyEqual(multiplyAccessoriesPricexDownPaymentPrctg, BDAccessoriesInputAmt)
        } else if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/Accessories').getValue(
            GlobalVariable.NumofAccessories, 8) == 'Amount') {
            float divideDownPaymentAmtAccessoriesPrice = BDAccessoriesInputAmt / BDAccessoriesPrice

            'verify securitydeposit value equal'
            WebUI.verifyEqual(divideDownPaymentAmtAccessoriesPrice, floatBDAccessoriesInputPrctg)
        }
        GlobalVariable.TotalAccessoriesPrice+=(BDAccessoriesPrice.doubleValue())
        'write to excel success'
        CustomKeywords.'writetoexcel.writeToExcel.writeToExcelFunction'(GlobalVariable.DataFilePath, '7a.Accessories', 0, 
            GlobalVariable.NumofAccessories - 1, GlobalVariable.StatusSuccess)
    }
}

if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
    GlobalVariable.NumofColm, 28) == 'Yes') {
    'click self usage check box'
    WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/div_Self Usage Checkbox'))
} else {
    'input user name'
    WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_User Name_form-control ng-untouched ng-pristine ng-valid'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 29))

    'select user relationship'
    WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/select_User Relationship'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 30), false)
}

if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
    GlobalVariable.NumofColm, 32) == 'Yes') {
    'click self owner checkbox'
    WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/div_Self Owner CheckBox'))
} else if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
    GlobalVariable.NumofColm, 32) == 'No') {
    if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 33) == 'Personal') {
        'click radio button personal'
        WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/div_Personal'))
    } else if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 33) == 'Company') {
        'click radio button company'
        WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/div_Company'))
    }

    'input owner name'
    WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Owner Name_form-control ng-untouched ng-pristine ng-invalid'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 34))

    'select owner relationship'
    WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/select_Owner Relationship'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 35), false)

    if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 33) == 'Personal') {
        if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 37).length() > 1) {
            'click button owner profession'
            WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Owner Profession_btn btn-raised btn-primary'))

            'input profession code'
            WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Profession Code_professionCodeId'), 
                findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
                    GlobalVariable.NumofColm, 36))

            'click button search'
            WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Search'))

            'verify input error'
            if (WebUI.verifyElementPresent(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'), 
                5, FailureHandling.OPTIONAL)) {
                'click select'
                WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'))
            } else {
                'click X'
                WebUI.click(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_XAccessories'))
            }
        }
    } else if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 33) == 'Company') {
        'select company owner profession'
        WebUI.selectOptionByLabel(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/select_Company Owner Profession'), 
            findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
                GlobalVariable.NumofColm, 38), false)
    }
    
    'select id type'
    WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/select_-ID Type'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 39), false)

    'input id no'
    WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Owner Id No'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 40))

    'input owner mobile no'
    WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Owner Mobile Phone No'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 41))

    if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
        GlobalVariable.NumofColm, 42) == 'Yes') {
        'select copy address'
        WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/select_-Select One- JOB  LEGAL  MAILING  RESIDENCE'), 
            findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
                GlobalVariable.NumofColm, 43), false)

        'click button copy'
        WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Copy'))
    } else {
        'input address'
        WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/textarea_Address_form-control ng-untouched ng-pristine ng-invalid'), 
            findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
                GlobalVariable.NumofColm, 44))

        'input RT'
        WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_RT'), 
            findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
                GlobalVariable.NumofColm, 45))

        'input RW'
        WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_RW'), 
            findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
                GlobalVariable.NumofColm, 46))

        'click button Zipcode'
        WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Zipcode_btn btn-raised btn-primary'))

        'input Zipcode'
        WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Zipcode_form-control ng-untouched ng-pristine ng-invalid'), 
            findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
                GlobalVariable.NumofColm, 47))

        'input Kecamatan'
        WebUI.setText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_KecamatanAssetOwner'), 
            findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
                GlobalVariable.NumofColm, 49))

        'input kelurahan'
        WebUI.setText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_KelurahanAssetOwner'), 
            findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
                GlobalVariable.NumofColm, 48))

        'input Kota'
        WebUI.setText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_KotaAssetOwner'), 
            findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
                GlobalVariable.NumofColm, 50))

        'click search button'
        WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Search Supplier'))

        'verify input error'
        if (WebUI.verifyElementPresent(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'), 
            5, FailureHandling.OPTIONAL)) {
            'click select'
            WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'))
        } else {
            'click X'
            WebUI.click(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_XAccessories'))

            'click button cancel'
            WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Cancel'))

            CustomKeywords.'writetoexcel.writeToExcel.writeToExcelFunction'(GlobalVariable.DataFilePath, '7.TabAssetData', 
                0, GlobalVariable.NumofColm - 1, GlobalVariable.StatusFailed)

            'Pengecekan jika new consumer finance belum diexpand'
            if (WebUI.verifyElementNotVisible(findTestObject('LoginR3BranchManagerSuperuser/a_CUSTOMER MAIN DATA'), FailureHandling.OPTIONAL)) {
                'Klik new consumer finance'
                WebUI.click(findTestObject('LoginR3BranchManagerSuperuser/a_New Consumer Finance'))
            }
        }
    }
}

if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
    GlobalVariable.NumofColm, 52) == 'Yes') {
    'select copy address'
    WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/AssetLocation _ Copy Address'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 53), false)

    'click button copy'
    WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/AssetLocation_ButtonCopy'))
} else {
    'input address'
    WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/AssetLocation_AddressText'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 54))

    'input RT'
    WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/AssetLocation_InputRT'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 55))

    'input RW'
    WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/AssetLocation_InputRW'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 56))

    'click button zipcode'
    WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/AssetLocation_ButtonZipcode'))

    'input Zipcode'
    WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/AssetLocation_inptZipcode'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 57))

    'input Kecamatan'
    WebUI.setText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/AssetLocation_inputKecamatan'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 59))

    'input kelurahan'
    WebUI.setText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/AssetLocation_inputKelurahan'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 58))

    'input Kota'
    WebUI.setText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/AssetLocation_inputKota'), 
        findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
            GlobalVariable.NumofColm, 60))

    'click search button'
    WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Search Supplier'))

    'verify input error'
    if (WebUI.verifyElementPresent(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'), 
        5, FailureHandling.OPTIONAL)) {
        'click select'
        WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/a_Select'))
    } else {
        'click X'
        WebUI.click(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_XAccessories'))

        'click button cancel'
        WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Cancel'))

        CustomKeywords.'writetoexcel.writeToExcel.writeToExcelFunction'(GlobalVariable.DataFilePath, '7.TabAssetData', 0, 
            GlobalVariable.NumofColm - 1, GlobalVariable.StatusFailed)

        'Pengecekan jika new consumer finance belum diexpand'
        if (WebUI.verifyElementNotVisible(findTestObject('LoginR3BranchManagerSuperuser/a_CUSTOMER MAIN DATA'), FailureHandling.OPTIONAL)) {
            'Klik new consumer finance'
            WebUI.click(findTestObject('LoginR3BranchManagerSuperuser/a_New Consumer Finance'))
        }
    }
}

GlobalVariable.AssetPrice += Double.parseDouble(WebUI.getAttribute(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/input_Asset Price_assetPriceAmt'),"value").replace(",",""))
println(GlobalVariable.AssetPrice)
println(GlobalVariable.TotalAccessoriesPrice)
'click button save'
WebUI.click(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Save'))


'Menunggu Alert security deposit dibawah minimum atau manufacturing year dibawah angka tertentu (jika ada) muncul'
WebUI.waitForAlert(3)
'Accept Alert Konfirmasi Security deposit dibawah minimum atau manufacturing year dibawah angka tertentu'
WebUI.acceptAlert(FailureHandling.OPTIONAL)


if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData').getValue(
	GlobalVariable.NumofColm, 62) == 'No') {
		'Menunggu Alert konfirmasi integrator muncul'
		WebUI.waitForAlert(3)
		'Accept Alert Konfirmasi Integrator'
		WebUI.acceptAlert(FailureHandling.OPTIONAL)
}



WebUI.delay(20)

if (WebUI.verifyElementPresent(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Lookup Supplier'),5,FailureHandling.OPTIONAL)) {
    'click button cancel'
    WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Cancel'))

	
	'Click cancel'
	WebUI.click(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_CancelAssetCollateral'))
	
    CustomKeywords.'writetoexcel.writeToExcel.writeToExcelFunction'(GlobalVariable.DataFilePath, '7.TabAssetData', 0, GlobalVariable.NumofColm - 
        1, GlobalVariable.StatusFailed)
	
	
} else {
    CustomKeywords.'writetoexcel.writeToExcel.writeToExcelFunction'(GlobalVariable.DataFilePath, '7.TabAssetData', 0, GlobalVariable.NumofColm - 
        1, GlobalVariable.StatusSuccess)
	if(flagWarning>0){
		CustomKeywords.'writetoexcel.writeToExcel.writeToExcelFunction'(GlobalVariable.DataFilePath, '7.TabAssetData', 0, GlobalVariable.NumofColm -
			1, GlobalVariable.StatusWarning)
	}
	
	'Click save'
	WebUI.click(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabAssetData/button_Savecontinue'))
}

WebUI.delay(5)
