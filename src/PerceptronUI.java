import javafx.application.Application;
import javafx.geometry.Insets; import javafx.scene.*;
import javafx.scene.control.*; import javafx.scene.layout.*;
import javafx.scene.paint.*; import javafx.scene.shape.*;
import javafx.stage.Stage; import java.text.DecimalFormat;

public class PerceptronUI extends Application {
    private Point[] trainDataSet=new Point[500];
    private Point[] testDataSet=new Point[500];
    private double leaningRate=0.5;
    private int epochs=0;
    private int numInputs=2;
    private Button buttonTrain=new Button("Train Again");
    private Label labelEpochs=new Label("Epochs");
    private Label labelEpochsValue=new Label("0");
    private Label labelAcc=new Label("Accuracy:");
    private Label labelAccValue=new Label("");
    Perceptron brain=new Perceptron(numInputs,leaningRate);
    Group group;
    BorderPane borderPane;
    @Override
    public void start(Stage primaryStage) throws Exception {
        borderPane=new BorderPane();
        HBox hBox=new HBox();hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(buttonTrain,labelEpochs,labelEpochsValue,labelAcc,labelAccValue);

        borderPane.setTop(hBox);

        for (int i = 0; i <testDataSet.length ; i++) {
            testDataSet[i]=new Point();
        }
        for (int i = 0; i <trainDataSet.length ; i++) {
            trainDataSet[i]=new Point();
        }
        group=new Group();
        borderPane.setCenter(group);

        testModel(testDataSet);

        Scene scene=new Scene(borderPane,800,600);
        primaryStage.setScene(scene);
        primaryStage.show();
        buttonTrain.setOnAction((e)->{

            trainModel(trainDataSet);
            testModel(testDataSet);
        });
    }
    public void viewData(){
        group.getChildren().clear();
        Rectangle rectangle=new Rectangle(Params.STAGE_X,Params.STAGE_Y,Params.STAGE_WIDTH,Params.STAGE_HEIGHT);
        rectangle.setFill(Color.rgb(255,255,255));
        rectangle.setStroke(Color.BLACK);
        Point p1=new Point(-1,Params.transform(-1));
        Point p2=new Point(1,Params.transform(1));
        Line line=new Line(p1.xPosition(),p1.yPosition(),p2.xPosition(),p2.yPosition());
        line.setStroke(Color.BLACK);
        Point p3=new Point(-1,brain.guessOutputValue(-1));
        Point p4=new Point(1,brain.guessOutputValue(1));
        Line line2=new Line(p3.xPosition(),p3.yPosition(),p4.xPosition(),p4.yPosition());
        line2.setStroke(Color.RED);
        group.getChildren().add(rectangle);
        group.getChildren().add(line);
        group.getChildren().add(line2);
    }
    public void testModel(Point[] testDataSet){
        viewData();
        double numberOfSuccess=0;
        for (int i = 0; i <testDataSet.length ; i++) {
            int output=brain.predict(new double[]{testDataSet[i].x,testDataSet[i].y});
            if(output==testDataSet[i].label){
                ++numberOfSuccess;
            }
            testDataSet[i].label=output;
            testDataSet[i].show(group);
        }
        double acc=(numberOfSuccess/testDataSet.length)*100;
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        labelAccValue.setText(String.valueOf(decimalFormat.format(acc))+" %");
    }
    public void trainModel(Point[] dataSet){
        ++epochs;
        for (int i = 0; i <trainDataSet.length ; i++) {
            brain.train(new double[]{trainDataSet[i].x,trainDataSet[i].y},trainDataSet[i].label);
            //trainDataSet[i].show(group);
        }
        labelEpochsValue.setText(String.valueOf(epochs));
    }
    public static void main(String[] args) {
        launch(PerceptronUI.class);
    }
}
