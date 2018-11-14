package pl.kpie.creditcard;

import java.math.BigDecimal;

class CreditCard {

    private boolean blocked = false;
    private BigDecimal limit;
    private BigDecimal balance = BigDecimal.ZERO;
    private String id;

    public CreditCard() {
        this.id = "random_string";
    }

    public CreditCard(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void assignLimit(BigDecimal money) {
        if (isLimitAlreadyAsigned()) {
            throw new LimitAlreadyAssignedException();
        }

        if (isLimitBelowOrEquals0(money)) {
            throw new InsufficientCreditLimitException();
        }

        this.limit = money;
        this.balance = money;
    }

    private boolean isLimitBelowOrEquals0(BigDecimal money) {
        return money.compareTo(BigDecimal.ZERO) <= 0;
    }

    private boolean isLimitAlreadyAsigned() {
        return limit != null;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void block() {
        this.blocked = true;
    }

    public boolean isBlocked() {
        return this.blocked;
    }


    public void repay(BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new CantRepayNegativeAmountException();
        }
        balance = balance.add(money);
    }

    public BigDecimal getBalance() {
        return balance;
    }
}