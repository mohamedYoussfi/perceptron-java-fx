public interface Params {
    public static final int STAGE_WIDTH=500;
    public static final int STAGE_HEIGHT=500;
    public static final int STAGE_X=20;
    public static final int STAGE_Y=20;
    public static double transform(double x){
        double y=0.3*x-0.2;
        return y;
    }
}
