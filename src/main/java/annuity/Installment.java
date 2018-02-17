package annuity;

import org.javamoney.moneta.FastMoney;

import java.util.Date;


public class Installment {
    private Date date;
    private FastMoney borrowerPaymentAmount;
    private FastMoney principal;
    private FastMoney interest;
    private FastMoney initialOutstandingPrincipal;
    private FastMoney remainingOutstandingPrincipal;

    public Installment(Date date, FastMoney principal, FastMoney interest, FastMoney initialOutstandingPrincipal, FastMoney remainingOutstandingPrincipal) {
        this.date = date;
        this.borrowerPaymentAmount = principal.add(interest);
        this.principal = principal;
        this.interest = interest;
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }

    @Override
    public String toString() {
        return "Installement{" +
                "date=" + date +
                ", borrowerPaymentAmount=" + borrowerPaymentAmount +
                ", principal=" + principal +
                ", interest=" + interest +
                ", initialOutstandingPrincipal=" + initialOutstandingPrincipal +
                ", remainingOutstandingPrincipal=" + remainingOutstandingPrincipal +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FastMoney getBorrowerPaymentAmount() {
        return borrowerPaymentAmount;
    }

    public void setBorrowerPaymentAmount(FastMoney borrowerPaymentAmount) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
    }

    public FastMoney getPrincipal() {
        return principal;
    }

    public void setPrincipal(FastMoney principal) {
        this.principal = principal;
    }

    public FastMoney getInterest() {
        return interest;
    }

    public void setInterest(FastMoney interest) {
        this.interest = interest;
    }

    public FastMoney getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public void setInitialOutstandingPrincipal(FastMoney initialOutstandingPrincipal) {
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
    }

    public FastMoney getRemainingOutstandingPrincipal() {
        return remainingOutstandingPrincipal;
    }

    public void setRemainingOutstandingPrincipal(FastMoney remainingOutstandingPrincipal) {
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }
}
