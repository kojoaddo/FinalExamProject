package pkgApp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {

		
	private RetirementApp mainApp = null;
	
	@FXML
	private TextField txtSaveEachMonth;
	private TextField txtYearsToWork;
	private TextField txtAnnualReturn;
	private TextField txtWhatYouNeedSaved;
	private TextField txtYearsRetired;
	private TextField txtAnnualReturnRetired;
	private TextField txtRequiredIncome;
	private TextField txtMonthlySSI;
	
	
	

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	}
	
	@FXML
	public void btnClear(ActionEvent event) {
//		TODO: Clear all the text inputs
		System.out.println("Clear pressed");
		txtSaveEachMonth.clear();
		txtYearsToWork.clear();
		txtAnnualReturn.clear();
		txtWhatYouNeedSaved.clear();
		txtYearsRetired.clear();
		txtAnnualReturnRetired.clear();
		txtRequiredIncome.clear();
		txtMonthlySSI.clear();
		}
	@FXML
	public void btnCalculate(ActionEvent event) {
		//	TODO: Call AmountToSave and TotalAmountSaved and populate 
		if (ValidInputs()) {
			txtSaveEachMonth.setDisable(false);
			txtWhatYouNeedSaved.setDisable(false);
			double dAnnualReturnRetired = Double.parseDouble(txtAnnualReturnRetired.getText());
			int iYearsRetired = Integer.parseInt(txtYearsRetired.getText());
			double dRequiredIncome = Double.parseDouble(txtRequiredIncome.getText());
			double dMonthlySSI = Double.parseDouble(txtMonthlySSI.getText());
			double dAnnualReturn = Double.parseDouble(txtAnnualReturn.getText());
			int iYearsToWork = Integer.parseInt(txtYearsToWork.getText());
			Retirement retirement = new Retirement(iYearsToWork, dAnnualReturn, iYearsRetired,
					dAnnualReturnRetired, dRequiredIncome, dMonthlySSI);
			double pv = retirement.TotalAmountSaved();
			double pmt = retirement.AmountToSave();
			txtWhatYouNeedSaved.setText(Double.toString(pv));
			txtSaveEachMonth.setText(Double.toString(pmt));
			txtYearsToWork.setDisable(true);
			txtAnnualReturn.setDisable(true);
			txtYearsRetired.setDisable(true);
			txtAnnualReturnRetired.setDisable(true);
			txtRequiredIncome.setDisable(true);
			txtMonthlySSI.setDisable(true);
			}
		}

	private boolean ValidInputs() {
		String errorMessage = "";
		try {
			if (txtYearsToWork.getText() == null || txtYearsToWork.getText().length() == 0
					|| Integer.parseInt(txtYearsToWork.getText()) < 0) {
				errorMessage += "Number of years worked must be an integer and must be numbers. ";
						
			}
		} catch (NumberFormatException e) {
			errorMessage += "Number of years to work must be a whole number and integer greater than 0. ";
		}

		try {
			if (txtAnnualReturn.getText() == null || txtAnnualReturn.getText().length() == 0
					|| Double.parseDouble(txtAnnualReturn.getText()) < 0
					|| Double.parseDouble(txtAnnualReturn.getText()) > 20) {
				errorMessage += "The working annual return must be a number greater than or equal to 0 and less than or equal to 20.  ";
			}
		} catch (NumberFormatException e) {
			errorMessage += "The annual return while working must be a number greater than or equal to 0 and less than or equal to 20. Only numbers and one decimal point are accepted as input. \n\n";
		}

		try {
			if (txtYearsRetired.getText() == null || txtYearsRetired.getText().length() == 0
					|| Integer.parseInt(txtYearsRetired.getText()) < 0) {
				errorMessage += "The number of years retired must be a whole number (an integer greater than or equal to 0).  ";
			}
		} catch (NumberFormatException e) {
			errorMessage += "The number of years retired must be a whole number (an integer greater than or equal to 0).  ";
		}

		try {
			if (txtAnnualReturnRetired.getText() == null || txtAnnualReturnRetired.getText().length() == 0
					|| Double.parseDouble(txtAnnualReturnRetired.getText()) < 0
					|| Double.parseDouble(txtAnnualReturnRetired.getText()) > 3)

			{
				errorMessage += "The retired annual return must be a number greater than or equal to 0 and less than or equal to 3.  ";
			}
		} catch (NumberFormatException e) {
			errorMessage += "The retired annual return must be a number greater than or equal to 0 and less than or equal to 3.  ";
		}

		try {
			if (txtRequiredIncome.getText() == null || txtRequiredIncome.getText().length() == 0
					|| Double.parseDouble(txtRequiredIncome.getText()) < 0) {
				errorMessage += "The required income must be a number greater than or equal to 0.  ";
			}
		} catch (NumberFormatException e) {
			errorMessage += "The required income must be a number greater than or equal to 0.  ";
		}

		try {
			if (txtMonthlySSI.getText() == null || txtMonthlySSI.getText().length() == 0
					|| Double.parseDouble(txtMonthlySSI.getText()) < 0) {
				errorMessage += "The monthly SSI must be a number greater than or equal to 0.  ";
			}
		} catch (NumberFormatException e) {
			errorMessage += "The monthly SSI must be a number greater than or equal to 0.  ";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct the invalid field(s).");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
	
			
	}
	
