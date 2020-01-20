package lambdasinaction.chap2.my;

/**
 * @Author: 52483
 * @CreateDate: 2019/11/6 20:40:08
 * @Version: v1.0
 * @Description:
 */
public class Apple {
    private int weight = 0;
    private String color = "";

    public Apple() {
    }

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
