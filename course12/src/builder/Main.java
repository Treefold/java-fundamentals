package builder;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount.BankAccountBuilder()
            .withAccountNo("111111")
            .withName("Andrei")
            .withEmail("a@a.a")
            .acceptNewsLetter(true)
            .build();
        System.out.println(bankAccount);
    }

}
