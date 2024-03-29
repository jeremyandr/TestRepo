import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.math.RoundingMode

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable


'declare NTFforProvisionCalc Value'
BigDecimal NTFforProvisionCalc
'declare SubsidyDP Value'
String SubsidyDPValue = '0'

'get total asset price'
def TotalAssetPrice = WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_TotalAssetPrice(InclAccessory)'),
	FailureHandling.OPTIONAL).replace(',', '').replaceAll('\\s', '').replace('.00', '')

	'get DPAssetAccessory'
def DPAssetAccessory = (WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_DP ASSET  ACCESSORY  () DP'),
	FailureHandling.OPTIONAL).replace(',', '').replaceAll('\\s', '').split('/')[0]).replace('.00', '')

'get total fee capitalized value'
TotalfeeCapitalized = WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_TOTAL FEE CAPITALIZED'),
		FailureHandling.OPTIONAL).replace(',', '').replace('.00', '')
'get provision fee capitalized value'
ProvisionFeeCapitalize = WebUI.getAttribute(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/input_Provision Fee Capitalize'),
		'value', FailureHandling.OPTIONAL).replace(',', '').replace('.00', '')
'get total insurancecap value'
TotalInsuranceCap = WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_TOTAL INSURANCE CAPITALIZED'),
		FailureHandling.OPTIONAL).replace(',', '').replace('.00', '')
	
'get provision percentage value'
ProvisionFeePercentage = WebUI.getAttribute(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/input_Provision Fee Percentage'),
		'value', FailureHandling.OPTIONAL).replace('%', '').replaceAll('\\s', '')
		
'get provisionfee amount value'
strProvisionFeeAmount = WebUI.getAttribute(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/input_Provision Fee Amount'),
		'value', FailureHandling.OPTIONAL).replace(',', '')
		
	'convert Total Asset Price to BigDecimal'
BigDecimal BDTotalAssetPrice = new BigDecimal(TotalAssetPrice)

'convert DP Asset Accessory to BigDecimal'
BigDecimal BDDPAssetAccessory = new BigDecimal(DPAssetAccessory)

	'convert total fee capitalize to BigDecimal'
BigDecimal BDTotalfeeCapitalized = new BigDecimal(TotalfeeCapitalized)

'convert provision fee capitalized to BigDecimal'
BigDecimal BDProvisionFeeCapitalize = new BigDecimal(ProvisionFeeCapitalize)
'convert Total insurance cap to BigDecimal'
BigDecimal BDTotalInsuranceCap = new BigDecimal(TotalInsuranceCap)

'convert provision percentage to BigDecimal'
BigDecimal ProvisionPercentage = new BigDecimal(ProvisionFeePercentage)

'convert provision fee amount to BigDecimal'
BigDecimal ProvisionFeeAmount = new BigDecimal(strProvisionFeeAmount)

'calculate value for NTFprovisioncalc'
NTFforProvisionCalc = BDTotalAssetPrice.subtract(BDDPAssetAccessory)

'calculate value for total fee cap Exclude provision fee cap'
BigDecimal TotalFeeCapEXCProvCap = BDTotalfeeCapitalized.subtract(BDProvisionFeeCapitalize)

'calculate value NTFProvision add Total insurance Cap'
BigDecimal NTFValueADDInsuranceCap = NTFforProvisionCalc.add(BDTotalInsuranceCap)

'calculate NTFProvisioncalc Add Total fee Cap Exclude Provision fee cap'
BigDecimal NTFValueADDCapEXCProvCap = NTFValueADDInsuranceCap.add(TotalFeeCapEXCProvCap)

'calculate NTFValue multiply percentage'
BigDecimal NTFValueFinal = Math.round(NTFValueADDCapEXCProvCap.multiply(ProvisionPercentage / 100))

