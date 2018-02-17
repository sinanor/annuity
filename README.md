This small PlanGenerator class calculates annuity and generate installements as Installement objects.

Simply we can use below class to generate installements.

List<Installment> installments = new PlanGenerator().generate(new Date(), 5000.0, 5.0, 24);

PlanGeneratorTest class has some usage and test cases.


Implementation Details.

Unfortunetely Java has not a currency type for currency calculations and I thought to use BigDecimal for calculations,
but it is 10-15 times slower than long or double. One option would be hold the currency as cents, it means when we want to
hold 5.000 Euro we store it as 500.000 Euro so calculations has enough accuracy.

Then I looked internet and I saw a new JSR named  Money and Currency API (JSR 354). Some says its going to be part of
Java 10. For my needs FastMoney implementation has enough accuracy.