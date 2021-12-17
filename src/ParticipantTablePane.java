import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.IteratorUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class ParticipantTablePane extends VBox {
	public TableView participantTableView = new TableView(); // temporary public 
	private TableColumn<Map, String> studNumCol = new TableColumn<>("Num");
	private TableColumn<Map, String> ids = new TableColumn<>("Student ID");
	private TableColumn<Map, String> names = new TableColumn<>("Student Name");
	private TableColumn<Map, String> majors = new TableColumn<>("Major");
	private TableColumn<Map, String> teams = new TableColumn<>("Team");
	private TableColumn<Map, String> teamsNames = new TableColumn<>("Team Name");
	private TableColumn<Map, String> ranks = new TableColumn<>("Rank");
	private TableColumn<Map, Void> emailColumn = new TableColumn<>("Email");
	int cellWidth = 80;
	private Button addBtn = new Button("+");
	private Button removeBtn = new Button("-");
	private HBox addnRemovePane = new HBox(Globals.SPACING,addBtn,removeBtn);
	private RadioButton TeamRb = new RadioButton();
	private ComboBox<Team> teamsCbo = new ComboBox<>();

	public ParticipantTablePane() {
		setMinWidth(860);
		setMinHeight(490);
		participantTableView.setMinHeight(400);
		VBox.setMargin(addnRemovePane, new Insets(10, 50, 0, 50));
		addBtn.setOnAction((e) -> addBtnClicked());
		removeBtn.setOnAction((e) -> removeBtnClicked());
		
		VBox.setMargin(participantTableView, new Insets(10, 50, 40, 50));
		studNumCol.setPrefWidth(cellWidth*0.5);
		ids.setMinWidth(cellWidth);
		names.setMinWidth(cellWidth * 2);
		majors.setMinWidth(cellWidth);
		teams.setMinWidth(cellWidth);
		teamsNames.setMinWidth(cellWidth * 2);
		ranks.setMinWidth(cellWidth);
		emailColumn.setMinWidth(cellWidth);
		
		studNumCol.setCellValueFactory(new MapValueFactory<>(studNumCol.getText()));
		ids.setCellValueFactory(new MapValueFactory<>(ids.getText()));
		names.setCellValueFactory(new MapValueFactory<>(names.getText()));
		majors.setCellValueFactory(new MapValueFactory<>(majors.getText()));
		teams.setCellValueFactory(new MapValueFactory<>(teams.getText()));
		teamsNames.setCellValueFactory(new MapValueFactory<>(teamsNames.getText()));
		ranks.setCellValueFactory(new MapValueFactory<>(ranks.getText()));
		emailColumn();
		
		studNumCol.setEditable(false);
		teams.setEditable(false);
		saveCellsEdited();
		participantTableView.getColumns().addAll(studNumCol, ids, names, majors, teams, teamsNames, ranks, emailColumn);

		getChildren().add(participantTableView);
		setAlignment(Pos.CENTER);
	}

	public void fill() {
		if (Globals.currentCompetition == null) {
			return;
			
		}
		participantTableView.setItems(generateDataInMap());
		Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap = (
				TableColumn<Map, String> p) -> new TextFieldTableCell<>(new StringConverter() {
					@Override
					public String toString(Object t) {
						if (t == null) { return ""; }
						return t.toString();
					}

					@Override
					public Object fromString(String string) {
						return string;
					}
				});

		studNumCol.setCellFactory(cellFactoryForMap);
		ids.setCellFactory(cellFactoryForMap);
		names.setCellFactory(cellFactoryForMap);
		majors.setCellFactory(cellFactoryForMap);
		teams.setCellFactory(cellFactoryForMap);
		teamsNames.setCellFactory(cellFactoryForMap);
		ranks.setCellFactory(cellFactoryForMap);

		if (Globals.currentCompetition != null) {
			if (Globals.currentCompetition instanceof TeamCompetition) {
				addnRemovePane.setMaxWidth(cellWidth * 9.5);
				if (!addnRemovePane.getChildren().contains(teamsCbo))
					addnRemovePane.getChildren().add(0, teamsCbo);
				teamCboUpdate((TeamCompetition)Globals.currentCompetition);
				teams.setVisible(true);
				teamsNames.setVisible(true);
				participantTableView.setMaxWidth(cellWidth * 9.5);
			} else {
				addnRemovePane.setMaxWidth(cellWidth * 6.5);
				addnRemovePane.getChildren().remove(teamsCbo);
				teams.setVisible(false);
				teamsNames.setVisible(false);
				participantTableView.setMaxWidth(cellWidth * 6.5);
			}
		}
		teamsCbo.setPromptText("Select team");
	}

	private ObservableList<Map> generateDataInMap() {
		ObservableList<Map> alldata = FXCollections.observableArrayList();
		// TODO Solve the null value Exception
		Iterator<?> particpants = Globals.currentCompetition.getParticipants().iterator();
		int teamNum = 1;
		int studentNum = 1;
		while (particpants.hasNext()) {
			Map<String, String> dataRow = new HashMap<>();
			Student currentStudent;
			if (Globals.currentCompetition instanceof TeamCompetition) {
				Team currentTeam = (Team) particpants.next();
				Iterator<Student> students = currentTeam.getStudents().iterator();
				currentStudent = new Student();
				while (students.hasNext()) {
					dataRow = new HashMap<>();
					currentStudent = students.next();
					dataRow.put(studNumCol.getText(), Integer.toString(studentNum));
					dataRow.put(ids.getText(), currentStudent.getId());
					dataRow.put(names.getText(), currentStudent.getName());
					dataRow.put(majors.getText(), currentStudent.getMajor());
					dataRow.put(teams.getText(), Integer.toString(teamNum));
					dataRow.put(teamsNames.getText(), currentTeam.getName());
					dataRow.put(ranks.getText(),
							((Competition<Participant>) Globals.currentCompetition).getResult(currentTeam));
					alldata.add(dataRow);
					studentNum++;
				}
			} else {
				currentStudent = ((Student) particpants.next());
				dataRow.put(studNumCol.getText(), Integer.toString(studentNum));
				dataRow.put(ids.getText(), currentStudent.getId());
				dataRow.put(names.getText(), currentStudent.getName());
				dataRow.put(majors.getText(), currentStudent.getMajor());
				dataRow.put(teams.getText(), "-");
				dataRow.put(teamsNames.getText(), "-");
				dataRow.put(ranks.getText(),
						((Competition<Participant>) Globals.currentCompetition).getResult(currentStudent));
				alldata.add(dataRow);
				studentNum++;
			}
			teamNum++;
		}

		return alldata;
	}

	private void emailColumn() {

		Callback<TableColumn<Map, Void>, TableCell<Map, Void>> cellFactory = new Callback<TableColumn<Map, Void>, TableCell<Map, Void>>() {
			@Override
			public TableCell<Map, Void> call(final TableColumn<Map, Void> param) {
				final TableCell<Map, Void> cell = new TableCell<Map, Void>() {
					byte[] emojiByteCode = new byte[] { (byte) 0xF0, (byte) 0x9F, (byte) 0x93, (byte) 0xA7 };
					private final Button emailBtn = new Button(new String(emojiByteCode, Charset.forName("UTF-8")));

					{
						setAlignment(Pos.CENTER);
						// sendEmail() trasnfered to here
						emailBtn.setOnAction(a -> {
							new Thread(() -> {
								try {

									HashMap row = (HashMap) participantTableView.getItems().get(getIndex());

									// example 1
									if (Globals.currentCompetition instanceof TeamCompetition) {
										int teamNumber = Integer.parseInt((String) row.get(teams.getText())) - 1;
										TeamCompetition competition = (TeamCompetition) Globals.currentCompetition;
										Team t = (Team) competition.getTeams().toArray()[teamNumber];
										Emailer.email((TeamCompetition) Globals.currentCompetition, t);
									}
									// example 2
									else {
										String studentID = (String) row.get(ids.getText());
										System.out.println(studentID);
										Student s = new Student();
										Iterator<Student> students = Globals.currentCompetition.getStudents()
												.iterator();
										while (students.hasNext()) {
											Student sTmp = students.next();
											if (sTmp.getId().equals(studentID))
												s = sTmp;
										}
										Emailer.email((StudentCompetition) Globals.currentCompetition, s);
									}
								} catch (Exception e) {
									System.out.println(e);
								}
							}).start();
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(emailBtn);
						}
					}
				};
				return cell;
			}
		};
		emailColumn.setCellFactory(cellFactory);
	}
	
	private void saveCellsEdited( ) {
		ids.setOnEditCommit((CellEditEvent<Map, String> t) -> {
			if (!t.getNewValue().equals("")) {
				List<Student> students = IteratorUtils.toList(Globals.currentCompetition.getStudents().iterator());
				int stdNum = Integer.parseInt(studNumCol.getCellData(t.getTablePosition().getRow())) - 1;
				students.get(stdNum).setId(t.getNewValue());
			}
			fill();
		});
		
		names.setOnEditCommit((CellEditEvent<Map, String> t) -> {
			if (!t.getNewValue().equals("")) {
				List<Student> students = IteratorUtils.toList(Globals.currentCompetition.getStudents().iterator());
				int stdNum = Integer.parseInt(studNumCol.getCellData(t.getTablePosition().getRow())) - 1;
				students.get(stdNum).setName(t.getNewValue());
			}
			fill();
		});
		
		majors.setOnEditCommit((CellEditEvent<Map, String> t) -> {
			if (!t.getNewValue().equals("")) {
				List<Student> students = IteratorUtils.toList(Globals.currentCompetition.getStudents().iterator());
				int stdNum = Integer.parseInt(studNumCol.getCellData(t.getTablePosition().getRow())) - 1;
				students.get(stdNum).setMajor(t.getNewValue());
			}
			fill();
		});
		
		teamsNames.setOnEditCommit((CellEditEvent<Map, String> t) -> {
			if (!t.getNewValue().equals("")) { 
				List<Team> teams = IteratorUtils.toList(((TeamCompetition)Globals.currentCompetition).getParticipants().iterator());
				int teamNum = Integer.parseInt(this.teams.getCellData(t.getTablePosition().getRow())) - 1;
				teams.get(teamNum).setName(t.getNewValue());
			}
			fill();
		});
		
		ranks.setOnEditCommit((CellEditEvent<Map, String> t) -> {
			if (!t.getNewValue().equals(""))  {
				if (Globals.currentCompetition instanceof TeamCompetition) {
					List<Team> teams = IteratorUtils.toList(((TeamCompetition)Globals.currentCompetition).getParticipants().iterator());
					int teamNum = Integer.parseInt(this.teams.getCellData(t.getTablePosition().getRow())) - 1;
					((TeamCompetition)Globals.currentCompetition).results.replace(teams.get(teamNum), t.getNewValue());
				} else {
					List<Student> students = IteratorUtils.toList(Globals.currentCompetition.getStudents().iterator());
					int stdNum = Integer.parseInt(studNumCol.getCellData(t.getTablePosition().getRow())) - 1;
					((StudentCompetition)Globals.currentCompetition).results.replace(students.get(stdNum), t.getNewValue());
				}
			}
			fill();
		});

	}

	public boolean btnsIsDisabled() {
		return !this.getChildren().contains(addnRemovePane);
	}
	
	public void btnsSetDisabled(boolean value) {
		if (value == true)
			getChildren().remove(addnRemovePane);
		else
			getChildren().add(0, addnRemovePane);
	}
	
	private void addBtnClicked() {
		if (Globals.currentCompetition instanceof TeamCompetition) {
			if (teamsCbo.getSelectionModel().isEmpty()) return;
			Student teamMember = new Student("ID","Name","Major");
			Team selectedTeam = teamsCbo.getSelectionModel().getSelectedItem();
			selectedTeam.add(teamMember);
			if (selectedTeam.getName().equals(("New team"))) {
				((TeamCompetition)Globals.currentCompetition).put(selectedTeam, "-");
				selectedTeam.setName("Team name");
			}
			int stdIndex = ((TeamCompetition)Globals.currentCompetition).getParticipants().iterator().next().indexOf(teamMember);
			fill();
			participantTableView.getSelectionModel().select(stdIndex);
		}
		else {
			((StudentCompetition)Globals.currentCompetition).put(new Student("ID","Name","Major"), "-");
			fill();
			participantTableView.getSelectionModel().select(participantTableView.getItems().size()-1);
			participantTableView.getSelectionModel().getSelectedItem();
		}
	}
	
	private void removeBtnClicked() {
		if (participantTableView.getSelectionModel().isEmpty()) return;
		int selectedIndex = participantTableView.getSelectionModel().getSelectedIndex();
		if (Globals.currentCompetition instanceof TeamCompetition) {
			List<Student> students = IteratorUtils.toList(Globals.currentCompetition.getStudents().iterator());
			List<Team> teams = IteratorUtils.toList(((TeamCompetition)Globals.currentCompetition).getParticipants().iterator());
			int stdIndex = Integer.parseInt(studNumCol.getCellData(selectedIndex)) - 1;
			int teamIndex = Integer.parseInt(this.teams.getCellData(selectedIndex)) - 1;
			teams.get(teamIndex).remove(students.get(stdIndex));
			if (teams.get(teamIndex).isEmpty()) ((TeamCompetition) Globals.currentCompetition).remove(teams.get(teamIndex));
		}
		else {
			List<Student> students = IteratorUtils.toList(Globals.currentCompetition.getStudents().iterator());
			int stdIndex = Integer.parseInt(studNumCol.getCellData(selectedIndex)) - 1;
			((StudentCompetition) Globals.currentCompetition).remove(students.get(stdIndex));
		}
		fill();
		if (selectedIndex >= participantTableView.getItems().size()) selectedIndex = participantTableView.getItems().size()-1;
		participantTableView.getSelectionModel().select(selectedIndex);
	}
	
	public void teamCboUpdate(TeamCompetition competition) {
		ObservableList<Team> teamsObsLst= FXCollections.observableArrayList(competition.getParticipants());
		teamsCbo.setItems(teamsObsLst);
		teamsCbo.getItems().add(0,new Team("New team"));
	}
}
