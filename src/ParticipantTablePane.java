import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class ParticipantTablePane extends VBox {
	public TableView participantTableView = new TableView(); // temporary public 
	private TableColumn<Map, String> ids = new TableColumn<>("Student ID");
	private TableColumn<Map, String> names = new TableColumn<>("Student Name");
	private TableColumn<Map, String> majors = new TableColumn<>("Major");
	private TableColumn<Map, String> teams = new TableColumn<>("Team");
	private TableColumn<Map, String> teamsNames = new TableColumn<>("Team Name");
	private TableColumn<Map, String> ranks = new TableColumn<>("Rank");
	private TableColumn<Map, Void> emailColumn = new TableColumn<>("Email");
	int cellWidth = 80;

	public ParticipantTablePane() {
		VBox.setMargin(participantTableView, new Insets(10, 50, 10, 50));
		ids.setMinWidth(cellWidth);
		names.setMinWidth(cellWidth * 2);
		majors.setMinWidth(cellWidth);
		teams.setMinWidth(cellWidth);
		teamsNames.setMinWidth(cellWidth * 2);
		ranks.setMinWidth(cellWidth);
		emailColumn.setMinWidth(cellWidth);

		ids.setCellValueFactory(new MapValueFactory<>(ids.getText()));
		names.setCellValueFactory(new MapValueFactory<>(names.getText()));
		majors.setCellValueFactory(new MapValueFactory<>(majors.getText()));
		teams.setCellValueFactory(new MapValueFactory<>(teams.getText()));
		teamsNames.setCellValueFactory(new MapValueFactory<>(teamsNames.getText()));
		ranks.setCellValueFactory(new MapValueFactory<>(ranks.getText()));
		emailColumn();
		participantTableView.getColumns().addAll(ids, names, majors, teams, teamsNames, ranks, emailColumn);
		getChildren().add(participantTableView);
		setAlignment(Pos.CENTER);
	}

	public void fill() {
		participantTableView.setItems(generateDataInMap());
		Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap = (
				TableColumn<Map, String> p) -> new TextFieldTableCell<>(new StringConverter() {
					@Override
					public String toString(Object t) {
						return t.toString();
					}

					@Override
					public Object fromString(String string) {
						return string;
					}
				});
		ids.setCellFactory(cellFactoryForMap);
		names.setCellFactory(cellFactoryForMap);
		majors.setCellFactory(cellFactoryForMap);
		teams.setCellFactory(cellFactoryForMap);
		teamsNames.setCellFactory(cellFactoryForMap);
		ranks.setCellFactory(cellFactoryForMap);

		if (Globals.currentCompetition != null) {
			if (Globals.currentCompetition instanceof TeamCompetition) {
				teams.setVisible(true);
				teamsNames.setVisible(true);
				participantTableView.setMaxWidth(cellWidth * 9);
			} else {
				teams.setVisible(false);
				teamsNames.setVisible(false);
				participantTableView.setMaxWidth(cellWidth * 6);
			}
		}
	}

	private ObservableList<Map> generateDataInMap() {
		ObservableList<Map> alldata = FXCollections.observableArrayList();
		// TODO Solve the null value Exception
		Iterator<?> particpants = Globals.currentCompetition.getParticipants().iterator();
		int teamNum = 1;
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
					dataRow.put(ids.getText(), currentStudent.getId());
					dataRow.put(names.getText(), currentStudent.getName());
					dataRow.put(majors.getText(), currentStudent.getMajor());
					dataRow.put(teams.getText(), Integer.toString(teamNum));
					dataRow.put(teamsNames.getText(), currentTeam.getName());
					dataRow.put(ranks.getText(),
							((Competition<Participant>) Globals.currentCompetition).getResult(currentTeam));
					alldata.add(dataRow);
				}

			} else {
				currentStudent = ((Student) particpants.next());
				dataRow.put(ids.getText(), currentStudent.getId());
				dataRow.put(names.getText(), currentStudent.getName());
				dataRow.put(majors.getText(), currentStudent.getMajor());
				dataRow.put(teams.getText(), "-");
				dataRow.put(teamsNames.getText(), "-");
				dataRow.put(ranks.getText(),
						((Competition<Participant>) Globals.currentCompetition).getResult(currentStudent));
				alldata.add(dataRow);
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
}
