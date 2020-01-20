package lambdasinaction.chap2.my;

/**
 * @Author: 52483
 * @CreateDate: 2019/11/8 21:11:17
 * @Version: v1.0
 * @Description:
 */
public class AppleHeavyWeightPredicate implements  ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 3;
    }
}
