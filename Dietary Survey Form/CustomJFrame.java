import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;

/**
 * -Creates and implements the JFrame being presented by the GUI.
 * It adds and formats all components in the GUI for user use.
 * @author Andrew Simon
 *
 */
public class CustomJFrame extends JFrame{

	private JLabel headingLabel = new JLabel("Personal Information");
	private JLabel firstNameLabel = new JLabel("First Name:");
	private JLabel lastNameLabel = new JLabel("Last Name:");
	private JLabel phoneNumberLabel = new JLabel("Phone Number:");
	private JLabel emailLabel = new JLabel("Email:");
	private JLabel dietaryLabel = new JLabel("Dietary Questions");
	private JLabel genderLabel = new JLabel("Sex");
	private JLabel waterLabel = new JLabel("How many cups of water on average do you drink a day?");
	private JLabel mealsLabel = new JLabel("How many meals on average do you eat a day?");
	private JLabel checkBoxLabel = new JLabel("Do any of these meals regularly contain:");
	private JLabel walkLabel = new JLabel("On average how many miles do you walk in a day?");
	private JLabel weightLabel = new JLabel("How much do you weigh?");
	
	private JTextField firstNameTextField = new JTextField();
	private JTextField lastNameTextField = new JTextField();
	private JTextField phoneNumberTextField = new JTextField();
	private JTextField emailTextField = new JTextField();
	
	private JRadioButton maleRadioButton = new JRadioButton();
	private JRadioButton femaleRadioButton = new JRadioButton();
	private JRadioButton preferRadioButton = new JRadioButton();
	private ButtonGroup radioButtonGroup = new ButtonGroup();
	
	private JSpinner waterIntakeSpinner = new JSpinner(new SpinnerNumberModel(15, 0, 50, 1));
	
	private JSlider mealSlider = new JSlider(0, 10, 3);
	
	private JCheckBox wheatCheckBox = new JCheckBox();
	private JCheckBox sugarCheckBox = new JCheckBox();
	private JCheckBox dairyCheckBox = new JCheckBox();
	
	private String[] walkOptions = {"Less than 1 Mile", " More than 1 mile but less than 2 miles", "More than 2 miles but less than 3 miles", "More than 3 miles "};
	private JComboBox<String> walkComboBox = new JComboBox<String>(walkOptions);
	
	private JFormattedTextField weightFormattedTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
	
	private JButton clearButton = new JButton("Clear");
	private JButton submitButton = new JButton("Submit");
	