'check if calculate based is OTR-DP'
if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData').getValue(
	GlobalVariable.NumofColm, 28).equalsIgnoreCase('OTR-DP')) {
	
	'check if provision fee type Percentage'
	if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData').getValue(
		GlobalVariable.NumofColm, 27) == 'Percentage') {
	
		'calculate NTFProvisionCalc multiply percentage'
		BigDecimal NTFValuexPercentage = NTFforProvisionCalc.multiply(ProvisionPercentage / 100)

		'convert NTFValueXPercentage to string'
		String strNTFValuexPercentage = NTFValuexPercentage.toString()

		'verify match strNTFValueXPercentage and strProvisionAmount'
		WebUI.verifyMatch(strNTFValuexPercentage.replace('.000000', ''), strProvisionFeeAmount, false, FailureHandling.OPTIONAL)
	} else {
	
		'calculate Provisionfeeamount divide NTFforprovisioncalc'
		BigDecimal ProvisionFeeAmountDivideNTFValue = ProvisionFeeAmount.divide(NTFforProvisionCalc, 8, RoundingMode.HALF_EVEN)

		'verify equal provisionfeeamountdiveNTFProvision and provision percentage'
		WebUI.verifyEqual(ProvisionFeeAmountDivideNTFValue, ProvisionPercentage / 100, FailureHandling.OPTIONAL)
	}
} else {
'check if provision fee type Percentage'
	if (findTestData('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData').getValue(
		GlobalVariable.NumofColm, 27) == 'Percentage') {
	
	'convert NTFValueFinal to string'
		String strNTFValueFinal = NTFValueFinal.toString()

		'verify matvh NTFValueFinal and provision amount'
		WebUI.verifyMatch(strNTFValueFinal.replace('.000000', ''), strProvisionFeeAmount, false, FailureHandling.OPTIONAL)
	} else {
	
	'calculate provisionfeeamount divide NTFValueexcludeprovisionfeecap'
		ProvisionFeeAmountDivideNTFValueExcProv = ProvisionFeeAmount.divide(NTFValueADDCapEXCProvCap, 8, RoundingMode.HALF_EVEN)

		'verify matvh provisionfeemaountdivideNTFValueexcludeprovisionfeecap and provision percentage'
		WebUI.verifyMatch(ProvisionFeeAmountDivideNTFValueExcProv.toString(), (ProvisionPercentage / 100).toString(),
			false, FailureHandling.OPTIONAL)
	}
}

'get attribute admincapitalizevalue'
def AdminCapitalizeValue = WebUI.getAttribute(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/input_Admin Fee Capitalize_'),
	'value', FailureHandling.OPTIONAL).split(',').join()

	'get attribute AdditionalAdminFeeValue'
def AdditionalAdminFeeValue = WebUI.getAttribute(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/input_Additional Admin Capitalize'),
	'value', FailureHandling.OPTIONAL).split(',').join()

	'get attribute NotaryFeeCapitalizeValue'
def NotaryFeeCapitalizeValue = WebUI.getAttribute(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/input_Notary Fee Capitalize'),
	'value', FailureHandling.OPTIONAL).split(',').join()

	'get attribute OtherFeeCapitalize'
def OtherFeeCapitalize = WebUI.getAttribute(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/input_Other Fee Capitalize'),
	'value', FailureHandling.OPTIONAL).split(',').join()

	'get attribute FiduciaFeeCapitalize'
def FiduciaFeeCapitalize = WebUI.getAttribute(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/input_Fiducia Fee Capitalize'),
	'value', FailureHandling.OPTIONAL).split(',').join()

	'get attribute ProvisionFeeCapitalize'
def ProvisionFeeCapitalize = WebUI.getAttribute(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/input_Provision Fee Capitalize'),
	'value', FailureHandling.OPTIONAL).split(',').join()

	'get attribute NTF'
def NTF = WebUI.getText(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_NTF'),
	FailureHandling.OPTIONAL).replace(',', '').replace('.00', '')

	'get attribute DPAssetAccessoryValue'
def DPAssetAccessoryValue = (WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_DP ASSET  ACCESSORY  () DP'),
	FailureHandling.OPTIONAL).replace(',', '').replaceAll('\\s', '').split('/')[0]).replace('.00', '')

	'get attribute DPAssetAccessoryMinSubValue'
def DPAssetAccessoryMinSubValue = (WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_DP ASSET  ACCESSORY - SUBSIDY DP  () DP NETT'),
	FailureHandling.OPTIONAL).replace(',', '').replaceAll('\\s', '').split('/')[0]).replace('.00', '')

	'get attribute TotalAssetAccessoryPriceValue'
