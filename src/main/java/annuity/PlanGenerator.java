package annuity;

import org.javamoney.moneta.FastMoney;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryOperator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlanGenerator{

    private double loanAmount;
    private double nominalRate;
    private int duration;
    private double im;
    private FastMoney initialOutstandingPrincipal;
    private FastMoney annuity;
    private List<Installment> installments = new ArrayList();
    private Calendar calendar;

    /**
     *
     * @param payoutDate first installement date
     * @param loanAmount  loan amout for installementcalculation
     * @param nominalRate  nominal rate 5.0 etc
     * @param duration  duration in months
     * @return
     */

    public List<Installment> generate(Date payoutDate, double loanAmount, double nominalRate, int duration){
        this.calendar = Calendar.getInstance();
        calendar.setTime(payoutDate);
        this.loanAmount = loanAmount;
        this.nominalRate = nominalRate;
        this.duration = duration;
        this.im = nominalRate / 12 / 100;
        this.initialOutstandingPrincipal = getFastMoney(loanAmount);
        calculateAnnuity();
        calculateInstallment();
        return installments;
    }

    public FastMoney calculateAnnuity() {
        annuity = initialOutstandingPrincipal.multiply(im).multiply(Math.pow(1 + im, duration)).divide(Math.pow(1 + im, duration) - 1);
        annuity = annuity.with(round);
        return annuity;
    }


    private void calculateInstallment() {
        for(int i=0; i < duration; i++) {
            FastMoney interest = initialOutstandingPrincipal.multiply(im).with(round);
            FastMoney principal = getPrincipal(annuity, interest, initialOutstandingPrincipal);
            FastMoney remainingOutstandingPrincipal = initialOutstandingPrincipal.subtract(principal);
            Installment installment = new Installment(calendar.getTime(), principal, interest, initialOutstandingPrincipal, remainingOutstandingPrincipal);
            installments.add(installment);
            initialOutstandingPrincipal = remainingOutstandingPrincipal;
            calendar.add(Calendar.MONTH, 1);
        }
    }

    public FastMoney getPrincipal(FastMoney annuity, FastMoney interest, FastMoney initialOutstandingPrincipal  ){
        if(annuity.subtract(interest).isGreaterThan(initialOutstandingPrincipal)){
            return initialOutstandingPrincipal;
        }
        else{
            return annuity.subtract(interest).with(round);
        }
    }

    private CurrencyUnit eur = Monetary.getCurrency("EUR");
    private MonetaryOperator round = Monetary.getDefaultRounding();
    private FastMoney getFastMoney(Number number){
        return FastMoney.of(number, eur);
    }

}
