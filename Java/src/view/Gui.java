package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import motor.Connection;
import motor.TimeServer;

public class Gui extends Application {

	private TimeServer ts;
	private Label bat, speedG, speedD, isForward;
	private Series<Number, Number> leftSeries, rightSeries;
	private Connection con;
	
	@Override
	public void start(Stage rootStage) throws Exception
	{
	
		rootStage.setTitle("Test JavaFx");
		BorderPane bp = new BorderPane();

		Scene backScene = new Scene(bp, Color.ANTIQUEWHITE);
		bp.setTop(createTopPane());
		bp.setLeft(createLeftPane());
		bp.setCenter(createCenterPane());
		rootStage.setScene(backScene);
		rootStage.sizeToScene();
		
		
		//String address = InetAddress.getByName("raspberrypi").getHostAddress();
		String host = "192.168.43.172";
		int port = 4243;

		ts = new TimeServer(host, port, this);
		ts.open();
		rootStage.show();
		
	}
		
	@Override
	public void stop() throws Exception {
		ts.close();
		super.stop();
	}

	private GridPane createLeftPane()
	{
		GridPane gp = new GridPane();
		gp.setStyle("-fx-background-color : #C0C0C0;");

		gp.setVgap(15);
		gp.add(new Label("Battery Level : "), 0, 0);
		bat = new Label("1");
		gp.add(bat, 1, 0);
		
		
		gp.add(new Label("Is Forward : "), 0, 1);
		isForward = new Label("True");
		gp.add(isForward, 1, 1);
		
		
		gp.add(new Label("Left Speed : "), 0, 2);
		speedG = new Label("1");
		gp.add(speedG, 1, 2);
		
		
		gp.add(new Label("Battery Level : "), 0, 3);
		speedD = new Label("1");
		gp.add(speedD, 1, 3);
		
		gp.setPadding(new Insets(10, 10, 10, 10));
		
		return gp;
		
	}
	
	private Pane createTopPane()
	{
		Pane p = new Pane();
		p.getChildren().add(new Label("Roboto"));
		return p;
		
	}
	
	private Pane createCenterPane()
	{
		Pane p = new Pane();
		
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Time");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Speed");
		LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
		
		leftSeries = new Series<>();
		leftSeries.setName("Left Speed");
		
		rightSeries = new Series<>();
		rightSeries.setName("Right Speed");

		chart.getData().add(leftSeries);
		chart.getData().add(rightSeries);
		
		
		p.getChildren().add(chart);
		
		return p;
	}
	
	private void getData()
	{
		String data = con.getData();
	}

	public final Label getBat() {
		return bat;
	}

	public final Label getSpeedG() {
		return speedG;
	}

	public final Label getSpeedD() {
		return speedD;
	}

	public final Label getIsForward() {
		return isForward;
	}

	public final Series<Number, Number> getLeftSeries() {
		return leftSeries;
	}

	public final Series<Number, Number> getRightSeries() {
		return rightSeries;
	}

	public final Connection getCon() {
		return con;
	}
	
	
}
