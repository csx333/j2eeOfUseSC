package water.ustc.bean;

/**
 * @author : csx
 * @description : AccountBean懒属性
 * @date : 2018/12/23 23:56
 */
public class AccountMoney {
    private String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "AccountMoney{" +
                "money='" + money + '\'' +
                '}';
    }
}