	private FileHandler fileHandler = new FileHandler();
	
	
	/**
	 * -Creates and implements the JFrame being presented by the GUI.
	 * It adds and formats all components in the GUI for user use.
	 *
	 */
	public CustomJFrame() {
		this.setTitle("Dietary Survey");
		
		this.firstNameTextField.setColumns(15);
		this.lastNameTextField.setColumns(15);
		this.phoneNumberTextField.setColumns(15);
		this.emailTextField.setColumns(15);
		
		
		maleRadioButton.setText("Male");
		maleRadioButton.setSelected(false);
		maleRadioButton.setActionCommand("Male");
		
		femaleRadioButton.setText("Female");
		femaleRadioButton.setSelected(false);
		femaleRadioButton.setActionCommand("Female");
		
		preferRadioButton.setText("Prefer ot to say");
		preferRadioButton.setSelected(false);
		preferRadioButton.setActionCommand("Prefer not to say");
		
		radioButtonGroup.add(maleRadioButton);
		radioButtonGroup.add(femaleRadioButton);
		radioButtonGroup.add(preferRadioButton);
		
		mealSlider.setMajorTickSpacing(1);
		mealSlider.setPaintTicks(true);
		mealSlider.setPaintLabels(true);
		
		wheatCheckBox.setText("Wheat");
		wheatCheckBox.setSelected(false);
		
		dairyCheckBox.setText("Dairy");
		dairyCheckBox.setSelected(false);
		
		sugarCheckBox.setText("Sugar");
		sugarCheckBox.setSelected(false);
		
		weightFormattedTextField.setColumns(15);
		weightFormattedTextField.setValue(null);
		
		clearButton.setBackground(Color.YELLOW);
		submitButton.setBackground(Color.GREEN);
				
		this.setLayout(new GridBagLayout());
		GridBagConstraints layoutManager = new GridBagConstraints();
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 0;
		layoutManager.insets = new Insets(10, 10, 10, 10);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 0;
		this.add(headingLabel, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 1;
		this.add(firstNameLabel, layoutManager);
		
		layoutManager.gridx = 1;
		layoutManager.gridy = 1;
		this.add(firstNameTextField, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 2;
		this.add(lastNameLabel, layoutManager);
		
		layoutManager.gridx = 1;
		layoutManager.gridy = 2;
		this.add(lastNameTextField, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 3;
		this.add(phoneNumberLabel, layoutManager);
		
		layoutManager.gridx = 1;
		layoutManager.gridy = 3;
		this.add(phoneNumberTextField, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 4;
		this.add(emailLabel, layoutManager);
		
		layoutManager.gridx = 1;
		layoutManager.gridy = 4;
		this.add(emailTextField, layoutManager);
		
		layoutManager.insets = new Insets(1,10,1,1);
		layoutManager.gridx = 0;
		layoutManager.gridy = 5;
		this.add(genderLabel, layoutManager);
		
		layoutManager.anchor = GridBagConstraints.WEST;
		layoutManager.gridx = 1;
		layoutManager.gridy = 5;
		this.add(maleRadioButton, layoutManager);
		
		layoutManager.gridx = 1;
		layoutManager.gridy = 6;
		this.add(femaleRadioButton, layoutManager);
		
		layoutManager.gridx = 1;
		layoutManager.gridy = 7;
		this.add(preferRadioButton, layoutManager);
		
		layoutManager.insets = new Insets(10,10,10,10);
		layoutManager.gridx = 0;
		layoutManager.gridy = 8;
		this.add(dietaryLabel, layoutManager);
		
		layoutManager.gridwidth = 2;
		layoutManager.gridx = 0;
		layoutManager.gridy = 9;
		this.add(waterLabel, layoutManager);
		
		layoutManager.anchor = GridBagConstraints.CENTER;
		layoutManager.gridx = 0;
		layoutManager.gridy = 10;
		this.add(waterIntakeSpinner, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 11;
		this.add(mealsLabel, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 12;
		this.add(mealSlider, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 13;
		this.add(checkBoxLabel, layoutManager);
		
		
		layoutManager.anchor = GridBagConstraints.WEST;
		layoutManager.gridx = 0;
		layoutManager.gridy = 14;
		this.add(dairyCheckBox, layoutManager);
		
		layoutManager.anchor = GridBagConstraints.CENTER;
		layoutManager.gridx = 0;
		layoutManager.gridy = 14;
		this.add(wheatCheckBox, layoutManager);
		
		layoutManager.anchor = GridBagConstraints.EAST;
		layoutManager.gridx = 0;
		layoutManager.gridy = 14;
		this.add(sugarCheckBox, layoutManager);
		
		layoutManager.anchor = GridBagConstraints.CENTER;
		layoutManager.gridwidth = 2;
		layoutManager.gridx = 0;
		layoutManager.gridy = 15;
		this.add(walkLabel, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 16;
		this.add(walkComboBox, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 15;
		this.add(walkLabel, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 16;
		this.add(walkComboBox, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 17;
		this.add(weightLabel, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 18;
		this.add(weightFormattedTextField, layoutManager);
		
		class InnerActionListener implements ActionListener {

			/**
			 * Allows for actions to be triggered when the "Clear" and "Submit" buttons are pressed.
			 * "Submit" will write the user data to survey_results.csv and clear the data in the GUI
			 *  to the defaults and clear the components to their default values.
			 * "Clear" will only clear the components of their default values.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == submitButton) {
				String firstName = firstNameTextField.getText();
				String lastName = lastNameTextField.getText();
				String phoneNumber = phoneNumberTextField.getText();
				String email = emailTextField.getText();
				String buttonSel = "";
				
				if(!radioButtonGroup.isSelected(null)) {
					buttonSel = radioButtonGroup.getSelection().getActionCommand();
				}
				else {
					buttonSel = "null";
				}
				
				String spinnerVal = String.valueOf(waterIntakeSpinner.getValue());
				String sliderVal = String.valueOf(mealSlider.getValue());
				boolean dairySel = dairyCheckBox.isSelected();
				boolean wheatSel = wheatCheckBox.isSelected();
				boolean sugarSel = sugarCheckBox.isSelected();
				String comboSel = (String) walkComboBox.getSelectedItem();
				String weight = "";
				
				if(!weightFormattedTextField.getText().equals("")) {
					weight = weightFormattedTextField.getText();
				}
				
				else {
					weight = "null";
				}
		
				String surveyData = (firstName + "," + lastName + "," + phoneNumber + "," + email + "," + buttonSel + "," + spinnerVal + "," + sliderVal + "," + wheatSel + "," + sugarSel + "," + dairySel + "," + comboSel + "," + weight);
				fileHandler.writeResults(surveyData);
				
				clearForm();
				}
				
				else if(e.getSource() == clearButton) {
					clearForm();
				}
			}
			/**
			 * -Sets all components in the GUI to their default values.
			 */
			public void clearForm() {
				firstNameTextField.setText("");
				lastNameTextField.setText("");
				phoneNumberTextField.setText("");
				emailTextField.setText("");
				maleRadioButton.setSelected(false);
				femaleRadioButton.setSelected(false);
				preferRadioButton.setSelected(false);
				radioButtonGroup.clearSelection();
				waterIntakeSpinner.setValue(15);
				mealSlider.setValue(3);
				dairyCheckBox.setSelected(false);
				wheatCheckBox.setSelected(false);
				sugarCheckBox.setSelected(false);
				walkComboBox.setSelectedIndex(0);
				weightFormattedTextField.setText(null);
			}
		}
		
		InnerActionListener listener = new InnerActionListener();
		clearButton.addActionListener(listener);
		submitButton.addActionListener(listener);
		
		layoutManager.anchor = GridBagConstraints.WEST;
		layoutManager.gridwidth = 1;
		layoutManager.gridx = 0;
		layoutManager.gridy = 19;
		this.add(clearButton, layoutManager);
		
		layoutManager.anchor = GridBagConstraints.EAST;
		layoutManager.gridx = 1;
		layoutManager.gridy = 19;
		this.add(submitButton, layoutManager);
		
		
	}

}
