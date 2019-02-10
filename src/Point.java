import javafx.scene.*; import javafx.scene.paint.*;
import javafx.scene.shape.*;
public class Point {
    public double x;  public double y;
    public int label; public Shape shape;

    public Point(){
        this.x=1-Math.random()*2;
        this.y=1-Math.random()*2;
        this.label=(y>=Params.transform(x))?1:-1;
    }
    public Point(double x,double y){
        this.x=x;
        this.y=y;
        this.label=(y>=Params.transform(x))?1:-1;
    }


    public double xPosition(){
        int minX=-1;int maxX=1;
       return  Params.STAGE_X+(x-minX)*Params.STAGE_WIDTH/(maxX-minX);
    }
    public double yPosition(){
        int minY=-1;int maxY=1;
        return  Params.STAGE_Y+ Params.STAGE_HEIGHT-((y-minY)*Params.STAGE_HEIGHT/(maxY-minY));
    }
    public void show(Group group){
        if(label==1){
            shape=new Circle(xPosition(),yPosition(),5);
            shape.setFill(Color.RED);
        }
        else{
            shape=new Rectangle(xPosition()-5,yPosition()-5,10,10);
            shape.setFill(Color.GREEN);
        }
        group.getChildren().add(shape);
    }
    @Override
    public String toString() {
        return "Point{" +"x=" + x +", y=" + y +", label=" + label +'}';
    }
}

