package enumExample;

public class Pizza {
    public enum PizzaStatus {
        ORDERED(2), READY(1), DELIVERED(0);

        private int hoursToDeliver;
        PizzaStatus (int hoursToDeliver) {
            this.hoursToDeliver = hoursToDeliver;
        }

        public int getHoursToDeliver() {
            return hoursToDeliver;
        }
    }

    private PizzaStatus pizzaStatus;

    public PizzaStatus getPizzaStatus() {
        return pizzaStatus;
    }

    public void setPizzaStatus(PizzaStatus pizzaStatus) {
        this.pizzaStatus = pizzaStatus;
    }

    public boolean isDeliverable() {
        return this.pizzaStatus == PizzaStatus.READY;
    }

    public int getTimeOfDeliveryInHours() {
        switch (pizzaStatus){
            case ORDERED:   return 2;
            case READY:     return 1;
            case DELIVERED: return 0;
            default:        return 0;
        }
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "pizzaStatus=" + pizzaStatus +
                '}';
    }
}
