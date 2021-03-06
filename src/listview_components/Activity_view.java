package listview_components;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import resources.Activities;
import resources.Users;
import saver_loader.DataResource;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Activity_view extends JFrame {

	private JPanel contentPane;
	private JLabel descriptionField;
	private JLabel startField;
	private JLabel endField;
	private JLabel activityLabelField;
	private JLabel progressField;
	private JLabel budgetField;
	private JLabel mostLikelyTimeField;
	private JLabel optimisticTimeField;
	private JLabel pessimisticTimeField;
	private JLabel targetDateField;
	
	public Activity_view() {
		
		//Initialize JFrame Settings
		setTitle("VIEW");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 426, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		activityLabelField = new JLabel(DataResource.selectedActivity.getLabel());
		activityLabelField.setBounds(226, 23, 124, 20);
		contentPane.add(activityLabelField);
		
		progressField = new JLabel(DataResource.selectedActivity.getProgress().name());
		progressField.setBounds(226, 40, 124, 20);
		contentPane.add(progressField);
		
		budgetField = new JLabel("" + DataResource.selectedActivity.getBudget());
		budgetField.setBounds(226, 140, 124, 20);
		contentPane.add(budgetField);
		  
		mostLikelyTimeField = new JLabel("" + DataResource.selectedActivity.getMostLikelyTime());
		mostLikelyTimeField.setBounds(216, 400, 124, 20);
		contentPane.add(mostLikelyTimeField);
		
		optimisticTimeField = new JLabel("" + DataResource.selectedActivity.getOptimisticTime());
		optimisticTimeField.setBounds(216, 420, 124, 20);
		contentPane.add(optimisticTimeField);
		
		pessimisticTimeField = new JLabel("" + DataResource.selectedActivity.getPessimisticTime());
		pessimisticTimeField.setBounds(216, 440, 124, 20);
		contentPane.add(pessimisticTimeField);
		
		targetDateField = new JLabel("" + DataResource.selectedActivity.getTargetDate());
		targetDateField.setBounds(216, 460, 124, 20);
		contentPane.add(targetDateField);
		
		//Create and add Description Field
		descriptionField = new JLabel(DataResource.selectedActivity.getDescription());
		descriptionField.setBounds(226, 120, 124, 20);
		contentPane.add(descriptionField);

		//Create and add all Labels
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(21, 123, 160, 14);
		contentPane.add(lblDescription);
		
		JLabel lblProgress = new JLabel("Progress");
		lblProgress.setBounds(21, 40, 160, 14);
		contentPane.add(lblProgress);
		
		JLabel lblBudget = new JLabel("Budget");
		lblBudget.setBounds(21, 140, 160, 14);
		contentPane.add(lblBudget);
		
		JLabel lblMostLikelyTime = new JLabel("Most Likely Time");
		lblMostLikelyTime.setBounds(21, 400, 160, 14);
		contentPane.add(lblMostLikelyTime);
		
		JLabel lblOptimisticTime = new JLabel("Optimistic Time");
		lblOptimisticTime.setBounds(21, 420, 160, 14);
		contentPane.add(lblOptimisticTime);
		
		JLabel lblPessimisticTime = new JLabel("Pessimistic Time");
		lblPessimisticTime.setBounds(21, 440, 160, 14);
		contentPane.add(lblPessimisticTime);
		
		JLabel lblTargetDate = new JLabel("Target");
		lblTargetDate.setBounds(21, 460, 160, 14);
		contentPane.add(lblTargetDate);
		
		JLabel lblDuration = new JLabel("Start Date (DD-MM-YYYY)");
		lblDuration.setBounds(21, 64, 170, 14);
		contentPane.add(lblDuration);
		
		JLabel lblEnd = new JLabel("End Date (DD-MM-YYYY)");
		lblEnd.setBounds(21, 94, 170, 14);
		contentPane.add(lblEnd);
		
		JLabel lblLabel = new JLabel("Name");
		lblLabel.setBounds(21, 26, 160, 14);
		contentPane.add(lblLabel);
		
		JLabel lblDependencies = new JLabel("Dependencies");
		lblDependencies.setBounds(21, 157, 160, 14);
		contentPane.add(lblDependencies);
		
		JLabel lblResources = new JLabel("Resources");
		lblResources.setBounds(21, 267, 160, 14);
		contentPane.add(lblResources);
		
		//Create and add all text Fields
		String initialStart = DataResource.dateFormatter.format(DataResource.selectedActivity.getStartDate());
		startField = new JLabel(initialStart.toString());
		startField.setBounds(226, 61, 124, 20);
		contentPane.add(startField);

		String initialEnd = DataResource.dateFormatter.format(DataResource.selectedActivity.getEndDate());
		endField = new JLabel(initialEnd.toString());
		endField.setBounds(226, 91, 124, 20);
		contentPane.add(endField);
		
		//Create an array of the current Activities
		int activityCount = DataResource.selectedProject.getActivityList().size();
		Activities[] activityList = new Activities[activityCount];
		DataResource.selectedProject.getActivityList().toArray(activityList);
		
		//Create Selections from the list of Activities and their labels
		String[] selections = new String[activityCount];
		
		//Get the currently selected dependencies for this activity
		ArrayList<Activities> currentSelections = DataResource.selectedProject.getSetofDependencyActivities(DataResource.selectedActivity);
		
		
		for(int i = 0; i < activityCount; i++){			
			if(!DataResource.selectedActivity.getLabel().equals(activityList[i].getLabel()))
				selections[i] = activityList[i].getLabel();
			}
		
		
		//Create ScrollPane with list inside and add to Frame
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setBounds(226, 175, 101, 88);
		
		contentPane.add(scrollPane_1);
		
		//Create list with selections
		JList<String> selectionList = new JList<>(selections);
		selectionList.setBounds(232, 192, 95, 82);
		
		//Set the default selections to current dependent activities
		int[] selectedIndices = new int[currentSelections.size()];
		for(int i = 0; i < currentSelections.size(); i++)
		{
			for(int j = 0; j < activityList.length; j++)
			{
				if(currentSelections.get(i).getId() == activityList[j].getId())
					selectedIndices[i] = j;
			}
		}
		selectionList.setSelectedIndices(selectedIndices); 
		contentPane.add(selectionList);
		
		//Add to viewport
		scrollPane_1.setViewportView(selectionList);
		
		ArrayList<Users> currentMembers = DataResource.selectedActivity.getMemberList();
		String[] memberNames = new String[DataResource.projectMembers.size()];
		
		for(int i = 0; i < DataResource.projectMembers.size(); i++) {
			memberNames[i] = DataResource.projectMembers.get(i).getName();
		}
		
		//Create ScrollPane with list inside and add to Frame
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_2.setBounds(226, 285, 101, 88);
				
		contentPane.add(scrollPane_2);
		
		JList<String> memberList = new JList<String>(memberNames);
		memberList.setBounds(232, 192, 95, 82);
		
		int[] memberIndices = new int[currentMembers.size()];
		for(int i = 0; i < currentMembers.size(); i++)
		{
			for(int j = 0; j < DataResource.projectMembers.size(); j++)
			{
				if(currentMembers.get(i).getID() == DataResource.projectMembers.get(j).getID())
					memberIndices[i] = j;
			}
		}
		
		memberList.setSelectedIndices(memberIndices);
		contentPane.add(memberList);
		
		scrollPane_2.setViewportView(memberList);
		
		//Initialize and set Buttons
		JButton btnCancel = new JButton("Close");
		btnCancel.setBounds(250, 460, 89, 23);
		contentPane.add(btnCancel);
		
		//Add and define ActionListeners to the buttons
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				disposeWindow();
			}
		});
	}
	
	private void disposeWindow(){
		this.dispose();
	}
	
}