public class Perceptron {
    private double[] weights;
    private double bias=1;
    private double learningRate;
    public Perceptron(int numInputs, double learningRate){
        this.learningRate=learningRate;
        weights=new double[numInputs+1];
        for (int i = 0; i <weights.length ; i++) {
            weights[i]=1-Math.random()*2;
        }
    }
    public int predict(double[] input){
        double sum=bias*weights[0];
        for (int i = 1; i <weights.length ; i++) {
            sum+=input[i-1]*weights[i];
        }
        int output=activation(sum);
        return output;
    }
    public int activation(double n){
        return (n>=0)?1:-1;
    }
    public void train(double[] input,int target){
        int guess=predict(input);
        double error=target-guess;
        for (int i = 0; i <weights.length ; i++) {
            double dataInput=(i==0)?bias:input[i-1];
            weights[i]+=error*dataInput*learningRate;
        }
    }

    public double guessOutputValue(double x){
        return -(weights[0]/weights[2])-(weights[1]/weights[2])*x;
    }
}