def TotalAssetAccessoryPriceValue = WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_TOTAL ASSET ACCESSORY PRICE')).replace(
	',', '').replace('.00', '')
	
	'get attribute TotalARValue'
	def TotalARValue = WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_TOTAL AR')).replace(
		',', '').replace('.00', '')
		
		'get attribute InterestAmountValue'
		def InterestAmountValue = WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_TOTAL INTEREST'), FailureHandling.OPTIONAL).replace(',','').replace('.00','')
		
		
		'convert admincapitalizedvalue to BigDecimal'
BigDecimal intAdminCapitalizeValue = Integer.parseInt(AdminCapitalizeValue)

'convert AdditionalAdminFeeValue to BigDecimal'
BigDecimal intAdditionalAdminFeeValue = Integer.parseInt(AdditionalAdminFeeValue)

'convert NotaryFeeCapitalizeValue to BigDecimal'
BigDecimal intNotaryFeeCapitalizeValue = Integer.parseInt(NotaryFeeCapitalizeValue)

'convert OtherFeeCapitalize to BigDecimal'
BigDecimal intOtherFeeCapitalize = Integer.parseInt(OtherFeeCapitalize)

'convert FiduciaFeeCapitalize to BigDecimal'
BigDecimal intFiduciaFeeCapitalize = Integer.parseInt(FiduciaFeeCapitalize)

'convert ProvisionFeeCapitalize to BigDecimal'
BigDecimal intProvisionFeeCapitalize = Integer.parseInt(ProvisionFeeCapitalize)

'convert NTF to BigDecimal'
BigDecimal intNTF = Integer.parseInt(NTF)

'convert DPAssetAccessoryValue to BigDecimal'
BigDecimal intDPAssetAccessoryValue = Integer.parseInt(DPAssetAccessoryValue)

'convert DPAssetAccessoryMinSubValue to BigDecimal'
BigDecimal intDPAssetAccessoryMinSubValue = Integer.parseInt(DPAssetAccessoryMinSubValue)

'convert TotalAssetAccessoryPriceValue to BigDecimal'
BigDecimal intTotalAssetAccessoryPriceValue = Integer.parseInt(TotalAssetAccessoryPriceValue)

'convert TotalAR to BigDecimal'
BigDecimal intTotalAR = Integer.parseInt(TotalARValue)

'convert InterestAmountValue to BigDecimal'
BigDecimal intInterestAmountValue = Integer.parseInt(InterestAmountValue)

'get value total fee capitalize'
int TotalFeeCapitalize = Integer.parseInt(WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_TOTAL FEE CAPITALIZED')).replace(
		'.00', '').split(',').join())

'calculate total fee capitalize'
int TotalCapitalizeValue = ((((intAdminCapitalizeValue + intAdditionalAdminFeeValue) + intNotaryFeeCapitalizeValue) + intOtherFeeCapitalize) +
intFiduciaFeeCapitalize) + intProvisionFeeCapitalize

'verify equal totalfeecapitalize'
WebUI.verifyEqual(TotalFeeCapitalize, TotalCapitalizeValue)

'click button calculate'
WebUI.click(findTestObject('NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/button_Calculate'))

'verify equal DPAssetAccessory minus subsidy DP = DPAssetAccessoryMinSubsidyDP'
WebUI.verifyEqual(intDPAssetAccessoryValue - Integer.parseInt(SubsidyDPValue), intDPAssetAccessoryMinSubValue, FailureHandling.CONTINUE_ON_FAILURE)

'Get value Total insurance value'
String TotalInsuranceValue = WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_TOTAL INSURANCE'))

'get value total insurance capitalized value'
def TotalInsuranceCapitalizeValue = WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_TOTAL INSURANCE CAPITALIZED')).replace(
	'.00', '')

'convert total insurance value to BigDecimal'
BigDecimal intTotalInsurancevalue = new BigDecimal(TotalInsuranceCapitalizeValue.replace(',', ''))

'verify match Total insurance(from tab insruance) and total insurance (from tab financial)'
WebUI.verifyMatch(GlobalVariable.TotalInsurance, TotalInsuranceValue, false)

'verify match Insurance capitalize amount (from tab insurance) and total insurance capitalize (from tab financial)'
WebUI.verifyMatch(GlobalVariable.InsuranceCapitalizeAmount, TotalInsuranceCapitalizeValue, false)

