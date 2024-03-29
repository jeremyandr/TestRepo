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

String filePath = userDir + GlobalVariable.Path

GlobalVariable.DataFilePath = filePath

if (Integer.parseInt(GlobalVariable.CountofUploadDocument) >= 1) {
    for (GlobalVariable.NumofUploadDocument = 2; GlobalVariable.NumofUploadDocument <= (Integer.parseInt(GlobalVariable.CountofUploadDocument) + 
    1); (GlobalVariable.NumofUploadDocument)++) {
        if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument').getValue(
            GlobalVariable.NumofUploadDocument, 2).equalsIgnoreCase(findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP1-CustomerData/TabCustomerData').getValue(
            GlobalVariable.NumofColm, 3))) {
		
			'click menu titik 3'
			WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/span_VIEW APPLICATION  0002APP20211201128_spanMenu'))

			'jika menu titik 3 disabled/enabled'
			if(WebUI.verifyElementPresent(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/a_New Document'),5,FailureHandling.OPTIONAL)){
				'click new document'
				WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/a_New Document'))
			}
			else{
				break
			}
			
            'upload file'
            CustomKeywords.'upload.uploadfile.uploadFile'(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/img_Upload _thumbnail thumbnail-change'), 
                findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument').getValue(
                    GlobalVariable.NumofUploadDocument, 3))

            'input document name'
            WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/input_Document Name  _gvMetadatactl02txtRecordValue'), 
                findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument').getValue(
                    GlobalVariable.NumofUploadDocument, 4))

            'select jenis document'
            WebUI.selectOptionByLabel(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/select_Jenis Document'), 
                findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument').getValue(
                    GlobalVariable.NumofUploadDocument, 5), false)

            'input MOU Id'
            WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/input_Mou Id_gvMetadatactl05txtRecordValue'), 
                findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument').getValue(
                    GlobalVariable.NumofUploadDocument, 6))

            'input notes'
            WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/input_Notes_gvMetadatactl06txtRecordValue'), 
                findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument').getValue(
                    GlobalVariable.NumofUploadDocument, 7))

            'click button save'
            WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/button_save'))

            CustomKeywords.'writetoexcel.writeToExcel.writeToExcelFunction'(GlobalVariable.DataFilePath, '12.TabUploadDocument', 
                0, GlobalVariable.NumofUploadDocument - 1, GlobalVariable.StatusSuccess)

            if (WebUI.verifyElementPresent(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/button_Close'),5, 
                FailureHandling.OPTIONAL)) {
                'click button close'
                WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/button_Close'))

                CustomKeywords.'writetoexcel.writeToExcel.writeToExcelFunction'(GlobalVariable.DataFilePath, 
                    '12.TabUploadDocument', 0, GlobalVariable.NumofUploadDocument - 1, GlobalVariable.StatusFailed)
            }
        }
    }
}

'click button submit'
WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/button_Submit'))


WebUI.delay(3)

if(WebUI.verifyElementNotVisible(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/a_CUSTOMER DATA COMPLETION'),FailureHandling.OPTIONAL)){
	if(WebUI.verifyElementNotVisible(findTestObject('LoginR3BranchManagerSuperuser/a_NewFinanceLeasing4W'),FailureHandling.OPTIONAL)){
		'click menu finance leasing 4w'
		WebUI.click(findTestObject('LoginR3BranchManagerSuperuser/a_FinanceLeasing4W'))
	}
	'click menu new finance leasing 4w'
	WebUI.click(findTestObject('LoginR3BranchManagerSuperuser/a_NewFinanceLeasing4W'))
}

'click menu Customer data completion paging'
WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/a_CUSTOMER DATA COMPLETION'))

WebUI.delay(3)
'input app no'
WebUI.setText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/input_Application No_AppNoId'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP1-CustomerData/TabCustomerData').getValue(
        GlobalVariable.NumofColm, 3))

'click button search'
WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/button_Search'))

'verify appno'
WebUI.verifyElementText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabUploadDocument/td_0002APP20211201128'), 
    findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP1-CustomerData/TabCustomerData').getValue(
        GlobalVariable.NumofColm, 3), FailureHandling.OPTIONAL)

