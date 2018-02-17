package com.lendico.annuity;

import annuity.Installment;
import annuity.PlanGenerator;
import org.javamoney.moneta.FastMoney;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlanGeneratorTest {

    @Test
    public void testGeneratePlanInstallmentSize(){
        int duration = 24;
        List<Installment> installments = new PlanGenerator().generate(new Date(), 5000.0, 5.0, duration);
        assertEquals(duration, installments.size());
        installments.stream().forEach(System.out::println);
    }

    @Test
    public void testLastRemainingOutstandingPrincipalisZero(){
        int duration = 24;
        List<Installment> installments = new PlanGenerator().generate(new Date(), 5000.0, 5.0, duration);
        assertEquals(FastMoney.of(0, "EUR"), installments.get(installments.size() - 1).getRemainingOutstandingPrincipal());
    }

    @Test
    public void testLastInstallementDates(){
        int duration = 24;
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Installment> installments = new PlanGenerator().generate(date, 5000.0, 5.0, duration);
        for(int i = 0; i < duration; i++){
            Installment ins = installments.get(i);
            assertEquals(calendar.getTime(), ins.getDate());
            calendar.add(Calendar.MONTH, 1);
        }
    }

    @Test
    public void testGetPrincipal(){
        FastMoney annuty = FastMoney.of(100, "EUR");
        FastMoney interest = FastMoney.of(20, "EUR");
        FastMoney initialOutstandingPrincipal = FastMoney.of(50, "EUR");
        FastMoney principal =  new PlanGenerator().getPrincipal(annuty, interest, initialOutstandingPrincipal);
        assertEquals(FastMoney.of(50, "EUR"), principal);
        initialOutstandingPrincipal = FastMoney.of(10, "EUR");
        principal =  new PlanGenerator().getPrincipal(annuty, interest, initialOutstandingPrincipal);
        assertEquals(FastMoney.of(10, "EUR"), principal);
    }

}