'get total life insurance value'
def TotalLifeInsurance = WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_TOTAL LIFE INSURANCE')).replace(
	'.00', '').split(',').join()

	'get total life insurance capitalize'
def TotalLifeInsuranceCapitalize = WebUI.getText(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/label_TOTAL LIFE INSURANCE CAPITALIZED')).replace(
	'.00', '').split(',').join()

	'convert total life insurance to BigDecimal'
BigDecimal intTotalLifeInsurance = Integer.parseInt(TotalLifeInsurance)

'convert total life insurance capitalized to BigDecimal'
BigDecimal intTotalLifeInsuranceCapitalize = Integer.parseInt(TotalLifeInsuranceCapitalize)

'convert capitalize premium percentage as Number'
Number CapitalizePremiumPercentage = GlobalVariable.CapitalizePremiumPercentage

'calculate total life insurance x capitalize premium percentage'
int multiplyTotalLifeInsurancexPercentage = intTotalLifeInsurance * CapitalizePremiumPercentage

'verify equal total life insurance cap = multiplyTotalLifeInsurancexPercentage'
WebUI.verifyEqual(intTotalLifeInsuranceCapitalize, multiplyTotalLifeInsurancexPercentage)


'verify NTF Value'
WebUI.verifyEqual(((NTFforProvisionCalc + TotalFeeCapitalize) + intTotalInsurancevalue) + intTotalLifeInsuranceCapitalize,
		intNTF, FailureHandling.OPTIONAL)


'import webdriver'
WebDriver driver = DriverFactory.getWebDriver()

'get table row'
ArrayList<WebElement> counttdInstallment = driver.findElements(By.cssSelector('#FinData_FinData > form > div.ng-star-inserted > table > tbody tr'))

'declare installmentamountvalue'
int installmentamountvalue
'declare principalamountvalue'
int principalamountvalue
'declare interestamountvalue'
int interestamountvalue

