import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

String userDir = System.getProperty('user.dir')

String filePath = userDir + GlobalVariable.DataFileFamilyPersonal

GlobalVariable.DataFilePath = filePath

CustomKeywords.'opencloseExcel.opencloseExcel.Open_File'(GlobalVariable.DataFilePath)

def CountofFamily = findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP4-CustomerDataCompletion/FamilyPersonal/CustomerDetail - Personal - Family').getColumnNumbers()

WebUI.delay(10)

for (GlobalVariable.NumofFamily = 2; GlobalVariable.NumofFamily <= CountofFamily; (GlobalVariable.NumofFamily)++) {
    if ((findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP4-CustomerDataCompletion/FamilyPersonal/CustomerDetail - Personal - Family').getValue(
        GlobalVariable.NumofFamily, 2).equalsIgnoreCase(findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP1-CustomerData/TabCustomerData').getValue(
        GlobalVariable.NumofColm, 3))) && (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP4-CustomerDataCompletion/FamilyPersonal/CustomerDetail - Personal - Family').getValue(
        GlobalVariable.NumofFamily, 3).equalsIgnoreCase(WebUI.getText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP4-CustomerDataCompletion/CustomerPersonal/CustomerDetail - Personal/CustomerNameDetail'))))) {
        if(GlobalVariable.Role=="Data Entry"){
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabCustomerDetail - Personal - Family'),
				[:], FailureHandling.CONTINUE_ON_FAILURE)
	
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabAddressInformation - Personal - Family'),
				[:], FailureHandling.CONTINUE_ON_FAILURE)
	
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabJobData - Personal - Family'),
				[:], FailureHandling.CONTINUE_ON_FAILURE)
	
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabEmergencyContact - Personal - Family'),
				[:], FailureHandling.CONTINUE_ON_FAILURE)
	
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabFinancialData - Personal - Family'),
				[:], FailureHandling.CONTINUE_ON_FAILURE)
	
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabCustomerAsset - Personal - Family'),
				[:], FailureHandling.CONTINUE_ON_FAILURE)
	
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabOtherAttribute - Personal - Family'),
				[:], FailureHandling.CONTINUE_ON_FAILURE)
		}	
		else if(GlobalVariable.Role=="Testing"){
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabCustomerDetail - Personal - Family'),
				[:], FailureHandling.STOP_ON_FAILURE)
	
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabAddressInformation - Personal - Family'),
				[:], FailureHandling.STOP_ON_FAILURE)
	
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabJobData - Personal - Family'),
				[:], FailureHandling.STOP_ON_FAILURE)
	
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabEmergencyContact - Personal - Family'),
				[:], FailureHandling.STOP_ON_FAILURE)
	
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabFinancialData - Personal - Family'),
				[:], FailureHandling.STOP_ON_FAILURE)
	
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabCustomerAsset - Personal - Family'),
				[:], FailureHandling.STOP_ON_FAILURE)
	
			WebUI.callTestCase(findTestCase('NAP-FL4W-CustomerPersonal/NAP-FL4W-PersonalSingle/NAP4 - Customer Data Completion/FamilyPersonal/TabOtherAttribute - Personal - Family'),
				[:], FailureHandling.STOP_ON_FAILURE)
		}
		
    }
}

