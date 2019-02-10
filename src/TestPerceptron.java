public class TestPerceptron {
    public static void main(String[] args) {
        Perceptron brain=new Perceptron(2,0.5);
        System.out.println(brain.predict(new double[]{1-Math.random()*2,1-Math.random()*2}));
        Point[] trainDataset=new Point[100];
        for (int i = 0; i <trainDataset.length ; i++) {
            trainDataset[i]=new Point();
        }
        int epochs=100;
        for (int i = 0; i <epochs ; i++) {
            System.out.println("Epochs:"+i);
            for (int j = 0; j <trainDataset.length ; j++) {
                brain.train(new double[]{trainDataset[j].x,trainDataset[j].y},trainDataset[j].label);
            }
        }
        Point[] testDataset=new Point[100];
        for (int i = 0; i <testDataset.length ; i++) {
            testDataset[i]=new Point();
        }
        double numberCorrect=0;
        double numberWrong=0;
        for (int i = 0; i <testDataset.length ; i++) {
           int output=brain.predict(new double[]{testDataset[i].x,testDataset[i].y});
           if(output==testDataset[i].label) ++numberCorrect;
           else ++numberWrong;
        }
        System.out.println("Accuracy:"+(numberCorrect/testDataset.length)*100+"%");
    }
}