'loop for tabel amortisasi '
for(int InstallmentSchemecount = 1 ; InstallmentSchemecount <= counttdInstallment.size() ; InstallmentSchemecount++){

	
String NewInstallmentAmount = ('//*[@id="FinData_FinData"]/form/div[3]/table/tbody/tr[' + InstallmentSchemecount + ']/td[2]')

		 'modify object installmentamount'
		 modifyNewInstallmentAmount = WebUI.modifyObjectProperty(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/td_InstallmentAmount'),
			 'xpath', 'equals', NewInstallmentAmount, true)
		 
		 String strInstallmentamount = WebUI.getText(modifyNewInstallmentAmount, FailureHandling.OPTIONAL).replace(',','').replace('.00', '')
		 
		 String NewPrincipalAmount = ('//*[@id="FinData_FinData"]/form/div[3]/table/tbody/tr[' + InstallmentSchemecount + ']/td[3]')
		 
		 'modify object principal amount'
		 modifyNewPrincipalAmount = WebUI.modifyObjectProperty(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/td_PrincipalAmount'),
			 'xpath', 'equals', NewPrincipalAmount, true)
		 
		 String strPrincipalamount = WebUI.getText(modifyNewPrincipalAmount, FailureHandling.OPTIONAL).replace(',','').replace('.00', '')
		 
		 String NewInterestAmount = ('//*[@id="FinData_FinData"]/form/div[3]/table/tbody/tr[' + InstallmentSchemecount + ']/td[4]')
		 
		 'modify object interest amount'
		 modifyNewInterestAmount = WebUI.modifyObjectProperty(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/td_InterestAmount'),
			 'xpath', 'equals', NewInterestAmount, true)
		 
		 String strInterestamount = WebUI.getText(modifyNewInterestAmount, FailureHandling.OPTIONAL).replace(',','').replace('.00', '')
		 
		 String NewOSPrincipalAmount = ('//*[@id="FinData_FinData"]/form/div[3]/table/tbody/tr[' + InstallmentSchemecount + ']/td[5]')
		  
		  'modify object OS Principal amount'
		  modifyNewOSPrincipalAmount = WebUI.modifyObjectProperty(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/td_OSPrincipalAmount'),
			  'xpath', 'equals', NewOSPrincipalAmount, true)
		  
		  String strOSPrincipalAmount = WebUI.getText(modifyNewOSPrincipalAmount, FailureHandling.OPTIONAL).replace(',','').replace('.00', '')
		  
		  String NewOSInterestAmount = ('//*[@id="FinData_FinData"]/form/div[3]/table/tbody/tr[' + InstallmentSchemecount + ']/td[6]')
		   
		   'modify object OS Interest amount'
		   modifyNewOSInterestAmount = WebUI.modifyObjectProperty(findTestObject('Object Repository/NAP-FL4W-CustomerPersonal/NAP-FL4W-CustomerPersonalSingle/NAP2-ApplicationData/TabFinancialData/td_OSInterestAmount'),
			   'xpath', 'equals', NewOSInterestAmount, true)
		   
		   String strOSInterestAmount = WebUI.getText(modifyNewOSInterestAmount, FailureHandling.OPTIONAL).replace(',','').replace('.00', '')
		 
		   'count total of installment amount'
		 if(installmentamountvalue == null){
		 installmentamountvalue = Integer.parseInt(strInstallmentamount)
		 }else{
		 installmentamountvalue = installmentamountvalue + Integer.parseInt(strInstallmentamount)
		 }
		 
		 'count total of principal amount'
		 if(principalamountvalue == null){
			 principalamountvalue = Integer.parseInt(strPrincipalamount)
			 }else{
			 principalamountvalue = principalamountvalue + Integer.parseInt(strPrincipalamount)
			 }
			 
			 'count total of interest amount'
			 if(interestamountvalue == null){
				 interestamountvalue = Integer.parseInt(strInterestamount)
				 }else{
				 interestamountvalue = interestamountvalue + Integer.parseInt(strInterestamount)
				 }
				 
				 'verify value not minus(-)'
		WebUI.verifyGreaterThanOrEqual(Integer.parseInt(strInstallmentamount), 0)
		'verify value not minus(-)'
		WebUI.verifyGreaterThanOrEqual(Integer.parseInt(strPrincipalamount), 0)
		'verify value not minus(-)'
		WebUI.verifyGreaterThanOrEqual(Integer.parseInt(strInterestamount), 0)
		'verify value not minus(-)'
		WebUI.verifyGreaterThanOrEqual(Integer.parseInt(strOSPrincipalAmount), 0)
		'verify value not minus(-)'
		WebUI.verifyGreaterThanOrEqual(Integer.parseInt(strOSInterestAmount), 0)
		
		'get last row value'
		if(InstallmentSchemecount - counttdInstallment.size() == 0){
			'verify value is 0'
			WebUI.verifyEqual(Integer.parseInt(strOSPrincipalAmount), 0)
			'verify value is 0'
			WebUI.verifyEqual(Integer.parseInt(strOSInterestAmount), 0)
		}

}

'verify equal installmentamount value = total AR'
WebUI.verifyEqual(installmentamountvalue, intTotalAR, FailureHandling.OPTIONAL)
'verify pincipalamount value = NTF'
WebUI.verifyEqual(principalamountvalue, intNTF, FailureHandling.OPTIONAL)
'verity interest amount value - total interest'
WebUI.verifyEqual(interestamountvalue, intInterestAmountValue, FailureHandling.OPTIONAL)

'verify PrincipalAmount+InteresetAmount == AR Value'
WebUI.verifyEqual(principalamountvalue + interestamountvalue, intTotalAR , FailureHandling.OPTIONAL)
'verify installmentamountvalue == AR Value'
WebUI.verifyEqual(installmentamountvalue, intTotalAR, FailureHandling.OPTIONAL)
'verify intNTF + intInterestAmountValue == AR Value'
WebUI.verifyEqual(intNTF + intInterestAmountValue, intTotalAR, FailureHandling.OPTIONAL)

'verify rumus 1 == rumus 2'
WebUI.verifyEqual(principalamountvalue + interestamountvalue, installmentamountvalue, FailureHandling.OPTIONAL)
'verify rumus 1 == rumus 3'
WebUI.verifyEqual(principalamountvalue + interestamountvalue, intNTF + intInterestAmountValue, FailureHandling.OPTIONAL)
'verify rumus 2 == rumus 3'
WebUI.verifyEqual(installmentamountvalue, intNTF + intInterestAmountValue, FailureHandling.OPTIONAL)

