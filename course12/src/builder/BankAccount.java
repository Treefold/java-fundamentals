package builder;

public class BankAccount {
    private String accountNo;
    private String name;
    private String email;
    private boolean acceptNewsletter;

    public BankAccount (BankAccountBuilder bankAccountBuilder) {
        this.accountNo = bankAccountBuilder.accountNo;
        this.name = bankAccountBuilder.name;
        this.email = bankAccountBuilder.email;
        this.acceptNewsletter = bankAccountBuilder.acceptNewsletter;
    }

    public static class BankAccountBuilder {
        private String accountNo;
        private String name;
        private String email;
        private boolean acceptNewsletter;

        public BankAccountBuilder withAccountNo(String accountNo) {
            this.accountNo = accountNo;
            return this;
        }

        public BankAccountBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public BankAccountBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public BankAccountBuilder acceptNewsLetter(boolean acceptNewsletter) {
            this.acceptNewsletter = acceptNewsletter;
            return this;
        }

        public BankAccount build() {
            return new BankAccount(this);
        }
    }

}
