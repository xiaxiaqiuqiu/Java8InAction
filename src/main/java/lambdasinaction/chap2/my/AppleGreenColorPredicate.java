package lambdasinaction.chap2.my;

/**
 * @Author: 52483
 * @CreateDate: 2019/11/8 21:26:44
 * @Version: v1.0
 * @Description:
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
