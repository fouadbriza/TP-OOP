public class FeedingProgram {
    private String feedtype;
    private double quantityPerMeal;

    public FeedingProgram(String feedtype, double quantityPerMeal) {
        this.feedtype = feedtype;
        this.quantityPerMeal = quantityPerMeal;
    }

    public FeedingProgram() {
       
    }

    public String getFeedtype() {
        return feedtype;
    }

    public double getQuantityPerMeal() {
        return quantityPerMeal;
    }

    public String toString() {
        return "FeedingProgram{" +
                "feedtype='" + feedtype + '\'' +
                ", quantityPerMeal=" + quantityPerMeal +
                '}';
    }

    public void setFeedingProgram(String feedtype, double quantityPerMeal) {
        this.feedtype = feedtype;
        this.quantityPerMeal = quantityPerMeal;
    }
}
